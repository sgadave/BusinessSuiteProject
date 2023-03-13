import axios from 'axios';


class CareerService {
    constructor() {
        this.baseUrl = "http://localhost:8080/"
    }

    getJobDetails(details) {
        let method = details.keyword === "" ? "location" : "keyword"
        let value = details.keyword === "" ? details.location : details.keyword
        return axios.get(this.baseUrl + "jobdesc/" + method + "/" + value);
    }

    getAllJobDetails() {
        return axios.get(this.baseUrl + "jobdesc/jobposts");
    }

    applyToJob(appId, jobId) {
        return axios.post(this.baseUrl + "jobdesc/apply/" + jobId + "/" + appId)
    }

    createJobPost(data) {
        return axios.post(this.baseUrl + "jobdesc/createpost", data)
    }

    changeInterviewStatus(interviewerId) {
        return axios.put(this.baseUrl + "interview/status/" + interviewerId)
    }

    getAllInterviewSchedules(applicantId) {
        return axios.get(this.baseUrl + "interview/applicant/" + applicantId)
    }

    getAllEmployeeInterviewSchedules(applicantId) {
        return axios.get(this.baseUrl + "interview/employee/" + applicantId)
    }

    getApplicantResume(userName) {
        return axios.get(this.baseUrl + "applicant/resume/" + userName, {
            responseType: 'blob'
        })
    }

}

export default new CareerService();