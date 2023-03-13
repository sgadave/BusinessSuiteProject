# BusinessSuiteProject
##Academic Project Of CDAC PG-DAC Course

#Brief Information About Project
The Business Suite is a web application that is based on the Career Module from the ERP System's HR Module. The frontend of the application is developed using React, while the backend is developed using Spring Boot. In addition to this, I have also used Agora.io for peer-to-peer communication, which enables video calling in the system.

The Business Suite allows any anonymous user to see the job posts created by the HR and then apply for it. To apply, the user must create a profile, which is then shortlisted and scheduled for an interview by the HR. For the interview purpose, we have used Agora.io, which is based on the WebRTC protocol. Using STUN Servers and ICE Candidates, which are public information of the machine, we create a channel where we continuously send these obtained ICE Candidates. When a peer joins the channel, the ICE Candidates are sent to the peer, and the peer's ICE Candidates are sent to the host, and a connection is formed. Video and audio information are then sent to each other through this connection.

The Business Suite project has been a challenging and rewarding experience for me. It has allowed me to gain experience in developing both frontend and backend components of a web application, as well as in integrating third-party tools like Agora.io for real-time communication.

