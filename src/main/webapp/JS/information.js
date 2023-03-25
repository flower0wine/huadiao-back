$(function () {


    var date = new Date();
    var NowDate = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
    jeDate("#date", {
        format: "YYYY-MM-DD",
        minDate: '1900-01-01',
        maxDate: NowDate
    });

    // 这里包含着重复使用 jquery 对象
    var allObject = {
        nickname: $('#nickname'),
        canvases: $('#canvases'),
        canvasesTip: $('.canvasesTip'),
        man: $('.man'),
        women: $('.women'),
        noknown: $('.noknown'),
        date: $('#date'),
        school: $('#school'),
        saveSucceedBackground: $('.save-succeed-background')
    }

    // 存储用户信息
    var user;

    var browserServer = {

        // 主机地址
        localhost: window.localhost,

        // 获取用户信息
        getUserInfer: function () {
            let data = {
                requestType: "userInfer",
                operation: "getUserInfer"
            };
            browserServer.browserServerCore(data, function (response) {
                // 服务端发送过来的是 utf-8 编码的，不用解码
                user = response.data;
                console.log(user)
                otherEvent.initialUserInfer();
            });
        },

        // 异步提交表单
        submit: function () {
            $('.save').click(function (e) {
                if (!otherEvent.checkNickname()) {
                    $('.nicknameTip').show();
                } else {
                    // 要改变的日期
                    var update = user.bornDate;
                    // 如果要修改的日期正确
                    if (otherEvent.checkDate()) {
                        update = allObject.date.val();
                    }

                    let data = {
                        requestType: "userInfer",
                        operation: "updateUserInfer",
                        nickname: allObject.nickname.val(),
                        canvases: allObject.canvases.val(),
                        sex: otherEvent.sex,
                        bornDate: update,
                        school: allObject.school.val()
                    };
                    browserServer.browserServerCore(data, function (response) {
                        var temp = decodeURI(response.data);
                        console.log(temp)
                        if (temp === 'userInferUpdateSucceed') {
                            otherEvent.saveSucceed();
                        }
                    });
                }
            });
        },

        browserServerCore(data, fn) {
            axios({
                url: browserServer.localhost + "dispatcherServlet",
                method: "post",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded; charset=utf-8"
                },
                data

            }).then(fn);
        }
    }

    browserServer.getUserInfer();
    browserServer.submit();

    let canvasesVal = allObject.canvases.val();
    var otherEvent = {

        sex: 0,

        // 初始化信息
        initialUserInfer: function (){
            if (user.sex === '0') {
                $('.unknown').click();
            } else if (user.sex === '1') {
                $('.man-box').click();
            } else if (user.sex === '2') {
                $('.women-box').click();
            }
            allObject.nickname.val(user.nickname);
            allObject.canvases.val(user.canvases);
            allObject.canvasesTip.text(user.canvases.length + '/50');
            allObject.date.val(user.bornDate);
            allObject.school.val(user.school);
            $('.information-username').text(user.userId);
        },

        // 失去鼠标焦点进行判断
        nicknameEvent: function () {
            allObject.nickname.blur(function () {
                var nicknameTip = $('.nicknameTip');
                if (!otherEvent.checkNickname()) {
                    nicknameTip.show();
                } else {
                    nicknameTip.hide();
                }
            });
        },

        // 输出个人介绍长度
        canvasesEvent: function () {

            allObject.canvases.keydown(function (e) {
                otherEvent.checkWord(e);
            });
            allObject.canvases[0].addEventListener('compositionstart', function (e) {
                $(this).off('keydown');
                canvasesVal = allObject.canvases.val();
                allObject.canvasesTip.text(e.data.length + canvasesVal.length + '/50');
            });
            allObject.canvases[0].addEventListener('compositionend', function () {
                canvasesVal = allObject.canvases.val();
                allObject.canvasesTip.text(canvasesVal.length + '/50');
                $(this).off('keydown').keydown(function (e) {
                    otherEvent.checkWord(e);
                });
            })
        },

        // 字数检查
        checkWord: function (e) {
            canvasesVal = allObject.canvases.val();
            if (e.keyCode === 8) {
                $(this).off('keyup').keyup(function () {
                    if (e.keyCode === 8) {
                        canvasesVal = allObject.canvases.val();
                        allObject.canvasesTip.text(canvasesVal.length + '/50');
                    }
                });
            } else if (e.keyCode !== 16 && canvasesVal.length < 50) {
                allObject.canvasesTip.text(canvasesVal.length + 1 + '/50');
            }
        },

        // 检查昵称
        checkNickname: function () {
            var nicknameLength = allObject.nickname.val().length;
            return !(nicknameLength < 1 || nicknameLength > 20);
        },

        // 检查日期
        checkDate: function () {
            // 获取填写的日期
            var date_ = allObject.date.val();
            var reg = /^(19[0-9]{2})|(20[0-9]{2})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[0-1])$/;
            // 用正则表达式判定一定的日期格式
            if (reg.test(date_)) {
                var dateSplit = date_.split('-');
                var year = parseInt(dateSplit[0]);
                var month = parseInt(dateSplit[1]);
                var day = parseInt(dateSplit[2]);
                // 如果年份大于现在的年份
                var fullYear = date.getFullYear();
                if (year > fullYear)
                    return false;
                // 判断除2月份天数的正确性
                switch (month) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        return day <= 31;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        return day <= 30;
                }
                // 如果是闰年
                if ((year % 400 === 0) || (year % 100 !== 0 && year % 4 === 0)) {
                    if (month === 2)
                        return day <= 29;
                }
                // 否则就是平年
                if (month === 2)
                    return day <= 28;
            }
            // 正则表达式不通过
            return false;
        },

        // 改变按钮样式
        changeCSS: function (obj, sexObj) {
            var o = $(obj);
            var s = $(sexObj);
            o.css('background-color', '#2cb7df').css('color', '#fff').siblings().css({
                'background-color': '#d8d8d8',
                'color': '#545454'
            });
            s.prop('checked', 'true').siblings('input').prop('checked', 'false');
        },

        // 性别框点击
        clickSexBox: function () {
            $('.man-box').click(function () {
                otherEvent.changeCSS(this, $('#sex-man'));
                otherEvent.sex = 1;
                allObject.man.show();
                allObject.man.stop().animate({
                    top: '-1.8125rem'

                }, 300);

                allObject.women.stop().animate({
                    top: '-1.125rem'
                }, 300, function () {
                    $(this).hide();
                })
                allObject.noknown.stop().animate({
                    top: '-1rem'
                }, 300, function () {
                    $(this).hide();
                })


            });
            $('.women-box').click(function () {
                otherEvent.changeCSS(this, $('#sex-women'));
                otherEvent.sex = 2;
                allObject.women.show();
                allObject.women.stop().animate({
                    top: '-2.125rem'

                }, 300);

                allObject.man.stop().animate({
                    top: '-0.8125rem'
                }, 300, function () {
                    $(this).hide();
                })
                allObject.noknown.stop().animate({
                    top: '-1rem'
                }, 300, function () {
                    $(this).hide();
                })
            });
            $('.unknown').click(function () {
                otherEvent.changeCSS(this, $('#sex-unknown'));
                otherEvent.sex = 0;
                allObject.noknown.show();
                allObject.noknown.stop().animate({
                    top: '-2rem'

                }, 300);

                allObject.women.stop().animate({
                    top: '-1.125rem'
                }, 300, function () {
                    $(this).hide();
                })
                allObject.man.stop().animate({
                    top: '-0.8125rem'
                }, 300, function () {
                    $(this).hide();
                })
            });
        },

        // 修改成功提示
        saveSucceed: function (){
            allObject.saveSucceedBackground.show().stop().animate({
                opacity: 1
            }, 400, function (){
                setTimeout(function (){
                    allObject.saveSucceedBackground.stop().animate({
                        opacity: .3
                    }, 400, function (){
                        $(this).hide();
                    });
                }, 1000)
            })
        }

    };


    otherEvent.nicknameEvent();
    otherEvent.canvasesEvent();
    otherEvent.clickSexBox();


})