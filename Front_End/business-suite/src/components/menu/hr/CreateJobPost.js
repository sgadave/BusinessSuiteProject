import React, { useState } from "react";
import axios from "axios";
import "../../../styles/CreateJobPost.css"
import CareerService from "../../../services/CareerService";

const CreateJobPost = () => {
    const [jobTitle, setJobTitle] = useState("");
    const [jobCategory, setJobCategory] = useState("");
    const [jobType, setJobType] = useState("FULLTIME");
    const [appPostDate, setAppPostDate] = useState(new Date().toISOString().slice(0, 10));
    const [appStartDate, setAppStartDate] = useState("");
    const [appStopDate, setAppStopDate] = useState("");
    const [salary, setSalary] = useState("");
    const [city, setCity] = useState("");
    const [eduQualification, setEduQualification] = useState("");
    const [appStatus, setAppStatus] = useState("INACTIVE");
    const [description, setDescription] = useState("");

    const handleSubmit = (e) => {
        e.preventDefault();
        const data = {
            jobTitle,
            jobCategory,
            jobType,
            appPostDate,
            appStartDate,
            appStopDate,
            salary,
            city,
            eduQualification,
            appStatus,
            description,
        };
       CareerService.createJobPost(data)
            .then(response => {
                console.log(response);
            })
            .catch(error => {
                console.log(error);
            });
    };

    return (
        <div className="form-container" onSubmit={handleSubmit}>
            <h1>Job Application Form</h1>
            <form id="job-application-form">
                <div className="form-group">
                    <label htmlFor="jobTitle">Job Title:</label>
                    <input
                        type="text"
                        id="jobTitle"
                        value={jobTitle}
                        onChange={(e) => setJobTitle(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="jobCategory">Job Category:</label>
                    <select id="jobCategory" name="jobCategory" required
                        value={jobCategory}
                        onChange={(e) => setJobCategory(e.target.value)}
                    >
                        <option value="">-- Select Job Category --</option>
                        <option value="QA_ANALYST">QA Analyst</option>
                        <option value="SYSTEM_ENGINEER">System Engineer</option>
                        <option value="MANAGER">Manager</option>
                        <option value="HR">HR</option>
                        <option value="SYSTEM_ANALYST">System Analyst</option>
                    </select>
                </div>

                <div className="form-group">
                    <label htmlFor="jobType">Job Type:</label>
                    <select
                        id="jobType"
                        value={jobType}
                        onChange={(e) => setJobType(e.target.value)}
                    >
                        <option value="FULLTIME">Full-time</option>
                        <option value="PARTTIME">Part-time</option>
                        <option value="FREELANCE">Freelance</option>
                        <option value="INTERSHIP">Internship</option>
                    </select>
                </div>
                <div className="form-group">
                    <label htmlFor="appPostDate">Application Post Date:</label>
                    <input
                        type="date"
                        id="appPostDate"
                        value={appPostDate}
                        onChange={(e) => setAppPostDate(e.target.value)}
                        disabled
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="appStartDate">Application Start Date:</label>
                    <input
                        type="date"
                        id="appStartDate"
                        value={appStartDate}
                        onChange={(e) => setAppStartDate(e.target.value)}
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="appStopDate">Application Stop Date:</label>
                    <input
                        type="date"
                        id="appStopDate"
                        value={appStopDate}
                        onChange={(e) => setAppStopDate(e.target.value)}
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="salary">Salary:</label>
                    <input
                        type="text"
                        id="salary"
                        value={salary}
                        onChange={(e) => setSalary(e.target.value)}
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="city">City:</label>
                    <select id="city" name="city" required
                        value={city}
                        onChange={(e) => setCity(e.target.value)}
                    >
                        <option value="" disabled>Select City</option>
                        <option value="PUNE">Pune</option>
                        <option value="MUMBAI">Mumbai</option>
                        <option value="NASHIK">Nashik</option>
                        <option value="BANGLORE">Banglore</option>
                        <option value="HYDRABAD">Hydrabad</option>
                        <option value="KOLHAPUR">Kolhapur</option>
                        <option value="SANGLI">Sangli</option>
                    </select>
                </div>
                <div className="form-group">
                    <label htmlFor="eduQualification">Educational Qualification:</label>
                    <input type="text" id="eduQualification" name="eduQualification" required value={eduQualification}
                        onChange={(e) => setEduQualification(e.target.value)} />
                </div>
                <div className="form-group">
                    <label htmlFor="appStatus">Application Status:</label>
                    <select id="appStatus" name="appStatus" required value={appStatus}
                        onChange={(e) => setAppStatus(e.target.value)}>
                        <option value="" disabled>Select Application Status</option>
                        <option value="ACTIVE">Active</option>
                        <option value="INACTIVE">Inactive</option>
                    </select>
                </div>
                <div className="form-group">
                    <label htmlFor="description">Job Description:</label>
                    <textarea id="description" name="description" required value={description}
                        onChange={(e) => setDescription(e.target.value)}></textarea>
                </div>
                <div >
                    <button type="submit" className="form-submit">Submit</button>
                </div>
            </form>
        </div>

    )
}

export default CreateJobPost