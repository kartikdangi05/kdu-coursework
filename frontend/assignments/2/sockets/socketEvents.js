const moment = require('moment');

let activeUsers = new Set();
let chatData = {};

function initializeSocket(io) {
    /**
     * Socket functionalities
     */
    io.on("connection", (socket) => {
        let user = null;
        console.log("user connected");

        /**
         * Connecting user and adding it to active list
         */
        socket.on('connect me', (userData) => {
            user = { socketId: socket.id, ...userData };
            activeUsers.add(user);
            io.emit('active users', [...activeUsers]);
        })

        /**
         * Storing messages
         */
        socket.on('send private message', (data) => {
            if (!chatData[data.from]) {
                chatData[data.from] = {};
            }
            if (!chatData[data.to]) {
                chatData[data.to] = {};
            }
            if (!chatData[data.from][data.to]) {
                chatData[data.from][data.to] = [];
            }
            if (!chatData[data.to][data.from]) {
                chatData[data.to][data.from] = [];
            }

            const timestamp = moment().format('DD/MM HH:mm');
            const messageObject = {
                sender: data.from,
                message: data.message,
                timestamp: timestamp,
                socketId: socket.id
            };
            messageObject["type"] = "outgoing";
            chatData[data.from][data.to].push(messageObject);
            messageObject["type"] = "incoming";
            chatData[data.to][data.from].push(messageObject);

            io.to(data.socketId).emit('recieve private message', messageObject);

        })

        /**
         * Fetching previous messages
         */
        socket.on('fetch previous messages', ({ receiverUsername, senderUsername }) => {

            if (chatData[senderUsername] && chatData[senderUsername][receiverUsername]) {
                console.log(chatData[senderUsername][receiverUsername]);
                const previousMessages = chatData[senderUsername][receiverUsername].map(message => {
                    return {
                        ...message,
                        type: message.sender === senderUsername ? "outgoing" : "incoming"
                    };
                });
                socket.emit('previous messages', previousMessages);
            } else {
                return [];
            }
        });

        socket.on('disconnect', () => {
            activeUsers.delete(user);
            console.log('A user disconnected');
            socket.emit('active users', ...activeUsers);
        });
    });
}

module.exports = initializeSocket;
