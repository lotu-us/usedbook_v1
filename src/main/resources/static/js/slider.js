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



$(".previewslider .add-slide").on("click", function(){
    var thisID = $(this).attr('id').replace('add-slide-', '');

    previewSwipers.get("previewslider_"+thisID)
    .prependSlide(`
        <div class="swiper-slide card shadow-sm">
            <a href="#" class="small-slide">
                <img class="swiper-lazy" src="https://plchldr.co/i/150x150">
            </a>
        </div>
    `);

});


$(".previewslider .remove-slide").on("click", function(){
    var slideClicked = $(".previewslider .slide-click").parents()[1];
    slideClicked.remove();

//    var thisID = $(this).attr('id').replace('remove-slide-', '');
//    previewSwipers.get("previewslider_"+thisID)
//    .removeSlide($('#'+thisID+' .swiper-slide').length - 1);
});


$(window).on("load resize", function(){
    setTimeout(function(){
        var slidewidth = $(".previewslider .swiper-slide").css("width");
        $(".previewslider .swiper-slide").css("height", slidewidth);

        var previewwidth = $(".previewslider .previewimg").css("width");
        $(".previewslider .previewimg").css("height", previewwidth);
    }, 50);
});

$(".previewslider .small-slide img").on("click", function(){
    var imgsrc = $(this).attr("src");
    $(".previewslider .previewimg").attr("src", imgsrc);

    $(this).addClass("slide-click");
});