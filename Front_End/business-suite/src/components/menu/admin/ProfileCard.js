import React, { useEffect, useState } from 'react';
import UserServices from "../../../services/UserServices"
import EditForm from '../common/EditForm';
import EmployeeProfileCard from './EmployeeProfileCard';
// import '../../../styles/ProfileCard.css';

const ProfileCard = () => {
  const [profiles, setProfiles] = useState([]);
  const [user,setUser] = useState({});
  const [editStatus,setEditStatus] = useState(false)

  useEffect(() => {
    UserServices.getAllEmployees()
      .then(response => {
        setProfiles(response.data);
      })
      .catch(error => {
        console.error(error);
      });
  }, []);

  

  const handleEdit = (profile) => {
    setUser(profile);
    setEditStatus(true);
  };

  const handleDelete = (userName) => {
    UserServices.deleteEmployeeDetails(userName).then((resp)=>{
      console.log(resp.data)
    }).catch((err)=>{
      console.log(err)
    })
    document.location.reload();
  };

  return (
    <div id="profile-cards">
        
      {
        !editStatus ? profiles.map(profile => (
          <EmployeeProfileCard key={profile.employeeId} profile={profile} handleEdit={handleEdit} handleDelete={handleDelete}></EmployeeProfileCard>
        )):<EditForm user={user}></EditForm>
      }
    </div>
  );
};

export default ProfileCard;
