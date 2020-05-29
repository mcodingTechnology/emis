//微信sdk
document.write('<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>');

var BtCtrl= false;   //按钮时间限制开关   true 为禁止 ， false 为通过

//获得分享者Openid
var _shareOpenid = buijs_geturl("shareOpenid");
//创建加载页面
function fanjs_showloading() {
    $("#bui_loading").show();
};
function fanjs_closeloading() {
    $("#bui_loading").remove();
};
//加载拦截
var _windowload
function checkLoading() {
    if (_windowload == true) {
        vm();
        return setTimeout(function(){
           fanjs_closeloading();
           $("#year_content").attr('style', 'display:block');
        },3200)
        
    }
}
//绝对居中
function vm() {
	$(".year-section").map(function() {
		var _box = $(this);
		var _item = _box.children('.year-section-content');
		_item.css('top',  '0');
	});
};
//获取首页提示说明遮罩层
function year_homeInform() {
    $.ajax({
        type: 'get',
        url: _jsonUrl+ 'front/checkCurrentDayQAFinish.html',
        dataType: 'json',
        success: function(data){
            if (data.code == 0) {
                $("#homeInform").attr('style', 'display:block');
            }else if (data.code == 7) {
                fanjs_modal({
                    width: '296px',
                    positions: 'center',
                    content: '<p class="bui_ta_c">您今天已答过题，明天还能再玩一次哦！</p><p><a class="bui_btn_48 bui_bgc_red bui_block bui_radius" href="openenvelope.html">进去红包库</a></p>'
                })
            }
        }
    })
};
//打开分享提示
function year_shareTips() {
	$("#year_shareTips").show();
	$("#year_shareTips").click(function() {
		$(this).hide();
	});
};
//答对题目增加红包
function AddRedEnvelopeQ1TrueQ2(){
    if (BtCtrl) return;
    BtCtrl= true;
	$.ajax({
		type: "get",
		url: _jsonUrl + "/front/addCNYQAGameQAEnvelope.html",
		async: true,
		cache: false,
		data: {
			isAnswerCorrect:"true"
		},
		error: function(data) {
			jsonError();
            BtCtrl= false;
		},
		success: function(data) {

			if (data.code == 0) {
				$(".result").attr('style', 'display:none');
				$(".result ul li").attr('style', 'display:none');
				$("#question-pages").attr('style', 'display:block');
				$("#question2").attr('style', 'display:block');
			} else if (data.code == 7) {
				fanjs_modal({
                width: '296px',
                positions: 'center',
                content: '<p class="bui_ta_c">您今天的游戏已经完成！感谢您的参与...</p>'+
                '<p><a href="javascript:;"></a></p><p><a class="bui_btn_48 bui_bgc_red bui_block bui_radius" href="openenvelope.html">进去红包库</a></p>'
               })
			} else{
                fanjs_modal({
                width: '296px',
                positions: 'center',
                content: '<p class="bui_ta_c">'+data.msg+'</p>'
               })
            }
            BtCtrl= false;
		}
	});
}
function AddRedEnvelopeQ1FalseQ3(){
    if (BtCtrl) return;
    BtCtrl= true;
    $.ajax({
        type: "get",
        url: _jsonUrl + "/front/addCNYQAGameQAEnvelope.html",
        async: true,
        cache: false,
        data: {
            isAnswerCorrect:"false"
        },
        error: function(data) {
            jsonError();
            BtCtrl= false;
        },
        success: function(data) {

            if (data.code == 0) {
                $(".result").attr('style', 'display:none');
                $(".result ul li").attr('style', 'display:none');
                $("#question-pages").attr('style', 'display:block');
                $("#question3").attr('style', 'display:block');
            } else if (data.code == 7) {
                fanjs_modal({
                width: '296px',
                positions: 'center',
                content: '<p class="bui_ta_c">您今天的游戏已经完成！感谢您的参与...</p>'+
                '<p><a href="javascript:;"></a></p><p><a class="bui_btn_48 bui_bgc_red bui_block bui_radius" href="openenvelope.html">进去红包库</a></p>'
               })
            } else{
                fanjs_modal({
                width: '296px',
                positions: 'center',
                content: '<p class="bui_ta_c">'+data.msg+'</p>'
               })
            }
            BtCtrl= false;
        }
    });
}
function AddRedEnvelopeQ1FalseQ4(){
    if (BtCtrl) return;
    BtCtrl= true;
    $.ajax({
        type: "get",
        url: _jsonUrl + "/front/addCNYQAGameQAEnvelope.html",
        async: true,
        cache: false,
        data: {
            isAnswerCorrect:"false"
        },
        error: function(data) {
            jsonError();
            BtCtrl= false;
        },
        success: function(data) {

            if (data.code == 0) {
                $(".result").attr('style', 'display:none');
                $(".result ul li").attr('style', 'display:none');
                $("#question-pages").attr('style', 'display:block');
                $("#question4").attr('style', 'display:block');
            } else if (data.code == 7) {
                fanjs_modal({
                width: '296px',
                positions: 'center',
                content: '<p class="bui_ta_c">您今天的游戏已经完成！感谢您的参与...</p>'+
                '<p><a href="javascript:;"></a></p><p><a class="bui_btn_48 bui_bgc_red bui_block bui_radius" href="openenvelope.html">进去红包库</a></p>'
               })
            } else{
                fanjs_modal({
                width: '296px',
                positions: 'center',
                content: '<p class="bui_ta_c">'+data.msg+'</p>'
               })
            }
            BtCtrl= false;
        }
    });
}
//答对题目增加红包
function AddRedEnvelopeQ2TrueQ5(){
    if (BtCtrl) return;
    BtCtrl= true;
	$.ajax({
		type: "get",
		url: _jsonUrl + "/front/addCNYQAGameQAEnvelope.html",
		async: true,
		cache: false,
		data: {
			isAnswerCorrect:"true"
		},
		error: function(data) {
			jsonError();
            BtCtrl= false;
		},
		success: function(data) {
			//console.log(data.code);
			if (data.code == 0) {
				$(".result").attr('style', 'display:none');
				$(".result ul li").attr('style', 'display:none');
				$("#question-pages").attr('style', 'display:block');
				$("#question5").attr('style', 'display:block');
			}else if (data.code == 7 ) {
                fanjs_modal({
                width: '296px',
                positions: 'center',
                content: '<p class="bui_ta_c">您今天的游戏已经完成！感谢您的参与...</p>'+
                '<p><a href="javascript:;"></a></p><p><a class="bui_btn_48 bui_bgc_red bui_block bui_radius" href="openenvelope.html">进去红包库</a></p>'
               })
            }
            BtCtrl= false;			
		}
	});
}
function AddRedEnvelopeQ2FalseQ5(){
    if (BtCtrl) return;
    BtCtrl= true;
    $.ajax({
        type: "get",
        url: _jsonUrl + "/front/addCNYQAGameQAEnvelope.html",
        async: true,
        cache: false,
        data: {
            isAnswerCorrect:"false"
        },
        error: function(data) {
            jsonError();
            BtCtrl= false;
        },
        success: function(data) {
            //console.log(data.code);
            if (data.code == 0) {
                $(".result").attr('style', 'display:none');
                $(".result ul li").attr('style', 'display:none');
                $("#question-pages").attr('style', 'display:block');
                $("#question5").attr('style', 'display:block');
            }else if (data.code == 7 ) {
                fanjs_modal({
                width: '296px',
                positions: 'center',
                content: '<p class="bui_ta_c">您今天的游戏已经完成！感谢您的参与...</p>'+
                '<p><a href="javascript:;"></a></p><p><a class="bui_btn_48 bui_bgc_red bui_block bui_radius" href="openenvelope.html">进去红包库</a></p>'
               })
            }     
            BtCtrl= false;      
        }
    });
}
//答对题目增加红包
function AddRedEnvelopeQ3Ture(){
    if (BtCtrl) return;
    BtCtrl= true;
	$.ajax({
		type: "get",
		url: _jsonUrl + "/front/addCNYQAGameQAEnvelope.html",
		async: true,
		cache: false,
		data: {
			isAnswerCorrect:"true"
		},
		error: function(data) {
			jsonError();
            BtCtrl= false;
		},
		success: function(data) {
			fanjs_modal({
				width: '296px',
				positions: 'center',
				content: '<p class="bui_ta_c"><img src="images/openhintbg.png" style="max-width:272px;max-height:360px;" /></p><p id="openhint" style="position: absolute; margin-top:-40%; left:50%; margin-left:-90px;"><a href="openenvelope.html"><img src="images/open.png" style="max-width:180px;max-height:320px;" /></a></p>'
			})
            BtCtrl= false;
		}
	});
}
function AddRedEnvelopeQ3False(){
    if (BtCtrl) return;
    BtCtrl= true;
    $.ajax({
        type: "get",
        url: _jsonUrl + "/front/addCNYQAGameQAEnvelope.html",
        async: true,
        cache: false,
        data: {
            isAnswerCorrect:"false"
        },
        error: function(data) {
            jsonError();
            BtCtrl= false;
        },
        success: function(data) {
            fanjs_modal({
                width: '296px',
                positions: 'center',
                content: '<p class="bui_ta_c"><img src="images/openhintbg.png" style="max-width:272px;max-height:360px;" /></p><p id="openhint" style="position: absolute; margin-top:-40%; left:50%; margin-left:-90px;"><a href="openenvelope.html"><img src="images/open.png" style="max-width:180px;max-height:320px;" /></a></p>'
            })
            BtCtrl= false;
        }
    });
}
function PlayGameHint() {
	$("#playHint").attr('style', 'display:block');
}
$(document).ready(function(){
    //回答完问题获取最初的（即未开始拆之前）小红包总数
    var contentBox = $("#open-envelope");
    $.ajax({
        type: "get",
        url: _jsonUrl + "/front/getQARemainEnvelopeNumByOpenid.html",
        async: true,
        cache: false,
        error: function(data) {
            jsonError();
        },
        success: function(data) {
            //alert(data.data.id) 
            //判断可拆红包数量，大于0拆红包，为null时进入获取大红包分享提示页面           
            if(data.data != null && data.data.remainEnvelopeNum > 0){
                contentBox.html('<div class="year-section open-envelope">'+
                    '<div class="year-section-content" >'+
                    '<p class="titletu" ><img src="images/havetitle.png" /></p>'+
                        '<p><img src="images/opennumbg.png" /></p>'+
                        '<p class="opentotal"><span class="total-red" style="font-weight:bold;">' + data.data.totalEnvelopeNum + '</span></p>'+
                        '<p class="openremain"><span class="remain-red">'+ data.data.remainEnvelopeNum + '<span/></p>' +
                        '<p class="openremainy"><span class="remain-yellow" style="font-weight:bold;"><i>'+ data.data.remainEnvelopeNum + '</i><span/></p>' +
                        '<p class="opentest"><a href="javascript:OpenEnvelope('+data.data.id+');"><img src="images/opentest.png" /></a></p>'+
                        '</div>'+
                        '</div>');
            }else{
                contentBox.html('<div class="year-section open0-envelope">' +
                '<div class="year-section-content">' +
                '<p style="margin-top:3%;"><img src="images/total0.png" /></p>'+
                '<p class="sharetest"><a href="javascript:year_shareTips();"><img src="images/sharezi.png" /></a></p>'+
                '</div>'+
            '</div>');
            }
        }
    });       
});
//拆开红包，查看奖品
function OpenEnvelope(id){
    if (BtCtrl) return;
    BtCtrl= true;
    var contentBox = $("#envelope-results");
    $.ajax({
        type: "get",
        url: _jsonUrl + "/front/openEnvelopeByEnvelopeId.html",
        async: true,
        cache: false,
        data: {
           envelopeId:id ,
        },
        error: function(data) {
            jsonError();
            BtCtrl= false;
        },
        success: function(data) {
            //alert(data.data.prizeType)
            //成功拆红包
            //获得吉红包，返回判断可拆红包数量
            if(data.code == 0 && data.data.prizeType == 0){
                contentBox.html('<div class="year-section ji-envelope">'+
                    '<div class="year-section-content">'+
                    '<p style="margin-top:18%;"><img src="images/en-resultbg.png" /></p>'+
                    '<p class="titletu" style="margin-top:-140%;"><img src="images/yihan.png" /></p>'+
                    '<p class="jien"><a href="javascript:;"><img src="images/jien.png" /></a></p>'+
                    '<p class="return"><a href="javascript:OpenEnvelopeNum();"><img src="images/return.png" /></a></p>'+
                    '</div>'+
                    '</div>');
                $("#open-envelope").attr('style', 'display: none');
                $("#envelope-results").attr('style','display:block');
            }
            //获得产品红包，填写信息查表单页面的填写问题
            if(data.code == 0 && data.data.prizeType == 1){
                contentBox.html('<div class="year-section product-envelope">'+
                    '<div class="year-section-content">'+
                    '<p style="margin-top:18%;"><img src="images/en-resultbg.png" /></p>'+
                    '<p class="titletu" style="margin-top:-140%;"><img src="images/havetitle.png" /></p>'+
                    '<p style="margin-top:-4%;"><img src="images/productname.png" /></p>'+
                    '<div class="product">'+
                    '<div style="width:100%; height:auto; position: absolute; top:0; left:-24%;">'+
                    '<img src="'+data.data.prizeImg+'" style="width: 50%;" alt="" />'+
                    '</div>'+
                    '<div style="width:50%; height:auto; position:absolute; top:0; left:56%;">'+
                    '<p style="font-size:1.4rem">'+
                    '<span style="font-size:1.6rem">获得：</span>'+
                    '<span style="color:#000;">'+data.data.prizeName+'</span>'+
                    '</p>'+
                    '</div>'+
                    '</div>'+
                    '<form action="javascript:;" style="margin-top:-6%;">'+
                    '<p><img src="images/username.png" /></p>'+
                    '<li><div><input type="text" name="userName" id="userName" value="" placeholder="填写你的姓名" maxlength="20" class="informlist namelist" /></div></li>'+
                    '<p style="margin-top:-2%;"><img src="images/userphone.png" /></p>'+
                    '<li><div><input type="tel" name="userMobile" id="userMobile" value="" placeholder="填写你的手机号" maxlength="11" class="informlist namelist" /></div></li>'+
                    '<p style="margin-top:-2%;"><img src="images/useradress.png" /></p>'+
                    '<li><div ><textarea  rows="2" class="bui_ipt_48 informlist namelist" placeholder="填写你的地址" maxlength="255" name="userContact"></textarea></div></li>'+
                    '</form>'+
                    '<p class="getgift"><a href="javascript:year_form('+data.data.id+');"><img src="images/getgift.png" /></a></p>'+
                    '</div>'+
                    '</div>');
                    $("#open-envelope").attr('style', 'display: none');
                    $("#envelope-results").attr('style','display:block');
            }
            //优惠券红包（还需要添加扫码二维码领取奖品弹窗）
            if(data.code == 0 && data.data.prizeType == 2){
                contentBox.html('<div class="year-section product-envelope">'+
                '<div class="year-section-content">'+
                '<p style="margin-top:18%;"><img src="images/en-resultbg.png" /></p>'+
                '<p class="titletu" style="margin-top:-140%;"><img src="images/havetitle.png" /></p>'+
                '<p class="coupon"><span class="couponGet">中了：</span>'+data.data.prizeName +'</p>'+
                '<p style="width:55%;margin:10px auto 0;"><img src="'+data.data.prizeImg+'" /></p>'+
                '<p style="line-height:40px; text-align:center;">长按二维码领取奖品</p>'+
                '<p class="getgift"><a href="javascript:OpenEnvelopeNum()"><img src="images/return.png" /></a></p>'+
                '</div>'+'</div>');
                $("#open-envelope").attr('style', 'display: none');
                    $("#envelope-results").attr('style','display:block');
            }
            if(data.code == 8 && data.data == null){
                 buijs_alert({
            		content: '该红包您已经拆过，请勿重复操作哦~'
                })
            }
            if(data.code == 9 && data.data == null){
                contentBox.html('<div class="year-section product-envelope">'+
                        '<div class="year-section-content">'+
                            '<p style="margin-top:18%;"><img src="images/en-resultbg.png" /></p>'+
                            '<p class="titletu" style="margin-top:-140%;"><img src="images/mashang-open.png" /></p>'+
                            '<p><img src="images/mashang-hint.png" /></p>'+
                            '<p><img src="images/erweima.png" /></p>'+
                        '</div>'+
                    '</div>');
                $("#open-envelope").attr('style', 'display: none');
                $("#envelope-results").attr('style','display:block');
            }
            BtCtrl= false;
        }
    });
}
//有小红包拆的情况下判断可拆红包数量，大于0继续拆红包，为null时进入分享提示页面   
function OpenEnvelopeNum() {
    if (BtCtrl) return;
    BtCtrl= true;
    var contentBox = $("#open-envelope");
    $.ajax({
        type: "get",
        url: _jsonUrl + "/front/getQARemainEnvelopeNumByOpenid.html",
        async: true,
        cache: false,
        error: function(data) {
            jsonError();
            BtCtrl= false;
        },
        success: function(data) {
            BtCtrl= false;
            //alert(data.data.id)                                
            if(data.data != null && data.data.remainEnvelopeNum > 0){
                contentBox.html('<div class="year-section open-envelope">'+
                    '<div class="year-section-content" >'+
                    '<p class="titletu" ><img src="images/havetitle.png" /></p>'+
                        '<p><img src="images/opennumbg.png" /></p>'+
                        '<p class="opentotal"><span class="total-red" style="font-weight:bold;">' + data.data.totalEnvelopeNum + '</span></p>'+
                        '<p class="openremain"><span class="remain-red">'+ data.data.remainEnvelopeNum + '<span/></p>' +
                        '<p class="openremainy"><span class="remain-yellow" style="font-weight:bold;"><i>'+ data.data.remainEnvelopeNum + '</i><span/></p>' +
                        '<p class="opentest"><a href="javascript:OpenEnvelope('+data.data.id+');"><img src="images/opentest.png" /></a></p>'+
                        '</div>'+'</div>');
                $("#open-envelope").attr('style', 'display: block');
                $("#envelope-results").attr('style','display:none');
            }else{
                contentBox.html('<div class="year-section open0-envelope">' +
                '<div class="year-section-content">' +
                '<p style="margin-top:3%;"><img src="images/remain0.png" /></p>'+
                '<p class="sharetest"><a href="javascript:year_shareTips();"><img src="images/sharezi.png" /></a></p>'+
                '</div>'+'</div>');
                $("#open-envelope").attr('style', 'display: block');
                $("#envelope-results").attr('style','display: none');
            }
        }
    });
}



//拆开大红包，查看奖品
function OpenBigEnvelope(id,html){
    if (BtCtrl) return;
    BtCtrl= true;
    var contentBox = $("#envelope-results");
    if (html) {
        contentBox= $(html)
    };
    $.ajax({
        type: "get",
        url: _jsonUrl + "/front/openEnvelopeByEnvelopeId.html",
        data: {
           envelopeId:id
        },
        error: function(data) {
            jsonError();
            BtCtrl= false;
        },
        success: function(data) {
            BtCtrl= false;
            //alert(data.data.prizeType)
            //成功拆红包
            //获得吉红包，返回判断可拆红包数量
            if(data.code == 0 && data.data.prizeType == 0){
                contentBox.html('<div class="year-section ji-envelope">'+
                    '<div class="year-section-content">'+
                    '<p style="margin-top:18%;"><img src="images/en-resultbg.png" /></p>'+
                    '<p class="titletu" style="margin-top:-140%;"><img src="images/yihan.png" /></p>'+
                    '<p class="jien"><a href="javascript:;"><img src="images/jien.png" /></a></p>'+
                    '<p class="return"><a href="#"><img src="images/return.png" /></a></p>'+
                    '</div>'+
                    '</div>');
                $('.return').click(function(){
                    window.location.reload();
                })
                $("#open-envelope").attr('style', 'display: none');
            }
            //获得产品红包，填写信息查表单页面的填写问题
            if(data.code == 0 && data.data.prizeType == 1){
                contentBox.html('<div class="year-section product-envelope">'+
                    '<div class="year-section-content">'+
                    '<p style="margin-top:18%;"><img src="images/en-resultbg.png" /></p>'+
                    '<p class="titletu" style="margin-top:-140%;"><img src="images/havetitle.png" /></p>'+
                    '<p style="margin-top:-4%;"><img src="images/productname.png" /></p>'+
                    '<div class="product">'+
                    '<div style="width:100%; height:auto; position: absolute; top:0; left:-24%;">'+
                    '<img src="'+data.data.prizeImg+'" style="width: 50%;" alt="" />'+
                    '</div>'+
                    '<div style="width:50%; height:auto; position:absolute; top:0; left:56%;">'+
                    '<p style="font-size:1.4rem">'+
                    '<span style="font-size:1.6rem">获得：</span>'+
                    '<span style="color:#000;">'+data.data.prizeName+'</span>'+
                    '</p>'+
                    '</div>'+
                    '</div>'+
                    '<form action="javascript:;" style="margin-top:-6%;">'+
                    '<p><img src="images/username.png" /></p>'+
                    '<li><div><input type="text" name="userName" id="userName" value="" placeholder="填写你的姓名" maxlength="20" class="informlist namelist" /></div></li>'+
                    '<p style="margin-top:-2%;"><img src="images/userphone.png" /></p>'+
                    '<li><div><input type="tel" name="userMobile" id="userMobile" value="" placeholder="填写你的手机号" maxlength="11" class="informlist namelist" /></div></li>'+
                    '<p style="margin-top:-2%;"><img src="images/useradress.png" /></p>'+
                    '<li><div ><textarea  rows="2" class="bui_ipt_48 informlist namelist" placeholder="填写你的地址" maxlength="255" name="userContact"></textarea></div></li>'+
                    '</form>'+
                    '<p class="getgift"><a href="javascript:year_formBig('+data.data.id+');"><img src="images/getgift.png" /></a></p>'+
                    '</div>'+
                    '</div>');
                    $("#open-envelope").attr('style', 'display: none');
            }
            //优惠券红包（还需要添加扫码二维码领取奖品弹窗）
            if(data.code == 0 && data.data.prizeType == 2){
                contentBox.html('<div class="year-section product-envelope">'+
                '<div class="year-section-content">'+
                '<p style="margin-top:18%;"><img src="images/en-resultbg.png" /></p>'+
                '<p class="titletu" style="margin-top:-140%;"><img src="images/havetitle.png" /></p>'+
                '<p class="coupon"><span class="couponGet">中了：</span>'+data.data.prizeName +'</p>'+
                '<p style="width:55%;margin:10px auto 0;"><img src="'+data.data.prizeImg+'" /></p>'+
                '<p style="line-height:40px; text-align:center;">长按二维码领取奖品</p>'+
                '<p class="getgift"><a href="#"><img src="images/return.png" /></a></p>'+
                '</div></div>');
                $('.getgift').click(function(){
                    window.location.reload();
                })
                $("#open-envelope").attr('style', 'display: none');
            }
            if(data.code == 8 && data.data == null){
                buijs_alert({
            		content: '该红包您已经拆过，请勿重复操作哦~'
                })
            }
            //码上拆扫码页(关注才能拆红包提示)
            if(data.code == 9 && data.data == null){
                contentBox.html('<div class="year-section product-envelope">'+
                        '<div class="year-section-content">'+
                            '<p style="margin-top:18%;"><img src="images/en-resultbg.png" /></p>'+
                            '<p class="titletu" style="margin-top:-140%;"><img src="images/mashang-open.png" /></p>'+
                            '<p><img src="images/mashang-hint.png" /></p>'+
                            '<p><img src="images/erweima.png" /></p>'+
                        '</div>'+
                    '</div>');
                $("#open-envelope").attr('style', 'display: none');
            }
        }
    });
}

function year_formBig(id) {
    if (BtCtrl) return;
    BtCtrl= true;
    var _form = $('form');
    var _btn = _form.find('[name=btn]');
    var userName = _form.find('[name=userName]');
    var userMobile = _form.find('[name=userMobile]');
    var userContact = _form.find('[name=userContact]');

    if (buijs_formcheck_length(userName.val(), 20, 1) == false) {
        buijs_alert({
            content: '请认真填写你的名字'
        });
        BtCtrl= false;
        return false;
    } else if (buijs_formcheck_mobile(userMobile.val()) == false) {
        buijs_alert({
            content: '请认真填写你的手机'
        });
        BtCtrl= false;
        return false;
    } else if (buijs_formcheck_length(userContact.val(), 255, 1) == false) {
        buijs_alert({
            content: '请认真填写你的详细地址'
        });
        BtCtrl= false;
        return false;
    } else {
        _btn.html('<button disabled="disabled" class="bui_btn_48 bui_bgc_white_d48 bui_tc_white bui_block bui_mt_12"><i class="fa fa-circle-o-notch fa-spin fa-fw"></i> 正在提交...</button>');
        $.ajax({
            type: "post",
            url: _jsonUrl + "/front/updateEnvelopeAddressByEnvelopeId.html",
            async: true,
            cache: false,
            data: {
                'envelopeIds': id,        //（获取产品红包id）
                "name": userName.val(),
                "phone": userMobile.val(),
                "address": userContact.val()
            },
            error: function() {
                jsonError();
                BtCtrl= false;
            },
            success: function(data) {
                BtCtrl= false;
                if (data.code == 0) {
                    buijs_alert({
                        content: '信息已经提交！感谢您的参与'
                    });
                    setTimeout(function() {
                        window.location.href= 'index.html';
                    }, 2000);
                }else {
                    buijs_alert({
                        content: data.msg
                    })
                }
                
            }
        });
    };
};

//信息表单提交
function year_form(id) {
    if (BtCtrl) return;
    BtCtrl= true;
    var _form = $('form');
    var _btn = _form.find('[name=btn]');
    var userName = _form.find('[name=userName]');
    var userMobile = _form.find('[name=userMobile]');
    var userContact = _form.find('[name=userContact]');

    if (buijs_formcheck_length(userName.val(), 20, 1) == false) {
        buijs_alert({
            content: '请认真填写你的名字'
        });
        BtCtrl= false;
        return false;
    } else if (buijs_formcheck_mobile(userMobile.val()) == false) {
        buijs_alert({
            content: '请认真填写你的手机'
        });
        BtCtrl= false;
        return false;
    } else if (buijs_formcheck_length(userContact.val(), 255, 1) == false) {
        buijs_alert({
            content: '请认真填写你的详细地址'
        });
        BtCtrl= false;
        return false;
    } else {
        _btn.html('<button disabled="disabled" class="bui_btn_48 bui_bgc_white_d48 bui_tc_white bui_block bui_mt_12"><i class="fa fa-circle-o-notch fa-spin fa-fw"></i> 正在提交...</button>');
        $.ajax({
            type: "post",
            url: _jsonUrl + "/front/updateEnvelopeAddressByEnvelopeId.html",
            async: true,
            cache: false,
            data: {
                'envelopeIds': id,        //（获取产品红包id）
                "name": userName.val(),
                "phone": userMobile.val(),
                "address": userContact.val()
            },
            error: function() {
                jsonError();
                BtCtrl= false;
            },
            success: function(data) {
                BtCtrl= false;
                if (data.code == 0) {
                    buijs_alert({
                        content: '信息已经提交！感谢您的参与'
                    });
                    setTimeout(function() {
                        OpenEnvelopeNum();
                    }, 2000);
                }else {
                    buijs_alert({
                        content: data.msg
                    })
                }
                
            }
        });
    };
};

$(document).ready(function(){
	//fanjs_showloading();
	vm();
});

//json报错
function jsonError() {
	buijs_alert({
		content: '<p>虽然你的网速击败了99%的人...<br/>但~没拿到数据并没有什么*用...</p><p class="bui_inline bui_at_noline bui_mt_12"><a href="javascript:window.location.reload();" class="bui_bds_1 bui_bdc_white bui_tc_white bui_ptb_6 bui_plr_12 bui_radius bui_ts_12">再拿一次！</a></p>',
		timeout: 0
	});
};
$(window).load(function() {
    _windowload = true;
    checkLoading();
});

$(window).resize(function() {
    vm();
});