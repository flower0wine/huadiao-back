$(function () {


    var allObject = {
        fansNumber: $('.fans-group ul li .fans-groupNumber'),
        followFansPerson: $('.followfans-person'),
        followFansPersonUl: $('.followfans-person').children('ul'),
        fansGroupLis: $('.fans-group ul li'),
        followGroupLis: $('.follow-group ul li'),
    }

    // 这两个变量用来存储关注和粉丝
    var follow;
    var fans;

    var browserServer = {

        // 是本人吗？
        isMe: '',

        formData: '',

        // 页面请求访问的 uid 对应的用户的关注和粉丝
        uid: '',

        // 主机地址
        localhost: window.localhost,

        // 图片地址
        localhost_user_avatar: window.localhost_user_avatar,

        // 获取被访问的用户 uid
        getViewedUid() {
            if(!browserServer.uid) {
                browserServer.uid = window.location.href.split('/')[4];
            }
            return browserServer.uid;
        },

        // 根据 uid 获取关注
        getFollowFans: function () {
            let params = {
                requestType: "followsFans",
                operation: "getFollowsFans",
                viewedUid: browserServer.getUrlUid()
            };
            browserServer.browserServerCore(params, {}, null, function (response) {
                let temp = response.data;
                console.log(response.data)
                if(typeof response.data === "object") {
                    // 保存数据
                    follow = temp.follows;
                    fans = temp.fans;
                    // 对数据进行处理
                    browserServer.searchFriend();
                    browserServer.isMe = temp.me;
                    // 计数
                    otherEvent.calcFollowNumber();
                    otherEvent.calcFansNumber();
                    // 添加点击事件
                    otherEvent.showFollow();
                    otherEvent.showFans();
                    let type = window.location.href.split('/')[6];
                    if (type == 'follow') {
                        // 显示全部关注
                        $(allObject.followGroupLis[0]).click();
                    } else if (type == 'fans') {
                        // 显示我的粉丝
                        $(allObject.fansGroupLis[0]).click();
                    }
                    // 为每一个用户设置 url
                    browserServer.setUrl();
                }
            });
        },


        browserServerCore: function (params, headers, data, fn) {
            axios({
                url: browserServer.localhost + 'dispatcherServlet',
                method: 'post',
                params,
                headers,
                data
            }).then(fn);
        },

        // 获取 url 中的 uid
        getUrlUid: function () {
            let url = window.location.href;
            return url.split('/')[4];
        },


        // 查找 follow 和 fans 中相同的用户，若找到，说明是互粉
        searchFriend: function () {
            follow.forEach(function (fol) {
                fans.forEach(function (fan) {
                    if(fol.uid === fan.uid){
                        fol.isFriend = true;
                        fan.isFriend = true;
                    }
                })
            })
        },


        // 为每一个用户设置 url ，为了查找用户，目前只能通过 url 查找
        setUrl: function () {
            follow.forEach(function (fol) {
                fol.url = browserServer.localhost + 'homepage/' + fol.uid;
            });
            fans.forEach(function (fan) {
                fan.url = browserServer.localhost + 'homepage/' + fan.uid;
            });
        }
    };

    browserServer.getFollowFans();

    var otherEvent = {

        getSessionId: window.getUniqueString,

        // 计算出关注和粉丝的数量
        calcFollowNumber: function () {
            // 全部关注个数
            if (follow.length > 9999) {
                $('.follow-group ul li:eq(0) .follow-groupNumber').text((follow.length / 10000).toFixed(1) + '万');
            } else {
                $('.follow-group ul li:eq(0) .follow-groupNumber').text(follow.length);
            }

        },

        calcFansNumber: function () {
            // 我的粉丝个数
            if (fans.length > 9999) {
                allObject.fansNumber.text((fans.length / 10000).toFixed(1) + '万');
            } else {
                allObject.fansNumber.text(fans.length);
            }
        },

        // 关注按钮，不一定是关注粉丝，可能是已关注取消关注后再关注
        followOther: function () {
            allObject.followFansPerson.on('click', '.followPerson', function () {
                let li = $(this).parents('li');
                let avatar;
                avatar = li.find('.img-avatar');
                if(typeof avatar[0] != 'undefined') {
                    avatar = avatar.css('background-image').split(/["\/]/);
                    avatar = avatar[7] + '/' + avatar[8];
                }
                let isFriend = false;
                // 已经关注的要取消关注，通过唯一的 url 获取用户 id 在关注数组中找到后删除（也可以不获取 id ，看性能）
                let url = li.find('.avater').children('a').prop('href');
                // 判断是否是粉丝，如果是就在粉丝数组中设置 isFriend 为 true
                fans.forEach(function (fan) {
                    if (url == fan.url) {
                        fan.isFriend = true;
                        isFriend = true;
                    }
                });

                // 向关注数组中添加
                if (isFriend) {
                    $(this).removeClass('followPerson').addClass('cancelFollowPerson').text('已互粉');
                } else {
                    $(this).removeClass('followPerson').addClass('cancelFollowPerson').text('已关注');
                }
                let followerUid = url.split('/')[5];
                // 数组开头添加
                follow.unshift({
                    uid: followerUid,
                    url: url,
                    nickname: li.find('.nickname').text(),
                    canvases: li.find('.canvases').text(),
                    user_avatar: avatar,
                    isFriend: isFriend,
                });

                otherEvent.calcFollowNumber();

                // 上传关注
                let params = {
                    requestType: "followsFans",
                    operation: "changeFollowStatus",
                    uid: followerUid,
                    followStatus: "confirm"
                }
                browserServer.browserServerCore(params, {}, null, function (response) {
                    console.log(response.data);
                });
            });

        },


        // 取消关注，该关注可能是粉丝，也可能不是
        cancelFollow: function () {
            allObject.followFansPerson.on('click', '.cancelFollowPerson', function () {

                $(this).removeClass('cancelFollowPerson').addClass('followPerson').text('关注');

                // 已经关注的要取消关注，通过唯一的 url 获取用户 id 在关注数组中找到后删除（也可以不获取 id ，看性能）
                let url = $(this).parents('li').find('.avater').children('a').prop('href');
                // 取消关注
                follow.forEach(function (fol, index, arr) {
                    if (fol.url == url) {
                        // 删除
                        arr.splice(index, 1);
                        return;
                    }
                });

                fans.forEach(function (fan) {
                    if (fan.url == url) {
                        fan.isFriend = false;
                        return;
                    }
                });

                otherEvent.calcFollowNumber();

                // 上传取消关注
                let params = {
                    requestType: "followsFans",
                    operation: "changeFollowStatus",
                    uid: url.split(/[\/]/)[5],
                    followStatus: "cancel"
                }
                browserServer.browserServerCore(params, {}, null, function (response) {
                    console.log(response.data);
                });
            });
        },


        // 显示已经关注的
        showFollow: function () {
            $(allObject.followGroupLis[0]).click(function () {
                // 按钮点击样式变化
                allObject.fansGroupLis.css('background-color', '');
                $(this).css('background-color', 'rgba(175, 175, 175, 0.315)').siblings('li').css('background-color', '');
                let ul = allObject.followFansPersonUl;
                ul.empty();
                for (let i = 0; i < follow.length; i++) {

                    let srcLi = follow[i];
                    let li;
                    // 如果没有上传头像使用默认
                    if (typeof srcLi.userAvatar == 'undefined') {
                        li = $(' <li><div class="avater"><a href="javascript:;" target="_blank" title=""><svg t="1658118096234" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3788" width="200" height="200"><path d="M510.77050482 2.21923328c281.4240859 0 509.56537287 228.14128697 509.56537173 509.56653682 0 281.42757888-228.14128697 509.56653682-509.56537173 509.56653681-281.42757888 0-509.56886585-228.14012302-509.56886585-509.56653681 0-281.4240859 228.14128697-509.56653682 509.56886585-509.56653682z" fill="#EEEEEE" p-id="3789"></path><path d="M510.77050482 516.51164387c90.0558939 0 163.05952199-73.30168832 163.05952199-163.72549973 0-90.42264633-73.00362809-163.72433465-163.05952199-163.72433465-90.05705785 0-163.06301497 73.30168832-163.06301497 163.72433465-0.00116395 90.42497536 73.00479317 163.72549859 163.06301497 163.72549973z m251.51800206 200.09807531l1.21552441-0.45756757c-39.85964146-100.90130205-137.98992099-172.25861689-252.73352647-172.25861689-113.31967317 0-210.42537358 69.60155875-251.2141221 168.51890176l0.56235463 0.22820181a39.9609344 39.9609344 0 0 0-2.9188881 15.01940622c0 22.15886393 17.97555086 40.12510094 40.15420757 40.12509981 16.79146325 0 31.16701469-10.30401138 37.16196921-24.92522837l0.65433486 0.26545948c28.69987328-68.84360192 96.50958109-117.22355485 175.60014393-117.22355484 79.76934741 0 148.05874461 49.21940878 176.32433493 119.00725475l0.2875813-0.10828003c6.4338944 13.58499499 20.27037696 22.98434901 36.30970538 22.98434901 22.17516373 0 40.15187968-17.96507307 40.15187968-40.12509981a40.14256469 40.14256469 0 0 0-1.55549923-11.05032533z" fill="#CCCCCC" p-id="3790"></path></svg></a></div><div class="followfans-information"><div class="nickname"></div><div class="canvases"></div></div><div class="followfans-tools"><div class="cancelFollowPerson">已关注</div><div class="moreOperation"><div class="iconfont moreicon">&#xe62d;</div><div class="followfans-toolsBar"><div class="sendMessage">发消息</div><div class="set-group">设置分组</div></div></div></div></li>');
                    } else {
                        li = $(' <li><div class="avater"><a href="javascript:;" target="_blank" title=""><div class="avatar-box"><div class="img-avatar"></div></div></a></div><div class="followfans-information"><div class="nickname"></div><div class="canvases"></div></div><div class="followfans-tools"><div class="cancelFollowPerson">已关注</div><div class="moreOperation"><div class="iconfont moreicon">&#xe62d;</div><div class="followfans-toolsBar"><div class="sendMessage">发消息</div><div class="set-group">设置分组</div></div></div></div></li>');
                        li.find('.img-avatar').css('background-image', 'url(' + browserServer.localhost_user_avatar + srcLi.userAvatar + ')');
                    }
                    li.find('a').prop('title', srcLi.nickname);
                    li.find('.nickname').text(srcLi.nickname);
                    li.find('.avater').children('a').prop('href', browserServer.localhost + 'homepage/' + srcLi.uid);
                    if (browserServer.isMe) {
                        if (srcLi.isFriend) {
                            li.find('.cancelFollowPerson').text('已互粉');
                        }
                    } else {
                        li.find('.followfans-tools').remove();
                    }
                    if (srcLi.canvases.length < 30) {
                        li.find('.canvases').text(srcLi.canvases);
                    } else {
                        li.find('.canvases').text(srcLi.canvases.substring(0, 30) + '...');
                    }
                    ul.append(li);
                }
            });
        },


        // 显示未关注的，粉丝
        showFans: function () {
            $(allObject.fansGroupLis[0]).click(function () {
                allObject.followGroupLis.css('background-color', '');
                $(this).css('background-color', 'rgba(175, 175, 175, 0.315)');
                let ul = allObject.followFansPersonUl;
                ul.empty();
                for (let i = 0; i < fans.length; i++) {
                    let srcLi = fans[i];
                    let li;
                    // 如果没有上传头像，使用默认
                    if (typeof srcLi.userAvatar == 'undefined') {
                        li = $('<li><div class="avater"><a href="javascript:;" target="_blank" title=""><svg t="1658118096234" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3788" width="200" height="200"><path d="M510.77050482 2.21923328c281.4240859 0 509.56537287 228.14128697 509.56537173 509.56653682 0 281.42757888-228.14128697 509.56653682-509.56537173 509.56653681-281.42757888 0-509.56886585-228.14012302-509.56886585-509.56653681 0-281.4240859 228.14128697-509.56653682 509.56886585-509.56653682z" fill="#EEEEEE" p-id="3789"></path><path d="M510.77050482 516.51164387c90.0558939 0 163.05952199-73.30168832 163.05952199-163.72549973 0-90.42264633-73.00362809-163.72433465-163.05952199-163.72433465-90.05705785 0-163.06301497 73.30168832-163.06301497 163.72433465-0.00116395 90.42497536 73.00479317 163.72549859 163.06301497 163.72549973z m251.51800206 200.09807531l1.21552441-0.45756757c-39.85964146-100.90130205-137.98992099-172.25861689-252.73352647-172.25861689-113.31967317 0-210.42537358 69.60155875-251.2141221 168.51890176l0.56235463 0.22820181a39.9609344 39.9609344 0 0 0-2.9188881 15.01940622c0 22.15886393 17.97555086 40.12510094 40.15420757 40.12509981 16.79146325 0 31.16701469-10.30401138 37.16196921-24.92522837l0.65433486 0.26545948c28.69987328-68.84360192 96.50958109-117.22355485 175.60014393-117.22355484 79.76934741 0 148.05874461 49.21940878 176.32433493 119.00725475l0.2875813-0.10828003c6.4338944 13.58499499 20.27037696 22.98434901 36.30970538 22.98434901 22.17516373 0 40.15187968-17.96507307 40.15187968-40.12509981a40.14256469 40.14256469 0 0 0-1.55549923-11.05032533z" fill="#CCCCCC" p-id="3790"></path></svg></a></div><div class="followfans-information"><div class="nickname"></div><div class="canvases"></div></div><div class="followfans-tools"><div class="followPerson">关注</div><div class="moreOperation"><div class="iconfont moreicon">&#xe62d;</div><div class="followfans-toolsBar"><div class="sendMessage">发消息</div><div class="removeFans">移除粉丝</div></div></div></div></li>');
                    } else {
                        li = $('<li><div class="avater"><a href="javascript:;" target="_blank" title=""><div class="avatar-box"><div class="img-avatar"></div></div></a></div><div class="followfans-information"><div class="nickname"></div><div class="canvases"></div></div><div class="followfans-tools"><div class="followPerson">关注</div><div class="moreOperation"><div class="iconfont moreicon">&#xe62d;</div><div class="followfans-toolsBar"><div class="sendMessage">发消息</div><div class="removeFans">移除粉丝</div></div></div></div></li>');
                        li.find('.img-avatar').css('background-image', 'url(' + browserServer.localhost_user_avatar + srcLi.userAvatar + ')');
                    }
                    li.find('a').prop('title', srcLi.nickname);
                    li.find('.nickname').text(srcLi.nickname);
                    li.find('.avater').children('a').prop('href', browserServer.localhost + 'homepage/' + srcLi.uid);
                    if (browserServer.isMe) {
                        if (srcLi.isFriend) {
                            li.find('.followPerson').removeClass('followPerson').addClass('cancelFollowPerson').text('已互粉');
                        }
                    } else {
                        li.find('.followfans-tools').remove();
                    }
                    if (srcLi.canvases.length < 30) {
                        li.find('.canvases').text(srcLi.canvases);
                    } else {
                        li.find('.canvases').text(srcLi.canvases.substring(0, 30) + '...');
                    }
                    ul.append(li);
                }
            });
        },


        // 新建关注组别
        createGroup: function () {
            $('.follow-header .createNewGroup').click(function () {
                $('.createBackground').show();
                $('.confirmCreate').off('click').click(function () {
                    let createGroup = $('#createGroup').val()
                    if (createGroup.length === 0) {
                        $('.groupNameWrongTip').show().stop().animate({
                            opacity: 1
                        }, 700, function () {
                            setTimeout(function () {
                                $('.groupNameWrongTip').text('组名不能为空').stop().animate({
                                    opacity: .3
                                }, 700, function () {
                                    $(this).hide();
                                });
                            }, 700);
                        });
                    } else if (createGroup.length <= 10) {
                        let li = $('<li><div class="follow-groupName"></div><div class="follow-groupNumber">0</div><div class="deleteGroup iconfont">&#xec7b;</div></li>');
                        li.find('.follow-groupName').text(createGroup);
                        $('.follow-group ul').append(li);
                        $('.createBackground').hide();
                        allObject.followGroupLis = $('.follow-group ul li');

                    } else {
                        // 正常情况下是到不了该判断语句
                        $('.groupNameWrongTip').show().stop().animate({
                            opacity: 1
                        }, 700, function () {
                            setTimeout(function () {
                                $('.groupNameWrongTip').text('组名过长').stop().animate({
                                    opacity: .3
                                }, 700, function () {
                                    $(this).hide();
                                });
                            }, 700);
                        });
                    }
                });
                $('.cancelCreate').off('click').click(function () {
                    $('.createBackground').hide();
                })
            });
        },


        // 删除关注组别
        deleteGroup: function () {
            $('.follow-group ul').on('click', '.deleteGroup', function () {
                var li = $(this).parents('li');
                // 借用一下删除板块
                $('.removeFansBackground').show();
                $('.confirmDelete').off('click').click(function () {
                    li.remove();
                    $('.removeFansBackground').hide();
                    allObject.followGroupLis = $('.follow-group ul li');
                });
                $('.cancelDelete').off('click').click(function () {
                    $('.removeFansBackground').hide();
                });

            });
        }

    };


    otherEvent.followOther();
    otherEvent.cancelFollow();
    otherEvent.createGroup();
    otherEvent.deleteGroup();


    // 左边滑块，关注页面
    $('.huadiao-follow').click(function () {
        $($('.follow-group ul li')[0]).click();
    });
    // 左边滑块，粉丝页面
    $('.huadiao-fans').click(function () {
        $($('.fans-group ul li')[0]).click();
    })


    // 更多操作出现效果
    allObject.followFansPersonUl.on('mouseenter', '.moreOperation', function () {
        $(this).children('.followfans-toolsBar').show().stop().animate({
            opacity: 1,
            top: '3.125rem'
        }, 300);
    });
    allObject.followFansPersonUl.on('mouseleave', '.moreOperation', function () {
        $(this).children('.followfans-toolsBar').stop().animate({
            opacity: .5,
            top: '2.5rem'
        }, 300, function () {
            $(this).hide();
        });
    });


    // 移出粉丝
    allObject.followFansPersonUl.off('click').on('click', '.removeFans', function () {
        var li = $(this).parents('li');
        $('.removeFansBackground').show();
        // 确认吗
        $('.confirmDelete').off('click').click(function () {

            var url = li.find('.avater').children('a').prop('href');
            // 判断是否是互粉，互粉则将 followMe 设为 false
            for (var i = 0; i < follow.length; i++) {
                var srcUrl = follow[i].url;
                if (srcUrl == url) {
                    follow[i].followMe = false;
                    break;
                }
            }
            // 同时将 fans 数组中的 follow 设为 false
            for (var i = 0; i < fans.length; i++) {
                var srcUrl = fans[i].url;
                if (srcUrl == url) {
                    fans[i].followMe = false;
                    break;
                }

            }
            li.remove();

            // 粉丝数量
            var fansNumber = 0;
            for (var i = 0; i < fans.length; i++) {
                if (fans[i].followMe) {
                    fansNumber++;
                }
            }
            if (fansNumber > 9999) {
                $('.fans-group ul li:eq(0) .fans-groupNumber').text((fansNumber / 10000).toFixed(1) + '万');
            } else {
                $('.fans-group ul li:eq(0) .fans-groupNumber').text(fansNumber);
            }

            $('.removeFansBackground').hide();
        });
        // 取消
        $('.cancelDelete').off('click').click(function () {
            $('.removeFansBackground').hide();
        });
    });


    // 使用滚动条

    scrollBar($('.follow-group'));
    scrollBar(allObject.followFansPerson);

    // 使用滚动条插件
    function scrollBar(obj) {
        var o = $(obj);
        o.mCustomScrollbar({
            scrollButtons: {
                enable: true
            },
        });
    }
})