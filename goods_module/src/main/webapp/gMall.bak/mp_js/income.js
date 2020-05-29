$(document).ready(function() {

	//获取销量统计
	$.ajax({
		type: "get",
		url: _jsonUrl + "merriplusApi/getSumSaleAndIncome",
		async: true,
		cache: false,
		dataType: 'json',
		success: function(data) {
			vm.$set('maxCommission', data.data.maxCommission);
			vm.$set('sumCommission', data.data.sumCommission);
			vm.$set('sumOrderFee', data.data.sumOrderFee);
		}
	});

	//启用日期插件
	$("#choose_date").buijs_date({
		positions: 'bottom',
		start: 3,
		min: 3
	})
	$('.chartHeader').find('li').on({
		'touchend': function() {
			$(this).addClass('active').siblings().removeClass('active');
		}
	});

	$('#choose_date').val(new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + new Date().getDate())

	getCharts('week', null, null);
	//	getSameMonth();
});
var flag = true;

function chooseDate() {
	if(flag) {
		$('#choose_date').datetimepicker('show').on('hide', function() {
			$('.choose_text').text($('#choose_date').val().substring(0, $('#choose_date').val().length - 3));
			getCharts('month', new Date($('#choose_date').val()).getMonth() + 1, new Date($('#choose_date').val()).getFullYear());
			flag = false;

		});
	} else {
		$('#choose_date').datetimepicker('show');
	}

}

function showDayDetail(date, val) {
	//无收入
	if(val == 0) {
		buijs_alert({
			content: date + '无收入'
		});
	}
	//有收入
	else {
		buijs_modal({
			title: date + '收入',
			positions: 'right',
			setid: 'dayDetailPanel',
			showAfter: function() {
				$.ajax({
					type: "get",
					url: _jsonUrl + "/merriplusApi/getIncomeOrderListByDay",
					async: true,
					global: false,
					dataType: "json",
					data: {
						dateStr: date
					},
					success: function(data) {
						var content = '';
						if(data.data.length == 0) {
							content = '<p class="bui_p_24 bui_ta_c ' + vueObj.style.tcFalse + '">' + date + '没有任何收入</p>'
						}

						if(data.data.length > 0) {
							data.data.reverse().forEach(function(ele, index) {
								content += '<div class="bui_ptb_12 bui_plr_24 bui_bds_1_b bui_ts_12 ' + vueObj.style.tcFalse + '">' +
									'	<p><span>下线：</span><span class="bui_tc_black">' + ele.ordermembername + '</span></p>' +
									'	<p><span>订单：</span><span class="bui_tc_black">' + ele.ext1 + '</span></p>' +
									'	<div class="bui_avg_2">' +
									'		<p><span>金额：</span><span class="bui_ts_14 ' + vueObj.style.tcTrue + '">￥' + ele.orderfee / 100 + '</span></p>' +
									'		<p><span>收入：</span><span class="bui_ts_14 ' + vueObj.style.tcTrue + '">￥' + ele.commission / 100 + '</span></p>' +
									'	</div>' +
									'</div>';

							})
						}
						$("#dayDetailPanel .bui_modal_b").html(content);
					}
				});
			}
		});
	}

}

function getWeek() {
	var obj = new Object();
	var now = new Date();
	var myDate = new Date();
	var startDate = new Date(myDate.setDate((now.getDate() - now.getDay()) + 1));
	myDate = new Date();
	var endDate = new Date(myDate.setDate(now.getDate() + (7 - now.getDay())));;
	obj.startDate = startDate.getFullYear() + '-' + (startDate.getMonth() + 1) + '-' + startDate.getDate();
	obj.endDate = endDate.getFullYear() + '-' + (endDate.getMonth() + 1) + '-' + endDate.getDate();

	return obj;
}
//根据x向前推日期

function getTime(x) {
	var obj = new Object();
	var now = new Date();
	var myDate = new Date();
	var startDate = new Date(myDate.setDate(now.getDate() - x + 1));
	var endDate = now;
	obj.startDate = startDate.getFullYear() + '-' + (startDate.getMonth() + 1) + '-' + startDate.getDate();
	obj.endDate = endDate.getFullYear() + '-' + (endDate.getMonth() + 1) + '-' + endDate.getDate();
	return obj;
}
//根据月份获取日期  如果有31 包含31

function getMonth(month, year) {

	var obj = new Object();
	var myDate;
	if(month) {
		myDate = new Date(new Date().setFullYear(year, month - 1));
	} else {
		myDate = new Date();
	}
	var startDate = new Date(myDate.setDate(1));
	var endDate = new Date(myDate.setDate(monthMath(month)));
	obj.startDate = startDate.getFullYear() + '-' + (startDate.getMonth() + 1) + '-' + startDate.getDate();
	obj.endDate = endDate.getFullYear() + '-' + (endDate.getMonth() + 1) + '-' + endDate.getDate();
	obj.dateSize = monthMath(month);
	return obj;
}

//判断当前月份是否有31号
function monthMath(month) {
	var now = new Date(new Date().setMonth(month - 1));
	var d = new Date(now.setDate(31));
	if(d.getDate() > 30) {
		return 31;
	} else {
		return 30;
	}
}

function getMoney(x) {
	if(x > 100000) {
		var money = Number(x) / 10000;
		return money.toFixed(2).toString() + '万';
	} else {
		return x;
	}

}
//start end 计算起始  例如  0 为从第一个开始  5为第一个结束
//splitSize 间隔;
//data 要计算的list
//x,y 计算出来的  x和y轴 可为null;
//singleMax 单个最大

function getMonthData(start, end, splitSize, data, singleMax, max, all, x, y, callback) {
	if(!x) {
		x = [];
	}
	if(!y) {
		y = [];
	}
	if(!all) {
		all = 0;
	}
	var math = 0;
	for(var i = start; i < end; i++) {

		if(i < data.length) {
			var commission = Number(data[i].commission || 0) / 100;
			if(!singleMax) {
				singleMax = commission
			}
			if(data[i + 1] && singleMax < Number(data[i + 1].commission || 0) / 100) {
				singleMax = Number(data[i + 1].commission || 0) / 100;
			}
			math += commission
		}
	}
	if(!max) {
		max = math;
	}
	if(max < math) {
		max = math;
	}
	all += math;
	if(data.length > 0) {
		x.push(end);
		y.push(math);
	} else {
		x.push('当前无数据');
		y.push(0);
	}
	if(end < data.length) {
		if(end == 25 && data.length == 31) {
			getMonthData(start + splitSize, 31, splitSize, data, singleMax, max, all, x, y, function(singleMax, max, all, x, y) {
				callback(singleMax, max, all, x, y)
			});
		} else if(end == 25 && data.length == 29) {
			getMonthData(start + splitSize, 29, splitSize, data, singleMax, max, all, x, y, function(singleMax, max, all, x, y) {
				callback(singleMax, max, all, x, y)
			});
		} else if(end == 25 && data.length == 28) {
			getMonthData(start + splitSize, 28, splitSize, data, singleMax, max, all, x, y, function(singleMax, max, all, x, y) {
				callback(singleMax, max, all, x, y)
			});
		} else {
			getMonthData(start + splitSize, end + splitSize, splitSize, data, singleMax, max, all, x, y, function(singleMax, max, all, x, y) {
				callback(singleMax, max, all, x, y)
			});
		}

	} else {

		callback(singleMax, max, all, x, y);
	}
}

function getDayByTime(x) {
	var date = new Date(x);
	if(changeDateFormat(new Date().getTime()) == changeDateFormat(date)) {
		return '今天';
	} else {
		return changeDateFormat(date);
	}
}

function getDayByTimeForWeek(x) {
	var date = new Date(x);
	if(changeDateFormat(new Date().getTime()) == changeDateFormat(date)) {
		return '今天';
	} else if(date.getDay() == 0) {
		return '星期日';
	} else if(date.getDay() == 1) {
		return '星期一';
	} else if(date.getDay() == 2) {
		return '星期二';
	} else if(date.getDay() == 3) {
		return '星期三';
	} else if(date.getDay() == 4) {
		return '星期四';
	} else if(date.getDay() == 5) {
		return '星期五';
	} else if(date.getDay() == 6) {
		return '星期六';
	}
}

function getIncomeDetail(data, all, type) {
	var content = '';
	var top = '';
	if(data.length != 0) {
		for(var i = (data.length - 1); i < data.length && i >= 0; i--) {
			var ele = data[i];
			var _w = (((ele.commission || 0) / 100) / all) * 100;
			if(ele.createtime < new Date().getTime()) {
				var time;
				if(type == 'month') {
					time = getDayByTime(ele.createtime)
				} else {
					time = getDayByTimeForWeek(ele.createtime)
				}
				var timeF = new Date(ele.createtime);

				content += '<div class="bui_bgc_white bui_bds_1_b">' +
					'<div id="' + timeF.getFullYear() + '-' + (Number(timeF.getMonth()) + 1) + '-' + timeF.getDate() + '" style="background-size:' + _w + '% 100%" class=" bui_media bui_vm bui_p_12 bui_block" onclick="javascript:;showDayDetail(\'' + timeF.getFullYear() + '-' + (Number(timeF.getMonth()) + 1) + '-' + timeF.getDate() + '\',' + (ele.commission || 0) / 100 + ')">' +
					'<div class="bui_media_l">' +
					'<p class="bui_ts_12">' + time + '</p>' +
					'</div>' +
					'<div class="bui_media_b bui_ta_r bui_ts_12 ' + vueObj.style.tcFalse + '">' +
					//'<p class="bui_inline bui_vm">销售额：<span class="bui_ts_14 ' + vueObj.style.tcTrue + '">￥' + (ele.orderfee || 0) / 100 + '</span></p>' +
					'<p class="bui_inline bui_vm">收入：<span class="bui_ts_14 bui_tc_green">￥' + (ele.commission || 0) / 100 + '</span></p>' +
					'</div>' +
					'<div class="bui_media_r bui_ta_r">' +
					'<i class="fa fa-angle-right ' + vueObj.style.facFalse + '"></i>' +
					'</div>' +
					'</div>' +
					'<div class="dayDetail" style="display: none;">' +
					'</div>' +
					'</div>';
			}
		};
	} else {
		content = '<p class="bui_p_12 bui_p_24 bui_ta_c ' + vueObj.style.tcFalse + '">没有任何数据...</p>'
	}

	$('#incomeDetail').html(content);
}

function getCharts(type, month, year) {
	buijs_showloading('bui_bgc_black_f72');
	if(type == 'week') {
		var date = getWeek();
		console.log(date)
		$.ajax({
			type: "get",
			url: _jsonUrl + "/merriplusApi/getIncomeDayList",
			async: true,
			global: false,
			dataType: "json",
			data: {
				startDateStr: date.startDate,
				endDateStr: date.endDate,
				dataSize: 7
			},
			success: function(rs) {
				var x = ['周一', '周二', '周三', '周四', '周五', '周六', '周日'];
				var y = [];
				var incomeAll = 0;
				var salesAll = 0;
				var max = 0;
				for(var i = 0; i < 7; i++) {
					if(rs.data[i]) {

						var commission = Number(rs.data[i].commission || 0) / 100;
						var orderfee = 0;
						if(rs.data[i].commission > 0) {
							orderfee += Number(rs.data[i].orderfee) / 100;
						}
						if(i == 0) {
							max = commission
						}
						if(rs.data[i + 1] != undefined && max < Number(rs.data[i + 1].commission || 0) / 100) {

							max = Number(rs.data[i + 1].commission || 0) / 100;
						}
						incomeAll += commission;
						salesAll += orderfee;
						y.push(commission);
					} else {
						y.push(0);
					}
				}
				if(rs.data.length == 0) {
					x = ['暂无数据'];
					y = [0];
				}
				//				$('.all').html(incomeAll);
				//				$('.max').html(max);
				//				$('.avg').html((incomeAll / 7).toFixed(2));
				$('.incomeVal').text(incomeAll);
				$('.salesVal').text(salesAll);
				$('.incomeText').text('本周收入');
				$(".salesText").text('本周销售额');
				charts(x, y, 'auto');
				getIncomeDetail(rs.data, max, 'week');
				buijs_closeloading();

			}
		});

	} else if(type == 'month') {
		var date;
		if(month) {
			date = getMonth(month, year);
		} else {
			date = getMonth(new Date().getMonth() + 1, new Date().getFullYear());
		}
		$.ajax({
			type: "get",
			url: _jsonUrl + "/merriplusApi/getIncomeDayList",
			async: true,
			global: false,
			dataType: "json",
			data: {
				startDateStr: date.startDate,
				endDateStr: date.endDate,
				dataSize: date.dateSize
			},
			success: function(rs) {
				var incomeAll = 0,
					salesAll = 0;
				$.map(rs.data, function(data) {
					console.log(data)
					incomeAll += Number(data.commission || 0) / 100;
					if(data.commission > 0) {
						salesAll += Number(data.orderfee) / 100;
					}
				});

				$('.incomeVal').text(incomeAll);
				$('.salesVal').text(salesAll);

				getMonthData(0, 5, 5, rs.data, null, null, null, null, null, function(singleMax, max, all, x, y) {
					//					$('.all').html(all);
					//					$('.max').html(max);
					//					$('.avg').html((all / 30).toFixed(2));
					charts(x, y, 'auto');

					if(month) {
						$('.incomeText').text(year + '-' + month + ' 收入');
						$(".salesText").text(year + '-' + month + ' 销售额');
					} else {
						$('.incomeText').text('本月收入');
						$(".salesText").text('本月销售额');
					}
					getIncomeDetail(rs.data, singleMax, 'month');
					buijs_closeloading();
				});

			}
		});

	} else if(type == 'season') {
		var date = getTime(90);
		$.ajax({
			type: "get",
			url: _jsonUrl + "/merriplusApi/getIncomeDayList",
			async: true,
			global: false,
			dataType: "json",
			data: {
				startDateStr: date.startDate,
				endDateStr: date.endDate,
				dataSize: 90
			},
			success: function(rs) {
				getMonthData(0, 15, 15, rs.data, null, null, null, null, null, function(singleMax, max, all, x, y) {
					//					$('.all').html(all);
					//					$('.max').html(max);
					//					$('.avg').html((all / 90).toFixed(2));
					charts(x, y, 'auto');
					getIncomeDetail(rs.data, singleMax);
				});

			}
		});
	}
}
var myChart;

function charts(_x, _y, _d) {
	myChart = echarts.init(document.getElementById('charts'));

	myChart.setOption({
		backgroundColor: "rgba(243,123,29,1)",
		grid: {
			x: 50,
			y: 24,
			x2: 24,
			y2: 32,
			borderWidth: 0
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'line',
				lineStyle: {
					color: 'rgba(255,255,255,0.32)',
					width: 2,
					type: 'solid'
				},
				crossStyle: {
					color: '#1e90ff',
					width: 1,
					type: 'dashed'
				},
				shadowStyle: {
					color: 'rgba(150,150,150,1)',
					width: 'auto',
					type: 'default'
				}
			}
		},
		toolbox: {
			show: false,
			feature: {
				mark: {
					show: true
				},
				dataView: {
					show: true,
					readOnly: false
				},
				magicType: {
					show: true,
					type: ['line', 'bar', 'stack', 'tiled']
				},
				restore: {
					show: true
				},
				saveAsImage: {
					show: true
				}
			}
		},
		calculable: true,
		xAxis: [{
			axisLine: { // 轴线
				show: true,
				lineStyle: {
					color: 'white',
					type: 'solid',
					width: 1
				}
			},
			axisTick: { // 轴标记
				show: false
			},
			axisLabel: {
				show: true,
				interval: _d, // {number}
				rotate: 0,
				margin: 8,
				formatter: '{value}',
				textStyle: {
					color: 'white',
					fontFamily: '微软雅黑',
					fontSize: 12

				}
			},
			splitLine: {
				show: false
			},
			splitArea: {
				show: false

			},
			type: 'category',
			boundaryGap: true,
			data: _x
		}],
		yAxis: [{
			axisLine: { // 轴线
				show: false,
				lineStyle: {
					color: 'white',
					type: 'solid',
					width: 1
				}
			},
			axisTick: { // 轴标记
				show: false
			},
			axisLabel: {
				show: true,
				interval: 'auto', // {number}
				rotate: 0,
				margin: 8,
				formatter: '{value}',
				textStyle: {
					color: 'white',
					fontFamily: 'sans-serif',
					fontSize: 12
				}
			},
			splitLine: {
				show: true,
				lineStyle: { // 系列级个性化折线样式
					color: "rgba(255,255,255,0.33)",
					width: 1
				}
			},
			splitArea: {
				show: false
			},
			type: 'value'
		}],
		series: [{
			name: '收入',
			type: 'line',
			stack: '总量',
			data: _y,
			symbolSize: 2,
			showAllSymbol: false,
			itemStyle: {
				normal: {
					color: 'rgba(255,255,255,0.64)',
					lineStyle: { // 系列级个性化折线样式
						width: 2
					}
				},
				emphasis: {
					color: 'rgba(255,255,255,1)',
					borderWidth: 4
				}
			}
		}]
	});
}