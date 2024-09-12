const express = require('express');
const app = express();
app.use(express.json());

const twitterRouter = require('./routes/twitter');
app.use("/api/twitter",twitterRouter);

app.listen(5001, () => {
    console.log("Server is running on port 5001");
})