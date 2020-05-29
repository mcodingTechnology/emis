
$(document).ready(function() {
	//点击只看我的~按钮事件
	$("#checkMyself").click(function(){
		$.ajax({
			type:"get",
			url:_url+"front/winnerList.html?isSelf=yes",
			success:function(data){
				exportList(data)
			}
		})
	})
	
	//进入页面默认接口
	var url1="./winnerList.json"
	$.ajax({
		type:"get",
		url: _url+"front/winnerList.html?isSelf=no",
		success: function(data) {
			exportList(data)
		}
	});

});

//调接口成功后调用此方法输出html
function exportList(data){
	var list="";
	var dataList = data.data.sort(function(a,b){
		return a.startTime< b.startTime;
	});
	var k = {
		0:"一",
		1:"二",
		2:"三",
		3:"四"
	};
	$.map(dataList,function(data,index){
		var winner="";
		data.times = "第"+k[index]+"轮";
		$.map(data.winnerList, function(item) {
			var length= item.nickname.length-1;
			switch(length){
				case 0:
					item.nickname= "*";
					break;
				case 1:
					item.nickname= item.nickname[0]+"*";
					break;
				case 2:
					item.nickname= item.nickname[0]+"*"+item.nickname[2];
					break;
				case 3:
					item.nickname= item.nickname[0]+"**"+item.nickname[3];
					break;
				default:
					item.nickname= item.nickname[0]+"***"+item.nickname[length-1];
					break;
			}
			winner += 			'<div class="winnerList_right_userwarp">'
				+					'<div class="winnerList_right_userPict bui_round" data-bui_img="">'
				+						'<img src="'+item.headimgurl+'"/>'
				+					'</div>'
				+					'<p>'+item.nickname+'</p>'
				+				'</div>'
		});
		list +='<li class="winnerList">'
				+	'<div class="bui_media bui_vt">'
				+		'<div class="bui_media_l winnerList_left">'
				+			'<div class="winnerList_leftPict bui_round" data-bui_img="">'
				+				'<img src="'+data.productConvert+'"/>'
				+			'</div>'
				+			'<p class="opacity_48">'+data.startTimeStr.substr(0,10)+'</p>'
				+			'<p class="opacity_48">'+data.times+'</p>'
				+		'</div>'
				+		'<div class="bui_media_r winnerList_right">'
				+			'<div>'
				+				'<p>'+data.productName+'</p>'
				+				winner
				+			'</div>'
				+		'</div>'
				+	'</div>'
				+'</li>'
	})
	$("#contentList").html(list);
	$("[data-bui_img]").buijs_img();
}
