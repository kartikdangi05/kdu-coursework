const express = require('express');
const http = require('http');
const cors = require('cors');
const { Server } = require('socket.io');
const path = require('path');
const session = require('express-session');
const moment = require('moment');

const app = express();
const server = http.createServer(app);
const PORT = 3001;

const userRoutes = require('./routes/userRoute');

/**
 * Creating socket server
 */
const io = new Server(server, {
    cors: {
        origin: "http://127.0.0.1:5500"
    }
});

let activeUsers = new Set();
let chatData = {};
/**
 * Predefined Users array
 */
const users = [
    {
        "id": "1",
        "user_name": "alice_smith",
        "user_email_id": "alice@example.com",
        "password": "alice",
        "profile_url": "../images/a.png"
    },
    {
        "id": "2",
        "user_name": "bob_jones",
        "user_email_id": "bob@example.com",
        "password": "bob",
        "profile_url": "../images/b.png"
    },
    {
        "id": "3",
        "user_name": "charlie_doe",
        "user_email_id": "charlie@example.com",
        "password": "charlie",
        "profile_url": "../images/c.png"
    },
    {
        "id": "4",
        "user_name": "daisy_smith",
        "user_email_id": "daisy@example.com",
        "password": "daisy",
        "profile_url": "../images/d.png"
    },
    {
        "id": "5",
        "user_name": "edward_jones",
        "user_email_id": "edward@example.com",
        "password": "edward",
        "profile_url": "../images/e.png"
    }
]

let posts = [];

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
        user = {socketId : socket.id, ...userData};
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

app.use(express.static(path.join(__dirname, 'public')));
app.use(express.json());
app.use(cors());
app.use(session({
    secret: 'secret_key',
    resave: false,
    saveUninitialized: true
}));

/**
 * Generating some posts at start
 * @param {*} users 
 * @param {*} numberOfPosts 
 */
function generateRandomPosts(users, numberOfPosts) {
    for (let i = 0; i < numberOfPosts; i++) {
        const randomUserIndex = Math.floor(Math.random() * users.length);
        const user = users[randomUserIndex];
        const newPost = {
            id: posts.length + 1,
            postContent: `Post : ${i + 1} -> Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.`,
            user: user.user_name,
            time: moment().format('DD/MM HH:mm:ss'),
            image: user.profile_url
        };
        posts.push(newPost);
    }
}

generateRandomPosts(users,25);

/**
 * API Endpoints
 */
app.get('/', (req, res) => {
    if (!req.session.isAuth)
        return res.redirect('/user/login');
    else
        res.sendFile(path.join(__dirname, 'public','html', 'index.html'));
});

app.get('/user/login', (req, res) => {
    res.sendFile(path.join(__dirname, 'public','html', 'login.html'));
});

app.post('/user/login', (req, res) => {
    const reqUser = req.body;
    const user = users.find(u => u.user_name === reqUser.username && u.password === reqUser.password);
    
    if (user) {
        req.session.isAuth = true; 
        res.send({ userDetails: user, message: "User is found successfully!" });
    } else {
        res.status(401).send({ message: "Invalid username or password" });
        res.redirect('/user/login');
    }
});

app.get('/api/posts', (req, res) => {
    const currentPage = req.query.currentPage || 1;
    const postsPerPage = 10;

    let startIndex = (currentPage - 1) * postsPerPage;
    if(startIndex > posts.length){
        startIndex = 0;
    }
    const endIndex = startIndex + postsPerPage;

    const currentPosts = posts.slice(startIndex, endIndex);

    res.json(currentPosts);
});

app.post('/api/posts', (req, res) => {
    const { postContent, user, time, image, imageUrl } = req.body;
    const newPost = {
        id: posts.length + 1,
        postContent: postContent,
        user: user,
        time: moment(time).format('DD/MM HH:mm:ss'),
        image: image,
        imageUrl : imageUrl
    };
    posts.unshift(newPost);
    res.status(201).json(newPost);
});

server.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
})