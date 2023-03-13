import "../../styles/NavBar.css"
import "../../styles/commonStyle.css"
import { Link, useNavigate } from "react-router-dom";
import logo from "../../images/common/logo.png"
import profile from "../../images/common/profile.svg"
import { useEffect, useState } from "react";
import AuthenticationService from "../../services/AuthenticationService";

const NavBar = () => {
    let [bool, changebool] = useState(true);
    let [Profilebool, changeProfileBool] = useState(true);

    const BoolFalse = () => {
        console.log(bool)
        changebool(false);
        console.log(bool)
    }
    const BoolTrue = () => {
        console.log(bool)
        changebool(true);
        console.log(bool)
    }

    const ProfileBoolFalse = () => {
        console.log(Profilebool)
        changeProfileBool(false);
        console.log(Profilebool)
    }
    const ProfileBoolTrue = () => {
        console.log(Profilebool)
        changeProfileBool(true);
        console.log(Profilebool)
    }

    const navigate = useNavigate();
    const logoutUser = (event) => {
        event.preventDefault();
        ProfileBoolTrue();
        AuthenticationService.removeUserDetails();
        navigate("/")
    }

    return (
        <div className="navbar">


            <div className="homeBody">
                <div className="logoBody">
                    <Link to="/">
                        <img src={logo} alt="Logo" className="logoImg"></img>
                    </Link>
                </div>
                <div className="Name">
                    <Link to="/">
                        <div className="homeButton">SP Solutions</div>
                    </Link>
                </div>
            </div>
            <div className="menuBody" >
                <div className="navLinkBody" >
                    <Link to="/aboutteam">
                        <div type="button" className="navButton" onClick={BoolTrue} >About Team</div>
                    </Link>
                </div>
                <div className="navLinkBody">
                    <Link to="/contactus">
                        <div type="button" className="navButton">Contact Us</div>
                    </Link>
                </div>
                <div className="navLinkBody">
                    <Link to="/careers">
                        <div type="button" className="navButton">Careers</div>
                    </Link>
                </div>
                <div className="navLinkBody">
                    <Link to="/signup">
                        <button type="button" className="SignUp">Sign Up</button>
                    </Link>
                </div>

                {AuthenticationService.isUserLoggedIn() === true ? <div className="loginBody">
                    <div className="navLinkBody" onMouseLeave={ProfileBoolTrue}>
                        {/* <button type="button" className="navButton"  > */}
                        <div className="profileDiv">
                            <img onMouseOver={ProfileBoolFalse} src={profile} className="profileIconBody"></img>
                        </div>
                        {/* </button> */}
                        {!Profilebool ?
                            <table className="profileSubMenu loggedProfileSubMenu">
                                <tbody>
                                    <tr>
                                        <td>
                                            <Link to="/dashboard">
                                                <button type="button" className="navButton" onClick={ProfileBoolTrue} >Profile</button>
                                            </Link>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <button type="button" className="loginButton" onClick={logoutUser}>LOGOUT</button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            : ""
                        }
                    </div>

                </div> : <div className="loginBody">
                    <Link to="/login">
                        <button type="button" className="loginButton">LOGIN</button>
                    </Link>
                </div>}
            </div>
        </div>
    )
}

export default NavBar;