$(document).ready(function() {
	global_goToYouZhan(); //跳转到有赞
	setTimeout(function() {
		//当前时间
		vm.$set('now', {
			year: new Date().getFullYear(),
			month: new Date().getMonth() + 1,
			day: new Date().getDate(),
			weekDay: new Date().getDay()
		});
		//统计数据
		global_getJsonSetVue(workSpace.jsonUrl + 'merriplusApi/getSumSaleAndIncome', 'total', vueData.urlData.memberId != 'null' ? {
			memberId: vueData.urlData.memberId
		} : null, function() {
			//获取数据
			distributor_getListArray(vueData.urlData.type || 'week');
		});
	}, 300)
});

function distributor_getListArray(type) {

	console.log(type)

	//提交对象
	var postData;
	if(type == 'week') {
		postData = {
			startDateStr: vueData.now.year + '-' + vueData.now.month + '-' + [vueData.now.day - vueData.now.weekDay],
			endDateStr: vueData.now.year + '-' + vueData.now.month + '-' + vueData.now.day,
			dataSize: 7,
		};
	} else if(type == 'month') {
		postData = {
			startDateStr: vueData.now.year + '-' + vueData.now.month + '-' + '1',
			endDateStr: vueData.now.year + '-' + vueData.now.month + '-' + vueData.now.day,
			dataSize: 31
		};
	} else {
		postData = {
			startDateStr: vueData.urlData.type.split('|')[0],
			endDateStr: vueData.urlData.type.split('|')[1],
			dataSize: 31
		};
	};

	postData = $.extend(postData, vueData.urlData.memberId != 'null' ? {
		memberId: vueData.urlData.memberId
	} : null);

	//自定义日期
	global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getIncomeDayList', 'getIncomeDayList', postData, function() {
		setTimeout(function() {
			//统计销量收入
			vm.$set('orderFeeTotal', 0);
			vm.$set('commissionTotal', 0);
			$.map(vueData.getIncomeDayList.data, function(data) {
				vueData.orderFeeTotal += data.orderfee
				vueData.commissionTotal += data.commission
			});
			//时间插件
			$("#selectTime").buijs_date({
				positions: 'bottom',
				start: 3,
				min: 3,
				format: 'yyyy-mm',
				changeAfter: function() {
					var year = Number($("#selectTime").val().split('-')[0]);
					var month = Number($("#selectTime").val().split('-')[1]);
					setTimeout(function() {
						window.location.href = '?memberId=' + vueData.urlData.memberId + '&type=' + year + '-' + month + '-' + '1' + '|' + year + '-' + month + '-' + '31'
					}, 300);

				}
			});
		}, 300);

	});
};

//显示当天明细
function distributor_showDayDetail(date) {
	global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getIncomeOrderListByDay', 'getIncomeOrderListByDay', {
		dateStr: date
	}, function() {
		setTimeout(function() {
			if(vueData.getIncomeOrderListByDay.data.length == 0) {
				buijs_alert({
					content: '当天没有销量和收入'
				});
			} else {
				buijs_modal({
					setid: 'distributor_showDayDetail',
					positions: 'center',
					width: '86%',
					height: '86%',
					title: '收入明细',
					footer: '<div class="bui_p_8"><button class="bui_btn bui_btn_48 bui_bgc_red bui_ts_16 bui_block" buijs_modal_close="distributor_showDayDetail">我知道了</button></div>',
					showAfter: function() {
						$("#" + this.setid).css('z-index', 10004);
					}
				});
			}
		}, 300)
	});
};
//显示订单明细
function distributor_showOrderDetail(orderId) {
	global_getJsonSetVue(workSpace.jsonUrl + '/merriplusApi/getOrderInfoByOrderId', 'getOrderInfoByOrderId', {
		orderId: orderId
	}, function() {
		setTimeout(function() {
			buijs_modal({
				zindex: 2,
				setid: 'distributor_showOrderDetail',
				positions: 'center',
				width: '86%',
				height: '86%',
				title: '订单明细',
				footer: '<div class="bui_p_8"><button class="bui_btn bui_btn_48 bui_bgc_red bui_ts_16 bui_block" buijs_modal_close="distributor_showOrderDetail">我知道了</button></div>',
				showAfter: function() {
					$("#" + this.setid).css('z-index', 10005);
				}
			});

		}, 300)
	});
};