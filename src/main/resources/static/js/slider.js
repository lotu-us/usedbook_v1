 var Swipers = [];
 $('.swiper').each(function(i) {
   var thisID = $(this).attr('id');

   Swipers[i] = new Swiper('#'+thisID, {
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
       }
   });
 });





  const swiperB = new Swiper('.swiperB', {
      loop: true, //무한반복 O

      navigation: {
        nextEl: '.next_swiperB',
        prevEl: '.prev_swiperB',
      },

  	lazy : {
  		loadPrevNext : true // 이전, 다음 이미지는 미리 로딩
  	},

      breakpoints: {
          320: {
            slidesPerView: 2,
            spaceBetween: 10,
          },
          500: {
            slidesPerView: 3,
            spaceBetween: 10,
          },
          768: {
            slidesPerView: 4,
            spaceBetween: 10,
          },
          1024: {
              slidesPerView: 5,
              spaceBetween: 10,
            },
      }

    });

