var inputs = $("#bookName, #bookCategory, #bookPrice, #bookDescription");
var imgCountMin = 1;
var imgCountMax = 10;
var imgCountMaxMessage = "이미지는 10개까지만 업로드 가능합니다.";

inputs.on("click", function(){
    if($(this).hasClass("is-invalid")){
        $(this).removeClass("is-invalid");
        var thisid = $(this).attr("id");
        $("#"+thisid+"Help").removeClass("ani");
    }
});

$(".previewslider .add-slideimg").on("click", function(){
    //console.log("add-slideimg click");
    if(uploadFileMap.size >= imgCountMax){
        alert(imgCountMaxMessage);
        return;
    }
    $("#fileUploadBtn").click();
});


var uploadFileMap = new Map();

$("#fileUploadBtn").on("change", function(event){
    //console.log("fileUploadBtn onChange");
    //파일 선택 후 실행됨

    var files = event.target.files; // = $("#fileUploadBtn")[0].files
    /* FileList반환
       [0]: File / length: 1
       name: "캡처.JPG"(파일명), size: 107939(파일사이즈), type: "image/jpeg" (파일형식)
       업로드 후 files는 초기화된다
     */

    var filesArr = Array.prototype.slice.call(files);
    // File로 분리

    var blobsrc = "";
    var count = 0;
    filesArr.forEach(function(file){
        count++;
        if(count > imgCountMax){
        }

        if(count <= imgCountMax){
            if(!file.type.match("image.*")){
                alert("이미지 확장자만 가능합니다");
                return;
            }

            blobsrc = URL.createObjectURL(file);
            //Blob 반환. Binary Large Object
            //blob객체의 url주소값으로 이미지를 불러올 수 있게된다.
            //이렇게 생성된 주소는 브라우저의 메모리에 올라가있다.

            uploadFileMap.set(blobsrc, file);
            addSlideDesign(blobsrc);
        }
    });

    if(count > imgCountMax){
        alert(imgCountMaxMessage+"\n10개 이후의 이미지는 삭제되었습니다.");
    }

    blobsrc.onload = function(){
        URL.rejectObjectUrl(blobsrc);
        //이미지 로딩 후 URL 메모리에서 해제
    }
//    console.log(uploadFileMap);
//    uploadFileMap.forEach(function(v, k){
//        console.log(k, v);
//    });
})


function fileDelete(blobFileSrc){
    uploadFileMap.delete(blobFileSrc);
//    console.log(uploadFileMap);
//    uploadFileMap.forEach(function(v, k){
//        console.log(k, v);
//    });
}


var validCheck = 0;
$("#editorFormSubmit").click(function(event){
    if(uploadFileMap.size < imgCountMin){
        alert("이미지는 최소 1개 이상 업로드해야합니다.");
        return false;
    }

    var form = $("#editorForm")[0];
    var formData = new FormData(form);
    uploadFileMap.forEach(function(v, k){
        formData.append("fileList", v);
    });
//    console.log(uploadFileMap);
//    console.log($("#fileUploadBtn")[0].files);
//    for (var pair of formData.entries()) {
//        console.log(pair[0]+ ', ' + pair[1]);
//    }

    $.ajax({
        url: "/newBookPost",
        type: "POST",
        data: formData,    //formData는 반드시 ajax랑 사용@!!
        contentType: false,
        processData: false,
        success : function(result){
            if(result.status == "validPhoto"){
                alert(imgCountMaxMessage);
            }

            if(result.status == "valid"){
                result.response.forEach(e => {
                    if(e.status == false){
                        $("#"+e.field).addClass("is-invalid");
                        $("#"+e.field+"Help").addClass("ani");
                        $("#"+e.field+"Help").html(""+e.message);
                    }
                    setTimeout(function(){
                        $("#"+e.field+"Help").removeClass("ani");
                    }, 2000);
                });
            }

            if(result.status == "saveOk"){
                alert(result.response);
                //window.location.replace(result.response);
            }

        },
        error: function(error){
            alert(error);
        }
    });
});


