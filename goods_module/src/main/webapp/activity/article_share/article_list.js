var _index = 0;
var listCount = 0;
var stakeList = [];

$(document).ready(function() {
	queryArcticle(0, 10);

});

//跳转分享说明
function goHelp() {
	mp_checkMemberDetail();
	console.log(mp_memberInfo);
	if (mp_memberInfo.levelId) {
		window.location.href = '../game_public/game_help.html?type=articlelist_memberjoin'
	} else {
		window.location.href = '../game_public/game_help.html?type=articlelist'
	}
};

//图片错误 by pangxj
function picError(_this) {
	_this.parents('.bui_media_l').remove();
}

function formatTime(time) {
	var date = new Date(time);
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	return year + "-" + month + "-" + day;
}

function queryArcticle(pageNo, pageSize) {
	//查询文章列表接口
	$.ajax({
		type: "get",
		url: _jsonUrl + "front/queryArticleDefByPage.html",
		data: {
			iDisplayStart: pageNo * 10,
			iDisplayLength: pageSize,
			id: null
		},
		dataType: "json",
		success: function(data) {
			if (pageNo <= data.rowCount / pageSize) {
				$("#listTips").removeClass('active').html('<i class="fa fa-circle-o-notch fa-fw fa-spin"></i> 正在加载中...');
			} else {
				$("#listTips").addClass('active').html('<i class="fa fa-check fa-fw"></i> 已全部加载');
			};
			$("#more").text("点击加载更多");
			listCount = data.iTotalRecords;
			stakeList = stakeList.concat(data.queryResult);
			var _list = "";
			$.map(data.queryResult, function(data) {
				if (data.readnums > 10000) {
					data.readnums = (data.readnums / 10000).toFixed(2) + "万";
				}
				_list += '<li>' +
					'<a href="article_detail.html?id=' + data.id + '" class="bui_media bui_vm bui_bgc_white bui_p_12 bui_bds_1_b">' +
					'<div class="bui_media_l">' +
					'<div class="bui_radius bui_bgc_lgray" style="width: 64px; height: 64px;" data-bui_img="">' +
					'<img src="' + data.coverimg + '" onerror="picError($(this));"/>' +
					'</div>' +
					'</div>' +
					'<div class="bui_media_b">' +
					'<p class="bui_ts_14">' + data.title.substr(0, 24) + '...</p>' +
					'<p class="bui_ts_12 bui_tc_white_d48">' + data.stitle + '</p>' +
					'<div class="bui_mt_6">' +
					'<p class="bui_row_p_12 bui_ts_12 bui_tc_white_d48">' +
					'<span><i class="fa fa-eye"></i> ' + data.readnums + '</span>' +
					'<span>' + buijs_formatTime(data.updatetime) + '</span>' +
					'</p>' +
					'</div>' +
					'</div>' +
					'<div class="bui_media_r">' +
					'<i class="fa fa-angle-right fa-fw bui_fac_white_d48"></i>' +
					'</div>' +
					'</a>' +
					'</li>'
			});
			$("#contentBody").append(_list).find("[data-bui_img]").buijs_img();

			$(".bui_mo_b").buijs_scrollTobottom(function() {
				if (!$("#listTips").hasClass('active')) {
					$("#listTips").addClass('active').html('<i class="fa fa-circle-o-notch fa-fw fa-spin"></i> 正在加载中...');
					pageNo = pageNo + 1;
					setTimeout(function() {
						queryArcticle(pageNo, pageSize)
					}, 300)

				}
			});
		}
	});
}