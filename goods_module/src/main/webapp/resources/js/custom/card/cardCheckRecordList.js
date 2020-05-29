var tableList = function () {
	var tableId = "tableList";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
            { "sTitle": "ID", "mData": "id"},
            { "sTitle": "名称", "mData": "cardName"},
            { "sTitle": "卡券前缀编号", "mData": "codePrefix"},
            { "sTitle": "全券号", "mData": "code"},
            { "sTitle": "客户id", "mData": "user"},
            {
            	"sTitle": "核销时间",
            	"mDataProp" : "",
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
    	console.log(oTable);
        var headerInfo = initTableHeaderInfo();
        var search = $("#SearchForm").serializeArray();
        oTable = $('#'+tableId).DataTable({
        	"fnServerParams": function (aoData) {
        		$.each(search, function(index, value){
        			aoData.push({"name": value.name, "value": value.value})
                });
            },
            "sAjaxSource": "cardPrivilegeCheckRecord/service/findByPage",
            "sAjaxDataProp": "queryResult",
            "bFilter" : true,
            "bInfo": true,
            "bJQueryUI": true,
            "bLengthChange": true,
            "bPaginate": true,
            "bProcessing": true,
            "bSort": false,
            "bSortClasses": true,
            "bStateSave": true,
            "bAutoWidth":false,
            "bSortCellsTop": true,
            "iTabIndex": 1,
            "sServerMethod": "POST",
            "bServerSide": false,
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
                "sSearch" : "客户搜索：",
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

    //初始化界面相关事件
    var initPageEvent = function(){
    	//保存
	    $("#inputSearch").on("click", function(){
	    	oTable.fnDestroy();
	    	loadTableData();	
	    	clean();
	    });
    	
        //注册点击查看详情事件
        $('#'+tableId).on('click', ' tbody td .row-details', function () {
            var nTr = $(this).parents('tr')[0];
            //获取隐藏域的userId值
            var userId = $(this).parents('tr').find("input:hidden").val();
            if (oTable.fnIsOpen(nTr)){
                /* 关闭 */
                $(this).addClass("row-details-close").removeClass("row-details-open");
                oTable.fnClose(nTr);
            }else{
                /* 打开 */
                $(this).addClass("row-details-open").removeClass("row-details-close");
                ORG_ContactList.fnFormatDetails(oTable, nTr, userId);
            }
        });
        
        jQuery('#'+tableId).on('change', '.group-checkable', function(){
            var set = jQuery(this).attr("data-set");
            var checked = jQuery(this).is(":checked");
            jQuery(set).each(function () {
                if (checked) {
                    $(this).attr("checked", true);
                    $(this).parents('tr').addClass("active");
                } else {
                    $(this).attr("checked", false);
                    $(this).parents('tr').removeClass("active");
                }
            });
            jQuery.uniform.update(set);
        });
        
        
    };
    
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


        $("#statusSearch").on("click", function(){
            oTable.fnDestroy();
            loadTableData();
            clean();
        });

        $('#btnReset').click(function(){
            $("#SearchForm")[0].reset();
        });

        $("#exportExcel").on("click", function(){
            if(!confirm("导出时间有点久，确定要执行此操作？")){
                return;
            }
            var user = $("#user").val() || '';
            var startTime = $("#startTime").val() || '';
            var endTime = $("#endTime").val() || '';
            var cardName = $("#cardName").val()|| '';
            var codePrefix = $("#codePrefix").val()|| '';

            var params = 'user='+user+'&startTime='+startTime+'&endTime='+endTime+
                '&cardName='+cardName+'&codePrefix='+codePrefix;

            var url = 'cardPrivilegeCheckRecord/service/exportExcel';
            window.open(url + '?' + params);
        });
    };

    return {
        init: function () {
            loadTableData();
            initPageEvent();
            initToolBar();
        },
        loadTableData:loadTableData
    };
}();

