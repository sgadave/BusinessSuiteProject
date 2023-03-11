import React, { useState, useEffect } from "react";
import UserServices from "../../../services/UserServices";
import '../../../styles/ProfileCard.css';

const EmployeeProfileCard = ({ profile,handleEdit,handleDelete }) => {
    const [image, setImage] = useState("");

    const handleClickOnEdit=()=>{
        handleEdit(profile)
    }

    const handleClickOnDelete=()=>{
        handleDelete(profile.employeeCredentials.userName)
    }

    useEffect(() => {
        UserServices.getProfileImageByUserName(profile.employeeCredentials.userName).then((resp) => {
            setImage(URL.createObjectURL(resp.data))
        }).catch((err) => {
            console.log("ERR", err)
        })
    }, []);

    return (
        <div key={profile.employeeId} className="profile-card">
            <img className="profile-img" src={image} alt="Profile Image" />
            <h2 className="profile-name">{profile.firstName + " " + profile.lastName}</h2>
            <p className="profile-id">{profile.employeeId}</p>
            <h2 className="profile-id">{profile.designation}</h2>
            <h2 className="profile-id">{profile.emailId}</h2>

            <div className="profile-actions">
                <button onClick={handleClickOnEdit}>Edit</button>
                <button onClick={handleClickOnDelete}>Delete</button>
            </div>
        </div>
    );
};

export default EmployeeProfileCard;
