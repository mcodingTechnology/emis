//员工通讯录
var member_memberJoinRecordList = function () {
	var tableId = "memberTable";
	var oTable = null;
	
	//初始化加载表格数据的表头定义
    var initTableHeaderInfo = function(){
        var aoColumns = [
			{ "sTitle": "加盟商ID", "sWidth" : "5%", "mData": "memberId"},
            { "sTitle": "加盟商名称", "sWidth" : "5%", "mData": "fullName"},
            { "sTitle": "下线数量", "sWidth" : "5%", "mData": "childrenMember"},
            { "sTitle": "下级数量", "sWidth" : "5%", "mData": "childrenDealer"},
            { "sTitle": "渠道","sWidth" : "5%", "sDefaultContent" : "",
                "fnCreatedCell": function(nTd, sData, oData, iRow, iCol){
                    switch(oData.channelsId){
                        case 4 :
                            $(nTd).append("健身房渠道");
                            break;
                        case 5 :
                            $(nTd).append("展会渠道");
                            break;
                        default :
                            $(nTd).append("默认渠道");
                    }
                }
            },
            { "sTitle": "级别","sWidth" : "5%", "sDefaultContent" : "",
                "fnCreatedCell": function(nTd, sData, oData, iRow, iCol){
                    switch(oData.levelId){
                        case 1 :
                            $(nTd).append("三级");
                            break;
                        case 3 :
                            $(nTd).append("二级");
                            break;
                        case 4 :
                            $(nTd).append("四级");
                            break;
                        case 5 :
                            $(nTd).append("顶级");
                            break;
                        default :
                            $(nTd).append("无");
                    }
                }
            },
/*			{ "sTitle": "身份标识", "sWidth" : "5%", "mData": ""},*/
            { "sTitle": "所属上级","sWidth" : "5%", "sDefaultContent" : "",
                "fnCreatedCell": function(nTd, sData, oData, iRow, iCol){
                    if(oData.parentName){
                        $(nTd).append(oData.parentName);
                    }
                }
            },
            { "sTitle": "品牌","sWidth" : "5%", "sDefaultContent" : "",
                "fnCreatedCell": function(nTd, sData, oData, iRow, iCol){
                    switch(oData.brandCode){
                        case "JLD" :
                            $(nTd).append("BIG生活");
                            break;
                        case "MRMJ" :
                            $(nTd).append("极智构");
                            break;
                        default :
                            $(nTd).append("未知");
                    }
                }
            },
            {
                "sTitle": "创建时间",
                "sWidth" : "5%", "sDefaultContent" : "",
                "fnCreatedCell" : function(nTd,sData, oData, iRow, iCol) {
                    $(nTd).append((oData.createTime==null ? "" :changeDateFormat(oData.createTime)));
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
            		/*if(App.checkUserOperRight("memberJoinRecordList", "confirmMemberJoin")){
            			$(nTd).append("<span class='btn default btn-xs black' data-toggle='modal' " +
            					"href='#confirmWin' enable='ok' " + "id='"+ oData.id+"' >" +
                        "<i class='fa fa-check'></i> 通过 </span>");
            		}*/

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
        var search = $("#searchForm").serializeArray();
        oTable = $('#'+tableId).DataTable({
            "fnServerParams": function (aoData) {
                $.each(search, function(index, value){
                    aoData.push({"name": value.name, "value": value.value})
                });
            },
            "sAjaxSource": "queryDealerListData",
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
        member_memberJoinRecordList.loadTableData();
     });
    
    $('#btnReset').click(function(){
		document.getElementById("fullName").value="";
		document.getElementById("channelsId").value="";
    });


    var initToolBar = function(){
    	//触发相关赋值
       /* $("#confirmWin").on("show.bs.modal", function(e){
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
        });*/
    };

    return {
        init: function () {
            loadTableData();
            initToolBar();
        },
        loadTableData: loadTableData
    };
}();

