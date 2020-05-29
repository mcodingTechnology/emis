/**
 * Created by ISSUSER on 2015/10/19 0019.
 */
var _ids = buijs_geturl("ids");
var _length = 0;
var _flag = false;
function getdata(){
    $.ajax({
        type: "get",
        url: _url+"product/queryProductByIds?ids="+_ids,
        async:false,
        success:function(rs){
            if(localStorage.getItem("hairSuggestMsg")){
                $(".suggest_msg").append(localStorage.getItem("hairSuggestMsg"));
            }
            var length = rs.data.length ;
            _length=length;
            $("#suggest_img").html("");
            /*length<=3*/
            var k =2;
            if(length<=3){
                k = length -1;
            }
            for(var i=0;i<length;i++){
                $("#suggest_img").append("<a class='suggest_pic"+k+"' id='suggest_photo"+i+"'><img id='suggestImg"+i+"' /></a>");
                $("#suggestImg"+i).attr("src",rs.data[i].productCoverImg).css("opacity",1);
                //$("#suggest_photo"+i).css("backgroundImage","url('"+rs.data[i].productCoverImg+"')");
                $("#suggest_photo"+i).attr("href","http://v.merryplus.com/wMall/product_content.html?productId="+rs.data[i].productId);
            }
            _flag = true;
        }
    });
}
function layout(){
    for(var j=0;j<_length;j++){
        var pl = ($("#suggest_photo"+j).width() - $("#suggestImg"+j).width())/2;
        $("#suggestImg"+j).css("padding-left",pl+"px");
    }
}
$(document).ready(function(){
    getdata();
    var timer = window.setInterval(function(){
        if(_flag){
            layout();
            clearInterval(timer);
        }
    },50);

});