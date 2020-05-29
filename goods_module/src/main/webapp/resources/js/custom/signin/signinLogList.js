//员工通讯录
var queryData_list = function () {
	var tableId = "tableId";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ "sTitle": "ID", "sWidth" : "5%", "mData": "id"},
			{ "sTitle": "品牌", "sWidth" : "5%", "mData": "brandcode"},
			{ "sTitle": "微信Openid", "sWidth" : "5%", "mData": "openid"},
			{ "sTitle": "昵称", "sWidth" : "5%", "mData": "membername"},
			{
				"sTitle": "获得积分",
				"mDataProp" : "",
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					$(nTd).append(oData.signintegral+"分");
				}
			},
            {
                "sTitle": "签到日期",
                "mDataProp" : "",
                "sDefaultContent" : "",
                "sVisible" : false,
                "fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
                    $(nTd).append(changeYearDateFormat(oData.signtime));
                }
            }
        ];

        return {
            aoColumns : aoColumns
        };
    };
    
    
    //加载datatable表格数据
    var loadTableData = function(){
        var headerInfo = initTableHeaderInfo();
        var openid = $("#openid").val();
        console.log(openid);
        oTable = $('#'+tableId).DataTable({
        	"fnServerParams": function (aoData) {
        			aoData.push({"name":"openid", "value":openid})
        	},
            "sAjaxSource": "querySigninLogData",
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
                "sSearch" : "搜索",
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
    
    $('#btnSearch').click(function(){
    	oTable.fnDestroy();
    	queryData_list.loadTableData();
     });
    //清空搜索条件
    $("#btnReset").on("click", function(){
    	var search = $("#searchForm").serializeArray();
		$.each(search, function(index, value){
			$("#"+value.name).val('');
        });
    });

    var initToolBar = function(){
    	//触发相关赋值
        $("#confirmWin").on("show.bs.modal", function(e){
            var id = $(e.relatedTarget).attr("id");
            $("#id").val('').val(id);
            var brandCode = $(e.relatedTarget).attr("brandCode");
            $("#brandCode").val('').val(brandCode);
        });
    	//删除事件
        $("#deleteBut").on("click", function(){
        	var param = {teplId : $("#id").val(),brandCode : $("#brandCode").val()};
            $.ajax({
                type : "post",
                dataType : "json",
                url : "deleteMember",
                data : param,
                success : function(data) {
                    if(data.code==0){
                        oTable.fnReloadAjax();
                        bootbox.alert("删除成功");
                        $("#confirmWin").modal("hide");
                    }else{
                        bootbox.alert("删除失败");
                    }
                }
            });
        });
    };

    return {
        init: function () {
            loadTableData();
            initToolBar();
        },
        loadTableData: loadTableData
    };
}();

