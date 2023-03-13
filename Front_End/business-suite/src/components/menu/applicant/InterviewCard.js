import React from "react";
import { useNavigate } from "react-router-dom";
import "../../../styles/InterviewCard.css";

const InterviewCard = ({ profile}) => {
  const navigate = useNavigate();
  const handleOnClick=()=>{
    console.log("In Handle On Click")
    if(profile.status === "ACTIVE"){
      document.documentElement.requestFullscreen();
      navigate("/interview")
    }
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

export default InterviewCard;
