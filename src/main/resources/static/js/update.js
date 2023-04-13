const update = () => {
    const inputPass = document.getElementById("pass").value;
    if (pass == inputPass) {
        document.updatePost.submit();
    } else {
        alert("비밀번호 불일치");
    }
}