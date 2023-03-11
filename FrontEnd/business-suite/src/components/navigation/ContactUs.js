import contactUsImg from "../../images/contactus/contactus.jpeg"
import "../../styles/ContactUs.css"

const ContactUs = () => {

    return (
        <div className="ContactUsBody">
            <div className="ContactUsFormBody">
                <div className="ContactUsForm">
                    <form>
                        <table className="contactUsTable">
                            <thead>
                                <th colSpan={3}>
                                    <div className="formTitle">
                                        Email Us
                                    </div>
                                </th>
                            </thead>
                            <tbody>
                                <tr>
                                    <td className="formStyle">
                                        <label htmlFor="emailId" className="LabelStyle">
                                            Name :
                                        </label>
                                    </td>
                                    <td>
                                        <input type="text" required placeholder="Your Name" className="InputBox"></input>
                                    </td>
                                </tr>
                                <tr>
                                    <td className="formStyle">
                                        <label htmlFor="emailId" className="LabelStyle">
                                            Email Id :
                                        </label>
                                    </td>
                                    <td>
                                        <input type="email" required placeholder="Email Id" className="InputBox"></input>
                                    </td>
                                </tr>
                                <tr>
                                    <td className="tdStyle">
                                        <label htmlFor="emailId" className="LabelStyle">
                                            Message :
                                        </label>
                                    </td>
                                    <td className="InputBoxWidth">
                                        <textarea cols="20" rows="2" className="TextAreaStyle" placeholder="Message"></textarea>
                                    </td>
              
                                </tr>
                                <tr>
                                    <td colSpan={2}>

                                        <div className="SendButtonBody">
                                            <button type="button" className="SendButton">Send</button>
                                        </div>

                                    </td>
                                </tr>
                            </tbody>
                        </table>

                    </form>
                </div>
            </div>
            <div className="ContactUsImgContainer">
                <img src={contactUsImg} alt="Contact US" className="contactUsImgStyle"></img>
            </div>
        </div>
    );
}

export default ContactUs;