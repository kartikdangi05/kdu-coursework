const express = require('express');
const http = require('http');
const cors = require('cors');
const { Server } = require('socket.io');

const app = express();
const server = http.createServer(app);

const io = new Server(server, {
    cors: {
        origin: "http://127.0.0.1:5500"
    }
});

app.use(express.json());

io.on("connection", (socket) => {
    console.log("Connect to a client!");

    socket.on("chat message", (data) => { 
        socket.broadcast.emit("get-message", data); 
    });

    socket.on('disconnect', () => {
        console.log('A user disconnected');
    });
});

server.listen(3001, () => {
    console.log(`Server is listening on port 3001`);
});
