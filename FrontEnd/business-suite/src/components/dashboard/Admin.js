import { useEffect, useState } from "react";
import UserServices from "../../services/UserServices";
import "../../styles/NavBar.css"
import "../../styles/commonStyle.css"
import ProfileRouter from "../router/ProfileRouter";
import AnalyticsPage from "../menu/admin/AnalyticsPage";
import ProfileCard from "../menu/admin/ProfileCard";
import CreateJobPost from "../menu/hr/CreateJobPost";
import ApplicantProfileMenu from "../menu/admin/ApplicantProfileMenu";


const Admin = () => {
    let [profileStatus, setProfileStatus] = useState(false)
    let [allEmployeeList, setAllEmployeeList] = useState(false)
    let [analyticsStatus, setAnalyticsStatus] = useState(false)
    let [createJobStatus, setCreateJobStatus] = useState(false)
    let [applicantListStatus, setApplicantListStatus] = useState(false)
    let [empList, setEmpList] = useState([]);
    let [name, setName] = useState("");
    useEffect(() => {
        UserServices.getNameByUserName(sessionStorage.getItem("user_details")).then((resp) => {
            setName(resp.data.name)
            sessionStorage.setItem("appId",resp.data.id)
        }).catch((err) => {
            console.log(err)
        })
    })
    const createEmployeeList = () => {
        console.log(empList)
    }
    const updateProfileStatus = () => {
        setProfileStatus(true);

    }

    const updateCreateJobStatus=()=>{
        setCreateJobStatus(true);
        setAnalyticsStatus(false);
        setProfileStatus(false);
        setAllEmployeeList(false);
    }

    const updateApplicantListStatus=()=>{
        setApplicantListStatus(true);
        setCreateJobStatus(false);
        setAnalyticsStatus(false);
        setProfileStatus(false);
        setAllEmployeeList(false);
    }

    const updateEmployeeList = () => {
        setProfileStatus(false);
        setAllEmployeeList(true);
        UserServices.getAllEmployees().then((resp) => {
            setEmpList(resp.data);
        })
        createEmployeeList();
    }

    const updateDashBoardStatus = () => {
        setAnalyticsStatus(true);
        setProfileStatus(false);
        setAllEmployeeList(false);
    }

    return (
        <div className="dashboardBody">
            <div className="mainContent">
                <aside className="sideMenu">
                    <table className="sibeMenuButtons">
                        <tbody>
                            <tr>
                                <td>
                                    <div className="navLinkBody">
                                        <button type="button" className="sideNavButton" onClick={updateDashBoardStatus}>Analytics</button>
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
                                        <button type="button" className="sideNavButton" onClick={updateEmployeeList}>Employee List</button>

                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div className="navLinkBody">
                                        <button type="button" className="sideNavButton" onClick={updateApplicantListStatus}>Applicant List</button>

                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div className="navLinkBody">
                                        <button type="button" className="sideNavButton" onClick={updateCreateJobStatus}>Create Job Post</button>

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
                            allEmployeeList === true ?
                                // <EmployeeList employee={empList}></EmployeeList> :
                                <ProfileCard></ProfileCard>:
                                analyticsStatus === true ?
                                    <AnalyticsPage></AnalyticsPage> :
                                    createJobStatus === true ?
                                    <CreateJobPost></CreateJobPost> :
                                    applicantListStatus === true ?
                                    <ApplicantProfileMenu></ApplicantProfileMenu>:
                                    <div className="welcomeMessageBody">
                                        <h5>Welcome, {name} </h5>
                                    </div>
                    }
                </div>
            </div>
        </div>
    )
}

export default Admin;