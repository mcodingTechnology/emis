//员工通讯录
var member_memberSalesLogList = function () {
	var tableId = "memberTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
            { "sTitle": "流水id", "sWidth" : "2%", "mData": "saleLogId"},
            { "sTitle": "营销活动id", "sWidth" : "2%", "mData": "saleId"},
			{ "sTitle": "营销活动名称", "sWidth" : "5%", "mData": "saleName"},
			{
            	"sTitle": "营销信息发送方式",
            	"sWidth" : "5%",
            	"mDataProp" : "",
				"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if(oData.saleSendMsgType=="SMS" || oData.saleSendMsgType=="短信"){
            			$(nTd).append("<label class='label label-info'>短信</label>");
            		}else if(oData.gender=="WX" || oData.gender=="微信"){
            			$(nTd).append("<label class='label label-info'>微信</label>");
            		}
            		else{
            			$(nTd).append("<label class='label label-info'>暂无</label>");
            		}
            	}
            },
			{ "sTitle": "会员id", "sWidth" : "5%", "mData": "memberId"},
			{ "sTitle": "会员手机号码", "sWidth" : "5%", "mData": "memberPhoneNumber"},
			{ "sTitle": "会员openid", "sWidth" : "5%", "mData": "memberOpenid"},
			{
            	"sTitle": "是否参加了活动",
            	"sWidth" : "5%",
            	"mDataProp" : "",
				"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if(oData.isIntoAct=="Y" || oData.isIntoAct=="是"){
            			$(nTd).append("<label class='label label-info'>是</label>");
            		}
            		else{
            			$(nTd).append("<label class='label label-info'>否</label>");
            		}
            	}
            },
            { 
				"sTitle": "营销信息发送时间",
				"mDataProp" : "",
				"sDefaultContent" : "",
            	"sWidth" : "5%",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
						$(nTd).append(changeDateFormat(oData.createTime));
				}
			}
        ];
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
        var search = $("#searchForm").serializeArray();
        oTable = $('#'+tableId).DataTable({
        	"fnServerParams": function (aoData) {
        		$.each(search, function(index, value){
        			aoData.push({"name": value.name, "value": value.value})
                });
        	},
            "sAjaxSource": "querySalesLogData",
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
            "oSearch":false,
            "aoColumns": headerInfo.aoColumns,
            "aLengthMenu": [
                [10, 20, 30, 40, -1],
                [10, 20, 30, 40, 50]
            ],
            "iDisplayLength": 10,
            "oLanguage": {
            	"sProcessing" : "努力加载中...",
                "sLengthMenu": "显示 _MENU_ 条记录 ",
                "sInfoEmpty" : "搜索结果为0条记录",
                "sInfoFiltered": "(从 _MAX_ 条记录中过滤出)",
                "sZeroRecords" : "没有您要搜索的内容", 
                "sSearch" : "搜索手机号码", 
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

	$("#searchForm").submit(function(e){
		e.preventDefault();
		oTable.fnDestroy();
		member_memberSalesLogList.loadTableData();
     });

	$('#btnReset').click(function(){
		document.getElementById("mobilePhone").value="";	  	
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
            $("#saleId").val('').val(id);
        });
        $("#confirmWin2").on("show.bs.modal", function(e){
        	debugger;
        	var id = $(e.relatedTarget).attr("id");
        	$("#saleId").val('').val(id);
        	var status = $(e.relatedTarget).attr("status");
        	if(status == 'QY'){
        		$("#saleStatus").val('').val('TY');
        	}else {
        		$("#saleStatus").val('').val('QY');
        	}
        });
        $("#confirmWin3").on("show.bs.modal", function(e){
        	var id = $(e.relatedTarget).attr("id");
        	$("#saleId").val('').val(id);
        });
       //营销信息发送
        $("#sendMsgBut").on("click", function(){
        	var param = {saleId : $("#saleId").val(),memberTageIds:'41'};
            $.ajax({
                type : "post",
                dataType : "json",
                url : "sendSalesMsg",
                data : param,
                success : function(data) {
                    if(data.code==0){
                        oTable.fnReloadAjax();
                        bootbox.alert("营销信息发送成功");
                        $("#confirmWin3").modal("hide");
                    }else{
                        bootbox.alert("营销信息发送失败");
                    }
                }
            });
        });
        //停用启用事件
        $("#disableOrEnabledBut").on("click", function(){
        	var status = $("#saleStatus").val();
        	if(!status){
        		status = "QY";
        	}
        	var param = {saleId : $("#saleId").val(),saleStatus:status};
            $.ajax({
                type : "post",
                dataType : "json",
                url : "disableOrEnabledMemberSales",
                data : param,
                success : function(data) {
                    if(data.code==0){
                        oTable.fnReloadAjax();
                        bootbox.alert("停用/启用成功");
                        $("#confirmWin2").modal("hide");
                    }else{
                        bootbox.alert("停用/启用失败");
                    }
                }
            });
        });
        
    	//删除事件
        $("#deleteBut").on("click", function(){
        	var param = {saleId : $("#saleId").val()};
            $.ajax({
                type : "post",
                dataType : "json",
                url : "deleteMemberSales",
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

