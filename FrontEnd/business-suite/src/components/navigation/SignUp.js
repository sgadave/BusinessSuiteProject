import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../../styles/SignUp.css"
import LoginImg from "../../images/loginpage/LoginImg.jpeg"
import hidePass from "../../images/loginpage/hidePass.svg";
import showPass from "../../images/loginpage/showPass.svg";
import UserServices from "../../services/UserServices";

const SignUp = () => {
    let [userCred, setUserCred] = useState({ userName: "", password: "" });
    let [appDetails, setAppDetails] = useState({
        firstName: "",
        middleName: "",
        lastName: "",
        prn: 0,
        emailId: "",
        priContact: "",
        secContact: "",
        expYears: 0,
        address: "",
        dob: "",
        gender: "",
        highestEducationProfile: "",
        score12th: 0,
        passingYear12th: "",
        passingYear10th: "",
        score10th: 0
    });

    let [showPassStatus, setShowPassStatus] = useState(false);
    const navigate = useNavigate();

    const validateUser = (event) => {
        event.preventDefault();

        console.log("in Submit Form")
        UserServices.signUpUser(userCred).then((resp) => {
            UserServices.addApplicantDetails(resp.data, appDetails).then((resp) => {
                navigate("/login")
            }).catch((err) => {
                console.log("SignUp ", err)
            })
        }).catch((err) => {
            console.log("SignUp Cred", err)
        })

    }

    const handleChangeCredential = (event) => {
        const { name, value } = event.target
        setUserCred({ ...userCred, [name]: value })
    }

    const handleChangeAppDetails = (event) => {
        const { name, value } = event.target
        setAppDetails({ ...appDetails, [name]: value })
    }


    const showPassword = () => {
        console.log("hello")
        if (document.getElementById("password") != null) {
            document.getElementById("password").type = "text"
        }
        setShowPassStatus(true);
    }

    const hidePassword = () => {
        if (document.getElementById("password") != null) {
            document.getElementById("password").type = "password"
        }
        setShowPassStatus(false);
    }

    return (
        <div className="form-container" onSubmit={validateUser}>
            <h1>Sign Up Form</h1>
            <form id="job-application-form">
                <div className="form-group">
                    <label htmlFor="userName">User Name:</label>
                    <input
                        type="text"
                        id="userName"
                        name="userName"
                        placeholder="User Name"
                        onChange={handleChangeCredential} value={userCred.userName}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        placeholder="Password"
                        onChange={handleChangeCredential} value={userCred.password}
                        required
                    />
                    {!showPassStatus ? <div className="showPassToggle" onClick={showPassword}><img src={showPass} className="containPasswordToggle"></img></div> : <div className="hidePassToggle" onClick={hidePassword}><img src={hidePass} className="containPasswordToggle"></img></div>}
                </div>
                <div className="form-group">
                    <label htmlFor="firstName">First Name:</label>
                    <input
                        type="text"
                        id="firstName"
                        name="firstName"
                        placeholder="First Name"
                        onChange={handleChangeAppDetails} value={appDetails.firstName}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="middleName">Middle Name:</label>
                    <input
                        type="text"
                        id="middleName"
                        name="middleName"
                        placeholder="Middle Name"
                        onChange={handleChangeAppDetails} value={appDetails.middleName}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="lastName">Last Name:</label>
                    <input
                        type="text"
                        id="lastName"
                        name="lastName"
                        placeholder="Last Name"
                        onChange={handleChangeAppDetails} value={appDetails.lastName}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="emailId">Email:</label>
                    <input
                        type="email"
                        id="emailId"
                        name="emailId"
                        placeholder="Email Id"
                        onChange={handleChangeAppDetails} value={appDetails.emailId}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="prn">PRN:</label>
                    <input
                        type="number"
                        id="prm"
                        name="prn"
                        placeholder="Permanent Registration Number"
                        onChange={handleChangeAppDetails} value={appDetails.prn}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="priContact">Primary Contact:</label>
                    <input
                        type="number"
                        id="priContact"
                        name="priContact"
                        placeholder="Primary Contact"
                        onChange={handleChangeAppDetails} value={appDetails.priContact}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="secContact">Secondary Contact:</label>
                    <input
                        type="number"
                        id="secContact"
                        name="secContact"
                        placeholder="Secondary Contact"
                        onChange={handleChangeAppDetails} value={appDetails.secContact}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="expYears">Experience:</label>
                    <input
                        type="number"
                        id="expYears"
                        name="expYears"
                        placeholder="Experience In years"
                        onChange={handleChangeAppDetails} value={appDetails.expYears}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="address">Address:</label>
                    <input
                        type="text"
                        id="address"
                        name="address"
                        placeholder="City You Stay In"
                        onChange={handleChangeAppDetails} value={appDetails.address}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="dob">Date of Birth:</label>
                    <input
                        type="date"
                        id="dob"
                        name="dob"
                        placeholder="Date Of Birth"
                        onChange={handleChangeAppDetails} value={appDetails.dob}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="gender">Job Category:</label>
                    <select id="gender" name="gender" required
                       onChange={handleChangeAppDetails} value={appDetails.gender}
                    >
                        <option value="">-- Select Gender --</option>
                        <option value="MALE">Male</option>
                        <option value="FEMALE">Female</option>
                        <option value="OTHER">Other</option>
                    </select>
                </div>
                <div className="form-group">
                    <label htmlFor="highestEducationProfile">Highest Education:</label>
                    <input
                        type="text"
                        id="highestEducationProfile"
                        name="highestEducationProfile"
                        placeholder="Highest Education Qualification"
                        onChange={handleChangeAppDetails} value={appDetails.highestEducationProfile}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="score12th">12th Score:</label>
                    <input
                        type="text"
                        id="score12th"
                        name="score12th"
                        placeholder="12th Score"
                        onChange={handleChangeAppDetails} value={appDetails.score12th}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="passingYear12th">12th Passing Year:</label>
                    <input
                        type="date"
                        id="passingYear12th"
                        name="passingYear12th"
                        placeholder="12th Passing Year"
                        onChange={handleChangeAppDetails} value={appDetails.passingYear12th}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="score10th">10th Score:</label>
                    <input
                        type="text"
                        id="score10th"
                        name="score10th"
                        placeholder="10th Score"
                        onChange={handleChangeAppDetails} value={appDetails.score10th}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="passingYear10th">10th Passing Year:</label>
                    <input
                        type="date"
                        id="passingYear10th"
                        name="passingYear10th"
                        placeholder="10th Passing Year"
                        onChange={handleChangeAppDetails} value={appDetails.passingYear10th}
                        required
                    />
                </div>
                <div >
                    <button type="submit" className="form-submit">Submit</button>
                </div>
            </form>
            {/* 
                               
                               
                                    <td colSpan={6}>
                                        <button type="submit" className="SignInButton">Sign Up</button>
                                    </td>
                       */}
        </div>
    )

}

export default SignUp