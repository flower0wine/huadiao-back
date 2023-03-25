$(function () {

    // 全屏滚动
    $('.huadiao-fanjuHouse').fullpage();

    // 重复的 jquery 选择器
    var allObject = {
        pagetwoFanjusBar: $('.pagetwo-fanjus-bar'),
        pageTwoFanjusBarUl: $('.pagetwo-fanjus-bar ul'),
        fanjuName: $('#fanjuname'),
        angle: $('#angle'),
        // 上传番剧的 input
        fanjuCover: $('#fanjucover'),
        imgTip: $('.imgTip'),
        imgTipBackground: $('.img-tip-background'),
        pagetwoBackground: $('.fanjuHouse-pagetwo-background'),
        pagetwoForeground: $('.pagetwo-fanjus-bar'),
        cardBackground: $('.pagetwo-fanjus-bar ul .addfanju-enter .fanju-cover'),
    }

    // 与创建番剧有关
    var createFanju = {

        // 上传的图片最大为 2M
        FILE_SIZE: 2097152,

        // 番剧名，临时保存，因为在被赋值前会被置空
        fanjuName: '',

        // 检查番剧名称是否为空
        nameIsNull: function () {
            $('#fanjuname').blur(function () {
                createFanju.fanjuname($(this));
            });
        },

        // 检查番剧名称
        fanjuname: function (obj) {
            var o = $(obj);
            var nullname = $('.nullname');
            if (o.val().length == 0) {
                nullname.show();
                return false;
            } else {
                nullname.hide();
                return true;
            }
        },


        addFanjus: function () {
            // 新增番剧
            allObject.pageTwoFanjusBarUl.on('click', '.addfanju-enter', function () {

                $('.addfanju-background').show();
                // 如果有文件就清除文件
                allObject.fanjuCover.val('');
                // 预览图片，方法来源于网络
                allObject.fanjuCover.off('change').change(function () {

                    // 如果文件大小在2M以内
                    if (sizeCorrect(allObject.fanjuCover)) {
                        $('.coverTip').hide().siblings('.filetype').hide();
                        let imgPath = createFanju.getImgPath($(this));

                        cover_icon(false, imgPath);
                    } else {
                        cover_icon(true);
                        $('.filetype').show().siblings('.coverTip').hide();
                    }
                });


                // 提交按钮
                $('.add-btn').off('click').click(function () {

                    if (!createFanju.fanjuname(allObject.fanjuName)) {
                        return false;
                    }
                    let files = allObject.fanjuCover[0].files;
                    // 保存番剧名
                    createFanju.fanjuName = allObject.fanjuName.val();
                    // 如果上传了文件并且格式正确
                    if (files.length !== 0) {
                        if (sizeCorrect(allObject.fanjuCover)) {
                            // 上传文件
                            browserServer.fanjuCoverUpload(allObject.fanjuCover);

                        } else {
                            cover_icon(true);
                            $('.filetype').show().siblings('.coverTip').hide();
                        }

                    } else {
                        cover_icon(true);
                        $('.coverTip').show().siblings('.filetype').hide();
                    }

                    allObject.fanjuName.val('');
                });
            });
        },


        // 确认添加后，让服务器返回的结果决定是否建立
        buildNewFanju: function () {
            $('.coverTip').hide().siblings('.filetype').hide();
            let time = new Date();
            let year = time.getFullYear();
            let month = (time.getMonth() + 1) > 9 ? time.getMonth() + 1 : '0' + (time.getMonth() + 1);
            let day = time.getDate() > 9 ? time.getDate() : '0' + time.getDate();
            let hour = time.getHours() > 9 ? time.getHours() : '0' + time.getHours();
            let minute = time.getMinutes() > 9 ? time.getMinutes() : '0' + time.getMinutes();
            let val = createFanju.fanjuName;
            let li = $('<li><div class="fanju-cover"></div><div class="fanju-below"><div class="fanju-information"><div class="fanju-name"></div><div class="add-time"></div></div><div class="fanju-operation"><div class="delete-icon"><svg t="1658305889860" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3964" width="200" height="200"><path d="M268.214857 121.929143C268.214857 68.022857 311.881143 24.356571 365.714286 24.356571h292.571428c53.906286 0 97.572571 43.666286 97.572572 97.572572V219.428571h96.987428a46.811429 46.811429 0 0 1 0.877715 0H950.857143a48.786286 48.786286 0 0 1 0 97.499429h-52.077714l-42.349715 592.091429a97.499429 97.499429 0 0 1-97.28 90.624H264.850286c-51.2 0-93.622857-39.497143-97.28-90.624l-42.276572-592.091429H73.142857A48.786286 48.786286 0 0 1 73.142857 219.428571h97.133714a46.811429 46.811429 0 0 1 0.877715 0h97.060571V121.929143zM365.714286 219.428571h292.571428V121.929143H365.714286V219.428571zM223.085714 316.928l41.691429 585.142857h494.445714l41.691429-585.142857h-577.828572z m191.414857 97.572571c26.916571 0 48.786286 21.796571 48.786286 48.786286v292.571429a48.786286 48.786286 0 1 1-97.572571 0v-292.571429c0-26.989714 21.869714-48.786286 48.786285-48.786286z m195.072 0c26.916571 0 48.713143 21.796571 48.713143 48.786286v292.571429a48.786286 48.786286 0 1 1-97.499428 0v-292.571429c0-26.989714 21.796571-48.786286 48.786285-48.786286z" p-id="3965"></path></svg></div><div class="delete-word">删除该收藏</div></div></div></li>')

            // 番剧名称
            if (val.length > 12) {
                li.find('.fanju-name').html(val.substring(0, 12) + '<br>' + val.substring(12)).prop('title', val);
            } else {
                li.find('.fanju-name').text(val).prop('title', val);
            }
            // 添加时间
            li.find('.add-time').text(year + '年' + month + '月' + day + '日 ' + hour + ':' + minute);
            // 本地添加番剧背景，就不从数据库获取了
            let imgPath = createFanju.getImgPath(allObject.fanjuCover);
            li.find('.fanju-cover').css('background-image', 'url(' + imgPath + ')');
            // 在最后一个 li 前添加
            $('.addfanju-enter').before(li);

            // 完成后隐藏
            $('.addfanju-background').hide();
            cover_icon(true);
        },


        // 取消添加
        cancelAdd: function () {
            $('.canceladdfanju').click(function () {
                $('.addfanju-background').hide();
                allObject.fanjuName.val('');
                $('.icon-background').show();
                $('.img-box').hide().prop('src', '');
            });
        },


        // 预览图片所需的 url 从这里来
        // 将带文件的 input 传入，返回的 url 可以放在 img 标签的 src 或者 任何盒子的 background-image 中
        getImgPath: function (obj) {
            let o = obj[0];
            let fileObj = o.files[0];
            let url;
            if (window.createObjectURL != undefined) {
                url = window.createObjectURL(fileObj);
            } else if (window.URL != undefined) {
                url = window.URL.createObjectURL(fileObj);
            } else if (window.webkitURL != undefined) {
                url = window.webkitURL.createObjectURL(fileObj);
            }
            return url;
        },


        // 删除番剧
        deleteFanju: function () {
            // 删除收藏
            allObject.pageTwoFanjusBarUl.on('click', '.fanju-operation', function () {

                let li = $(this).parents('li');
                $('.deleteFanju-background').show(200);
                $('.deleteFanju-confirm').off('click').click(function () {

                    $('.deleteFanju-background').hide(200);
                    let params = {
                        requestType: "huadiaoHouse",
                        operation: "deleteFanju",
                        signId: module.fanjuInfer[li.index()].signId
                    };
                    browserServer.browserServerCore(params, {}, null, function (response) {
                        let temp = response.data;
                        if (temp === 'uploadFanjuCoverSucceed') {
                            Gradient.imgTip("添加成功", 300, 800);
                            // 根据返回值来判断是否建立番剧
                            createFanju.buildNewFanju();
                        } else if (temp === 'uploadFail') {
                            Gradient.imgTip("删除失败", 300, 800);
                        } else {
                            Gradient.imgTip("删除成功", 300, 800);
                        }
                    });
                    module.fanjuInfer.splice(li.index(), 1);
                    li.remove();
                });

                $('.deleteFanju-cancel').off('click').click(function () {
                    $('.deleteFanju-background').hide(200);
                });

            });
        }

    };


    createFanju.nameIsNull();
    createFanju.addFanjus();
    createFanju.cancelAdd();
    createFanju.deleteFanju();


    var otherEvent = {

        // 添加滚动条
        addScrollBar: function (target){
            target.slimScroll({
                height: '80.283vh',
                size: '7px', //组件宽度
                color: '#e3e3e3', //滚动条颜色
                start: 'top', //默认滚动位置：top/bottom
                disableFadeOut: false, //是否 鼠标经过可滚动区域时显示组件，离开时隐藏组件
                railVisible: true, //是否 显示轨道
                railColor: '#6c6c6c', //轨道颜色
                railOpacity: .2, //轨道透明度
                railDraggable: true, //是否 滚动条可拖动
                allowPageScroll: true, //是否 使用滚轮到达顶端/底端时，滚动窗口
            });
        },
    };

    otherEvent.addScrollBar(allObject.pagetwoFanjusBar);


    // 这个函数为了简介代码
    function cover_icon(boolean, url) {
        if (boolean) {
            $('.icon-background').show();
            $('.img-box').hide().find('.coverImg').prop('src', '');
        } else {
            $('.icon-background').hide();
            $('.img-box').show().find('.coverImg').prop('src', url);
        }
    }


    // 判断文件大小，传入带有文件的 input 即可
    function sizeCorrect(t) {
        var target = $(t);
        // 获取文件大小
        var size = target[0].files[0].size;
        return size <= createFanju.FILE_SIZE;
    }


    // 使用渐变色
    var Gradient = {

        // 该变量确认点击的是哪个商店组件，只有一个拾色器，必须确定是谁正在使用
        li_index: '',

        // 角度
        angle: '',
        // 渐变框的数量
        colorbox: 1,
        // 面板
        multiplyBar: $('.multiply-bar'),
        // 面板右侧 input 的 ul
        rightBar_ul: $('.multiply-bar-right ul'),
        // 更改颜色的对象变量
        targetGround: '',

        BackgroundString: '',
        backgroundInfer: '',
        // 新的渐变色，临时保存
        newBackground: '',
        // 用来保存 background-image 的值，用于恢复
        oldImg: '',
        // 选择颜色
        flag: 0,
        // 限制每个模块不能连续
        modular: {
            array: [false, false, false, false, false, false],
            restoreDefault: function (t) {
                // 如果 li_index 与 t 不相同就恢复默认
                if (Gradient.li_index != t) {
                    for (var i = 0; i < this.array.length; i++) {
                        this.array[i] = false;
                    }
                }
            }
        },

        // 为了让每一个模块不能连续点击
        old_li_index: -1,
        new_li_index: -1,
        // 保存两次点击是否相同的变量
        same: false,
        // 首次点击跳过
        ini: false,
        pagetwoBackground: false,
        pagetwoForeground: false,
        // 点击确认后再点击其他模块不恢复
        isRestoreColor: true,

        // 判断两次点击是否相同，进而做更多的判断
        isSame: function () {
            this.old_li_index = this.new_li_index;
            this.new_li_index = Gradient.li_index;

            // 首次进入页面跳过
            if (this.ini) {
                // 如果两次点击相同
                if (this.old_li_index !== this.new_li_index) {
                    this.same = false;
                    this.tailHandle();
                    // 如果点击的不是同一个，并且没有点击确认
                    if (Gradient.isRestoreColor) {
                        Gradient.restoreColor();
                    }
                } else {
                    this.same = true;

                }
            }
            this.ini = true;
        },


        // 打开主题商店
        openThemeShop: function () {
            $('.themeshop-icon .icon').click(function () {
                var themeshopBar = $('.huadiao-fanju-themeshop');
                JquerySlideRight(themeshopBar, 400, themeshopBar.width(), function () {
                });
            });
        },


        // 如果使用渐变色就用渐变色初始化
        colorInitial: function () {
            // 初始化
            Gradient.newBackground = {
                direction: '',
                colorOne: '',
                colorTwo: '',
                colorThree: '',
                url: ''
            }
            // 获取字符串
            Gradient.BackgroundString = Gradient.targetGround.css('background-image');
            Gradient.oldImg = Gradient.BackgroundString;
            // 如果不是渐变色，是单色
            if (Gradient.BackgroundString == 'none') {
                Gradient.BackgroundString = Gradient.targetGround.css('background-color');
                Gradient.angle = 0;
                // 如果是一张图片
            } else if (Gradient.BackgroundString.search('url') != -1) {
                Gradient.angle = 0;

                // 否则就是渐变色
            } else {

                Gradient.backgroundInfer = Gradient.BackgroundString.split('t(')[1].split(', rgb');

                Gradient.angle = Gradient.backgroundInfer[0].split('deg')[0];

            }
            // 进入时加载之前的角度
            if (Gradient.angle == '') {
                $('#angle').val('0');
            } else {
                $('#angle').val(Gradient.angle);
            }
        },

        // 进行渐变色处理，这里是渐变色控制面板
        colorOperation: function () {
            // 得到焦点自动清空
            allObject.angle.off('focus').focus(function () {
                $(this).val('');
                Gradient.angle = '';
            });

            // 角度框失去焦点进行赋值
            allObject.angle.off('blur').blur(function () {
                let value = $(this).val();

                // 必须是数字并且范围在 0 到 360 度之间
                if (Gradient.angleIsTrue()) {
                    // 是小数转换为整数
                    Gradient.newBackground.direction = parseInt($(this).val()).toFixed(0);
                    Gradient.angle = Gradient.newBackground.direction;
                    // 实时处理
                    Gradient.actualTime(Gradient.newBackground, Gradient.colorbox);
                } else {
                    if (value == '') {
                        $(this).val('0');
                        Gradient.newBackground.direction = '0';
                        Gradient.angle = '0';
                        Gradient.actualTime(Gradient.newBackground, Gradient.colorbox);
                    } else {
                        $(this).val('错误输入');
                    }
                }
            });


            // 添加渐变色框
            $('.addnewcolor').off('click').click(function () {
                let li;
                if (Gradient.colorbox == 1) {
                    Gradient.colorbox++;
                    li = $('<li><label for="colorbar"><svg t="1658404316200" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4184" data-spm-anchor-id="a313x.7781069.0.i2" width="200" height="200"><path d="M518.18892857 28.74575632c-266.46499615-3.41205767-486.02835289 210.60446149-489.44593394 477.07083847-2.72025642 212.63429749 132.97359041 395.38719959 323.31697451 462.28755662 48.19686809 16.94429774 99.91488029 26.46381437 153.74834062 27.14871143 78.10726115 1.00525212 132.17408358-16.52590298 134.43728167-17.27431869l6.41538647-2.73130315c9.19363821-4.87436811 18.69658474-9.90753291 27.60024638-16.48447775 7.1403279-5.25271849 20.43644418-15.01250143 28.03521123-32.33929207 1.81028233-4.1369991 7.51591661-19.18264071 4.59129572-38.77815283-4.15633087-27.9772159-26.40305738-50.49044469-49.94639341-74.33480404-9.63964979-9.76254462-19.61208221-19.85649104-25.8120575-28.0821598-6.72607564-9.22677838-23.09456276-38.78229536-18.68691886-65.48913773 4.49049435-27.26608289 28.89547506-55.2805815 54.40374759-62.4347178 26.80073953-7.54491426 55.09831052 8.79181351 95.20345139 34.55416074 42.00655869 27.00372313 89.62761614 57.55344551 142.98192471 36.00128194 39.45614567-15.97632833 66.53581505-56.02623555 80.53892184-119.35711907 0.37420786-1.73985943 9.07488588-43.13470563 9.68245585-90.32079823 3.40929599-266.46499615-210.64864839-486.04492299-477.06393427-489.43626804z m386.42001929 562.18724765c-7.2549377 32.81153963-18.72420157 55.29438992-30.64223871 60.12457111-13.5874737 5.47089134-40.80660801-12.00088759-67.1323383-28.90514094-43.54619619-27.97031171-97.7372943-62.78821307-162.37859575-44.62049035-56.21679158 15.77334471-104.05740274 69.90782834-113.76609457 128.72888571-10.91416587 66.21684082 29.46576233 122.40739645 34.10676834 128.60046753l0.02209346 0.02071262c9.55541849 12.72306735 21.56044861 24.88965584 33.17055824 36.64751545 6.52999626 6.59765745 16.12545914 16.31601516 22.27572416 23.71317945-2.56698309 1.52444827-5.54683758 3.15245961-8.78767099 4.88541482-10.83683877 3.0378498-50.58158019 12.9785229-104.60283487 12.28119829-220.79230509-2.83210452-398.11574105-184.74131287-395.28363653-405.54052215 2.8210578-220.77573499 184.75512129-398.1102177 405.53361795-395.27811317 44.61634784 0.57581061 87.44864933 8.45765004 127.38947013 22.49942037 157.70306873 55.43937821 270.15598367 206.86652539 267.89554724 383.04110178-0.49572185 38.68977902-7.80036981 73.80179948-7.8003698 73.80179948z" p-id="4185"></path><path d="M762.72893411 457.77990425c-10.11465902 28.76981855 5.00969051 60.29441454 33.77950907 70.40769272 28.76981855 10.11327819 60.29165285-5.00969051 70.40769273-33.77950906 10.11327819-28.76981855-5.00969051-60.2930337-33.78088989-70.40769272-28.76981855-10.11327819-60.2930337 5.00969051-70.40631191 33.77950906zM323.52489355 254.55465611c-28.77119939-10.11327819-60.29165285 5.00969051-70.40769273 33.77950905s5.00969051 60.29027202 33.78088988 70.40769273c28.76981855 10.11327819 60.29027202-5.00969051 70.40631189-33.77950905 10.11603987-28.77258022-5.00969051-60.29441454-33.77950904-70.40769273zM458.47446718 158.94109501c-28.76981855-10.11327819-60.29165285 5.00969051-70.40631189 33.77950904-10.11465902 28.76981855 5.00969051 60.29441454 33.77950906 70.40769273 28.76981855 10.11327819 60.29165285-5.00969051 70.40769272-33.77950905 10.11327819-28.76981855-5.00969051-60.29441454-33.78088989-70.40769272zM766.95016451 266.63425163c-28.76981855-10.11742071-60.29165285 5.00554799-70.40769271 33.77950905-10.11327819 28.76981855 5.00969051 60.29027202 33.77950905 70.40769273 28.77119939 10.11327819 60.29441454-5.00969051 70.40769273-33.77950905 10.11327819-28.77396107-5.00969051-60.29441454-33.77950907-70.40769273zM628.77494672 166.0206659c-28.76981855-10.11327819-60.29165285 5.00969051-70.40769273 33.77950905-10.11327819 28.76981855 5.00969051 60.29441454 33.7808899 70.40769273 28.76981855 10.11327819 60.29165285-5.00969051 70.40493104-33.77950905 10.11603987-28.76981855-5.00830966-60.29441454-33.77812821-70.40769273z" p-id="4186"></path></svg></label><input type="text" name="colortwo" id="colortwo" placeholder="渐变色二"></li>');
                    Gradient.rightBar_ul.append(li);
                } else if (Gradient.colorbox == 2) {
                    Gradient.colorbox++;
                    li = $('<li><label for="colorbar"><svg t="1658404316200" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4184" data-spm-anchor-id="a313x.7781069.0.i2" width="200" height="200"><path d="M518.18892857 28.74575632c-266.46499615-3.41205767-486.02835289 210.60446149-489.44593394 477.07083847-2.72025642 212.63429749 132.97359041 395.38719959 323.31697451 462.28755662 48.19686809 16.94429774 99.91488029 26.46381437 153.74834062 27.14871143 78.10726115 1.00525212 132.17408358-16.52590298 134.43728167-17.27431869l6.41538647-2.73130315c9.19363821-4.87436811 18.69658474-9.90753291 27.60024638-16.48447775 7.1403279-5.25271849 20.43644418-15.01250143 28.03521123-32.33929207 1.81028233-4.1369991 7.51591661-19.18264071 4.59129572-38.77815283-4.15633087-27.9772159-26.40305738-50.49044469-49.94639341-74.33480404-9.63964979-9.76254462-19.61208221-19.85649104-25.8120575-28.0821598-6.72607564-9.22677838-23.09456276-38.78229536-18.68691886-65.48913773 4.49049435-27.26608289 28.89547506-55.2805815 54.40374759-62.4347178 26.80073953-7.54491426 55.09831052 8.79181351 95.20345139 34.55416074 42.00655869 27.00372313 89.62761614 57.55344551 142.98192471 36.00128194 39.45614567-15.97632833 66.53581505-56.02623555 80.53892184-119.35711907 0.37420786-1.73985943 9.07488588-43.13470563 9.68245585-90.32079823 3.40929599-266.46499615-210.64864839-486.04492299-477.06393427-489.43626804z m386.42001929 562.18724765c-7.2549377 32.81153963-18.72420157 55.29438992-30.64223871 60.12457111-13.5874737 5.47089134-40.80660801-12.00088759-67.1323383-28.90514094-43.54619619-27.97031171-97.7372943-62.78821307-162.37859575-44.62049035-56.21679158 15.77334471-104.05740274 69.90782834-113.76609457 128.72888571-10.91416587 66.21684082 29.46576233 122.40739645 34.10676834 128.60046753l0.02209346 0.02071262c9.55541849 12.72306735 21.56044861 24.88965584 33.17055824 36.64751545 6.52999626 6.59765745 16.12545914 16.31601516 22.27572416 23.71317945-2.56698309 1.52444827-5.54683758 3.15245961-8.78767099 4.88541482-10.83683877 3.0378498-50.58158019 12.9785229-104.60283487 12.28119829-220.79230509-2.83210452-398.11574105-184.74131287-395.28363653-405.54052215 2.8210578-220.77573499 184.75512129-398.1102177 405.53361795-395.27811317 44.61634784 0.57581061 87.44864933 8.45765004 127.38947013 22.49942037 157.70306873 55.43937821 270.15598367 206.86652539 267.89554724 383.04110178-0.49572185 38.68977902-7.80036981 73.80179948-7.8003698 73.80179948z" p-id="4185"></path><path d="M762.72893411 457.77990425c-10.11465902 28.76981855 5.00969051 60.29441454 33.77950907 70.40769272 28.76981855 10.11327819 60.29165285-5.00969051 70.40769273-33.77950906 10.11327819-28.76981855-5.00969051-60.2930337-33.78088989-70.40769272-28.76981855-10.11327819-60.2930337 5.00969051-70.40631191 33.77950906zM323.52489355 254.55465611c-28.77119939-10.11327819-60.29165285 5.00969051-70.40769273 33.77950905s5.00969051 60.29027202 33.78088988 70.40769273c28.76981855 10.11327819 60.29027202-5.00969051 70.40631189-33.77950905 10.11603987-28.77258022-5.00969051-60.29441454-33.77950904-70.40769273zM458.47446718 158.94109501c-28.76981855-10.11327819-60.29165285 5.00969051-70.40631189 33.77950904-10.11465902 28.76981855 5.00969051 60.29441454 33.77950906 70.40769273 28.76981855 10.11327819 60.29165285-5.00969051 70.40769272-33.77950905 10.11327819-28.76981855-5.00969051-60.29441454-33.78088989-70.40769272zM766.95016451 266.63425163c-28.76981855-10.11742071-60.29165285 5.00554799-70.40769271 33.77950905-10.11327819 28.76981855 5.00969051 60.29027202 33.77950905 70.40769273 28.77119939 10.11327819 60.29441454-5.00969051 70.40769273-33.77950905 10.11327819-28.77396107-5.00969051-60.29441454-33.77950907-70.40769273zM628.77494672 166.0206659c-28.76981855-10.11327819-60.29165285 5.00969051-70.40769273 33.77950905-10.11327819 28.76981855 5.00969051 60.29441454 33.7808899 70.40769273 28.76981855 10.11327819 60.29165285-5.00969051 70.40493104-33.77950905 10.11603987-28.76981855-5.00830966-60.29441454-33.77812821-70.40769273z" p-id="4186"></path></svg></label><input type="text" name="colorthree" id="colorthree" placeholder="渐变色三"></li>')
                    Gradient.rightBar_ul.append(li);
                }
            });


            // 删除渐变色框 
            $('.deletenewcolor').off('click').click(function () {
                if (Gradient.colorbox > 1) {
                    Gradient.colorbox--;
                    $(Gradient.rightBar_ul.children('li')[Gradient.rightBar_ul.children('li').length - 1]).remove();
                    if (Gradient.colorbox == 1) {
                        Gradient.newBackground.colorTwo = '';
                    } else if (Gradient.colorbox == 2) {
                        Gradient.newBackground.colorThree = '';
                    }
                    // 实时处理
                    Gradient.actualTime(Gradient.newBackground, Gradient.colorbox);
                }
            });


            // flag 判断点击的是哪个渐变框
            let flag = 0;
            let li;
            // 防止多次添加 change 事件
            $('.multiply-bar-right ul').off('click').on('click', '.icon', function () {
                li = $(this).parents('li');
                flag = li.index();

                // 防止事件多次添加
                // 通过点击的 li 来给文本框赋值
                $('#colorbar').off('change').change(function () {
                    // 由于网络大神的在 注册事件之前先去除事件，这个方法真是太棒了，没想到，省了好多事，之前通过 li_index 来限制 change 太麻烦了

                    $($('.multiply-bar-right ul li')[flag]).find('input').val(this.value);

                    if (flag == 0) {
                        Gradient.newBackground.colorOne = this.value;
                    } else if (flag == 1) {
                        Gradient.newBackground.colorTwo = this.value;
                    } else if (flag == 2) {
                        Gradient.newBackground.colorThree = this.value;
                    }
                    // 实时处理
                    Gradient.actualTime(Gradient.newBackground, Gradient.colorbox);
                });
            });


            // 用户自己输入
            $('.multiply-bar-right ul').off('blur').on('blur', 'input', function () {
                var lis = $('.multiply-bar-right ul li');
                for (var k = 0; k < Gradient.colorbox; k++) {
                    if (k == 0) {
                        // 判断输入是否规范
                        if (Gradient.colorIsTrue($(lis[k]))) {
                            Gradient.newBackground.colorOne = $(lis[k]).find('input').val();
                        }
                    } else if (k == 1) {
                        if (Gradient.colorIsTrue($(lis[k]))) {
                            Gradient.newBackground.colorTwo = $(lis[k]).find('input').val();
                        }
                    } else if (k == 2) {
                        if (Gradient.colorIsTrue($(lis[k]))) {
                            Gradient.newBackground.colorThree = $(lis[k]).find('input').val();
                        }
                    }
                }
                Gradient.actualTime(Gradient.newBackground, Gradient.colorbox);
            });

            // 每个渐变色输入框得到焦点自动清空
            $('.multiply-bar-right ul').off('focus').on('focus', 'input', function () {
                $(this).val('');
            });


            // 确认修改
            $('.confirmcolor').off('click').click(function () {
                Gradient.isRestoreColor = false;
                Gradient.multiplyBar.fadeOut(300);

                // 如果是单色
                if (Gradient.colorbox == 1) {
                    Gradient.targetGround.css('background-color', Gradient.newBackground.colorOne);

                    // 如果是渐变色
                } else {
                    var background = '-webkit-linear-gradient(' + Gradient.angle + 'deg, ';
                    for (var i = 0; i < Gradient.colorbox; i++) {
                        if (i == 0) {
                            background += Gradient.newBackground.colorOne;
                        } else if (i == 1) {
                            background += ', ' + Gradient.newBackground.colorTwo;
                        } else if (i == 2) {
                            background += ', ' + Gradient.newBackground.colorThree;
                        }
                    }
                    background += ')';
                    Gradient.targetGround.css('background-image', background);
                }


                // 解绑事件及其他处理
                Gradient.tailHandle();

                let pagetwoBackground = $('#pagetwoBackground');
                let pagetwoForeground = $('#pagetwoForeground');
                let cardBackground = $('#cardBackground');

                // 上传更改，0 和 4 比较特殊，不需要确定，点击后就会更改
                if (Gradient.li_index === 1) {
                    browserServer.uploadTitleBackground();
                    /**
                     * 这里解释一下 (Gradient.li_index === 2 && module.inspected) 是为了检验图片，如这里的点击的必须是 页面背景 模块，并且图片大小规范
                     * $('#pagetwoBackground')[0].files.length === 0 而后面的这个是为了让单色或渐变色能通过检查，因为 module.inspected
                     * 是检查文件大小才配置的，单色或渐变色不会更改它的值，附带 typeof pagetwoBackground[0] != 'undefined' 检验是否为 undefined，
                     * 因为每个 input:file 都是点击后添加的
                     */
                    // 上传页面背景
                } else if ((Gradient.li_index === 2 && module.inspected) || (typeof pagetwoBackground[0] != 'undefined' && pagetwoBackground[0].files.length === 0)) {
                    module.inspected = false;
                    browserServer.uploadBackgroundOrForeground(pagetwoBackground, $('.fanjuHouse-pagetwo-background'), "modifyHuadiaoBackground", function (response) {
                        console.log(response.data);
                    });

                    // 上传页面前景
                } else if ((Gradient.li_index === 3 && module.inspected) || (typeof pagetwoForeground[0] != 'undefined' && pagetwoForeground[0].files.length === 0)) {
                    module.inspected = false;
                    browserServer.uploadBackgroundOrForeground(pagetwoForeground, $('.pagetwo-fanjus-bar'), "modifyHuadiaoForeground", function (response) {
                        console.log(response.data);
                    });

                    // 上传卡片背景
                } else if ((Gradient.li_index === 5 && module.inspected) || (typeof cardBackground[0] != 'undefined' && cardBackground[0].files.length === 0)) {
                    module.inspected = false;
                    browserServer.uploadBackgroundOrForeground(cardBackground, allObject.cardBackground, "modifyHuadiaoCardBackground", function (response) {
                        console.log(response.data);
                    });
                }

            })


            // 取消修改，不用担心在点击取消时会将上一个模块的设置设置到该模块，因为每次点击，关键变量都已经加载了点击模块的数据，即已经覆盖
            $('.cancelcolor').off('click').click(function () {
                Gradient.multiplyBar.fadeOut(300);

                // 如果是单色
                if (Gradient.oldImg == 'none') {
                    Gradient.targetGround.css('background-color', Gradient.BackgroundString);

                    // 否则是渐变色或者是图片
                } else {
                    Gradient.targetGround.css('background-image', Gradient.BackgroundString);
                }
                // 解绑事件及其他处理
                Gradient.tailHandle();
            });
        },


        // 点击其他模块时恢复原色，并且点击空白位置恢复
        restoreColor: function () {
            if(Gradient.oldImg == ''){
                return;
            }
            if (Gradient.oldImg == 'none') {
                Gradient.targetGround.css('background-color', Gradient.BackgroundString);

            } else {
                Gradient.targetGround.css('background-image', Gradient.BackgroundString);
            }
        },

        // 确认 取消 结束处理，也运用在了点击其他模块的操作上，这里主要是进行了重置调色面板的操作,还进行了可以点击下一个模块的操作
        tailHandle: function () {

            // 恢复
            $($('.multiply-bar-right ul li')[0]).find('input').val('');
            // 调用 click() 不能用 jquery 对象，否则报错，some
            var lis = $('.multiply-bar-right ul li');
            for (var k = 1; k < lis.length; k++) {
                lis[k].remove();
            }
            Gradient.colorbox = 1;

            // 作者的话
            {
                /**
                 * 这里可以是点击确认或取消后设为 false，点击任何模块，点击的模块都将设为 false，
                 * this.isSame 有连续点击判断，不会造成重复点击，配合点击模块里面设为 true 的语句，碰巧做出来了
                 * 原理：第一次点击时，this.isSame() 只是做了li_index赋值操作，不能连续点击靠的是点击的模块里面的 true 赋值语句
                 *      若点击其他模块，this.isSame() 会将该模块设为 false，也就是可以点击，开始时我觉得有问题，但使用起来又没有毛病
                 *      后来发现设为 true 的模块也只能点击一次，终于发现了是在这里将点击的模块设为可以点击，又由于 this.isSame() 中有判断
                 *      是否点击了同一个模块的语句，所以连续点击同一模块，该语句只执行一次，这就是大概的原理，碰巧做出来的，脑子不太好使，才发现
                 */
            }

            // 在点击下一个模块时，将点击的模块设为 false，但该语句只在点击不同模块时触发
            Gradient.modular.array[Gradient.li_index] = false;
        },

        // 实时处理，background 为 newBackground，如果是图片，并不在这里处理，直接从客户端获取图片进行预览
        actualTime: function (Background, i) {
            var back;
            if (i > 1) {
                // 渐变色

                back = '-webkit-linear-gradient(' + Gradient.angle + 'deg, ';
                for (var j = 0; j < i; j++) {
                    if (j == 0) {
                        back += Background.colorOne;
                    } else if (j == 1 && Background.colorTwo != '') {
                        back += ', ' + Background.colorTwo;
                    } else if (j == 2 && Background.colorThree != '') {
                        back += ', ' + Background.colorThree;
                    }
                }
                back += ')';
                Gradient.targetGround.css('background-image', back).css('background-color', 'transparent');
            } else {
                // 单色
                Gradient.targetGround.css('background-image', 'none').css('background-color', Background.colorOne);
            }
        },

        // 角度是否正确
        angleIsTrue: function () {
            var value = parseInt(allObject.angle.val());

            // value == value 是为了防止其是 NaN，因为 NaN 不等于 NaN
            return value == value && typeof value == 'number' && value >= 0 && value <= 360;
        },

        // 检查颜色是否正确输入，li 为传入的 渐变框 的父级
        colorIsTrue: function (li) {
            var input = li.find('input');
            var value = input.val();
            if (value.search('#') != -1) {
                value = value.split('#')[1];
                if ((value.length == 6 || value.length == 3) && typeof parseInt('0x' + value) == 'number') {
                    return true;
                } else {
                    input.val('错误输入');
                    return false;
                }

            } else {
                input.val('错误输入');
                return false;
            }
        },


        // 文件类型错误提示,参数说明，text 为提示信息，t_one 和 t_two 都是延时的时间
        imgTip: function (text, t_one, t_two) {
            let time_one = 1000;
            let time_two = 3000;
            if (typeof t_one != 'undefined') {
                time_one = t_one;
            }
            if (typeof t_two != 'undefined') {
                time_two = t_two;
            }
            allObject.imgTip.text(text);
            allObject.imgTipBackground.show().stop().animate({
                opacity: 1
            }, time_one, function () {
                setTimeout(function () {
                    allObject.imgTipBackground.stop().animate({
                        opacity: .7
                    }, time_one, function () {
                        $(this).hide();
                    });
                }, time_two);
            })
        }

    };
    // 打开主题商店
    Gradient.openThemeShop();


    // 主题商店所有功能模块
    var module = {

        // 该变量存储从服务端获取到的除番剧外的其他信息
        otherInfer: '',
        // 该变量存储从服务端获取到的番剧信息
        fanjuInfer: '',

        // 该变量用来判断是否要上传页面背景或前景
        inspected: false,

        // 图片边框
        borderImg: {
            // 选择的图片边框
            borderImg_index: '',
            // 存放图片边框的展示框
            ul: $('.borderimg-bar-body ul'),
            className: ['douluodaluborder', 'spy', 'onepieceborder', 'yuanzhikong', 'kenan', ''],
            // 将展示框进行设置
            initial: function () {
                module.borderImg.isload = true;
                // 下面是用于展示效果的图片边框
                for (var i = 0; i < module.borderImg.className.length; i++) {
                    if (module.borderImg.className[i] != '') {
                        // 添加边框
                        module.borderImg.ul.append($('<li></li>'));
                        $(this.ul.children('li')[i]).addClass(module.borderImg.className[i]);
                    } else {
                        // 添加无边框
                        module.borderImg.ul.append($('<li>无</li>'));
                    }
                }
            }
        },

        // 设置标题颜色
        setTitleColor: function () {
            $('.title-color-modular label').click(function () {
                Gradient.isRestoreColor = true;
                Gradient.li_index = 0;
                Gradient.isSame();
                $('#colorbar').off('change').change(function () {
                    $('.pagetwo-header-title').css('color', this.value);
                    browserServer.uploadTitleColor();
                });
            });
        },

        // 设置标题背景颜色
        setTitleBackgroundColor: function () {
            $('.title-background-color-modular').click(function () {
                Gradient.isRestoreColor = true;
                Gradient.li_index = 1;
                // 判断是不是连续点击同一个，不是同一个进行恢复原色，并且恢复渐变板，同时进行了一些初始化操作
                Gradient.isSame();

                // 如果重复点击 return
                if (Gradient.modular.array[Gradient.li_index]) {
                    return;
                }
                Gradient.modular.array[Gradient.li_index] = true;
                Gradient.multiplyBar.children('.multiply-title').text('标题背景');
                Gradient.multiplyBar.children('.more-operation').remove();

                Gradient.multiplyBar.fadeIn(400);

                // 更改颜色的对象，屏二头部
                Gradient.targetGround = $('.pagetwo-header');

                // 使用渐变色，渐变色初始化
                Gradient.colorInitial();

                // 进行渐变色处理
                Gradient.colorOperation();

            });
        },

        // 设置背景
        setPageBackground: function () {
            $('.pagetwo-background-modular').click(function () {
                Gradient.li_index = 2;
                Gradient.isSame();

                if (Gradient.modular.array[Gradient.li_index]) {
                    return;
                }
                Gradient.modular.array[Gradient.li_index] = true;
                Gradient.multiplyBar.children('.multiply-title').text('更换背景');

                Gradient.multiplyBar.fadeIn(400);

                var div = $('<div class="more-operation"><label for="pagetwoBackground" class="oraddimg"><div class="moretip">或者你可以上传图片作为页面背景</div><input type="file" name="pagetwoBackground" id="pagetwoBackground" accept="image/gif,image/jpg,image/jpeg,image/png"></label></div>')
                Gradient.multiplyBar.children('.more-operation').remove();
                Gradient.multiplyBar.append(div);

                // 屏二背景
                Gradient.targetGround = allObject.pagetwoBackground;

                // 使用渐变色，渐变色初始化
                Gradient.colorInitial();

                // 进行渐变色处理
                Gradient.colorOperation();

                $('#pagetwoBackground').off('change').change(function () {

                    if (sizeCorrect($(this))) {
                        module.inspected = true;
                        // 预览图片，这里就不在 Gradient 中进行了，在这里更方便
                        let imgPath = createFanju.getImgPath($(this));
                        allObject.pagetwoBackground.css('background-image', 'url(' + imgPath + ')');
                    } else {
                        Gradient.imgTip("图片大小不能超过2M");
                    }
                });

            });
        },


        // 设置前景
        setPageForeground: function () {
            $('.pagetwo-foreground-modular').click(function () {
                Gradient.li_index = 3;
                Gradient.isSame();

                if (Gradient.modular.array[Gradient.li_index]) {
                    return;
                }
                Gradient.modular.array[Gradient.li_index] = true;
                Gradient.multiplyBar.children('.multiply-title').text('更换前景');

                Gradient.multiplyBar.fadeIn(400);

                var div = $('<div class="more-operation"><label for="pagetwoForeground" class="oraddimg"><div class="moretip">或者你可以上传图片作为页面前景</div><input type="file" name="pagetwoForeground" id="pagetwoForeground" accept="image/gif,image/jpg,image/jpeg,image/png"></label></div>')
                Gradient.multiplyBar.children('.more-operation').remove();
                Gradient.multiplyBar.append(div);

                // 屏二前景
                Gradient.targetGround = $('.pagetwo-fanjus-bar');

                // 使用渐变色，渐变色初始化
                Gradient.colorInitial();

                // 进行渐变色处理
                Gradient.colorOperation();

                $('#pagetwoForeground').off('change').change(function () {

                    if (sizeCorrect($(this))) {
                        module.inspected = true;
                        let imgPath = createFanju.getImgPath($(this));
                        $('.pagetwo-fanjus-bar').css('background-image', 'url(' + imgPath + ')');
                    } else {
                        Gradient.imgTip("图片大小不能超过2M");
                    }
                });

            });
        },

        // 设置图片边框
        setBorderImg: function () {
            $('.borderimg-modular').click(function () {
                Gradient.li_index = 4;
                Gradient.isSame();
                if (Gradient.modular.array[Gradient.li_index]) {
                    return;
                }
                Gradient.modular.array[Gradient.li_index] = true;

                $('.borderimg-bar').fadeIn(200);

                module.borderImg.initial();

                // 选择后添加边框
                module.borderImg.ul.off('click').on('click', 'li', function () {
                    // 获取所有的番剧封面
                    let cover = $('.pagetwo-fanjus-bar ul li .fanju-cover');
                    // 将所有的番剧封面都清除特定类名
                    for (let i = 0; i < module.borderImg.className.length; i++) {
                        // 隐式迭代
                        cover.removeClass(module.borderImg.className[i]);
                    }
                    let index = $(this).index();
                    cover.addClass(module.borderImg.className[index]);
                    // 记录所点击的 index
                    module.borderImg.borderImg_index = index;
                    // 上传
                    browserServer.uploadBorderImgChoice();
                });

                $('.shutdown-icon .icon').off('click').click(function () {
                    $('.borderimg-bar').fadeOut(200);
                    Gradient.modular.array[4] = false;
                });

            });
        },


        // 卡片背景，只修改添加番剧那一张
        setCardBackground: function () {
            $('.card-background-modular').click(function () {
                Gradient.li_index = 5;
                Gradient.isSame();
                if (Gradient.modular.array[Gradient.li_index]) {
                    return;
                }
                Gradient.modular.array[Gradient.li_index] = true;
                Gradient.multiplyBar.children('.multiply-title').text('卡片背景');
                let div = $('<div class="more-operation"><label for="cardBackground" class="oraddimg"><div class="moretip">或者你可以上传图片作为卡片背景</div><input type="file" name="cardBackground" id="cardBackground" accept="image/gif,image/jpg,image/jpeg,image/png"></label></div>')
                Gradient.multiplyBar.children('.more-operation').remove();
                Gradient.multiplyBar.append(div);

                Gradient.multiplyBar.fadeIn(400);

                // 卡片背景，只修改添加番剧那一张
                Gradient.targetGround = allObject.cardBackground;

                // 使用渐变色，渐变色初始化
                Gradient.colorInitial();

                // 进行渐变色处理
                Gradient.colorOperation();

                $('#cardBackground').off('change').change(function () {

                    if (sizeCorrect($(this))) {
                        let imgPath = createFanju.getImgPath($(this));
                        module.inspected = true;
                        allObject.cardBackground.css('background-image', 'url(' + imgPath + ')');
                        // 将影响视觉的添加图标去掉，并加上提示信息
                        $('.addfanju-enter .icon').css('fill', 'transparent');
                        $('.clickToAdd').show();
                    } else {
                        Gradient.imgTip("图片大小不能超过2M");
                    }
                });
            });
        }

    };

    // 更改页面配置
    module.setTitleColor();
    module.setTitleBackgroundColor();
    module.setPageBackground();
    module.setPageForeground();
    module.setBorderImg();
    module.setCardBackground();

    var browserServer = {

        // 获取页面的 uid
        uid: null,

        // 主机地址
        localhost: window.localhost,

        // 主机地址中的图片目录，包含所有用户的图片，虽然这种方式暴露，安全性不高，但目前这是最容易实现的方式了
        localhost_huadiao_house: window.localhost_huadiao_house,

        // 番剧图片保存地址
        localhost_huadiao_fanju: window.localhost_huadiao_fanju,

        // 获取作者 uid
        getAuthorUid() {
            if(!browserServer.uid) {
                browserServer.uid = window.location.href.split(/[\/]/)[5];
            }
            return browserServer.uid;
        },

        // 根据获取的信息初始化除番剧外的页面
        initialOtherPage: function () {
            $('.pagetwo-header-title').css('color', module.otherInfer.huadiaoHouseTitleColor);

            // 添加图片或渐变色或单色
            browserServer.searchSingleColor($('.pagetwo-header'), module.otherInfer.huadiaoHouseTitleBackgroundColor);
            browserServer.searchSingleColor(allObject.pagetwoBackground, module.otherInfer.huadiaoHouseBackground);
            browserServer.searchSingleColor($('.pagetwo-fanjus-bar'), module.otherInfer.huadiaoHouseForeground);
            browserServer.searchSingleColor($('.pagetwo-fanjus-bar ul .addfanju-enter .fanju-cover'), module.otherInfer.buildingCardBackground);
            // 如果是渐变色或单色就显示添加图标，这里以后缀专有的 . 点作为判断依据，并进行转义\\.
            if (typeof module.otherInfer.buildingCardBackground != 'undefined' && module.otherInfer.buildingCardBackground.search('\\.') !== -1) {
                $('.addfanju-enter .icon').css('fill', 'transparent');
                $('.clickToAdd').show();
            }
        },

        // 根据获取的信息初始化番剧
        initialFanju: function () {
            // 第一页的轮播图
            // 标题背景置为空
            $('.pageone-header').css('background-image', 'none');
            let wrapper = $('.swiper-wrapper');
            wrapper.empty();
            // 添加轮播图必要组件
            for (let i = 0; i < module.fanjuInfer.length; i++) {
                let div = $('<div class="swiper-slide swiper-slide-active" data-swiper-slide-index="0"><div></div></div>');
                div.find('div').css('background-image', 'url(' + browserServer.localhost_huadiao_fanju + module.fanjuInfer[i].fanjuCover + ')')
                wrapper.append(div);
            }
            // 轮播图运作
            swiper();


            // 第二页的番剧封面
            for (let i = 0; i < module.fanjuInfer.length; i++) {
                let li = $('<li><div class="fanju-cover"></div><div class="fanju-below"><div class="fanju-information"><div class="fanju-name"></div><div class="add-time"></div></div><div class="fanju-operation"><div class="delete-icon"><svg t="1658305889860" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3964" width="200" height="200"><path d="M268.214857 121.929143C268.214857 68.022857 311.881143 24.356571 365.714286 24.356571h292.571428c53.906286 0 97.572571 43.666286 97.572572 97.572572V219.428571h96.987428a46.811429 46.811429 0 0 1 0.877715 0H950.857143a48.786286 48.786286 0 0 1 0 97.499429h-52.077714l-42.349715 592.091429a97.499429 97.499429 0 0 1-97.28 90.624H264.850286c-51.2 0-93.622857-39.497143-97.28-90.624l-42.276572-592.091429H73.142857A48.786286 48.786286 0 0 1 73.142857 219.428571h97.133714a46.811429 46.811429 0 0 1 0.877715 0h97.060571V121.929143zM365.714286 219.428571h292.571428V121.929143H365.714286V219.428571zM223.085714 316.928l41.691429 585.142857h494.445714l41.691429-585.142857h-577.828572z m191.414857 97.572571c26.916571 0 48.786286 21.796571 48.786286 48.786286v292.571429a48.786286 48.786286 0 1 1-97.572571 0v-292.571429c0-26.989714 21.869714-48.786286 48.786285-48.786286z m195.072 0c26.916571 0 48.713143 21.796571 48.713143 48.786286v292.571429a48.786286 48.786286 0 1 1-97.499428 0v-292.571429c0-26.989714 21.796571-48.786286 48.786285-48.786286z" p-id="3965"></path></svg></div><div class="delete-word">删除该收藏</div></div></div></li>')
                li.find('.fanju-cover').css('background-image', 'url(' + browserServer.localhost_huadiao_fanju + module.fanjuInfer[i].fanjuCover + ')');
                li.find('.fanju-name').text(module.fanjuInfer[i].fanjuName);
                let add_date = module.fanjuInfer[i].buildDate.split(' ');
                let ymd = add_date[0].split('-');
                let hms = add_date[1].split(':');
                li.find('.add-time').text(ymd[0] + '年' + ymd[1] + '月' + ymd[2] + '日' + ' ' + hms[0] + ':' + hms[1]);
                $('.addfanju-enter').before(li);
            }
            // 添加类名，这句本来写在 initialOtherPage 那个函数中，但写在那里不能使每个番剧都戴上图片边框
            $('.pagetwo-fanjus-bar ul .fanju-cover').addClass(module.borderImg.className[parseInt(module.otherInfer.huadiaoHouseCoverBorder)]);

        },


        // 判断是否是单色并赋值
        searchSingleColor: function (obj, color) {
            if (typeof color == 'undefined') {
                return;
            }
            // 判断是否有后缀名专用的 . 点，或者有渐变色专用的角度单位，没有就是单色
            // 注意 . 点在正则表达式中是任何字符，这里为了匹配点，必须转义
            if (color.search('\\.') === -1 && color.search('deg') === -1) {
                obj.css({'background-color': color, 'background-image': 'none'});
            } else if (color.search('deg') !== -1) {
                obj.css('background-image', color);
            } else {
                obj.css('background-image', 'url(' + browserServer.localhost_huadiao_house + color + ')')
            }
        },

        // 番剧封面上传，运用在新建番剧时
        fanjuCoverUpload: function (obj) {
            let o = obj[0];
            let params = {
                requestType: "huadiaoHouse",
                operation: "buildNewFanju",
                fanjuName: createFanju.fanjuName,
                signId: window.getUniqueString(20),
                // fanjuIndex: allObject.pageTwoFanjusBarUl.find('li').length - 1,
            };
            let data = new FormData();
            data.append("fanjuCover", o.files[0]);
            browserServer.browserServerCore(params, {}, data, function (response) {
                console.log(response.data);
                // "番剧添加成功", "番剧添加失败"
            });
        },


        // 上传标题颜色
        uploadTitleColor: function () {
            let titleColor = $('.pagetwo-header-title').css('color');
            let params = {
                requestType: "huadiaoHouse",
                operation: "modifyTitleColor",
                titleColor
            }
            browserServer.browserServerCore(params, {}, null, function (response) {
                console.log(response.data);
                // "标题颜色修改成功", "标题颜色修改失败"
            });
        },

        // 上传标题背景
        uploadTitleBackground: function () {
            browserServer.uploadBackgroundOrForeground(undefined, $('.pagetwo-header'), "modifyTitleBackground", '标题背景修改成功', '标题背景修改失败')
        },

        // 上传页面背景
        /**
         * @param inputImg 存放上传图片的对象
         * @param showImg 展示图片的对象
         * @param module 模块字段
         * @param fn 回调函数
         * 这个函数用于检测上传的类型 -- 单色 -- 渐变色 -- 图片 --
         * 同时用于这四个模块 --页面背景--页面前景--标题背景--卡片背景--
         */
        uploadBackgroundOrForeground: function (inputImg, showImg, module, fn) {
            browserServer.formData = new FormData();
            let data;
            let params = {
                requestType: "huadiaoHouse",
                operation: module,
            };
            if (showImg.css('background-image') === 'none') {
                params.background = showImg.css('background-color');
            } else {
                if (typeof inputImg == 'undefined' || inputImg[0].files.length === 0) {
                    params.background = showImg.css('background-image');
                } else {
                    data = new FormData();
                    data.append("background", inputImg[0].files[0]);
                }
            }

            // 上传
            browserServer.browserServerCore(params, {}, data, fn);
        },


        // 上传图片边框的选择
        uploadBorderImgChoice: function () {
            let params = {
                requestType: "huadiaoHouse",
                operation: "modifyBorderImg",
                borderImgChoice: module.borderImg.borderImg_index
            }
            browserServer.browserServerCore(params, {}, null, function (response) {
                console.log(response.data);
            });
        },

        // 获取页面番剧信息
        getFanjuInfer: function () {
            let params = {
                requestType: "huadiaoHouse",
                operation: "getFanjuInfer",
                authorUid: browserServer.getAuthorUid(),
            };
            browserServer.browserServerCore(params, {}, null, function (response) {
                console.log(response.data);
                if(typeof response.data === "object") {
                    module.fanjuInfer = response.data.huadiaoHouseFanjuInferList;
                    module.otherInfer = response.data;
                    browserServer.initialFanju();
                    browserServer.initialOtherPage();
                }
            })
        },


        // 与服务端通信的核心方法
        browserServerCore: function (params, headers, data = {}, fn) {
            axios({
                url: browserServer.localhost + "dispatcherServlet",
                method: "post",
                params,
                headers,
                data

            }).then(fn);
        }
    };

    // 获取页面信息
    browserServer.getFanjuInfer();

    // 点击空白处关闭页面
    window.onclick = function (e) {

        // 商店面板
        let themeshop = $('.huadiao-fanju-themeshop')[0];
        // 商店图标
        let themeshop_icon = $('.themeshop-icon')[0];


        // 点击位置是否与 themeshop 有关,有关返回 true
        let themeshop_boolean = themeshop.contains(e.target);
        let themeshop_icon_boolean = themeshop_icon.contains(e.target);

        // 如果点击位置找不到面板及其图标，就隐藏
        if (!themeshop_boolean && !themeshop_icon_boolean) {
            JquerySlideLeft(themeshop, 400, $(themeshop).width(), function () {
                $(themeshop).hide();
            });
        }

        // 标题背景模块
        let titleBackground_ = $('.title-background-color-modular')[0];
        // 页面背景模块
        let pagetwoBackground = $('.pagetwo-background-modular')[0];
        // 页面前景模块
        let pagetwoForeground = $('.pagetwo-foreground-modular')[0];
        // 图片边框模块
        let borderImg = $('.borderimg-modular')[0];
        // 卡片背景模块
        let cardBackground = $('.card-background-modular')[0];
        // 渐变面板
        let multiplyBar_ = $('.multiply-bar')[0];
        // 拾色器
        let colorBar = $('#colorbar')[0];
        // 边框选择面板
        let borderImgBar = $('.borderimg-bar')[0];
        let titleBackground_boolean = titleBackground_.contains(e.target);
        let multiplyBar_boolean = multiplyBar_.contains(e.target);
        let pagetwoBackground_boolean = pagetwoBackground.contains(e.target);
        let pagetwoForeground_boolean = pagetwoForeground.contains(e.target);
        let borderImg_boolean = borderImg.contains(e.target);
        let cardBackground_boolean = cardBackground.contains(e.target);
        let colorBar_boolean = colorBar.contains(e.target);
        let borderImgBar_boolean = borderImgBar.contains(e.target);
        // 点击无关位置隐藏渐变板
        if (!multiplyBar_boolean && !titleBackground_boolean && !colorBar_boolean && !pagetwoBackground_boolean && !pagetwoForeground_boolean && !cardBackground_boolean) {
            $(multiplyBar_).fadeOut(400);
            Gradient.tailHandle();
            // 如果是点击确认后就不会恢复
            if (Gradient.isRestoreColor) {
                Gradient.restoreColor();
            }
        }
        if (!borderImg_boolean && !borderImgBar_boolean) {
            $(borderImgBar).fadeOut(200);
            Gradient.modular.array[4] = false;
        }
    }


    var flag = true;

    // jquery 实现左移渐出
    function JquerySlideLeft(obj, time, length, fn) {
        // 对象转换
        obj = $(obj);
        // 判断类型
        if (typeof obj != 'object' || typeof time != 'number' || typeof length != 'number' || typeof fn != 'function') {
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
        if (typeof obj != 'object' || typeof time != 'number' || typeof length != 'number' || typeof fn != 'function') {
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


    // 第一页轮播图
    function swiper() {
        var swiperSlides = $('.swiper-wrapper').children('.swiper-slide');
        if (swiperSlides.length > 1) {
            var swiper = new Swiper('.swiper-container', {
                loop: true,
                autoplay: {
                    delay: 3000,
                    // 用户操作轮播图后是否停止自动播放，默认为 true
                    disableOnInteraction: false
                },
                speed: 2500,
                slidesPerView: 1.5,
                spaceBetween: 30,
                centeredSlides: true,
                watchSlidesProgress: true,
                on: {
                    setTranslate: function () {
                        slides = this.slides
                        for (i = 0; i < slides.length; i++) {
                            slide = slides.eq(i);
                            progress = slides[i].progress;
                            // 初始化
                            slide.css({'opacity': '', 'background': ''});
                            slide.transform('');

                            // 如果是正中间的图片就恢复原样
                            if (Math.abs(progress.toFixed(2)) == 0.00) {
                                slide.transform('scale(' + 1 + ')');

                                // 缓慢换背景
                                $('.fanjuHouse-pageone-background').stop().animate({
                                    opacity: 0.5
                                }, 1250, function () {

                                    // 页面背景换成正中间的 slide 的背景
                                    $(this).css('background', $('.swiper-slide-active div').css('background')).stop().animate({
                                        opacity: 1
                                    }, 1250);
                                });
                            } else {
                                // 如果是其他位置的图片就缩小
                                slide.transform('scale(' + 0.8 + ')');

                            }
                            if (Math.abs(progress.toFixed(2)) == 0.00) {
                                // 这里添加和删除类名来实现 transform 缩放动画
                                $(slide).addClass('expand').removeClass('zoom');
                            } else {
                                $(slide).addClass('zoom').removeClass('expand');

                            }

                        }
                    }

                },
                navigation: {
                    nextEl: '.swiper-button-next',
                    prevEl: '.swiper-button-prev',
                },
                pagination: {
                    el: '.swiper-pagination',
                    clickable: true
                }
            });

        } else if (swiperSlides.length == 1) {
            // 如果只有一张
            $('.swiper-slide').width('66rem').css('margin', '0 auto');

        } else {
            // 一张都没有
            var speciesSlide = $('<div class="swiper-slide swiper-slide-active"></div>');
            speciesSlide.width('66rem').css('margin', '0 auto');
            speciesSlide.text('快把你喜欢的番剧记录下来吧！打造属于你的番剧馆！');
            $('.swiper-wrapper').append(speciesSlide);
        }

    }


})