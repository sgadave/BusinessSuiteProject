import { useEffect, useState } from "react";
// import UserServices from "../../service/UserServices"
import EmployeeCard from "./EmployeeCard";
import "../../../styles/EmployeeCard.css"

const EmployeeList = (props) => {


    const renderList = () => props.employee.map((emp, index) => {
        return <tr key={emp.employeeId}><td colSpan={5}><EmployeeCard employee={emp}  ></EmployeeCard></td></tr>
    });

    return (
        <div>
            <table>
                <thead>
                    <tr>
                        <td className="dataWidth">
                            Employee Id
                        </td>
                        <td className="dataWidth">
                            Employee name
                        </td>
                        <td className="dataWidth">
                            Designation
                        </td>
                        <td className="dataWidth">
                            Department
                        </td>
                        <td className="dataWidth">
                            Employee Address
                        </td>
                    </tr>
                </thead>
                <tbody>
                    {renderList()}
                </tbody>
            </table>

        </div>
    )
}

export default EmployeeList;