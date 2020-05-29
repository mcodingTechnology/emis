/**
 * Created by ISSUSER on 2015/10/16 0016.
 */
var _phone = buijs_geturl("phone");

function getdata(){
    var data={
        phone:_phone
    };
    $.ajax({
        type: "get",
        url: _url+"healthCriteriaMember/queryResultByPhone?phone="+_phone,
       /* data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        dataType: "json",*/
        success:function(result){
            if(result.data.length==0){
                $("#report_no").show();
            }/*else if(data.state =="1"){
                $("#report_fail").show();
            } else if(data.state =="3"){
                location.href = "index.html";
            }*/else  {
                var data = result.data[0];
                window.localStorage.setItem("hair_report_download",data.detailedreport);
                $("#report_success").show();
                var score = data.score;
                var msg = "";
                $("#report_content_analyse").attr("href","analyse.html?ids="+data.healthcriteriaid);
                localStorage.setItem("hairSuggestMsg",data.additionaladvice);
                $("#report_content_suggest").attr("href","suggest.html?ids="+data.productid);
                $("#report_head_score").text(score);
                var desc = "";
                if (score < 60) {
                    desc="病怏怏";
                    msg = "天天困成狗，头晕又手抖，想吃没胃口，疲劳赶不走？身体都在投诉你了！赶紧调整生活方式，找回健康的自己吧！";
                    $(".report_head").attr("class", "report_head report_bg_red");
                    $("#report_head_img").attr("src", "images/red.png");
                } else if (score < 75) {
                    desc="虚飘飘";
                    msg = "你用着最新潮的手机，可你对健康的认识还停留在上个世纪。快看看潜藏在身体的问题，update自己的健康吧！";
                    $(".report_head").attr("class", "report_head report_bg_orange");
                    $("#report_head_img").attr("src", "images/orange.png");
                } else if (score < 85) {
                    desc="棒棒哒";
                    msg = "看来你对健康还是有点概念，不过有些问题可能还没发现。为健康，问题不论大小，越早解决越好！";
                    $(".report_head").attr("class", "report_head report_bg_blue");
                    $("#report_head_img").attr("src", "images/blue.png");
                } else if (score <= 100) {
                    desc="棒棒的";
                    msg = "三餐饮食有规矩，作息时间有规律，锻炼身体经常去，健康生活请继续！改善一些小问题，你将变得更好哦！";
                    $(".report_head").attr("class", "report_head report_bg_green");
                    $("#report_head_img").attr("src", "images/green.png");
                }
                $("#report_head_desc").text(data.determine);
                $(".report_head_msg").text(data.description);
            }
            $('[data-bui_img]').buijs_img();
        }
    });
}


$(document).ready(function(){
    var getReport = buijs_geturl("getReport");
    localStorage.removeItem("hairSuggestMsg");
    if(getReport=="1"){
        $("#report_success,#report_no,#report_fail").hide();
        $("#get_report").show();
        $("#report_download").html(localStorage.getItem("hair_report_download"));
    } else {
        getdata();
    }
    $("#report_content_ask").click(function(e){
        e.preventDefault();
        buijs_modal({
            title:"要找营养师？",
            positions: 'center',
            content: "<p class='bui_ta_c'>只要在我们微信号直接留言，并备注“咨询营养师”，我们的营养师会尽快回复您哦！ </p>"
        });
    });
    $("#report_success_head").height($(window).height()*0.5);
});