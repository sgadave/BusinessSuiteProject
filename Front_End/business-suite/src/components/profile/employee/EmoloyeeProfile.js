import { useEffect } from "react";
import { useState } from "react";
import UserServices from "../../../services/UserServices";
import "../../../styles/ApplicantProfile.css"
import EditForm from "../../menu/common/EditForm";

const EmployeeProfile = () => {

    const [profileData, setProfileData] = useState({});
    const [profileImage, setProfileImage] = useState('');
    const [editProfileData,setEditProfileData] = useState(false) 

    const updateProfileData=()=>{
        setEditProfileData(true);
    }

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
            {
                editProfileData === false ?
                    <div className="profile-details">
                        <div className="profile-name">{profileData.firstName} {profileData.middleName} {profileData.lastName}</div>
                        <div className="profile-info">
                            <span className="profile-label">Employee ID:</span>
                            <span className="profile-value">{profileData.employeeId}</span>
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
                            <span className="profile-label">Designation:</span>
                            <span className="profile-value">{profileData.designation}</span>
                        </div>
                        <div className="profile-info">
                            <span className="profile-label">Email ID:</span>
                            <span className="profile-value">{profileData.emailId}</span>
                        </div>
                        <div>
                            <button className="edit-button" onClick={updateProfileData}>Edit</button>
                        </div>
                    </div> :
                    <EditForm user={profileData}></EditForm>

            }
        </div>
    );
}

export default EmployeeProfile;