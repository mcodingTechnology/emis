var member_memberJoinRecordList = function () {
	var tableId = "memberTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ "sTitle": "ID", "sWidth" : "5%", "mData": "memberId"},
			{ "sTitle": "名称", "sWidth" : "5%", "mData": "fullName"},
            {
                "sTitle": "购买总金额",
                "mDataProp" : "",
                "sWidth" : "5%",
                "sDefaultContent" : "",
                "sVisible" : false,
                "fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
                    $(nTd).append("￥"+oData.orderSumPrice/100);
                }
            },
            {
                "sTitle": "加入时间",
                "mDataProp" : "",
                "sWidth" : "5%",
                "sDefaultContent" : "",
                "sVisible" : false,
                "fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
                    $(nTd).append((oData.createTime==null ? "" :changeDateFormat(oData.createTime)));
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
        var searParam = $("#searchForm").serializeArray();
        oTable = $('#'+tableId).DataTable({
            "fnServerParams": function (aoData) {
                $.each(searParam, function(index, value){
                    aoData.push({"name": value.name, "value": value.value})
                });
            },
            "sAjaxSource": "memberJoinRecord/queryMemberBelowLineDataByPage",
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
                "sSearch" : "搜索会员名称",
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

    $('#btnReturn').click(function(){

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
    };

    return {
        init: function () {
            loadTableData();
            initToolBar();
        },
        loadTableData: loadTableData
    };
}();

