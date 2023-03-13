import "../../../styles/Interview.css"

const InterviewVideoCall = () => {
    const handleFullscreen = () => {
        // if (document.fullscreenElement) {
        //   document.exitFullscreen();
        // } else {
          
        // }
      };
    return (
            <div className="videoWindow" >
                <iframe className="videoCallWindow" src="https://sgadave18.github.io/vconferencing/" allow="camera;microphone"></iframe>
            </div>
    )
}

export default InterviewVideoCall;