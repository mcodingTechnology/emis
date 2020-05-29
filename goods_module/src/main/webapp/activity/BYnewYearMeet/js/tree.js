requirejs(['jquery','api','common'],function($,api,common){
	//签到树/签到墙
	var tree = {
		arr:[],	//待显示的数据
		has:0,//已显示多少人
		index : 0,	//下次添加的苹果序号
		name : '',	//下次添加的名字
		pic : '',	//下次添加的头像url
		greetings : '',	//下次添加的祝福语
		time : 5000,	//当获取完数据后，隔多久再去取一次数据，单位为毫秒
		showTime : 5000,	//祝福语显示时长
		init : function(){
			var _this = this;
			$.getJSON(api.wallData,function(data){
		
				if(data.code=="0"){
					var arr = data.data.memberList || [];
					if(arr.length) arr.reverse();
					_this.has += arr.length;
					_this.arr = arr;
					_this.add();
				}else{
					//请求错误
					console.log(data);
					_this.add();
				}
			})
		},
		add : function(){
			//如果为空数组，说明没数据，再次请求数据
			if(this.arr.length<=0) return setTimeout(function(){tree.init();},tree.time) || false;
			//否则，将当前祝福人移动到苹果上
			if(this.name !=''){	//如果this.name为空，说明为第一个苹果
				var el = $('.apple'+this.index);
				el.find('img').attr('src',this.pic);
				el.find('.name').html(this.name);
				el.show();
				this.index = this.index >=19 ? 0 : this.index+1;
			}
			//显示下一个祝福人
			var cur = this.arr.pop();
			this.name = common.filter(cur.name);
			this.pic = (cur.headImgUrl=="N/A" ||cur.headImgUrl=="" || cur.headImgUrl == undefined) ? "file/photo.png" : cur.headImgUrl;
			this.greetings = cur.bless;
			this.showCur();
		},
		showCur : function(){
			$('#cur .name').html(this.name);
			$('#cur .img img').attr('src',this.pic);
			$('#cur .greetings').html(this.greetings+'<i class="dsj"></i><i class="dsj2"></i>');
			$('#cur').show();
			$('#wallList').append('<li><div class="name">'+this.name+'说：</div> <div class="txt">'+this.greetings+'</div> <div class="img"><img src="'+this.pic+'"></div></li>');	//显示在祝福墙上
			setTimeout(function(){
				tree.add();
			},tree.showTime);
		}
	}
	tree.init();
	
	common.keyup(tree.add)

})
