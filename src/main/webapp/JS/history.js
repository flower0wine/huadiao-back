$(function () {


    var allObject = {
        noteHistoryContent: $('.noteHistoryContent'),
        fanjuHistoryContent: $('.fanjuHistoryContent'),
    };

    var browserServer = {

        localhost: window.localhost,

        localhost_user_avatar: window.localhost_user_avatar,

        localhost_huadiao_house: window.localhost_huadiao_house,

        formData: null,

        // 获取笔记历史记录
        getNotesHistories: function () {
            let data = {
                requestType: 'history',
                operation: 'getNoteHistory'
            };
            browserServer.browserServerCore(data, function (response) {
                console.log(response.data);
                if (typeof response.data == 'object') {
                    otherEvent.notes = response.data;
                    otherEvent.initialNoteHistory();
                }
            });
        },

        // 获取番剧馆历史记录
        getFanjusHistories() {
            let data = {
                requestType: "history",
                operation: "getFanjuHistory"
            };
            browserServer.browserServerCore(data, function (response) {
                console.log(response.data);
                if (typeof response.data == 'object') {
                    otherEvent.fanjus = response.data;
                    otherEvent.initialFanjuHistory();
                }
            });
        },

        // 删除笔记历史记录
        deleteNoteHistory(otherUid, noteId) {
            let data = {
                requestType: "history",
                operation: "deleteNoteHistory",
                authorUid: otherUid,
                noteId
            };
            browserServer.browserServerCore(data, () => {});
        },

        // 删除番剧馆历史记录
        deleteFanjuHistory: function (otherUid) {
            let data = {
                requestType: "history",
                operation: "deleteFanjuHistory",
                authorUid: otherUid,
            };
            browserServer.browserServerCore(data, () => {});
        },

        // 封装 axios
        browserServerCore: function (data, fn) {
            axios({
                url: browserServer.localhost + 'dispatcherServlet',
                method: 'post',
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded; charset=utf-8"
                },
                data
            }).then(fn);
        },
    };

    browserServer.getNotesHistories();
    browserServer.getFanjusHistories();

    var otherEvent = {

        notes: null,

        fanjus: null,

        // 初始化笔记历史记录
        initialNoteHistory: function () {

            if(otherEvent.notes.length === 0){
                allObject.noteHistoryContent.children('.note-empty').show();
            } else {
                // 初始化笔记面板
                let noteUl = allObject.noteHistoryContent.children('ul');
                noteUl.empty();
                otherEvent.notes.forEach(function (item) {
                    let li = $('<li><div class="lastTime"><div class="time"></div><div class="triangle"></div></div><div class="seenNote"><div class="note-txt"><a href="javascript:;" target="_blank" title=""><div class="noteTitle"></div></a><div class="noteSection"></div><div class="historyBelow"><a href="javascript:;" target="_blank" title=""><div class="noteAuther-avater"><svg t="1658222343190" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3820"><path d="M510.77050482 2.21923328c281.4240859 0 509.56537287 228.14128697 509.56537173 509.56653682 0 281.42757888-228.14128697 509.56653682-509.56537173 509.56653681-281.42757888 0-509.56886585-228.14012302-509.56886585-509.56653681 0-281.4240859 228.14128697-509.56653682 509.56886585-509.56653682z" fill="#EEEEEE" p-id="3821"></path><path d="M510.77050482 516.51164387c90.0558939 0 163.05952199-73.30168832 163.05952199-163.72549973 0-90.42264633-73.00362809-163.72433465-163.05952199-163.72433465-90.05705785 0-163.06301497 73.30168832-163.06301497 163.72433465-0.00116395 90.42497536 73.00479317 163.72549859 163.06301497 163.72549973z m251.51800206 200.09807531l1.21552441-0.45756757c-39.85964146-100.90130205-137.98992099-172.25861689-252.73352647-172.25861689-113.31967317 0-210.42537358 69.60155875-251.2141221 168.51890176l0.56235463 0.22820181a39.9609344 39.9609344 0 0 0-2.9188881 15.01940622c0 22.15886393 17.97555086 40.12510094 40.15420757 40.12509981 16.79146325 0 31.16701469-10.30401138 37.16196921-24.92522837l0.65433486 0.26545948c28.69987328-68.84360192 96.50958109-117.22355485 175.60014393-117.22355484 79.76934741 0 148.05874461 49.21940878 176.32433493 119.00725475l0.2875813-0.10828003c6.4338944 13.58499499 20.27037696 22.98434901 36.30970538 22.98434901 22.17516373 0 40.15187968-17.96507307 40.15187968-40.12509981a40.14256469 40.14256469 0 0 0-1.55549923-11.05032533z" fill="#CCCCCC" p-id="3822"></path></svg></div><div class="noteAuther-nickname"></div></a></div><div class="delete"><svg t="1658222415032" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3965"><path d="M955.904 119.46666667H750.93333333V34.13333333a34.13333333 34.13333333 0 0 0-34.13333333-34.13333333H307.2a34.13333333 34.13333333 0 0 0-34.13333333 34.13333333v85.33333334H68.096a34.13333333 34.13333333 0 0 0 0 68.26666666H104.10666667l83.11466666 745.23306667A102.4 102.4 0 0 0 288.9728 1024h446.02026667a102.43413333 102.43413333 0 0 0 101.7856-91.0336L919.89333333 187.73333333h35.97653334a34.13333333 34.13333333 0 0 0 0.03413333-68.26666666zM341.33333333 68.26666667h341.33333334v51.2H341.33333333V68.26666667z m427.6224 857.12213333a34.13333333 34.13333333 0 0 1-33.92853333 30.34453333H288.9728a34.13333333 34.13333333 0 0 1-33.92853333-30.34453333L172.78293333 187.73333333h678.43413334l-82.26133334 737.65546667z" p-id="3966"></path><path d="M614.4 841.5232a34.13333333 34.13333333 0 0 0 34.13333333-34.13333333V306.24426667a34.13333333 34.13333333 0 0 0-68.26666666 0v501.1456a34.13333333 34.13333333 0 0 0 34.13333333 34.13333333zM426.66666667 841.5232a34.13333333 34.13333333 0 0 0 34.13333333-34.13333333V306.24426667a34.13333333 34.13333333 0 0 0-68.26666667 0v501.1456a34.13333333 34.13333333 0 0 0 34.13333334 34.13333333z" p-id="3967"></path></svg></div></div></div></li>');
                    if (typeof item.authorUserAvatar != 'undefined') {
                        let avatar = $('<div class="avatar-box"><div class="img-avatar"></div></div>');
                        avatar.children('.img-avatar').css('background-image', 'url(' + browserServer.localhost_user_avatar + item.authorUserAvatar + ')')
                        li.find('.noteAuther-avater').append(avatar).children('svg').remove();
                    }
                    let date = item.viewedDate.split(/[\s-:]/);
                    li.find('.time').text(date[0] + '年' + date[1] + '月' + date[2] + '日');
                    li.find('.note-txt').children('a').prop('href', browserServer.localhost + item.authorUid + '/notes/note?note_id=' + item.noteId).prop('title', item.noteTitle).find('.noteTitle').text(item.noteTitle);
                    if (item.noteAbstract.length > 45) {
                        li.find('.noteSection').text(item.noteAbstract.substring(0, 45) + '...');
                    } else {
                        li.find('.noteSection').text(item.noteAbstract);
                    }
                    li.find('.historyBelow').children('a').prop('title', item.authorNickname).prop('href', browserServer.localhost + 'homepage/' + item.authorUid).find('.noteAuther-nickname').text(item.authorNickname);
                    noteUl.prepend(li);
                });
            }
        },

        // 初始化番剧馆
        initialFanjuHistory() {
            if(otherEvent.fanjus.length === 0){
                allObject.fanjuHistoryContent.children('.fanju-empty').show();
            } else {
                // 初始化番剧馆面板
                let fanjuUl = allObject.fanjuHistoryContent.children('ul');
                fanjuUl.empty();
                otherEvent.fanjus.forEach(function (item) {
                    let li = $('<li><div class="lastTime"><div class="time"></div><div class="triangle"></div></div><div class="seenNote"><a href="javascript:;" target="_blank" title=""><div class="fanjuMansion"></div></a><div class="fanju-txt"><a href="javascript:;" target="_blank" title=""><div class="fanjuMansionTitle"></div></a><div class="fanjuMansionCanvases"></div><div class="historyBelow"><a href="javascript:;" target="_blank" title=""><div class="fanjuAuther-avater"><svg t="1658222343190" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3820"><path d="M510.77050482 2.21923328c281.4240859 0 509.56537287 228.14128697 509.56537173 509.56653682 0 281.42757888-228.14128697 509.56653682-509.56537173 509.56653681-281.42757888 0-509.56886585-228.14012302-509.56886585-509.56653681 0-281.4240859 228.14128697-509.56653682 509.56886585-509.56653682z" fill="#EEEEEE" p-id="3821"></path><path d="M510.77050482 516.51164387c90.0558939 0 163.05952199-73.30168832 163.05952199-163.72549973 0-90.42264633-73.00362809-163.72433465-163.05952199-163.72433465-90.05705785 0-163.06301497 73.30168832-163.06301497 163.72433465-0.00116395 90.42497536 73.00479317 163.72549859 163.06301497 163.72549973z m251.51800206 200.09807531l1.21552441-0.45756757c-39.85964146-100.90130205-137.98992099-172.25861689-252.73352647-172.25861689-113.31967317 0-210.42537358 69.60155875-251.2141221 168.51890176l0.56235463 0.22820181a39.9609344 39.9609344 0 0 0-2.9188881 15.01940622c0 22.15886393 17.97555086 40.12510094 40.15420757 40.12509981 16.79146325 0 31.16701469-10.30401138 37.16196921-24.92522837l0.65433486 0.26545948c28.69987328-68.84360192 96.50958109-117.22355485 175.60014393-117.22355484 79.76934741 0 148.05874461 49.21940878 176.32433493 119.00725475l0.2875813-0.10828003c6.4338944 13.58499499 20.27037696 22.98434901 36.30970538 22.98434901 22.17516373 0 40.15187968-17.96507307 40.15187968-40.12509981a40.14256469 40.14256469 0 0 0-1.55549923-11.05032533z" fill="#CCCCCC" p-id="3822"></path></svg></div><div class="fanjuAuther-nickname"></div></a></div><div class="delete"><svg t="1658222415032" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3965"><path d="M955.904 119.46666667H750.93333333V34.13333333a34.13333333 34.13333333 0 0 0-34.13333333-34.13333333H307.2a34.13333333 34.13333333 0 0 0-34.13333333 34.13333333v85.33333334H68.096a34.13333333 34.13333333 0 0 0 0 68.26666666H104.10666667l83.11466666 745.23306667A102.4 102.4 0 0 0 288.9728 1024h446.02026667a102.43413333 102.43413333 0 0 0 101.7856-91.0336L919.89333333 187.73333333h35.97653334a34.13333333 34.13333333 0 0 0 0.03413333-68.26666666zM341.33333333 68.26666667h341.33333334v51.2H341.33333333V68.26666667z m427.6224 857.12213333a34.13333333 34.13333333 0 0 1-33.92853333 30.34453333H288.9728a34.13333333 34.13333333 0 0 1-33.92853333-30.34453333L172.78293333 187.73333333h678.43413334l-82.26133334 737.65546667z" p-id="3966"></path><path d="M614.4 841.5232a34.13333333 34.13333333 0 0 0 34.13333333-34.13333333V306.24426667a34.13333333 34.13333333 0 0 0-68.26666666 0v501.1456a34.13333333 34.13333333 0 0 0 34.13333333 34.13333333zM426.66666667 841.5232a34.13333333 34.13333333 0 0 0 34.13333333-34.13333333V306.24426667a34.13333333 34.13333333 0 0 0-68.26666667 0v501.1456a34.13333333 34.13333333 0 0 0 34.13333334 34.13333333z" p-id="3967"></path></svg></div></div></div></li>');
                    if (typeof item.authorUserAvatar != 'undefined') {
                        let avatar = $('<div class="avatar-box"><div class="img-avatar"></div></div>');
                        avatar.children('.img-avatar').css('background-image', 'url(' + browserServer.localhost_user_avatar + item.authorUserAvatar + ')')
                        li.find('.fanjuAuther-avater').append(avatar).children('svg').remove();
                    }
                    let date = item.viewDate.split(/[\s-:]/);
                    li.find('.time').text(date[0] + '年' + date[1] + '月' + date[2] + '日');
                    li.find('.seenNote').children('a').prop('title', item.panoperaTitle).prop('href', browserServer.localhost + 'panoperaHouse/' + item.authorUid);
                    li.find('.fanju-txt').children('a').prop('href', browserServer.localhost + 'panoperaHouse/' + item.authorUid).prop('title', item.panoperaHouse).children('.fanjuMansionTitle').text(item.panoperaHouse);
                    li.find('.fanjuMansionCanvases').text(item.panoperaCanvases);
                    li.find('.historyBelow').children('a').prop('title', item.authorNickname).prop('href', browserServer.localhost + 'homepage/' + item.authorUid).children('.fanjuAuther-nickname').text(item.authorNickname);
                    fanjuUl.prepend(li);
                });
            }
        },

        // 点击笔记，展示笔记记录
        showNotes: function () {
            $('.indicator-notes').click(function () {
                allObject.fanjuHistoryContent.hide();
                allObject.noteHistoryContent.show();
            });
        },

        // 展示番剧馆记录
        showPanoperaHouse: function () {
            $('.indicator-fanju').click(function () {
                allObject.noteHistoryContent.hide();
                allObject.fanjuHistoryContent.show();
            });
        },

        // 删除单个记录
        deleteHistory: function (obj, text, deleteType) {
            let o = $(obj);
            o.on('click', '.delete', function () {
                let delete_ = $(this);
                let li = $(this).parents('li');
                li.stop().animate({
                    height: 0
                }, 400, function () {
                    let url = delete_.parents(text).children('a').prop('href').split(/[\/=]/);
                    $(this).remove();
                    if (deleteType === 'deleteNoteHistory') {
                        otherEvent.notes.splice($(this).index(), 1);
                        if(otherEvent.notes.length === 0) {
                            allObject.noteHistoryContent.children('.note-empty').show();
                        }
                        browserServer.deleteNoteHistory(url[4], url[7]);
                    } else {
                        otherEvent.fanjus.splice($(this).index(), 1);
                        if(otherEvent.fanjus.length === 0) {
                            allObject.fanjuHistoryContent.children('.fanju-empty').show();
                        }
                        browserServer.deleteFanjuHistory(url[5]);
                    }
                })
            });
        }
    };

    otherEvent.showNotes();
    otherEvent.showPanoperaHouse();
    otherEvent.deleteHistory(allObject.noteHistoryContent, '.note-txt', 'deleteNoteHistory');
    otherEvent.deleteHistory(allObject.fanjuHistoryContent, '.fanju-txt', 'deleteFanjuHistory');

    // 默认打开笔记记录
    $('.indicator-notes').click()

})