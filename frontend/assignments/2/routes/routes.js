const express = require('express');
const path = require('path');
const moment = require('moment');
const { users, posts, generateRandomPosts } = require('../utils/utils');

generateRandomPosts(users, 25);

const router = express.Router();


router.get('/', (req, res) => {
    if (!req.session.isAuth)
        return res.redirect('/user/login');
    else
        res.sendFile(path.join(__dirname, '..', 'public', 'html', 'index.html'));
});

router.get('/user/login', (req, res) => {
    res.sendFile(path.join(__dirname, '..', 'public', 'html', 'login.html'));
});

router.post('/user/login', (req, res) => {
    const reqUser = req.body;
    const user = users.find(u => u.user_name === reqUser.username && u.password === reqUser.password);
    
    if (user) {
        req.session.isAuth = true; 
        res.send({ userDetails: user, message: "User is found successfully!" });
    } else {
        res.status(401).send({ message: "Invalid username or password" });
    }
});

router.get('/api/posts', (req, res) => {
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

router.post('/api/posts', async (req, res) => {
    let { postContent, user, time, image, imageUrl } = req.body;
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



module.exports = router;