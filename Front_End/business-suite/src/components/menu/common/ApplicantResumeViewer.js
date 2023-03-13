import React, { useEffect, useState } from 'react';
import UserServices from "../../../services/UserServices"
import '../../../styles/ProfileCard.css';
import ApplicantResumeCard from './ApplicantResumeCard';
import PdfViewer from '../hr/PdfViewer';

const ApplicantResumeViewer = () => {
  const [profiles, setProfiles] = useState([]);
  const [user, setUser] = useState({});
  const [viewResumeStatus, setViewResumeStatus] = useState(false)


  useEffect(() => {
    UserServices.getAllApplicants()
      .then(response => {
        setProfiles(response.data);
      })
      .catch(error => {
        console.error(error);
      });
  }, []);


  const handleViewResume = (profile) => {
    setUser(profile);
    setViewResumeStatus(true);
  };


  return (
    <div id="profile-cards">
      {
        !viewResumeStatus ? profiles.map(profile => (
          <ApplicantResumeCard key={profile.applicantId} profile={profile} handleViewResume={handleViewResume} ></ApplicantResumeCard>
        )) : <PdfViewer user={user}></PdfViewer>
      }

    </div>
  );
};

export default ApplicantResumeViewer;
