$(function(){

	if (document.body.clientHeight == 527) {
		$(".Bt_wrap").css({top: "434px"})
	}

	isVotePower();

	// buijs_alert({
	// 	content: "已经投票过了"
	// })


})

/*查询是否有投票资格*/
function isVotePower(){
	$.ajax({
		type: "get",
		url: _jsonUrl+ "merriplusApi/queryGamePrizesByGameid?gameid="+GameId,
		dataType: "json",
		success: function (data){
			if (data.code == 0) {
				left= data.data[0].id;
				right= data.data[1].id;
			}else {
				msg = data.msg;
				buijs_alert({
					content: data.msg
				})
			}
		}
	})
}


var GameId= buijs_geturl("gameid");
var left;
var right;
var msg; 

/*投票事件*/
function select(n){
	var name="";
	var voteId;
	switch (n) {
		case "left": 
			name= "宋文思";
			voteId= left;
			break;
		case "right":
			name= "汪广忠";
			voteId= right;
			break;
		default:
			break;
	}
	buijs_modal({
		title: "",
		setid: "secondWindow",
		width: "65%",
		content: '<p class="bui_ta_c">给<span class="bui_tc_red bui_plr_3">'+name+'</span>投票</p>',
		positions: "center",
		footer: '<div class="bui_ta_c"><button id="cancel" class="bui_mr_48 bui_btn_32 bui_bgc_white bui_m_6">取消</button><button id="sure" class="bui_btn_32 bui_bgc_red bui_m_3">投票</button></div>'
	})

	$("#cancel").unbind().bind("click",function(){
		window.location.hash = '';
	})

	$("#sure").unbind().bind("click",function(){
		if (msg) {
			window.location.hash = '';
			return buijs_alert({
				content: msg
			})
		}
		$.ajax({
			type: "post",
			url: _jsonUrl +"/merriplusApi/submitPlayerInfo?prizeid="+voteId,
			dataType: "json",
			success: function(data){
				if (data.code == 0) {
					msg= "您已经投过票了";
					buijs_alert({
						content: data.msg
					});
					window.location.hash = '';
				}else {
					window.location.hash = '';
					msg= data.msg;
					buijs_alert({
						content: data.msg
					});
				}
			}
		})
	})
}