import React from 'react';
import "../../../styles/JobPost.css";

const JobPost = ({ jobPost, handleApply}) => {
  const handleClick = () => {
    handleApply(jobPost.jobPostId);
  };

  return (
    <div className="job-post">
      <div className="job-post__title">{jobPost.jobTitle}</div>
      <div className="job-post__category">{jobPost.jobCategory}</div>
      <div className="job-post__type">{jobPost.jobType}</div>
      <div className="job-post__date">Posted: {jobPost.appPostDate}</div>
      <div className="job-post__date">Start Date: {jobPost.appStartDate}</div>
      <div className="job-post__date">Stop Date: {jobPost.appStopDate}</div>
      <div className="job-post__salary">Salary: {jobPost.salary}</div>
      <div className="job-post__city">Location: {jobPost.city}</div>
      <div className="job-post__education">Education: {jobPost.eduQualification}</div>
      <div className="job-post__description">{jobPost.description}</div>
      <button className="job-post__apply" onClick={handleClick}>Apply</button>
    </div>
  );
}

export default JobPost;
