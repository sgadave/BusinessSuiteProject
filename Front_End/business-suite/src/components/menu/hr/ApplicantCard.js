import React, { useState, useEffect } from "react";
import UserServices from "../../../services/UserServices";
import '../../../styles/ProfileCard.css';

const ApplicantCard = ({ profile, handleDelete }) => {
  const [image, setImage] = useState("");

  const handleClickOnDelete = () => {
    handleDelete(profile.applicantCredentials.userName)
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
      <h2 className="profile-id">{profile.address}</h2>
      <h2 className="profile-id">{profile.emailId}</h2>

      <div className="profile-actions">
        <button onClick={handleClickOnDelete}>Delete</button>
      </div>
    </div>
  );
};

export default ApplicantCard;
