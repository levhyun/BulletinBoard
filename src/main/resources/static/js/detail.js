const listReq = () => {
    console.log("목록 요청");
    location.href = '/bulletin-board/paging?page=' + page;
}

const updateReq = () => {
    console.log("수정 요청");
    location.href = '/bulletin-board/update/' + id;
}

const deleteReq = () => {
    console.log("삭제 요청");
    location.href = '/bulletin-board/delete/' + id;
}

const writeComment = () => {
    const writer = document.getElementById("writer").value;
    const content = document.getElementById("content").value;
    console.log("작성자  : ", writer);
    console.log("내용  : ", content);
    $.ajax({
        type: "post",
        url: "/comment/write",
        data: {
            "writer" : writer,
            "content" : content,
            "bulletinBoardId" : id
        },
        success: function(res) {
          console.log("요청성공", res);
        },
        error: function(err) {
            console.log("요청실패", err);
        }
    });
}