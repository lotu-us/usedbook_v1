const swiperA = new Swiper('.swiperA', {
    // Optional parameters
    loop: true, //무한반복 O

    // Navigation arrows
    navigation: {
      nextEl: '.swiper-button-next-custom',
      prevEl: '.swiper-button-prev-custom',
    },

    // ★동적로딩 설정
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