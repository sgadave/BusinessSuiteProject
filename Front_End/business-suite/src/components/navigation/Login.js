import { useState } from "react";
import AuthenticationService from "../../services/AuthenticationService"
import {  useNavigate } from "react-router-dom";
import "../../styles/Login.css"
import LoginImg from "../../images/loginpage/LoginImg.jpeg"
import hidePass from "../../images/loginpage/hidePass.svg";
import showPass from "../../images/loginpage/showPass.svg";
import ForgotPassword from "./ForgotPassword";

const Login = () => {
    let [userCred, setUserCred] = useState({ userName: "", password: "" });
    let [forgotPassStatus, setForgotPassStatus] = useState(false);
    let [showPassStatus, setShowPassStatus] = useState(false);
    const navigate = useNavigate();
    const validateUser = (event) => {
        event.preventDefault();
        console.log("in validate")
        AuthenticationService.authenticateUser(userCred)
            .then((resp) => {
                console.log("Auth Successful")
                console.log("User Role", resp.data.role)
                AuthenticationService.storeUserDetails(userCred.userName, resp.data);
                console.log("Login User Role Check", sessionStorage.getItem("user_role"))
                navigate("/dashboard")
            })
            .catch((err) => {
                console.log("Failed Auth", err);
                navigate("/login")
            })
    }

    const handleChange = (event) => {
        const { name, value } = event.target
        setUserCred({ ...userCred, [name]: value })
    }

    const updateForgotPassStatus = () => {
        setForgotPassStatus(true)
        console.log("Forgot Pass Status", forgotPassStatus)
    }


    const showPassword = () => {
        if (document.getElementById("passwordid") != null) {
            document.getElementById("passwordid").type = "text"
        } else {
            document.getElementById("cngPasswordid").type = "text"
        }
        setShowPassStatus(true);
    }

    const hidePassword = () => {
        if (document.getElementById("passwordid") != null) {
            document.getElementById("passwordid").type = "password"
        } else {
            document.getElementById("cngPasswordid").type = "password"
        }
        setShowPassStatus(false);
    }



    return (
        <div className="LoginPageBody">
            <div className="LoginPageImgContainer">
                <img src={LoginImg} alt="Login Page" className="LoginPageImg"></img>
            </div>
            <div className="LoginFormBody">
                <div className="LoginForm">
                    {
                        !forgotPassStatus ?
                            <form action="hello" onSubmit={validateUser} method="POST" >
                                <table>
                                    <thead>
                                        <tr>
                                            <th colSpan={4}>
                                                <div className="formTitle">
                                                    Login Form
                                                </div>
                                            </th>
                                        </tr>
                                        <tr><td> </td><td> </td><td> </td><td> </td></tr>
                                        <tr><td> </td></tr>
                                        <tr><td> </td></tr>
                                        <tr><td> </td></tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <div className="formFont">
                                                    <label htmlFor="loginid">User Name</label>
                                                </div>
                                            </td>
                                            <td>
                                                <input type="text" id="loginid" className="InputBox" placeholder="User Name" name="userName" onChange={handleChange} value={userCred.userName}></input>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div className="formFont">
                                                    <label htmlFor="passwordid">Password</label>
                                                </div>
                                            </td>
                                            <td>
                                                <input type="password" id="passwordid" className="InputBox" placeholder="Password" name="password" onChange={handleChange} value={userCred.password}></input>
                                            </td>
                                            <td>
                                                {!showPassStatus ? <div className="showPassToggle" onClick={showPassword}><img src={showPass} className="containPasswordToggle"></img></div> : <div className="hidePassToggle" onClick={hidePassword}><img src={hidePass} className="containPasswordToggle"></img></div>}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td><div className="forgotPass">
                                                <button type="button" className="Forgotpassbutton" onClick={updateForgotPassStatus}>forgot password?</button>
                                            </div></td>
                                        </tr>
                                        <tr>
                                            <td colSpan={4}>
                                                <button type="submit" className="SignInButton">Sign In</button>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </form>
                            :
                            <ForgotPassword></ForgotPassword>
                    }
                </div>
            </div>

        </div >
    )
}

export default Login;