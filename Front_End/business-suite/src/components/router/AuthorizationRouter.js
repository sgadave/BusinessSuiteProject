import Admin from "../dashboard/Admin";
import Applicant from "../dashboard/Applicant";
import Employee from "../dashboard/Employee";
import HR from "../dashboard/HR";
import UnAuthorizedError401 from "../errorhandling/UnAuthorizedError401";


const AuthorizationRouter = () => {
    return (
        sessionStorage.getItem("user_role") === "ADMIN" ?
            <Admin></Admin> :
            sessionStorage.getItem("user_role") === "APPLICANT" ?
                <Applicant></Applicant> :
                sessionStorage.getItem("user_role") === "EMPLOYEE" ?
                    <Employee></Employee> :
                    sessionStorage.getItem("user_role") === "HR" ?
                        <HR></HR> :
                        <UnAuthorizedError401></UnAuthorizedError401>

    )
}

export default AuthorizationRouter;