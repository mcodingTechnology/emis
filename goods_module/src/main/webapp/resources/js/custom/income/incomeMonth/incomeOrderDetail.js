/**
 * 
 */
(function(){
	$.fn.extend({
        "incomeOrderDetail": function (_options) {
        	var defaultOpts = {
            	elm : $(this)
            }
            options = $.extend({}, defaultOpts, _options);
        	
        	init(options);
    		return {
			    show: showModal,
			    hide: function(){ $('#keywordWin').modal('hide'); }
		    };
        }
    });
	
	var init = function(options){
    	var html = 
    	'<div class="modal fade" tabindex="-1" aria-hidden="true" id="keywordWin">' +
    	'	<div class="modal-dialog">'+
		'       <div class="modal-content">'+
		'           <div class="modal-header">'+
		'               <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>' +
		'               <h4 class="modal-title">订单佣金详情</h4>' +
	    '           </div>' +
		'	        <div class="modal-body"><table id="keywordtable" class="table table-striped table-bordered table-hover"></table></div>'+
		'       </div>'+
	    '    </div>'+
        '</div>';
    	$(options.elm).replaceWith(html);
    }
	
	var oTable = null;
	var orderId = null;
	
	var showModal = function(_orderId){
		debugger;
		orderId = _orderId;
		if(oTable == null){
			loadTableData();
		}else{
			oTable.fnReloadAjax();
		}
		$('#keywordWin').modal('show');
	}
	
	var aoColumns = [
		{ "sTitle": "产品名称", "mData": "orderProduct.productname"},
		{ "sTitle": "数量", "mData": "orderProduct.nums"},
		{ "sTitle": "1级佣金", "mData": "", "sDefaultContent":"","sVisible":false, 
			"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
				if(oData.incomeProduct && oData.incomeProduct.level1){
					$(nTd).append(oData.incomeProduct.level1/100);	
				}else{
					$(nTd).append(0);
				}
        }},
        { "sTitle": "2级佣金", "mData": "", "sDefaultContent":"","sVisible":false, 
        	"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
        		if(oData.incomeProduct && oData.incomeProduct.level2){
        			$(nTd).append(oData.incomeProduct.level2/100);	
        		}else{
        			$(nTd).append(0);
        		}
        }},
        { "sTitle": "3级佣金", "mData": "", "sDefaultContent":"","sVisible":false, 
        	"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
        		if(oData.incomeProduct && oData.incomeProduct.level3){
        			$(nTd).append(oData.incomeProduct.level3/100);	
        		}else{
        			$(nTd).append(0);
        		}
        }},
        { "sTitle": "4级佣金", "mData": "", "sDefaultContent":"","sVisible":false, 
        	"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
        		if(oData.incomeProduct && oData.incomeProduct.level4){
        			$(nTd).append(oData.incomeProduct.level4/100);	
        		}else{
        			$(nTd).append(0);
        		}
        }},
//		{ "sTitle": "结算佣金(元)", "mData": "", "sDefaultContent":"","sVisible":false, 
//			"fnCreatedCell":function(nTd,sData, oData, iRow, iCol){
//				$(nTd).append(oData.income/100);
//        }},
    ];
	
	var loadTableData = function(){
        oTable = $('#keywordtable').DataTable({
            "sAjaxSource": "incomeOrderProduct/service/findDetail",
            "sAjaxDataProp": "data",
            "bFilter" : false,
            "bInfo": false,
            "bJQueryUI": true,
            "bLengthChange": true,
            "bPaginate": false,
            "bProcessing": true,
            "bSort": false,
            "bSortClasses": true,
            "bStateSave": false,
            "bAutoWidth":true,
            "bSortCellsTop": true,
            "iTabIndex": 1,
            "sServerMethod": "POST",
            "bServerSide": true,
            "aoColumns": aoColumns,
            "aLengthMenu": [
                [10, 20, 30, 40, 50],
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
            },
            "fnServerParams": setQueryParams,
        });
    };
    
    var setQueryParams = function(aoData){
    	var params = [];
    	params.push({"name": "orderId", "value": orderId});

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
	
})();