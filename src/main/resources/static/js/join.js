(function () {

    document.getElementById('form-register').addEventListener('submit', (event) => {
        event.preventDefault();
        fetch('/member/join', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                'username': document.getElementById('username').value,
                'password': document.getElementById('password').value
            })
        })
            .then((res) => {
                console.log('success')
            })
            .catch((err) => {
                console.error('error occured')
            })
    })
})();