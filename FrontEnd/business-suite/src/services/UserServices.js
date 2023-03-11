import axios from 'axios';


class UserServices {
    constructor() {
        this.baseUrl = "http://localhost:8080/"
    }

    getUserByUserName(userName) {
        let role = sessionStorage.getItem("user_role") === "APPLICANT" ? "applicant/" : "employee/"
        return axios.get(this.baseUrl + role + "profile/"+userName);
    }

    getNameByUserName(userName) {
        let role = sessionStorage.getItem("user_role") === "APPLICANT" ? "applicant/" : "employee/"
        console.log(this.baseUrl)
        return axios.get(this.baseUrl + role + "name/" + userName);
    }

    getProfileImageByUserName(appUserName) {
        let role = sessionStorage.getItem("user_role") === "APPLICANT" ? "applicant/" : "employee/"
        return axios.get(this.baseUrl + role + "prof_image/" + appUserName, { responseType: 'blob' })
    }

    getApplicantProfileImageByUserName(appUserName){
        return axios.get(this.baseUrl + "applicant/prof_image/" + appUserName, { responseType: 'blob' })
    }

    sendPassChangeOTP(userName) {
        return axios.post(this.baseUrl + "email/forgot_password/" + userName)
    }

    verifyOTPAndChangePass(newPassDetails) {
        return axios.post(this.baseUrl + "auth/change_password", newPassDetails)
    }

    getAllEmployees() {
        return axios.get(this.baseUrl + "employee/allemployeedetails")
    }

    getAllApplicants() {
        return axios.get(this.baseUrl + "applicant/view")
    }

    signUpUser(cred) {
        return axios.post(this.baseUrl + "auth/signup", cred)
    }

    addApplicantDetails(userName, appDetails) {
        return axios.post(this.baseUrl + "applicant/" + userName + "/createprofile", appDetails)
    }

    uploadApplicantProfile(image) {
        const profile = new FormData()
        profile.append("image",image)
        return axios.post(this.baseUrl + "applicant/upload_profile_image/" + sessionStorage.getItem("user_details"),profile,{ headers:{
            "Accept":"multipart/form-data",
            "content-type": "multipart/form-data"
        }}
          )
    }

    uploadApplicantGovId(govId, image, userName) {
        const govIdImg = new FormData()
        govIdImg.append("govId",image)
        console.log(govIdImg.get("govId"))
        return axios.post(this.baseUrl + "applicant/upload_gov_id/" + userName +"/"+govId.govType+"/"+govId.govIdNo, govIdImg, { headers:{
            "Accept":"*/*",
            "content-type": "multipart/form-data"
        }}
          )
    }

    uploadApplicantResume(resume, userName) {
        const data = new FormData()
        data.append("resume",resume)
        return axios.post(this.baseUrl + "applicant/upload_resume/" + userName, data, { headers:{
            "Accept":"*/*",
            "content-type": "multipart/form-data"
        }})
    }

    scheduleApplicantInterview(schedule){
        console.log(schedule)
        return axios.post(this.baseUrl+"interview/schedule_interview",schedule);
    }

    deleteEmployeeDetails(userName){
        return axios.delete(this.baseUrl+"employee/delete/"+userName);
    }

    deleteApplicantDetails(userName){
        return axios.delete(this.baseUrl+"employee/delete/"+userName);
    }

    updateEmployeeDetails(updatedUser){
        return axios.put(this.baseUrl+"employee/updatedetails",updatedUser);
    }

    updateApplicantDetails(updatedUser){
        return axios.put(this.baseUrl+"applicant/updatedetails",updatedUser);
    }
}

export default new UserServices();