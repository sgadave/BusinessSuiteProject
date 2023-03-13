import React, { useState, useEffect } from 'react';
import CareerService from '../../../services/CareerService';
import "../../../styles/PdfViewer.css"

const PdfViewer = ({ user }) => {
  const [pdf, setPdf] = useState('');

  useEffect(() => {
    console.log("profile",user)
    CareerService.getApplicantResume(user.applicantCredentials.userName).then((response) => {
      const file = new Blob([response.data], { type: 'application/pdf' });
      const fileURL = URL.createObjectURL(file);
      setPdf(fileURL);
    });
  }, [user]);

  return (
    <div>
      <iframe title="PDF Viewer" className='pdfDisplay' src={pdf} />
    </div>
  );
};

export default PdfViewer;
