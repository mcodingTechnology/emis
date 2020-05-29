var turnplate;
requirejs(['jquery','api','common'],function($,api,common){
	turnplate = {
		turnplateBox : $('#turnplate'),
		turnplateBtn : $('#platebtn'),
		lightDom : $('#turnplatewrapper'),
		msgBox : $('#msg'),
		height : '710', //帧高度
		lightSwitch : 0, //闪灯切换开关. 0 和 1间切换
		minResistance : 5, //基本阻力
		Cx : 0.01, //阻力系数 阻力公式：  totalResistance = minResistance + curSpeed * Cx;	
		accSpeed : 15, //加速度
		accFrameLen : 40, //加速度持续帧数
		slowdown : true,	//到最高速时是否减速
		maxSpeed : 250, //最大速度 
		minSpeed : 20, //最小速度 
		frameLen : 16, //帧总数
		totalFrame : 0, //累计帧数,每切换一帧次数加1
		curFrame : 0, //当前帧
		curSpeed : 20, //上帧的速度
		lotteryTime : 0, //抽奖次数
		lotteryIndex : 2, //奖品index
		errorIndex : 0, //异常时的奖品指针
		//initBoxEle : $('#turnplate #init'),
		progressEle : $('#turnplate #init span'),
		initProgressContent : '~~~^_^~~~', //初始化进度条的内容
		initFreshInterval : 500, //进度条刷新间隔
		virtualDistance : 10000, //虚拟路程,固定值，速度越快，切换到下帧的时间越快: 切换到下帧的时间 = virtualDistance/curSpeed
		isStop : false, //抽奖锁,为true时，才允许下一轮抽奖
		timer2 : undefined, //计时器
		initTime : undefined,
		showMsgTime : 2000, //消息显示时间
		lotteryChannel: '',
		isReady:false,	//是否准备好闪动
		running : false,	//是否中奖部门在闪动
		person : [],	//中奖部门人员列表
		curPerson : {},	//中奖人员
		inter : 1,	//中奖部门人员闪动间隔
		lotteryType : {
			'董事长办公室' : 0,	
			'总经理办公室' : 1,
			'佰健子公司' : 2,
			'董秘办投资发展中心' : 3,
			'财务中心' : 4,
			'法务部' : 5,
			'供应链中心' : 6,
			'科技中心' : 7,
			'人力行政中心' : 8,	
			'公共事务中心' : 9,
			'销售中心' : 10,
			'市场中心' : 11,
			'审计部' : 12,
			'信息技术部' : 13,
			'营养家中心' : 14,
			'电子商务部' : 15
		},
		lotteryText : [
			'董事长<br>办公室',
			'总经理<br>办公室',
			'佰健子公司',
			'董秘办<br>投资发展中心',
			'财务中心',
			'法务部',
			'供应链<br>中心',
			'科技中心',
			'人力行政<br>中心',
			'公共事务<br>中心',
			'销售中心',
			'市场中心',
			'审计部',
			'信息<br>技术部',
			'营养家<br>中心',
			'电子<br>商务部'
		],
		//初始化
		init : function() {
			//this.initAnimate()
			this.freshLottery();
			this.turnplateBtn.click($.proxy(function(){
				this.click();	
			},this));	
		},
/*
		//初始化动画
		initAnimate : function() {
			this.initBoxEle.show();
			clearTimeout(this.initTimer);
			var curLength = this.progressEle.text().length,
				totalLength = this.initProgressContent.length;

			if (curLength < totalLength) {
				this.progressEle.text(this.initProgressContent.slice(0, curLength + 1));
			}else{
				this.progressEle.text('');
			}
			this.initTimer = setTimeout($.proxy(this.initAnimate, this), this.initFreshInterval);
		},

		//停止初始化动画
		stopInitAnimate : function() {
			clearTimeout(this.initTimer);
			this.initBoxEle.hide();
		},*/

		freshLotteryTime : function() {
			$('#top-menu').find('.lottery-times').html(this.lotteryTime);
		},

		//更新抽奖次数
		freshLottery : function() {
			//this.stopInitAnimate();
			this.setBtnHover();
			this.isStop = true;
			this.lotteryTime = 1;
			this.freshLotteryTime();

		},

		//设置按钮三态
		setBtnHover : function() {
			//按钮三态
			/*$('#platebtn').hover(function(){
				$(this).addClass('hover');
			},function() {
				$(this).removeClass('hover click');
			}).mousedown(function(){
				$(this).addClass('click');
			}).mouseup(function(){
				$(this).removeClass('click');
			});	*/
		},

		//获取奖品
		lottery : function() {
			//this.lotteryIndex = undefined;
			this.lotteryTime--;
			this.freshLotteryTime();
			this.totalFrame = 0;
			this.curSpeed = this.minSpeed;
			this.turnAround();
			this.slowdown = false;	//到最高速时是否减速
			//this.lotteryIndex = typeof this.lotteryType[2] !== 'undefined' ? this.lotteryType[2] : this.errorIndex;	
		},

		//点击抽奖
		click : function() {
			var _this = this;
			//如果还维持在最高速度
			if(!this.slowdown){
				this.slowdown = true;
				return;
			}
			//如果准备闪动中奖部门人员
			if(this.isReady){
				this.beginTimer();
				this.isReady = false;
				return;
			}
			//加锁时
			if(this.isStop == false) {
				//this.showMsg('现在还不能抽奖哦');
				return;
			} 
				
			//中奖部门在闪动时
			if(this.running){
				this.endTimer();
				return;
			}
			$.getJSON(api.generate,{"lottoPrizeGroupId":6,"lottoPrizeCount":1}).done(function(data){
				//var data = {"errorCode":"00","errorMessage":"生成抽奖结果完毕.","returnObject":[{"lottoPrizeId":17,"lottoPrizeName":"苹果Macbook Air","openId":"14aec6621754ffcfb26b2d744feae830","memberName":"刘銮佳","picUrl":"N/A","departmentName":"健康管理项目组","firstLevelDepartmentName":"科技中心","lottoPrizeGroupId":"6","lottoPrizeGroupName":"特等奖"}]}
				if(data.errorCode!='00') return alert(data.errorMessage) || false;
				if(data.returnObject && data.returnObject.length>0){
					_this.lotteryIndex = _this.lotteryType[data.returnObject[0].firstLevelDepartmentName];
					_this.curPerson = data.returnObject[0];
					if(!_this.lotteryIndex) return alert('错误，未找到对应部门！') || false;
					_this.person = common.branch(data.returnObject[0].departmentName);
					if(data.returnObject[0].firstLevelDepartmentName=='董秘办投资发展中心'){
						_this.person = common.branch('董秘办','投资发展中心');
					}else{
						_this.person = common.branch(data.returnObject[0].firstLevelDepartmentName);
					}
					_this.lottery();
				}			
			})
			

		},

		//更新当前速度
		freshSpeed : function() {
			var totalResistance = this.minResistance + this.curSpeed * this.Cx;	//阻力=基本阻力+上帧速度x阻力系数
			var accSpeed = this.accSpeed;	//加速度
			var curSpeed = this.curSpeed;	//当速度 = 上帧速度
			if(this.totalFrame >= this.accFrameLen) {//如果累计帧数（每次加1）大于加速度持续帧数
				accSpeed = 0;	//加速度=0
				if(!this.slowdown){	//到最高速时，是否减速，不减则加速度 = 阻力，让其不减速
					accSpeed = totalResistance;
				}
			}
			curSpeed = curSpeed - totalResistance + accSpeed;	//当前速度 = 上帧速度 - 阻力 + 加速度

			if(curSpeed < this.minSpeed){	//如果当前速度<最低速度
				curSpeed = this.minSpeed;	//当前速度 = 最低速度
			}else if(curSpeed > this.maxSpeed){	//如果当前速度>最高速度
				curSpeed = this.maxSpeed;	//当前速度=最高速度
			}

			this.curSpeed  = curSpeed;	//更新为当前速度
		},

		//闪灯,切换到下一张时调用
		switchLight : function() {
			//this.lightSwitch = this.lightSwitch === 0 ? 1 : 0;
			//var lightHeight = -this.lightSwitch * this.height;
			//this.lightDom.css('backgroundPosition','0 ' + lightHeight.toString() + 'px');	
		},

		//旋转,trunAround,changeNext循环调用
		turnAround : function() {
			//加锁
			this.isStop = false;
			var intervalTime = parseInt(this.virtualDistance/this.curSpeed);		
			this.timer = window.setTimeout('turnplate.changeNext()', intervalTime);		
		},

		//停止转圈
		showAwards : function(){
			this.isReady = true;
			//$('#lotteryMsg').dialog('open');
		},

		//显示提示信息
		showMsg : function(msg, isFresh) {
			isFresh = typeof isFresh == 'undefined' ? false : true;
			if(typeof msg != 'string'){
				msg = '今天已经抽过奖了，明天再来吧';
			}
			var msgBox = this.msgBox;
			var display = msgBox.css('display');

			msgBox.html(msg);	

			window.clearTimeout(this.timer2);
			msgBox.stop(true,true).show();
			var fadeOut = $.proxy(function() {
				this.msgBox.fadeOut('slow');
			}, this);
			this.timer2 = window.setTimeout(fadeOut, this.showMsgTime);
		},


		//切换到下帧
		changeNext : function() {
			//判断是否应该停止
			if(this.lotteryIndex !== undefined && this.curFrame == this.lotteryIndex && this.curSpeed <= this.minSpeed+10 && this.totalFrame > this.accFrameLen) {
				this.isStop = true;
				this.showAwards();
				return; 		
			}

			var nextFrame =  this.curFrame+1 < this.frameLen ? this.curFrame+1 : 0;
			var bgTop = - nextFrame * this.height;		
			this.turnplateBox.css('backgroundPosition','0 ' + bgTop.toString() + 'px');
			$('#plate2').hide();
			$('#plateBranch').html(this.lotteryText[nextFrame]).show();
			this.switchLight();
			this.curFrame = nextFrame;
			this.totalFrame ++;
			this.freshSpeed();
			this.turnAround();
		},
		//中奖部门人员闪动抽奖
		//显示当前人员
		updateRndNum : function(end){	//end 是否停止
			var cur = '';
			if(!end){
				var num = Math.floor(Math.random()*turnplate.person.length);
				cur =  turnplate.person[num];
			}else{
				cur  = turnplate.curPerson;
			}
			$('#plateBranch').hide();
			var curName = common.filter(cur.memberName);
			$('#plate2').show().html('<div class="img"><img src="'+((cur.picUrl=="N/A" ||cur.picUrl=="") ? "file/photo.png" : cur.picUrl)+'"></div><div class="branch">'+cur.departmentName+'</div><div class="name">'+curName+'</div>')
		},
		//开始闪动
		beginTimer : function(){
			turnplate.t = setTimeout(turnplate.beat, turnplate.inter);
			turnplate.running = true;
		},
		//结束闪动，显示获奖结果
		endTimer : function(){
			turnplate.running = false;
			clearTimeout(turnplate.t);
			turnplate.updateRndNum(true);
		},
		//执行闪动
		beat : function() {
			turnplate.t = setTimeout(turnplate.beat, turnplate.inter);
			turnplate.updateRndNum();
		}
	}

	turnplate.init();

	common.keyup(function(){
		$('#platebtn').click();
	})

})
