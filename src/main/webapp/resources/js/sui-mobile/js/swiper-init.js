+(function ($) {

    'use strict';

    $.swiper = function (container, params) {
        return new $.Swiper(container, params);
    };

    $.fn.swiper = function (params) {
        return new $.Swiper(this, params);
    };

    $.initSwiper = function (pageContainer) {
        var page = $(pageContainer || document.body);
        var swipers = page.find('.swiper-container');
        if (swipers.length === 0) {
            return;
        }

        function destroySwiperOnRemove(slider) {
            function destroySwiper() {
                slider.destroy();
                page.off('pageBeforeRemove', destroySwiper);
            }

            page.on('pageBeforeRemove', destroySwiper);
        }

        for (var i = 0; i < swipers.length; i++) {
            var swiper = swipers.eq(i);
            if (swiper.data('swiper')) {
                swiper.data("swiper").update(true);
                continue;
            }
            var params = swiper.dataset();
            params.paginationClickable = true;
            params.pagination = '.page-current .swiper-pagination';
            destroySwiperOnRemove(swiper.swiper(params));
        }
    };
})(jQuery);
