import React, { useEffect, useState } from 'react';
import UserServices from "../../../services/UserServices"
import '../../../styles/ProfileCard.css';
import ApplicantEditForm from '../common/ApplicantEditForm';
import ApplicantProfileCard from './ApplicantProfileCard';

const ApplicantProfileMenu = () => {
  const [profiles, setProfiles] = useState([]);
  const [user, setUser] = useState({});
  const [editStatus, setEditStatus] = useState(false)


  useEffect(() => {
    UserServices.getAllApplicants()
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
    UserServices.deleteApplicantDetails(userName).then((resp) => {
      console.log(resp.data)
    }).catch((err) => {
      console.log(err)
    })
    document.location.reload();
  };

  return (
    <div id="profile-cards">
      {
        !editStatus ? profiles.map(profile => (
          <ApplicantProfileCard key={profile.applicantId} profile={profile} handleEdit={handleEdit} handleDelete={handleDelete}></ApplicantProfileCard>
        )) : <ApplicantEditForm user={user}></ApplicantEditForm>
      }

    </div>
  );
};

export default ApplicantProfileMenu;
