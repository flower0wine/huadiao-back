$(function () {

    let allObject = {
        myStarRightBelow: $('.myStar-right-below'),
        huangxing: $('.huangxing'),
        shanxing: $('.shanxing'),
        juandai: $('.juandai'),
        giftbox: $('.giftbox'),
        batchProcessingTools: $('.batchProcessing-tools'),
    }

    let browserServer = {

        localhost: window.localhost,

        localhost_user_avatar: window.localhost_user_avatar,

        formData: null,

        noteStars: null,

        // 获取所有收藏的笔记
        getNoteStars: function () {
            let params = {
                requestType: "star",
                operation: "getNoteStar"
            };
            browserServer.browserServerCore(params, {}, null, function (response) {
                console.log(response.data);
                if (typeof response.data[0] == 'object') {
                    browserServer.noteStars = response.data;
                    otherEvent.initialNoteStar();
                    // 默认打开精品收藏夹，写在点击事件下方
                    $('.myStar-left-files li')[0].click();
                }
            });
        },

        browserServerCore: function (params, headers, data, fn) {
            axios({
                url: browserServer.localhost + "dispatcherServlet",
                method: 'post',
                params,
                headers,
                data
            }).then(fn);
        }
    };

    browserServer.getNoteStars();


    let otherEvent = {

        // 初始化收藏面板
        initialNoteStar: function () {
            let starUl = allObject.myStarRightBelow.children('ul');
            starUl.empty();
            if (browserServer.noteStars.length === 0) {
                allObject.myStarRightBelow.addClass('nofile');
            } else {
                browserServer.noteStars.forEach(function (item) {
                    let li = $('<li><a href="javascript:;" target="_blank" title="查看笔记" class="noteLink"><div class="note-title"></div></a><div class="note-abstract"></div><div class="note-below"><div class="starTime"></div><a href="javascript:;" target="_blank" title="" class="homepage"><svg t="1657536982283" class="icon userAvater" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3627"><path d="M510.77050482 2.21923328c281.4240859 0 509.56537287 228.14128697 509.56537173 509.56653682 0 281.42757888-228.14128697 509.56653682-509.56537173 509.56653681-281.42757888 0-509.56886585-228.14012302-509.56886585-509.56653681 0-281.4240859 228.14128697-509.56653682 509.56886585-509.56653682z" fill="#EEEEEE" p-id="3628"></path><path d="M510.77050482 516.51164387c90.0558939 0 163.05952199-73.30168832 163.05952199-163.72549973 0-90.42264633-73.00362809-163.72433465-163.05952199-163.72433465-90.05705785 0-163.06301497 73.30168832-163.06301497 163.72433465-0.00116395 90.42497536 73.00479317 163.72549859 163.06301497 163.72549973z m251.51800206 200.09807531l1.21552441-0.45756757c-39.85964146-100.90130205-137.98992099-172.25861689-252.73352647-172.25861689-113.31967317 0-210.42537358 69.60155875-251.2141221 168.51890176l0.56235463 0.22820181a39.9609344 39.9609344 0 0 0-2.9188881 15.01940622c0 22.15886393 17.97555086 40.12510094 40.15420757 40.12509981 16.79146325 0 31.16701469-10.30401138 37.16196921-24.92522837l0.65433486 0.26545948c28.69987328-68.84360192 96.50958109-117.22355485 175.60014393-117.22355484 79.76934741 0 148.05874461 49.21940878 176.32433493 119.00725475l0.2875813-0.10828003c6.4338944 13.58499499 20.27037696 22.98434901 36.30970538 22.98434901 22.17516373 0 40.15187968-17.96507307 40.15187968-40.12509981a40.14256469 40.14256469 0 0 0-1.55549923-11.05032533z" fill="#CCCCCC" p-id="3629"></path></svg></a><div class="nickName"></div><a href="javascript:;" class="more" title="更多操作"><i class="iconfont">&#xe62d;</i></a><div class="moreBar"><div class="moreBar-in"><div class="moreBar-cancelStar">取消收藏</div><div class="moreBar-copyTo">复制到</div><div class="moreBar-moveTo">移动到</div></div></div></div><div class="choice" value="0"><div></div></div></li>');
                    li.children('.noteLink').prop('href', browserServer.localhost + item.authorUid + '/notes/note?note_id=' + item.noteId).children('.note-title').text(item.noteTitle);
                    let date = item.starDate.split(/[\s-]/);
                    li.find('.starTime').text('收藏于' + date[0] + '年' + date[1] + '月' + date[2] + '日');
                    if (typeof item.userAvatar != 'undefined') {
                        let avatar = $('<div class="avatar-box"><div class="img-avatar"></div></div>');
                        avatar.children('.img-avatar').css('background-image', 'url(' + browserServer.localhost_user_avatar + item.user_avatar + ')')
                        li.find('.homepage').append(avatar).children('svg').remove();
                    }
                    li.find('.homepage').prop('title', item.nickname).prop('href', browserServer.localhost_user_avatar + 'homepage/' + item.authorUid);
                    li.find('.nickName').text(item.nickname);
                    if (item.noteAbstract.length > 40) {
                        li.find('.note-abstract').text(item.noteAbstract.substring(0, 40) + '...');
                    } else {
                        li.find('.note-abstract').text(item.noteAbstract);
                    }
                    starUl.prepend(li);
                });
            }
        },


        // 缓动动画函数
        downStar: function (obj, delayTime) {
            let o = $(obj);
            setTimeout(function () {
                let height = o.height();
                o.stop().animate({
                    top: '0rem',
                }, 130, function () {
                    $(this).stop().animate({
                        top: -height / 48 + 'rem',
                    }, 130, function () {
                        $(this).stop().animate({
                            top: '0rem',
                        }, 130, function () {
                            $(this).stop().animate({
                                top: -height / 80 + 'rem',
                            }, 130, function () {
                                $(this).stop().animate({
                                    top: '0rem',
                                }, 130, function () {
                                    $(this).stop().animate({
                                        top: -height / 160 + 'rem',
                                    }, 130, function () {
                                        $(this).stop().animate({
                                            top: '0rem',
                                        }, 130, function () {
                                            $(this).stop().animate({
                                                top: -height / 320 + 'rem',
                                            }, 130, function () {
                                                $(this).stop().animate({
                                                    top: '0rem',
                                                }, 130);
                                            });
                                        });
                                    });
                                });
                            });
                        });
                    });
                });

            }, delayTime);
        },


        // 打开新建面板
        openCreateBar: function () {
            $('.createFile').click(function () {
                $('.addNewFile').show();
            });
        },

        // 关闭新建面板
        closeCreateBar: function () {
            $('.shutdown').click(function () {
                $('.addNewFile').hide();
            });
        },

        // 点击建立后
        afterClickCreate: function () {
            $('.submit-btn').click(function () {
                let fileName = $('#filename');
                let value = fileName.val();
                // 清空输入框
                fileName.val('');
                $('.fileCanvases-number').text('0/120');
                $('.filename-number').text('0/20');
                $('#fileCanvases').val('');
                // 收藏夹名称长度判断
                if (value.length < 1 || value.length > 20) {
                    filenametip();
                    return false;
                } else {
                    // 克隆
                    let li = $($('.myStar-left-files ul li')[0]).clone(true);
                    if (value.length > 6) {
                        value = value.substring(0, 6) + '...';
                    }
                    // 初始化数据
                    li.find('span').text(value);
                    li.find('em').text(0);
                    let div = $('<div class="deleteFile iconfont">&#xec7b;</div>');
                    li.append(div);
                    // 添加收藏夹
                    $('.myStar-left-files ul').append(li);
                    // 隐藏新建页面
                    $('.addNewFile').hide();
                    // 跳往该收藏夹
                    li.click();
                }

            });
        },

        // 删除收藏夹
        // 这里需要给未来新增的 li 添加事件，使用 on 绑定事件也有固定写法
        deleteStarFile: function () {
            $('.myStar-left-files ul li').on('click', '.deleteFile', function () {
                $('.deleteStarBackground').show().stop().animate({
                    opacity: 1
                }, 400);

                let target = $(this);
                $('.confirmDelete').click(function () {
                    $(target).parents('li').remove();
                    otherEvent.deleteFile();
                    $('.myStar-left-files ul li').last('li').click();

                    // 解绑点击事件，防止事件重叠
                    $('.confirmDelete').off('click');
                });

                $('.cancelDelete').click(function () {
                    otherEvent.deleteFile();

                    // 解绑点击事件，防止事件重叠
                    $('.cancelDelete').off('click');
                });
            });
        },

        deleteFile: function () {
            $('.deleteStarBackground').show().stop().animate({
                opacity: .4
            }, 400, function () {
                $(this).hide();
            });
        },

        // 介绍框字数， keydown 仅支持英文计数,这里先使用 keyup
        canvasesNumber: function () {
            $('#fileCanvases').keyup(function () {
                let fileCanvases = $(this).val();
                if (fileCanvases.length < 120) {
                    $('.fileCanvases-number').text(fileCanvases.length + 1 + '/120');
                }
            });
        },

        // 收藏夹名称计数
        fileStarNumber: function () {
            $('#filename').keyup(function () {
                let fileCanvases = $(this).val();
                if (fileCanvases.length < 20) {
                    $('.filename-number').text(fileCanvases.length + 1 + '/20');
                }
            });
        },

        // 取消收藏——单个处理
        cancelStar: function () {
            let lis = $('.myStar-left-files li');
            // 设置收藏数量
            $(lis[index]).find('em').text(lis.length);

            // 如果收藏夹为空就显示背景
            if (browserServer.noteStars.length === 0) {
                allObject.myStarRightBelow.addClass('nofile');
            } else {
                allObject.myStarRightBelow.removeClass('nofile');
            }
        },

        filesTools: function () {
            let choseLength = 0;
            // 批量处理
            $('.cancel').click(function () {
                allObject.batchProcessingTools.hide();
                $('.batchProcessing-btn').show().attr('value', '1');
                $('.myStar-right-below ul li').find('.choice').removeClass('choice-class');
            });
            $('.batchProcessing-btn').click(function () {
                let this__ = $(this);
                allObject.batchProcessingTools.show();
                this__.hide();
                this__.attr('value', '1');

                // 逐个选择
                $('.choice-class').click(function () {
                    let this_ = $(this);
                    let value = this_.attr('value');
                    if (value == '0') {
                        this_.children('div').css('background-color', 'rgba(124, 23, 255, 0.452)');
                        this_.parents('li').find('.choice').addClass('choice-class');
                        this_.attr('value', '1');
                        choseLength++;
                    } else {
                        this_.children('div').css('background-color', '');
                        this_.parents('li').find('.choice').removeClass('choice-class');
                        this_.attr('value', '0');
                        choseLength--;
                    }

                });
            });

            // 全选
            $('#allChoice').change(function () {
                let value = $(this).prop('checked');
                if (value) {
                    $('.myStar-right-below ul li').find('.choice').addClass('choice-class');
                    $('.choice-class div').css('background-color', 'rgba(124, 23, 255, 0.452)').parent('.choice-class').attr('value', '1');
                } else {
                    $('.myStar-right-below ul li').find('.choice').removeClass('choice-class');
                    $('.choice-class div').css('background-color', '').parent('.choice-class').attr('value', '0');
                }
            });

            // 取消收藏——批量处理
            $($('.batchProcessing-tools ul li')[1]).click(function () {
                let lis = $('.myStar-right-below ul li');

                // 将 lis 遍历检查
                for (let i = 0; i < lis.length; i++) {
                    if ($(lis[i]).find('.choice-class').attr('value') == '1') {
                        $($(lis)[i]).remove();  /* 删除 */
                    }
                }

                otherEvent.cancelStar();
            });

            let singlecopy = 0;
            allObject.myStarRightBelow.on('click', '.moreBar-copyTo', function () {
                singlecopy = 1;
                $(this).parents('li').children('.choice').attr('value', '1');
                $('.copyTo').click();
            });

            let singlemove = 0;
            allObject.myStarRightBelow.on('click', '.moreBar-moveTo', function () {
                singlemove = 1;
                $(this).parents('li').children('.choice').attr('value', '1');
                $('.moveTo').click();
            });


            // 复制到
            $('.copyTo').click(function () {
                if (choseLength > 0 || singlecopy == '1') {
                    singlecopy = 0;
                    $('.copyMove-background').find('.copyMoveBar-title').text('将此笔记复制到');
                    copymove();
                    $(this).attr('copy', '1');
                }
            });

            // 移动到
            $('.moveTo').click(function () {
                if (choseLength > 0 || singlemove == '1') {
                    singlemove = 0;
                    $('.copyMove-background').find('.copyMoveBar-title').text('将此笔记移动到');
                    copymove();
                    $(this).attr('move', '1');
                }
            });

            // 复制 移动 ，复制收藏夹名称到指定位置
            function copymove() {
                let src = $('.myStar-left-files ul li');
                let ul = $('.copyMoveBar-body ul');
                ul.empty(); /* 清空 */


                for (let i = 0; i < src.length; i++) {
                    let filename = $(src[i]).children('span').text();
                    // 模版
                    let li = $('<li><div class="folderName" value="0">' + filename + '</div></li>');
                    ul.append(li);  /* 添加 */
                }
                // 显示面板
                $('.copyMove-background').show();
            }


            // 复制或移动笔记收藏夹，选择存放收藏夹，必须用 on()
            $('.starFolders ul').on('click', 'li', function () {
                let folder = $(this).children('.folderName');


                if (folder.attr('value') == '0') {

                    $(this).css('background-color', 'rgb(218, 218, 218)').siblings('li').css('background-color', '').children('.folderName').attr('value', '0');
                    folder.attr('value', '1');
                    $('.confirm-btn').css('background-color', 'rgb(30, 146, 224)');

                } else if (folder.attr('value') == '1') {

                    $(this).css('background-color', '');
                    folder.attr('value', '0');
                    $('.confirm-btn').css('background-color', 'rgb(172, 172, 172)');
                }

            });

            // 进行复制或移动
            $('.confirm-btn').click(function () {

                let move = $('.moveTo').attr('move');
                let copy = $('.copyTo').attr('copy');
                let tarlis = $('.starFolders ul li');
                let targetIndex;
                // 找到选中的收藏夹
                for (let i = 0; i < tarlis.length; i++) {
                    if ($(tarlis[i]).children('.folderName').attr('value') == '1') {
                        targetIndex = i;
                        $(tarlis[i]).children('.folderName').attr('value', '0');
                        break;
                    }
                }
                // 将要复制或移动的全部找出
                let srclis = $('.myStar-right-below ul li');
                let srcIndexs = [];
                for (let i = 0; i < srclis.length; i++) {
                    if ($(srclis[i]).children('.choice').attr('value') == '1') {
                        srcIndexs.push(i);
                    }
                }

                if (move == '1' && copy == '0') {     // 移动

                    for (let i = 0; i < srcIndexs.length; i++) {

                        let li = $(srclis[srcIndexs[i]]).clone(true);  /* 深拷贝 */
                        let em = $($('.myStar-left-files ul li')[targetIndex]).children('em');
                        $(srclis[srcIndexs[i]]).remove();   /* 原本的删除 */
                        $(srclis).parents('ul').append(li);
                        if (srcIndexs[i] != targetIndex) {
                            em.text(parseInt(em.text()) + 1);
                        }
                        choseLength--;
                    }

                } else if (move == '0' && copy == '1') {  // 复制

                    for (let i = 0; i < srcIndexs.length; i++) {

                        let li = $(srclis[srcIndexs[i]]).clone(true);  /* 深拷贝 */
                        let em = $($('.myStar-left-files ul li')[targetIndex]).children('em');
                        $(srclis).parents('ul').append(li);
                        em.text(parseInt(em.text()) + 1);
                        choseLength--;
                    }
                }
                let choice = $('.choice');
                // 恢复，这段代码写在 关闭代码下面执行不了
                choice.children('div').css('background-color', '');
                choice.attr('value', '0');
                // 自动关闭
                $('.shutdown').click();

            });

            // 关闭移动 复制页面
            $('.shutdown').click(function () {
                $('.copyMove-background').hide();
                $('.moveTo').attr('move', '0');
                $('.copyTo').attr('copy', '0');
            });


            // 点击变色，其他恢复原色
            $('.myStar-left-files li').click(function () {
                $(this).css('background-color', 'rgba(178, 133, 252, 0.274)').siblings('li').css('background-color', '');
                // 没有收藏显示背景
                if (browserServer.noteStars.length === 0) {
                    $('.myStar-right-below').addClass('nofile');
                } else {
                    $('.myStar-right-below').removeClass('nofile');
                }
            });

            file(allObject.huangxing, 0);
            file(allObject.juandai, 1);
            file(allObject.shanxing, 2);
            file(allObject.giftbox, 3);

            function file(obj, index) {
                let o = $(obj);
                o.click(function () {
                    $($('.myStar-left-files ul li')[index]).click();
                });
            }
        }
    };

    otherEvent.openCreateBar();
    otherEvent.closeCreateBar();
    otherEvent.afterClickCreate();
    otherEvent.deleteStarFile();
    otherEvent.filesTools();


    otherEvent.canvasesNumber();
    otherEvent.fileStarNumber();


    // 将收藏计数
    let index = 0;
    $($('.myStar-left-files li')[index]).find('em').text($('.myStar-right-below ul li').length);


    allObject.myStarRightBelow.on('click', '.moreBar-cancelStar', function () {
        $(this).parents('li').remove();
        otherEvent.cancelStar();
    });


    let oldTargetIndex;
    let newTargetIndex;
    let same;

    // 更多操作
    allObject.myStarRightBelow.on('click', '.more', function () {
        newTargetIndex = $(this).parents('li').index();

        $($('.myStar-right-below ul li')[newTargetIndex]).find('.moreBar').show();
    });

    // 点击空白处关闭页面
    window.onclick = function (e) {
        same = oldTargetIndex;
        let moreBar = $($('.myStar-right-below ul li')[oldTargetIndex]).children('.note-below').find('.moreBar')[0];
        let more = $(moreBar).siblings('.more')[0];

        // 点击位置是否与 morebar 有关,有关返回 true
        let flag = e.path.some(item => item == moreBar);
        // 点击位置是否与 more 有关
        flag = e.path.some(item => item == more);


        if (!flag) {
            $(moreBar).hide();
        }
        oldTargetIndex = newTargetIndex;

    }


    // 礼物盒
    setTimeout(function () {
        allObject.giftbox.stop().animate({
            bottom: -80 / 16 + 'rem'
        }, 2500, function () {
            $(this).stop().animate({
                bottom: 69 / 16 + 'rem'
            }, 200);
        });

    }, 2500);

    // 上面装饰
    otherEvent.downStar(allObject.juandai, 2000);

    otherEvent.downStar(allObject.shanxing, 2500);

    otherEvent.downStar(allObject.huangxing, 2300)


    // 收藏夹名提示
    function filenametip() {
        // 先显示做动画
        $('.filenametip').show().stop().animate({
            opacity: 1
        }, 1000, function () {
            // 延时 500 毫秒让用户看清
            setTimeout($(this).stop().animate({
                opacity: .3
            }, 1000, function () {
                // 之后再隐藏
                $(this).hide();
            }), 500);
        });
    }


})