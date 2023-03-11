import React, { useState, useEffect } from 'react';
import UserServices from '../../../services/UserServices';

const ApplicantEditForm = ({ user }) => {
    const [firstName, setFirstName] = useState('');
    const [middleName, setMiddleName] = useState('');
    const [lastName, setLastName] = useState('');
    const [emailId, setEmailId] = useState('');
    const [city, setCity] = useState('');
    const [userId, setUserId] = useState()

    useEffect(() => {
        setFirstName(user.firstName);
        setMiddleName(user.middleName);
        setLastName(user.lastName);
        setEmailId(user.emailId);
        setCity(user.address);
        setUserId(user.applicantId)
    }, [user]);

    const handleSubmit = (e) => {
        e.preventDefault();
        const updatedUser = { firstName, middleName, lastName, emailId, city, userId };
        UserServices.updateApplicantDetails(updatedUser)
            .then((response) => {
                console.log(response.data);
                document.location.reload();
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return (
        <div className="form-container" onSubmit={handleSubmit}>
            <h1>Update Profile</h1>
            <form id="job-application-form">
                <div className="form-group">
                    <label htmlFor="jobTitle">First Name:</label>
                    <input
                        type="text"
                        id="jobTitle"
                        value={firstName}
                        onChange={(e) => setFirstName(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="jobTitle">Middle Name:</label>
                    <input
                        type="text"
                        id="jobTitle"
                        value={middleName}
                        onChange={(e) => setMiddleName(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="jobTitle">Last Name:</label>
                    <input
                        type="text"
                        id="jobTitle"
                        value={lastName}
                        onChange={(e) => setLastName(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="jobTitle">Email:</label>
                    <input
                        type="email"
                        id="jobTitle"
                        value={emailId}
                        onChange={(e) => setEmailId(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="jobTitle">City:</label>
                    <input
                        type="text"
                        id="jobTitle"
                        value={city}
                        onChange={(e) => setCity(e.target.value)}
                        required
                    />
                </div>
                
                <div >
                    <button type="submit" className="form-submit">Submit</button>
                </div>
            </form>
        </div>
    );
};

export default ApplicantEditForm;
