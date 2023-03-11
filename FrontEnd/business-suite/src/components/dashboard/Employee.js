import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
// import AuthenticationService from "../../service/AuthenticationService";
import UserServices from "../../services/UserServices";
import "../../styles/NavBar.css"
import "../../styles/commonStyle.css"
import ProfileRouter from "../router/ProfileRouter";
import AppliedJob from "../menu/applicant/AppliedJob";
import EmployeeInterviewSchedule from "../menu/employee/EmployeeInterviewSchedule";

const Employee = () => {
    let [name, setName] = useState("")
    let [profileStatus, setProfileStatus] = useState(false)
    let [interviewSchedule, setInterviewSchedule] = useState(false)
    let [dashBoardStatus, setDashBoardStatus] = useState(false)
    useEffect(() => {
        UserServices.getNameByUserName(sessionStorage.getItem("user_details")).then((resp) => {
            setName(resp.data.name)
            sessionStorage.setItem("appId",resp.data.id)
        }).catch((err) => {
            console.log(err)
        })
    })
    const updateProfileStatus = () => {
        setProfileStatus(true);
    }

    const updateDashBoardStatus=()=>{
        setDashBoardStatus(true);
        setInterviewSchedule(false);
        setProfileStatus(false);
    }

    const updateInterviewStatus = () => {
        setInterviewSchedule(true);
        setProfileStatus(false);
    }

    return (
        <div>
            <div className="mainContent">
                <aside className="sideMenu">
                    <table className="sibeMenuButtons">
                        <tbody>
                            <tr>
                                <td>
                                    <div className="navLinkBody">
                                        <button type="button" className="sideNavButton" onClick={updateDashBoardStatus}>Home</button>

                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div className="navLinkBody">
                                        <button type="button" className="sideNavButton" onClick={updateProfileStatus}>Profile</button>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div className="navLinkBody">
                                        <button type="button" className="sideNavButton" onClick={updateInterviewStatus}>Interview</button>

                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </aside>
                <div className="dashboardPageViewer">
                    {
                        profileStatus === true ?
                            <ProfileRouter></ProfileRouter> :
                            interviewSchedule === true ?
                                <EmployeeInterviewSchedule></EmployeeInterviewSchedule> :
                                <div className="welcomeMessageBody">
                                    <h5>Welcome, {name} </h5>
                                </div>
                    }
                </div>
            </div>
        </div>
    )
}

export default Employee;