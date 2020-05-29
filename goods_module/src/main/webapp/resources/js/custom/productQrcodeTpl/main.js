var DataTableList = function () {
	var tableId = "dataTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
            { "sTitle": "产品编码", "mData": "productCode"},
			{ "sTitle": "产品名称", "mData": "productName"},
			{
            	"sTitle": "是否上架",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if(oData.isSale==0){
            			$(nTd).append("<label class='label label-success'>是</label>");
            		}else{
            			$(nTd).append("<label class='label label-danger'>否</label>");
            		}
            		
            	}
            },{
            	"sTitle": "商品类型",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		$(nTd).append(convertProductType(oData.productType));
            	}
            },{
            	"sTitle": "是否参与会员日",
            	"mDataProp" : "",
            	"sDefaultContent" : "",
            	"sVisible" : false,
            	"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
            		if(oData.isOpenDsicountPoint==1){
            			$(nTd).append("<label class='label label-success'>是</label>");
            		}else{
            			$(nTd).append("<label class='label label-danger'>否</label>");
            		}
            		
            	}
            }
        ];
        
        aoColumns.push({
        	"sTitle": "操作",
        	"mDataProp" : "",
        	"sDefaultContent" : "",
        	"sVisible" : false, 
        	"fnCreatedCell": function(nTd,sData, oData, iRow, iCol){
        		

        		var addOrRemoveTplHtml = "<span style='margin: 0 5px 0 5px'><span class='btn default btn-xs black ajaxify' href='productQrcodeTemplate/toListView.html?productId="+oData.productId+"' ><i class='fa fa-trash-o'></i>添加或修改海报</span>";
        		$(nTd).append(addOrRemoveTplHtml);
//        		var addOrRemoveTplHtml = "<span class='btn default btn-xs black' ><i class='fa fa-trash-o'></i>添加或修改海报";
//        		var addOrRemoveTplObj = $('<span>', {
//        			style : "margin: 0 5px 0 5px",
//        			click: function(){ addOrRemoveTplHandler(oData.productId); }
//        		});
//        		addOrRemoveTplObj.append(addOrRemoveTplHtml);
//        		addOrRemoveTplObj.appendTo($(nTd));
        	}
        });
        
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
            "sAjaxSource": "queryProductData",
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
    
    var convertProductType = function(productType){
    	var html = '';
    	if(productType == "商品"){
    		html = "<label class='label label-success'>商品</label>";
    	}else if(productType == "赠品"){
    		html = "<label class='label label-primary'>赠品</label>";
    	}else{
    		html = "<label class='label label-info'>无</label>";
    	}
    	return html;
    };
    
    var addOrRemoveTplHandler = function(productId){
//    	$('#myModal_autocomplete').modal().show();
    	$.productQrcdeTpl({productId : productId});
//    	bootbox.confirm("确认删除吗?", function(result) {
//			
//		});
    };
    
    

    return {
        init: function () {
            loadTableData();
        }
    };
}();

