import { useEffect, useState } from "react";
import UserServices from "../../../services/UserServices";
import "../../../styles/commonStyle.css"
import "../../../styles/ProfileStyle.css"

const UpdateApplicantProfile = () => {    
    let [employee, setEmployee] = useState({
        userName:"",
        firstName: "null",
        middleName: "null",
        lastName: "null",
        address: "null",
        dob: "null",
        gender: "null",
    })
    let [img, setImg] = useState({})
    useEffect(() => {
        UserServices.getUserByUserName(sessionStorage.getItem("user_details")).then((resp) => {
            console.log(resp.data);
            setEmployee({ ...employee, ...resp.data} )
        }).catch((err) => {
            console.log("Employee Profile Page : ", err)
        })

        UserServices.getProfileImageByUserName(sessionStorage.getItem("user_details")).then((resp) => {
            setImg(URL.createObjectURL(resp.data))
        }).catch((err) => {
            console.log("Employee Profile Image Err", err)
        })
    }, [])

    const updateUserData=()=>{
        console.log(employee)
    }

    return (
        <div className="profileViewerBody">
            <table className="profileDataBody">
                <tbody>
                    <tr>
                        <td rowSpan={5}>
                            <img src={img} className="ProfileImage"></img>
                        </td>
                        <td>
                            Employee Id
                        </td>
                        <td>
                            <input type="number" className="userDataViewer" value={employee.employeeId || ''} disabled={true}></input>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            User Name
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Employee Name
                        </td>
                        <td>
                            <input type="text" className="userDataViewer" placeholder="FirstName" value={employee.firstName}></input>
                            <input type="text" className="userDataViewer" placeholder="MiddleName" value={employee.middleName}></input>
                            <input type="text" className="userDataViewer" placeholder="LastName" value={employee.lastName}></input>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Address
                        </td>
                        <td>
                            <input type="text" className="userDataViewer" placeholder="Address" value={employee.address}></input>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Date of Birth
                        </td>
                        <td>
                            <input type="date" className="userDataViewer" placeholder="DOB" value={employee.dob} ></input>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Gender
                        </td>
                        <td>
                            <input type="text" className="userDataViewer" value={employee.gender} pattern="[A-Z]"></input>
                        </td>
                    </tr>
                    
                    <tr>
                        <td colSpan={3}>
                            <button className="loginButton buttonWidth" onClick={updateUserData}>Update Data</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    )
}

export default UpdateApplicantProfile;