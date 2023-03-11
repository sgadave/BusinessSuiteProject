import { useEffect, useState } from "react";
import UserServices from "../../services/UserServices";
import "../../styles/NavBar.css"
import "../../styles/commonStyle.css"
import ProfileRouter from "../router/ProfileRouter";
import AppliedJob from "../menu/applicant/AppliedJob";
import InterviewVideoCall from "../menu/common/InterviewVideoCall";
import InterviewSchedule from "../menu/applicant/InterviewSchedule";
import FAQ from "../menu/common/FAQ";

const Applicant = () => {
    let [name, setName] = useState("")
    let [profileStatus, setProfileStatus] = useState(false)
    let [faqStatus, setFaqStatus] = useState(false)
    let [InterviewStatus, setInterviewStatus] = useState(false)
    let [dashBoardStatus, setDashBoardStatus] = useState(false)
    useEffect(() => {
        UserServices.getNameByUserName(sessionStorage.getItem("user_details")).then((resp) => {
            setName(resp.data.name)
            sessionStorage.setItem("appId",resp.data.id)
            sessionStorage.setItem("profile_status",resp.data.status)
        }).catch((err) => {
            console.log(err)
        })
    })
    const updateProfileStatus = () => {
        setProfileStatus(true);
        setDashBoardStatus(false);
        setInterviewStatus(false);
    }

    const updateDashBoardStatus = () => {
        setDashBoardStatus(true);
        setProfileStatus(false);
        setInterviewStatus(false);
    }
    const updateInterviewStatus = () => {
        setInterviewStatus(true);
        setDashBoardStatus(true);
        setProfileStatus(false);
    }

    const updateFaqStatus = () => {
        setFaqStatus(true)
        setInterviewStatus(false);
        setDashBoardStatus(true);
        setProfileStatus(false);
        setJobStatus(false);
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
                            <tr>
                                <td>
                                    <div className="navLinkBody">
                                        <button type="button" className="sideNavButton" onClick={updateFaqStatus}>FAQ</button>
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
                                InterviewStatus === true ?
                                    // <InterviewVideoCall></InterviewVideoCall>
                                    <InterviewSchedule></InterviewSchedule>
                                    :
                                    faqStatus === true ?
                                    <FAQ></FAQ>:
                                    dashBoardStatus === true ?
                                        <div className="welcomeMessageBody">
                                            <h5>Welcome, {name} </h5>
                                        </div> :
                                        <div className="welcomeMessageBody">
                                            <h5>Welcome, {name} </h5>
                                        </div>
                    }
                </div>
            </div>
        </div>
    )
}

export default Applicant;