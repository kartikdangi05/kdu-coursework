const express = require('express');
const postsModel = require('../models/posts');
const router = express.Router();

router.post('/add',(req,res) => {
    const uuid = postsModel.addPost(req.body);
    res.status(200).json({ "message" : `Post added successfully with id : ${uuid}`});
})

router.get('/getAll', (req,res) => {
    const posts = postsModel.getAllPosts();
    res.status(200).json(posts);
})

router.get('/get/:id', (req,res) => {
    const post = postsModel.getPostById(req.params.id);
    if(!post){
        return res.status(404).json({ "message" : "Post not found" });
    }
    res.status(200).json(post);
})

module.exports = router;