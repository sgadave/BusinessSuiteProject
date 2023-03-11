import { useEffect } from "react";
import { useState } from "react";
import UserServices from "../../../services/UserServices";
import "../../../styles/ApplicantProfile.css"

const ApplicantProfile = () => {

    const [profileData, setProfileData] = useState({});
    const [profileImage, setProfileImage] = useState('');


    useEffect(() => {
        UserServices.getUserByUserName(sessionStorage.getItem("user_details"))
            .then(response => {
                setProfileData(response.data);
            })
            .catch(error => {
                console.log(error);
            });

        UserServices.getProfileImageByUserName(sessionStorage.getItem("user_details")).then((resp) => {
            setProfileImage(URL.createObjectURL(resp.data))
        }).catch((err) => {
            console.log("ERR", err)
        })
    }, [])


    return (
        <div className="profile-container">
            <img src={profileImage} alt="Profile" className="profile-image" />
            <div className="profile-details">
                <div className="profile-name">{profileData.firstName} {profileData.middleName} {profileData.lastName}</div>
                <div className="profile-info">
                    <span className="profile-label">Applicant ID:</span>
                    <span className="profile-value">{profileData.applicantId}</span>
                </div>
                <div className="profile-info">
                    <span className="profile-label">Address:</span>
                    <span className="profile-value">{profileData.address}</span>
                </div>
                <div className="profile-info">
                    <span className="profile-label">DOB:</span>
                    <span className="profile-value">{profileData.dob}</span>
                </div>
                <div className="profile-info">
                    <span className="profile-label">Gender:</span>
                    <span className="profile-value">{profileData.gender}</span>
                </div>
                <div className="profile-info">
                    <span className="profile-label">Gov ID Type:</span>
                    <span className="profile-value">{profileData.govIdType}</span>
                </div>
                <div className="profile-info">
                    <span className="profile-label">Government ID Number:</span>
                    <span className="profile-value">{profileData.governmentIdNumber}</span>
                </div>
                <div className="profile-info">
                    <span className="profile-label">PRN:</span>
                    <span className="profile-value">{profileData.prn}</span>
                </div>
                <div className="profile-info">
                    <span className="profile-label">Email ID:</span>
                    <span className="profile-value">{profileData.emailId}</span>
                </div>
                <div className="profile-info">
                    <span className="profile-label">Primary Contact:</span>
                    <span className="profile-value">{profileData.priContact}</span>
                </div>
            </div>
        </div>
    );
}


export default ApplicantProfile;