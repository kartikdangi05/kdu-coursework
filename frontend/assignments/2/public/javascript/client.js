let loggedInUser = null;
let loading = false;
const dpImageHome = document.querySelector('.dp');
const dpImageNav = document.querySelector('.dp1');
const dpImage2 = document.querySelector('.dp2');
const dpImage4 = document.querySelector('.dp4');
const dpImage5 = document.querySelector('.dp5');

const username1 = document.querySelector('.home-nav-profile-name');
const username2 = document.querySelector('.name');
const username3 = document.querySelector('.home-nav-profile-username');
const username4 = document.querySelector('.username');

/**
 * Loading posts and setting details on load
 */
window.onload = function() {
    loggedInUser = JSON.parse(sessionStorage.getItem('loggedInUser'));
    dpImageHome.src = loggedInUser.userDetails.profile_url;
    dpImageNav.src = loggedInUser.userDetails.profile_url;
    dpImage2.src = loggedInUser.userDetails.profile_url;
    dpImage4.src = loggedInUser.userDetails.profile_url;
    dpImage5.src = loggedInUser.userDetails.profile_url;

    username1.textContent = loggedInUser.userDetails.user_name;
    username2.textContent = loggedInUser.userDetails.user_name;
    username3.textContent = loggedInUser.userDetails.user_name;
    username4.textContent = loggedInUser.userDetails.user_name;

    fetchPosts(true);
};


const messageBtn = document.querySelector(`.message-btn`);
const messageBtn2 = document.querySelector(`.message-btn2`);
const navigationSection = document.querySelector('.navigation-section');

dpImageHome.addEventListener('click', function() {
    navigationSection.classList.toggle('active');
});

dpImageNav.addEventListener('click', function() {
    navigationSection.classList.toggle('active');
});

messageBtn.addEventListener('click', function() {
    window.location.href ='../html/messages.html';
})

messageBtn2.addEventListener('click', function() {
    window.location.href ='../html/messages.html';
})

const toPostBtn = document.querySelector(".toPost-btn");
const postBox = document.querySelector(".post-box");
const layout = document.querySelector(".layout");
const backArrow = document.querySelector(".back-arrow");
const postBtn = document.querySelector('.post-btn');
const postInput = document.getElementById('post-input');
const mainDiv = document.querySelector('.main');
const postBtnDesk = document.querySelector(".post-btn-desk");
const postInputDesk = document.getElementById('post-input-desk');

backArrow.addEventListener("click", function() {
    postBox.classList.toggle("show");
    layout.classList.toggle("notShow");
});


toPostBtn.addEventListener("click", function() {
    postBox.classList.toggle("show");
    layout.classList.toggle("notShow");
});

/**
 * Adding the posts to DOM
 * @param {*} param0 
 */
function addPost({postContent, user, time, image, imageUrl}){
    const postContainer = document.createElement('div');
    postContainer.classList.add('post');
    
    const postDpContainer = document.createElement('div');
    postDpContainer.classList.add('post-dp');
    
    const profileImage = document.createElement('img');
    profileImage.classList.add('dp3');
    profileImage.src = image;
    profileImage.alt = 'profile';
    profileImage.height = '40';
    profileImage.width = '40';
    
    postDpContainer.appendChild(profileImage);
    
    const postContentContainer = document.createElement('div');
    postContentContainer.classList.add('post-content');
    
    const postContentName = document.createElement('div');
    postContentName.classList.add('post-content-name');
    postContentName.innerHTML = `${user} <span style="font-weight: 400; color: rgb(113, 118, 123);">@${user} · ${time}</span> <span style="font-weight: 700; position: absolute; right: 20px; color: rgb(113, 118, 123);"> ··· </span>`;
    
    const actualPostContent = document.createElement('div');
    actualPostContent.classList.add('actual-post-content');
    actualPostContent.innerHTML = postContent
    
    const reactionBox = document.createElement('div');
    reactionBox.classList.add('reaction-box');
    
    const postReactions = document.createElement('div');
    postReactions.classList.add('post-reactions');
    postReactions.innerHTML = ` <div class="reply">
        <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
            <g>
                <path d="M1.751 10c0-4.42 3.584-8 8.005-8h4.366c4.49 0 8.129 3.64 8.129 8.13 0 2.96-1.607 5.68-4.196 7.11l-8.054 4.46v-3.69h-.067c-4.49.1-8.183-3.51-8.183-8.01zm8.005-6c-3.317 0-6.005 2.69-6.005 6 0 3.37 2.77 6.08 6.138 6.01l.351-.01h1.761v2.3l5.087-2.81c1.951-1.08 3.163-3.13 3.163-5.36 0-3.39-2.744-6.13-6.129-6.13H9.756z"></path>
            </g>
        </svg>
    </div>
    <div class="retweet">
        <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
            <g>
                <path d="M4.5 3.88l4.432 4.14-1.364 1.46L5.5 7.55V16c0 1.1.896 2 2 2H13v2H7.5c-2.209 0-4-1.79-4-4V7.55L1.432 9.48.068 8.02 4.5 3.88zM16.5 6H11V4h5.5c2.209 0 4 1.79 4 4v8.45l2.068-1.93 1.364 1.46-4.432 4.14-4.432-4.14 1.364-1.46 2.068 1.93V8c0-1.1-.896-2-2-2z"></path>
            </g>
        </svg>
        <span class="retweets-count" style="vertical-align: bottom;">0</span>
    </div>
    <div class="like-post">
        <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi unlike-svg"><g><path d="M16.697 5.5c-1.222-.06-2.679.51-3.89 2.16l-.805 1.09-.806-1.09C9.984 6.01 8.526 5.44 7.304 5.5c-1.243.07-2.349.78-2.91 1.91-.552 1.12-.633 2.78.479 4.82 1.074 1.97 3.257 4.27 7.129 6.61 3.87-2.34 6.052-4.64 7.126-6.61 1.111-2.04 1.03-3.7.477-4.82-.561-1.13-1.666-1.84-2.908-1.91zm4.187 7.69c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path></g></svg> 
        <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi red-color like-svg" style="display: none;"><g><path d="M20.884 13.19c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path></g></svg>
        <span class="likes-count" style="vertical-align: bottom;">0</span>
    </div>
    <div class="stats">
        <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
            <g>
                <path d="M8.75 21V3h2v18h-2zM18 21V8.5h2V21h-2zM4 21l.004-10h2L6 21H4zm9.248 0v-7h2v7h-2z"></path>
            </g>
        </svg>
        <span class="stats-count" style="vertical-align: bottom;">1</span>
    </div>
    <div class="bookmarks">
        <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
            <g>
                <path d="M4 4.5C4 3.12 5.119 2 6.5 2h11C18.881 2 20 3.12 20 4.5v18.44l-8-5.71-8 5.71V4.5zM6.5 4c-.276 0-.5.22-.5.5v14.56l6-4.29 6 4.29V4.5c0-.28-.224-.5-.5-.5h-11z"></path>
            </g>
        </svg>
    </div>
    <div class="share">
        <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
            <g>
                <path d="M12 2.59l5.7 5.7-1.41 1.42L13 6.41V16h-2V6.41l-3.3 3.3-1.41-1.42L12 2.59zM21 15l-.02 3.51c0 1.38-1.12 2.49-2.5 2.49H5.5C4.11 21 3 19.88 3 18.5V15h2v3.5c0 .28.22.5.5.5h12.98c.28 0 .5-.22.5-.5L19 15h2z"></path>
            </g>
        </svg>
    </div>
`;
    
    reactionBox.appendChild(postReactions);
    postContentContainer.appendChild(postContentName);
    postContentContainer.appendChild(actualPostContent);
    if(imageUrl){
        const imageContainer = document.createElement('img');
        imageContainer.classList.add('image-post');
        imageContainer.src = imageUrl;
        postContentContainer.appendChild(imageContainer);
    }
    postContentContainer.appendChild(reactionBox);
    postContainer.appendChild(postDpContainer);
    postContainer.appendChild(postContentContainer);
    
    const mainContainer = document.querySelector('.post-container');
    mainContainer.appendChild(postContainer);    

    postBox.classList.toggle("show");
    layout.classList.toggle("notShow");
}

let currentPage = 0;

/**
 * Fetching posts 
 * @param {*} isScrollEvent -> true for scroll event, false for normal fetching after posts
 */
async function fetchPosts(isScrollEvent) {

    try {
        if(isScrollEvent)   currentPage++;
        const response = await fetch(`/api/posts?currentPage=${currentPage}`);
        if (response.ok) {
            const mainContainer = document.querySelector('.post-container');

            if(isScrollEvent){   
                const loadingSpinnerDiv = document.createElement('div');
                loadingSpinnerDiv.classList.add('loading-spinner');

                const spinnerDiv = document.createElement('div');
                spinnerDiv.classList.add('spinner');

                loadingSpinnerDiv.appendChild(spinnerDiv);
                mainContainer.appendChild(loadingSpinnerDiv);
                await new Promise(resolve => setTimeout(resolve, 2000));
            }
            else
                mainContainer.innerHTML = '';

            const currentPosts = await response.json();
            console.log(currentPosts);

            if(isScrollEvent) {
                const loadingIndicator = document.querySelector('.loading-spinner');
                if (loadingIndicator) {
                    loadingIndicator.remove();
                }
            }
            currentPosts.forEach(post => {
                addPost(post);
            });
        } else {
            console.error('Error fetching posts:', response.statusText);
        }
    } catch (error) {
        console.error('Error fetching posts:', error);
    }
}

/**
 * On bottom of page, fetch more posts
 */
async function handleScroll() {
    const { scrollTop, clientHeight, scrollHeight } = document.documentElement;
    if (scrollTop + clientHeight >= scrollHeight - 5 && !loading) {
        window.removeEventListener('scroll', handleScroll); 
        fetchPosts(true).then( () => {window.addEventListener('scroll', handleScroll);});
    }
}

window.addEventListener('scroll', handleScroll);

const imageInput = document.querySelector('.imageInput');
let imageUpload = false;
let imageUrl = null;

/**
 * Handling images
 */
imageInput.addEventListener('change', (event) => {
    const files = event.target.files;
    if (files && files[0]) {
        const imagePreviewContainer = document.getElementById('imagePreviewContainer');
        imagePreviewContainer.style.display = 'block';
        imagePreviewContainer.textContent = 'Image has been added successfully!';
        imageUrl = URL.createObjectURL(files[0]);
        postBtnDesk.disabled = false;
        postBtn.disabled = false;
    }
});

/**
 * Adding posts in mobile
 */
postBtn.addEventListener('click', async () => {
    let postContent = postInputDesk.value;
    const hashtagRegex = /(#\w+)/g;
    postContent = postContent.replace(hashtagRegex, '<span style="color: rgb(29, 155, 240);">$&</span>');

    const postData = {
        postContent: postContent,
        user: loggedInUser.userDetails.user_name,
        time: new Date(),
        image: loggedInUser.userDetails.profile_url,
        imageUrl: imageUrl
    };

    const url = 'http://localhost:3001/api/posts';

    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(postData)
        });
        if (response.ok) {
            console.log('Post successful');
            postInput.value = '';
        } else {
            console.error('Error posting content:', response.statusText);
        }
    } catch (error) {
        console.error('Error posting content:', error);
    } finally {
        await fetchPosts(false); 
    }
});

/**
 * Adding posts in desktop
 */
postBtnDesk.addEventListener('click', async () => {
    let postContent = postInputDesk.value;  
    const hashtagRegex = /(#\w+)/g;
    postContent = postContent.replace(hashtagRegex, '<span style="color: rgb(29, 155, 240);">$&</span>');

    const postData = {
        postContent: postContent,
        user: loggedInUser.userDetails.user_name,
        time: new Date(),
        image: loggedInUser.userDetails.profile_url,
        imageUrl: imageUrl
    };

    const url = 'http://localhost:3001/api/posts';

    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(postData)
        });
        if (response.ok) {
            console.log('Post successful');
            postInput.value = '';
        } else {
            console.error('Error posting content:', response.statusText);
        }
    } catch (error) {
        console.error('Error posting content:', error);
    } finally {
        await fetchPosts(false); 
    }
});


postInputDesk.addEventListener('input', function() {
    if (postInputDesk.value.trim() !== '') {
        postBtnDesk.disabled = false;
    } else {
        postBtnDesk.disabled = true;
    }
});

postInput.addEventListener('input', function() {
    if (postInput.value.trim() !== '') {
        postBtn.disabled = false;
    } else {
        postBtn.disabled = true;
    }
});

mainDiv.addEventListener('click', function(event) {
    const target = event.target;

    if (target.parentElement.classList.contains('like-post') || target.parentElement.classList.contains('unlike-post')) {
        handleLike(target.parentElement);
    } else if (target.parentElement.classList.contains('retweet')) {
        handleRetweet(target.parentElement);
    }
});

function handleLike(button) {
    let likeIcon = button.querySelector('.like-svg');
    let unlikeIcon = button.querySelector('.unlike-svg');
    let likeCountElement = button.querySelector('.likes-count');
    let likeCount = parseInt(likeCountElement.textContent);

    if(button.classList.contains("unlike-post")){
        likeIcon.style.display = "none";
        unlikeIcon.style.display = "inline-block";
        likeCount--;
        button.classList.add("like-post");
        button.classList.remove("unlike-post");
    } else {
        likeIcon.style.display = "inline-block";
        unlikeIcon.style.display = "none";
        likeCount++;
        button.classList.remove("like-post");
        button.classList.add("unlike-post");
    }

    likeCountElement.textContent = likeCount;

    let newLikeIcon = button.querySelector('.like-svg');
    let newUnlikeIcon = button.querySelector('.unlike-svg');

    newLikeIcon.removeEventListener('click', handleLike);
    newUnlikeIcon.removeEventListener('click', handleLike);

    newLikeIcon.addEventListener('click', function() {
        handleLike(button);
    });

    newUnlikeIcon.addEventListener('click', function() {
        handleLike(button);
    });
}


function handleRetweet(retweetButton) {
    const retweetIcon = retweetButton.querySelector('svg');
    const retweetCountElement = retweetButton.querySelector('.retweets-count');
    let retweetCount = parseInt(retweetCountElement.textContent);

    if (retweetButton.classList.contains("retweeted")) {
        retweetIcon.classList.remove('green-color');
        retweetCount--;
    } else {
        retweetIcon.classList.add('green-color');
        retweetCount++;
    }

    retweetCountElement.textContent = retweetCount;
    retweetButton.classList.toggle("retweeted");
}



