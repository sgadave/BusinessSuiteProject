
import error401 from "../../images/errorpage/error401_1.jpg"
import "../../styles/Error.css"

const UnAuthorizedUser=()=>{

    return(
        <div>
           <img className="error401Body" src={error401}></img>
        </div>
    )
}

export default UnAuthorizedUser;