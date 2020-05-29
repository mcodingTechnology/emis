/*
 * jQuery jSlots Plugin
 * http://matthewlein.com/jslot/
 * Copyright (c) 2011 Matthew Lein
 * Version: 1.0.2 (7/26/2012)
 * Dual licensed under the MIT and GPL licenses
 * Requires: jQuery v1.4.1 or later
 */

(function($){

    $.jSlots = function(el, options){

        var base = this;

        base.$el = $(el);
        base.el = el;

        base.$el.data("jSlots", base);

        base.init = function() {

            base.options = $.extend({},$.jSlots.defaultOptions, options);

            base.setup();
            base.bindEvents();

        };


        // --------------------------------------------------------------------- //
        // DEFAULT OPTIONS
        // --------------------------------------------------------------------- //

        $.jSlots.defaultOptions = {
            url:'', //获取中奖结果地址
            arr:[], //中奖结果
            html:[],    //html: 滚动列表
            number : 5,          // 数字: 一共几个滚动栏
            level:null,          // 数字: 抽奖等级
            winnerNumber : 1,    // 数字或数组：大奖的序号
            spinner : '',        // CSS选择器: 启动事件绑定的元素
            spinEvent : 'click', // 字符串: 触发活动的事件
            onStart : $.noop,    // 函数:旋转启动之前,
            onEnd : $.noop,      // 函数:旋转结束时，他传递一个参数（最终编号：数组）。最终编号为每个中奖的索引 run on spin end. It is passed (finalNumbers:Array). finalNumbers gives the index of the li each slot stopped on in order.
            onWin : $.noop,      // 函数:中奖号码，他传递（winCount:数字，winners：数组） run on winning number. It is passed (winCount:Number, winners:Array)
            easing : 'swing',    // 字符串: 停止时的缓冲类型，由easing插件提供支持
            time : 70000,         // 数字: 旋转动画总时间
            loops : 60            // Number: 旋转圈数
        };

        // --------------------------------------------------------------------- //
        // HELPERS
        // --------------------------------------------------------------------- //

        base.randomRange = function(low, high) {
            console.log(base.options.arr)
            var num = base.options.arr.pop();
            return num;
            //return Math.floor( Math.random() * (1 + high - low) ) + low;    //返回随机中奖结果
        };

        // --------------------------------------------------------------------- //
        // VARS
        // --------------------------------------------------------------------- //

        base.isSpinning = false;
        base.spinSpeed = 0;
        base.winCount = 0;
        base.doneCount = 0;

        base.$liHeight = 0;
        base.$liWidth = 0;

        base.winners = [];
        base.allSlots = [];

        base.cur = 0;   //记录当前应该停止的拉霸序号

        // --------------------------------------------------------------------- //
        // FUNCTIONS
        // --------------------------------------------------------------------- //


        base.setup = function() {

            // 设置滚动个数

            var $list = base.$el;
            var $li = $list.find('li').first();

            base.$liHeight = $li.outerHeight();
            base.$liWidth = $li.outerWidth();

            base.liCount = base.$el.children().length;

            base.listHeight = base.$liHeight * base.liCount;

            base.increment = (base.options.time / base.options.loops) / base.options.loops; //设置滚动一次所需时间

            $list.css('position', 'relative');

            $li.clone().appendTo($list);

            base.$wrapper = $list.wrap('<div class="jSlots-wrapper"></div>').parent();

            // 先除去原来的
            base.$el.remove();

            // 复制列表
            for (var i = base.options.number - 1; i >= 0; i--){
                base.allSlots.push( new base.Slot(i) );  //根据设定的number值循环添加拉霸实例
            }

        };
        //绑定触发事件
        base.bindEvents = function() {
            $(base.options.spinner).bind(base.options.spinEvent, function(event) {
                if (!base.isSpinning) {
                    base.playSlots();
                }else{
                    //base.doneCount++;
                    // base.allSlots[(base.doneCount)].run = false;
                    //if (base.doneCount === base.options.number) base.isSpinning = false;    //如果已经全部停止，则结束本轮抽奖
                    // $.each(base.allSlots, function(index, val) {
                    //     this.run = false;
                    // });

                    /*从左到右一个一个停止*/
                    if (base.options.level ==3) {
                        base.allSlots[0].run = false;
                        setTimeout(function(){
                            base.allSlots[1].run = false;
                        },1000);
                        setTimeout(function(){
                            base.allSlots[2].run = false;
                        },2000);
                        setTimeout(function(){
                            base.allSlots[3].run = false;
                        },3000);
                        setTimeout(function(){
                            base.allSlots[4].run = false;
                            base.isSpinning = false
                        },4000);
                    }else if (base.options.level ==2) {
                        base.allSlots[0].run = false;
                        setTimeout(function(){
                            base.allSlots[1].run = false;
                        },1000);
                        setTimeout(function(){
                            base.allSlots[2].run = false;
                            base.isSpinning = false;
                        },2000);
                    }
                    
                }
            });
        };

        // 生成拉霸滚动框
        base.Slot = function(i) {

            this.spinSpeed = 0;
            //this.el = base.$el.clone().appendTo(base.$wrapper)[0];
            var html = base.options.html[i] ? base.options.html[i] : base.options.html[0];
            this.el = $(html).appendTo(base.$wrapper)[0];
            this.$el = $(this.el);
            this.loopCount = 0;
            this.number = 0;

        };


        base.Slot.prototype = {

            // 执行一次旋转
            spinEm : function() {

                var that = this;

                that.$el
                    .css( 'top', -base.listHeight )
                    .animate( { 'top' : '0px' }, that.spinSpeed, 'linear', function() {
                        that.lowerSpeed();
                    });

            },

            lowerSpeed : function() {

                //this.spinSpeed += base.increment;
                this.spinSpeed = base.increment;    //将原来越来越慢的效果设置为所有都匀速执行
                this.loopCount++;

                if (this.run) { //判断是否还需旋转N

                    this.spinEm();

                } else {

                    this.finish();

                }
            },

            // 最后一次旋转
            finish : function() {

                var that = this;

                var endNum = base.randomRange( 1, base.liCount );
                var finalPos = - ( (base.$liHeight * endNum) - base.$liHeight );
                var finalSpeed = ( (this.spinSpeed * 0.5) * (base.liCount) ) / endNum;

                that.$el
                    .css( 'top', -base.listHeight )
                    //.animate( {'top': finalPos}, finalSpeed, base.options.easing, function() {
                    .animate( {'top': finalPos}, this.spinSpeed, base.options.easing, function() {
                        base.checkWinner(endNum, that);
                    });

            }

        };

        base.checkWinner = function(endNum, slot) {

            base.doneCount++;
            // 设置拉霸结束时的数字
            slot.number = endNum;

            // 如果是获奖数组
            if (
                ( $.isArray( base.options.winnerNumber ) && base.options.winnerNumber.indexOf(endNum) > -1 ) ||
                endNum === base.options.winnerNumber
                ) {

                // its a winner!
                base.winCount++;
                base.winners.push(slot.$el);

            }

            if (base.doneCount === base.options.number) {

                var finalNumbers = [];

                $.each(base.allSlots, function(index, val) {
                    finalNumbers[index] = val.number;
                });

                if ( $.isFunction( base.options.onEnd ) ) {
                    base.options.onEnd(finalNumbers);
                }

                if ( base.winCount && $.isFunction(base.options.onWin) ) {
                    base.options.onWin(base.winCount, base.winners, finalNumbers);
                }
                base.isSpinning = false;
            }
            console.log(base.isSpinning)
        };


        base.playSlots = function() {
            base.isSpinning = true;
            var postData= {"level":base.options.level,"num":base.options.number}
            $.ajax({
                type:"get",
                url:base.options.url,
                // data:JSON.stringify(postData),
                dataType:"json"
            }).done(function(data){
                // var data = {"errorCode":"00","errorMessage":"生成抽奖结果完毕.","returnObject":[{"lottoPrizeId":14,"lottoPrizeName":"路虎山地自行车","openId":"13d35b3b231f991e4072c154cc79b8ec","memberName":"万丽文","picUrl":"http://shp.qpic.cn/bizmp/2uZibrk6nmW2LKcSxibLcuKhyufU2ycjCaBliaiaHk2uA9eCXpMibeM8JAA/","departmentName":"财务中心","lottoPrizeGroupId":"3","lottoPrizeGroupName":"三等奖"},{"lottoPrizeId":14,"lottoPrizeName":"路虎山地自行车","openId":"13dfd91bb425f7a5162cdbb4148957cb","memberName":"唐炜","picUrl":"http://shp.qpic.cn/bizmp/2uZibrk6nmW2LKcSxibLcuKhyufU2ycjCaCEWJ9De2GskFu978hPkclg/","departmentName":"信息技术部","lottoPrizeGroupId":"3","lottoPrizeGroupName":"三等奖"},{"lottoPrizeId":14,"lottoPrizeName":"路虎山地自行车","openId":"149604eebe2933bd9bbc0fe46ea8f699","memberName":"陈栋","picUrl":"N/A","departmentName":"信息技术部","lottoPrizeGroupId":"3","lottoPrizeGroupName":"三等奖"},{"lottoPrizeId":14,"lottoPrizeName":"路虎山地自行车","openId":"148c47f468d22392dc109344b33a1f88","memberName":"李康林","picUrl":"http://shp.qpic.cn/bizmp/2uZibrk6nmW2LKcSxibLcuKhyufU2ycjCa82yfgRxSWgCtSxCPydBVmg/","departmentName":"信息技术部","lottoPrizeGroupId":"3","lottoPrizeGroupName":"三等奖"},{"lottoPrizeId":14,"lottoPrizeName":"路虎山地自行车","openId":"13afcb3fe04fc1ee9ff84db49bead2db","memberName":"唐金银(Angela)","picUrl":"http://shp.qpic.cn/bizmp/2uZibrk6nmW2LKcSxibLcuKhyufU2ycjCas4ic7Xx26f8uRnYu7icOjWjg/","departmentName":"董秘办","lottoPrizeGroupId":"3","lottoPrizeGroupName":"三等奖"}]}
                // var data = {"errorCode":"00","errorMessage":"生成抽奖结果完毕.","returnObject":[{"lottoPrizeId":14,"lottoPrizeName":"路虎山地自行车","openId":"13d35b3b231f991e4072c154cc79b8ec","memberName":"万丽文","picUrl":"http://shp.qpic.cn/bizmp/2uZibrk6nmW2LKcSxibLcuKhyufU2ycjCaBliaiaHk2uA9eCXpMibeM8JAA/","departmentName":"财务中心","lottoPrizeGroupId":"3","lottoPrizeGroupName":"三等奖"},{"lottoPrizeId":14,"lottoPrizeName":"路虎山地自行车","openId":"13dfd91bb425f7a5162cdbb4148957cb","memberName":"唐炜","picUrl":"http://shp.qpic.cn/bizmp/2uZibrk6nmW2LKcSxibLcuKhyufU2ycjCaCEWJ9De2GskFu978hPkclg/","departmentName":"信息技术部","lottoPrizeGroupId":"3","lottoPrizeGroupName":"三等奖"},{"lottoPrizeId":14,"lottoPrizeName":"路虎山地自行车","openId":"149604eebe2933bd9bbc0fe46ea8f699","memberName":"陈栋","picUrl":"N/A","departmentName":"信息技术部","lottoPrizeGroupId":"3","lottoPrizeGroupName":"三等奖"}]}                
                
                if(data.code !='0'){
                    base.isSpinning = false;

                    //抽奖失败后直接跳到中奖名单界面
                    if (base.options.level ==3) {
                        window.location.href = './slotResult.html'
                    }else if (base.options.level == 2) {
                        window.location.href = './slotresult2.html'
                    }
                }
                
                if (base.options.level == 2) {
                    if(!$.isArray(data.data.memberList) || data.data.memberList.length!=3){
                        base.isSpinning = false;
                        return alert('错误，未返回正确的中奖人数！') || false;
                    }
                }else if (base.options.level ==3) {
                    if(!$.isArray(data.data.memberList) || data.data.memberList.length!=5){
                        base.isSpinning = false;
                        return alert('错误，未返回正确的中奖人数！') || false;
                    }
                }
                
                //插入中奖人员及中奖序号
                var arr = []
                for(var i=0;i<base.options.number;i++){
                    var high = base.liCount,low = 1;
                    arr.push(Math.floor( Math.random() * (1 + high - low) ) + low);
                    //arr.push(1)
                }
                base.options.arr = arr.concat();

                
                base.winCount = 0;
                base.doneCount = 0;
                base.winners = [];

                if ( $.isFunction(base.options.onStart) ) {
                    base.options.onStart();
                }

                $.each(base.allSlots, function(index, val) {
                    var i = arr.pop();
                    var n = data.data.memberList[index];
                    var curName = filter(n.name);
                    /*停止滚动的最后一条信息*/
                    // '<div class="img"><img src="'+((n.picUrl=="N/A" ||n.picUrl=="") ? "file/photo.png" : n.picUrl)+'"></div> <div class="branch">'+n.departmentName+'</div> <div class="name">'+curName+'</div> '
                    $('.jSlots-wrapper ul').eq(index).find('li').eq(i-1).html('<div class="img"><img src="'+((n.head_img_url=="N/A" ||n.head_img_url=="" ||n.head_img_url==undefined) ? "file/photo.png" : n.head_img_url)+'"></div> <div class="branch" style="padding-bottom:30px"></div> <div class="name">'+curName+'</div> ')
                    this.spinSpeed = 0;
                    this.loopCount = 0;
                    this.run = true;
                    this.spinEm();
                });
            }).fail(function(){
                base.isSpinning = false;
            })
        };


        base.onWin = function() {
            if ( $.isFunction(base.options.onWin) ) {
                base.options.onWin();
            }
        };


        // 初始化运行
        base.init();
    };

    var filter = function(txt){
        var txt = txt.replace(/（/, "(");
        return txt.split('(')[0];
    }


    // --------------------------------------------------------------------- //
    // JQUERY FN
    // --------------------------------------------------------------------- //

    $.fn.jSlots = function(options){
        if (this.length) {
            return this.each(function(){
                (new $.jSlots(this, options));
            });
        }
    };

})(jQuery);
