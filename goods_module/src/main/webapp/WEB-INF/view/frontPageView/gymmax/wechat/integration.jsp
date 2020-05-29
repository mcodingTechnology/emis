<%@ page import="com.xingrun.pms.common.util.XRContextUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="format-detection" content="telephone=no" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>我要积分</title>
		<link rel="stylesheet" href="<%= XRContextUtil.getBaseRootPath()%>resources/css/mrmjWeChat.css" />
		<style>
			.cls_capsule_tips > div:last-child span{background:#e83428;}
			.cls_button, .cls_button2, .cls_button4{background:#e83428;}
			.cls_pop_tips > div > div:last-child{background:#e83428;}
			.cls_securityCode_block .cls_or_tips{color:#e83428;}
		</style>
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/metronic_v2.0.2/assets/plugins/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/js/front/wechat/mrmjUtil.js"></script>
		<!-- 引入微信JSJDK -->
		<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	</head>
	<body>

		<!-- Integration page start -->
		<div class="cls_block_bg">
			<div class="cls_capsule_tips">
				<div><img src="<%= XRContextUtil.getBaseRootPath()%>resources/images/gxmQrcode_logo.png"></div>
				<div>
					<div style="padding-top:40px;font-size:12px;font-weight:bold;"><span></span>刮开瓶盖图层，戳下方按钮进行扫码积分</div>
				</div>
			</div>
			
			<%-- <form id="id_securityCode_form" class="cls_input_block2">
				<div style="padding-right:0px;">
				<input id="id_securityCode_input" name="securityCode" type="tel" placeholder="请输入16位防伪码" style="font-size: 17px;"></div>
   			 	<div class="cls_label_or">OR</div>
   			 	<div  class="cls_button_scanicon"><img src="<%= XRContextUtil.getBaseRootPath()%>resources/css/images/scancode.png"></div>
				<input type="hidden" id="openid" name="openid" value="${openid}"/>	
				<div onclick="validateSecurityCodeInfo()" class="cls_button">立即积分</div>
			</form> --%>
			<input type="hidden" id="timestamp" value="${timestamp}"/>
			<input type="hidden" id="nonceStr" value="${nonceStr}"/>
			<input type="hidden" id="signature" value="${signature}"/>
			<form id="id_securityCode_form">
				<input type="hidden" id="openid" name="openid" value="${sessionScope.openid}"/>	
				<div class="cls_border_top"></div>
				<div class="cls_input_box">
					<div><span class="cls_icon_securityCode"></span></div>
					<div class="cls_securityCode_block">
						<div>
						<input id="id_securityCode_input" value="${SecurityQrcode}" name="securityCode" type="tel" placeholder="请输入16位防伪码" style="font-size: 17px;"/>
						</div>
						<div>
							<span class="cls_or_tips">OR</span>
							<span id="scanQRCode" class="cls_scan_icon"></span>
						</div>	
					</div>					
				</div> 
				<div id="id_psm_block" class="cls_input_box cls_hidden">
					<div><span class="cls_icon_psm"></span></div>
					<div>
						<input id="id_psm_input" name="id_barCode_input" type="tel" placeholder="请输入13位瓶身条形码" />
					</div>					
				</div>   			 	
				<div onclick="validateSecurityCodeInfo()" class="cls_button">立即积分</div>
				<!-- <div onclick="redirectMemberCenter()" class="cls_button">注册会员，立送100积分</div> -->
				<div onclick="redirectFanpai()" class="cls_button">拿10积分试试手气</div>
			</form>

			<div class="cls_dividing_line"></div>
			
			<div class="cls_integration_info" style="margin:0px;" id="id_result"></div>
			
			<!-- 积分失败 -->
			<div id="integration_fail"></div>
		</div>
		<!-- Integration page end -->

		<!-- 百度网站统计 star -->
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/js/front/webStatistics.js"></script>
		<!-- end -->
	</body>
	
	<script>
		var isNeedCheckPSM = false;  // 是否需要验证瓶身码
		var timestamp = $("#timestamp").val();//时间戳
		var nonceStr = $("#nonceStr").val();//随机串
		var signature = $("#signature").val();//签名
		wx.config({
  		    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
  		    appId: 'wxc453558db0e1d800', // 必填，公众号的唯一标识
  		    timestamp: timestamp, // 必填，生成签名的时间戳
  		    nonceStr: nonceStr, // 必填，生成签名的随机串
  		    signature: signature,// 必填，签名，见附录1
  		    jsApiList: ['scanQRCode'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
  		});
		//扫描二维码并返回结果
  	    document.querySelector('#scanQRCode').onclick = function () {
      	    wx.scanQRCode({
      	      needResult: 1,
      	      desc: 'scanQRCode desc',
      	      success: function (res) {
      	    	    //扫码后获取结果参数:htpp://v.merryplus.com/c/?64464，截取到url中的防伪码后，赋值给Input
      	    	  	var url = res.resultStr;
	      	    	var tempArray = url.split('?');
	      	    	var tempNum = tempArray[1];
	      	    	$("#id_securityCode_input").val(tempNum);
	      	    	
	      	    	//隐藏瓶身条形码输入框
                	$('#id_psm_block').addClass('cls_hidden');	
                	//关闭校验条形码
                	isNeedCheckPSM =false;
                	$("#id_result").html("");
                	$("#integration_fail").html("");
      	      }
  	    	});
  	    };
		var scanApp = {
				checkNum: function(str){
					var tempArray = [],
						i = 0,
						len = 0,
						tempValue = 0,
						isValid = true;

					tempArray = str.split('');

					for(i=0,len=tempArray.length; i<len;i++){
						tempValue = parseInt(tempArray[i], 10);
						if (isNaN(tempValue)) {
							isValid = false;
							break;
						};
					}

					return isValid;
				} // End checkNum
		}

		//跳转个人中心
  		/* function redirectMemberCenter(){
			window.location.href="http://dwz.cn/2azxtp";
			$.ajax({//是否完善资料
                type: "POST",
                url: "/EMIS/front/checkIsReady.html",
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        if(data.data!=null){
                        	var member = data.data;
                        	if(member.levelName!=null && member.healthProblem!=null && member.concernsPerson!=null && member.concerns!=null){
                        		mrmj.showMessage("您已完善个人资料");
                        	}else{
                        		window.location.href="http://dwz.cn/2azxtp";
                        	}
                        }
                    }else{
                    	mrmj.showMessage("获取信息失败");
                    }
                }
            }); */
			/* if(true){
				window.location.href="http://v.merryplus.com/api/getWechatCode.html?actionPage=personal";
			}else{
				
			}
  		} */
  		//跳转翻牌游戏
  		function redirectFanpai(){
  			window.location.href="http://v.gymmaxer.com/activity/giftmall_gxm_20151117/index.html?gameid=44";
  			/* $.ajax({//是否完善资料
                type: "POST",
                url: "/EMIS/front/checkIsReady.html",
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        if(data.data!=null){
                        	var member = data.data;
                        	if(member.levelName!=null && member.healthProblem!=null && member.concernsPerson!=null && member.concerns!=null){
                        		window.location.href="http://v.gymmaxer.com/activity/giftmall_gxm_20151117/index.html?gameid=44";
                        	}else{
                        		mrmj.showMessage("请选注册会员并完善资料");
                        	}
                        }else{
                        	mrmj.showMessage("请选注册会员并完善资料");
                        }
                    }else{
                    	mrmj.showMessage("获取信息失败");
                    }
                }
            });
  			//window.location.href="http://v.merryplus.com/activity/giftmall_20150921_game/index.html"; */
  		}
  		
        // 验证表单
		function validateSecurityCodeInfo(){
			var openid = $("#openid").val();
	        var securityCode = $("#id_securityCode_input").val();
	        var id_barCode_input = $("#id_psm_input").val().trim();
            if(mrmj.checkInputEmpty('id_securityCode_input')){
            	mrmj.showMessage('亲，防伪码不能为空');
            	return;
            }
            if(isNeedCheckPSM==true){
            	if(isNeedCheckPSM){
    				//校验条形码是否为空
            		if(mrmj.checkInputEmpty('id_psm_input')){
    	            	mrmj.showMessage('亲，瓶身条形码不能为空');
    	            	return;
    	            }
            		if (id_barCode_input.length !== 13) {
    					mrmj.showMessage('请输入以69开头的13位数字的条形码');
    					return;
    				};	
    				if (!scanApp.checkNum(id_barCode_input)) {
    					mrmj.showMessage('请输入以69开头的13位数字的条形码');
    					return false;
    				};
    				if ('69' !== id_barCode_input.substr(0,2)) {
    					mrmj.showMessage('亲，条形码是以69开头，在瓶身上的，不是在瓶盖哦!');
    					return false;
    				};
    				
    			}
            }else{
            	//条形码值置空
            	id_barCode_input ="";
            	$("#id_psm_input").val("");
            }
            
            mrmj.showLoader();
            var param = "openid="+openid+"&securityCode="+securityCode+"&id_barCode_input="
            +id_barCode_input+"&brandCode=JLD";
            
            //校验防伪码真伪与积分
            $.ajax({
                type: "post",
                data: param,
                url: "wechatCodeAndPoint",
                success: function (data) {
                	if (data.status == 0) {
                		$("#integration_fail").html("");
                		$("#id_result").html("");
                		$("#id_result").append("<div><img src='http://v.merryplus.com/resources/images/integration_success_gxm.png'></div>"+
                		"<div><div style='font-size: 20px;font-weight: bold;color: #7f7f7f;'>恭喜您，积分成功！</div>"+
                		"<div style='margin-top:8%'>产品名称："+data.data.ext2+"</div>"+
                		"<div>本次积分："+data.data.ext1+"分</div>"+
                		"<div>账户余额："+data.data.pointSum+"分</div></div>");
                		
                		//隐藏瓶身条形码输入框
                    	$('#id_psm_block').addClass('cls_hidden');	
                    	//关闭校验条形码
                    	isNeedCheckPSM =false;
                    } else if(data.status == 1){
                    	//显示瓶身条形码输入框
                    	$('#id_psm_block').removeClass('cls_hidden');	
                    	//开启校验条形码
                    	isNeedCheckPSM =true;
                    	$("#id_result").html("");
                    	$("#integration_fail").html("");
                    	$("#integration_fail").append("<div class='cls_integration_info2'>"+
								"<img src='http://v.merryplus.com/resources/css/images/integration_fail_gxm.png'> "+data.msg+"</div>"+	
            					"<div class='cls_label2'>请您尝试输入瓶身条形码</div>	"+
            					"<div class='cls_label3'>您亦可打4009-313-999， 由客服人员协助您解决问题</div>");
                    }else if(data.status == 2){ //产品码不存在，手动输入
                    	mrmj.showMessage(data.msg);
                    	$("#id_result").html("");
                    	$("#integration_fail").html("");
                    	//隐藏瓶身条形码输入框
                    	$('#id_psm_block').addClass('cls_hidden');	
                    	//关闭校验条形码
                    	isNeedCheckPSM =false;
                    }else if(data.status == 3){ //赠品
                    	$("#id_result").html("");
                    	$("#integration_fail").html("");
                    	$("#integration_fail").html("<div class='cls_integration_info2'>"+
								"<img src='http://v.merryplus.com/resources/css/images/integration_fail_gxm.png'> "+data.msg+"</div></br>"+	
            					"<div class='cls_label3'>您亦可打4009-313-999， 由客服人员协助您解决问题</div>");
                    }else if(data.status == 4){ //非该品牌
                    	window.location.href="brandTipView.html?brandCode=MRMJ";
                    }else if(data.status == 5){ //防伪码已积分
                    	$("#id_result").html("");
                    	$("#integration_fail").html("");
                    	$("#integration_fail").html("<div class='cls_integration_info2'>"+
								"<img src='http://v.merryplus.com/resources/css/images/integration_fail_gxm.png'> "+data.msg+"</div></br>"+	
            					"<div class='cls_label3'>您亦可打4009-313-999， 由客服人员协助您解决问题</div>");
                    }else{
                    	mrmj.showMessage(data.msg);
                    	$("#id_result").html("");
                    	$("#integration_fail").html("");
                    	//隐藏瓶身条形码输入框
                    	$('#id_psm_block').addClass('cls_hidden');	
                    	//关闭校验条形码
                    	isNeedCheckPSM =false;
                    }
                	mrmj.hideLoader();
                }, error : function(err) {
                	mrmj.hideLoader();
                	mrmj.showMessage(data.msg);
                	$("#id_result").html("");
                	$("#integration_fail").html("");
                	//隐藏瓶身条形码输入框
                	$('#id_psm_block').addClass('cls_hidden');	
                	//关闭校验条形码
                	isNeedCheckPSM =false;
         			 return;
        		   }                
         	}); 
        } // End validateSecurityCodeInfo

		$(document).ready(function() {
			mrmj.init();
		});  // End ready


		</script>
</html>