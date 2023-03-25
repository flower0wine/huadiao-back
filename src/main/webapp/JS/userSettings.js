$(function () {

    var allObject = {
        collection: $('.collection'),
        bornDate: $('.bornDate'),
        Fanju: $('.Fanju'),
        school: $('.school'),
        follow: $('.follow'),
        canvases: $('.canvases'),
        homepage: $('.homepage'),
        messageRemind: $('.messageRemind'),
        messageReply: $('.messageReply'),
        messageLikes: $('.messageLikes'),
    }

    var browserServer = {

        localhost: window.localhost,

        formData: null,

        userSettings: null,

        getUserSettings: function () {
            browserServer.formData = new FormData();
            browserServer.formData.append('getSettings', '');
            let data = {
                requestType: "userSettings",
                operation: "getUserSettings"
            };
            browserServer.browserServerCore(data, function (response) {
                console.log(response.data);
                if (typeof response.data.uid != 'undefined') {
                    browserServer.userSettings = response.data;
                    otherEvent.initialSettings();
                }
            });
        },

        // 更改隐私设置
        modifyPrivacySettings: function (setting) {
            let data = {
                requestType: "userSettings",
                operation: "modifyUserSettings",
                option: setting
            };
            browserServer.browserServerCore(data, function (response) {
                console.log(response.data)
            });
        },

        browserServerCore: function (data, fn) {
            axios({
                url: browserServer.localhost + 'dispatcherServlet',
                method: 'post',
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded; charset=utf-8"
                },
                data
            }).then(fn);
        }
    };

    browserServer.getUserSettings();

    var otherEvent = {

        // 初始化设置
        initialSettings: function (){
            otherEvent.setSliderCSS(allObject.collection, browserServer.userSettings.publicStarStatus);
            otherEvent.setSliderCSS(allObject.bornDate, browserServer.userSettings.publicBornStatus);
            otherEvent.setSliderCSS(allObject.Fanju, browserServer.userSettings.publicFanjuStatus);
            otherEvent.setSliderCSS(allObject.school, browserServer.userSettings.publicSchoolStatus);
            otherEvent.setSliderCSS(allObject.follow, browserServer.userSettings.publicFollowStatus);
            otherEvent.setSliderCSS(allObject.canvases, browserServer.userSettings.publicCanvasesStatus);
            otherEvent.setSliderCSS(allObject.homepage, browserServer.userSettings.publicHomepageStatus);
            otherEvent.setSliderCSS(allObject.messageRemind, browserServer.userSettings.messageRemindStatus);
            otherEvent.setSliderCSS(allObject.messageReply, browserServer.userSettings.messageReplyStatus);
            otherEvent.setSliderCSS(allObject.messageLikes, browserServer.userSettings.messageLikeStatus);
        },

        // 设置滑块样式
        setSliderCSS: window.setSliderCSS,

        slide: window.slide,
    }


    otherEvent.slide(allObject.collection, 'publicStar', browserServer.modifyPrivacySettings);
    otherEvent.slide(allObject.bornDate, 'publicBornDate', browserServer.modifyPrivacySettings);
    otherEvent.slide(allObject.Fanju, 'publicFanju', browserServer.modifyPrivacySettings);
    otherEvent.slide(allObject.school, 'publicSchool', browserServer.modifyPrivacySettings);
    otherEvent.slide(allObject.follow, 'publicFollow', browserServer.modifyPrivacySettings);
    otherEvent.slide(allObject.canvases, 'publicCanvases', browserServer.modifyPrivacySettings);
    otherEvent.slide(allObject.homepage, 'publicHomepage', browserServer.modifyPrivacySettings);
    otherEvent.slide(allObject.messageRemind, 'messageNotice', browserServer.modifyPrivacySettings);
    otherEvent.slide(allObject.messageReply, 'replyNotice', browserServer.modifyPrivacySettings);
    otherEvent.slide(allObject.messageLikes, 'likeNotice', browserServer.modifyPrivacySettings);


})