import { useState } from "react";
import UserServices from "../../../services/UserServices";
import "../../../styles/ScheduleInterview.css"


const Interview = (props) => {

    let [schedule, setSchedule] = useState({
        applicantId: "",
        interviewerId: "",
        jobId: "",
        interviewDate: '',
        interviewTime: "",
        status: ""
    })

    const handleChange = (event) => {
        const { name, value } = event.target
        setSchedule({ ...schedule, [name]: value })
    }

    const scheduleInterview = (e) => {
        e.preventDefault();
        UserServices.scheduleApplicantInterview(schedule).then((resp) => {
            console.log(resp.data)
        }).catch((err) => {
            console.log("schedule Interview ", err)
        })
    }

    return (
        <div className="form-container" onSubmit={scheduleInterview}>
            <h1>Schedule Job Interview</h1>
            <form id="job-application-form">
                <div className="form-group">
                    <label htmlFor="applicantId">Applicant Id:</label>
                    <select id="applicantId" name="applicantId" required
                        value={schedule.applicantId} onChange={handleChange}
                    >
                        <option key={"a"} value="">---------applicant---------</option>
                        {props.applicantArray.map((app, index) => {
                            return (<option key={index} value={app.applicantId}>{app.applicantId}</option>)
                        })}
                    </select>
                </div>
                <div className="form-group">
                    <label htmlFor="interviewerId">Interviewer Id:</label>
                    <select id="interviewerId" name="interviewerId" required
                        value={schedule.interviewerId} onChange={handleChange}
                    >
                        <option key={"b"} value="">---------interviewer---------</option>
                        {props.employeeArray.map((app, index) => {
                            return <option key={index} value={app.employeeId}>{app.employeeId}</option>
                        })}
                    </select>
                </div>
                <div className="form-group">
                    <label htmlFor="jobId">Interviewer Id:</label>
                    <select id="jobId" name="jobId" required
                        value={schedule.jobId} onChange={handleChange}
                    >
                        <option key={"c"} value="">---------job id---------</option>
                        {props.jobs.map((app, index) => {
                            return <option key={index} value={app.jobPostId}>{app.jobPostId}</option>
                        })}
                    </select>
                </div>
                <div className="form-group">
                    <label htmlFor="interviewDate">Application Post Date:</label>
                    <input
                        type="date"
                        id="interviewDate"
                        value={schedule.date} onChange={handleChange}
                        name="interviewDate"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="interviewTime">Application Post Date:</label>
                    <input
                        type="time"
                        id="interviewTime"
                        value={schedule.interviewTime} onChange={handleChange}
                        name="interviewTime"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="status">Application Status:</label>
                    <select id="status" name="status" required 
                    value={schedule.status} onChange={handleChange}>
                        <option value="" disabled>Select Application Status</option>
                        <option value="ACTIVE">Active</option>
                        <option value="INACTIVE">Inactive</option>
                    </select>
                </div>
                <div >
                    <button type="submit" className="form-submit">Schedule</button>
                </div>
            </form>
        </div>

    )
}

export default Interview;