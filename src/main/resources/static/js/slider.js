 var Swipers = new Map();
 $('.slider .swiper').each(function(i) {
   var thisID = $(this).attr('id');

   Swipers.set("slider_"+thisID, new Swiper('#'+thisID, {
        loop: true,
        navigation: {
            nextEl: '#next_'+thisID,
            prevEl: '#prev_'+thisID,
        },
        lazy : {
            loadPrevNext : true // 이전, 다음 이미지는 미리 로딩
        },
        breakpoints: {
            320: {  slidesPerView: 2,  spaceBetween: 10,  },
            500: {  slidesPerView: 3,  spaceBetween: 10,  },
            768: {  slidesPerView: 4,  spaceBetween: 10,  },
            1024: { slidesPerView: 5,  spaceBetween: 10,  },
        },

    }
   ));
 });





 var previewSwipers = new Map();
 $('.previewslider .swiper').each(function(i) {
   var thisID = $(this).attr('id');

   previewSwipers.set("previewslider_"+thisID, new Swiper('#'+thisID, {
        loop: false,
        navigation: {
            nextEl: '#next_'+thisID,
            prevEl: '#prev_'+thisID,
        },
        lazy : {
            loadPrevNext : true // 이전, 다음 이미지는 미리 로딩
        },
        breakpoints: {
            320: {  slidesPerView: 3,  spaceBetween: 10, slidesPerGroup: 3, },
            500: {  slidesPerView: 4,  spaceBetween: 10, slidesPerGroup: 4, },

        },
    }
    ));
 });




$(window).on("load resize", function(){
    heightResize();
});

function heightResize(){
    setTimeout(function(){
        var slidewidth = $(".previewslider .swiper-slide").css("width");
        $(".previewslider .swiper-slide").css("height", slidewidth);

        var previewwidth = $(".previewslider .previewimg").css("width");
        $(".previewslider .previewimg").css("height", previewwidth);
    }, 50);
}






$(".previewslider .add-slideimg").on("click", function(){
    //console.log("add-slideimg click");

    $("#fileUploadBtn").click();
});

function addSlideDesign(blobsrc){
    //console.log("addSlideDesign")
    //console.log(blobsrc);
    //blob:http://localhost:8080/20e84207-1992-41ab-9b88-7949bb94f05f

    var thisID = $(".previewslider .add-slideimg").attr('id').replace('add-slideimg-', '');

    previewSwipers.get("previewslider_"+thisID)
    .prependSlide(`
        <div class="swiper-slide card shadow-sm">
            <img class="small-slideimg" src="${blobsrc}">
        </div>
    `);
    //<img class="small-slideimg" src="/img/testimg/${testcount}.jpg">

    previewImgShow($(".previewslider .swiper-slide img").first());
    heightResize();
}


$(".previewslider .remove-slideimg").on("click", function(){
    var slideClicked = $(".previewslider .slide-click");

    fileDelete($(slideClicked).attr("src"));

    slideClicked.parents()[0].remove();
//    var thisID = $(this).attr('id').replace('remove-slide-', '');
//    previewSwipers.get("previewslider_"+thisID)
//    .removeSlide($('#'+thisID+' .swiper-slide').length - 1);
});





//$(".previewslider .small-slideimg").on("click", function(){
//    console.log($(this).attr("class"));
//    previewImgShow($(this));
//});
//동적으로 추가된 요소의 이벤트를 감지하려면 document로 작성해야함

$(document).on("click", ".previewslider .small-slideimg" , function() {
     previewImgShow($(this));
});

function previewImgShow(thisElement){
    var imgsrc = thisElement.attr("src");
    $(".previewslider .previewimg").attr("src", imgsrc);

    $(".previewslider .swiper-slide img").each(function(index, imgElement){
        $(imgElement).removeClass("slide-click");
    });

    thisElement.addClass("slide-click");
}








function ajaxImageUpload(){
//    var bookPost = {};
//    bookPost.imgFilePath = "";
//    bookPost.imgFileName = "";
//
//    $.ajax({
//        type:"POST",
//        url:"/newPost/imageUpload",
//        contentType: 'application/json',
//        data: JSON.stringify(bookPost),
//        success: function(data) {
//
//        },
//        error: function(){
//
//        }
//    });
}


