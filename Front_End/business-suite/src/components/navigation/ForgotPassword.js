import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../../styles/Login.css"
import UserServices from "../../services/UserServices";
import hidePass from "../../images/loginpage/hidePass.svg";
import showPass from "../../images/loginpage/showPass.svg";
const ForgotPassword = () => {

    let [changePass, setChangePass] = useState({ userName: "", password: "", otp: "" });

    let [genOTPStatus, setGenOTPStatus] = useState(false);
    let [showPassStatus, setShowPassStatus] = useState(false);
    let [messageStatus, setMessageStatus] = useState(false);
    let [respMessage, setRespMessage] = useState("");
    const navigate = useNavigate();

    const handlePassChange = (event) => {
        const { name, value } = event.target
        setChangePass({ ...changePass, [name]: value })
    }


    const verifyOTP = () => {
        UserServices.verifyOTPAndChangePass(changePass).then((resp) => {
            console.log(resp.data)
            setRespMessage(resp.data.message)
            setMessageStatus(true);
            navigate("/login")
        }).catch((err) => {
            console.log(err)
            setMessageStatus(true)
        })
    }

    const sendOTP = () => {
        setGenOTPStatus(true);
        UserServices.sendPassChangeOTP(changePass.userName).then((resp) => {
            console.log(resp)
        }).catch((err) => {
            console.log("Send Email", err.toJSON())
        })
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

    const reloadPage = () => {
        location.reload();
    }
    return (
        <div>
            <form method="post">
                <table>
                    <thead>
                        <tr>
                            <th colSpan={4}>
                                <div className="formTitle">
                                    Change Password
                                </div>
                            </th>
                        </tr>
                        <tr><td> </td></tr>
                        <tr><td> </td></tr>
                        <tr><td> </td></tr>
                        <tr><td> </td></tr>
                        <tr><td> </td></tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <div className="formFont">
                                    <label htmlFor="cngPassUname">User Name</label>
                                </div>
                            </td>
                            <td>
                                <input type="text" id="cngPassUname" className="InputBox" placeholder="User Name" name="userName" onChange={handlePassChange} value={changePass.userName}></input>
                            </td>
                        </tr>
                    </tbody>
                    {
                        !genOTPStatus ?

                            <tbody>

                                <tr>
                                    <td colSpan={4}>
                                        {
                                            changePass.userName != "" ?
                                                < button type="button" className="SignInButton" onClick={sendOTP}>Send OTP</button> :
                                                ""
                                        }
                                    </td>
                                </tr>

                            </tbody>

                            :

                            <tbody>

                                <tr>
                                    <td>
                                        <div className="formFont">
                                            <label htmlFor="cngPasswordid">Password</label>
                                        </div>
                                    </td>
                                    <td>
                                        <input type="password" id="cngPasswordid" className="InputBox" placeholder="New Password" name="password" onChange={handlePassChange} value={changePass.password}></input>
                                    </td>
                                    <td>
                                        {!showPassStatus ? <div className="showPassToggle" onClick={showPassword}><img src={showPass} className="containPasswordToggle"></img></div> : <div className="hidePassToggle" onClick={hidePassword}><img src={hidePass} className="containPasswordToggle"></img></div>}
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div className="formFont">
                                            <label htmlFor="cngPassOtp">Enter OTP</label>
                                        </div>
                                    </td>
                                    <td>
                                        <input type="text" id="cngPassOtp" className="InputBox" placeholder="OTP" name="otp" onChange={handlePassChange} value={changePass.otp}></input>
                                    </td>
                                </tr>
                                <tr><td></td></tr>
                                <tr><td></td></tr>
                                {
                                    !messageStatus ? "" :
                                        <tr>
                                            <td colSpan={4}>
                                                <input className="messageBody" value={respMessage} readOnly></input>
                                            </td>
                                        </tr>
                                }
                                <tr>
                                    <td>

                                    </td>
                                </tr>
                                <tr>
                                    <td>

                                    </td>
                                </tr>
                                <tr>
                                    <td colSpan={4}>
                                        <button type="button" className="SignInButton" onClick={verifyOTP}>Verify OTP</button>
                                        &nbsp;
                                        &nbsp;
                                        &nbsp;
                                        &nbsp;
                                        <button type="button" className="SignInButton" onClick={reloadPage}>LOGIN</button>
                                    </td>
                                </tr>
                            </tbody>
                    }
                </table>
            </form>
        </div >
    )
}

export default ForgotPassword;