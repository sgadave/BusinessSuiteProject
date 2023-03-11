import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthenticationService from "../../services/AuthenticationService";
import UserServices from "../../services/UserServices";
import "../../styles/NavBar.css"
import "../../styles/commonStyle.css"
import ProfileRouter from "../router/ProfileRouter";
import CreateJobPost from "../menu/hr/CreateJobPost";
import ScheduleInterview from "../menu/hr/ScheduleInterview";
import ApplicantProfileList from "../menu/hr/ApplicantProfileList";
import ApplicantResumeViewer from "../menu/common/ApplicantResumeViewer";


const HR = () => {
    let [name, setName] = useState("")
    let [profileStatus, setProfileStatus] = useState(false)
    let [JobStatus, setJobStatus] = useState(false)
    let [applicantStatus, setApplicantStatus] = useState(false)
    let [dashBoardStatus, setDashBoardStatus] = useState(false)
    let [createPostStatus, setCreatePostStatus] = useState(false)
    let [viewResumeStatus, setViewResumeStatus] = useState(false)
    useEffect(() => {
        UserServices.getNameByUserName(sessionStorage.getItem("user_details")).then((resp) => {
            setName(resp.data.name)
            sessionStorage.setItem("appId", resp.data.id)
        }).catch((err) => {
            console.log(err)
        })
    })

    const updateProfileStatus = () => {
        setProfileStatus(true);
    }

    const updateJobStatus = () => {
        setProfileStatus(false);
        setJobStatus(true);
    }

    const updateDashBoardStatus = () => {
        setDashBoardStatus(true);
        setProfileStatus(false);
        setJobStatus(false);
    }

    const updateCreatePostStatus = () => {
        setCreatePostStatus(true)
        setDashBoardStatus(false);
        setProfileStatus(false);
        setJobStatus(false);
    }

    const updateApplicantStatus=()=>{
        setApplicantStatus(true)
        setCreatePostStatus(false)
        setDashBoardStatus(false);
        setProfileStatus(false);
        setJobStatus(false);
    }

    const updateViewResumeStatus=()=>{
        setViewResumeStatus(true)
        setApplicantStatus(false)
        setCreatePostStatus(false)
        setDashBoardStatus(false);
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
                                        <button type="button" className="sideNavButton" onClick={updateJobStatus}>Schedule Interview</button>

                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div className="navLinkBody">
                                        <button type="button" className="sideNavButton" onClick={updateCreatePostStatus}>Create Job Post</button>

                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div className="navLinkBody">
                                        <button type="button" className="sideNavButton"  onClick={updateApplicantStatus}>Applicants </button>

                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div className="navLinkBody">
                                        <button type="button" className="sideNavButton"  onClick={updateViewResumeStatus}>View Resume </button>

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
                            JobStatus === true ?
                                <ScheduleInterview></ScheduleInterview> :
                                createPostStatus === true ?
                                    <CreateJobPost></CreateJobPost> :
                                    applicantStatus === true ?
                                    <ApplicantProfileList></ApplicantProfileList>:
                                    viewResumeStatus === true ?
                                    <ApplicantResumeViewer></ApplicantResumeViewer>:
                                    <div className="welcomeMessageBody">
                                        <h5>Welcome, {name} </h5>
                                    </div>
                    }
                </div>
            </div>
        </div>
    )
}

export default HR;