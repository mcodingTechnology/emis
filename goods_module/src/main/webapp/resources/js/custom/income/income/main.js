
var DataTableList = function () {
	var tableId = "dataTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ "sTitle": "呢称", "mData": "membername"},
			{ "sTitle": "真实姓名", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					if(oData.memberBankerInfo!=null)$(nTd).append(oData.memberBankerInfo.realname);
            }},
			{ "sTitle": "身份证", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					if(oData.memberBankerInfo!=null)$(nTd).append(oData.memberBankerInfo.identity);
            }},
			{ "sTitle": "级别", "mData": "levelName"},

        	{ "sTitle": "渠道", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(getChannelStr(oData.channelId));
            }},
			{ "sTitle": "累计佣金(元)", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(oData.commission/100);
            }},
			{ "sTitle": "待发放佣金(元)", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(oData.incomeUnsend/100);
            }},
			{ "sTitle": "当前分销商状态", "mData": "", "sDefaultContent":"","sVisible":false, 
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(oData.status=='completed'? '已完善': '未完善');
            }},
			
			//	操作模板
			//	{
			//		"sTitle": "", 
			//		"mDataProp" : "",
			//		"sDefaultContent" : "",	
			//		"sVisible" : false, 
			//		"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
			//			if(oData.orderNum == 0){
			//				$(nTd).append('XXXX');
			//			}else{
			//				$(nTd).append('OOOO');
			//			}
			//		}
			//	}
			
        ];
        
//        aoColumns.push({
//        	"sTitle": "操作",
//        	"mDataProp" : "",
//        	"sDefaultContent" : "",
//        	"sVisible" : false, 
//        	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
//        	    var btnUpdateByIdHtml = "<span style='margin: 0 5px 0 5px'><span class='btn btn-xs purple ajaxify' href='income/service/toUpdateViewById?id="+oData.id+"'><i class='fa fa-edit'></i> 更改 </span>";
//        		var btnUpdateByIdObj = $(btnUpdateByIdHtml).appendTo($(nTd));
//        		
//        		var btnDelteByIdHtml = "<span class='btn default btn-xs black' ><i class='fa fa-trash-o'></i>删除";
//        		var btnDelteByIdObj = $('<span>', {
//        			style : "margin: 0 5px 0 5px",
//        			click: function(){ deleteHandler(oData.id); }
//        		});
//        		btnDelteByIdObj.append(btnDelteByIdHtml);
//        		btnDelteByIdObj.appendTo($(nTd));
//        	}
//        });
        
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
                //aoData.push({"name": "orgId", "value": currtOrgId});
            },
            "sAjaxSource": "income/service/findByPage",
            "sAjaxDataProp": "queryResult",
            "bFilter" : true,
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
    
    var deleteHandler = function(id){
    	bootbox.confirm("确认删除吗?", function(result) {
			if (!result) {
				return;
			}
			var url = 'income/service/deleteById?id=' +id;
			
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
    
    var countIncome = function(){
    	var url = 'income/service/countIncome' ;
		
		$.getJSON(url, function(json){
			if(json && json.status == '00'){
				$('#total').text(json.data.total/100);
				$('#unSendTotal').text(json.data.unSendTotal/100);
				$('#sendTotal').text(json.data.sendTotal/100);
				$('#received').text(json.data.received/100);
				$('#unReceived').text(json.data.unReceived/100);
				$('#failed').text(json.data.failed/100);
				$('#refund').text(json.data.refund/100);
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
            loadTableData();
            countIncome();
        }
    };
}();

