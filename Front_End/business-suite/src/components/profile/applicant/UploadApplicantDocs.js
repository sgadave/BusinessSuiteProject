import { useEffect } from "react";
import { useState } from "react";
import UserServices from "../../../services/UserServices";
import "../../../styles/commonStyle.css"

const UploadApplicantDocs = () => {

    let [img, setImg] = useState({})
    let [govId, setgovId] = useState({ govIdNo: "", govType: "" })
    let [govIdImg, setgovIdImg] = useState({})
    let [resume, setResume] = useState({})

    const handleChangeProfile = (event) => {
        setImg(event.target.files[0])
    }
    const handleChangeGovId = (event) => {
        console.log(event.target.value)
        let { name, value } = event.target
        setgovId({ ...govId, [name]: value })
    }
    const handleChangeGovIdImg = (event) => {
        setgovIdImg(event.target.files[0])
    }
    const handleChangeResume = (event) => {
        setResume(event.target.files[0])
    }

    const UploadDocuments = (event) => {
        event.preventDefault();
        UserServices.uploadApplicantProfile(img).then((resp) => {
            UserServices.uploadApplicantGovId(govId, govIdImg, resp.data).then((resp) => {
                UserServices.uploadApplicantResume(resume, resp.data).then((resp) => {
                    console.log(resp.data)
                    sessionStorage.setItem("profile_status", resp.data.appStatus)
                    document.location.reload();
                }).catch((err) => {
                    console.log("Resume Upload", err)
                })
            }).catch((err) => {
                console.log("gov Id Upload", err)
            })
        }).catch((err) => {
            console.log("Profile Img Upload", err)
        })
    }

    return (
        <div>
            <form method="post" onSubmit={UploadDocuments}>
                <table>
                    <tbody>
                        <tr>
                            <td>
                                Profile Image :
                            </td>
                            <td>
                                <input type="file" onChange={handleChangeProfile} ></input>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div className="form-group">
                                    <label htmlFor="govType"> Government Id Type :</label>
                                </div>
                            </td>
                            <td>
                                <div className="form-group">
                                    <select id="govType" name="govType" required
                                        value={govId.govType} onChange={handleChangeGovId}
                                    >
                                        <option value="">---------Gov Id Type---------</option>
                                        <option value="PASSPORT">PASSPORT</option>
                                        <option value="AADHAR">AADHAR</option>
                                        <option value="PANCARD">PANCARD</option>
                                        <option value="DRIVINGLICENSE">DRIVINGLICENSE</option>
                                    </select>
                                </div>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                Government Id No :
                            </td>
                            <td>
                                <input type="number" value={govId.govIdNo} name="govIdNo" onChange={handleChangeGovId} placeholder="Gov Id No."></input>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Government Id Image :
                            </td>
                            <td>
                                <input type="file" onChange={handleChangeGovIdImg}></input>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Resume :
                            </td>
                            <td>
                                <input type="file" onChange={handleChangeResume} ></input>
                            </td>
                        </tr>
                        <tr >
                            <td colSpan={2}>
                                <button type="submit" >Submit</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    )
}

export default UploadApplicantDocs;