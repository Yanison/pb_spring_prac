// common.js
$.ajaxSetup({
    beforeSend: function(xhr) {
        xhr.setRequestHeader("Authorization", getAuthorizationTokenFromCookie());
    }
});


function getAuthorizationTokenFromCookie() {
    const cookies = document.cookie; // 모든 쿠키 값을 가져옵니다.
    const cookieArray = cookies.split(';'); // ';'로 쿠키를 분리합니다.

    for (let cookie of cookieArray) {
        const [key, value] = cookie.trim().split('='); // 쿠키를 key=value 형태로 나눕니다.
        if (key === 'Authorization') {
            return value; // Authorization 쿠키의 값을 반환합니다.
        }
    }
    return null; // Authorization 쿠키가 없으면 null 반환
}