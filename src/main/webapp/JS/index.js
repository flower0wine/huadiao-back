$(function () {

    // 常使用到的 jquery 对象
    var allObject = {
        menu: $('.menu'),
        menuBar: $('.menuBar'),
        rightEntry: $('.right-entry'),
        indexHeadUserAvater: $('.index-headUserAvatar'),
        Fanjus: $('.Fanjus'),
        register: $('.register'),
        login: $('.login'),
        LoginRegisterForeground: $('.LoginRegisterForeground'),
        searchBox: $('.search-box'),
        search: $('#search'),
        LoginRegister: $('.LoginRegister'),
        huadiaoJumpLoginA: $('.huadiao-jumpLogin a'),
        huadiaoJumpRegisterA: $('.huadiao-jumpRegister a'),
        registerusername: $('#registerusername'),
        registerSameUserName: $('.registerSameUserName'),
        registerWrongUserName: $('.registerWrongUserName'),
        registerUsernameTipIcon: $('.registerUsernameTip .warning'),
        registerpassword: $('#registerpassword'),
        registerWrongLengthPassword: $('.registerWrongLengthPassword'),
        registerWrongPassword: $('.registerWrongPassword'),
        registerPasswordTipIcon: $('.registerPasswordTip .warning'),
        checkCode: $('#checkCode'),
        checkCodeTipIcon: $('.checkCodeTip .warning'),
        wrongCheckCode: $('.wrongCheckCode'),
        loginusername: $('#loginusername'),
        loginUsernameTipIcon: $('.loginUsernameTip .warning'),
        loginWrongUserName: $('.loginWrongUserName'),
        loginLengthUserName: $('.loginLengthUserName'),
        loginpassword: $('#loginpassword'),
        loginPasswordTipIcon: $('.loginPasswordTip .warning'),
        loginWrongLengthPassword: $('.loginWrongLengthPassword'),
        loginWrongPassword: $('.loginWrongPassword')
    };

    // 用户
    var user = {
        // 用户登录判断
        isLogin: false,
    }

    // 组件
    var module = {

        // 头像（原 html 的顺序就是这样排列）
        avater: $('<a href="javascript:;" title="要传送了，准备好了吗？"><svg t="1657536982283" class="icon userAvater" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3627" width="200" height="200"><path d="M510.77050482 2.21923328c281.4240859 0 509.56537287 228.14128697 509.56537173 509.56653682 0 281.42757888-228.14128697 509.56653682-509.56537173 509.56653681-281.42757888 0-509.56886585-228.14012302-509.56886585-509.56653681 0-281.4240859 228.14128697-509.56653682 509.56886585-509.56653682z" fill="#EEEEEE" p-id="3628"></path><path d="M510.77050482 516.51164387c90.0558939 0 163.05952199-73.30168832 163.05952199-163.72549973 0-90.42264633-73.00362809-163.72433465-163.05952199-163.72433465-90.05705785 0-163.06301497 73.30168832-163.06301497 163.72433465-0.00116395 90.42497536 73.00479317 163.72549859 163.06301497 163.72549973z m251.51800206 200.09807531l1.21552441-0.45756757c-39.85964146-100.90130205-137.98992099-172.25861689-252.73352647-172.25861689-113.31967317 0-210.42537358 69.60155875-251.2141221 168.51890176l0.56235463 0.22820181a39.9609344 39.9609344 0 0 0-2.9188881 15.01940622c0 22.15886393 17.97555086 40.12510094 40.15420757 40.12509981 16.79146325 0 31.16701469-10.30401138 37.16196921-24.92522837l0.65433486 0.26545948c28.69987328-68.84360192 96.50958109-117.22355485 175.60014393-117.22355484 79.76934741 0 148.05874461 49.21940878 176.32433493 119.00725475l0.2875813-0.10828003c6.4338944 13.58499499 20.27037696 22.98434901 36.30970538 22.98434901 22.17516373 0 40.15187968-17.96507307 40.15187968-40.12509981a40.14256469 40.14256469 0 0 0-1.55549923-11.05032533z" fill="#CCCCCC" p-id="3629"></path></svg></a>'),

        // 为了美观的部分
        other: $('<div class="login-box">登录</div>'),

        // 已登录面板
        avaterBar: $('<div class="avaterBar"><h1 class="avaterBar-nickname">昵称</h1><div class="avaterBar-interact"><a href="javascript:;" class="avaterBar-follow"><em>0</em><span>关注</span></a><a href="javascript:;" class="avaterBar-fans"><em>0</em><span>粉丝</span></a></div><div class="avaterBar-links"><a href="javascript:;" class="avaterBar-personal"><span>个人主页</span><em class="iconfont">&#xe6b9;</em></a><a href="javascript:;" class="avaterBar-myNotes"><span>我的笔记</span><em class="iconfont">&#xe6b9;</em></a><a href="javascript:;" class="avaterBar-likeFanju"><span>我喜欢的番剧</span><em class="iconfont">&#xe6b9;</em></a></div><div class="avaterBar-loginOut"><span>退出登录</span></div></div>'),

        // 未登录用户面板
        noLoginAvater: $('<div class="noLoginAvaterBar"><h6>登陆后你可以:</h6><div class="introduce"><div><em class="iconfont">&#xe6b5;</em><span>记录笔记</span></div><div><em class="iconfont">&#xeb88;</em><span>发布笔记</span></div><div><em class="iconfont">&#xe639;</em><span>标记喜欢的番剧</span></div></div><div class="immediatelyLogin">立即登录</div><div class="lower"><i>首次使用？</i><a href="javascript:;">点我注册</a></div></div>'),
    }

    // 菜单
    var menu = {
        // 点击菜单图标唤起菜单栏
        show: function () {
            allObject.menu.click(function () {
                var success = JquerySlideRight(allObject.menuBar, 500, allObject.menuBar.width(), function () {

                });
                // 菜单唤出就隐藏
                if (success) {
                    menu.showMenu();
                }
            });
        },

        // 点击关闭图标隐藏菜单
        hide: function () {
            $('.shutdown').click(function () {
                var success = JquerySlideLeft(allObject.menuBar, 500, allObject.menuBar.width(), function () {
                    allObject.menuBar.hide();
                });
                // 菜单隐藏成功就显示
                if (success) {
                    menu.hideMenu();
                }
            });
        },

        // 番剧收藏下拉展示
        fanjuDrop: function () {
            var drop = true;
            $('.dropDown').click(function () {

                // 配合 css 过渡实现点击旋转动画
                if (drop) {
                    this.style.transform = 'rotate(180deg)';

                    // 显示番剧收藏
                    allObject.Fanjus.show();

                    drop = false;
                } else {
                    this.style.transform = 'rotate(0deg)';

                    // 显示番剧收藏
                    allObject.Fanjus.hide();

                    drop = true;
                }


            });
        },

        // 显示菜单，但隐藏其他组件
        showMenu: function menuShow() {

            allObject.menu.hide();          // 隐藏菜单图标
            $('.newlyAdding').hide();   // 隐藏新增笔记图标
            $('.left-entry').hide();    // 隐藏左上角的主站logo，主要是因为有 bug，不知道原因
            return true;
        },

        // 隐藏菜单，但显示其他组件
        hideMenu: function menuHide() {
            allObject.menu.show();          // 隐藏菜单图标
            $('.newlyAdding').show();   // 隐藏新增笔记图标
            $('.left-entry').show();    // 隐藏左上角的主站logo，主要是因为有 bug，不知道原因
            return true;
        }
    };

    // 菜单的出现与消失
    menu.show();
    menu.hide();
    menu.fanjuDrop();

    var browserServer = {

        formData: '',

        // 主机地址
        localhost: window.localhost,

        // 图片地址
        localhost_user_avatar: window.localhost_user_avatar,

        // 向服务器请求登录状态
        loginState: function () {
            browserServer.formData = new FormData();
            browserServer.formData.append("requestType", "ordinary");
            browserServer.formData.append("operation", "status");
            browserServer.browserServerCore(browserServer.formData, function (response) {
                let temp = response.data;
                console.log(temp)
                if (typeof temp == 'object') {
                    user = temp;
                    user.isLogin = true;
                } else {
                    user.isLogin = false;
                }
                browserServer.twoEffects();
            });
        },

        // 登录与未登录的两种效果
        twoEffects: function () {
            if (user.isLogin) {
                console.log(11)
                allObject.indexHeadUserAvater.empty();
                // 设置头像，删除默认
                if (typeof user.user_avatar != 'undefined') {
                    module.avater.find('svg').remove();
                    // 添加图片
                    let avatar = $('<div class="userAvater"><div class="avatar"></div></div>');
                    avatar.find('.avatar').css('background-image', 'url(' + browserServer.localhost_user_avatar + user.user_avatar + ')');
                    module.avater.append(avatar);
                }
                allObject.indexHeadUserAvater.append(module.avaterBar).append(module.avater);


                browserServer.loadUserInfer();

                let avaterBar = $('.avaterBar');
                let userAvater = $('.userAvater');

                // 鼠标经过用户头像区域 逐渐显示已登录用户面板，离开逐渐隐藏已登录用户面板，必须登录才有效果！
                allObject.rightEntry.on('mouseenter', '.index-headUserAvatar', function (e) {
                    avaterBar.show().stop().animate({
                        opacity: 1
                    }, 500);

                    // 用户头像变化
                    userAvater.css('top', '1.875rem').css('right', '1.25rem').width('4.4375rem').height('4.4375rem');

                });

                allObject.rightEntry.on('mouseleave', '.index-headUserAvatar', function (e) {
                    avaterBar.stop().animate({
                        opacity: .3
                    }, 500, function () {
                        $(this).hide();
                    });

                    userAvater.css('top', '0rem').css('right', '0rem').width('2.4375rem').height('2.4375rem');
                });
                console.log(22)
            } else {

                allObject.indexHeadUserAvater.append(module.avater).append(module.other).append(module.noLoginAvater);

                var noLoginAvaterBar = $('.noLoginAvaterBar');
                // 用户未登录显示未登录面板的效果
                allObject.rightEntry.on('mouseenter', '.index-headUserAvatar', function (e) {
                    // 未登录介绍
                    noLoginAvaterBar.show().stop().animate({
                        opacity: 1
                    }, 500);
                });
                allObject.rightEntry.on('mouseleave', '.index-headUserAvatar', function (e) {
                    noLoginAvaterBar.stop().animate({
                        opacity: .3
                    }, 500, function () {
                        $(this).hide();
                    });

                })
            }
        },


        // 加载用户信息
        loadUserInfer: function () {
            // 加载用户信息
            if (user.nickname.length > 8) {
                module.avaterBar.find('.avaterBar-nickname').text(user.nickname.substring(0, 8) + '...');
            } else {
                module.avaterBar.find('.avaterBar-nickname').text(user.nickname);
            }
            let lis = allObject.rightEntry.children('li');
            $(lis[0]).children('a').prop('href', browserServer.localhost + 'history');
            $(lis[1]).children('a').prop('href', browserServer.localhost + 'message');
            module.avaterBar.find('.avaterBar-follow').children('em').text(user.follow);
            module.avaterBar.find('.avaterBar-fans').children('em').text(user.fans);
            module.avater.prop('href', browserServer.localhost + 'homepage/' + user.uid);
            $('.myHomePage a').prop('href', browserServer.localhost + 'homepage/' + user.uid);
            $('.follow a').prop('href', browserServer.localhost + user.uid + '/follow/follow');
            $('.avaterBar-follow').prop('href', browserServer.localhost + user.uid + '/follow/follow');
            $('.avaterBar-fans').prop('href', browserServer.localhost + user.uid + '/follow/fans');
            $('.avaterBar-personal').prop('href', browserServer.localhost + 'homepage/' + user.uid);
            $('.like-Fanju a').prop('href', browserServer.localhost + 'panoperaHouse/' + user.uid);
            $('.avaterBar-likeFanju').prop('href', browserServer.localhost + 'panoperaHouse/' + user.uid);
            $('.settings a').prop('href', browserServer.localhost + 'settings');
            $('.myStar a').prop('href', browserServer.localhost + 'star/' + user.uid);
            $('.newlyAdding-link').prop('href', browserServer.localhost + 'buildNote');
            $('.build').prop('href', browserServer.localhost + 'buildNote');
            $('.avaterBar-myNotes').prop('href', browserServer.localhost + user.uid + '/notes');
            $('.notes').prop('href', browserServer.localhost + user.uid + '/notes');

        },


        // 检查是否重名
        sameUsername: function () {
            axios({
                url: browserServer.localhost + "dispatcherServlet",
                method: "post",
                headers: {
                   "Content-Type": "application/x-www-form-urlencoded; charset=utf-8"
                },
                data: {
                    requestType: "ordinary",
                    operation: "register",
                    username: allObject.registerusername.val()
                }
            }).then(function (response) {
                var temp = decodeURI(response.data);// 使用 UTF-8 解码，再对数据进行处理
                if (temp === "sameUsername") { // 0 是用户名存在， 1 是用户名不存在
                    allObject.registerSameUserName.show();
                    allObject.registerWrongUserName.hide();
                    allObject.registerUsernameTipIcon.css('visibility', 'visible');
                } else {
                    allObject.registerSameUserName.hide();
                    allObject.registerUsernameTipIcon.css('visibility', 'hidden');
                }
            });
        },

        // 判断是否有这个用户
        loginNoUser: function () {

            axios({
                url: browserServer.localhost + "dispatcherServlet",
                method: "post",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded; charset=utf-8"
                },
                data: {
                    requestType: "ordinary",
                    operation: "login",
                    username: allObject.loginusername.val(),
                    password: allObject.loginpassword.val()
                }
            }).then(function (response) {
                var temp = decodeURI(response.data);// 使用 UTF-8 解码，再对数据进行处理
                console.log(temp)
                if (temp === 'noUser') {
                    allObject.loginWrongUserName.show();
                    allObject.loginUsernameTipIcon.css('visibility', 'visible');
                } else if(temp === 'loginSucceed'){
                    allObject.loginWrongUserName.hide();
                    allObject.loginUsernameTipIcon.css('visibility', 'hidden');
                    $('.shutdownLoginRegister i')[0].click();
                    // 获取登录状态
                    browserServer.loginState();
                }
            });
        },

        // 退出登录
        loginOut: function () {
            allObject.indexHeadUserAvater.on('click', '.avaterBar-loginOut', function () {
                axios({
                    url: browserServer.localhost + "dispatcherServlet",
                    method: "post",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded; charset=utf-8"
                    },
                    data: {
                        requestType: "ordinary",
                        operation: "loginOut"
                    }
                }).then(function () {
                    location.reload();
                });
            });
        },


        browserServerCore: function (data, fn) {
            axios({
                url: browserServer.localhost + 'dispatcherServlet',
                method: 'post',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8',
                },
                data: {
                    "requestType": "ordinary",
                    "operation": "status"
                }

            }).then(fn);
        }
    };

    browserServer.loginOut();
    browserServer.loginState();


    // 登录注册功能
    var loginRegister = {

        // 登录注册页面切换
        loginRegisterChange: function () {
            allObject.huadiaoJumpLoginA.click(function () {
                allObject.register.hide();
                allObject.login.show();
                allObject.LoginRegisterForeground.css('background', 'url(' + browserServer.localhost + 'images/indexImages/Login.png) no-repeat').animate({
                    right: 300
                }, 800);
            });

            allObject.huadiaoJumpRegisterA.click(function () {
                allObject.register.show();
                allObject.login.hide();
                allObject.LoginRegisterForeground.css('background', 'url(' + browserServer.localhost + 'images/indexImages/Register.png) no-repeat').animate({
                    right: 36
                }, 800);
            });
        },

        // 跳往注册页面
        jumpRegister: function () {
            allObject.indexHeadUserAvater.on('click', '.lower a', function () {
                allObject.LoginRegister.show();
                allObject.huadiaoJumpRegisterA.click();
            });
        },

        // 跳往登录页面
        jumpLogin: function () {
            allObject.indexHeadUserAvater.on('click', '.immediatelyLogin', function () {
                allObject.huadiaoJumpLoginA.click();
                allObject.LoginRegister.show();
            });
        },

        // 检查用户名
        checkUsername: function checkUsername(this_, icon, wrongLength, wrongUsername) {
            var value = $(this_).val();
            if (value.length > 20 || value.length < 6) {
                wrongLength.show();
                wrongUsername.hide();
                icon.css('visibility', 'visible');
                return false;
            } else {
                wrongLength.hide();
                if (allObject.loginWrongUserName.css('display') !== 'block') {
                    icon.css('visibility', 'hidden');
                }
                return true;
            }
        },

        // 检查密码
        checkPassword: function checkPassword(this_, icon, wrongLength, wrongPassword) {
            var value = $(this_).val();
            var reg = /\W*((([0-9]+)\W*[a-zA-Z]+)|(([a-zA-Z]+)\W*[0-9]+)).*/;
            if (value.length < 8 || value.length > 32) {
                wrongLength.show();
                wrongPassword.hide();
                icon.css('visibility', 'visible');
                return false;
            } else {
                wrongLength.hide();
                icon.css('visibility', 'hidden');
                if (!reg.test(value)) {
                    wrongLength.hide();
                    wrongPassword.show();
                    icon.css('visibility', 'visible');
                    return false;
                } else {
                    wrongPassword.hide();
                    icon.css('visibility', 'hidden');
                    return true;
                }

            }

        },

        // 验证码点击切换
        changeCheckCode: function () {

            // 验证码图片点击切换
            $('.checkCode img').click(function () {
                // 这里需要注意，改变验证码需要不同的路径，这里用时间做后缀最合适
                this.src = '/huadiao/checkCodeServlet?' + new Date().getMilliseconds();
            })
        },

        // 登录表单提交
        loginForm: function () {
            $('.login form').submit(function (e) {
                // 阻止默认的表单提交
                e.preventDefault();
                if (loginRegister.checkUsername(allObject.loginusername, allObject.loginUsernameTipIcon, allObject.loginLengthUserName, allObject.loginWrongUserName) &&
                    loginRegister.checkPassword(allObject.loginpassword, allObject.loginPasswordTipIcon, allObject.loginWrongLengthPassword, allObject.loginWrongPassword)) {
                    // 判断是否存在该用户
                    browserServer.loginNoUser();
                } else {
                    return false;
                }
            });
        },

        // 注册表单提交
        registerForm: function () {
            $('.register form').submit(function (e) {
                // 阻止默认的表单提交
                e.preventDefault();
                if (loginRegister.checkUsername(allObject.registerusername, allObject.registerUsernameTipIcon, allObject.registerWrongUserName, allObject.registerSameUserName) &&
                    loginRegister.checkPassword(allObject.registerpassword, allObject.registerPasswordTipIcon, allObject.registerWrongLengthPassword, allObject.registerWrongPassword)) {

                    axios({
                        url: browserServer.localhost + "dispatcherServlet",
                        method: "post",
                        headers: {
                            "Content-Type": "application/x-www-form-urlencoded; charset=utf-8"
                        },
                        data: {
                            username: allObject.registerusername.val(),
                            password: allObject.registerpassword.val(),
                            checkCode: allObject.checkCode.val()
                        }
                    }).then(function (response) {
                        var temp = decodeURI(response.data);
                        if (temp === 'wrongCode') {
                            allObject.checkCodeTipIcon.css('visibility', 'visible');
                            allObject.wrongCheckCode.show();
                        } else if (temp === 'succeedRegister') {
                            allObject.checkCodeTipIcon.css('visibility', 'hidden');
                            allObject.wrongCheckCode.hide();
                            allObject.huadiaoJumpLoginA.click();
                            $('.checkCode img')[0].src = '/huadiao/checkCodeServlet?' + new Date().getMilliseconds();
                        }
                    });

                } else {
                    return false;
                }
            });
        },

        // 关闭登录注册页面
        shutdown: function () {
            $('.shutdownLoginRegister i').click(function () {
                allObject.LoginRegister.hide();
            });
        }
    };

    loginRegister.loginRegisterChange();
    loginRegister.jumpLogin();
    loginRegister.jumpRegister();
    loginRegister.shutdown();
    loginRegister.changeCheckCode();
    loginRegister.loginForm();
    loginRegister.registerForm();

    // 默认背景为 注册背景
    allObject.LoginRegisterForeground.css('background', 'url(../images/Register.png) no-repeat');


    allObject.loginusername.blur(function () {
        loginRegister.checkUsername($(this), allObject.loginUsernameTipIcon, allObject.loginLengthUserName, allObject.loginWrongUserName);
    });

    allObject.registerusername.blur(function () {
        if (loginRegister.checkUsername($(this), allObject.registerUsernameTipIcon, allObject.registerWrongUserName, allObject.registerSameUserName)) {
            browserServer.sameUsername();
        }
    });


    allObject.loginpassword.blur(function () {
        loginRegister.checkPassword($(this), allObject.loginPasswordTipIcon, allObject.loginWrongLengthPassword, allObject.loginWrongPassword);
    });

    allObject.registerpassword.blur(function () {
        loginRegister.checkPassword($(this), allObject.registerPasswordTipIcon, allObject.registerWrongLengthPassword, allObject.registerWrongPassword);
    });


    // 滚动页面到第二屏的背景效果，主要是第二屏背景会出现裂缝
    scrollPage();
    $(window).scroll(function () {
        scrollPage();
    });

    // 鼠标滚动触发
    $(window).scroll(function () {
        if (allObject.menuBar.css('display') === 'block') {
            JquerySlideLeft(allObject.menuBar, 500, allObject.menuBar.width(), function () {
                allObject.menuBar.hide();
                menu.hideMenu();
            });
        }
    });


    // 搜索框的点击变化
    var search_index = true;
    allObject.searchBox.click(function () {
        if (search_index) {

            allObject.search.stop().animate({
                width: 408
            }, 300, function () {
                allObject.search.css('padding-left', '.625rem').focus();
                search_index = false;
            });

            allObject.searchBox.css('transform', 'scale(0.631, 0.631)');
        } else {

            allObject.search.stop().animate({
                width: 0
            }, 300, function () {
                allObject.search.css('padding-left', '0rem').blur();
                search_index = true;
            });

            allObject.searchBox.css('transform', 'scale(1, 1)');
        }
    });


    // 线条移动
    linemove('#register', '.register', '#checkCode');
    linemove('#login', '.login');


    // ————————————————————————————————————————————————————————————————————————————————————————————

    // 下面是函数集群


    // 自制左滑效果（采用原生 DOM 实现）
    // 参数：obj 对象；time 过渡时间；length 运动长度，fn 回调函数

    function DOMSlideLeft(obj, time, length, fn) {
        // 判断类型
        if (typeof obj != 'object' || typeof time != 'number' || typeof length != 'number') {
            return;
        }
        // 执行动画
        obj.animate({
                // css 样式
                transform: 'translateX(-' + length + 'px)'
            },
            {
                // 其他属性
                duration: time,  //  动画时长  (单位毫秒)
                easing: 'linear',        // 平滑
                iterations: 1  //  重复次数  （无限循环：Infinity）
            });

        // 延时执行回调函数
        setTimeout(fn, time);
    }


    var flag = true;

    // jquery 实现左移渐出
    function JquerySlideLeft(obj, time, length, fn) {
        // 对象转换
        obj = $(obj);
        // 判断类型
        if (typeof obj != 'object' || typeof time != 'number' || typeof length != 'number') {
            return;
        }

        // 注意这里 left 可能为 auto 
        var oldLeft = parseInt(obj.css('left').split('p')[0]);

        if (flag) {   // 此if 语句是为了在 一个周期内只能进行一次左移动画 
            flag = false;
            obj.animate({
                left: (oldLeft - length) / 16 + 'rem'
            }, time, function () {
                fn();
                flag = true;
            });
        }


        return true;
    }


    // jquery 实现右移渐入
    function JquerySlideRight(obj, time, length, fn) {
        // 对象转换
        obj = $(obj);
        // 判断类型
        if (typeof obj != 'object' || typeof time != 'number' || typeof length != 'number') {
            return;
        }
        obj.css('left', -length);
        // 获取不带单位的数值
        var oldLeft = parseInt(obj.css('left').split('p')[0]);

        // 动画前先显示
        obj.show();
        obj.stop().animate({
            left: (oldLeft + length) / 16 + 'rem'
        }, time, fn);

        // 完成
        return true;
    }


    // 滚动页面到第二屏的背景效果，主要是第二屏背景会出现裂缝
    function scrollPage() {

        if ($(window).height() <= window.pageYOffset) {
            $('.page-one').css('width', 'calc(100vw - 1.0625rem)');
            $('.page-two-background').css('position', 'fixed').css('top', '0rem').css('width', 'calc(100vw - 1.0625rem)').css('background-size', '98.9375rem');
            $('.huadiao-index-search').css('position', 'fixed').css('top', '.9375rem');
        } else {
            $('.page-one').css('width', '100vw');
            $('.page-two-background').css('position', 'absolute').css('top', '100vh').css('width', '100vw').css('background-size', '100rem');
            $('.huadiao-index-search').css('position', 'absolute').css('top', 'calc(100vh + .9375rem)');
        }
    }


    // 线条移动
    function linemove(strid, strclass, strcode) {
        var current = null;
        document.querySelector(strid + 'username').addEventListener('focus', function (e) {
            if (current) current.pause();
            current = anime({
                targets: 'path',
                strokeDashoffset: {
                    value: 0,
                    duration: 700,
                    easing: 'easeOutQuart'
                },
                strokeDasharray: {
                    value: '200 1386',
                    duration: 700,
                    easing: 'easeOutQuart'
                }
            });
        });
        document.querySelector(strid + 'password').addEventListener('focus', function (e) {
            if (current) current.pause();
            current = anime({
                targets: 'path',
                strokeDashoffset: {
                    // 每次移动距离
                    value: -295,
                    duration: 700,
                    easing: 'easeOutQuart'
                },
                strokeDasharray: {
                    // 左边的是长度
                    value: '200 1386',
                    duration: 700,
                    easing: 'easeOutQuart'
                }
            });
        });
        if (typeof strcode != 'undefined') {
            document.querySelector(strcode).addEventListener('focus', function (e) {
                if (current) current.pause();
                current = anime({
                    targets: 'path',
                    strokeDashoffset: {
                        value: -600,
                        duration: 700,
                        easing: 'easeOutQuart'
                    },
                    strokeDasharray: {
                        value: '75 1386',
                        duration: 700,
                        easing: 'easeOutQuart'
                    }
                });
            });
        }
        document.querySelector(strclass + 'Button').addEventListener('focus', function (e) {
            if (current) current.pause();
            current = anime({
                targets: 'path',
                strokeDashoffset: {
                    value: -938,
                    duration: 700,
                    easing: 'easeOutQuart'
                },
                strokeDasharray: {
                    value: '500 1386',
                    duration: 700,
                    easing: 'easeOutQuart'
                }
            });
        });
    }

})