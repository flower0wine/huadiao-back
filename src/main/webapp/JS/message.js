$(function () {

    var allObject = {
        replyMeBodyUl: $('.replyMe-body ul'),
        lastestMessageBodyUl: $('.lastestMessage-body ul'),
        messageBodyLeftUlLis: $('.message-body-left ul li'),
        systemMessageBodyUl: $('.systemMessage-body ul'),
        likeBodyUl: $('.like-body ul'),
        systemMessageNumber: $('.systemMessage-number'),
        replyMelikeNumber: $('.replyMelike-number'),
        privateMessageNumber: $('.privateMessage-number'),
        replyMeNumber: $('.replyMe-number'),
        messageRemind: $('.messageRemind'),
        messageReply: $('.messageReply'),
        messageLikes: $('.messageLikes'),
        replyMeReplyBarBackground: $('.replyMe-replyBar-background'),
        Chatwindow: $('.Chatwindow'),
        sendMessage: $('.sendMessage'),
        privateMessage: $('#privateMessage'),
        msgList: $('.msg-list'),
        latestMessageBodyUlLis: null,
    };

    var module = {

        otherAvatar: $('<svg t="1657842780645" class="otherAvater" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3669"><path d="M510.77050482 2.21923328c281.4240859 0 509.56537287 228.14128697 509.56537173 509.56653682 0 281.42757888-228.14128697 509.56653682-509.56537173 509.56653681-281.42757888 0-509.56886585-228.14012302-509.56886585-509.56653681 0-281.4240859 228.14128697-509.56653682 509.56886585-509.56653682z" fill="#EEEEEE" p-id="3670"></path><path d="M510.77050482 516.51164387c90.0558939 0 163.05952199-73.30168832 163.05952199-163.72549973 0-90.42264633-73.00362809-163.72433465-163.05952199-163.72433465-90.05705785 0-163.06301497 73.30168832-163.06301497 163.72433465-0.00116395 90.42497536 73.00479317 163.72549859 163.06301497 163.72549973z m251.51800206 200.09807531l1.21552441-0.45756757c-39.85964146-100.90130205-137.98992099-172.25861689-252.73352647-172.25861689-113.31967317 0-210.42537358 69.60155875-251.2141221 168.51890176l0.56235463 0.22820181a39.9609344 39.9609344 0 0 0-2.9188881 15.01940622c0 22.15886393 17.97555086 40.12510094 40.15420757 40.12509981 16.79146325 0 31.16701469-10.30401138 37.16196921-24.92522837l0.65433486 0.26545948c28.69987328-68.84360192 96.50958109-117.22355485 175.60014393-117.22355484 79.76934741 0 148.05874461 49.21940878 176.32433493 119.00725475l0.2875813-0.10828003c6.4338944 13.58499499 20.27037696 22.98434901 36.30970538 22.98434901 22.17516373 0 40.15187968-17.96507307 40.15187968-40.12509981a40.14256469 40.14256469 0 0 0-1.55549923-11.05032533z" fill="#CCCCCC" p-id="3671"></path></svg>')
    }


    var browserServer = {

        formData: null,

        //主机地址
        localhost: window.localhost,

        // 图片地址
        localhost_user_avatar: window.localhost_user_avatar,

        replyMeArray: [],

        privateMessageArray: [],

        likeArray: [],

        systemArray: [],

        messageSettings: null,

        // 最近日期
        latestDate: null,

        // 私信发送对象
        privarySendUid: null,

        // websocket
        ws: null,

        // 建立 websocket 连接
        buildWebsocket: function () {

            browserServer.ws = new WebSocket('ws://localhost:9090/huadiao/webSocket');

            browserServer.ws.onopen = function () {
                console.log('建立连接')
            };

            browserServer.ws.onerror = function () {
                console.log('发生错误')
            };

            browserServer.ws.onclose = function () {
                console.log('连接关闭')
            };

            browserServer.ws.onmessage = function (e) {
                otherEvent.showOtherMessage(JSON.parse(e.data));
            };

            this.sendMessage();
        },

        // 私信发送消息
        sendMessage: function () {
            allObject.sendMessage.click(function () {
                let val = allObject.privateMessage.html();
                allObject.privateMessage.html("");
                if (val !== '') {
                    // 在消息窗口展示发送的消息
                    otherEvent.showSendMessage(val);
                    $('.wordNumber').text(+'0/300');
                    console.log('发送' + val)
                    browserServer.ws.send(otherEvent.messageToJSON(val));
                }
            })
        },

        // 获取回复我的消息
        getReplyMeMessage: function () {
            let data = {
                requestType: "message",
                operation: "getReplyMessage",
            };
            browserServer.browserServerCore(data, function (response) {
                console.log(response.data);
                if (typeof response.data === "object") {
                    browserServer.replyMeArray = response.data;
                    otherEvent.initialReplyMessage();
                }
            });
        },

        // 获取笔记点赞信息
        getNoteMarkLikeMessage() {
            let data = {
                requestType: "message",
                operation: "getNoteMarkLike"
            };
            browserServer.browserServerCore(data, function (response) {
                console.log(response.data)
                if (typeof response.data === 'object') {
                    browserServer.likeArray = response.data;
                    otherEvent.initialLikeMessage();
                }
            });
        },

        // 获取系统消息
        getSystemMessage() {
            let data = {
                requestType: "message",
                operation: "getSystemMessage"
            };
            browserServer.browserServerCore(data, function (response) {
                console.log(response.data);
                if (typeof response.data === 'object') {
                    browserServer.systemArray = response.data;
                    otherEvent.initialSystemMessage();
                }
            })
        },

        // 获取信息设置
        getMessageSettings() {
            let data = {
                requestType: "userSettings",
                operation: "getMessageSettings"
            };
            browserServer.browserServerCore(data, function (response) {
                console.log(response.data);
                if (typeof response.data === 'object') {
                    browserServer.messageSettings = response.data;
                    otherEvent.initialMessageSettings();
                }
            })
        },

        // 获取私信信息
        getPrivateMessage() {
            let data = {
                requestType: "message",
                operation: "getPrivateMessage"
            };
            browserServer.browserServerCore(data, function (response) {
                console.log(response.data);
                if (typeof response.data === "object") {
                    browserServer.privateMessageArray = response.data;
                    otherEvent.initialPrivacyMessage();
                }
            })
        },

        browserServerCore: function (data, fn) {
            axios({
                url: browserServer.localhost + "dispatcherServlet",
                method: 'post',
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded; charset=utf-8"
                },
                data
            }).then(fn)
        }
    };

    browserServer.buildWebsocket();
    browserServer.getMessageSettings()
    browserServer.getReplyMeMessage();
    browserServer.getPrivateMessage();
    browserServer.getNoteMarkLikeMessage();
    browserServer.getSystemMessage();

    var otherEvent = {

        // 记录私信点击对象数组下标
        index: -1,

        // 初始化系统消息
        initialSystemMessage: function () {
            allObject.systemMessageBodyUl.empty();
            browserServer.systemArray.forEach((item) => {
                let li = $('<li><div class="authority-avater"><div class="authority-logo"></div><div class="authority-background"></div></div><div class="authority-content"><div class="authority-content-header"><div class="authority-content-title"></div><div class="authority-sendTime"></div></div><div class="authority-messageContent"></div></div></li>');
                li.find('.authority-content-title').text(item.messageTitle);
                let date = item.buildDate.split(/[\s-:]/);
                li.find('.authority-sendTime').text(date[0] + '年' + date[1] + '月' + date[2] + '日 ' + date[3] + ':' + date[4]);
                li.find('.authority-messageContent').text(item.message_content);
                allObject.systemMessageBodyUl.append(li);
            });
            otherEvent.newMessageNumber(browserServer.systemArray.length, allObject.systemMessageNumber);
        },

        // 初始化回复我的消息
        initialReplyMessage: function () {
            allObject.replyMeBodyUl.empty();
            browserServer.replyMeArray.forEach(function (item) {
                let li = $('<li><div class="replyMe-avater"><a href="javascript:;" class="homepage" target="_blank" title=""><svg t="1657842780645" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3669"><path d="M510.77050482 2.21923328c281.4240859 0 509.56537287 228.14128697 509.56537173 509.56653682 0 281.42757888-228.14128697 509.56653682-509.56537173 509.56653681-281.42757888 0-509.56886585-228.14012302-509.56886585-509.56653681 0-281.4240859 228.14128697-509.56653682 509.56886585-509.56653682z" fill="#EEEEEE" p-id="3670"></path><path d="M510.77050482 516.51164387c90.0558939 0 163.05952199-73.30168832 163.05952199-163.72549973 0-90.42264633-73.00362809-163.72433465-163.05952199-163.72433465-90.05705785 0-163.06301497 73.30168832-163.06301497 163.72433465-0.00116395 90.42497536 73.00479317 163.72549859 163.06301497 163.72549973z m251.51800206 200.09807531l1.21552441-0.45756757c-39.85964146-100.90130205-137.98992099-172.25861689-252.73352647-172.25861689-113.31967317 0-210.42537358 69.60155875-251.2141221 168.51890176l0.56235463 0.22820181a39.9609344 39.9609344 0 0 0-2.9188881 15.01940622c0 22.15886393 17.97555086 40.12510094 40.15420757 40.12509981 16.79146325 0 31.16701469-10.30401138 37.16196921-24.92522837l0.65433486 0.26545948c28.69987328-68.84360192 96.50958109-117.22355485 175.60014393-117.22355484 79.76934741 0 148.05874461 49.21940878 176.32433493 119.00725475l0.2875813-0.10828003c6.4338944 13.58499499 20.27037696 22.98434901 36.30970538 22.98434901 22.17516373 0 40.15187968-17.96507307 40.15187968-40.12509981a40.14256469 40.14256469 0 0 0-1.55549923-11.05032533z" fill="#CCCCCC" p-id="3671"></path></svg></a></div><div class="replyMeBody-content"><div class="reply-header"><div class="nickname"></div><div class="reply-mymark">回复了我的评论</div></div><a href="javascript:;" class="note-detail" target="_blank" title="查看详情"><div class="reply-content"></div></a><div class="reply-below"><div class="replyTime"></div><div class="replyTools"><div class="replyOthers"><i class="iconfont">&#xe776;</i><span>回复</span></div><div class="like" value="0"><i class="iconfont">&#xe601;</i><span>点赞</span></div><div class="replydelete"><i class="iconfont">&#xec7b;</i><span>删除该通知</span></div></div></div></div></li>')
                let avatar = $('<div class="reply-avatar-box"><div class="img-avatar"></div></div>');
                avatar.find('.img-avatar').css('background-image', 'url(' + browserServer.localhost_user_avatar + item.userAvatar + ')');
                li.find('.homepage').prop('href', browserServer.localhost + 'homepage/' + item.uid).prop('title', item.nickname).append(avatar).children('svg').remove();
                li.find('.note-detail').prop('href', browserServer.localhost + item.uid + '/notes/note?note_id=' + item.noteId);
                if (item.nickname.length < 20) {
                    li.find('.nickname').text(item.nickname);
                } else {
                    li.find('.nickname').text(item.nickname.substring(0, 20) + '...');
                }
                if (item.markContent.length < 170) {
                    li.find('.reply-content').text(item.markContent);
                } else {
                    li.find('.reply-content').text(item.markContent.substring(0, 170) + '...');
                }
                let date = item.markDate.split(/[\s-:]/);
                li.find('.replyTime').text(date[0] + '年' + date[1] + '月' + date[2] + '日 ' + date[3] + ':' + date[4]);
                if (item.likeStatus === browserServer.messageSettings.uid) {
                    li.find('.like').css('color', '#58bceb').attr('value', '1');
                } else {
                    li.find('.like').css('color', '#4b4b4b').attr('value', '0');
                }

                allObject.replyMeBodyUl.append(li);
            });
            otherEvent.newMessageNumber(browserServer.replyMeArray.length, allObject.replyMeNumber);
        },

        // 初始化点赞消息
        initialLikeMessage: function () {

            let ul = allObject.likeBodyUl;
            let li;
            let number = 0;
            ul.empty();
            browserServer.likeArray.forEach(function (item) {
                number += item.likeMarkList.length;
                // 多人
                if (item.likeMarkList.length > 1) {
                    li = $('<li><div class="likemessage-left"><div class="like-avaters"><a href="javascript:;" class="homepage" target="_blank" title=""><svg t="1657842780645" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3669"><path d="M510.77050482 2.21923328c281.4240859 0 509.56537287 228.14128697 509.56537173 509.56653682 0 281.42757888-228.14128697 509.56653682-509.56537173 509.56653681-281.42757888 0-509.56886585-228.14012302-509.56886585-509.56653681 0-281.4240859 228.14128697-509.56653682 509.56886585-509.56653682z" fill="#EEEEEE" p-id="3670"></path><path d="M510.77050482 516.51164387c90.0558939 0 163.05952199-73.30168832 163.05952199-163.72549973 0-90.42264633-73.00362809-163.72433465-163.05952199-163.72433465-90.05705785 0-163.06301497 73.30168832-163.06301497 163.72433465-0.00116395 90.42497536 73.00479317 163.72549859 163.06301497 163.72549973z m251.51800206 200.09807531l1.21552441-0.45756757c-39.85964146-100.90130205-137.98992099-172.25861689-252.73352647-172.25861689-113.31967317 0-210.42537358 69.60155875-251.2141221 168.51890176l0.56235463 0.22820181a39.9609344 39.9609344 0 0 0-2.9188881 15.01940622c0 22.15886393 17.97555086 40.12510094 40.15420757 40.12509981 16.79146325 0 31.16701469-10.30401138 37.16196921-24.92522837l0.65433486 0.26545948c28.69987328-68.84360192 96.50958109-117.22355485 175.60014393-117.22355484 79.76934741 0 148.05874461 49.21940878 176.32433493 119.00725475l0.2875813-0.10828003c6.4338944 13.58499499 20.27037696 22.98434901 36.30970538 22.98434901 22.17516373 0 40.15187968-17.96507307 40.15187968-40.12509981a40.14256469 40.14256469 0 0 0-1.55549923-11.05032533z" fill="#CCCCCC" p-id="3671"></path></svg></a><a href="javascript:;" class="homepage" target="_blank" title=""><svg t="1657842780645" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3669"><path d="M510.77050482 2.21923328c281.4240859 0 509.56537287 228.14128697 509.56537173 509.56653682 0 281.42757888-228.14128697 509.56653682-509.56537173 509.56653681-281.42757888 0-509.56886585-228.14012302-509.56886585-509.56653681 0-281.4240859 228.14128697-509.56653682 509.56886585-509.56653682z" fill="#EEEEEE" p-id="3670"></path><path d="M510.77050482 516.51164387c90.0558939 0 163.05952199-73.30168832 163.05952199-163.72549973 0-90.42264633-73.00362809-163.72433465-163.05952199-163.72433465-90.05705785 0-163.06301497 73.30168832-163.06301497 163.72433465-0.00116395 90.42497536 73.00479317 163.72549859 163.06301497 163.72549973z m251.51800206 200.09807531l1.21552441-0.45756757c-39.85964146-100.90130205-137.98992099-172.25861689-252.73352647-172.25861689-113.31967317 0-210.42537358 69.60155875-251.2141221 168.51890176l0.56235463 0.22820181a39.9609344 39.9609344 0 0 0-2.9188881 15.01940622c0 22.15886393 17.97555086 40.12510094 40.15420757 40.12509981 16.79146325 0 31.16701469-10.30401138 37.16196921-24.92522837l0.65433486 0.26545948c28.69987328-68.84360192 96.50958109-117.22355485 175.60014393-117.22355484 79.76934741 0 148.05874461 49.21940878 176.32433493 119.00725475l0.2875813-0.10828003c6.4338944 13.58499499 20.27037696 22.98434901 36.30970538 22.98434901 22.17516373 0 40.15187968-17.96507307 40.15187968-40.12509981a40.14256469 40.14256469 0 0 0-1.55549923-11.05032533z" fill="#CCCCCC" p-id="3671"></path></svg></a></div></div><div class="likemessage-right"><span class="name-filed"><a href="javascript:;"></a><span class="dunhao">、</span><a href="javascript:;"></a><span class="person"></span></span><div class="likemessage-right-below"><div class="likeTime"></div><div class="deleteLike"><i class="iconfont">&#xec7b;</i><span class="deleteNotice">删除该通知</span></div></div></div></li>')

                    if (typeof item.likeMarkList[0].userAvatar != 'undefined') {
                        let avatar = $('<div class="avatar-box-above"><div class="more-img-avatar"></div></div>');
                        avatar.children('.more-img-avatar').css('background-image', 'url(' + browserServer.localhost_user_avatar + item.likeMarkList[0].userAvatar + ')')
                        li.find('a:eq(0)').append(avatar).children('svg').remove();
                    }
                    if (typeof item.likeMarkList[1].userAvatar != 'undefined') {
                        let avatar = $('<div class="avatar-box-below"><div class="more-img-avatar"></div></div>');
                        avatar.children('.more-img-avatar').css('background-image', 'url(' + browserServer.localhost_user_avatar + item.likeMarkList[1].userAvatar + ')')
                        li.find('a:eq(1)').append(avatar).children('svg').remove();
                    }
                    li.find('.like-avaters').children('a:eq(0)').prop('title', item.likeMarkList[0].nickname).prop('href', browserServer.localhost + 'homepage/' + item.likeMarkList[0].uid);
                    li.find('.like-avaters').children('a:eq(1)').prop('title', item.likeMarkList[1].nickname).prop('href', browserServer.localhost + 'homepage/' + item.likeMarkList[1].uid);
                    if (item.likeMarkList[0].nickname.length > 17) {
                        li.find('.name-filed').children('a:eq(0)').prop('href', browserServer.localhost + 'homepage/' + item.likeMarkList[0].uid).prop('title', item.likeMarkList[0].nickname).text(item.likeMarkList[0].nickname.substring(0, 17) + '...');
                    } else {
                        li.find('.name-filed').children('a:eq(0)').prop('href', browserServer.localhost + 'homepage/' + item.likeMarkList[0].uid).prop('title', item.likeMarkList[0].nickname).text(item.likeMarkList[0].nickname);
                    }
                    if (item.likeMarkList[1].nickname.length > 17) {
                        li.find('.name-filed').children('a:eq(1)').prop('href', browserServer.localhost + 'homepage/' + item.likeMarkList[1].uid).prop('title', item.likeMarkList[1].nickname).text(item.likeMarkList[1].nickname.substring(0, 17) + '...');
                    } else {
                        li.find('.name-filed').children('a:eq(1)').prop('href', browserServer.localhost + 'homepage/' + item.likeMarkList[1].uid).prop('title', item.likeMarkList[1].nickname).text(item.likeMarkList[1].nickname);
                    }
                    li.find('.name-filed').children('.person').text('等 ' + item.likeMarkList.length + ' 人赞了我的评论');
                    let date = item.likeMarkList[0].likedDate.split(/[\s-:]/);
                    li.find('.likeTime').text(date[0] + '年' + date[1] + '月' + date[2] + '日');

                    // 单人
                } else {

                    li = $('<li><div class="likemessage-left"><div class="like-avater"><a href="javascript:;" class="homepage" target="_blank" title=""><svg t="1657842780645" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3669"><path d="M510.77050482 2.21923328c281.4240859 0 509.56537287 228.14128697 509.56537173 509.56653682 0 281.42757888-228.14128697 509.56653682-509.56537173 509.56653681-281.42757888 0-509.56886585-228.14012302-509.56886585-509.56653681 0-281.4240859 228.14128697-509.56653682 509.56886585-509.56653682z" fill="#EEEEEE" p-id="3670"></path><path d="M510.77050482 516.51164387c90.0558939 0 163.05952199-73.30168832 163.05952199-163.72549973 0-90.42264633-73.00362809-163.72433465-163.05952199-163.72433465-90.05705785 0-163.06301497 73.30168832-163.06301497 163.72433465-0.00116395 90.42497536 73.00479317 163.72549859 163.06301497 163.72549973z m251.51800206 200.09807531l1.21552441-0.45756757c-39.85964146-100.90130205-137.98992099-172.25861689-252.73352647-172.25861689-113.31967317 0-210.42537358 69.60155875-251.2141221 168.51890176l0.56235463 0.22820181a39.9609344 39.9609344 0 0 0-2.9188881 15.01940622c0 22.15886393 17.97555086 40.12510094 40.15420757 40.12509981 16.79146325 0 31.16701469-10.30401138 37.16196921-24.92522837l0.65433486 0.26545948c28.69987328-68.84360192 96.50958109-117.22355485 175.60014393-117.22355484 79.76934741 0 148.05874461 49.21940878 176.32433493 119.00725475l0.2875813-0.10828003c6.4338944 13.58499499 20.27037696 22.98434901 36.30970538 22.98434901 22.17516373 0 40.15187968-17.96507307 40.15187968-40.12509981a40.14256469 40.14256469 0 0 0-1.55549923-11.05032533z" fill="#CCCCCC" p-id="3671"></path></svg></a></div></div><div class="likemessage-right"><span class="name-filed"><a href="javascript:;"></a><span class="person"></span></span><div class="likemessage-right-below"><div class="likeTime"></div><div class="deleteLike"><i class="iconfont">&#xec7b;</i><span class="deleteNotice">删除该通知</span></div></div></div></li>')
                    if (typeof item.likeMarkList[0].user_avatar != 'undefined') {
                        let avatar = $('<div class="single-avatar-box"><div class="single-img-avatar"></div></div>');
                        avatar.children('.single-img-avatar').css('background-image', 'url(' + browserServer.localhost_user_avatar + item.likeMarkList[0].userAvatar + ')')
                        li.find('.homepage').prop('title', item.likeMarkList[0].nickname).append(avatar).children('svg').remove();
                    }
                    li.find('.homepage').prop('href', browserServer.localhost + 'homepage/' + item.likeMarkList[0].uid);
                    li.find('.name-filed').children('a').prop('href', browserServer.localhost + 'homepage/' + item.likeMarkList[0].uid).text(item.likeMarkList[0].nickname);
                    li.find('.name-filed').children('.person').text('赞了我的评论');
                    let date = item.likeMarkList[0].likedDate.split(/[\s-:]/);
                    li.find('.likeTime').text(date[0] + '年' + date[1] + '月' + date[2] + '日');
                }
                ul.append(li);
            });
            otherEvent.newMessageNumber(number, allObject.replyMelikeNumber);
        },


        // 初始化消息设置
        initialMessageSettings: function () {
            otherEvent.setSliderCSS(allObject.messageRemind, browserServer.messageSettings.messageRemindStatus);
            otherEvent.setSliderCSS(allObject.messageReply, browserServer.messageSettings.messageReplyStatus);
            otherEvent.setSliderCSS(allObject.messageLikes, browserServer.messageSettings.messageLikeStatus);

            // 初始化回复输入框，自己有头像添加头像
            if (typeof browserServer.messageSettings.userAvatar != 'undefined') {
                let avatar = $('<div class="mine-avatar-box"><div class="img-avatar"></div></div>');
                avatar.children('.img-avatar').css('background-image', 'url(' + browserServer.localhost_user_avatar + browserServer.messageSettings.userAvatar + ')')
                allObject.replyMeReplyBarBackground.find('.avaters').prepend(avatar).children('.myAvater').remove();
            }
        },

        // 初始化私信信息
        initialPrivacyMessage: function () {
            allObject.lastestMessageBodyUl.empty();
            browserServer.privateMessageArray.forEach(function (item) {
                let li = $('<li><div class="lastestMessage"><svg t="1657842780645" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3669"><path d="M510.77050482 2.21923328c281.4240859 0 509.56537287 228.14128697 509.56537173 509.56653682 0 281.42757888-228.14128697 509.56653682-509.56537173 509.56653681-281.42757888 0-509.56886585-228.14012302-509.56886585-509.56653681 0-281.4240859 228.14128697-509.56653682 509.56886585-509.56653682z" fill="#EEEEEE" p-id="3670"></path><path d="M510.77050482 516.51164387c90.0558939 0 163.05952199-73.30168832 163.05952199-163.72549973 0-90.42264633-73.00362809-163.72433465-163.05952199-163.72433465-90.05705785 0-163.06301497 73.30168832-163.06301497 163.72433465-0.00116395 90.42497536 73.00479317 163.72549859 163.06301497 163.72549973z m251.51800206 200.09807531l1.21552441-0.45756757c-39.85964146-100.90130205-137.98992099-172.25861689-252.73352647-172.25861689-113.31967317 0-210.42537358 69.60155875-251.2141221 168.51890176l0.56235463 0.22820181a39.9609344 39.9609344 0 0 0-2.9188881 15.01940622c0 22.15886393 17.97555086 40.12510094 40.15420757 40.12509981 16.79146325 0 31.16701469-10.30401138 37.16196921-24.92522837l0.65433486 0.26545948c28.69987328-68.84360192 96.50958109-117.22355485 175.60014393-117.22355484 79.76934741 0 148.05874461 49.21940878 176.32433493 119.00725475l0.2875813-0.10828003c6.4338944 13.58499499 20.27037696 22.98434901 36.30970538 22.98434901 22.17516373 0 40.15187968-17.96507307 40.15187968-40.12509981a40.14256469 40.14256469 0 0 0-1.55549923-11.05032533z" fill="#CCCCCC" p-id="3671"></path></svg></div><div class="lastestMessage-information"><div class="lastestMessage-nickname"></div><div class="lastestMessage-canvases"></div><div class="newLastestMessage" title=""></div></div><div class="deletePrivateObj"><div class="iconfont deletePrivateObjicon">&#xe723;</div></div></li>')
                if (typeof item.userAvatar != 'undefined') {
                    let avatar = $('<div class="latest-avatar-box"><div class="latest-avatar"></div></div>');
                    avatar.find('.latest-avatar').css('background-image', 'url(' + browserServer.localhost_user_avatar + item.userAvatar + ')');
                    li.find('.lastestMessage').append(avatar).children('svg').remove();
                }
                li.find('.lastestMessage-canvases').prop('title', item.canvases);

                // 判断昵称长度
                if (item.nickname.length > 10) {
                    li.find('.lastestMessage-nickname').text(item.nickname.substring(0, 10) + '...');
                } else {
                    li.find('.lastestMessage-nickname').text(item.nickname);
                }
                // 判断个人介绍长度
                if (item.canvases.length > 11) {
                    li.find('.lastestMessage-canvases').text(item.canvases.substring(0, 11) + '...');
                } else {
                    li.find('.lastestMessage-canvases').text(item.canvases);
                }
                allObject.lastestMessageBodyUl.append(li);
            });
            allObject.latestMessageBodyUlLis = $('.lastestMessage-body ul li');
            otherEvent.distinctionPrivacyUser();
            // 默认点击私信页面打开第一个私聊对象的页面
            allObject.lastestMessageBodyUl.find('li')[0].click();
        },

        setSliderCSS: window.setSliderCSS,

        slide: window.slide,

        // 回复那些回复我的评论
        replyMeBack: function () {
            allObject.replyMeBodyUl.on('click', '.replyOthers', function () {
                let index = $(this).parents('li').index();
                allObject.replyMeReplyBarBackground.show().find('.other-avatar-box').remove();
                if (typeof browserServer.replyMeArray[index].user_avatar != 'undefined') {
                    let avatar = $('<div class="other-avatar-box"><div class="img-avatar"></div></div>');
                    avatar.children('.img-avatar').css('background-image', 'url(' + browserServer.localhost_user_avatar + browserServer.replyMeArray[index].user_avatar + ')');
                    allObject.replyMeReplyBarBackground.find('.avaters').append(avatar).children('.otherAvater').remove();
                } else {
                    allObject.replyMeReplyBarBackground.find('.avaters').append(module.otherAvatar);
                }
                otherEvent.sendReplyMessage(index);
            });
        },

        getUniqueString: window.getUniqueString,

        // 发布评论
        sendReplyMessage: function (index) {
            $('.replyMeBack-btn').off('click').click(function () {
                allObject.replyMeReplyBarBackground.hide();
                let comment = $('#replyMeBack').val();
                let subMarkId = otherEvent.getUniqueString(20);
                let data = {
                    requestType: "note",
                    operation: "sendComment",
                    authorUid: browserServer.replyMeArray[index].authorUid,
                    markedUid: browserServer.replyMeArray[index].uid,
                    noteId: browserServer.replyMeArray[index].noteId,
                    rootMarkId: browserServer.replyMeArray[index].rootMarkId,
                    subMarkId,
                    comment,
                };
                browserServer.browserServerCore(data, function (response) {
                    console.log(response.data);
                });
            });
        },


        // 关闭回复面板
        shutdownReplyBar: function () {
            $('.shutdown').click(function () {
                $('.replyMe-replyBar-background').hide();
            });
        },


        // 给别人的评论点赞
        giveLikeForMark: function () {
            allObject.replyMeBodyUl.on('click', '.like', function () {
                let this_ = $(this);
                let index = this_.parents('li').index();
                let value = this_.attr('value');
                if (value == '0') {
                    this_.css('color', '#58bceb').attr('value', '1');
                } else if (value == '1') {
                    this_.css('color', '#4b4b4b').attr('value', '0');
                }
                otherEvent.uploadLikeStatus(browserServer.replyMeArray[index].uid, browserServer.replyMeArray[index].rootMarkId, browserServer.replyMeArray[index].subMarkId, browserServer.replyMeArray[index].noteId);
            });
        },

        // 上传点赞状态
        uploadLikeStatus: function (otherUid, rootMarkId, subMarkId, noteId, authorUid) {
            let data = {
                requestType: "note",
                operation: "noteCommentLike",
                authorUid,
                noteId,
                markedUid: otherUid,
                rootMarkId,
            };
            if (subMarkId) {
                data.subMarkId = subMarkId;
            }
            browserServer.browserServerCore(data, function (response) {
                console.log(response.data);
            });
        },


        // 删除别人对我的回复
        deleteReplyForMe: function () {
            allObject.replyMeBodyUl.on('click', '.replydelete', function () {

                let targetli = $(this).parents('li');
                let index = targetli.index();
                $('.deleteMarkBackground').show();
                $('.confirmDelete').off('click').click(function () {
                    targetli.remove();
                    $('.deleteMarkBackground').hide();

                    // 向服务端发送删除信息
                    browserServer.formData = new FormData();
                    browserServer.formData.append('otherUid', browserServer.replyMeArray[index].uid);
                    browserServer.formData.append('noteId', browserServer.replyMeArray[index].noteId);
                    browserServer.formData.append('rootMarkId', browserServer.replyMeArray[index].rootMarkId);
                    browserServer.formData.append('deleteReplyMessage', '');
                    let data = {
                        requestType: "message",
                        operation: "deleteReply",
                        noteId: browserServer.replyMeArray[index].noteId,
                        rootMarkId: browserServer.replyMeArray[index].rootMarkId,
                        subMarkId: browserServer.replyMeArray[index].subMarkId,
                        markUid: browserServer.replyMeArray[index].uid
                    };
                    browserServer.browserServerCore(data, function (response) {
                        console.log(response.data);
                    });

                    // 删除数组相应元素，这里注意 JavaScript 数组是可变数组
                    browserServer.replyMeArray.splice(index, 1);
                    if (browserServer.replyMeArray.length === 0) {
                        $('.replyMe-body').css('background', 'url(../images/nomessage.png) no-repeat 13.0625rem 8.75rem');
                    }
                });
                $('.cancelDelete').off('click').click(function () {
                    $('.deleteMarkBackground').hide();
                });

            });
        },


        // 删除收到的赞提醒
        deleteOtherLike: function () {
            allObject.likeBodyUl.on('click', '.deleteNotice', function () {

                let targetli = $(this).parents('li');
                let index = targetli.index();
                $('.deleteMarkBackground').show();
                $('.confirmDelete').off('click').click(function () {
                    // 同时移除元素
                    targetli.remove();
                    $('.deleteMarkBackground').hide();

                    browserServer.formData = new FormData();
                    browserServer.formData.append('noteId', browserServer.likeArray[index].note_id);
                    browserServer.formData.append('rootMarkId', browserServer.likeArray[index].root_mark_id);
                    browserServer.formData.append('subMarkId', browserServer.likeArray[index].sub_self_id);
                    browserServer.formData.append('deleteMarkLikeMessage', '');
                    let data = {
                        requestType: "message",
                        operation: "deleteLikeNotice",
                        noteId: browserServer.likeArray[index].noteId,
                        rootMarkId: browserServer.likeArray[index].rootMarkid,
                        subMarkId: browserServer.likeArray[index].subMarkId,
                    };
                    browserServer.browserServerCore(data, function (response) {
                        console.log(response.data);
                    });

                    // 清除数组相应元素
                    browserServer.likeArray.splice(targetli.index(), 1);
                    if (browserServer.likeArray.length === 0) {
                        $('.like-body').css('background', 'url(../images/nomessage.png) no-repeat 13.0625rem 8.75rem');
                    }
                });
                $('.cancelDelete').off('click').click(function () {
                    $('.deleteMarkBackground').hide();
                });
            });
        },


        // 删除最近联系人及其聊天记录
        deleteLastestChat: function () {
            allObject.lastestMessageBodyUl.on('click', '.deletePrivateObj', function () {
                let li = $(this).parents('li');
                let index = li.index();
                let otherUid = browserServer.privateMessageArray[index].uid;

                // 删除数组相应元素
                browserServer.privateMessageArray.splice(index, 1);
                li.remove();

                let data = {
                    requestType: "message",
                    operation: "deleteLatestChat",
                    chatUid: otherUid,
                };
                browserServer.browserServerCore(data, function (response) {
                    console.log(response.data)
                });
            });
        },


        // 打开不同消息面板
        openMessageBar: function () {
            allObject.messageBodyLeftUlLis.click(function () {
                let index = $(this).index();
                $($('.messageType div')[index]).show().siblings('div').hide();
                $($('.message-content>div')[index]).show().siblings('div').hide();
                // 设置消息已读
                if (index === 0) {
                    allObject.replyMeNumber.hide();
                    /*let data = {
                        requestType: "message",
                        operation: "read"
                    };
                    browserServer.browserServerCore(data, function (response) {
                        console.log(response.data)
                    });*/
                } else if (index === 2) {
                    allObject.replyMelikeNumber.hide();
                } else if (index === 3) {
                    allObject.systemMessageNumber.hide();
                }
            });
            // 默认打开回复我的
            allObject.messageBodyLeftUlLis[0].click();
        },

        // 打开消息设置
        openMessageSettings: function () {
            $('.meassage-settings').click(function () {
                $('.meassage-settings-title').show().siblings('div').hide();
                $('.meassage-settings-body').show().siblings('div').hide();
            });
        },


        // 设置消息数量
        newMessageNumber: function (number, assignedTarget) {
            let targetObj = $(assignedTarget);
            if (number === 0) {
                targetObj.hide();
            }
            targetObj.text(number);
        },

        // 添加滚动条
        addScrollBar: function (target) {
            target.slimScroll({
                height: 'calc(81.343vh - 12.75rem)',
                size: '7px', //组件宽度
                color: '#656565', //滚动条颜色
                start: 'bottom', //默认滚动位置：top/bottom
                disableFadeOut: false, //是否 鼠标经过可滚动区域时显示组件，离开时隐藏组件
                railVisible: true, //是否 显示轨道
                railColor: '#b6b6b6', //轨道颜色
                railOpacity: .2, //轨道透明度
                railDraggable: true, //是否 滚动条可拖动
                allowPageScroll: true, //是否 使用滚轮到达顶端/底端时，滚动窗口
            });
        },

        // 私信消息发送字数计数
        calcPrivacyWordNumber: function () {
            $('#privateMessage').keyup(function () {
                let text = $(this).text();
                $('.wordNumber').text(text.length + '/300');
            });
        },

        // 点击不同的私信对象
        distinctionPrivacyUser: function () {
            allObject.lastestMessageBodyUl.on('click', 'li', function () {

                let this_ = $(this);
                otherEvent.index = this_.index();
                // 点击不同的聊天对象
                this_.parents('.privateMeassage-body').find('.communicationNickname').text(browserServer.privateMessageArray[otherEvent.index].nickname);
                this_.css('background-color', '#d8d8d87a').siblings('li').css('background-color', '');
                this_.find('.newLastestMessage').hide();

                // 初始化与该对象的聊天信息
                allObject.msgList.empty();
                let currentTime = new Date();
                let chatUser = browserServer.privateMessageArray[otherEvent.index];
                // 点击对象的 uid
                browserServer.privarySendUid = chatUser.uid;
                let msgMore = $('<div class="msg-more"><span class="msg-no-more">没有更多消息了~</span></div>');
                allObject.msgList.append(msgMore);
                if (chatUser.chatContentList.length !== 0) {
                    let avatar;
                    let sessionContent;
                    let uid;

                    for (let i = 0; i < chatUser.chatContentList.length; i++) {
                        if (i === 0) {
                            // 第一条消息必须有时间
                            allObject.msgList.append(otherEvent.setDate(chatUser.chatContentList[0].sendDate, currentTime, true));
                        }
                        let srcLi = chatUser.chatContentList[i];
                        uid = srcLi.sendUid;
                        // 如果两个消息相差小于等于三分钟，就不显示发送时间
                        if (i > 0 && otherEvent.calcDifferDate(chatUser.chatContentList[i - 1].sendDate, srcLi.sendDate, 60000) > 3) {
                            allObject.msgList.append(otherEvent.setDate(srcLi.sendDate, currentTime, true));
                        }
                        // 消息内容
                        let msgItem = $('<div class="msg-item"><div class="msg-avatar-box"><a href="javascript:void(0);" target="_blank" class="msg-avatar"><svg t="1657842780645" class="icon" viewBox="0 0 1024 1024"version="1.1"xmlns="http://www.w3.org/2000/svg" p-id="3669"> <pathd="M510.77050482 2.21923328c281.4240859 0 509.56537287 228.14128697 509.56537173 509.56653682 0 281.42757888-228.14128697 509.56653682-509.56537173 509.56653681-281.42757888 0-509.56886585-228.14012302-509.56886585-509.56653681 0-281.4240859 228.14128697-509.56653682 509.56886585-509.56653682z"fill="#EEEEEE" p-id="3670"></path><path d="M510.77050482 516.51164387c90.0558939 0 163.05952199-73.30168832 163.05952199-163.72549973 0-90.42264633-73.00362809-163.72433465-163.05952199-163.72433465-90.05705785 0-163.06301497 73.30168832-163.06301497 163.72433465-0.00116395 90.42497536 73.00479317 163.72549859 163.06301497 163.72549973z m251.51800206 200.09807531l1.21552441-0.45756757c-39.85964146-100.90130205-137.98992099-172.25861689-252.73352647-172.25861689-113.31967317 0-210.42537358 69.60155875-251.2141221 168.51890176l0.56235463 0.22820181a39.9609344 39.9609344 0 0 0-2.9188881 15.01940622c0 22.15886393 17.97555086 40.12510094 40.15420757 40.12509981 16.79146325 0 31.16701469-10.30401138 37.16196921-24.92522837l0.65433486 0.26545948c28.69987328-68.84360192 96.50958109-117.22355485 175.60014393-117.22355484 79.76934741 0 148.05874461 49.21940878 176.32433493 119.00725475l0.2875813-0.10828003c6.4338944 13.58499499 20.27037696 22.98434901 36.30970538 22.98434901 22.17516373 0 40.15187968-17.96507307 40.15187968-40.12509981a40.14256469 40.14256469 0 0 0-1.55549923-11.05032533z" fill="#CCCCCC" p-id="3671"></path></svg></a></div><div class="msg-content"></div></div>')
                        if (typeof (avatar = chatUser.user_avatar) != 'undefined') {
                            sessionContent = srcLi.sessionContent;
                            // 判断消息是我发送的消息
                            if (uid != browserServer.messageSettings.uid) {
                                msgItem.find('.msg-avatar-box').addClass('not-me-avatar').find('.msg-avatar').css('background-image', 'url(' + browserServer.localhost_user_avatar + avatar + ')').find('svg').remove();
                                msgItem.find('.msg-content').addClass('not-me').html(sessionContent);
                            } else {
                                msgItem.find('.msg-avatar-box').addClass('me-avatar').find('.msg-avatar').css('background-image', 'url(' + browserServer.localhost_user_avatar + browserServer.messageSettings.user_avatar + ')').find('svg').remove();
                                msgItem.find('.msg-content').addClass('me').html(sessionContent);
                            }
                        }
                        msgItem.find('.msg-avatar').prop('href', browserServer.localhost + 'homepage/' + uid);
                        allObject.msgList.append(msgItem);
                    }
                    // 记录最近一次时间
                    browserServer.privateMessageArray[otherEvent.index].latestDate = chatUser.chatContentList[chatUser.chatContentList.length - 1].sendDate;
                }
                otherEvent.addScrollBar(allObject.Chatwindow);
                $('.privateMessage-number');
            });
        },


        // 判断消息的发送时间与现在的时间的差值是否在一两天之前，并设置时间为今天、昨天或前天, initial 为是否初始化时使用, 类型为 boolean
        setDate: function (sendDate, currentTime, initial) {
            let msgTime = $('<div class="msg-time"><span class="time"></span></div>');
            let number = Math.floor(otherEvent.calcDifferDate(sendDate, currentTime, 86400000));//86400000 为一天的毫秒数
            let date;
            if (initial) {
                date = sendDate.split(/[\s:]/);
            } else {
                date = currentTime.split(/[\s:]/);
            }
            if (number === 0) {
                msgTime.find('.time').text(date[1] + ':' + date[2]);
            } else if (number === 1) {
                msgTime.find('.time').text('昨天 ' + date[1] + ':' + date[2]);
            } else if (number === 2) {
                msgTime.find('.time').text('前天 ' + date[1] + ':' + date[2]);
            } else if (number > 2) {
                msgTime.find('.time').html(date[0] + ' ' + date[1] + ':' + date[2]);
            }
            return msgTime;
        },


        // 计算两个日期相差几分钟, time 为毫秒数
        calcDifferDate: function (preDate, afterDate, time) {
            let afterTime;
            if (!(afterDate instanceof Date)) {
                afterTime = new Date(Date.parse(afterDate.replace('-', '/')));
            } else {
                afterTime = afterDate;
            }
            let preTime = new Date(Date.parse(preDate.replace('-', '/')));
            return (afterTime - preTime) / time;
        },


        // 在客户端展示发送的消息
        showSendMessage: function (message) {
            let currentTime = window.getCurrentTime();
            let msgItem = $('<div class="msg-item"><div class="msg-avatar-box"><a href="javascript:void(0);" target="_blank" class="msg-avatar"><svg t="1657842780645" class="icon" viewBox="0 0 1024 1024"version="1.1"xmlns="http://www.w3.org/2000/svg" p-id="3669"> <pathd="M510.77050482 2.21923328c281.4240859 0 509.56537287 228.14128697 509.56537173 509.56653682 0 281.42757888-228.14128697 509.56653682-509.56537173 509.56653681-281.42757888 0-509.56886585-228.14012302-509.56886585-509.56653681 0-281.4240859 228.14128697-509.56653682 509.56886585-509.56653682z"fill="#EEEEEE" p-id="3670"></path><path d="M510.77050482 516.51164387c90.0558939 0 163.05952199-73.30168832 163.05952199-163.72549973 0-90.42264633-73.00362809-163.72433465-163.05952199-163.72433465-90.05705785 0-163.06301497 73.30168832-163.06301497 163.72433465-0.00116395 90.42497536 73.00479317 163.72549859 163.06301497 163.72549973z m251.51800206 200.09807531l1.21552441-0.45756757c-39.85964146-100.90130205-137.98992099-172.25861689-252.73352647-172.25861689-113.31967317 0-210.42537358 69.60155875-251.2141221 168.51890176l0.56235463 0.22820181a39.9609344 39.9609344 0 0 0-2.9188881 15.01940622c0 22.15886393 17.97555086 40.12510094 40.15420757 40.12509981 16.79146325 0 31.16701469-10.30401138 37.16196921-24.92522837l0.65433486 0.26545948c28.69987328-68.84360192 96.50958109-117.22355485 175.60014393-117.22355484 79.76934741 0 148.05874461 49.21940878 176.32433493 119.00725475l0.2875813-0.10828003c6.4338944 13.58499499 20.27037696 22.98434901 36.30970538 22.98434901 22.17516373 0 40.15187968-17.96507307 40.15187968-40.12509981a40.14256469 40.14256469 0 0 0-1.55549923-11.05032533z" fill="#CCCCCC" p-id="3671"></path></svg></a></div><div class="msg-content"></div></div>')
            let latestDate;
            if ((latestDate = browserServer.privateMessageArray[otherEvent.index].latestDate) != null) {
                // 两条信息间隔时间太短就不加时间
                if (otherEvent.calcDifferDate(latestDate, currentTime, 60000) > 3) {
                    allObject.msgList.append(otherEvent.setDate(latestDate, currentTime, false));
                }
            } else {
                // 第一条信息
                let msgTime = $('<div class="msg-time"><span class="time"></span></div>');
                let date = currentTime.split(/[\s:]/);
                msgTime.find('.time').text(date[1] + ':' + date[2]);
                allObject.msgList.append(msgTime);
            }
            msgItem.find('.msg-avatar-box').addClass('me-avatar').find('.msg-avatar').css('background-image', 'url(' + browserServer.localhost_user_avatar + browserServer.messageSettings.user_avatar + ')').find('svg').remove();
            msgItem.find('.msg-content').addClass('me').html(message);
            msgItem.find('.msg-avatar').prop('href', browserServer.localhost + 'homepage/' + browserServer.messageSettings.uid);
            allObject.msgList.append(msgItem);
            browserServer.privateMessageArray[otherEvent.index].latestDate = currentTime;
            browserServer.privateMessageArray[otherEvent.index].chatContentList.push({
                'sendDate': currentTime,
                'sendUid': browserServer.messageSettings.uid,
                'sessionContent': message
            });
            allObject.Chatwindow.slimScroll();
        },

        // 将对方发送的消息展示在窗口上
        showOtherMessage: function (obj) {
            let currentTime = window.getCurrentTime();
            let index;
            for (index = 0; index < browserServer.privateMessageArray.length; index++) {
                let srcLi = browserServer.privateMessageArray[index];
                if (srcLi.uid == obj.sendUid) {
                    srcLi.chatContentList.push({
                        'sendDate': obj.sendDate,
                        'sendUid': obj.sendUid,
                        'sessionContent': obj.content
                    });
                    break;
                }
            }
            // 如果当前点击的窗口是发送消息的用户
            if (browserServer.privateMessageArray[otherEvent.index].uid == obj.sendUid) {
                let msgItem = $('<div class="msg-item"><div class="msg-avatar-box"><a href="javascript:void(0);" target="_blank" class="msg-avatar"><svg t="1657842780645" class="icon" viewBox="0 0 1024 1024"version="1.1"xmlns="http://www.w3.org/2000/svg" p-id="3669"> <pathd="M510.77050482 2.21923328c281.4240859 0 509.56537287 228.14128697 509.56537173 509.56653682 0 281.42757888-228.14128697 509.56653682-509.56537173 509.56653681-281.42757888 0-509.56886585-228.14012302-509.56886585-509.56653681 0-281.4240859 228.14128697-509.56653682 509.56886585-509.56653682z"fill="#EEEEEE" p-id="3670"></path><path d="M510.77050482 516.51164387c90.0558939 0 163.05952199-73.30168832 163.05952199-163.72549973 0-90.42264633-73.00362809-163.72433465-163.05952199-163.72433465-90.05705785 0-163.06301497 73.30168832-163.06301497 163.72433465-0.00116395 90.42497536 73.00479317 163.72549859 163.06301497 163.72549973z m251.51800206 200.09807531l1.21552441-0.45756757c-39.85964146-100.90130205-137.98992099-172.25861689-252.73352647-172.25861689-113.31967317 0-210.42537358 69.60155875-251.2141221 168.51890176l0.56235463 0.22820181a39.9609344 39.9609344 0 0 0-2.9188881 15.01940622c0 22.15886393 17.97555086 40.12510094 40.15420757 40.12509981 16.79146325 0 31.16701469-10.30401138 37.16196921-24.92522837l0.65433486 0.26545948c28.69987328-68.84360192 96.50958109-117.22355485 175.60014393-117.22355484 79.76934741 0 148.05874461 49.21940878 176.32433493 119.00725475l0.2875813-0.10828003c6.4338944 13.58499499 20.27037696 22.98434901 36.30970538 22.98434901 22.17516373 0 40.15187968-17.96507307 40.15187968-40.12509981a40.14256469 40.14256469 0 0 0-1.55549923-11.05032533z" fill="#CCCCCC" p-id="3671"></path></svg></a></div><div class="msg-content"></div></div>')
                let latestDate;
                if ((latestDate = browserServer.privateMessageArray[otherEvent.index].latestDate) != null) {
                    // 两条信息间隔时间太短就不加时间
                    if (otherEvent.calcDifferDate(latestDate, currentTime, 60000) > 3) {
                        allObject.msgList.append(otherEvent.setDate(latestDate, currentTime, false));
                    }
                } else {
                    // 第一条信息
                    let msgTime = $('<div class="msg-time"><span class="time"></span></div>');
                    let date = currentTime.split(/[\s:]/);
                    msgTime.find('.time').text(date[1] + ':' + date[2]);
                    allObject.msgList.append(msgTime);
                }
                msgItem.find('.msg-avatar-box').addClass('not-me-avatar').find('.msg-avatar').css('background-image', 'url(' + browserServer.localhost_user_avatar + browserServer.privateMessageArray[otherEvent.index].user_avatar + ')').find('svg').remove();
                msgItem.find('.msg-content').addClass('not-me').html(obj.content);
                msgItem.find('.msg-avatar').prop('href', browserServer.localhost + 'homepage/' + obj.sendUid);
                allObject.msgList.append(msgItem);
                browserServer.privateMessageArray[otherEvent.index].latestDate = currentTime;
            } else {
                $(allObject.latestMessageBodyUlLis[index]).find('.newLastestMessage').show();
            }
        },

        // 将消息打包成 JSON 对象, 需要接收对象的 uid
        messageToJSON: function (message) {
            return JSON.stringify({
                'targetUid': browserServer.privarySendUid,
                'message': message,
                'sendDate': window.getCurrentTime(),
                'sessionId': window.getUniqueString(20)
            });
        },


        // 使用滚动条插件
        scrollBar: function (obj) {
            let o = $(obj);
            $(window).load(function () {
                o.mCustomScrollbar({
                    scrollButtons: {
                        enable: true
                    }
                });
            });
        }
    };

    otherEvent.replyMeBack();
    otherEvent.shutdownReplyBar();
    otherEvent.giveLikeForMark();
    otherEvent.deleteReplyForMe();
    otherEvent.deleteOtherLike();
    otherEvent.deleteLastestChat();
    otherEvent.openMessageBar();
    otherEvent.openMessageSettings();
    otherEvent.calcPrivacyWordNumber();
    otherEvent.addScrollBar(allObject.Chatwindow);

    // 开启关闭按钮
    otherEvent.slide(allObject.messageRemind, 'messageNotice', browserServer.modifyPrivacySettings);
    otherEvent.slide(allObject.messageReply, 'replyNotice', browserServer.modifyPrivacySettings);
    otherEvent.slide(allObject.messageLikes, 'modifyHomepageSettings', browserServer.modifyPrivacySettings);
    // 使用滚动条
    otherEvent.scrollBar($('.replyMe-body'));
    otherEvent.scrollBar($('.like-body'));
    otherEvent.scrollBar($('.systemMessage-body'));
    otherEvent.scrollBar($('.lastestMessage-body'));

})


