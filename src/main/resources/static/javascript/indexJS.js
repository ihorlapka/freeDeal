jQuery(function () {
    $('.avatar').animate({'width':'200px', 'height':'200px'}, 3000);


    $('.slider').slick({
        dots: true,
        infinite: true,
        speed: 300,
        slidesToShow: 1,
        centerMode: true,
        variableWidth: true
    });
});