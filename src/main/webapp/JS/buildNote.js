$(function () {

    var allObject = {
        release: $('.release'),
        huadiaoEditorTitle: $('.huadiao-editor-title'),
        titleNumber: $('.title-number'),
        noteContentNumber: $('.note-content-number'),
        wordColor: $('.word-color'),
    }

    var browserServer = {

        localhost: window.localhost,

        formData: null,

        // 提交笔记
        uploadNote: function (K, editor) {
            allObject.release.click(function () {
                if (otherEvent.checkTitleWord()) {
                    // 标题
                    let noteTitle = allObject.huadiaoEditorTitle.val();
                    // HTML 内容
                    let noteHTMLContent = editor.html();
                    // 摘要
                    let noteAbstract = editor.text().substring(0, 150);
                    browserServer.formData = new FormData();
                    browserServer.formData.append("noteTitle", noteTitle);
                    browserServer.formData.append("noteContent", noteHTMLContent);
                    browserServer.formData.append("noteAbstract", noteAbstract);
                    browserServer.formData.append("buildNote", "");
                    let data = {
                        requestType: "note",
                        operation: "releaseNote",
                        noteTitle,
                        noteContent: noteHTMLContent,
                        noteAbstract
                    }
                    browserServer.browserServerCore(data);
                }
            });
        },

        // axios 上传封装
        browserServerCore: function (data) {
            axios({
                url: browserServer.localhost + 'dispatcherServlet',
                method: 'post',
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded; charset=urf-8"
                },
                data

            }).then(function (response) {
                let temp = response.data;
                if(temp == 'uploadSucceed'){
                    otherEvent.showTip('发布成功');
                } else if(temp == 'error'){
                    otherEvent.showTip('发布失败');
                }
            });
        },
    };


    var otherEvent = {

        // 字数样式变化确认按钮
        wordCSS: false,

        wordColor: {
            zero: 0,
            exceedOneHundred: 100,
            exceedTwoHundred: 200,
            exceedThreeHundred: 300,
            exceedFourHundred: 400,
            exceedFiveHundred: 500,
            exceedSevenHundred: 700,
            exceedThousand: 1000,
            exceedThousandThree: 1300,
            exceedThousandSix: 1600,
            exceedTwoThousand: 2000,
            exceedTwoThousandFour: 2400,
            exceedTwoThousandEight: 2800,
            exceedThreeThousandTwo: 3200
        },

        // 加载文本编辑器
        loadKindEditor: function () {

            KindEditor.ready(function (K) {

                // 配置编辑器参数
                let options = {
                    width: '100%',
                    // 这里在源码基础上做了修改
                    // themePath 为文件目录路径，
                    themesPath: browserServer.localhost + 'CSS/',
                    // themeType 为自定义编辑器的 css 风格
                    themeType: 'simple',
                    // 插件目录路径
                    pluginsPath: browserServer.localhost + 'JS/kindeditorPlugins/',
                    // 表情路径
                    emoticonsPath: browserServer.localhost + 'images/kindeditorEmoticons/emoticons/',
                    // 预览表情
                    allowPreviewEmoticons: true,
                    // 远程浏览图片
                    allowFileManager: true,
                    // 自动调整高度
                    autoHeightMode: true,
                    afterCreate: function () {
                        this.loadPlugin('autoheight');
                    },
                    afterChange: function () {
                        // 计算笔记内容字数
                        let wordNumber = this.count('text');
                        allObject.noteContentNumber.text('共 ' + wordNumber + ' 个字');
                        if (otherEvent.wordCSS) {
                            // 当内容字数超过特定数字时，给予样式变化
                            let before = null;
                            let after = null;
                            for (let number in otherEvent.wordColor) {
                                if (wordNumber > otherEvent.wordColor[number]) {
                                    before = number;
                                } else {
                                    after = number;
                                    break;
                                }
                            }
                            allObject.noteContentNumber[0].classList.remove(allObject.noteContentNumber[0].classList.item(1));
                            if (wordNumber === otherEvent.wordColor[after]) {
                                allObject.noteContentNumber.addClass(after);
                            } else {
                                allObject.noteContentNumber.addClass(before);
                            }
                        }
                    },
                    // 设置不能改变编辑器大小
                    resizeType: 0,
                    // 编辑器面板右击无菜单
                    useContextmenu: false,
                }
                let huadiao_editor = K.create('.huadiao-editor', options);

                // 这里是为了获取笔记内容
                browserServer.uploadNote(K, huadiao_editor);

            });

        },


        // 计算标题字数
        calcTitleNumber: function () {
            allObject.huadiaoEditorTitle.keyup(function () {
                let length = $(this).val().length;
                if (length > 100 || length < 5) {
                    allObject.titleNumber.text($(this).val().length + '/100').css('color', '#b63838');
                } else {
                    allObject.titleNumber.text($(this).val().length + '/100').css('color', '#868686');
                }

            });
        },


        // 检查标题字数
        checkTitleWord: function () {
            let length = allObject.huadiaoEditorTitle.val().length;
            if (length > 100 || length < 5) {
                otherEvent.showTip('标题字数要求在 5 ~ 100 个字');
                return false;
            }
            return true;
        },

        // 提示信息
        showTip: function (tip, t1, t2) {
            let tipBox = $('.tip');
            let time1 = 300;
            let time2 = 1200;
            if (typeof t1 == 'number') {
                time1 = t1;
            }
            if (typeof t2 == 'number') {
                time2 = t2;
            }
            tipBox.text(tip).show().animate({
                opacity: 1
            }, time1, function () {
                setTimeout(function () {
                    tipBox.animate({
                        opacity: 0
                    }, time1, function () {
                        tipBox.hide()
                    });
                }, time2)
            })
        },

        // 用户填写数据后，刷新页面时给予提示
        reloadPageTip: function () {
            window.addEventListener("beforeunload", function (e) {
                let confirmationMessage = "你确定要离开吗？";
                (e || window.event).returnValue = confirmationMessage; // 兼容 Gecko + IE
                return confirmationMessage; // 兼容 Gecko + Webkit, Safari, Chrome
            });
        },

        // 鼠标滚动到特定位置时改变发布按钮位置
        changeReleasePosition: function () {
            window.addEventListener('scroll', function () {
                let scrollTop = window.scrollY;
                if (scrollTop > 300) {
                    allObject.release.addClass('changePosition').removeClass('release')
                } else {
                    allObject.release.addClass('release').removeClass('changePosition')
                }
            });
        },

        // 字数颜色变化
        wordColorButton: function () {
            allObject.wordColor.off('click').click(function () {
                // 如果打开就关闭
                if (otherEvent.wordCSS) {
                    otherEvent.wordCSS = false;
                    allObject.noteContentNumber[0].classList.remove(allObject.noteContentNumber[0].classList.item(1));
                    allObject.wordColor.text('open').addClass('word-color-close').removeClass('word-color-open');
                    otherEvent.showTip('关闭字数颜色变化');
                } else {
                    otherEvent.wordCSS = true;
                    allObject.wordColor.text('close').addClass('word-color-open').removeClass('word-color-close');
                    otherEvent.showTip('成功开启字数颜色变化，当字数到达指定字数时，字数颜色就会产生相应变化', 400, 2500);
                }
            });
        }
    };
    otherEvent.loadKindEditor();
    otherEvent.calcTitleNumber();
    otherEvent.reloadPageTip();
    otherEvent.changeReleasePosition();
    otherEvent.wordColorButton();


});
