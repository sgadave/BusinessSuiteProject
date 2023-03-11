import ApplicantProfile from "../profile/applicant/ApplicantProfile";
import UploadApplicantDocs from "../profile/applicant/UploadApplicantDocs";
import EmployeeProfile from "../profile/employee/EmoloyeeProfile";
import UnAuthorizedError401 from "../errorhandling/UnAuthorizedError401";

const ProfileRouter = () => {
    return (
        sessionStorage.getItem("user_role") === "APPLICANT" ?
            sessionStorage.getItem("profile_status") === "COMPLETED" ?
            <ApplicantProfile></ApplicantProfile> :
            <UploadApplicantDocs></UploadApplicantDocs> :
            (sessionStorage.getItem("user_role") === "EMPLOYEE" || sessionStorage.getItem("user_role") === "HR" || sessionStorage.getItem("user_role") === "ADMIN") ?
            <EmployeeProfile></EmployeeProfile>:
            <UnAuthorizedError401></UnAuthorizedError401>

    )
}

export default ProfileRouter;