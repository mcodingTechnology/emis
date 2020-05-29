/**
 * Created by ISSUSER on 2015/10/19 0019.
 */
//3分制的指标
var threeStr="营养:维生素A,营养:维生素D,营养:维生素E,营养:锌(Zn),营养:硒(Se),营养:铁(Fe)";
var fourStr="营养:维生素B,营养:维生素C,营养:钙(Ca)";
var _ids = buijs_geturl("ids");

function getdata(){
    $.ajax({
        type: "get",
        url: _url+"healthCriteria/queryHealthCriteriaByIds2?ids="+_ids,
        /*data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        dataType: "json",*/
        success:function(data){
            var content ="";
            $.map(data.data,function(item,index){
                var score = item.score;
                var a=10, b=8, c=6,d=3;//定义4个等级做样式
                if(threeStr.indexOf(item.testitem)!=-1){
                    a=3;
                    b=2;
                    c=1;
                    d=0.5;
                }else if(fourStr.indexOf(item.testitem)!=-1){
                    a=4;
                    b=3;
                    c=2;
                    d=0.5;
                }
                var className = "";

                if(score <=d){
                    className="report_bg_red";
                } else if(score<=c){
                    className="report_bg_orange";
                } else if(score<=b){
                    className = "report_bg_blue" ;
                } else if(score<=a){
                    className = "report_bg_green"
                }
                var msg = !item.analysis?"":"原因分析：";
                content +='<li class='+className+'>'
                        +'   <h3>'+item.testitem+'('+item.result+')</h3>'
                        +'   <p>'+msg+item.analysis+'</p>'
                        +'</li>';
            });
            $(".analyse_content").html(content);
            $('[data-bui_img]').buijs_img();
        }
    });
}
$(document).ready(function(){
    getdata();
});