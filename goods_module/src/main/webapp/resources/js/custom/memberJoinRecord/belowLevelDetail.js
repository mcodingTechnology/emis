//员工通讯录
var member_memberJoinRecordList = function () {
	var tableId = "memberTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ "sTitle": "申请人", "sWidth" : "5%", "mData": "membername"},
			{ "sTitle": "申请级别", "sWidth" : "5%", "mData": "levelname"},
			{ "sTitle": "推荐人", "sWidth" : "5%", "mData": "parentname"},
            { "sTitle": "推荐人级别","sWidth" : "5%", "mData": "parentlevelname"},
            { "sTitle": "申请进度","sWidth" : "5%", "sDefaultContent" : "",
            	"fnCreatedCell": function(nTd, sData, oData, iRow, iCol){
            		switch(oData.status){
            		case "100" :
            			$(nTd).append("申请中");
            			break;
            		case "101" :
            			$(nTd).append("已通过");
            			break;
            		default :
            			$(nTd).append("未知");
            		}
            	}
            },
            {
                "sTitle": "加入时间",
                "mDataProp" : "",
                "sWidth" : "5%",
                "sDefaultContent" : "",
                "sVisible" : false,
                "fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
                    $(nTd).append((oData.confirmtime==null ? "" :changeDateFormat(oData.confirmtime)));
                }
            }
        ];
        //渲染特殊的列(操作列)
        var aoColumnDefs =[];
            aoColumns.push({
            	"sTitle": "操作",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sWidth" : "5%",
            	"sVisible" : false, 
            	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
            		if (oData.status == '100') {
                        /*显示通过按钮*/
                        if(App.checkUserOperRight("memberJoinRecordList", "confirmMemberJoin")){
                            $(nTd).append("<span class='btn default btn-xs black' data-toggle='modal' " +
                                "href='#confirmWin' enable='ok' " + "id='"+ oData.id+"' >" +
                                "<i class='fa fa-check'></i> 通过 </span>");
                        }
                    } else {
                        $(nTd).append("<span class='btn btn-xs purple black ajaxify' data-toggle='modal' " +
                            "href='memberJoinRecord/saleDataDetailView.html?id="+oData.id+"&name="+oData.membername+"&memberId="+oData.memberid+"' enable='ok' " + "id='" + oData.id + "'>" +
                            "<i class='fa fa-edit'></i> 销售数据 </span>&nbsp;");
                        $(nTd).append("<span class='btn default btn-xs black ajaxify' data-toggle='modal' " +
                            "href='memberJoinRecord/belowLevelDetailView.html?id="+oData.id+"&name="+oData.membername+"&memberId="+oData.memberid+"' enable='ok' " + "id='" + oData.id + "' >" +
                            " TA的下级  </span>");
                        $(nTd).append("&nbsp;<span class='btn default btn-xs black ajaxify' data-toggle='modal' " +
                            "href='memberJoinRecord/belowLineDetailView.html?id="+oData.id+"&name="+oData.membername+"&memberId="+oData.memberid+"' enable='ok' " + "id='" + oData.id + "' >" +
                            " TA的下线  </span>");
                    }
            		 
                }
            });

        return {
            aoColumns : aoColumns,
            aoColumnDefs : aoColumnDefs
        };
    };
    
    
    //加载datatable表格数据
    var loadTableData = function(){
        var headerInfo = initTableHeaderInfo();
        var searParam = $("#searchForm").serializeArray();
        oTable = $('#'+tableId).DataTable({
            "fnServerParams": function (aoData) {
                $.each(searParam, function(index, value){
                    aoData.push({"name": value.name, "value": value.value})
                });
            },
            "sAjaxSource": "memberJoinRecord/queryMemberJoinRecordData",
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
                "sSearch" : "搜索加盟商名称",
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
        member_memberJoinRecordList.loadTableData();
     });
    
    $('#btnReset').click(function(){
        $("#searchForm")[0].reset();
    });


    var initToolBar = function(){
        //绑定日历
        if (jQuery().timepicker) {
            $('.timepicker').timepicker({
                template: 'dropdown' ,
                showMeridian: false,
                autoclose: true,
                language: "zh-CN",
                defaultTime:""
            });
        }


        //绑定日历
        if (jQuery().datepicker) {
            $('.date-picker').datepicker({
                format: 'yyyy-mm-dd hh:ii',
                weekStart: 1,
//                  minuteStep: 10,
                todayBtn: 'linked',
                rtl: App.isRTL(),
                language: "zh-CN",
                //minView: 'hour',　　　　//日期时间选择器所能够提供的最精确的时间选择视图。
                autoclose: true
            });
        }
        if (jQuery().datetimepicker) {
            $(".form_datetime").datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                weekStart: 1,
                todayBtn: 'linked',
                rtl: App.isRTL(),
                language: "zh-CN",
                autoclose: true
            });
        }

    	//触发相关赋值
        $("#confirmWin").on("show.bs.modal", function(e){
            var id = $(e.relatedTarget).attr("id");
            $("#id").val('').val(id);
        });
    	//删除事件
        $("#deleteBut").on("click", function(){
        	var param = {recordId : $("#id").val()};
            $.ajax({
                type : "post",
                dataType : "json",
                url : "memberJoinRecord/confirmMemeberJoin",
                data : param,
                success : function(data) {
                    if(data.status=='00'){
                        oTable.fnReloadAjax();
                        bootbox.alert("通过成功");
                        $("#confirmWin").modal("hide");
                    }else{
                        bootbox.alert("通过失败");
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

