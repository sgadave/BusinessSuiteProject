import { useEffect, useState } from "react"
import CareerService from "../../../services/CareerService"
import UserServices from "../../../services/UserServices"
import Interview from "./Interview"


const ScheduleInterview = () => {

    let [applicants, setApplicants] = useState([])
    let [employees, setEmployees] = useState([])
    let [jobDescr, setJobDescr] = useState([])

    useEffect(() => {
        UserServices.getAllApplicants().then((resp) => {
            setApplicants(resp.data)
        }).catch((err) => {
            console.log(err)
        })
        UserServices.getAllEmployees().then((resp) => {
            setEmployees(resp.data)
        }).catch((err) => {
            console.log(err)
        })
        CareerService.getAllJobDetails().then((resp) => {
            setJobDescr(resp.data)
        }).catch((err) => {
            console.log(err)
        })
    }, [])



    return (
        <div>
            <Interview applicantArray={applicants} employeeArray={employees} jobs={jobDescr}></Interview>
        </div>
    )
}

export default ScheduleInterview