import "../../../styles/EmployeeCard.css"

const EmployeeCard = function (props) {

    return (
        <div >
            <div className="EmployeeCardBody">
                <table>
                    <tbody>
                        <tr>
                            <td className="dataWidth">
                                {props.employee.employeeId}
                            </td>
                            <td className="dataWidth">
                                {props.employee.firstName}
                            </td>
                            <td className="dataWidth">
                                {props.employee.designation}
                            </td>
                            <td className="dataWidth">
                                {props.employee.departmentId.departmentName}
                            </td>
                            <td className="dataWidth">
                                {props.employee.address}
                            </td>
                        </tr>
                    </tbody>
                </table>

            </div>

        </div>
    )
}

export default EmployeeCard;