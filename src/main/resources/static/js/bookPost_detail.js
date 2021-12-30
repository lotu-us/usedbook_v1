function commentSubmit(){
    var bookPostId = $("#bookPostId").val();
    var content = $.trim($(".commentWrite textarea").val());
    if(content == ""){
        alert("댓글내용을 입력해주세요");
        return
    }

    $.ajax({
        type:"POST",
        url:"/comment/write",
        contentType: 'application/json',
        data: JSON.stringify({
            "bookPostId":bookPostId,
            "content":content
        }),
        success: function(data){
            if(data != null){
                var createTime = data.createTime.replace(".0", "").substring(2, 19);
                $("#commentList").append(`
                    <div class="row commentWrapper">
                        <div class="col">
                            <p class="mb-2">
                                <i class="fas fa-user"></i> <span>${data.writer}</span>
                                <span class="ms-3" style="font-size:0.7em;">${createTime}</span>
                            </p>
                            <p>${data.content}</p>
                            <p style="font-size:0.8em;">답글 <span>개수</span></p>
                        </div>
                        <div class="col-2">
                            <span class="commentLink">수정</span>
                            <span class="commentLink" id="comment${data.id}" onclick="deleteComment(event)">삭제</span>
                        </div>
                    </div>
                `);
            }
        }
    });
}

function deleteComment(event){
    var commentId = event.target.id;

    var result = confirm("삭제하시겠습니까?");
    if(result){    //yes
        $.ajax({
            type:"POST",
            url:"/comment/delete",
            contentType: 'application/json',
            data: JSON.stringify({
                "commentId":commentId
            }),
            success: function(data){
                if(data == 1){
                    var element = event.target;
                    var removeTarget = $(element).parents()[1];
                    removeTarget.remove();
                }else{
                    alert("삭제되지않았습니다.");
                }
            }
        });
    }else{          //no
        return
    }
}