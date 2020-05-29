var mallgame_addGamblingLabel = function() {
	var addressCount=3;
	var tagTableId = "memberTageTable";
	//初始化加载标签表格数据的表头定义
    var initTagTableHeaderInfo = function(){
        var aoColumns = [
        	{
	             "sTitle":'<th>单选1</th>',
	             "bSortable": false,
	             "bSearchable": false,
	             'sWidth':'2%',
	             "mDataProp" : "",
	             "sDefaultContent" : "",
	             "sVisible" : false,
	             "fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
	                 $(nTd).append('<td class="table-checkbox">');
		                $(nTd).append('<input type="checkbox" name="test1" class="checkboxes" value="'+ oData.memberId +'" />');
	                 $(nTd).append('</td>');
	             }
	        },
            { "sTitle": "标签id",'sWidth':'4%', "mData": "memberId" },
            { "sTitle": "标签名",'sWidth':'4%', "mData": "fullName" },
        ];
        return {
            aoColumns : aoColumns
        };
    };
    

    
	var initSelect = function(){
   	
		/*$.ajax({
			type : "GET",
			url : "queryProvinceRegion",
			dataType : "json",
			success : function(data) {
				var result="";
				result += "<option value=''>请选择省份</option>";
				$.each(data, function(index, value){
					result += "<option value=\"" + value.id + "\">" + value.name + "</option>";
                });
				for(var i=1;i<=addressCount;i++){
					$("#provinceId"+i).empty().append(result).change();					
				}
			}
		});*/
		
		/* //选择省份事件
		for(var i=1;i<=addressCount;i++){
			$("#provinceId"+i).on("change", function(){
	            var regionId = $(this).val();
	            var provinceSelectOrder=$(this).attr("id").replace(/[^0-9]/ig, "");
	            if(regionId==''){
	                return;
	            }
	            $.ajax({
	                type : "post",
	                dataType : "json",
	                url : "queryRegionByParentId",
	                data : {parentRegionId : regionId},
	                success : function(data){
	                    var result = "";
	                    result += "<option value=''>请选择城市</option>";
	                    $.each(data, function(index, value){
	                        var editRegionCityId = $("#editRegionCityId").val();
	                            result += "<option value=\"" + value.id + "\">" + value.name + "</option>";
	                    });
	                    
	                    $("#cityId"+provinceSelectOrder).empty().append(result).change();
	                }
	            })
	        });
		};*/
		
		/*//选择城市事件
		for(var i=1;i<=addressCount;i++){
			$("#cityId"+i).on("change", function(){
				var regionId = $(this).val();
				var citySelectOrder=$(this).attr("id").replace(/[^0-9]/ig, "");
				
				if(regionId==''){
					return;
				}
				$.ajax({
					type : "post",
					dataType : "json",
					url : "queryRegionByParentId",
					data : {parentRegionId : regionId},
					success : function(data){
						var result = "";
						result += "<option value=''>请选择区域</option>";
						$.each(data, function(index, value){
							var editRegionAreaId = $("#editRegionAreaId").val();
								result += "<option value=\"" + value.name + "\">" + value.name + "</option>";
						});
						$("#countyId"+citySelectOrder).empty().append(result).change();
					}
				})
			});			
		};*/
		
	};
	
	//设置单选
	var initMemberToolBar = function (){
		jQuery('#'+tagTableId).on('change', 'tbody tr .checkboxes', function(){
	    	//checkbox 设置单选
		    $(':checkbox[type="checkbox"]').each(function(){
		        $(this).click(function(){
		            if($(this).attr('checked')){
		                $(':checkbox[type="checkbox"]').removeAttr('checked');
		                $(this).attr('checked','checked');
		            }
		        });
		    });
		    
	    });
	};

	// 初始化页面相关按钮事件
	var initEvent = function() {
		$("#singleAdd").on("click", function() {
			debugger
			var submitForm = $("#submitForm"); //获取Form表单
			
			var dealerName = $("#dealerName").val(); //渠道商名称
			var dealerType = $("#dealerType").val(); //业务类型
			var memberId = $("#memberId").val(); //业务类型
			
			 if(memberId==""){
			   bootbox.alert("请输入会员名称");
			   return;
			 }
		    if(dealerName==""){
		    	bootbox.alert("请输入渠道商");
		    	return;
		    }
		    if(dealerType==""){
		    	bootbox.alert("请选择业务类型");
		    	return;
		    }
			
			/*var submitForm1 = $("#submitForm1");
			var submitForm2 = $("#submitForm2");
			var submitForm3 = $("#submitForm3");*/
			if (!submitForm.valid()) {
				return;
			}
			/*if (!submitForm1.valid()){
				return;
			}*/
			var requestURL = "addchannel";
			var tips = "增加失败!";
			/*var company = serializeObject(submitForm.serializeArray());
			var addr1 = serializeObject(submitForm1.serializeArray());
			var addr2 = serializeObject(submitForm2.serializeArray());
			var addr3 = serializeObject(submitForm3.serializeArray());
			
			var obj = new Object();
			obj.company = company;
			
			var addresses=new Array(3)
			addresses[0]=addr1;
			addresses[1]=addr2;
			addresses[2]=addr3;
			obj.addresses=addresses;*/
			var param = serializeObject(submitForm.serializeArray());
			$.ajax({
				type : "POST",
				url : requestURL,
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : JSON.stringify(param),
				success : function(data) {
					if (data.code == 0) {
						$("#backListPage").click();
					} else {
						bootbox.alert(tips);
					}
				}
			});
		});

		var options = {
			errorElement : 'span',
			errorClass : 'help-block',
			focusInvalid : true,
			ignore : "",
			rules : {
				deliveryArea : {
					min:0.0001,
					max:9999999,
					required : true
				},
				warehouseNumber : {
					required : true
				},
				warehouseAddress : {
					required : true
				},
				warehouseName : {
					required : true
				}
			},
			highlight : function(element) {
				$(element).closest('.form-group').addClass('has-error');
			},
			unhighlight : function(element) {
				$(element).closest('.form-group').removeClass('has-error');
			},
			success : function(label) {
				label.closest('.form-group').removeClass('has-error');
			}
		};
		
		// 验证表单
		$("#submitForm").validate(options);
		// $("#submitForm1").validate(options);
		
		
		//单击 input test 
	   $("#confirm").click (function() {
			 $('#confirmWin3').modal("show");
			 loadTagData();
			debugger;
			 initMemberToolBar();
		});
	  
	   $("#sendMsgBut").click (function() {
		   debugger
		  var obj = document.getElementsByName("test1");
		   var check_val = [];
		    for(var k in obj){
		        if(obj[k].checked)
		            check_val.push(obj[k].value);
		    }
		    if(check_val==""){
		    	alert("请选择会员");
		    	return;
		    }else{
		    	$("#memberId").val(check_val);
		    	$("#memberName").val(check_val);
		    }
		    $("#sb").click();
		   
		   
	   });
	};
	

	return {
		init : function() {
			initEvent();
			initSelect();
		},
	};
	
	if (jQuery().datetimepicker) {
		$(".datetime_picker").datetimepicker({
			format : 'yyyy-mm-dd hh:ii:ss',
			weekStart : 1,
			todayBtn : 'linked',
			rtl : App.isRTL(),
			language : "zh-CN",
			autoclose : true
		});
	}
	
	
	 /*$("#dealerName").on("show.bs.modal", function(e){
		 
		 alert("请选择发送标签");
     	var id = $(e.relatedTarget).attr("id");
     	$("#saleId").val('').val(id);
     	
     	loadTagData();
     	initTagsToolBar();
     });*/
	
	   
	    
	    $("#searchTagForm").submit(function(e){
			e.preventDefault();
			tagTable.fnDestroy();
			mallgame_addGamblingLabel.loadTagData();
	     });
	    
	    
	    
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
	            "sAjaxSource": "queryMemberListByPage",
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
