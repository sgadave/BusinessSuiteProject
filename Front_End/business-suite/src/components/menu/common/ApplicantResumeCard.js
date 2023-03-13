import React, { useState, useEffect } from "react";
import UserServices from "../../../services/UserServices";
import '../../../styles/ProfileCard.css';

const ApplicantResumeCard = ({ profile, handleViewResume }) => {
  const [image, setImage] = useState("");

  const handleClickOnViewResume = () => {
    handleViewResume(profile)
  }

  useEffect(() => {
    UserServices.getApplicantProfileImageByUserName(profile.applicantCredentials.userName).then((resp) => {
      setImage(URL.createObjectURL(resp.data))
    }).catch((err) => {
      console.log("ERR", err)
    })
  }, []);

  return (
    <div key={profile.employeeId} className="profile-card">
      <img className="profile-img" src={image} alt="Profile Image" />
      <h2 className="profile-name">{profile.firstName + " " + profile.lastName}</h2>
      <p className="profile-id">{profile.applicantId}</p>
      <div className="profile-actions">
        <button onClick={handleClickOnViewResume}>View Resume</button>
      </div>
    </div>
  );
};

export default ApplicantResumeCard;
