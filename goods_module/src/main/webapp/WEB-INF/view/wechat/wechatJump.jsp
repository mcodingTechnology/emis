<%@ page contentType="text/html;charset=UTF-8"%>
					<center>
						code: ${code} <br>
						state: ${state} <br>
						openid: ${openid} <br>
						OAuthAccessToken:${OAuthAccessToken}<br>
						<a href="http://v.merryplus.com/api/wechatAddress.html?openid=${openid}&code=${code}&state=${state}">获取收货地址</a>
					</center>
					
<script>
/* function createMenu(){
	//alert("s");
	$.ajax({
 		type:"post",
 		dataType:"json",
 		data:{"xxx":"xxx"},
 		url:"createWechatMenu.html",
 		success:function(data){  
            alert("提示："+data.msg);
        },  
        error : function(data) {  
        	alert("提示："+data.msg);
        } 
     });
} */
</script>