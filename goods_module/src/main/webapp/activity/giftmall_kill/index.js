/**
 * Created by ISSUSER on 2015/10/14 0014.
 */
//秒杀列表
var acitivityList = [];
var newAcitivityList = [];
var _flag = true;
//设置用户信息
/*function setUserInfo(){
    $("#kill_userimg").attr("src","../game_public/pic/demo_face/face_002.jpg");
    var url="./user.json";
    var data={};
    $.ajax({
        type: "get",
        url: url,
       /!* data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        dataType: "json",*!/
        success:function(data){
            $("#kill_userimg").attr("src",data.userImg);
            $("#kill_username").text(data.username);
            $("#kill_userpoint").text(data.userpoint);
            $('[data-bui_img]').buijs_img();
        }
    });
}*/

//设置秒杀列表
function getActivityList(){
    $.ajax({
        type: "get",
        url: _url+"front/todaySeckillList.html",
        /*data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        dataType: "json",*/
        success:function(data){
            $("#kill_userimg").attr("src",data.data.headimgUrl);
            $("#kill_username").text(data.data.nickName);
            $("#kill_userpoint").text(data.data.pointSum+"分");
            $('[data-bui_img]').buijs_img();
            acitivityList = data.data.seckillList;
            sortList();
            setActivityList(false);
            setTime();
        }
    });

}
/**
 * time的格式为2015-10-13 16:00:00
 * time的格式为毫秒数
 * */
function formatDate(time){
    var date = new Date(time);
    var times  = date.toTimeString().substr(0,9);
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    return year+"年"+month+"月"+day+"日  "+times;
}
//获取毫秒数  time的格式为2015-10-13 16:00:00
function getTime(time){

   /* var year,month,day,hour,minute,second;
    year = time.substr(0,4);
    month = parseInt(time.substr(5,2))-1;
    day = time.substr(8,2);
    hour = time.substr(11,2);
    minute = time.substr(14,2);
    second = time.substr(17,2);
    return new Date(year,month,day,hour,minute,second);*/
    return new Date(time);
}

function sortList(){
    //每次清空一下
    newAcitivityList = [];
    var now = new Date().getTime();
    $.map(acitivityList,function(item,index){
        var b = new Date();
        item.startTime = getTime(item.starttime);
        item.goodsTime = formatDate(item.starttime);
        newAcitivityList.push(item);
    });
}
function setActivityList(isFlag){
    var content = "";
    var temp = {};
    for(var i=0;i<newAcitivityList.length;i++){
        for(var j=i+1;j<newAcitivityList.length;j++){
            var flag = false;
            if(newAcitivityList[i].status =="end" && newAcitivityList[j].status!="end"){
                flag = true;
            } else if((newAcitivityList[i].status == newAcitivityList[j].status) && (newAcitivityList[i].starttime>newAcitivityList[j].starttime)){
                flag = true;
            }
            if(flag){
                temp = newAcitivityList[i];
                newAcitivityList[i]=newAcitivityList[j];
                newAcitivityList[j] =temp;
            }
        }
    }
    $.map(newAcitivityList,function(item,index) {
        item.tipMsg = "即将开始";
        item.btnStyle = "off";
        var now=new Date().getTime();
        var startTime = item.startTime.getTime();
        item.btnMsg = "马上秒杀";
        //是否显示箭头
        item.arrowStyle="inline-block";
        if(item.status == "end"){ //判断活动结束没有
            item.btnMsg = "看看谁秒到了";
            item.tipMsg ="已结束";
            item.btnStyle = "on";
        } else if(now<item.startTime.getTime()){
            item.btnMsg = "倒计时 ";
            var time = (item.startTime.getTime()-now)/1000;
            var hour = Math.floor(time/3600);
            var minute = Math.floor((time-hour*3600)/60);
            var second = Math.floor((time-hour*3600-minute*60));
            if(hour<10) hour ="0"+hour;
            if(minute<10) minute ="0"+minute;
            if(second<10) second ="0"+second;
            item.btnMsg +=hour+":"+minute+":"+second;
            item.arrowStyle="none";
        } else if(now>=item.startTime.getTime()){
            item.tipMsg = "进行中";
            item.btnStyle = "on";
        }
        if(isFlag){
            $("#"+item.productid).text(item.btnMsg);
            if(!$("#"+item.productid).hasClass("on") && item.btnStyle == "on"){
                $("#"+item.productid).removeClass("off").addClass("on");
            }
        } else {
            content += '<li>'
                + '  <p class="bui_vm bui_ts_14"><span>' + item.goodsTime + '</span> <span class="bui_tc_o48">' + item.tipMsg + '</span></p>'
                + '  <div class="bui_avg_2">'
                + '    <div style="box-shadow: inset -1px 0px 0px rgba(255,255,255,0.48);">'
                + '      <div class="bui_plr_24 bui_ta_r">'
                + '        <i class="bui_ts_48 bui_tc_yellow" style="font-weight: bold;">' + item.needpoint + '</i>'
                + '        <p class="bui_ts_12 bui_tc_o64">积分秒杀</p>'
                + '      </div>'
                + '    </div>'
                + '    <div>'
                + '     <div class="bui_plr_24">'
                + '      <div data-bui_img=\'\' style="width: 96px; height: 96px;">'
                + '         <img src="' + item.productconvert + '" />'
                + '        </div>'
                + '      </div>'
                + '    </div>'
                + '   </div>'
                + '   <p class="bui_vm bui_ts_14 bui_mt_12">' + item.productname + '</p>'
                + '   <p class="bui_ts_12 bui_tc_yellow">（共' + item.ordernum + '份）</p>'
                + '   <a href="javascript:;" class="bui_btn_48 bui_radius bui_mt_12 kill_btn ' + item.btnStyle + ' skillBtn" isOver="' + item.status + '" id="' + item.productid + '"  skillId="' + item.id + '" goodsId="' + item.productid + '"  fee="' + item.needpoint + '">' + item.btnMsg + '<i style="display:' + item.arrowStyle + '" class="fa fa-angle-right fa-fw bui_fas_24"></i></a>'
                + '  </li>';
        }
    });
    if(!isFlag) {
        $("#killActivityList").html(content);
        $('[data-bui_img]').buijs_img();
    }
    setSkillBtn();
}
function setSkillBtn(){
    if(!_flag) return;
    _flag = false;
    //绑定秒杀点击事件
    $(".skillBtn").click(function(e){
        e.preventDefault();
        var _self = $(this);
        //活动没开始
        if(_self.hasClass("off")) return;
        var goodsId = _self.attr("goodsId");
        var skillId = _self.attr("skillId");
        var fee = _self.attr("fee");
        //活动结束跳到中奖名单
        if(_self.attr("isOver")=="end"){
            window.location.href = "./winnerList.html?goodsId=" + goodsId;
        } else {
            //调用是否能被秒杀
            $.ajax({
                url:_url + "front/canGet.html?seckillId="+skillId,
                type:"get",
                success:function(rs){
                    if(rs.code==0 || rs.code==5){
                        window.location.href ='../../GiftMall/order_add.html?productId=' + goodsId + '&productNum=1&fee='+fee+'&type=kill&resultid='+rs.data.id;
                    } else{
                        //失败的时候再调用一下接口
                        buijs_alert({
                            position:"center",
                            content:rs.msg
                        });
                        getActivityList();
                    }
                },
                error:function(err){
                    buijs_alert({
                        position:"center",
                        content:err
                    });
                }
            });
        }
    });
}
//设置倒计时
function setTime(){
    window.setInterval(function(){
       setActivityList(true);
    },500);
}

$(document).ready(function() {
    //setUserInfo();
    getActivityList();
});