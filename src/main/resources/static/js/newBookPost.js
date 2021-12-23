var inputs = $("#bookName, #bookCategory, #bookPrice, #bookDescription");

inputs.on("click", function(){
    if($(this).hasClass("is-invalid")){
        $(this).removeClass("is-invalid");
    }
});




$("#fileUploadBtn").on("change", function(event){

    console.log("fileUploadBtn onChange");
    //파일 선택 후 실행됨

    var files = event.target.files; // = $("#fileUploadBtn")[0].files
    /* FileList반환
       [0]: File / length: 1
       name: "캡처.JPG"(파일명), size: 107939(파일사이즈), type: "image/jpeg" (파일형식)
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
    });

    addSlideDesign(blobsrc);

    blobsrc.onload = function(){
        URL.rejectObjectUrl(blobsrc);
        //이미지 로딩 후 URL 메모리에서 해제
    }
})




