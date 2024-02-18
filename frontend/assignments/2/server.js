const express = require('express');
const http = require('http');
const cors = require('cors');
const { Server } = require('socket.io');
const session = require('express-session');
const path = require('path');
const routes = require('./routes/routes');
const initializeSocket = require('./sockets/socketEvents');

const app = express();
const server = http.createServer(app);
const PORT = 3001;

const io = new Server(server, {
    cors: {
        origin: "http://127.0.0.1:5500"
    }
});

app.use(express.static(path.join(__dirname, 'public')));
app.use(express.json());
app.use(cors());
app.use(session({
    secret: 'secret_key',
    resave: false,
    saveUninitialized: true
}));

app.use('/', routes);

initializeSocket(io);

server.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
