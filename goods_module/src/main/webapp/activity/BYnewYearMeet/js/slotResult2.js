require(['jquery','api','common'],function($,api,common){

	//遍历输出中奖员工头像名字
	var getList = function(memberList){
		var html= '';
		if (!memberList.length || memberList.length ==0) return alert('未知抽奖名单')
		for (i=0;i<=memberList.length;i+=3) {
			var li ='';
			for (j=i;j<=i+2 && j<memberList.length;j++) {
				if (j==i) {
					li += '<li><div class="img"><img src="'+(memberList[j].head_img_url || "file/photo.png")+'"></div><div class="name">'+memberList[j].name+'</div></li>'
				}else {
					li += '<li class="mrl_120"><div class="img"><img src="'+(memberList[j].head_img_url || "file/photo.png")+'"></div><div class="name">'+memberList[j].name+'</div></li>';
				}
			}
			html += '<ul>'+li+'</ul>';
		}
		$('.slotResultSecond').html(html);
	}

	//获取中奖名单
	$.ajax({
		type:'get',
		url:api.Winning_second,
		dataType:'json',
		success:function(rs){
			getList(rs.data.memberList)
		}
	})
})