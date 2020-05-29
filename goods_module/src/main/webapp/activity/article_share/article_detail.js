var _id = buijs_geturl('id'); //获取查看的名称
var _shareOpenId = buijs_geturl('shareOpenId');
var _object = {}; //请求当前的文章
//var flag = false; //设置定时器的标志

$(document).ready(function() {
	viewPoint(); //阅读积分 by pangxj
	first();
	init();
	mp_checkMemberDetail();
	wechatShare();
});

//图片错误 by pangxj
function picError(_this) {
	_this.parents('.bui_media_l').remove();
}

//阅读积分 by pangxj
function viewPoint() {
	if (_shareOpenId) {
		$.ajax({
			type: "get",
			url: _jsonUrl + "front/articleReadGetPoint.html",
			async: true,
			dataType: 'json',
			data: {
				shareOpenId: _shareOpenId,
				articleId: _id
			},
			error: function() {},
			success: function(data) {
				console.log(data)
			}
		});
	}
}

function formatTime(time) {
	//格式化时间
	var date = new Date(time);
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	return year + "-" + month + "-" + day;
}

function isAgree() {
	//查询是否点赞
	$.ajax({
		url: _jsonUrl + "front/checkLiked.html?articleId=" + _id,
		type: "get",
		success: function(rs) {
			if (rs.code == 0) {
				if (rs.data.isLike) {
					$("#shareTotal").addClass("agree");
					$("#shareTotal").removeClass('bui_bds_1 bui_tc_white_d48').addClass('bui_bgc_red bui_tc_white');
				}
			}
		}
	});
}
//如果链接带有openid证明是分享的，需要调接口
function share(openid) {
	$.ajax({
		url: _jsonUrl + "front/addArticleShare.html?shareOpenId=" + openid + "&articleId=" + _id,
		type: "get",
		success: function(rs) {
			if (rs.code == 0) {
				buijs_modal({
					content: '<p class="bui_ts_18">恭喜您分享成功！第一个好友阅读后可获得' + rs.data + '积分！</p><p class="bui_mt_12 bui_ta_c"><button class="bui_btn_48 bui_bgc_orange bui_tc_white bui_ts_14 bui_modal_close">我知道了</button></p>',
					positions: 'center'
				});
			}
		},
		error: function(err) {

		}
	});
}
//点赞
function agree() {
	//点赞过就不调接口
	if ($("#shareTotal").hasClass("agree")) {
		buijs_alert({
			content: '您已经赞过该文章'
		})
	}
	$.ajax({
		url: _jsonUrl + "front/addLikeNums.html?id=" + _id,
		type: "get",
		success: function(rs) {
			if (rs.code == 0) {
				$("#shareTotal").addClass("agree");
				var sum = _object.likenums + 1;
				$("#shareTotal").removeClass('bui_bds_1 bui_tc_white_d48').addClass('bui_bgc_red bui_tc_white');
				$("#shareTotal").html("<i class='fa fa-thumbs-o-up fa-fw bui_fas_14'></i> 点赞：" + sum);
				buijs_alert({
					content: '点赞成功！'
				});
			}
		}
	})
}

function first() {
	var queryDate = {
		iDisplayStart: 0,
		iDisplayLength: 10,
		read: true,
		id: _id
	};
	$.ajax({
		type: "get",
		url: _jsonUrl + "front/queryArticleDefByPage.html",
		async: false,
		data: queryDate,
		dataType: "json",
		success: function(data) {
			var html = "";
			var data = data.queryResult[0];
			_object = data;
			if (!data.banner) {
				$(".help_banner").html('<div class="handerTitle"><h1 class="bui_ts_24" style="white-space:normal;">' + data.title + '</h1></div>');
			} else {
				$(".help_banner").html('<img src="' + data.banner + '" style="width:96%; height:auto;"/>');
			}
			//			if (data.coverimg) {
			//				var pict = '<img style="width: 100%;padding:0 24px;" src="' + data.coverimg + '"/>';
			//				html += pict;
			//			}
			if (data.content) {
				var content = '<div class="help_content bui_p_12">' + data.content + '</div>';
				html += content;
			}
			if (data.likenums > 10000) {
				data.likenums = (data.likenums / 10000).toFixed(2) + "万";
			}
			if (data.recommend) {
				recommend(data.recommend);
			}
			$("#date").html(buijs_formatTime(data.updatetime));
			$("#shareTotal").html("<i class='fa fa-thumbs-o-up fa-fw bui_fas_14'></i> 点赞：" + data.likenums);
			$("#readNum").html(data.readpersonnums)
			$("#author").html("作者：" + data.author);
			$(".help_content").html(html);
		}
	});
}

function init() {
	var openid = buijs_geturl("shareOpenId");
	/*if(openid){
	    share(openid);
	}*/
	//绑定点赞事件
	$("#shareTotal").click(function() {
		agree();
	});
	isAgree();
};
//调用推荐列表接口
function recommend(ids) {
	$.ajax({
		url: _jsonUrl + "front/getRecommends.html?ids=" + ids,
		type: "get",
		success: function(rs) {
			if (rs.code == 0) {
				var list = "";
				$.map(rs.data.list, function(item, index) {
					var url = changeURLArg(location.href, "id", item.id);
					list += '<li><a href="' + url + '" class="bui_media bui_vm bui_tc_black bui_p_12 bui_bds_1_b"><div class="bui_media_l"><div data-bui_img="" style="width:48px;height:48px;"><img src="' + item.coverimg + '" onerror="picError($(this))"/></div></div><div class="bui_media_b"><p class="bui_ts_14">' + item.title.substring(0, 32) + '...</p></div></a></li>';
				});
				$("#recommendList").html('<div class="bui_p_12 bui_bds_1_b">推荐文章：</div>' + list).find('[data-bui_img]').buijs_img();
			}
		}
	})
}
//修改url参数的方法
function changeURLArg(url, arg, arg_val) {
	var pattern = arg + '=([^&]*)';
	var replaceText = arg + '=' + arg_val;
	if (url.match(pattern)) {
		var tmp = '/(' + arg + '=)([^&]*)/gi';
		tmp = url.replace(eval(tmp), replaceText);
		return tmp;
	} else {
		if (url.match('[\?]')) {
			return url + '&' + replaceText;
		} else {
			return url + '?' + replaceText;
		}
	}
	return url + '\n' + arg + '\n' + arg_val;
}

/***
 * 微信分享方法
 *
 * **/
function wechatShare() {

	var fullPath = window.location.href;
	var timestamp = 0; //时间戳
	var nonceStr = ''; //随机串
	var signature = ''; //签名
	var link = '';
	var msg = ""; //openid
	$.ajax({
		type: "post",
		url: _jsonUrl + "api/wechatShare2",
		async: false,
		//global: false,
		dataType: "json",
		data: {
			'fullPath': fullPath
		},
		success: function(rs) {
			timestamp = rs.data.timestamp;
			nonceStr = rs.data.nonceStr;
			signature = rs.data.signature;
			link = rs.data.link;
			msg = rs.msg;
			configWx(appid, timestamp, nonceStr, signature, link, msg);
		}
	});
}

function configWx(appid, timestamp, nonceStr, signature, link, openid) {

	var title = "";
	var desc = '';
	var imgUrl = "";
	title = _object.title;
	imgUrl = _object.coverimg;
	desc = _object.stitle;
	var titleId = buijs_geturl("id");
	var _link = location.href;
	if (_link.indexOf("shareOpenId") != -1) {
		_link = changeURLArg(_link, "shareOpenId", openid);
	} else {
		_link += "&shareOpenId=" + openid;
	};
	if (mp_memberInfo.levelId) {
		$.ajax({
			type: "get",
			url: _jsonUrl + "api/createWechatShareUrl",
			async: false,
			data: {
				targetUrl: _link
			},
			success: function(data) {
				_link = data.data.shareUrl;
			}
		});
	}
	wx.config({
		debug: false,
		appId: appid,
		timestamp: timestamp,
		nonceStr: nonceStr,
		signature: signature,
		jsApiList: ['onMenuShareTimeline',
			//'hideMenuItems',
			//'hideAllNonBaseMenuItem',
			'onMenuShareAppMessage'
		]
	});
	wx.error(function(res) {
		buijs_alert({
			content: res.errMsg
				//'href':'index.html'
		});
	});

	wx.ready(function() {
		wx.onMenuShareTimeline({
			title: title, // 分享标题
			desc: desc, // 分享描述
			link: _link, // 分享链接
			imgUrl: imgUrl, // 分享图标
			success: function() {
				// 用户确认分享后执行的回调函数
				share(openid);

			},
			fail: function() {},
			cancel: function() {
				// 用户取消分享后执行的回调函数
			}
		});

		wx.onMenuShareAppMessage({
			title: title, // 分享标题
			desc: desc, // 分享描述
			link: _link, // 分享链接
			imgUrl: imgUrl, // 分享图标
			type: '', // 分享类型,music、video或link，不填默认为link
			dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
			success: function() {
				// 用户确认分享后执行的回调函数
				share(openid);
			},
			fail: function() {},
			cancel: function() {
				// 用户取消分享后执行的回调函数
				buijs_alert({
					content: '已取消分享！'
				});

			}
		});
	});
}