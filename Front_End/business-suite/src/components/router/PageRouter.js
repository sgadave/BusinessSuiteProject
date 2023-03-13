import { BrowserRouter as Router, Routes, Route } from "react-router-dom"

import HomePage from "../navigation/HomePage"
import Login from "../navigation/Login"
import AuthorizationRouter from "./AuthorizationRouter"
import Disclaimer from "../navigation/Disclaimer"
import PrivacyPolicy from "../navigation/PrivacyPolicy"
import TermsOfUse from "../navigation/TermsOfUse"
import ContactUs from "../navigation/ContactUs"
import ForgotPassword from "../navigation/ForgotPassword"
import Career from "../navigation/career/Career"
import PageNotFoundError404 from "../errorhandling/PageNotFoundError404"
import AuthenticationService from "../../services/AuthenticationService"
import NavBar from "../common/NavBar"
import FooterNavBar from "../common/FooterNavBar"
import SignUp from "../navigation/SignUp"
import AboutTeam from "../navigation/AboutTeam"
import InterviewVideoCall from "../menu/common/InterviewVideoCall"


const PageRouter = () => {
    return (
        <div>
            {console.log("User Role", sessionStorage.getItem("user_role"))}
            {
                AuthenticationService.setupRequestInterceptor()
            }
            <Router>
                <NavBar></NavBar>
                <Routes>
                    {/* Root Page */}
                    <Route path="/" element={<HomePage></HomePage>}></Route>
                    {/* Login Page for Authentication */}
                    <Route path="/login" element={<Login></Login>}></Route>
                    {/* Authorization Router for DashBoard as per Role */}
                    <Route path="/dashboard" element={<AuthorizationRouter></AuthorizationRouter>}></Route>
                    {/* Disclaimer of the company */}
                    <Route path="/disclaimer" element={<Disclaimer></Disclaimer>}></Route>
                    {/* Privacy Policy of the company */}
                    <Route path="/privacypolicy" element={<PrivacyPolicy></PrivacyPolicy>}></Route>
                    {/* Terms Of Use of the company */}
                    <Route path="/termsofuse" element={<TermsOfUse></TermsOfUse>}></Route>
                    {/* Contact page Routing */}
                    <Route path="/contactus" element={<ContactUs></ContactUs>}></Route>
                    {/* Forgot password Routing */}
                    <Route path="/forgotpassword" element={<ForgotPassword></ForgotPassword>}></Route>
                    {/* careers Page  */}
                    <Route path="/careers" element={<Career></Career>}></Route>
                    {/* Page not found error handling */}
                    <Route path="*" element={<PageNotFoundError404></PageNotFoundError404>} />
                    {/* SignUp Page */}
                    <Route path="/signup" element={<SignUp></SignUp>}></Route>
                    {/* About Team */}
                    <Route path="/aboutteam" element={<AboutTeam></AboutTeam>}></Route>
                    {/* Interview */}
                    {sessionStorage.getItem("user_details") === null ? " ": <Route path="/interview" element={<InterviewVideoCall></InterviewVideoCall>}></Route>}
                </Routes>
                <FooterNavBar></FooterNavBar>
            </Router>
        </div>
    )
}

export default PageRouter;