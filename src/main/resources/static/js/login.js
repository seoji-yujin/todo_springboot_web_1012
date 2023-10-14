// (function () {
//
//     document.getElementById('form-login').addEventListener('submit', (event) => {
//         fetch('/member/login', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify({
//                 'username': document.getElementById('username').value,
//                 'password': document.getElementById('password').value
//             })
//         })
//             .then((res) => {
//                 res.json()
//             })
//             .then((data) => {
//                 console.log(data)
//             })
//             .catch((err) => {
//                 console.error('error occured')
//             })
//
//     })
// })();

(function () {
    document.getElementById('form-login').addEventListener('submit', (event) => {
        event.preventDefault(); // 이벤트 기본 동작을 막음

        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        fetch('/member/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                'username': username,
                'password': password
            })
        })
            .then((res) => res.json()) // 응답을 JSON 형식으로 파싱
            .then((data) => {
                console.log(data);
                if (data.status === 200) {
                    // 로그인 성공 시 홈페이지로 리다이렉트
                    window.location.href = '/';
                } else {
                    // 로그인 실패 시 메시지를 처리
                    alert('로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다.');
                }
            })
            .catch((err) => {
                console.error('에러 발생:', err);
                // 에러 발생 시 사용자에게 에러 메시지를 표시하거나 다른 작업 수행
            });
    });
})();
