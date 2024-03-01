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

let stockPrice = 142.32;
let historyDivs = [];

io.on("connection", (socket) => {

    console.log("user connected");
    socket.on("save history", (data) => {
        historyDivs.push(data);
    })
    
    socket.emit("load data", (historyDivs));

    setInterval(() => {
        let newStockPrice = Math.random() * (500 - 0);
        socket.emit("new price", newStockPrice);
    },5000)

    socket.on('disconnect', () => {
        console.log('A user disconnected');
    });
});

server.listen(3001, () => {
    console.log(`Server is listening on port 3001`);
});