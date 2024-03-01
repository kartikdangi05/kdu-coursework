const moment = require('moment');

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
module.exports = { users, posts, generateRandomPosts };
