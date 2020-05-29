requirejs(['jquery','api','common'],function($,api,common){
	var move = {
		//el:$('#moveBox dl').eq(1),	//当前移动的元素
		running : false,	//是否中奖部门在闪动
		person : common.shuffle(),
		curPerson : [],
		inter : 10,	//中奖部门人员闪动间隔
		t1: '',
		t2: '',
		i:0,	//当前显示的闪动颜色
		color:['#54A82C','#00B1FF','#EC5464','#F60'],	//闪动的颜色
		start:function(){
			var _this = this;
			if($('#hand').hasClass('run')) return;
			if(_this.running==true){	//正在闪动
				_this.endTimer();
				return;
			}
			_this.show();
		},
		get:function(cb){
			var _this = this;
			$.ajax({
				url:api.generate,
				type:'post',
				async:false,
				data:{"lottoPrizeGroupId":4,"lottoPrizeCount":2},
				dataType:'json'
			}).done(function(data){
				//var data = {"errorCode":"00","errorMessage":"生成抽奖结果完毕.","returnObject":[{"lottoPrizeId":9,"lottoPrizeName":"小蚁智能摄像机","openId":"1481a61cd93bed38d5b3822469cb9578","memberName":"邓全胜","picUrl":"http://shp.qpic.cn/bizmp/2uZibrk6nmW3PUR3ibc6cc7IeNib9ndwVv6QibQicibr3Nib3BibvHoXSOJolw/","departmentName":"信息技术部","lottoPrizeGroupId":"1","lottoPrizeGroupName":"刮一刮"},{"lottoPrizeId":9,"lottoPrizeName":"小蚁智能摄像机","openId":"1481a61cd93bed38d5b3822469cb9578","memberName":" fanxin","picUrl":"http://shp.qpic.cn/bizmp/2uZibrk6nmW3PUR3ibc6cc7IeNib9ndwVv6QibQicibr3Nib3BibvHoXSOJolw/","departmentName":"信息技术部","lottoPrizeGroupId":"1","lottoPrizeGroupName":"刮一刮"}]}
				if(data.errorCode != '00'){
					alert(data.errorMessage);
					$('#hand').removeClass('run');
					return false;
				}else{
					_this.curPerson = data.returnObject;
					cb();
				}
			},'json')
		},
		show:function(){
			var _this = this;
			if($('#moveBox dl').eq(1).hasClass('go01')){
				//添加手势动画
				$('#hand').attr('class','animated fadeInUp show run');
				setTimeout(function(){
					$('#hand').attr('class','animated bounceOutRight run');
				},1000)
				setTimeout(function(){
					//拖动显示下一个奖品
					//$('#hand').removeClass('run');
					$('#moveBox dl').eq(1).addClass('go02');
				},1200)
				setTimeout(function(){
					$('#moveBox dl').eq(1).prependTo($('#moveBox')).removeClass('go01 go02');
					$('#hand').removeClass('run');
				},2000)
			}else{
				//先给#hand添加run，防止进入下一次拖动
				//$('#hand').attr('class','run');
				_this.get(function(){
					//添加手势动画
					$('#hand').attr('class','animated fadeInUp show run');
					setTimeout(function(){
						$('#hand').attr('class','animated bounceOutRight run');
					},1000)
					setTimeout(function(){
						//拖动显示奖品
						$('#moveBox dl').eq(1).addClass('go01');
					},1200)
					setTimeout(function(){
						$('#hand').removeClass('run');
					},2000)
					_this.beginTimer();
				})
			}
		},
		//中奖人员闪动抽奖
		//显示当前人员
		updateRndNum:function(index,end){	//end 是否停止
			var cur = '';
			if(!end){
				var num = Math.floor(Math.random()*move.person.length);
				cur =  move.person[num];
			}else{
				cur  = move.curPerson.pop();
			}
			var curName = common.filter(cur.memberName);
			this.i+=1;
			$('#moveBox dt:eq(1) li').eq(index).css('background',this.color[this.i%this.color.length]).html('<div class="img"><img src="'+((cur.picUrl=="N/A" ||cur.picUrl=="") ? "file/photo.png" : cur.picUrl)+'"></div><div class="branch">'+cur.departmentName+'</div><div class="name">'+curName+'</div>');
			if(end) $('#moveBox dt:eq(1) li').eq(index).removeAttr('style')
		},
		//开始闪动
		beginTimer : function(){
			this.beat();
			this.beat2();
			this.running = true;
		},
		//结束闪动，显示获奖结果
		endTimer : function(index){
			// if(this.curPerson.length<=1){
			// 	this.running = false;
			// 	clearTimeout(move.t2);
			// 	this.updateRndNum(1,true);
			// }else{
			// 	clearTimeout(move.t1);
			// 	this.updateRndNum(0,true);
			// }
			this.running = false;
			clearTimeout(move.t1);
			this.updateRndNum(0,true);
			clearTimeout(move.t2);
			this.updateRndNum(1,true);
		},
		//执行闪动
		beat : function() {
			move.t1 = setTimeout(move.beat, move.inter);
			move.updateRndNum(0);
		},
		beat2 : function(index) {
			move.t2 = setTimeout(move.beat2, move.inter);
			move.updateRndNum(1);
		}
	}
	

	common.keyup(function(){
		move.start();
	})

			

	//二等奖 拖动
	/*if($('#hand').length>0){
		var el = $('#moveBox dl').eq(1);
		if($('#hand').hasClass('run')) return;
		$('#hand').addClass('run');
		if(el.hasClass('go01')){
			//拖动显示奖品
			el.addClass('go02');
			setTimeout(function(){
				el.prependTo($('#moveBox')).removeClass('go01 go02');
			},1000)
		}else{
			//产生抽奖结果
			$.ajax({
				get:'post',
				url:api.generate,
				//async:false,
				data:{"lottoPrizeGroupId":4,"lottoPrizeCount":2}
			}).done(function(data){
				if(data.errorCode != '00'){
					alert(data.errorMessage);
					$('#hand').removeClass('run');
					return false;
				}
				//添加手式动画
				$('#hand').attr('class','animated fadeInUp show run');
				setTimeout(function(){
					$('#hand').attr('class','animated bounceOutRight run');
				},1000)
				setTimeout(function(){
					//拖动显示获奖名单
					$('#hand').removeClass('run');
					el.addClass('go01');
				},1200)
			})
		}
	}*/

})
