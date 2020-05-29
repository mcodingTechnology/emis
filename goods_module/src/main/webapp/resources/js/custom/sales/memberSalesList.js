//员工通讯录
var member_memberSalesList = function () {
	var tableId = "memberTable";
	var tagTableId = "memberTageTable";
	var oTable = null;
	var tagTable = null;
	var sendTagIdArr = [];
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
            { "sTitle": "编号", "sWidth" : "1%", "mData": "saleId"},
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
            },{
            	"sTitle": "营销活动状态",
            	"sWidth" : "5%",
            	"mDataProp" : "",
				"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if(oData.saleStatus=="TY" || oData.saleStatus=="停用"){
            			$(nTd).append("<label class='label label-info'>停用</label>");
            		}else if(oData.saleStatus=="QY" || oData.saleStatus=="启用"){
            			$(nTd).append("<label class='label label-info'>启用</label>");
            		}
            		else{
            			$(nTd).append("<label class='label label-info'>暂无</label>");
            		}
            	}
            },
           {
            	"sTitle": "营销活动类型",
            	"mDataProp" : "",
				"sDefaultContent" : "",
            	"sWidth" : "5%",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if(oData.saleType=="YXTS"){
            			$(nTd).append("<label class='label label-info'>营销推送</label>");
            		}else if(oData.saleType=="DSF"){
            			$(nTd).append("<label class='label label-info'>第三方活动</label>");
            		}else{
            			$(nTd).append("<label class='label label-info'>暂无</label>");
            		}
            	}
            },
			{ 
				"sTitle": "活动开始时间",
				"mDataProp" : "",
				"sDefaultContent" : "",
            	"sWidth" : "5%",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
						$(nTd).append(changeDateFormat(oData.saleStartTime));
				}
			},
			{ 
				"sTitle": "活动结束时间",
				"mDataProp" : "",
				"sDefaultContent" : "",
            	"sWidth" : "5%",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
						$(nTd).append(changeDateFormat(oData.saleEndTime));
				}
			},
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
        			$(nTd).append("<span style='margin: 0 10px 0 0px;' class='btn default btn-xs black' data-toggle='modal' href='#confirmWin' id="+oData.saleId+"><i class='fa fa-trash-o'></i> 删除 </span>");
        			$(nTd).append("<span class='btn default btn-xs black purple' data-toggle='modal' " +
        					"href='#confirmWin2' enable='ok' " + "id='"+ oData.saleId+"' status='"+oData.saleStatus+"'>" +
                    "<i class='fa fa-trash-o'></i> 停用/启用 </span>&nbsp;&nbsp;");
        			if(oData.saleStatus == 'TY'){
        				$(nTd).append("<span class='btn btn-xs purple ajaxify'"
    							+ " href='saveMemberSalesConfigView?id" +
    									"="
    							+ oData.saleId
    							+ "'><i class='fa fa-edit'></i> 修改 </span>");
        			}else if(oData.saleStatus == 'QY') {
        				$(nTd).append("<span class='btn default btn-xs black purple' data-toggle='modal' " +
            					"href='#confirmWin3' enable='ok' " + "id='"+ oData.saleId+"'>" +
                        "<i class='fa fa-trash-o'></i> 发送营销信息 </span>&nbsp;&nbsp;");
        			}
        		}
            });

        return {
            aoColumns : aoColumns,
            aoColumnDefs : aoColumnDefs
        };
        
    };
    
  //初始化加载标签表格数据的表头定义
    var initTagTableHeaderInfo = function(){
        var aoColumns = [
        	{
	             "sTitle":'<th class="table-checkbox"><input type="checkbox" class="group-checkable" data-set="#'+tagTableId+' .checkboxes"/></th>',
	             "bSortable": false,
	             "bSearchable": false,
	             'sWidth':'2%',
	             "mDataProp" : "",
	             "sDefaultContent" : "",
	             "sVisible" : false,
	             "fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
	                 $(nTd).append('<td class="table-checkbox">');
	                 var falg = -1;
	                 for(var i = 0 ; i < sendTagIdArr.length ; i++){
	                	 if(sendTagIdArr[i] == oData.tagsId){
	                		 falg = i;
	                		 $(nTd).append('<input type="checkbox" class="checkboxes" value="'+ oData.tagsId +'" checked="checked"/>');
	                		 break;
		                 }
	                 }
	                 if(falg == -1){
		                $(nTd).append('<input type="checkbox" class="checkboxes" value="'+ oData.tagsId +'"/>');
	                 }
	                 $(nTd).append('</td>');
	             }
	        },
            { "sTitle": "标签id",'sWidth':'4%', "mData": "tagsId" },
            { "sTitle": "标签名",'sWidth':'4%', "mData": "tagsName" },
        ];
        return {
            aoColumns : aoColumns
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
            "sAjaxSource": "querySalesData",
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
		member_memberSalesList.loadTableData();
     });
	
	$("#searchTagForm").submit(function(e){
		e.preventDefault();
		tagTable.fnDestroy();
		member_memberSalesList.loadTagData();
     });

	$('#btnReset').click(function(){
		document.getElementById("mobilePhone").value="";	  	
    });
    var initToolBar = function(){
		Array.prototype.removeByValue = function(val) {
		  for(var i=0; i<this.length; i++) {
		    if(this[i] == val) {
		      this.splice(i, 1);
		      break;
		    }
		  }
		}
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
        	debugger
        	var id = $(e.relatedTarget).attr("id");
        	$("#saleId").val('').val(id);
        	// sendTagIdArr = ['21'];
        	
        	loadTagData();
        	initTagsToolBar();
        });
       //营销信息发送
        $("#sendMsgBut").on("click", function(){
        	if(sendTagIdArr.length == 0){
        		bootbox.alert("请选择发送标签");
        		return;
        	}
        	var memberTageIds = "";
        	for(var i = 0; i < sendTagIdArr.length ; i++){
        		if(sendTagIdArr.length == 1 || i == sendTagIdArr.length-1){
        			memberTageIds += sendTagIdArr[i];
        		}else {
        			memberTageIds += sendTagIdArr[i]+",";
        		}
        	}
        	var param = {saleId : $("#saleId").val(),memberTageIds:memberTageIds};
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
                    }else if (data.code==4){
                    	bootbox.alert("营销活动未开始");
                    }else if (data.code==5){
                    	bootbox.alert("营销活动已结束");
                    }else if (data.code==6){
                    	bootbox.alert("未匹配到目标用户");
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
    
    var initTagsToolBar = function (){
    	jQuery('#'+tagTableId).on('change', '.group-checkable', function(){
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
                 var tagid = $(this).val();
     	    	var falg = -1;
     	    	for(var i = 0 ;i < sendTagIdArr.length > 0 ; i++){
     	    		if(tagid == sendTagIdArr[i]){
     	    			if(!checked){
     	    				sendTagIdArr.splice(i,i+1);
     	    			}
     	    			falg = i;
     	    			break;
     	    		}
     	    	}
     	    	if(((sendTagIdArr.length == 0 && falg == -1) || falg == -1) && checked){
     	    		sendTagIdArr.push(tagid);
     	    	}
            });
            console.log(sendTagIdArr);
            jQuery.uniform.update(set);
        });
    jQuery('#'+tagTableId).on('change', 'tbody tr .checkboxes', function(){
	    	console.log($(this).parents('tr').children().children(".checkboxes").val());
	    	var tagid = $(this).parents('tr').children().children(".checkboxes").val();
	    	var falg = -1;
	    	for(var i = 0 ;i < sendTagIdArr.length > 0 ; i++){
	    		if(tagid == sendTagIdArr[i]){
	    			sendTagIdArr.splice(i,1);
	    			falg = i;
	    			break;
	    		}
	    	}
	    	if((sendTagIdArr.length == 0 && falg == -1) || falg == -1){
	    		sendTagIdArr.push(tagid);
	    	}
	    	console.log(sendTagIdArr);
	    	$(this).parents('tr').toggleClass("active");
	    });
    }
    return {
        init: function () {
        	// loadTagData();
            loadTableData();
            initToolBar();
        },
       // loadTagData:loadTagData,
        loadTableData: loadTableData
    };
    
    function loadTagData(){
    	var headerInfo = initTagTableHeaderInfo();
    	var search = $("#searchTagForm").serializeArray();
    	$('#tagTabel').html('<table id="memberTageTable" class="table table-striped table-bordered table-hover"></table>');
    	tagTable = $('#memberTageTable').DataTable({
        	"fnServerParams": function (aoData) {
        		$.each(search, function(index, value){
        			aoData.push({"name": value.name, "value": value.value});
                });
        	},
            "sAjaxSource": "queryLabelPoinListByPage",
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
    }
}();

