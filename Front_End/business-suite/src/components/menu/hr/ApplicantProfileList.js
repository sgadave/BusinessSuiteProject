import React, { useEffect, useState } from 'react';
import UserServices from "../../../services/UserServices"
import '../../../styles/ProfileCard.css';
import ApplicantCard from './ApplicantCard';

const ApplicantProfileList = () => {
  const [profiles, setProfiles] = useState([]);

  useEffect(() => {
    UserServices.getAllApplicants()
      .then(response => {
        setProfiles(response.data);
      })
      .catch(error => {
        console.error(error);
      });
  }, []);



  const handleDelete = (userName) => {
    UserServices.deleteApplicantDetails(userName).then((resp)=>{
      console.log(resp.data)
    }).catch((err)=>{
      console.log(err)
    })
    document.location.reload();
  };

  return (
    <div id="profile-cards">
        
      {profiles.map(profile => (

        <ApplicantCard key={profile.applicantId} profile={profile} handleDelete={handleDelete}></ApplicantCard>
      ))}
    </div>
  );
};

export default ApplicantProfileList;
