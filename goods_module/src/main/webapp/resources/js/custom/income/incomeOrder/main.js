
var DataTableList = function () {
	var tableId = "dataTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ "sTitle": "分销商名", "mData": "membername"},
			{ "sTitle": "佣金(元)", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(oData.commission/100);
            }},
			{ "sTitle": "收入类型", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(oData.ext=="2"?'扣款':'收入');
            }},
			{ "sTitle": "计算时间", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(moment(oData.createtime).format('YYYY-MM-DD HH:mm'));
            }},
            { "sTitle": "订单编号", "mData": "ext1"},
			{ "sTitle": "原订单会员名称", "mData": "ordermembername"},
			{ "sTitle": "原订单费用", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(oData.orderfee/100);
            }},
			{ "sTitle": "下单时间", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(moment(oData.ordertime).format('YYYY-MM-DD HH:mm'));
            }},
			
        ];
        
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
        	"fnServerParams": function (aoData) {
        		setQueryParams(aoData);
            },
            "sAjaxSource": "incomeOrder/service/findByPage",
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
//            "aoSearchCols": [null, null, {"sSearch":"myfilter"}, null, null, null, null, null, null],
            "aLengthMenu": [
                [10, 20, 30, 40, -1],
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
            }
        });
    };
    
    var setQueryParams = function(aoData){
    	var params = [];
    	params.push({"name": "startDate", "value": $('#queryStartTime').val()});
    	params.push({"name": "endDate", "value": $('#queryEndTime').val()});
    	params.push({"name": "memberName", "value": $('#queryMember').val()});

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
    }
    
    var getHtml = function(params, cb){
    	getDetailByAjax(params, function(data){
    		var incomeOrderProduct = '';
    		$.each(data.incomeOrderProductList, function(i, item){
    			incomeOrderProduct = incomeOrderProduct +
    			'<tr>'+
    			'<td style="font-weight:bold;">'+item.productName+':</td>' +
    	        '<td style="padding-right:-30px;">数量('+item.productCount+')，&nbsp;佣金('+item.income/100+'元)&nbsp;</td>'+
    	        '</tr>'
    			;
    		});
    		
    		var html =  
    	    	'<table><tbody>'+
    	    	    '<tr>'+
    	    	        '<td style="font-weight:bold;">分销商:</td>' +
    	    	        '<td style="padding-right:-30px;">'+data.member.levelName+'，&nbsp;'+getChannelStr(data.member.channelsId)+'&nbsp;</td>'+
    	    	    '</tr>'+incomeOrderProduct
    	    	 '</tbody></table>';
    		cb(html)
    	});
    }
    
    var getDetailByAjax = function(params, cb){
    	var url = 'incomeOrder/service/getIncomeOrderDetail?incomeOrderId='+params.id+'&memberId='+params.memberid;
		$.getJSON(url, function(json){
			if(json && json.status == '00'){
				cb(json.data)
				return;
			}else{
				return bootbox.alert("操作失败,请刷新后重试");
			}
		});
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
 
    return {
        init: function () {
        	$(".form_datetime").datetimepicker({
        		format : "yyyy-mm-dd",
        		autoclose : true,
        		pickerPosition : "bottom-left",
        		startView:'month',
        		maxView:'decade',
        		minView:'month',
        		todayBtn:true,
        		initialDate:new Date()
        	});
            loadTableData();
        },
        reload:function(){
        	oTable.fnReloadAjax();
        }
    };
}();

