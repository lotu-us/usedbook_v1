var inputs = $("#bookName, #bookCategory, #bookPrice, #bookDescription");

inputs.on("click", function(){
    if($(this).hasClass("is-invalid")){
        $(this).removeClass("is-invalid");
    }
});



var uploadFileMap = new Map();

$("#fileUploadBtn").on("change", function(event){

    if(uploadFileMap.size >= 10){
        alert("이미지는 10개까지만 업로드 가능합니다.");
        return;
    }

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
    filesArr.forEach(function(file){
        if(!file.type.match("image.*")){
            alert("이미지 확장자만 가능합니다");
            return;
        }

        blobsrc = URL.createObjectURL(file);
        //Blob 반환. Binary Large Object
        //blob객체의 url주소값으로 이미지를 불러올 수 있게된다.
        //이렇게 생성된 주소는 브라우저의 메모리에 올라가있다.

        uploadFileMap.set(blobsrc, file);
    });


    addSlideDesign(blobsrc);

    blobsrc.onload = function(){
        URL.rejectObjectUrl(blobsrc);
        //이미지 로딩 후 URL 메모리에서 해제
    }

//    console.log("uploadFileMap: ");
//    uploadFileMap.forEach(function(v, k){
//        console.log(k, v);
//    });

//    $("#fileUploadBtn").val("");
//    //초기화
})



function fileDelete(blobFileSrc){
    uploadFileMap.delete(blobFileSrc);

//    console.log("deleteFileMap: ");
//    uploadFileMap.forEach(function(v, k){
//        console.log(k, v);
//    });
}




$("#editorFormSubmit").click(function(event){

    var imgCount =  $(".previewslider .swiper-slide img").length;

    if(imgCount < 2){
        alert("이미지는 최소 1개 이상 업로드해야합니다.");
        return false;
    }

    //$("#fileUploadBtn").attr("multiple", "multiple");
    var form = $("#editorForm")[0];
    var formData = new FormData(form);

//    console.log(uploadFileMap);
//    console.log($("#fileUploadBtn")[0].files);

    uploadFileMap.forEach(function(v, k){
        //console.log(k, v);
        formData.append("fileList", v);
    });


    $.ajax({
        url: "/newBookPost/fileupload",
        type: "POST",
        data: formData,    //formData는 반드시 ajax랑 사용@!!
        contentType: false,
        processData: false,
        success : function(result){
            console.log(result);
            $("#editorForm").submit();
        },
        error: function(){
        }
    });
});







