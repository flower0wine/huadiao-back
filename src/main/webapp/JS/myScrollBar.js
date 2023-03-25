/**
 * 自制的滚动条
 * 添加滚动条要给父级添加
 */
// 使用立即执行函数
(function ($) {  // 加入 $ 才能使用 document.method() 的方式调用

    // 滚动条函数
    $.fn.myScrollBar = function (options) {
        let $ = jQuery;
        // 添加滚动条的目标
        let target = $(this);
        // 初始化目标
        initial(target);
        // 这是中间层，避免浮动和定位的不确定
        let newChildren = target.children();
        // 这是之前的子元素
        let oldChildren = newChildren.children()[0];
        // 记录开始时的 top
        let oldTop = parseInt($(oldChildren).css('top'));

        if(target.height() >= newChildren.height()){
            $('.scrollbar-track').show();
        } else {
            $('.scrollbar-track').hide();
        }
        let lastSecond = [];

        // 鼠标进入区域
        $(this).off('mouseenter').mouseenter(function () {
            // 滚轮滚动
            $(this).off('mousewheel')[0].addEventListener('mousewheel', function (e) {
                // 阻止浏览器的页面滚动
                e.preventDefault && e.preventDefault();

                lastSecond.push({
                    delta: Math.floor(Math.abs(e.deltaY)),
                    timestamp: new Date().getTime()
                });

                // 鼠标滚轮速度
                let pixelsPerSecond = displayLastSecond();

                let standard = 30;

                // 鼠标滚轮方向
                if(e.wheelDelta > 0){
                    // 内容下移
                    if($(oldChildren).css('top').split('px')[0] <= oldTop) {
                        $(oldChildren).css({
                            top: parseInt($(newChildren.children()[0]).css('top')) + (pixelsPerSecond / 15 / standard) * standard + 'px'
                        });
                    } else {
                        $(oldChildren).css('top', oldTop);
                    }
                    console.log($(oldChildren).css('top').split('px')[0])


                } else {
                    // 内容上移
                    if(-$(oldChildren).css('top').split('px')[0] + newChildren.height() <= $(oldChildren).height()){
                        $(oldChildren).css({
                            top: parseInt($(newChildren.children()[0]).css('top')) - (pixelsPerSecond / 15 / standard) * standard + 'px'
                        });
                    }
                    console.log($(oldChildren).css('top'))
                    console.log(-$(oldChildren).css('top').split('px')[0])
                }
            });
        });

        function displayLastSecond() {
            let now = new Date().getTime();
            let total = 0;
            let timestamps = 0;
            for (let x = 0; x < lastSecond.length; x++) {
                if (now - lastSecond[x].timestamp <= 1000) {
                    total += lastSecond[x].delta;
                    timestamps++;
                } else {
                    lastSecond.splice(x, 1);
                    x--;
                }
            }
            if (timestamps === 0) {
                return 0;
            }
            return Math.round(total);
        }



        // 鼠标离开区域
        $(this).off('mouseleave').mouseleave(function () {

        });



        // 当父盒子尺寸变化时
        target.off('resize').resize(function (){
            if(target.height() >= newChildren.height()){
                $('.scrollbar-track').show();
            } else {
                $('.scrollbar-track').hide();
            }
        });

        // 当子元素尺寸变化时
        newChildren.off('resize').resize(function (){
            if(target.height() >= newChildren.height()){
                $('.scrollbar-track').show();
            } else {
                $('.scrollbar-track').hide();
            }
        });

    };


    // 对目标初始化
    function initial(target) {
        // 深拷贝
        let children = target.children().clone(true);
        // 目标的最近一级子元素移除，因为不确定父元素到底是浮动还是，定位，需要添加一个中间元素，设为相对定位
        target.children().remove();
        let between = $('<div class="scrollbar-container"></div>');
        // 保留元素原先的位置
        children.css({
            position: 'absolute',
            top: parseInt(children.css('margin-top')) + parseInt(target.css('padding-top')),
            left: parseInt(children.css('margin-left')) + parseInt(target.css('padding-left'))
        });
        between.append(children);
        target.append(between);

        // 下面添加滚动条组件
        // 轨道
        let scrollBarTrack = $('<div class="scrollbar-track"><div class="scrollbar-thumb"></div></div>');
        // 滑块
        let scrollBarThumb = scrollBarTrack.children();
        // 轨道高度
        scrollBarTrack.css({
            height: 'calc(' + (target.height() - 40) + 'px)',
        });

        // 内容与父元素的高度比例
        let scale = between.height() / children.height();
        if(scale > 1){
            scale = 1;
        }
        // 滑块高度
        let thumbHeight = (target.height() * scale);
        // 防止滑块太小
        if (thumbHeight < 20) {
            thumbHeight = 20;
        }
        scrollBarThumb.css('height', thumbHeight)

        // 添加组件
        between.append(scrollBarTrack);

    }


})(jQuery)
