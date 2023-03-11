import React, { useEffect, useState } from 'react';
import CareerService from '../../../services/CareerService';
import '../../../styles/ProfileCard.css';
import InterviewCard from './InterviewCard';

const InterviewSchedule = () => {
  const [profiles, setProfiles] = useState([]);

  useEffect(() => {
    CareerService.getAllInterviewSchedules(sessionStorage.getItem("appId"))
      .then(response => {
        setProfiles(response.data);
        console.log(response.data);
      })
      .catch(error => {
        console.error(error);
      });
  }, []);


  return (
    <div id="interview-cards">
      {profiles.map((profile,index) => (
        <InterviewCard key={index} profile={profile}></InterviewCard>
      ))}
    </div>
  );
};

export default InterviewSchedule;
