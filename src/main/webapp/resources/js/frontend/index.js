$(function () {
    var url = '/O2O/frontend/listmainpageinfo';
    $.getJSON(url,function (data) {
        if(data.success){
            var headLineList = data.headLineList;
            var swiperHtml='';
            headLineList.map(function (item,index) {
                swiperHtml+='<a class="swiper-slide img-wrap">'
                +'< href="'+item.lineLink
                +'" external><img class="banner-img" src="'+item.lineImg
                +'" alt="'+item.lineName+'"></a>'+'</div>';
            });
            $(".swiper-wrapper").html(swiperHtml);
            $(".swiper-container").swiper({
                autoplay:3000,
                autoplayDisableOnInteraction: false
            });
        }

    })

})