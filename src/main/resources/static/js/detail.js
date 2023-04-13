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