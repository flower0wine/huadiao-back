(function (window) {
    window.localhost = 'http://localhost:9090/huadiao/';
    window.localhost_user_avatar = '/images/userAvatar/';
    window.localhost_homepage_background = '/images/homepageBackground/';
    window.localhost_huadiao_house = '/images/huadiaoHouseConfig/';
    window.localhost_huadiao_fanju = '/images/huadiaoHouseFanju/';

    window.slide = function (obj, setting, fn) {
        let o = $(obj);
        o.children('i').click(function () {
            let s = $(this).find('.rectangle').siblings('.circle').css('margin-left').split('p')[0];
            s = parseInt(s).toFixed(0);
            if (s == '2') {
                $(this).find('.rectangle').css('background-color', '#1ea5ea').siblings('.circle').stop().animate({
                    marginLeft: '1rem'
                }, 250);
            } else {
                $(this).find('.rectangle').css('background-color', '#d9d6d6').siblings('.circle').stop().animate({
                    marginLeft: '.125rem'
                }, 250);
            }
            fn(setting);
        });
    };

    window.setSliderCSS = function (this_, status) {
        if (status === '1') {
            this_.find('.rectangle').css('background-color', '#1ea5ea').siblings('.circle').css('margin-left', '1rem');
        } else {
            this_.find('.rectangle').css('background-color', '#d9d6d6').siblings('.circle').css('margin-left', '.125rem');
        }
    };

    window.getUniqueString = function (length) {
        let charArray = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, '-', '_', '&', '%', '#', '!', '?', '$'];
        let str = '';
        for (let i = 0; i < length; i++) {
            let random = Math.floor(Math.random() * charArray.length);
            str += charArray[random];
        }
        return str;
    };

    window.getCurrentTime = function () {
        let currentTime = new Date();
        return currentTime.getFullYear() + '-' + addZero(currentTime.getMonth() + 1) + '-' + addZero(currentTime.getDate()) + ' ' + addZero(currentTime.getHours())
            + ':' + addZero(currentTime.getMinutes()) + ':' + addZero(currentTime.getSeconds());
    };

    function addZero(factor) {
        return factor < 10 ? '0' + factor : factor;
    }
})(window)