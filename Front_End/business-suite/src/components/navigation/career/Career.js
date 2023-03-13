import React, { useEffect, useState } from 'react';
import "../../../styles/Career.css"
import CareerService from '../../../services/CareerService';
import JobPost from './JobPost';
import { useNavigate } from 'react-router-dom';

const CareerPage = () => {
  const [jobPosts, setJobPosts] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedJobPost, setSelectedJobPost] = useState(null);
  const navigate = useNavigate();

  const handleApply = (jobId) => {
    console.log(jobId)
    sessionStorage.getItem("appId") === null ?
      navigate("/login")
      :
      CareerService.applyToJob(sessionStorage.getItem("appId"), jobId)
        .then((response) => {
          console.log(response.data);
          // You can show a success message or redirect the user to a confirmation page
        })
        .catch((error) => {
          console.error(error);
          // You can show an error message to the user
        });

  };



  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value);
  };

  const filteredJobPosts = jobPosts.filter((jobPost) => (
    jobPost.jobTitle.toLowerCase().includes(searchTerm.toLowerCase()) || jobPost.jobCategory.toLowerCase().includes(searchTerm.toLowerCase())
    )
  );

  useEffect(() => {
    CareerService.getAllJobDetails()
      .then((response) => {
        setJobPosts(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <div className="career-container">
      <header>
        <h1>Careers</h1>
        <p>Find your dream job at our company</p>
      </header>
      <div className="search-container">
        <input
          type="text"
          placeholder="Search for job title"
          className="search-input"
          value={searchTerm}
          onChange={handleSearchChange}
        />
      </div>
      <div className="job-post-container">
        {filteredJobPosts.map((jobPost) => (
          <JobPost key={jobPost.jobPostId} jobPost={jobPost} handleApply={handleApply} />
        ))}
      </div>
    </div>
  );
};




export default CareerPage;