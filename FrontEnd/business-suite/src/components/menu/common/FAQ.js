import React from "react";
import "../../../styles/faq.css";

const FAQ = () => {
  return (
    <div className="faq-container">
      <h1>Frequently Asked Questions</h1>
      <div className="faq-section">
        <h2>General Questions</h2>
        <div className="faq-question">
          <h3>What is the hiring process at SP Solutions?</h3>
          <p>
            Our hiring process includes resume screening, phone interviews,
            technical assessments, and in-person interviews. The exact process
            may vary depending on the position you are applying for.
          </p>
        </div>
        <div className="faq-question">
          <h3>How long does the hiring process usually take?</h3>
          <p>
            The length of the hiring process can vary depending on the
            position and the number of applicants. We aim to move the process
            along as quickly as possible while still being thorough in our
            evaluations.
          </p>
        </div>
      </div>
      <div className="faq-section">
        <h2>Technical Questions</h2>
        <div className="faq-question">
          <h3>What programming languages do you use at SP Solutions?</h3>
          <p>
            We use a variety of programming languages, including Java, Python,
            JavaScript, and C++. The specific languages used may depend on the
            project or team you are working on.
          </p>
        </div>
        <div className="faq-question">
          <h3>What is your approach to testing and quality assurance?</h3>
          <p>
            We have a comprehensive testing and quality assurance process in
            place to ensure that our software is stable and reliable. This
            process includes unit testing, integration testing, and end-to-end
            testing, as well as code reviews and automated testing tools.
          </p>
        </div>
      </div>
    </div>
  );
};

export default FAQ;
