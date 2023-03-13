import error404 from "../../images/errorpage/error404.jpeg"
import "../../styles/Error.css"

const PageNotFoundError404=()=>{

    return(
        <div >
          <img src={error404} className="error404Body"></img>
        </div>
    )
}

export default PageNotFoundError404;