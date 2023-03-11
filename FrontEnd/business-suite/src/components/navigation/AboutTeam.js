import "../../styles/AboutTeam.css";
import Swapnil from "../../images/about/Swapnil.jpg"
import Prathamesh from "../../images/about/Prathamesh.jpg"


const AboutTeam = () => {

    return (
        <div className="AboutTeamBody">
            <div className="MemberCardBody">
                <div className="ProfilePhotoBody">
                    <img src={Swapnil} alt="Swapnil Gadave" className="ProfilePhoto"></img>
                </div>
                <div>
                    <table className="ProfileData">
                        <tr>
                            <th>Name:-</th>
                            <td>Swapnil Kisan Gadave</td>
                        </tr>
                        <tr>
                            <th>Roll No.:-</th>
                            <td>229030</td>
                        </tr>
                        <tr>
                            <th>PRN:-</th>
                            <td>220941220054</td>
                        </tr>
                        <tr>
                            <th>Git Hub:-</th>
                            <td><a href="https://github.com/sgadave">sgadave</a></td>
                        </tr>
                        <tr>
                            <th>LinkedIn:-</th>
                            <td><a href="https://www.linkedin.com/in/swapnil-gadave-8a090318b/">Swapnil Gadave</a></td>
                        </tr>
                    </table>
                </div>
            </div>
            <div className="MemberCardBody">
                <div className="ProfilePhotoBody">
                    <img src={Prathamesh} alt="Prathamesh Saraf" className="ProfilePhoto"></img>
                </div>
                <div>
                    <table className="ProfileData">
                        <tr>
                            <th>Name:-</th>
                            <td>Prathamesh Shamrao Saraf</td>
                        </tr>
                        <tr>
                            <th>Roll No.:-</th>
                            <td>229062</td>
                        </tr>
                        <tr>
                            <th>PRN:-</th>
                            <td>220941220118</td>
                        </tr>
                        <tr>
                            <th>Git Hub:-</th>
                            <td><a href="https://github.com/sarafprathamesh">sarafprathamesh</a></td>
                        </tr>
                        <tr>
                            <th>LinkedIn:-</th>
                            <td><a href="https://www.linkedin.com/in/prathamesh-saraf-46681b177/">Prathamesh Saraf</a></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    );
}

export default AboutTeam;