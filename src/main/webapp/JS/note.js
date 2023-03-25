$(function () {

    var allObject = {
        likeNumber: $('.like-number'),
        starNumber: $('.star-number'),
        dirTop: $('.dir-icon-top'),
        dirMiddle: $('.dir-icon-middle'),
        dirBottom: $('.dir-icon-bottom'),
        noteBar: $('.note-bar'),
        directoryBar: $('.directory-bar'),
        markList: $('.mark-list'),
        followBtn: $('.follow-btn'),
        userAvatar: $('.user-avatar'),
        markAll: $('.mark-all'),
        markNumber: $('.mark-number'),
        mineAvatar: $('.mine-avatar'),
        starIcon: $('.star-icon'),
        likeIcon: $('.like .icon'),
        unlikeIcon: $('.unlike .icon'),
    };

    var module = {

        markSubInput: $('<div class="mark-main-box"><div class="mark-avatar-box"><a href="javascript:;"><div class="mine-avatar"><svg t="1661003927792" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4593" width="200" height="200"><path d="M510.77050482 2.21923328c281.4240859 0 509.56537287 228.14128697 509.56537173 509.56653682 0 281.42757888-228.14128697 509.56653682-509.56537173 509.56653681-281.42757888 0-509.56886585-228.14012302-509.56886585-509.56653681 0-281.4240859 228.14128697-509.56653682 509.56886585-509.56653682z" fill="#EEEEEE" p-id="4594"></path><path d="M510.77050482 516.51164387c90.0558939 0 163.05952199-73.30168832 163.05952199-163.72549973 0-90.42264633-73.00362809-163.72433465-163.05952199-163.72433465-90.05705785 0-163.06301497 73.30168832-163.06301497 163.72433465-0.00116395 90.42497536 73.00479317 163.72549859 163.06301497 163.72549973z m251.51800206 200.09807531l1.21552441-0.45756757c-39.85964146-100.90130205-137.98992099-172.25861689-252.73352647-172.25861689-113.31967317 0-210.42537358 69.60155875-251.2141221 168.51890176l0.56235463 0.22820181a39.9609344 39.9609344 0 0 0-2.9188881 15.01940622c0 22.15886393 17.97555086 40.12510094 40.15420757 40.12509981 16.79146325 0 31.16701469-10.30401138 37.16196921-24.92522837l0.65433486 0.26545948c28.69987328-68.84360192 96.50958109-117.22355485 175.60014393-117.22355484 79.76934741 0 148.05874461 49.21940878 176.32433493 119.00725475l0.2875813-0.10828003c6.4338944 13.58499499 20.27037696 22.98434901 36.30970538 22.98434901 22.17516373 0 40.15187968-17.96507307 40.15187968-40.12509981a40.14256469 40.14256469 0 0 0-1.55549923-11.05032533z"  fill="#CCCCCC" p-id="4595"></path></svg></div></a></div><div class="input-box sub-input"><textarea class="mark-input" placeholder="暴风雨前的宁静..."></textarea></div><div class="send-box sub-send">发布</div></div>'),

    };


    var browserServer = {

        formData: null,

        localhost: window.localhost,

        // 图片地址
        localhost_user_avatar: window.localhost_user_avatar,

        // 笔记，这个变量包含当前用户的信息
        note: null,

        // 当前笔记的作者 uid
        uid: null,

        // 被回复的对象 uid
        otherUid: null,

        noteId: null,

        // 获取指定笔记
        getSingleNote: function () {
            let data = {
                requestType: "note",
                operation: "getNote",
                authorUid: browserServer.uid,
                noteId: browserServer.noteId
            };
            browserServer.browserServerCore(data);
        },


        // axios 核心代码封装
        browserServerCore: function (data) {
            axios({
                url: browserServer.localhost + "dispatcherServlet",
                method: 'post',
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded; charset=utf-8"
                },
                data
            }).then(function (response) {
                console.log(response.data);
                // 含有笔记标题，是笔记
                if (typeof response.data.noteTitle == 'string') {
                    browserServer.note = response.data;
                    otherEvent.initialData();
                    browserServer.addViewNumber();
                }
            });
        },

        // 如果是别人访问该笔记，视为浏览量
        addViewNumber: function () {
            if (browserServer.note.myUid != browserServer.uid) {
            }
        },

        // 上传点赞或不喜欢的状态
        uploadStatus: function (likedUid, rootMarkId, subMarkId, status) {
            let data = {
                requestType: "note",
                operation: status,
                noteId: browserServer.noteId,
                likedUid,
                rootMarkId,
            };
            if (subMarkId !== null) {
                data.subMarkId = subMarkId;
            }
            browserServer.browserServerCore(data);
        },


        // 解析 url，获取笔记作者 uid 和 笔记唯一标识 id
        analysisUrl: function () {
            let url = window.location.href.split(/[\/=#]/);
            browserServer.uid = url[4];
            browserServer.noteId = url[7];

            browserServer.getSingleNote();
        }

    };
    browserServer.analysisUrl();

    var otherEvent = {

        // 得到近期消息的唯一 sessionId
        getSessionId: window.getUniqueString,

        // 初始化笔记数据
        initialData: function () {
            otherEvent.initialFollow();

            // 作者有头像设置头像，没有头像使用默认头像
            if (typeof browserServer.note.authorUserAvatar != 'undefined') {
                let avatar = $('<div class="img-avatar"></div>');
                avatar.css('background-image', 'url(' + browserServer.localhost_user_avatar + browserServer.note.authorUserAvatar + ')');
                allObject.userAvatar.find('svg').remove();
                allObject.userAvatar.children('a').append(avatar);
            }
            if (typeof browserServer.note.viewerUserAvatar != 'undefined') {
                // 设置本人头像，点击回复文字显示的回复框的设置
                let mineAvatar = $('<div class="mine-img-avatar"></div>');
                mineAvatar.css('background-image', 'url(' + browserServer.localhost_user_avatar + browserServer.note.viewerUserAvatar + ')');
                module.markSubInput.find('a').prop('href', browserServer.localhost + 'homepage/' + browserServer.note.viewerUid).children('.mine-avatar').append(mineAvatar).children('svg').remove();

                // 固定的输入框，设置本人的头像
                mineAvatar = $('<div class="mine-img-avatar"></div>');
                mineAvatar.css('background-image', 'url(' + browserServer.localhost_user_avatar + browserServer.note.viewerUserAvatar + ')');
                allObject.mineAvatar.append(mineAvatar).parents('a').prop('href', browserServer.localhost + 'homepage/' + browserServer.note.viewerUid).find('svg').remove()
            }
            // 设置笔记信息
            $('.note-title').text(browserServer.note.noteTitle);
            $('.author-nickname').text(browserServer.note.authorNickname)
            let date = browserServer.note.buildDate.split(/[\s:-]/);
            $('.release-time').text('发表于 ' + date[0] + '年' + date[1] + '月' + date[2] + '日' + ' ' + date[3] + ':' + date[4]);
            $('.view-number').text(browserServer.note.viewNumber);
            $('.infer-star-number').text(browserServer.note.starNumber);
            $('.note-content').html(browserServer.note.noteContent);
            $('.user-avatar>a').prop('href', browserServer.localhost + 'homepage/' + browserServer.uid);

            if (browserServer.note.myStar) {
                allObject.starIcon.removeClass('star-icon').success = false;
            } else {
                allObject.starIcon.success = true;
            }
            if (browserServer.note.myLike) {
                allObject.likeIcon.removeClass('like-icon').success = false;
            } else {
                allObject.likeIcon.success = true;
            }
            if (browserServer.note.myUnlike) {
                allObject.unlikeIcon.removeClass('unlike-icon').success = false;
            } else {
                allObject.unlikeIcon.success = true;
            }
            $('.like-number').text(browserServer.note.likeNumber);
            $('.star-number').text(browserServer.note.starNumber);
            $('.mark-number').text(browserServer.note.markNumber);
            $('.mark-all').text(browserServer.note.markNumber);


            otherEvent.handleFollow();
            otherEvent.initialMarks();

        },


        // 初始化评论数据
        initialMarks: function () {
            let rootMarks = browserServer.note.rootMarkList;
            let markNumber = 0;
            // 父评论
            rootMarks.forEach(function (value) {
                let markRootItem = $('<div class="mark-item"><div class="mark-root-container"><div class="root-avatar-box"><a href="javascript:;"><div class="root-avatar"><svg t="1661046532431" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4593" width="200" height="200"><path d="M510.77050482 2.21923328c281.4240859 0 509.56537287 228.14128697 509.56537173 509.56653682 0 281.42757888-228.14128697 509.56653682-509.56537173 509.56653681-281.42757888 0-509.56886585-228.14012302-509.56886585-509.56653681 0-281.4240859 228.14128697-509.56653682 509.56886585-509.56653682z" fill="#EEEEEE" p-id="4594"></path><path d="M510.77050482 516.51164387c90.0558939 0 163.05952199-73.30168832 163.05952199-163.72549973 0-90.42264633-73.00362809-163.72433465-163.05952199-163.72433465-90.05705785 0-163.06301497 73.30168832-163.06301497 163.72433465-0.00116395 90.42497536 73.00479317 163.72549859 163.06301497 163.72549973z m251.51800206 200.09807531l1.21552441-0.45756757c-39.85964146-100.90130205-137.98992099-172.25861689-252.73352647-172.25861689-113.31967317 0-210.42537358 69.60155875-251.2141221 168.51890176l0.56235463 0.22820181a39.9609344 39.9609344 0 0 0-2.9188881 15.01940622c0 22.15886393 17.97555086 40.12510094 40.15420757 40.12509981 16.79146325 0 31.16701469-10.30401138 37.16196921-24.92522837l0.65433486 0.26545948c28.69987328-68.84360192 96.50958109-117.22355485 175.60014393-117.22355484 79.76934741 0 148.05874461 49.21940878 176.32433493 119.00725475l0.2875813-0.10828003c6.4338944 13.58499499 20.27037696 22.98434901 36.30970538 22.98434901 22.17516373 0 40.15187968-17.96507307 40.15187968-40.12509981a40.14256469 40.14256469 0 0 0-1.55549923-11.05032533z" fill="#CCCCCC" p-id="4595"></path></svg></div></a></div><div class="user-info"><div class="user-nickname"></div></div><div class="user-mark"></div><div class="mark-info"><div class="send-date"></div><div class="mark-like"><svg t="1661047210261" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4884" width="200" height="200"><path d="M121.44576285 462.74069451c17.53211062 0 31.72347024-14.2224079 31.72347024-31.72222749 0-17.53086786-14.19135963-31.75451731-31.72347024-31.75451731-0.32910723 0-0.59487756 0.1813199-0.89417805 0.18131991l-75.84745729 0.17759406c-1.72998778-0.23720513-3.51958661-0.35891276-5.3079439-0.35891276-23.88077834 0-43.23105178 19.58996287-43.23105178 43.82965397L-4.25091011 968.99232805c0 24.2111271 19.37883743 43.20248779 43.29066284 43.2024878 2.32610687 0 4.622407 0.35518813 6.85661176 0l74.05910122 0.02732244c0.12046609 0 0.18007715 0.05836951 0.30054324 0.05836952 0.11798179 0 0.17759406-0.05836951 0.2968174-0.05836952l0.89417803 0 0-0.18007715c16.27901861-0.65573137 29.33899386-13.86225239 29.33899386-30.29278416 0-16.45661147-13.05873371-29.6631337-29.33899386-30.31886386l0-0.4781373-61.83369173 0 1.7597933-488.2140644L121.44576285 462.73821021z" p-id="4885"></path><path d="M1015.07667892 417.27419643c-23.40264103-38.1627973-57.45221693-53.60849047-98.65522199-55.27638289-2.1485128-0.2993017-4.29205701-0.47689577-6.52998762-0.47689577l-249.33673521-0.83580852c16.33862966-47.40388545 27.54815227-103.87374687 27.54815227-156.94324744 0-34.40600557-3.87601628-67.91783188-10.97355902-100.17656741l-0.59487755 0.05961228c-12.91094516-56.5294749-62.78872481-98.68626907-122.53600033-98.68626906-69.49754804 0-115.86070847 58.43705552-115.86070847 128.79773453 0 3.9343858-0.35767122 7.75203135 0 11.56843535-3.63632686 131.54112368-110.73160018 237.32369393-238.1582609 251.84416078l0 66.60637035-0.95379031 269.70165279 0 278.82475793 12.99787988 0 606.84548445 0.26949496 10.61464505-0.29557586c23.49205883 0.05961228 36.6998214-5.84693502 58.01604618-19.55891458 20.24693578-13.05997524 35.48274506-30.94851433 45.50002947-51.16191872 2.74338915-4.05485189 4.88941887-8.58659833 6.19715455-13.59399899l93.47147002-417.88194937c1.281656-4.91922439 1.63684413-9.92910814 1.281656-14.75891473C1035.85515414 468.85091434 1029.95481699 441.54245151 1015.07667892 417.27419643zM975.39501929 481.46255903l-100.7118327 446.35284486-0.12046609-0.05961226c-3.10106158 7.45397241-8.34939321 14.07337782-15.62204569 18.72310606-5.06949601 3.30970271-10.64817642 5.28062145-16.28026015 6.02452787-1.81816283-0.23720513-3.69718067 0-5.60724439 0l-578.93593512-0.65573137-0.20864114-494.44350878c108.40549452-48.8681029 187.92032575-96.69051218 228.88612568-210.76038026 0.08817627 0.02980554 0.15151316 0.05961228 0.23720512 0.08817626 3.64005149-11.0890565 7.66261354-25.16367587 10.55503277-40.24921475 6.73738721-35.4206497 6.3822003-70.54075616 6.3822003-70.54075615-5.99223805-46.09366547 31.48253806-64.2802647 53.84197097-64.28026471 30.71006766 1.04445087 61.00160907 40.84657539 61.00160906 63.50530878 0 0 6.79699949 33.45221526 6.85661178 69.40813024 0.05836951 45.3485163-5.66437357 68.99208829-5.66437358 68.99208829l-0.56631357 0c-7.12734826 37.47601886-19.67813913 72.98608637-36.9730434 105.54288082l0.44708901 0.41604195c-2.8626137 5.84445072-4.50194091 12.40424387-4.50194091 19.34903191 0 24.17883728 23.16667745 26.38696236 47.04745579 26.38696234l289.7685102 0.32786569c0 0 17.79912127 0.56507082 17.82892802 0.56507081l0 0.12170764c14.7241406-0.7749547 29.39612182 6.32010373 37.74427226 19.91534425 6.68025924 10.94251073 7.81288516 23.79260208 4.2312032 35.18096027L975.39501929 481.46255903z" p-id="4886"></path><path d="M212.02357089 1012.40221545c0.38747796 0.02980554 0.53526528 0.02980554 0.35891276-0.02980554 0.29557586-0.05836951 0.44584748-0.09065935-0.35891276-0.09065935s-0.65448861 0.03228982-0.32910723 0.09065935C211.54667634 1012.43202097 211.66589967 1012.43202097 212.02357089 1012.40221545z" p-id="4887"></path></svg><span class="mark-like-number">0</span></div><div class="mark-unlike"><svg t="1661047210261" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4884" width="200" height="200"><path d="M121.44576285 462.74069451c17.53211062 0 31.72347024-14.2224079 31.72347024-31.72222749 0-17.53086786-14.19135963-31.75451731-31.72347024-31.75451731-0.32910723 0-0.59487756 0.1813199-0.89417805 0.18131991l-75.84745729 0.17759406c-1.72998778-0.23720513-3.51958661-0.35891276-5.3079439-0.35891276-23.88077834 0-43.23105178 19.58996287-43.23105178 43.82965397L-4.25091011 968.99232805c0 24.2111271 19.37883743 43.20248779 43.29066284 43.2024878 2.32610687 0 4.622407 0.35518813 6.85661176 0l74.05910122 0.02732244c0.12046609 0 0.18007715 0.05836951 0.30054324 0.05836952 0.11798179 0 0.17759406-0.05836951 0.2968174-0.05836952l0.89417803 0 0-0.18007715c16.27901861-0.65573137 29.33899386-13.86225239 29.33899386-30.29278416 0-16.45661147-13.05873371-29.6631337-29.33899386-30.31886386l0-0.4781373-61.83369173 0 1.7597933-488.2140644L121.44576285 462.73821021z" p-id="4885"></path><path d="M1015.07667892 417.27419643c-23.40264103-38.1627973-57.45221693-53.60849047-98.65522199-55.27638289-2.1485128-0.2993017-4.29205701-0.47689577-6.52998762-0.47689577l-249.33673521-0.83580852c16.33862966-47.40388545 27.54815227-103.87374687 27.54815227-156.94324744 0-34.40600557-3.87601628-67.91783188-10.97355902-100.17656741l-0.59487755 0.05961228c-12.91094516-56.5294749-62.78872481-98.68626907-122.53600033-98.68626906-69.49754804 0-115.86070847 58.43705552-115.86070847 128.79773453 0 3.9343858-0.35767122 7.75203135 0 11.56843535-3.63632686 131.54112368-110.73160018 237.32369393-238.1582609 251.84416078l0 66.60637035-0.95379031 269.70165279 0 278.82475793 12.99787988 0 606.84548445 0.26949496 10.61464505-0.29557586c23.49205883 0.05961228 36.6998214-5.84693502 58.01604618-19.55891458 20.24693578-13.05997524 35.48274506-30.94851433 45.50002947-51.16191872 2.74338915-4.05485189 4.88941887-8.58659833 6.19715455-13.59399899l93.47147002-417.88194937c1.281656-4.91922439 1.63684413-9.92910814 1.281656-14.75891473C1035.85515414 468.85091434 1029.95481699 441.54245151 1015.07667892 417.27419643zM975.39501929 481.46255903l-100.7118327 446.35284486-0.12046609-0.05961226c-3.10106158 7.45397241-8.34939321 14.07337782-15.62204569 18.72310606-5.06949601 3.30970271-10.64817642 5.28062145-16.28026015 6.02452787-1.81816283-0.23720513-3.69718067 0-5.60724439 0l-578.93593512-0.65573137-0.20864114-494.44350878c108.40549452-48.8681029 187.92032575-96.69051218 228.88612568-210.76038026 0.08817627 0.02980554 0.15151316 0.05961228 0.23720512 0.08817626 3.64005149-11.0890565 7.66261354-25.16367587 10.55503277-40.24921475 6.73738721-35.4206497 6.3822003-70.54075616 6.3822003-70.54075615-5.99223805-46.09366547 31.48253806-64.2802647 53.84197097-64.28026471 30.71006766 1.04445087 61.00160907 40.84657539 61.00160906 63.50530878 0 0 6.79699949 33.45221526 6.85661178 69.40813024 0.05836951 45.3485163-5.66437357 68.99208829-5.66437358 68.99208829l-0.56631357 0c-7.12734826 37.47601886-19.67813913 72.98608637-36.9730434 105.54288082l0.44708901 0.41604195c-2.8626137 5.84445072-4.50194091 12.40424387-4.50194091 19.34903191 0 24.17883728 23.16667745 26.38696236 47.04745579 26.38696234l289.7685102 0.32786569c0 0 17.79912127 0.56507082 17.82892802 0.56507081l0 0.12170764c14.7241406-0.7749547 29.39612182 6.32010373 37.74427226 19.91534425 6.68025924 10.94251073 7.81288516 23.79260208 4.2312032 35.18096027L975.39501929 481.46255903z" p-id="4886"></path><path d="M212.02357089 1012.40221545c0.38747796 0.02980554 0.53526528 0.02980554 0.35891276-0.02980554 0.29557586-0.05836951 0.44584748-0.09065935-0.35891276-0.09065935s-0.65448861 0.03228982-0.32910723 0.09065935C211.54667634 1012.43202097 211.66589967 1012.43202097 212.02357089 1012.40221545z" p-id="4887"></path></svg></div><div class="mark-reply">回复</div></div><div class="mark-sub-container"><div class="mark-sub-list"></div></div>');
                let avatar = $('<div class="root-img-avatar"></div>')

                if (typeof value.userAvatar != 'undefined') {
                    avatar.css('background-image', 'url(' + browserServer.localhost_user_avatar + value.userAvatar + ')')
                    markRootItem.find('.root-avatar').append(avatar).children('svg').remove()
                }
                markRootItem.find('a').prop('href', browserServer.localhost + 'homepage/' + value.uid);
                markRootItem.find('.user-nickname').text(value.nickname);

                let date = value.markDate.split(/[\s-:]/);
                markRootItem.find('.send-date').text(date[0] + '-' + date[1] + '-' + date[2] + ' ' + date[3] + ':' + date[4]);
                markRootItem.find('.user-mark').text(value.markContent);
                // 设置父评论的唯一标识，字符串长度为 10
                markRootItem[0].setAttribute('root-mark', value.rootMarkId);
                markRootItem[0].setAttribute('uid', value.uid);
                // 判断该用户是否点过赞，并显示点赞的数量
                let like = markRootItem.find('.mark-like');
                let unlike = markRootItem.find('.mark-unlike');
                let number = like.find('.mark-like-number');
                let numberDOM = number[0];
                if (value.rootMarkLike === browserServer.note.myUid) {
                    like.find('svg').css('fill', '#ce2a2a');
                    // 该变量用来判断点击与否
                    numberDOM.flag = false;
                } else {
                    like.find('svg').css('fill', '#858585');
                    numberDOM.flag = true;
                }
                if (value.rootMarkUnlike === browserServer.note.myUid) {
                    unlike.find('svg').css('fill', '#ce2a2a');
                    unlike[0].flag = false;
                } else {
                    unlike.find('svg').css('fill', '#858585');
                    unlike[0].flag = true;
                }
                like.find('.mark-like-number').text(value.markLikeNumber);

                // 子评论
                value.markList.forEach(function (item) {
                    let markSubItem = $('<div class="mark-sub-item"><div class="sub-avatar-box"><a href="javascript:;"><div class="sub-avatar"><svg t="1661046532431" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4593" width="200" height="200"><path d="M510.77050482 2.21923328c281.4240859 0 509.56537287 228.14128697 509.56537173 509.56653682 0 281.42757888-228.14128697 509.56653682-509.56537173 509.56653681-281.42757888 0-509.56886585-228.14012302-509.56886585-509.56653681 0-281.4240859 228.14128697-509.56653682 509.56886585-509.56653682z" fill="#EEEEEE" p-id="4594"></path><path d="M510.77050482 516.51164387c90.0558939 0 163.05952199-73.30168832 163.05952199-163.72549973 0-90.42264633-73.00362809-163.72433465-163.05952199-163.72433465-90.05705785 0-163.06301497 73.30168832-163.06301497 163.72433465-0.00116395 90.42497536 73.00479317 163.72549859 163.06301497 163.72549973z m251.51800206 200.09807531l1.21552441-0.45756757c-39.85964146-100.90130205-137.98992099-172.25861689-252.73352647-172.25861689-113.31967317 0-210.42537358 69.60155875-251.2141221 168.51890176l0.56235463 0.22820181a39.9609344 39.9609344 0 0 0-2.9188881 15.01940622c0 22.15886393 17.97555086 40.12510094 40.15420757 40.12509981 16.79146325 0 31.16701469-10.30401138 37.16196921-24.92522837l0.65433486 0.26545948c28.69987328-68.84360192 96.50958109-117.22355485 175.60014393-117.22355484 79.76934741 0 148.05874461 49.21940878 176.32433493 119.00725475l0.2875813-0.10828003c6.4338944 13.58499499 20.27037696 22.98434901 36.30970538 22.98434901 22.17516373 0 40.15187968-17.96507307 40.15187968-40.12509981a40.14256469 40.14256469 0 0 0-1.55549923-11.05032533z" fill="#CCCCCC" p-id="4595"></path></svg></div></a></div><div class="user-info"><div class="user-nickname"></div></div><div class="user-mark"></div><div class="mark-info"><div class="send-date"></div><div class="mark-like"><svg t="1661047210261" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4884" width="200" height="200"><path d="M121.44576285 462.74069451c17.53211062 0 31.72347024-14.2224079 31.72347024-31.72222749 0-17.53086786-14.19135963-31.75451731-31.72347024-31.75451731-0.32910723 0-0.59487756 0.1813199-0.89417805 0.18131991l-75.84745729 0.17759406c-1.72998778-0.23720513-3.51958661-0.35891276-5.3079439-0.35891276-23.88077834 0-43.23105178 19.58996287-43.23105178 43.82965397L-4.25091011 968.99232805c0 24.2111271 19.37883743 43.20248779 43.29066284 43.2024878 2.32610687 0 4.622407 0.35518813 6.85661176 0l74.05910122 0.02732244c0.12046609 0 0.18007715 0.05836951 0.30054324 0.05836952 0.11798179 0 0.17759406-0.05836951 0.2968174-0.05836952l0.89417803 0 0-0.18007715c16.27901861-0.65573137 29.33899386-13.86225239 29.33899386-30.29278416 0-16.45661147-13.05873371-29.6631337-29.33899386-30.31886386l0-0.4781373-61.83369173 0 1.7597933-488.2140644L121.44576285 462.73821021z" p-id="4885"></path><path d="M1015.07667892 417.27419643c-23.40264103-38.1627973-57.45221693-53.60849047-98.65522199-55.27638289-2.1485128-0.2993017-4.29205701-0.47689577-6.52998762-0.47689577l-249.33673521-0.83580852c16.33862966-47.40388545 27.54815227-103.87374687 27.54815227-156.94324744 0-34.40600557-3.87601628-67.91783188-10.97355902-100.17656741l-0.59487755 0.05961228c-12.91094516-56.5294749-62.78872481-98.68626907-122.53600033-98.68626906-69.49754804 0-115.86070847 58.43705552-115.86070847 128.79773453 0 3.9343858-0.35767122 7.75203135 0 11.56843535-3.63632686 131.54112368-110.73160018 237.32369393-238.1582609 251.84416078l0 66.60637035-0.95379031 269.70165279 0 278.82475793 12.99787988 0 606.84548445 0.26949496 10.61464505-0.29557586c23.49205883 0.05961228 36.6998214-5.84693502 58.01604618-19.55891458 20.24693578-13.05997524 35.48274506-30.94851433 45.50002947-51.16191872 2.74338915-4.05485189 4.88941887-8.58659833 6.19715455-13.59399899l93.47147002-417.88194937c1.281656-4.91922439 1.63684413-9.92910814 1.281656-14.75891473C1035.85515414 468.85091434 1029.95481699 441.54245151 1015.07667892 417.27419643zM975.39501929 481.46255903l-100.7118327 446.35284486-0.12046609-0.05961226c-3.10106158 7.45397241-8.34939321 14.07337782-15.62204569 18.72310606-5.06949601 3.30970271-10.64817642 5.28062145-16.28026015 6.02452787-1.81816283-0.23720513-3.69718067 0-5.60724439 0l-578.93593512-0.65573137-0.20864114-494.44350878c108.40549452-48.8681029 187.92032575-96.69051218 228.88612568-210.76038026 0.08817627 0.02980554 0.15151316 0.05961228 0.23720512 0.08817626 3.64005149-11.0890565 7.66261354-25.16367587 10.55503277-40.24921475 6.73738721-35.4206497 6.3822003-70.54075616 6.3822003-70.54075615-5.99223805-46.09366547 31.48253806-64.2802647 53.84197097-64.28026471 30.71006766 1.04445087 61.00160907 40.84657539 61.00160906 63.50530878 0 0 6.79699949 33.45221526 6.85661178 69.40813024 0.05836951 45.3485163-5.66437357 68.99208829-5.66437358 68.99208829l-0.56631357 0c-7.12734826 37.47601886-19.67813913 72.98608637-36.9730434 105.54288082l0.44708901 0.41604195c-2.8626137 5.84445072-4.50194091 12.40424387-4.50194091 19.34903191 0 24.17883728 23.16667745 26.38696236 47.04745579 26.38696234l289.7685102 0.32786569c0 0 17.79912127 0.56507082 17.82892802 0.56507081l0 0.12170764c14.7241406-0.7749547 29.39612182 6.32010373 37.74427226 19.91534425 6.68025924 10.94251073 7.81288516 23.79260208 4.2312032 35.18096027L975.39501929 481.46255903z" p-id="4886"></path><path d="M212.02357089 1012.40221545c0.38747796 0.02980554 0.53526528 0.02980554 0.35891276-0.02980554 0.29557586-0.05836951 0.44584748-0.09065935-0.35891276-0.09065935s-0.65448861 0.03228982-0.32910723 0.09065935C211.54667634 1012.43202097 211.66589967 1012.43202097 212.02357089 1012.40221545z" p-id="4887"></path></svg><span class="mark-like-number">0</span></div><div class="mark-unlike"><svg t="1661047210261" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4884" width="200" height="200"><path d="M121.44576285 462.74069451c17.53211062 0 31.72347024-14.2224079 31.72347024-31.72222749 0-17.53086786-14.19135963-31.75451731-31.72347024-31.75451731-0.32910723 0-0.59487756 0.1813199-0.89417805 0.18131991l-75.84745729 0.17759406c-1.72998778-0.23720513-3.51958661-0.35891276-5.3079439-0.35891276-23.88077834 0-43.23105178 19.58996287-43.23105178 43.82965397L-4.25091011 968.99232805c0 24.2111271 19.37883743 43.20248779 43.29066284 43.2024878 2.32610687 0 4.622407 0.35518813 6.85661176 0l74.05910122 0.02732244c0.12046609 0 0.18007715 0.05836951 0.30054324 0.05836952 0.11798179 0 0.17759406-0.05836951 0.2968174-0.05836952l0.89417803 0 0-0.18007715c16.27901861-0.65573137 29.33899386-13.86225239 29.33899386-30.29278416 0-16.45661147-13.05873371-29.6631337-29.33899386-30.31886386l0-0.4781373-61.83369173 0 1.7597933-488.2140644L121.44576285 462.73821021z" p-id="4885"></path><path d="M1015.07667892 417.27419643c-23.40264103-38.1627973-57.45221693-53.60849047-98.65522199-55.27638289-2.1485128-0.2993017-4.29205701-0.47689577-6.52998762-0.47689577l-249.33673521-0.83580852c16.33862966-47.40388545 27.54815227-103.87374687 27.54815227-156.94324744 0-34.40600557-3.87601628-67.91783188-10.97355902-100.17656741l-0.59487755 0.05961228c-12.91094516-56.5294749-62.78872481-98.68626907-122.53600033-98.68626906-69.49754804 0-115.86070847 58.43705552-115.86070847 128.79773453 0 3.9343858-0.35767122 7.75203135 0 11.56843535-3.63632686 131.54112368-110.73160018 237.32369393-238.1582609 251.84416078l0 66.60637035-0.95379031 269.70165279 0 278.82475793 12.99787988 0 606.84548445 0.26949496 10.61464505-0.29557586c23.49205883 0.05961228 36.6998214-5.84693502 58.01604618-19.55891458 20.24693578-13.05997524 35.48274506-30.94851433 45.50002947-51.16191872 2.74338915-4.05485189 4.88941887-8.58659833 6.19715455-13.59399899l93.47147002-417.88194937c1.281656-4.91922439 1.63684413-9.92910814 1.281656-14.75891473C1035.85515414 468.85091434 1029.95481699 441.54245151 1015.07667892 417.27419643zM975.39501929 481.46255903l-100.7118327 446.35284486-0.12046609-0.05961226c-3.10106158 7.45397241-8.34939321 14.07337782-15.62204569 18.72310606-5.06949601 3.30970271-10.64817642 5.28062145-16.28026015 6.02452787-1.81816283-0.23720513-3.69718067 0-5.60724439 0l-578.93593512-0.65573137-0.20864114-494.44350878c108.40549452-48.8681029 187.92032575-96.69051218 228.88612568-210.76038026 0.08817627 0.02980554 0.15151316 0.05961228 0.23720512 0.08817626 3.64005149-11.0890565 7.66261354-25.16367587 10.55503277-40.24921475 6.73738721-35.4206497 6.3822003-70.54075616 6.3822003-70.54075615-5.99223805-46.09366547 31.48253806-64.2802647 53.84197097-64.28026471 30.71006766 1.04445087 61.00160907 40.84657539 61.00160906 63.50530878 0 0 6.79699949 33.45221526 6.85661178 69.40813024 0.05836951 45.3485163-5.66437357 68.99208829-5.66437358 68.99208829l-0.56631357 0c-7.12734826 37.47601886-19.67813913 72.98608637-36.9730434 105.54288082l0.44708901 0.41604195c-2.8626137 5.84445072-4.50194091 12.40424387-4.50194091 19.34903191 0 24.17883728 23.16667745 26.38696236 47.04745579 26.38696234l289.7685102 0.32786569c0 0 17.79912127 0.56507082 17.82892802 0.56507081l0 0.12170764c14.7241406-0.7749547 29.39612182 6.32010373 37.74427226 19.91534425 6.68025924 10.94251073 7.81288516 23.79260208 4.2312032 35.18096027L975.39501929 481.46255903z" p-id="4886"></path><path d="M212.02357089 1012.40221545c0.38747796 0.02980554 0.53526528 0.02980554 0.35891276-0.02980554 0.29557586-0.05836951 0.44584748-0.09065935-0.35891276-0.09065935s-0.65448861 0.03228982-0.32910723 0.09065935C211.54667634 1012.43202097 211.66589967 1012.43202097 212.02357089 1012.40221545z" p-id="4887"></path></svg></div><div class="mark-reply">回复</div></div></div>');
                    let avatar = $('<div class="sub-img-avatar"></div>')
                    if (typeof item.userAvatar != 'undefined') {
                        avatar.css('background-image', 'url(' + browserServer.localhost_user_avatar + item.userAvatar + ')');
                        markSubItem.find('.sub-avatar').append(avatar).children('svg').remove()
                    }
                    markSubItem.find('a').prop('href', browserServer.localhost + 'homepage/' + item.uid);
                    markSubItem.find('.user-nickname').text(item.nickname);
                    markSubItem.find('.user-mark').text(item.markContent);
                    let date = item.markDate.split(/[\s-:]/);
                    markSubItem.find('.send-date').text(date[0] + '-' + date[1] + '-' + date[2] + ' ' + date[3] + ':' + date[4]);
                    // 该变量的作用是判断点击与否
                    markSubItem.find('.mark-like-number')[0].flag = true;
                    markSubItem.find('.mark-unlike')[0].flag = true;

                    markSubItem[0].setAttribute('sub-mark', item.subMarkId);
                    markSubItem[0].setAttribute('uid', item.uid);

                    // 判断该用户是否点过赞，并显示点赞的数量
                    let like = markSubItem.find('.mark-like');
                    let unlike = markSubItem.find('.mark-unlike');
                    let number = like.find('.mark-like-number');
                    let numberDOM = number[0];
                    // 是否点赞
                    if (item.subMarkLike === browserServer.note.myUid) {
                        like.find('svg').css('fill', '#ce2a2a');
                        numberDOM.flag = false;
                    } else {
                        like.find('svg').css('fill', '#858585');
                        numberDOM.flag = true;
                    }
                    // 是否不喜欢
                    if(item.subMarkUnlike === browserServer.note.myUid){
                        unlike.find('svg').css('fill', '#ce2a2a');
                        unlike[0].flag = false;
                    } else {
                        unlike.find('svg').css('fill', '#858585');
                        unlike[0].flag = true;
                    }
                    like.find('.mark-like-number').text(item.markLikeNumber);

                    markRootItem.find('.mark-sub-list').prepend(markSubItem);

                });
                markNumber += value.markList.length + 1;
                allObject.markList.prepend(markRootItem);
            })
            // 所有评论的数量
            allObject.markAll.text(markNumber);
            allObject.markNumber.text(markNumber);
        },

        // 鼠标经过评论按钮变色
        moveColor: function () {
            $('.mark .icon').hover(function () {
                $(this).removeClass('mark-icon');
            }, function () {
                $(this).addClass('mark-icon');
            });
        },

        // 点击按钮变色，用于点赞，收藏，不喜欢按钮
        /**
         * @param tar 类型是 jquery 对象
         * @param className css 选择器名称，不带点
         * @param number 要进行加减的数字对象
         * @param positiveFn 点赞、收藏
         * @param negativeFn 取消点赞，取消收藏
         */
        clickColor: function (tar, className, positiveFn, negativeFn, number) {
            tar.click(function () {
                console.log(tar.success)
                if (tar.success) {
                    tar.removeClass(className);
                    tar.success = false;
                    if (typeof number != 'undefined') {
                        number.text(parseInt(number.text()) + 1);
                    }
                    if (typeof positiveFn == 'function') {
                        positiveFn();
                    }
                } else {
                    tar.addClass(className);
                    tar.success = true;
                    number.text(parseInt(number.text()) - 1);
                    if (typeof negativeFn == 'function') {
                        negativeFn();
                    }
                }
            });
        },


        // 打开或关闭目录，适用PC端
        handleDirectory: function () {
            let flag = true;
            $('.open-directory').off('click').click(function () {
                if (flag) {
                    // 按钮变化
                    allObject.dirMiddle.hide();
                    allObject.dirTop.css('animation', 'openDirectory_top .2s linear forwards');
                    allObject.dirBottom.css('animation', 'openDirectory_bottom .2s linear forwards');

                    allObject.noteBar.css('width', '80%');
                    allObject.directoryBar.css({'visibility': 'visible', 'width': 'calc(20% - 15px)'});
                    $(this).prop('title', '关闭目录');
                    flag = false;
                } else {
                    // 按钮变化
                    allObject.dirTop.css('animation', 'closeDirectory_top .2s linear forwards');
                    allObject.dirBottom.css('animation', 'closeDirectory_bottom .2s linear forwards');
                    allObject.dirMiddle.show();

                    allObject.noteBar.css('width', '100%');
                    allObject.directoryBar.css({'width': '0', 'visibility': 'hidden'});
                    $(this).prop('title', '打开目录');
                    flag = true;
                }
            })
        },

        // 添加回复添加回复框
        replyInput: function () {
            allObject.markList.on('click', '.mark-reply', function () {
                let this_ = $(this);
                let markItem = this_.parents('.mark-item');
                let markSubItem = this_.parents('.mark-sub-item');
                browserServer.otherUid = markItem[0].getAttribute('uid');
                if (typeof markSubItem[0] != 'undefined') {
                    browserServer.otherUid = markSubItem[0].getAttribute('uid');
                }
                module.markSubInput.find('textarea').prop('placeholder', '回复 @' + this_.parent('.mark-info').siblings('.user-info').children('.user-nickname').text())
                markItem.find('.mark-sub-container').append(module.markSubInput);
            });
        },


        // 评论点赞
        clickLike: function () {
            allObject.markList.on('click', '.mark-like', function () {
                let this_ = $(this);
                let rootItem = this_.parents('.mark-item')[0];
                let subItem = this_.parents('.mark-sub-item')[0];
                let rootMarkId = rootItem.getAttribute('root-mark');
                let otherUid = rootItem.getAttribute('uid');
                let subMarkId = null;
                if (typeof subItem != 'undefined') {
                    otherUid = subItem.getAttribute('uid');
                    subMarkId = subItem.getAttribute('sub-mark');
                }
                let number = this_.children('.mark-like-number');
                let numberDOM = number[0];
                if (numberDOM.flag) {
                    number.text(parseInt(number.text()) + 1);
                    this_.find('svg').css('fill', '#ce2a2a');
                    numberDOM.flag = false;
                } else {
                    number.text(parseInt(number.text()) - 1);
                    this_.find('svg').css('fill', '#858585');
                    numberDOM.flag = true;
                }
                browserServer.uploadStatus(otherUid, rootMarkId, subMarkId, 'noteCommentLike');
            });
        },


        // 评论不喜欢
        clickUnlike: function () {

            allObject.markList.on('click', '.mark-unlike', function () {
                let this_ = $(this);
                let rootItem = this_.parents('.mark-item')[0];
                let subItem = this_.parents('.mark-sub-item')[0];
                let rootMarkId = rootItem.getAttribute('root-mark');
                let otherUid = rootItem.getAttribute('uid');
                let subMarkId = null;
                if (typeof subItem != 'undefined') {
                    otherUid = subItem.getAttribute('uid');
                    subMarkId = subItem.getAttribute('sub-mark');
                }
                let numberDOM = this_[0];
                if (numberDOM.flag) {
                    this_.find('svg').css('fill', '#ce2a2a');
                    numberDOM.flag = false;
                } else {
                    this_.find('svg').css('fill', '#858585');
                    numberDOM.flag = true;
                }
                browserServer.uploadStatus(otherUid, rootMarkId, subMarkId, 'noteCommentUnlike');

            });
        },


        // 添加回复评论的评论并上传
        addSubReply: function () {
            allObject.markList.on('click', '.sub-send', function () {
                // 获取内容
                let textarea = $(this).siblings('.input-box').children('textarea');
                let value = textarea.val();
                textarea.val('');
                if (value.length === 0) {
                    return;
                }
                // 回复评论的评论
                let markSubItem = $('<div class="mark-sub-item"><div class="sub-avatar-box"><a href="javascript:;"><div class="sub-avatar"><svg t="1661046532431" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4593" width="200" height="200"><path d="M510.77050482 2.21923328c281.4240859 0 509.56537287 228.14128697 509.56537173 509.56653682 0 281.42757888-228.14128697 509.56653682-509.56537173 509.56653681-281.42757888 0-509.56886585-228.14012302-509.56886585-509.56653681 0-281.4240859 228.14128697-509.56653682 509.56886585-509.56653682z" fill="#EEEEEE" p-id="4594"></path><path d="M510.77050482 516.51164387c90.0558939 0 163.05952199-73.30168832 163.05952199-163.72549973 0-90.42264633-73.00362809-163.72433465-163.05952199-163.72433465-90.05705785 0-163.06301497 73.30168832-163.06301497 163.72433465-0.00116395 90.42497536 73.00479317 163.72549859 163.06301497 163.72549973z m251.51800206 200.09807531l1.21552441-0.45756757c-39.85964146-100.90130205-137.98992099-172.25861689-252.73352647-172.25861689-113.31967317 0-210.42537358 69.60155875-251.2141221 168.51890176l0.56235463 0.22820181a39.9609344 39.9609344 0 0 0-2.9188881 15.01940622c0 22.15886393 17.97555086 40.12510094 40.15420757 40.12509981 16.79146325 0 31.16701469-10.30401138 37.16196921-24.92522837l0.65433486 0.26545948c28.69987328-68.84360192 96.50958109-117.22355485 175.60014393-117.22355484 79.76934741 0 148.05874461 49.21940878 176.32433493 119.00725475l0.2875813-0.10828003c6.4338944 13.58499499 20.27037696 22.98434901 36.30970538 22.98434901 22.17516373 0 40.15187968-17.96507307 40.15187968-40.12509981a40.14256469 40.14256469 0 0 0-1.55549923-11.05032533z" fill="#CCCCCC" p-id="4595"></path></svg></div></a></div><div class="user-info"><div class="user-nickname"></div></div><div class="user-mark"></div><div class="mark-info"><div class="send-date"></div><div class="mark-like"><svg t="1661047210261" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4884" width="200" height="200"><path d="M121.44576285 462.74069451c17.53211062 0 31.72347024-14.2224079 31.72347024-31.72222749 0-17.53086786-14.19135963-31.75451731-31.72347024-31.75451731-0.32910723 0-0.59487756 0.1813199-0.89417805 0.18131991l-75.84745729 0.17759406c-1.72998778-0.23720513-3.51958661-0.35891276-5.3079439-0.35891276-23.88077834 0-43.23105178 19.58996287-43.23105178 43.82965397L-4.25091011 968.99232805c0 24.2111271 19.37883743 43.20248779 43.29066284 43.2024878 2.32610687 0 4.622407 0.35518813 6.85661176 0l74.05910122 0.02732244c0.12046609 0 0.18007715 0.05836951 0.30054324 0.05836952 0.11798179 0 0.17759406-0.05836951 0.2968174-0.05836952l0.89417803 0 0-0.18007715c16.27901861-0.65573137 29.33899386-13.86225239 29.33899386-30.29278416 0-16.45661147-13.05873371-29.6631337-29.33899386-30.31886386l0-0.4781373-61.83369173 0 1.7597933-488.2140644L121.44576285 462.73821021z" p-id="4885"></path><path d="M1015.07667892 417.27419643c-23.40264103-38.1627973-57.45221693-53.60849047-98.65522199-55.27638289-2.1485128-0.2993017-4.29205701-0.47689577-6.52998762-0.47689577l-249.33673521-0.83580852c16.33862966-47.40388545 27.54815227-103.87374687 27.54815227-156.94324744 0-34.40600557-3.87601628-67.91783188-10.97355902-100.17656741l-0.59487755 0.05961228c-12.91094516-56.5294749-62.78872481-98.68626907-122.53600033-98.68626906-69.49754804 0-115.86070847 58.43705552-115.86070847 128.79773453 0 3.9343858-0.35767122 7.75203135 0 11.56843535-3.63632686 131.54112368-110.73160018 237.32369393-238.1582609 251.84416078l0 66.60637035-0.95379031 269.70165279 0 278.82475793 12.99787988 0 606.84548445 0.26949496 10.61464505-0.29557586c23.49205883 0.05961228 36.6998214-5.84693502 58.01604618-19.55891458 20.24693578-13.05997524 35.48274506-30.94851433 45.50002947-51.16191872 2.74338915-4.05485189 4.88941887-8.58659833 6.19715455-13.59399899l93.47147002-417.88194937c1.281656-4.91922439 1.63684413-9.92910814 1.281656-14.75891473C1035.85515414 468.85091434 1029.95481699 441.54245151 1015.07667892 417.27419643zM975.39501929 481.46255903l-100.7118327 446.35284486-0.12046609-0.05961226c-3.10106158 7.45397241-8.34939321 14.07337782-15.62204569 18.72310606-5.06949601 3.30970271-10.64817642 5.28062145-16.28026015 6.02452787-1.81816283-0.23720513-3.69718067 0-5.60724439 0l-578.93593512-0.65573137-0.20864114-494.44350878c108.40549452-48.8681029 187.92032575-96.69051218 228.88612568-210.76038026 0.08817627 0.02980554 0.15151316 0.05961228 0.23720512 0.08817626 3.64005149-11.0890565 7.66261354-25.16367587 10.55503277-40.24921475 6.73738721-35.4206497 6.3822003-70.54075616 6.3822003-70.54075615-5.99223805-46.09366547 31.48253806-64.2802647 53.84197097-64.28026471 30.71006766 1.04445087 61.00160907 40.84657539 61.00160906 63.50530878 0 0 6.79699949 33.45221526 6.85661178 69.40813024 0.05836951 45.3485163-5.66437357 68.99208829-5.66437358 68.99208829l-0.56631357 0c-7.12734826 37.47601886-19.67813913 72.98608637-36.9730434 105.54288082l0.44708901 0.41604195c-2.8626137 5.84445072-4.50194091 12.40424387-4.50194091 19.34903191 0 24.17883728 23.16667745 26.38696236 47.04745579 26.38696234l289.7685102 0.32786569c0 0 17.79912127 0.56507082 17.82892802 0.56507081l0 0.12170764c14.7241406-0.7749547 29.39612182 6.32010373 37.74427226 19.91534425 6.68025924 10.94251073 7.81288516 23.79260208 4.2312032 35.18096027L975.39501929 481.46255903z" p-id="4886"></path><path d="M212.02357089 1012.40221545c0.38747796 0.02980554 0.53526528 0.02980554 0.35891276-0.02980554 0.29557586-0.05836951 0.44584748-0.09065935-0.35891276-0.09065935s-0.65448861 0.03228982-0.32910723 0.09065935C211.54667634 1012.43202097 211.66589967 1012.43202097 212.02357089 1012.40221545z" p-id="4887"></path></svg><span class="mark-like-number">0</span></div><div class="mark-unlike"><svg t="1661047210261" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4884" width="200" height="200"><path d="M121.44576285 462.74069451c17.53211062 0 31.72347024-14.2224079 31.72347024-31.72222749 0-17.53086786-14.19135963-31.75451731-31.72347024-31.75451731-0.32910723 0-0.59487756 0.1813199-0.89417805 0.18131991l-75.84745729 0.17759406c-1.72998778-0.23720513-3.51958661-0.35891276-5.3079439-0.35891276-23.88077834 0-43.23105178 19.58996287-43.23105178 43.82965397L-4.25091011 968.99232805c0 24.2111271 19.37883743 43.20248779 43.29066284 43.2024878 2.32610687 0 4.622407 0.35518813 6.85661176 0l74.05910122 0.02732244c0.12046609 0 0.18007715 0.05836951 0.30054324 0.05836952 0.11798179 0 0.17759406-0.05836951 0.2968174-0.05836952l0.89417803 0 0-0.18007715c16.27901861-0.65573137 29.33899386-13.86225239 29.33899386-30.29278416 0-16.45661147-13.05873371-29.6631337-29.33899386-30.31886386l0-0.4781373-61.83369173 0 1.7597933-488.2140644L121.44576285 462.73821021z" p-id="4885"></path><path d="M1015.07667892 417.27419643c-23.40264103-38.1627973-57.45221693-53.60849047-98.65522199-55.27638289-2.1485128-0.2993017-4.29205701-0.47689577-6.52998762-0.47689577l-249.33673521-0.83580852c16.33862966-47.40388545 27.54815227-103.87374687 27.54815227-156.94324744 0-34.40600557-3.87601628-67.91783188-10.97355902-100.17656741l-0.59487755 0.05961228c-12.91094516-56.5294749-62.78872481-98.68626907-122.53600033-98.68626906-69.49754804 0-115.86070847 58.43705552-115.86070847 128.79773453 0 3.9343858-0.35767122 7.75203135 0 11.56843535-3.63632686 131.54112368-110.73160018 237.32369393-238.1582609 251.84416078l0 66.60637035-0.95379031 269.70165279 0 278.82475793 12.99787988 0 606.84548445 0.26949496 10.61464505-0.29557586c23.49205883 0.05961228 36.6998214-5.84693502 58.01604618-19.55891458 20.24693578-13.05997524 35.48274506-30.94851433 45.50002947-51.16191872 2.74338915-4.05485189 4.88941887-8.58659833 6.19715455-13.59399899l93.47147002-417.88194937c1.281656-4.91922439 1.63684413-9.92910814 1.281656-14.75891473C1035.85515414 468.85091434 1029.95481699 441.54245151 1015.07667892 417.27419643zM975.39501929 481.46255903l-100.7118327 446.35284486-0.12046609-0.05961226c-3.10106158 7.45397241-8.34939321 14.07337782-15.62204569 18.72310606-5.06949601 3.30970271-10.64817642 5.28062145-16.28026015 6.02452787-1.81816283-0.23720513-3.69718067 0-5.60724439 0l-578.93593512-0.65573137-0.20864114-494.44350878c108.40549452-48.8681029 187.92032575-96.69051218 228.88612568-210.76038026 0.08817627 0.02980554 0.15151316 0.05961228 0.23720512 0.08817626 3.64005149-11.0890565 7.66261354-25.16367587 10.55503277-40.24921475 6.73738721-35.4206497 6.3822003-70.54075616 6.3822003-70.54075615-5.99223805-46.09366547 31.48253806-64.2802647 53.84197097-64.28026471 30.71006766 1.04445087 61.00160907 40.84657539 61.00160906 63.50530878 0 0 6.79699949 33.45221526 6.85661178 69.40813024 0.05836951 45.3485163-5.66437357 68.99208829-5.66437358 68.99208829l-0.56631357 0c-7.12734826 37.47601886-19.67813913 72.98608637-36.9730434 105.54288082l0.44708901 0.41604195c-2.8626137 5.84445072-4.50194091 12.40424387-4.50194091 19.34903191 0 24.17883728 23.16667745 26.38696236 47.04745579 26.38696234l289.7685102 0.32786569c0 0 17.79912127 0.56507082 17.82892802 0.56507081l0 0.12170764c14.7241406-0.7749547 29.39612182 6.32010373 37.74427226 19.91534425 6.68025924 10.94251073 7.81288516 23.79260208 4.2312032 35.18096027L975.39501929 481.46255903z" p-id="4886"></path><path d="M212.02357089 1012.40221545c0.38747796 0.02980554 0.53526528 0.02980554 0.35891276-0.02980554 0.29557586-0.05836951 0.44584748-0.09065935-0.35891276-0.09065935s-0.65448861 0.03228982-0.32910723 0.09065935C211.54667634 1012.43202097 211.66589967 1012.43202097 212.02357089 1012.40221545z" p-id="4887"></path></svg></div><div class="mark-reply">回复</div></div></div>');
                let avatar = $('<div class="sub-img-avatar"></div>')
                if (typeof browserServer.note.myUserAvatar != 'undefined') {
                    avatar.css('background-image', 'url(' + browserServer.localhost_user_avatar + browserServer.note.myUserAvatar + ')');
                    markSubItem.find('.sub-avatar').append(avatar).children('svg').remove()
                }
                markSubItem.find('a').prop('href', browserServer.localhost + 'homepage/' + browserServer.note.myUid)
                markSubItem.find('.user-nickname').text(browserServer.note.myNickname);
                markSubItem.find('.user-mark').text(value);
                markSubItem.find('.send-date').text(otherEvent.formatDate());
                // 该变量的作用是判断点击与否
                markSubItem.find('.mark-like-number')[0].flag = true;
                markSubItem.find('.mark-unlike')[0].flag = true;

                $(this).parents('.mark-sub-container').children('.mark-sub-list').prepend(markSubItem);
                // 获取父评论唯一标识
                let rootMark = $(this).parents('.mark-item')[0].getAttribute('root-mark');
                // 设置子评论的唯一标识，用于点赞时，区分子评论
                let subMark = otherEvent.getUniqueString(20);
                markSubItem[0].setAttribute('sub-mark', subMark);
                markSubItem[0].setAttribute('uid', browserServer.note.myUid);
                module.markSubInput.remove();

                allObject.markAll.text(parseInt(allObject.markAll.text()) + 1);
                allObject.markNumber.text(parseInt(allObject.markNumber.text()) + 1);

                otherEvent.buildMark(value, 'buildSubMark', rootMark, subMark);
            });
        },

        // 添加回复的评论并上传
        addRootReply: function () {
            $('.root-send').click(function () {
                let textarea = $(this).siblings('.input-box').children('textarea');
                let value = textarea.val();
                textarea.val('');
                if (value.length === 0) {
                    return;
                }
                let markRootItem = $('<div class="mark-item"><div class="mark-root-container"><div class="root-avatar-box"><a href="javascript:;"><div class="root-avatar"><svg t="1661046532431" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4593" width="200" height="200"><path d="M510.77050482 2.21923328c281.4240859 0 509.56537287 228.14128697 509.56537173 509.56653682 0 281.42757888-228.14128697 509.56653682-509.56537173 509.56653681-281.42757888 0-509.56886585-228.14012302-509.56886585-509.56653681 0-281.4240859 228.14128697-509.56653682 509.56886585-509.56653682z" fill="#EEEEEE" p-id="4594"></path><path d="M510.77050482 516.51164387c90.0558939 0 163.05952199-73.30168832 163.05952199-163.72549973 0-90.42264633-73.00362809-163.72433465-163.05952199-163.72433465-90.05705785 0-163.06301497 73.30168832-163.06301497 163.72433465-0.00116395 90.42497536 73.00479317 163.72549859 163.06301497 163.72549973z m251.51800206 200.09807531l1.21552441-0.45756757c-39.85964146-100.90130205-137.98992099-172.25861689-252.73352647-172.25861689-113.31967317 0-210.42537358 69.60155875-251.2141221 168.51890176l0.56235463 0.22820181a39.9609344 39.9609344 0 0 0-2.9188881 15.01940622c0 22.15886393 17.97555086 40.12510094 40.15420757 40.12509981 16.79146325 0 31.16701469-10.30401138 37.16196921-24.92522837l0.65433486 0.26545948c28.69987328-68.84360192 96.50958109-117.22355485 175.60014393-117.22355484 79.76934741 0 148.05874461 49.21940878 176.32433493 119.00725475l0.2875813-0.10828003c6.4338944 13.58499499 20.27037696 22.98434901 36.30970538 22.98434901 22.17516373 0 40.15187968-17.96507307 40.15187968-40.12509981a40.14256469 40.14256469 0 0 0-1.55549923-11.05032533z" fill="#CCCCCC" p-id="4595"></path></svg></div></a></div><div class="user-info"><div class="user-nickname"></div></div><div class="user-mark"></div><div class="mark-info"><div class="send-date"></div><div class="mark-like"><svg t="1661047210261" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4884" width="200" height="200"><path d="M121.44576285 462.74069451c17.53211062 0 31.72347024-14.2224079 31.72347024-31.72222749 0-17.53086786-14.19135963-31.75451731-31.72347024-31.75451731-0.32910723 0-0.59487756 0.1813199-0.89417805 0.18131991l-75.84745729 0.17759406c-1.72998778-0.23720513-3.51958661-0.35891276-5.3079439-0.35891276-23.88077834 0-43.23105178 19.58996287-43.23105178 43.82965397L-4.25091011 968.99232805c0 24.2111271 19.37883743 43.20248779 43.29066284 43.2024878 2.32610687 0 4.622407 0.35518813 6.85661176 0l74.05910122 0.02732244c0.12046609 0 0.18007715 0.05836951 0.30054324 0.05836952 0.11798179 0 0.17759406-0.05836951 0.2968174-0.05836952l0.89417803 0 0-0.18007715c16.27901861-0.65573137 29.33899386-13.86225239 29.33899386-30.29278416 0-16.45661147-13.05873371-29.6631337-29.33899386-30.31886386l0-0.4781373-61.83369173 0 1.7597933-488.2140644L121.44576285 462.73821021z" p-id="4885"></path><path d="M1015.07667892 417.27419643c-23.40264103-38.1627973-57.45221693-53.60849047-98.65522199-55.27638289-2.1485128-0.2993017-4.29205701-0.47689577-6.52998762-0.47689577l-249.33673521-0.83580852c16.33862966-47.40388545 27.54815227-103.87374687 27.54815227-156.94324744 0-34.40600557-3.87601628-67.91783188-10.97355902-100.17656741l-0.59487755 0.05961228c-12.91094516-56.5294749-62.78872481-98.68626907-122.53600033-98.68626906-69.49754804 0-115.86070847 58.43705552-115.86070847 128.79773453 0 3.9343858-0.35767122 7.75203135 0 11.56843535-3.63632686 131.54112368-110.73160018 237.32369393-238.1582609 251.84416078l0 66.60637035-0.95379031 269.70165279 0 278.82475793 12.99787988 0 606.84548445 0.26949496 10.61464505-0.29557586c23.49205883 0.05961228 36.6998214-5.84693502 58.01604618-19.55891458 20.24693578-13.05997524 35.48274506-30.94851433 45.50002947-51.16191872 2.74338915-4.05485189 4.88941887-8.58659833 6.19715455-13.59399899l93.47147002-417.88194937c1.281656-4.91922439 1.63684413-9.92910814 1.281656-14.75891473C1035.85515414 468.85091434 1029.95481699 441.54245151 1015.07667892 417.27419643zM975.39501929 481.46255903l-100.7118327 446.35284486-0.12046609-0.05961226c-3.10106158 7.45397241-8.34939321 14.07337782-15.62204569 18.72310606-5.06949601 3.30970271-10.64817642 5.28062145-16.28026015 6.02452787-1.81816283-0.23720513-3.69718067 0-5.60724439 0l-578.93593512-0.65573137-0.20864114-494.44350878c108.40549452-48.8681029 187.92032575-96.69051218 228.88612568-210.76038026 0.08817627 0.02980554 0.15151316 0.05961228 0.23720512 0.08817626 3.64005149-11.0890565 7.66261354-25.16367587 10.55503277-40.24921475 6.73738721-35.4206497 6.3822003-70.54075616 6.3822003-70.54075615-5.99223805-46.09366547 31.48253806-64.2802647 53.84197097-64.28026471 30.71006766 1.04445087 61.00160907 40.84657539 61.00160906 63.50530878 0 0 6.79699949 33.45221526 6.85661178 69.40813024 0.05836951 45.3485163-5.66437357 68.99208829-5.66437358 68.99208829l-0.56631357 0c-7.12734826 37.47601886-19.67813913 72.98608637-36.9730434 105.54288082l0.44708901 0.41604195c-2.8626137 5.84445072-4.50194091 12.40424387-4.50194091 19.34903191 0 24.17883728 23.16667745 26.38696236 47.04745579 26.38696234l289.7685102 0.32786569c0 0 17.79912127 0.56507082 17.82892802 0.56507081l0 0.12170764c14.7241406-0.7749547 29.39612182 6.32010373 37.74427226 19.91534425 6.68025924 10.94251073 7.81288516 23.79260208 4.2312032 35.18096027L975.39501929 481.46255903z" p-id="4886"></path><path d="M212.02357089 1012.40221545c0.38747796 0.02980554 0.53526528 0.02980554 0.35891276-0.02980554 0.29557586-0.05836951 0.44584748-0.09065935-0.35891276-0.09065935s-0.65448861 0.03228982-0.32910723 0.09065935C211.54667634 1012.43202097 211.66589967 1012.43202097 212.02357089 1012.40221545z" p-id="4887"></path></svg><span class="mark-like-number">0</span></div><div class="mark-unlike"><svg t="1661047210261" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4884" width="200" height="200"><path d="M121.44576285 462.74069451c17.53211062 0 31.72347024-14.2224079 31.72347024-31.72222749 0-17.53086786-14.19135963-31.75451731-31.72347024-31.75451731-0.32910723 0-0.59487756 0.1813199-0.89417805 0.18131991l-75.84745729 0.17759406c-1.72998778-0.23720513-3.51958661-0.35891276-5.3079439-0.35891276-23.88077834 0-43.23105178 19.58996287-43.23105178 43.82965397L-4.25091011 968.99232805c0 24.2111271 19.37883743 43.20248779 43.29066284 43.2024878 2.32610687 0 4.622407 0.35518813 6.85661176 0l74.05910122 0.02732244c0.12046609 0 0.18007715 0.05836951 0.30054324 0.05836952 0.11798179 0 0.17759406-0.05836951 0.2968174-0.05836952l0.89417803 0 0-0.18007715c16.27901861-0.65573137 29.33899386-13.86225239 29.33899386-30.29278416 0-16.45661147-13.05873371-29.6631337-29.33899386-30.31886386l0-0.4781373-61.83369173 0 1.7597933-488.2140644L121.44576285 462.73821021z" p-id="4885"></path><path d="M1015.07667892 417.27419643c-23.40264103-38.1627973-57.45221693-53.60849047-98.65522199-55.27638289-2.1485128-0.2993017-4.29205701-0.47689577-6.52998762-0.47689577l-249.33673521-0.83580852c16.33862966-47.40388545 27.54815227-103.87374687 27.54815227-156.94324744 0-34.40600557-3.87601628-67.91783188-10.97355902-100.17656741l-0.59487755 0.05961228c-12.91094516-56.5294749-62.78872481-98.68626907-122.53600033-98.68626906-69.49754804 0-115.86070847 58.43705552-115.86070847 128.79773453 0 3.9343858-0.35767122 7.75203135 0 11.56843535-3.63632686 131.54112368-110.73160018 237.32369393-238.1582609 251.84416078l0 66.60637035-0.95379031 269.70165279 0 278.82475793 12.99787988 0 606.84548445 0.26949496 10.61464505-0.29557586c23.49205883 0.05961228 36.6998214-5.84693502 58.01604618-19.55891458 20.24693578-13.05997524 35.48274506-30.94851433 45.50002947-51.16191872 2.74338915-4.05485189 4.88941887-8.58659833 6.19715455-13.59399899l93.47147002-417.88194937c1.281656-4.91922439 1.63684413-9.92910814 1.281656-14.75891473C1035.85515414 468.85091434 1029.95481699 441.54245151 1015.07667892 417.27419643zM975.39501929 481.46255903l-100.7118327 446.35284486-0.12046609-0.05961226c-3.10106158 7.45397241-8.34939321 14.07337782-15.62204569 18.72310606-5.06949601 3.30970271-10.64817642 5.28062145-16.28026015 6.02452787-1.81816283-0.23720513-3.69718067 0-5.60724439 0l-578.93593512-0.65573137-0.20864114-494.44350878c108.40549452-48.8681029 187.92032575-96.69051218 228.88612568-210.76038026 0.08817627 0.02980554 0.15151316 0.05961228 0.23720512 0.08817626 3.64005149-11.0890565 7.66261354-25.16367587 10.55503277-40.24921475 6.73738721-35.4206497 6.3822003-70.54075616 6.3822003-70.54075615-5.99223805-46.09366547 31.48253806-64.2802647 53.84197097-64.28026471 30.71006766 1.04445087 61.00160907 40.84657539 61.00160906 63.50530878 0 0 6.79699949 33.45221526 6.85661178 69.40813024 0.05836951 45.3485163-5.66437357 68.99208829-5.66437358 68.99208829l-0.56631357 0c-7.12734826 37.47601886-19.67813913 72.98608637-36.9730434 105.54288082l0.44708901 0.41604195c-2.8626137 5.84445072-4.50194091 12.40424387-4.50194091 19.34903191 0 24.17883728 23.16667745 26.38696236 47.04745579 26.38696234l289.7685102 0.32786569c0 0 17.79912127 0.56507082 17.82892802 0.56507081l0 0.12170764c14.7241406-0.7749547 29.39612182 6.32010373 37.74427226 19.91534425 6.68025924 10.94251073 7.81288516 23.79260208 4.2312032 35.18096027L975.39501929 481.46255903z" p-id="4886"></path><path d="M212.02357089 1012.40221545c0.38747796 0.02980554 0.53526528 0.02980554 0.35891276-0.02980554 0.29557586-0.05836951 0.44584748-0.09065935-0.35891276-0.09065935s-0.65448861 0.03228982-0.32910723 0.09065935C211.54667634 1012.43202097 211.66589967 1012.43202097 212.02357089 1012.40221545z" p-id="4887"></path></svg></div><div class="mark-reply">回复</div></div><div class="mark-sub-container"><div class="mark-sub-list"></div></div>');
                let avatar = $('<div class="root-img-avatar"></div>')

                if (typeof browserServer.note.viewerUserAvatar != 'undefined') {
                    avatar.css('background-image', 'url(' + browserServer.localhost_user_avatar + browserServer.note.viewerUserAvatar + ')')
                    markRootItem.find('.root-avatar').append(avatar).children('svg').remove()
                }
                markRootItem.find('a').prop('href', browserServer.localhost + 'homepage/' + browserServer.note.viewerUid);
                markRootItem.find('.user-nickname').text(browserServer.note.viewerNickname);

                markRootItem.find('.send-date').text(otherEvent.formatDate());
                markRootItem.find('.user-mark').text(value);
                // 该变量的作用是判断点击与否
                markRootItem.find('.mark-like-number')[0].flag = true;
                markRootItem.find('.mark-unlike')[0].flag = true;
                let rootMark = otherEvent.getUniqueString(20);
                // 设置父评论的唯一标识，字符串长度为 20
                markRootItem[0].setAttribute('root-mark', rootMark);
                markRootItem[0].setAttribute('uid', browserServer.note.viewerUid);

                allObject.markList.prepend(markRootItem);
                module.markSubInput.remove();
                allObject.markAll.text(parseInt(allObject.markAll.text()) + 1);
                allObject.markNumber.text(parseInt(allObject.markNumber.text()) + 1);

                // 整理数据并上传
                otherEvent.buildMark(value, 'buildRootMark', rootMark);
            });
        },


        // 初始化关注框
        initialFollow: function () {
            if (browserServer.note.followed) {
                if (browserServer.note.fans) {
                    allObject.followBtn.text('已互粉').removeClass('unfollow').addClass('followed');
                } else {
                    allObject.followBtn.text('已关注').removeClass('unfollow').addClass('followed');
                }
            } else {
                allObject.followBtn.text('关注').removeClass('followed').addClass('unfollow');
            }
        },


        // 关注及取消关注
        handleFollow: function () {
            // 是本人就添加点击事件，不是就移除关注框
            if (!browserServer.note.me) {
                allObject.followBtn.click(function () {
                    let data = {
                        requestType: "followsFans",
                        operation: "changeFollowStatus",
                        uid: browserServer.uid,
                    }
                    if (!browserServer.note.followed) {
                        $(this).text('已关注').removeClass('unfollow').addClass('followed');
                        data.followStatus = "confirm";
                        browserServer.note.followed = false;
                    } else {
                        $(this).text('关注').removeClass('followed').addClass('unfollow');
                        data.followStatus = "cancel";
                        browserServer.note.followed = true;
                    }
                    browserServer.browserServerCore(data);
                });
            } else {
                allObject.followBtn.remove();
            }
        },

        // 控制 note-body 的高度，以使目录面板使用 position:sticky ，因为浮动原因，note-body 不能跟随内容变化而变化
        controlHeight: function () {
            $('.huadiao-note-body').height($('html').height());
        },

        /**
         * 建立评论
         * @param markContent 笔记内容
         * @param markType 评论的类型，有两种 buildRootMark (父评论), buildSubMark (子评论)
         * @param rootMark 父评论的唯一标识
         * @param subMark 子评论的唯一标识
         */
        buildMark: function (markContent, markType, rootMark, subMark) {
            let data = {
                requestType: "note",
                operation: "addNoteMark",
                noteId: browserServer.noteId,
                authorUid: browserServer.uid,
                markedUid: browserServer.otherUid,
                rootMarkId: rootMark,
                subMarkId: subMark,
                markContent
            };
            console.log(data)
            browserServer.browserServerCore(data);
        },

        // 输出指定日期格式
        formatDate: function () {
            let join = '-';
            let date = new Date();
            let year = date.getFullYear();
            let month = date.getMonth() + 1;
            month = month < 10 ? '0' + month : month;
            let day = date.getDate();
            day = day < 10 ? '0' + day : day;
            let hour = date.getHours();
            hour = hour < 10 ? '0' + hour : hour;
            let minute = date.getMinutes();
            minute = minute < 10 ? '0' + minute : minute;
            return year + join + month + join + day + ' ' + hour + ':' + minute;
        },

        // 创建一个大概率不会重复的字符串
        getUniqueString: window.getUniqueString,

        // 收藏笔记
        noteStar: function () {
            let data = {
                requestType: "star",
                operation: "starNote",
                authorUid: browserServer.uid,
                noteId: browserServer.noteId
            }
            browserServer.browserServerCore(data);
        },

        // 取消收藏笔记
        noteCancelStar: function () {
            let data = {
                requestType: "star",
                operation: "cancelNoteStar",
                authorUid: browserServer.uid,
                noteId: browserServer.noteId
            };
            browserServer.browserServerCore(data);
        },

        // 给笔记点赞
        noteLike: function () {
            let data = {
                requestType: "note",
                operation: "likeNote",
                authorUid: browserServer.uid,
                noteId: browserServer.noteId
            }
            browserServer.browserServerCore(data);
        },

        // 取消笔记点赞
        cancelNoteLike: function () {
            let data = {
                requestType: "note",
                operation: "cancelLikeNote",
                authorUid: browserServer.uid,
                noteId: browserServer.noteId
            }
            browserServer.browserServerCore(data);
        },

        // 不喜欢笔记
        noteUnlike: function () {
            let data = {
                requestType: "note",
                operation: "unlikeNote",
                authorUid: browserServer.uid,
                noteId: browserServer.noteId
            };
            browserServer.browserServerCore(data);
        },

        // 取消不喜欢笔记
        cancelNoteUnlike: function () {
            let data = {
                requestType: "note",
                operation: "cancelUnlikeNote",
                authorUid: browserServer.uid,
                noteId: browserServer.noteId
            };
            browserServer.browserServerCore(data);
        },

        // 封装上述重复代码
        noteOperation: function () {
        }
    };

    otherEvent.moveColor();
    otherEvent.clickColor(allObject.likeIcon, 'like-icon', otherEvent.noteLike, otherEvent.cancelNoteLike, allObject.likeNumber);
    otherEvent.clickColor(allObject.unlikeIcon, 'unlike-icon', otherEvent.noteUnlike, otherEvent.cancelNoteUnlike);
    otherEvent.clickColor(allObject.starIcon, 'star-icon', otherEvent.noteStar, otherEvent.noteCancelStar, allObject.starNumber);
    otherEvent.handleDirectory();
    otherEvent.replyInput();
    otherEvent.clickLike();
    otherEvent.clickUnlike();
    otherEvent.addRootReply();
    otherEvent.addSubReply();
    otherEvent.controlHeight();


});