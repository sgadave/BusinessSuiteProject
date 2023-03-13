import React, { useEffect, useState } from 'react';
import CareerService from '../../../services/CareerService';
import '../../../styles/ProfileCard.css';
import EmployeeInterviewCard from './EmployeeInterviewCard';

const EmployeeInterviewSchedule = () => {
  const [profiles, setProfiles] = useState([]);

  useEffect(() => {
    CareerService.getAllEmployeeInterviewSchedules(sessionStorage.getItem("appId"))
      .then(response => {
        setProfiles(response.data);
        console.log(response.data)
      })
      .catch(error => {
        console.error(error);
      });
  }, []);


  return (
    <div id="interview-cards">
      {profiles.map(profile => (
        <EmployeeInterviewCard key={profile.interviewerId} profile={profile}></EmployeeInterviewCard>
      ))}
    </div>
  );
};

export default EmployeeInterviewSchedule;
