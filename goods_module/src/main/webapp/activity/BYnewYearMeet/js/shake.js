requirejs(['jquery','api','common'],function($,api,common){
	//摇一摇
	var shake = {
		arr:[],	//待显示的数据
		has:0,//已显示多少人
		curHas:0,	//当前显示的人
		showSize:5,	//显示的中奖人个数
		getTime : 5000,	//当获取完数据后，隔多久再去取一次数据，单位为毫秒
		showTime : 500,	//动画时长
		isStart : false,	//是否开始倒计时
		time : 120,	//倒计时长，单位为秒
		t : null,	//计时函数
		start:function(time){
			var _this = this
			if(_this.isStart) return;
			$.post(api.start,{"lottoPrizeGroupId":2,"lottoPrizeGroupStatus":2},function(data){
				if(data.errorCode !='00') return alert(data.errorMessage) || false;
				_this.isStart = true;
				var time = shake.time || 60;
				_this.time = time;
				_this.countDown();
				_this.getData();
				$('.yTip').addClass('animated shake');
				$('.ps').hide();
				$('#time').css("display","inline-block");
				$('.ft').addClass('animated zoomInDown').show();
			},'json')
		},
		stop:function(){
			this.isStart = false;
			$.post(api.start,{"lottoPrizeGroupId":2,"lottoPrizeGroupStatus":3},function(data){
			});
		},
		getData : function(){
			var _this = this;
			var d = {"lottoPrizeGroupId":2,"fromRow":_this.has}
			$.getJSON(api.result,d,function(data){
				if(data.errorCode=="00"){
					var arr = data.returnObject || [];
					_this.has += arr.length;
					if(arr.length) arr.reverse();
					_this.arr = arr;
					_this.add();
				}else{
					//请求错误
					_this.add();
				}
			})
		},
		add : function(){
			//如果为空数组，说明没数据，再次请求数据
			if(this.arr.length<=0){
				if(!this.isStart) return;	//如果未开始，则直接返回
				setTimeout(function(){
					shake.getData();
				},shake.getTime)
				return false;
			}

			//否则，执行添加
			var cur = this.arr.pop();
			if(this.curHas%5==0){
				this.curHas = 0;
				setTimeout(function(){
					$('#winners li').addClass('bounceOutLeft');
					shake.show(cur);
				},2000)
			}else{
				shake.show(cur);
			}
		},
		show:function(cur){
			//显示中奖名单
			var curName = common.filter(cur.memberName)
			$('#winners').append('<li class="animated bounceInRight p'+this.curHas+'"><div class="ftBox"><div class="img"><img src="'+((cur.picUrl==""||cur.picUrl=="N/A")?'file/photo.png':cur.picUrl)+'"></div><div class="txt">'+curName+'抽中<br>'+cur.lottoPrizeName+'</div></div></li>')
			this.curHas += 1;
			setTimeout(function(){
				shake.add();
			},1000)
		},
		countDown : function(){
			this.t = setInterval(function(){
				var MM = parseInt(shake.time/60),SS = shake.time%60;
				$('#time').html('剩余时间：<em>'+MM+':'+(SS >9 ? SS : '0'+SS)+'</em>');
				if(MM<=0 && SS<=0){
					clearInterval(shake.t);
					$('.yTip').removeClass('animated shake');
					shake.stop();
					return $('#time').html('<em>本轮已结束！</em>');
				}
				shake.time -= 1;
				//shake.countDown();
			},1000);
		}
	}

	common.keyup(function(){
		shake.start();
	})

})
