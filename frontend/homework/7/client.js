let mainDoc = document.getElementById("main");
const socket = io("http://localhost:3001");
socket.on('connect', () => {
        console.log('Connected to server');
});

socket.on('disconnect', () => {
        console.log('Disconnected from server');
});

const messagesList = document.getElementById('messagesList');

function sendMessage() {
    const message = document.getElementById('messageInput').value;
    if(message.length == 0) return;
    socket.emit('chat message', { message }); 
    const div = document.createElement('div');
    div.classList.add("youdiv");
    const listItem = document.createElement('li');
    listItem.id = "you";
    listItem.textContent = message; 
    var icon = document.createElement('i');
    icon.classList.add('fi', 'fi-sr-circle-y');
    div.appendChild(listItem);
    div.appendChild(icon);
    messagesList.appendChild(div);
    document.getElementById('messageInput').value = ''; 
}

socket.on('get-message', (data) => { 
    const div = document.createElement('div');
    div.classList.add("youdiv");
    const listItem = document.createElement('li');
    listItem.id = "user";
    listItem.textContent = `${data.message}`; 
    messagesList.appendChild(listItem);
    var icon = document.createElement('i');
    icon.classList.add('fi', 'fi-sr-circle-u');
    div.appendChild(icon);
    div.appendChild(listItem);
    messagesList.appendChild(div);
    document.getElementById('messageInput').value = ''; 
});