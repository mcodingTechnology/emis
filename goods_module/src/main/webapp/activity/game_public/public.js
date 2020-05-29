var vueData = {};
vueData.brandLogo = 'images/indexLogo.png'; //项目logo
vueData.brandBanner = 'images/brandbar.jpg'; //品牌栏
vueData.brandName = '极智构'; //项目名称
vueData.brandNameEn = 'Merryplus'; //项目英文名称
vueData.brandCode = 'MRMJ'; //公用brandCode
vueData.mallType = 'wMall'; //公用mallType
vueData.mallPath = 'wMall/'; //目录名称
vueData.shareLogo = 'http://v.merryplus.com/wMall/pic/share_logo.jpg'; //分享图片路径
vueData.isShare = true; //分享开关
vueData.style = {
	tcTrue: 'bui_tc_orange bui_atc_orange', //文字True
	tcFalse: 'bui_tc_white_d48 bui_atc_white_d48', //文字True
	facTrue: 'bui_fac_orange', //图标True
	facFalse: 'bui_fac_white_d72', //图标False
	bgc: 'bui_bgc_orange', //背景
	btnTrue: 'bui_bgc_orange bui_tc_white bui_atc_white', //按钮ture
	btnFalse: 'bui_bgc_white_d64 bui_atc_white bui_tc_white' //按钮false
};
vueData.giftMallPath = '../GiftMall/';
vueData.signPath = 'sign.html?gameid=31';
vueData.memberInfo = {};
vueData.listLoading = '<p class="bui_ptb_8 bui_ta_c bui_vm"><i class="bui_btn_48 bui_btnsq fa fa-circle-o-notch fa-spin bui_fas_24 ' + vueData.style.facFalse + '"></i></p>';

buijs_getFile.js.swiper = '../../resources/baidiui_v2.0.1/js/swiper.jquery.min.js'; //第三方幻灯片插件
buijs_getFile.css.fontAwesome = '../../resources/baidiui_v2.0.1/css/font-awesome-4.4.0/css/font-awesome.min.css'; //fontAwesome图标

$(document).ready(function() {
	//全局变量
	var vuePublic = new Vue({
		el: 'html',
		data: vueData
	});
});

//选择省市区弹窗 by pangxj
function public_selectRegson(_input, _id, _level, _width) {
	if (_level < 4 || !_level) {
		//初始化进入
		if ($("#public_selectRegson").length == 0) {
			buijs_modal({
				setid: 'public_selectRegson',
				title: '选择地区',
				positions: 'right',
				width: _width || '85%',
				showAfter: function() {
					insetRegson(_id);
				}
			});
		} else {
			insetRegson(_id);
		};
	} else {
		buijs_modal_close('public_selectRegson');
	}

	function insetRegson(_sid) {
		var _contentBox = $("#public_selectRegson .bui_modal_b");
		var _regsonList = '';
		_contentBox.html('<p class="bui_ta_c bui_p_24 bui_tc_white_d48"><i class="fa fa-circle-o-notch fa-spin"></i> 正在处理中...</p>');
		$.ajax({
			type: "get",
			url: _jsonUrl + "/region/queryRegionByParentId.html",
			async: true,
			cache: false,
			dataType: 'json',
			data: {
				parentRegionId: _sid
			},
			error: function(data) {
				_contentBox.html('<div class="bui_p_24 bui_ta_c bui_tc_white_d48"><p><i class="fa fa-unlink bui_fas_64"></i></p><p class="bui_mt_12 bui_ts_14">数据读取失败，请检查网络设置</p></div>')
			},
			success: function(data) {
				//列表不为空
				if (data.length != 0) {
					$.map(data, function(data) {
						_regsonList += '<li class="item" regsonid="' + data.regionId + '" level="' + data.regionType + '">' +
							'<div class="bui_media bui_bgc_white bui_ptb_12 bui_plr_24">' +
							'<div class="bui_media_b">' + data.regionName + '</div>' +
							'<div class="bui_media_r"><i class="fa fa-angle-right fa-fw bui_fac_white_d64"></i></div>' +
							'</div>' +
							'<hr/>' +
							'</li>';
					});
					_contentBox.html(bui_side_search + '<div class="SearchList"><hr/>' + _regsonList + '</div>');
					//筛选列表
					buijs_side_search($('.SearchList'), '筛选地区');
				}
				//列表为空
				else {
					buijs_modal_close('public_selectRegson');
				};

				//点选操作
				$("#public_selectRegson .item").unbind('click').bind({
					'click': function() {
						var _RegsonName = $(this).find('.bui_media_b').text();
						var _RegsonId = $(this).attr('regsonid');
						var _level = $(this).attr('level');
						if (_level == 1) {
							$("[name=address_province]").val(_RegsonName);
							$("[name=address_city]").val('');
							$("[name=address_area]").val('');
							$("[name=address_street]").val('');
						} else if (_level == 2) {
							$("[name=address_city]").val(_RegsonName);
						} else if (_level == 3) {
							$("[name=address_area]").val(_RegsonName);
						} else if (_level == 4) {
							$("[name=address_street]").val(_RegsonName);
						}
						public_selectRegson(_input, _RegsonId, _level);
						_input.val($("[name=address_province]").val() + ' ' + $("[name=address_city]").val() + ' ' + $("[name=address_area]").val() + ' ' + $("[name=address_street]").val());
					}
				});
			}
		});
	};
};