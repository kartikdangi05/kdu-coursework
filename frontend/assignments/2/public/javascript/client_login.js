/**
 * Verifying the user details and make it login
 */
function verify() {
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;

    fetch('http://localhost:3001/user/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: username,
            password: password
        })
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else if (response.status === 401) {
            throw new Error('Invalid username or password');
        } else {
            throw new Error('An error occurred');
        }
    })
    .then(data => {
        sessionStorage.setItem('loggedInUser', JSON.stringify(data));
        window.location.href = 'http://localhost:3001/';
    })
    .catch(error => {
        console.error('Error:', error.message);
        alert('Invalid username or password');
    });
}

