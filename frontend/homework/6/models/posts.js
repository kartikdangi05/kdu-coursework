const { v4: uuidv4 } = require('uuid');

let posts = [];

const addPost = (data) => {
    const uuid = uuidv4();
    const post = { id : uuid, createdDate : new Date().toLocaleString('en-US', { timeZone: 'Asia/Kolkata' }),  ...data};
    posts.push(post);
    return uuid;
}

const getAllPosts = () => {
    return posts;
}

const getPostById = (id) => {
    const post = posts.find(post => post.id === id);
    return post;
}

module.exports = {addPost, getAllPosts, getPostById};