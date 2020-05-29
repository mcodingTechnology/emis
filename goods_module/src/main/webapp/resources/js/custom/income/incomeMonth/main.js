
var DataTableList = function () {
	
	var tableId = "dataTable";
	var oTable = null;
	var selectIds = [];
	var incomeOrderData = {};
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
        	{ "sTitle": "月份", "mData": "month"},
        	{ "sTitle": "渠道", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(getChannelStr(oData.channelId));
            }},
			{ "sTitle": "会员名称", "mData": "memberName"},
			{ "sTitle": "会员级别", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					var level = oData.levelId == 5 ? '1级' : oData.levelId == 3 ? '2级' : oData.levelId == 1 ? '3级' : oData.levelId == 4 ? '4级' : '未知';
					$(nTd).append(level);
            }},
            { "sTitle": "真实姓名", "mData": "realName"},
            { "sTitle": "身份证号", "mData": "identity"},
			{ "sTitle": "销售额(元)", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(oData.orderFee/100);
            }},
			{ "sTitle": "结算佣金(元)", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(oData.income/100);
            }},
			{ "sTitle": "状态", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(getStatusStr(oData.status));
            }},
			
        ];
        
        aoColumns.push({
        	"sTitle": "操作",
        	"mDataProp" : "",
        	"sDefaultContent" : "",
        	"sVisible" : false, 
        	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
				if(App.checkUserOperRight("income_month_menu", "comfirmIncomeMonth")){
					if(oData.status == '1' || oData.status == '2'){
						var btnValue = oData.status == '1' ? '审核' : '取消审核';
						var checkValue = oData.status == '1' ? 1 : 0;
						var btnCheckByIdHtml = "<span class='btn default btn-xs black' >" +
								"" + btnValue;

						var btnDelteByIdObj = $('<span>', {
							style : "margin: 0 5px 0 5px",
							click: function(){ checkHandler(oData.id, checkValue); }
						});
						btnDelteByIdObj.append(btnCheckByIdHtml);
						btnDelteByIdObj.appendTo($(nTd));
					}
        		}
        	}
        });
        
        aoColumns.unshift({
        	"sTitle": "详情",
        	"mDataProp" : "",
        	"sDefaultContent" : "",
        	"sVisible" : false, 
        	"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
        		var btnDetailByIdObj = $('<span>', {
        			"class":"row-details row-details-close",
        			click: function(){ showDetail(this, iRow, oData);}
        		});
        		btnDetailByIdObj.appendTo($(nTd));
        	}
        });
        
        aoColumns.unshift({ "sTitle": '<th class="table-checkbox"><input type="checkbox" id="checkbox_th"/></th>', 
			"mDataProp" : "",
			"sDefaultContent" : "",
			"sVisible" : false, 
			"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
				
				var checkboxObj = $('<input>', {
					"class": "incomeMonthCheckbox",
					disabled:oData.status!="1" && oData.status!="2" ? true:false,
					name:"incomeMonthCheckbox_" + oData.id,
					type: "checkbox",
					change : function(){ incomeMonthCheckboxChange(); }
        		});
				$(nTd).append(checkboxObj);
			}
        });
        
        //渲染特殊的列(操作列)
        var aoColumnDefs =[];
        return {
            aoColumns : aoColumns,
            aoColumnDefs : aoColumnDefs
        };
    };
    
    //加载datatable表格数据
    var loadTableData = function(){
        var headerInfo = initTableHeaderInfo();
        oTable = $('#'+tableId).DataTable({
            "sAjaxSource": "incomeMonth/service/findByPage",
            "sAjaxDataProp": "queryResult",
            "bFilter" : false,
            "bInfo": true,
            "bJQueryUI": true,
            "bLengthChange": true,
            "bPaginate": true,
            "bProcessing": true,
            "bSort": false,
            "bSortClasses": true,
            "bStateSave": false,
            "bAutoWidth":true,
            "bSortCellsTop": true,
            "iTabIndex": 1,
            "sServerMethod": "POST",
            "bServerSide": true,
            "aoColumns": headerInfo.aoColumns,
            "aLengthMenu": [
                [10, 20, 30, 40, 50],
                [10, 20, 30, 40, 50]
            ],
            "iDisplayLength": 10,
            "oLanguage": {
            	"sProcessing" : "努力加载中...",
                "sLengthMenu": "显示 _MENU_ 条记录",
                "sInfoEmpty" : "搜索结果为0条记录",
                "sInfoFiltered": "(从 _MAX_ 条记录中过滤出)",
                "sZeroRecords" : "没有您要搜索的内容", 
                "sSearch" : "搜索：", 
                "sInfo": "当前显示 _START_ 到 _END_ 总共 _TOTAL_ 条记录",
                "oPaginate": {
                    "sFirst":"首页",
                    "sPrevious": "上一页",
                    "sNext": "下一页",
                    "sLast":"末页"
                }
            },
            "fnServerParams": setQueryParams,
            "fnInitComplete": function() {
                $('#checkbox_th').change(function(event){
                	$(".incomeMonthCheckbox[disabled!='disabled']").attr("checked", event.target.checked);
                	incomeMonthCheckboxChange();
                });
            },
            "fnDrawCallback":function(){
            	incomeMonthCheckboxChange();
            	$('#checkbox_th').attr("checked", false);
            },
        });
    };
    
    var checkHandler = function(id, isEnable){
    	var checkValue = isEnable == 1? '审核':'取消审核';
    	bootbox.confirm("确认"+checkValue+"吗?", function(result) {
			if (!result) {
				return;
			}
			var url = 'incomeMonth/service/checkIncomeMonth?id=' +id + '&isEnable=' +isEnable;
			
			$.getJSON(url, function(json){
				if(json && json.status == '00'){
					bootbox.alert("操作成功");
					oTable.fnReloadAjax();
					return;
				}else{
					return bootbox.alert("操作失败,请刷新后重试");
				}
			});
		});
    };
    
    var batchCheckHandler =function(ids){
    	if(selectIds.length == 0){
    		return bootbox.alert("没找到选中的记录");
    	}
    	bootbox.confirm("确认批量审核吗?", function(result) {
			if (!result) {
				return;
			}
			var url = 'incomeMonth/service/batchCheckIncomeMonth?ids=' + selectIds.join(',');
			$.getJSON(url, function(json){
				if(json && json.status == '00'){
					bootbox.alert("操作成功");
					oTable.fnReloadAjax();
					return;
				}else{
					return bootbox.alert("操作失败,请刷新后重试");
				}
			});
		});
    }
    
    var setQueryParams = function(aoData){
    	var params = [];
    	params.push({"name": "month", "value": $('#queryMonth').val()});
    	params.push({"name": "memberName", "value": $('#queryMember').val()});
    	params.push({"name": "channel", "value": $('#queryChannel').val()});

    	$.each(aoData, function(i, item){
			if(item.name == 'iDisplayStart' || item.name == 'iDisplayLength'){
				params.push(item);
			}
		});
		aoData.splice(0, aoData.length);
		$.each(params, function(i, item){
			aoData.push(item);
		});
    }
    
    var showDetail = function(icon, iRow, oData){
    	var tr = oTable.fnGetNodes(iRow);
    	if(!oTable.fnIsOpen(tr)){
    		getHtml(oData, function(html){
    			oTable.fnOpen(tr, html);
    		});
    		$(icon).addClass("row-details-open").removeClass("row-details-close");
    	}else{
    		oTable.fnClose(tr);
    		$(icon).addClass("row-details-close").removeClass("row-details-open");
    	}
    };
    
    var getHtml = function(params, cb){
    	var key = params.id + '';
		var data = incomeOrderData[key];
		if(data && data != null ){
			return cb(buildTableHtml(data));
		}
    	
    	getDetailByAjax(params, function(result){
    		incomeOrderData[key] = result;
    		cb(buildTableHtml(result));
    	});
    };
    
    var getDetailByAjax = function(params, cb){
    	var startDate = moment($('#queryMonth').val(), "YYYY-MM").format('YYYY-MM-DD');
    	var endDate = moment($('#queryMonth').val(), "YYYY-MM").add(1, 'M').format('YYYY-MM-DD');
    	var url = 'incomeOrder/service/findByPage?startDate=' + startDate + '&endDate=' + endDate + '&memberId=' + params.memberId + '&iDisplayLength=200';
		$.getJSON(url, function(json){
			if(json){
				cb(json.queryResult)
				return;
			}else{
				return bootbox.alert("操作失败,请刷新后重试");
			}
		});
    }
    
    var buildTableHtml = function(data){
    	var incomeOrderProduct = '';
		$.each(data, function(i, item){
			var type = item.ext=="2"?'扣款':'收入';
			incomeOrderProduct = incomeOrderProduct +
			'<tr>'+
			'<td>'+ moment(item.ordertime).format('YYYY-MM-DD HH:mm') +'</td>' +
			'<td><a href="javascript:detailWin.show(\''+item.orderid +'\');">'+item.ext1+'</a></td>' +
			'<td>'+ item.orderfee / 100 +'</td>' +
			'<td>'+ item.commission /100 +'</td>' +
			'<td>'+ type +'</td>' +
	        '</tr>'
			;
//			incomeOrderProduct = '<a href="javascrpt:detailWin(\''+item.ext1 +'\');">'+incomeOrderProduct+'</a>'
		});
//		
		var html =  
	    	'<table class="table table-striped table-bordered table-hover">'+
	    	    '<tr>'+
	    	        '<td>时间</td><td>订单号</td><td>销售额</td><td>佣金</td><td>类型</td>' +
	    	    '</tr>'+incomeOrderProduct+
	        '</table>';
		return html;
    };
    
    var getStatusStr = function(status){
    	if(status == '0'){
    		return '会员资料未完善'
    	}else if(status == '1' ){
    		return '未审核';
    	}else if(status == '2' ){
    		return '已审核(未发红包)';
    	}else if(status == '3' ){
    		return '已发红包';
    	}else if(status == '4' ){
    		return '已收红包';
    	}else if(status == '5' ){
    		return '红包未收完';
    	}else if(status == '6' ){
    		return '红包发送失败';
    	}
    }
    
    var getChannelStr = function(channelsid){
    	if(channelsid==1){
			return "员工渠道";
		}else if(channelsid==2){
			return "BCP美丽社群渠道";
		}else if(channelsid==3){
            return "美丽健康渠道";
        }else if(channelsid==4){
            return "BIG生活线下健身房渠道";
        }else if(channelsid==5){
            return "BIG生活美骑线下渠道";
        }else{
			return "其他";
		}
    }
    
    var incomeMonthCheckboxChange = function(){
    	selectIds.splice(0, selectIds.length);
    	var c = $('.incomeMonthCheckbox:checked');
    	$.each($('.incomeMonthCheckbox:checked'), function(i, item){
    		selectIds.push($(item).attr("name").split("_")[1]);
    	})
    	$('#batchCheckBtnValue').text("批量审核("+selectIds.length+"个)");
    }
    
    var initEvent = function(){
    	$('#batchCheckBtn').click(batchCheckHandler);
    }
 
    return {
        init: function () {
        	$(".form_datetime").datetimepicker({
        		format : "yyyy-mm",
        		autoclose : true,
        		pickerPosition : "bottom-left",
        		startView:'year',
        		maxView:'year',
        		minView:'decade',
        		todayBtn:true,
        		initialDate:new Date()
        	});
            loadTableData();
            initEvent();
        },
        reload: function(){
			oTable.fnReloadAjax();
        }
        
    };
}();

