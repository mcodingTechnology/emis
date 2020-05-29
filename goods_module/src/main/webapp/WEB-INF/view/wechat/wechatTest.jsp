<%@ page contentType="text/html;charset=UTF-8"%>
					<center>
					<form id="form1" name="form1">
					<h2>微信收货地址</h2>
						<input type="text"  class="username" id="address1" name="address1" value=""/>
						<input type="text"  class="username" id="address2" name="address2" value=""/>
						<input type="text"  class="username" id="address3" name="address3" value=""/>
						<input type="text"  class="username" id="detail" name="detail" value=""/>
						<input type="text"  class="username" id="phone" name="phone" value=""/>
					</form>
					</center>
					
<script>
/**
 * 检测微信JsAPI
 * @param callback
 */
    if(typeof window.WeixinJSBridge == 'undefined' || typeof window.WeixinJSBridge.invoke == 'undefined'){
       alert("WeixinJSBridge work!");
    }else{
       alert("WeixinJSBridge error!");
    }
    
WeixinJSBridge.invoke('editAddress',{
	"appId" : "",
	"scope" : "jsapi_address",
	"signType" : "sha1",
	"addrSign" : "",
	"timeStamp" : "",
	"nonceStr" : "",
},function(res){
	alert("微信收货地址调成功！status:"+res);
	//若res 中所带的返回值不为空，则表示用户选择该返回值作为收货地址。否则若返回空，则表示用户取消了这一次编辑收货地址。
	document.form1.phone.value = res.telNumber;
	});
});
</script>