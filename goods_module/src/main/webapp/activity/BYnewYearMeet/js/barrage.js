requirejs(['jquery','api','common'],function($,api,common){
	//一等奖 弹幕
	//背景动画
	function list(){
		var html = ''
		$.each(common.shuffle(60),function(i,n){
			html += '<li> <div class="img"><img src="'+((n.picUrl=="N/A" ||n.picUrl=="") ? "file/photo.png" : n.picUrl)+'"></div> <div class="branch">'+n.departmentName+'</div> <div class="name">'+n.memberName+'</div> </li>';
		})
		return html;
	}

	$(".bg01").html(list())
	$(".bg02").html(list())
	var speed=5;
	var wrap = $("#wrap");
	$("#box2").html($("#box1").html());
	function Marquee(){
		if(wrap.scrollLeft()<=0)
			wrap.scrollLeft($("#box1").width()); 
		else{
			wrap.scrollLeft(wrap.scrollLeft()-5);
		}
	} 
	
	/*中间部分向左边移动*/
	function Qarquee(){
		if($('.bd').scrollLeft()>=$('.bg').width()/2){
			$('.bd').scrollLeft(0);
		}else {
			$('.bd').scrollLeft($('.bd').scrollLeft()+5);
		}
	}

	var MyMar=setInterval(Marquee,speed);
	var MyQar=setInterval(Qarquee,10);

	var tryText= {
		status : 0,
		data : null,
		get : function () {
			var _this = this;
			if (this.status ==0) {
				this.status = 1;
				$.ajax({
					type:'get',
					url:api.lottery_first,
					dataType:'json',
					success:function(data){
						if (data.code == '0') {
							$('.winner').addClass('rotateWinnerGoing');
							$('.wennerName').hide();
							_this.status= 2;
							_this.data = data.data.memberList || null;
						}else {
							alert(data.msg)
						}
					}
				})
			}else if (this.status ==2) {
	            this.status =3;
	            var _this = this;
				$('.winner').removeClass('rotateWinnerGoing');
				$('.winner').addClass('rotateWinnerEnd');
				$('.winnerImg').attr('src',_this.data[0].headImgUrl || "./file/photo.png")
				setTimeout(function(){
	                $('.wennerName').show().html(_this.data[0].name || 'XXX');
	            	this.status =4;
	            },3000);
			}
		}
	}


	//显示中奖人员
	// var barrage = {
	// 	has : 0,
	// 	get : function(){
	// 		var _this = this;
	// 		$.post(api.generate,{"lottoPrizeGroupId":5,"lottoPrizeCount":1}).done(function(data){
	// 			//var data = {"errorCode":"00","errorMessage":"生成抽奖结果完毕.","returnObject":[{"lottoPrizeId":9,"lottoPrizeName":"小蚁智能摄像机","openId":"1481a61cd93bed38d5b3822469cb9578","memberName":"邓全胜","picUrl":"http://shp.qpic.cn/bizmp/2uZibrk6nmW3PUR3ibc6cc7IeNib9ndwVv6QibQicibr3Nib3BibvHoXSOJolw/","departmentName":"信息技术部","lottoPrizeGroupId":"1","lottoPrizeGroupName":"刮一刮"}]}
	// 			if(data.errorCode != '00'){
	// 				alert(data.errorMessage);
	// 				return false;
	// 			}else{
	// 				if(data.returnObject && data.returnObject.length>0) return _this.show(data) || false;
	// 				alert(data.errorMessage);
	// 			}
	// 		},'json')
	// 	},
	// 	show : function(data){
	// 		if(this.has>=5){
	// 			$('#barrage .person').addClass('bounceOutLeft');
	// 			this.has = 0;
	// 		}
	// 		var curName = common.filter(data.returnObject[0].memberName)
	// 		$('#barrage .bd').append('<div class="person animated bounceInRight p'+this.has+'"> <div class="img"><img src="'+((data.returnObject[0].picUrl=="N/A" ||data.returnObject[0].picUrl=="") ? "file/photo.png" : data.returnObject[0].picUrl)+'"></div> <div class="branch">'+data.returnObject[0].departmentName+'</div> <div class="name">'+curName+'</div> </div>')
	// 		this.has += 1;
	// 	}
	// }

	common.keyup(function(){
		// barrage.get();
		tryText.get();
	})
	
});
