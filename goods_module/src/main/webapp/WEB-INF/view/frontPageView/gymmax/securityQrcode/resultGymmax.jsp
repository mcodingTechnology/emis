<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<style>
.cls_button3 {
    color: #ffffff;
    width: 250px;
    /* margin: 12px 0px 12px 30px; */
    margin: 0 auto;
    margin-bottom: 20px;
    line-height: 30px;
    background: #ff6a00;
    border-radius: 100px;
    -webkit-border-radius: 100px;
}
</style>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>BIG生活扫码积分</title>
		<link rel="stylesheet" href="<%= basePath%>resources/css/scan.css" />
		<link rel="stylesheet" href="<%= basePath%>resources/css/csshake.min.css" />
		<!-- 引入微信JSJDK -->
		<script type="text/javascript" src="<%= basePath%>resources/metronic_v2.0.2/assets/plugins/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	</head>
	<body>

		<!-- Post page start -->
		<div id="id_post_page" class="cls_block_gymmax" style="min-height:870px;">
			<div class="cls_info_block" style="opacity:0.9;">
				<div class="cls_name">亲爱的
				<c:choose>
                        <c:when test="${fullName!=null}">
                        	${fullName}
                        </c:when>
                <c:otherwise>
                		${mobilePhone}
                </c:otherwise>
                </c:choose>
				</div>
				
				<c:choose>
                        <c:when test="${isPointed=='1'}">
                        	<c:choose>
		              			<c:when test="${isGift=='1'}">
		                      	<div id="id_success_info" class="cls_tips" style="color:#e83428;">您所查询的产品是赠品，暂不积分，请放心使用。</div>
		                      </c:when>
		                      <c:otherwise>
		                       <!-- fail info start -->
	                        	<div id="id_success_info" class="cls_tips" style="color:#e83428;">你所查询的防伪码为正品，此产品已积分过，每个产品只能积分一次，若有疑问请登录BIG生活官方微信或致电4009-313-999进行查询。</div>
	 	                       <!-- success info end -->
		                      </c:otherwise>
		              		</c:choose>
                        </c:when>
                <c:otherwise>
                        <!-- success info start -->
                		<div id="id_success_info" class="cls_tips" style="color:#e83428;">本次积分已成功。</div>
						<div id="id_success_prod" class="cls_tips2">
						<span class="cls_tips4" style="margin:8px 28px;"><span style="color:#e83428;">积分产品：</span>${productName}</span>
						</div>
						<!-- success info end -->
                </c:otherwise>
                </c:choose>
				
				
				<!-- balance info start -->
				<div id="id_balance_info" class="cls_balance">
					<c:if test="${isPointed=='0'}">
						<div>
							<div style="background: #e83428;">${point}</div>
							<div style="color:#e83428;">本次积分</div>
						</div>
					</c:if>
					<c:if test="${isPointed=='0' or isPointed=='1'}">
						<div>
							<div>${pointSum}</div>
							<div>账户余额</div>
						</div>
					</c:if>
					
				</div>
				<!-- balance info end -->

				<!-- footer info start -->
				<div class="cls_dividing_line"></div>
				
				<div class="cls_button3" style="margin-top:-10px;background: #e83428;" id="redirectMemberCenter">注册会员，立送100积分</div>
				<!-- <div class="cls_button3" style="margin-top:-10px;background: #e83428;" id="redirectFanpai">拿10积分试试手气</div> -->
				
				<div class="cls_tips2" style="color:#e83428;">关注BIG生活官方微信，积分产品欢乐兑</div>
				
				<div class="cls_2weima_block cls_tips2">
					<ul>
						<li><span style="background: #e83428;"></span><span>长按下方二维码</span></li>
						<li><span style="background: #e83428;"></span><span>直接搜索“BIG生活” 或“gymmaxer”</span></li>
					</ul>
					<img style="width:100%;margin-left:0px;margin-top:120px;" src="<%= basePath%>resources/images/gymmax_wx.jpg">
				</div>
				<input type="hidden" id="timestamp" value="${timestamp}"/>
				<input type="hidden" id="nonceStr" value="${nonceStr}"/>
				<input type="hidden" id="signature" value="${signature}"/>
				<div class="cls_button3" style="margin-top:-10px;background: #e83428;" id="scanQRCode">继续扫码</div>
				<!-- footer info end -->
			</div>
		</div>
		<!-- Post page end -->
		<!-- 百度网站统计 star -->
		<script type="text/javascript" src="<%= basePath%>resources/js/front/webStatistics.js"></script>
		<!-- end -->
	</body>
	<script>
		var timestamp = $("#timestamp").val();
		var nonceStr = $("#nonceStr").val();
		var signature = $("#signature").val();
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
	      	    		window.location.href= res.resultStr;
	      	      }
      	    	});
      	    };
      	    
      	//跳转个人中心
      		$("#redirectMemberCenter").on("click",function(){
      			window.location.href="http://v.gymmaxer.com/gMall/personal_center.html";
      		});
      		//跳转翻牌游戏
      		/* $("#redirectFanpai").on("click",function(){
      			$.ajax({//是否完善资料
                    type: "POST",
                    url: "/front/checkIsReady.html",
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 0) {
                            if(data.data!=null){
                            	var member = data.data;
                            	if(member.levelName!=null && member.healthProblem!=null && member.concernsPerson!=null && member.concerns!=null){
                            		window.location.href="http://v.gymmaxer.com/activity/giftmall_gxm_20151117/index.html?gameid=44";
                            	}else{
                            		mrmj.showMessage("请先注册会员并完善资料");
                            	}
                            }else{
                            	mrmj.showMessage("请先注册会员并完善资料");
                            }
                        }else{
                        	mrmj.showMessage("获取信息失败");
                        }
                    }
                });
      			//window.location.href="http://v.gymmaxer.com/activity/giftmall_gxm_20151117/index.html?gameid=44";
      		}); */
		</script>
</html>