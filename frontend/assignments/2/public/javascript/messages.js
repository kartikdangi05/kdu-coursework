let loggedInUser = null;
const home = document.querySelector('.home');
const dpImage4 = document.querySelector('.dp4');
const username1 = document.querySelector('.home-nav-profile-name');
const username3 = document.querySelector('.home-nav-profile-username');

const socket = io("http://localhost:3001");

socket.on('connect', () => {
    console.log('Connected to server');
});

socket.on('disconnect', () => {
    console.log('Disconnected from server');
});
/**
 * Loading user data and setting up socket with server 
 */
window.onload = function() {
    loggedInUser = JSON.parse(sessionStorage.getItem('loggedInUser'));
    dpImage4.src = loggedInUser.userDetails.profile_url;
    
    username1.textContent = loggedInUser.userDetails.user_name;
    username3.textContent = loggedInUser.userDetails.user_name;

    socket.emit('connect me', {
        profileUrl: loggedInUser.userDetails.profile_url,
        username: loggedInUser.userDetails.user_name,
    });
};

let socketUsers = {};
const msgHeading2 = document.querySelector('.msg-heading-2');
const inputBox = document.querySelector('.input-msg-box');
const messageBox = document.getElementById('message-box');
const noActiveUser = document.querySelector('.no-active-user');
const messageContainer = document.querySelector('.message-container');
const messageShow = document.querySelector('.message-show');
const usersActive = document.querySelector('.users-active');
const backArrow = document.querySelector('.back-arrow');

/**
 * Socket to add active users to screen
 */
socket.on('active users', (users) => {
    socketUsers = {};
    const activeUsers = document.querySelector('.active-users');
    activeUsers.innerHTML = '';
    const loggedInUser = JSON.parse(sessionStorage.getItem('loggedInUser'));
    if(users.length == 1){ 
        inputBox.style.opacity = 0;
        msgHeading2.textContent = '';
        messageBox.style.opacity = 0;
        noActiveUser.style.display = 'block';
    }
    else{
        noActiveUser.style.display = 'none';
        users.reverse().forEach(element => {
            if(element.username === loggedInUser.userDetails.user_name){
                return;
            }
            const div = document.createElement('div');
            div.classList.add("active-user");

            div.addEventListener('click',handleActiveUserClick);
        
            const imgTag = document.createElement('img');
            imgTag.src = element.profileUrl;
            imgTag.height = 40;
            imgTag.width = 40;
        
            const nameTag = document.createElement('p');
            const spanTag = document.createElement('span');
            spanTag.textContent = ` @${element.username} `;
            nameTag.textContent = element.username;
            nameTag.appendChild(spanTag);
        
            div.appendChild(imgTag);
            div.appendChild(nameTag);
            activeUsers.appendChild(div);
            socketUsers[element.username] = element.socketId;
        });
    }
})
let selectedUsername = null;
let selectedSocketId = null;

/**
 * Function to open the messages of the particular user whom we clicked
 * @param {*} event 
 */
function handleActiveUserClick(event){
    messageContainer.innerHTML = '';
    const username = event.target.innerText;
    const name = username.split('@');
    if(window.innerHeight <= 896){
        msgHeading2.innerHTML = '';
        const arrowBtn = document.createElement('p');
        const headingName = document.createElement('h3');
        arrowBtn.innerHTML = `&#8592`;
        arrowBtn.addEventListener('click',handleArrow);
        headingName.textContent = `${name[1]}`
        msgHeading2.appendChild(arrowBtn);
        msgHeading2.appendChild(headingName);
    }
    else{
        msgHeading2.textContent = name[1];
    }
    selectedUsername = name[1];
    selectedSocketId = socketUsers[name[1]];
    inputBox.style.opacity = 1;
    messageBox.style.opacity = 1;

    if(window.innerHeight <= 896){
        messageShow.style.display = 'flex';
        usersActive.style.display = 'none';
    }

    socket.emit('fetch previous messages', {
        receiverUsername: name[1],
        senderUsername: loggedInUser.userDetails.user_name
    });
}

/**
 * Fetching previous messages
 */
socket.on('previous messages', (messages) => {
    messages.forEach(message => {
        displayMessage(message.message, message.type);
    });
});

/**
 * Function for sending messages
 * @returns 
 */

function sendMessage(){
    const message = messageBox.value.trim();
    messageBox.value = ''; 

    if (message === '') return; 

    socket.emit('send private message', {
        to: selectedUsername,
        from: loggedInUser.userDetails.user_name,
        socketId: selectedSocketId,
        message: message
    });

    displayMessage(message, 'outgoing');
}

/**
 * Adding messages to DOM
 * @param {*} message 
 * @param {*} messageType -> incoming/outgoing
 */
function displayMessage(message, messageType) {
    const messageElement = document.createElement('div');
    messageElement.classList.add('message-text', messageType);

    const timestampElement = document.createElement('span');
    timestampElement.classList.add('timestamp');
    const timestamp = moment().format('DD/MM HH:mm');
    timestampElement.textContent = timestamp;

    const messageContentElement = document.createElement('span');
    messageContentElement.classList.add('message-content');
    messageContentElement.textContent = message;

    messageElement.appendChild(messageContentElement);
    messageElement.appendChild(timestampElement);

    messageContainer.appendChild(messageElement);
}


socket.on('recieve private message', (data) => {
    if(selectedUsername == data.sender)
        displayMessage(data.message, 'incoming');
})

function handleArrow(){
    messageShow.style.display = 'none';
    usersActive.style.display = 'flex';
}


home.addEventListener('click', () => {
    window.location.href ='../html/index.html';
})