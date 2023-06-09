// Agora.io Configuration
const APP_ID = "Application Id From Agora.io Console";
const TOKEN = "Token Generated From Agora.io Console"
const CHANNEL = "Channel Name For Video Conferencing";

const client = AgoraRTC.createClient({ mode: 'rtc', codec: 'vp8' });

let localTracks = [];
let remoteUsers = {};

let joinAndDisplayLocalStream = async () => {

    client.on('user-published', handleUserJoined)

    client.on('user-left', handleUserLeft)

    let UID = await client.join(APP_ID, CHANNEL, TOKEN, null)

    localTracks = await AgoraRTC.createMicrophoneAndCameraTracks()
    let player = `<div class="video-container" id="user-container-${UID}"> 
    <div class="video-player" id="user-${UID}"></div> 
    </div>`
    document.getElementById('video-streams').insertAdjacentHTML('beforeend', player)
    localTracks[1].play(`user-${UID}`)
    await client.publish([localTracks[0], localTracks[1]])
}

let joinStream = async () => {
    await joinAndDisplayLocalStream()
    document.getElementById('join-btn').style.display = 'none'
    document.getElementById('stream-controls').style.display = 'flex'
}

let handleUserJoined = async (user, mediaType) => {
    remoteUsers[user.uid] = user
    await client.subscribe(user, mediaType)

    if (mediaType === 'video') {
        let player = document.getElementById(`user-container-${user.uid}`)
        if (player != null) {
            player.remove()
        }

        player = `<div class="video-container" id="user-container-${user.uid}"> 
    <div class="video-player" id="user-${user.uid}"></div> 
    </div>`
        document.getElementById('video-streams').insertAdjacentHTML('beforeend', player)
        user.videoTrack.play(`user-${user.uid}`)
    }
    if (mediaType === 'audio') {
        user.audioTrack.play()

    }
}


let handleUserLeft = async (user) => {
    delete remoteUsers[user.uid]
    document.getElementById(`user-container-${user.uid}`).remove()
}
let leaveAndRemoveLocalStream = async () => {
    for (let i = 0; localTracks.length > i; i++) {
        localTracks[i].stop()
        localTracks[i].close()
    }
    await client.leave()
    document.getElementById('join-btn').style.display = 'block' 
    document.getElementById('stream-controls').style.display = 'none'
    document.getElementById('video-streams').innerHTML =''
    }

let toggleMic = async (e) => {
    if (localTracks[0].muted) {
        await localTracks[0].setMuted(false)
        document.getElementById('mic-btn').innerText = 'Mic on'
        document.getElementById('mic-btn').style.backgroundColor='cadetblue'
    } else {
        await localTracks[0].setMuted(true)
        document.getElementById('mic-btn').innerText = 'Mic off'
        document.getElementById('mic-btn').style.backgroundColor= '#EE4B2B'
    }
}

let toggleCamera = async (e) =>{
    if(localTracks[1].muted){
        await localTracks[1].setMuted(false)
        console.log("camera is On")
        document.getElementById('camera-btn').innerText = 'Camera on'
        document.getElementById('camera-btn').style.backgroundColor='cadetblue'
    }else{
        await localTracks[1].setMuted(true)
        console.log("camera is Off")
        document.getElementById('camera-btn').innerText =  'Camera off'
        document.getElementById('camera-btn').style.backgroundColor= '#EE4B2B'
    }
}
