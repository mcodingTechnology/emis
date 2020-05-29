
var DataTableList = function () {
	var tableId = "dataTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{
				"sTitle": "详情",
				"mDataProp" : "",
				'sWidth':'2%',
				"sDefaultContent" : "",
				"sVisible" : false,
				"fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
					$(nTd).append("<span class='row-details row-details-close'></span>");
					$(nTd).append("<input type='hidden' name='orderId' value="+oData.orderid+">");
				}
			},
			{ "sTitle": "ID", "mData": "id"},
			{ "sTitle": "订单编号", "mData": "ext1"},
			{ "sTitle": "购买人", "mData": "", "sDefaultContent":"","sVisible":false,
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					var currentMemberId = $('#memberId').val();
					if (parseInt(currentMemberId) == parseInt(oData.ordermemberid)) {
						$(nTd).append(oData.ordermembername);
					} else {
						$(nTd).append(oData.ordermembername+" | "+"<a class='ajaxify' href='memberJoinRecord/saleDataDetailView.html?id="+oData.id+"&name="+oData.ordermembername+"&memberId="+oData.ordermemberid+"'>查看数据</a>");
					}
				}
			},
			{ "sTitle": "订单金额", "mData": "", "sDefaultContent":"","sVisible":false,
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(oData.orderfee/100);
				}
			},
			{ "sTitle": "佣金金额", "mData": "", "sDefaultContent":"","sVisible":false,
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(oData.commission/100);
            }},
			{ "sTitle": "佣金比例", "mData": "", "sDefaultContent":"","sVisible":false,
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					var scale = oData.commission / oData.orderfee;
					if (scale > 0.08 && scale < 0.15) {
						$(nTd).append("10%");
					} else if (scale > 0.2 && scale < 0.28) {
						$(nTd).append("25%");
					} else if (scale > 0.3 && scale < 0.38) {
						$(nTd).append("35%");
					}

				}},
			{ "sTitle": "下单时间", "mData": "", "sDefaultContent":"","sVisible":false,
				"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
					$(nTd).append(moment(oData.ordertime).format('YYYY-MM-DD HH:mm'));
				}}
        ];
        
        //渲染特殊的列(操作列)
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
            "sAjaxSource": "incomeOrder/service/findIncomeOrderByPage",
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

	//注册点击查看详情事件
	$('#'+tableId).on('click', ' tbody td .row-details', function () {var nTr = $(this).parents('tr')[0];

		//获取隐藏域的userId值
		var orderId = $(this).parents('tr').find("input:hidden").val();
		if (oTable.fnIsOpen(nTr)){
			/* 关闭 */
			$(this).addClass("row-details-close").removeClass("row-details-open");
			oTable.fnClose(nTr);
		}else{
			/* 打开 */
			$(this).addClass("row-details-open").removeClass("row-details-close");
			DataTableList.fnFormatDetails(oTable, nTr, orderId);
		}
	});

	//加载订单详情函数
	var fnFormatDetails = function(oTable, nTr, orderId) {
		$.ajax({
			type : "post",
			dataType : "json",
			data : {orderId : orderId},
			url : "merriplusApi/getOrderInfoByOrderId",
			success : function(data){
				var sOut = '<table>';
				if(data.status =="00"){
					var orderInfo = data.data.orderInfo;
					var addressInfo= data.data.addressInfo;
					var orderProductsInfo= data.data.orderProductsInfo;
					var parentMemberInfo= data.data.parentMember;
					var memberInfo= data.data.member;
					var weixinuserInfo= data.data.weixinUser;

					if(addressInfo==null){}else{
						sOut +='<tr><td style="font-weight:bold;">顾客信息:</td>' +
							'<td style="padding-right:-30px;">'+(memberInfo.fullName==null ? "" :  memberInfo.fullName)+'，&nbsp;'+(weixinuserInfo.nickname==null ? "" :  weixinuserInfo.nickname)
							+'&nbsp;，'+memberInfo.openid
							+'<b style="margin-left: 20px;">上级信息：</b>'+(parentMemberInfo==null ? "" :(parentMemberInfo.fullName==null ? "" :  parentMemberInfo.fullName))+'</td></tr>';
					}
					if(addressInfo==null){}else{
						sOut +='<tr>'
							+'<td style="font-weight:bold;">收货信息：</td><td style="padding-right:-30px;">'+(addressInfo.name==null ? "" :  addressInfo.name)+'，&nbsp;'+addressInfo.phone
							+'，&nbsp;'+addressInfo.regson+'&nbsp;'+addressInfo.address+'</td></tr>';
					}
					sOut += '<tr><td style="font-weight:bold;">商品信息:</td>';

					$.each(orderProductsInfo, function(index, item){
						console.log(item.plusmoney);
						sOut +='<td style="float:left;padding-right:20px;"><span>'+item.productname+'，'+item.nums+'份'+(item.plusmoney ==null ? "" :  '，￥'+item.plusmoney/100+'元')+(item.plusmoney ==null && (orderInfo.malltype == 'wMall' || orderInfo.malltype == 'gMall') ? '，￥'+item.price/100 :  "+"+item.price+'积分') + '；' +'</span></td>';
					});
					sOut += '<tr>'+
						'<td style="font-weight:bold;">订单信息:</td>' +
						'<td>'+(orderInfo.deliveryname==null ? "" :  '配送方式：')
						+(orderInfo.deliveryname==null ? "" :  orderInfo.deliveryname+'&nbsp;&nbsp;&nbsp;&nbsp;')
						+(orderInfo.deliveryorderno==null ? "" :  '物流单号：')
						+(orderInfo.deliveryorderno==null ? "" :  orderInfo.deliveryorderno+'&nbsp;&nbsp;&nbsp;&nbsp;')
						+(orderInfo.sendtime==null ? "" :  '发货时间：')
						+(orderInfo.sendtime==null ? "" :  changeDateFormat(orderInfo.sendtime)+'&nbsp;&nbsp;&nbsp;&nbsp;')
						+(orderInfo.receivetime==null ? "" :  '确认收货时间：')
						+(orderInfo.receivetime==null ? "" :  changeDateFormat(orderInfo.receivetime)+'&nbsp;&nbsp;&nbsp;&nbsp;')
						+(orderInfo.returntime==null ? "" :  '申请退换货时间：')
						+(orderInfo.returntime==null ? "" :  changeDateFormat(orderInfo.returntime)+'&nbsp;&nbsp;&nbsp;&nbsp;')
						+(orderInfo.orderpayresource==null ? "" :  '订单来源：')
						+(orderInfo.orderpayresource==null ? "" :  orderInfo.orderpayresource)
						+'</td></tr>';

					sOut += '<tr>'+
						'<td style="font-weight:bold;">优惠信息:</td>' +
						'<td>优惠类型：'
						+(orderInfo.ordertype==null ? "" :  convertOrderType(orderInfo.ordertype))
						+'&nbsp;&nbsp;&nbsp;&nbsp;优惠券码：'
						+(orderInfo.cardcode==null ? "" :  orderInfo.cardcode)
						+'&nbsp;&nbsp;&nbsp;&nbsp;现金券类型：'
						+(orderInfo.cartypename==null ? "" :  orderInfo.cartypename)
						+'&nbsp;&nbsp;&nbsp;&nbsp;卡券减免金额：'
						+(orderInfo.feereduce==null ? "" :  "￥"+orderInfo.feereduce/100)
						+'&nbsp;&nbsp;&nbsp;&nbsp;营销规则优惠总金额：'
						+(orderInfo.preferentialprice==null ? "" :  "￥"+orderInfo.preferentialprice/100) +
						'</td></tr>';
				}
				sOut += '</table>';
				oTable.fnOpen( nTr, sOut, 'details');
			}
		});
	}

	var loadTopData = function () {
		var param = {memberId : $("#memberId").val()};
		$.ajax({
			type : "post",
			dataType : "json",
			url : "incomeOrder/service/queryFranchiseeData",
			data : param,
			success : function(data) {
				if(data.status=='00'){
					/*页面呈现数据*/
					$("#sumOrderFee").html('<font size="5" color="red">'+data.data.sumOrderFee/100+'</font>');
					$("#sumCommission").html('<font size="5" color="red">'+data.data.sumCommission/100+'</font>');
					$("#childrenDealer").html('<font size="5" color="red">'+data.data.childrenDealer+'</font>');
					$("#childrenMember").html('<font size="5" color="red">'+data.data.childrenMember+'</font>');
				}else{
					/*暂时全部为0*/
				}
			}
		});
	}

	$('#btnSearch').click(function(){
		oTable.fnDestroy();
		DataTableList.loadTableData();
	});

	$('#btnReset').click(function(){
		$("#searchForm")[0].reset();
	});
 
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
			/*统计数据加载*/
			loadTopData();
        },
		loadTableData: loadTableData,
		fnFormatDetails:fnFormatDetails,
        reload:function(){
        	oTable.fnReloadAjax();
        }
    };
}();

