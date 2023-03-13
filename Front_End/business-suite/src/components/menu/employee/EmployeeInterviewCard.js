import React from "react";
import { useNavigate } from "react-router-dom";
import CareerService from "../../../services/CareerService";
import "../../../styles/InterviewCard.css";

const EmployeeInterviewCard = ({ profile}) => {
  const navigate = useNavigate();
  const handleOnClick=()=>{
      document.documentElement.requestFullscreen();
      CareerService.changeInterviewStatus(profile.interviewerId).then((resp)=>{
        console.log(resp.data)
      }).catch((err)=>{
        console.log(err)
      })
      navigate("/interview")
    
  }
  return (
    <div className="interview-card">
      <div className="interview-card-header">
        <h3>Interview Details</h3>
      </div>
      <div className="interview-card-body">
        <p><strong>Applicant ID:</strong> {profile.applicantId}</p>
        <p><strong>Interviewer ID:</strong> {profile.interviewerId}</p>
        <p><strong>Job ID:</strong> {profile.jobId}</p>
        <p><strong>Interview Date:</strong> {profile.interviewDate}</p>
        <p><strong>Interview Time:</strong> {profile.interviewTime}</p>
      </div>
      <div className="interview-card-footer">
        <button className="interview-card-join-button" onClick={handleOnClick} >Join</button>
      </div>
    </div>
  );
};

export default EmployeeInterviewCard;
