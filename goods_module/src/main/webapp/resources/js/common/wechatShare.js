/**Leim对微信分享方法进行封装*/
//配置各环境路径
var _domain = window.location.host;
var urlContent;
var appid;

//地图参数
var geocoder,map, marker = null;

if (_domain.indexOf('testv20') >= 0) {
	//XDtech_阿里云
	urlContent = "http://" + _domain;
	//XDtech公众号appid
	appid = "wx358f79dcdf157a0d";
} else if (_domain.indexOf('jizhigou') >= 0) {
	//XDtech_阿里云
	urlContent = "http://" + _domain;
	//XDtech公众号appid
	appid = "wxffe816dc1ae878d2";
} else if (_domain.indexOf('mcoding.cn') >= 0) {
	//XDtech_阿里云
	urlContent = "http://" + _domain;
	//XDtech公众号appid
	appid = "wx07c01da2e6bcb01d";
} else {
	//测试环境_局域网
	urlContent = "http://localhost:7080/goods_module/";
};
console.log("==========="+urlContent);
console.log("==========="+appid);

//title分享标题
//desc分享简述内容
//link点击后的链接地址，""表示当前链接
//getshorturllink为当前链接下是否获取短链接 true是false否
//用于获取短链接的地址，getshorturl为true时需要该参数，为false传空字符串即可
//imageUrl图片地址
function wechatSharePublic(title, desc, link, getshorturl, shorturl, imgUrl) {
	var fullPath = window.location.href;
	var timestamp = 0; //时间戳
	var nonceStr = ''; //随机串
	var signature = ''; //签名
	$.ajax({
		type: "post",
		url: urlContent + "/api/wechatShare2",
		async: false,
		//global: false,
		dataType: "json",
		data: {
			'fullPath': fullPath
		},
		success: function(rs) {
			console.log(rs);
			timestamp = rs.data.timestamp;
			nonceStr = rs.data.nonceStr;
			signature = rs.data.signature;
			appid = rs.data.appid;
			openid = rs.msg;
			if(link==""){
				link = rs.data.link;
				if(getshorturl){
					runShortUrl(shorturl,openid);
				}
			}
			configWxPublic(appid, timestamp, nonceStr, signature, link, title, desc, getshorturl, imgUrl);
		}
	});
}
//获取短链接
function runShortUrl(shorturl, _openid) {
	$.ajax({
		type: "get",
		url: _jsonUrl + "/front/makeShortUrl.html",
		async: false,
		cache: false,
		dataType: 'json',
		data: {
			longUrl: _jsonUrl + shorturl + _openid
		},
		error: function(data) {
			jsonError();
		},
		success: function(data) {
			shortLink = data.data;
		}
	});
};
function configWxPublic(appid, timestamp, nonceStr, signature, link, title, desc, getshorturl, imgUrl) {
	/*var link = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
			"appid=" +appid+
			"&redirect_uri="+urlContent+"/api/wechatCheckWxUser2.html?brandCode=MRMJ" +
			"&response_type=code&scope=snsapi_userinfo" +
			"&state="+urlContent+"/api/wechatTest.html#wechat_redirect";*/
	if(getshorturl){//短链接
		var index = link.indexOf("&state=");
		link = link.substring(0,index)+"&state="+shortLink;
	}
	if(imgUrl.indexOf("http://")>=0){
		imgUrl = imgUrl;
	}else{
		imgUrl = urlContent + imgUrl;
	}

	wx.config({
		debug: false,
		appId: appid,
		timestamp: timestamp,
		nonceStr: nonceStr,
		signature: signature,
		jsApiList: ['onMenuShareTimeline',
			//'hideMenuItems',
			//'hideAllNonBaseMenuItem',
			'onMenuShareAppMessage',
			'previewImage',    //预览图片功能
			'getLocation'
		]
	});

	wx.error(function(res) {
		js_msg({
			'msg': res.errMsg
				//'href':'index.html'
		});
	});

	wx.ready(function() {
		wx.onMenuShareTimeline({
			title: title, // 分享标题
			desc: desc, // 分享描述
			link: link, // 分享链接
			imgUrl: imgUrl, // 分享图标
			success: function() {
				// 用户确认分享后执行的回调函数
			},
			cancel: function() {
				// 用户取消分享后执行的回调函数
			}
		});
		
		wx.onMenuShareAppMessage({
			title: title, // 分享标题
			desc: desc, // 分享描述
			link: link, // 分享链接
			imgUrl: imgUrl, // 分享图标
			type: '', // 分享类型,music、video或link，不填默认为link
			dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
			success: function() {
				// 用户确认分享后执行的回调函数
			},
			cancel: function() {
				// 用户取消分享后执行的回调函数
				js_msg({
					'msg': '已取消分享！'
				});

			}
		});

		//获取当前位置是否在配送范围内
		getLocal();
	});
};

function getLocal(){
    
    wx.getLocation({
      success: function (res) {
          
    	  console.log("lat:"+res.latitude+"   lng:"+res.longitude);

    	  var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90            
    	  var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。            
    	  var speed = res.speed; // 速度，以米/每秒计            
    	  var accuracy = res.accuracy; // 位置精度            
    	  $.ajax({   
	    	  url: "http://apis.map.qq.com/ws/geocoder/v1/?location="+latitude+","+longitude+"&coord_type=5&key=TA4BZ-BGY3U-XVPVS-BE6B7-YH2C7-NMFRX&output=jsonp&callback=calllocation",             
	    	  type: "GET",             
	    	  dataType:'jsonp',             
	    	  jsonp:'calllocation'             
    	  }); 
    	
    	  //console.log("获取地址转坐标：http://apis.map.qq.com/ws/geocoder/v1/?address=广州花园酒店&key=TA4BZ-BGY3U-XVPVS-BE6B7-YH2C7-NMFRX");
    	  //console.log("获取地址转坐标：http://apis.map.qq.com/ws/geocoder/v1/?address=广州市增城区荔城街增城中学&key=TA4BZ-BGY3U-XVPVS-BE6B7-YH2C7-NMFRX");
    	  //console.log("获取地址转坐标：http://apis.map.qq.com/ws/geocoder/v1/?address=	广东省佛山市南海区大沥体育西路2号宝盈国际广场&key=TA4BZ-BGY3U-XVPVS-BE6B7-YH2C7-NMFRX");
    	  $.ajax({
				type: "GET",
				//url: "http://apis.map.qq.com/ws/distance/v1/?mode=driving&from=23.13542,113.28572&to="+latitude+","+longitude+"&key=TA4BZ-BGY3U-XVPVS-BE6B7-YH2C7-NMFRX&output=jsonp&callback=calllocation2",
				//url: "http://apis.map.qq.com/ws/distance/v1/?mode=driving&from=23.28947,113.79125&to="+latitude+","+longitude+"&key=TA4BZ-BGY3U-XVPVS-BE6B7-YH2C7-NMFRX&output=jsonp&callback=calllocation2",
				url: "http://apis.map.qq.com/ws/distance/v1/?mode=driving&from=23.11763,113.10383&to="+latitude+","+longitude+"&key=TA4BZ-BGY3U-XVPVS-BE6B7-YH2C7-NMFRX&output=jsonp&callback=calllocation2",
				dataType: "jsonp",				             
		    	jsonp:'calllocation2'
			});
      },
      cancel: function (res) {
        alert('用户拒绝授权获取地理位置');
      }
    });
}


function calllocation(data){
	var name=data.result.formatted_addresses.rough;
	var address=data.result.formatted_addresses.recommend;
	var lat=data.result.location.lat;
	var lng=data.result.location.lng;
	console.log(JSON.stringify(data));
	/**
	wx.openLocation({
	    latitude: lat, // 纬度，浮点数，范围为90 ~ -90
	    longitude: lng, // 经度，浮点数，范围为180 ~ -180。
	    name: name, // 位置名
	    address: address, // 地址详情说明
	    scale: 18, // 地图缩放级别,整形值,范围从1~28。默认为最大
	    infoUrl: '' // 在查看位置界面底部显示的超链接,可点击跳转
	});
	*/
}

function calllocation2(data){
	console.log(data);

	var localUrl=window.location.href;
	if(localUrl.indexOf('index') >= 0) {
		if(typeof(Storage) !== "undefined") {
	        if (sessionStorage.firstAccess) {
	        		
	        } else {
	        	sessionStorage.firstAccess=1;
	        	
	        	if(data != undefined && data.status == 0){
	    			//console.log("驾车距离：约"+ data.result.elements[0].distance +"米  耗用时间：约"+ data.result.elements[0].duration + "秒" );
	    			var distance=parseFloat(data.result.elements[0].distance);
	    			
	    			if(distance > 5000.00){
	    				
	    				buijs_modal({
	    					isclose: false,
	    					positions: 'center',
	    					width: '85%',
	    					height: '250px',
	    					content: '<div class="bui_p_32 bui_ta_c"><p>您当前的位置不在配送范围内，</p><p>是否继续访问？</p></div>',
	    					footer: '<hr><div class="bui_p_12"><button class="bui_btn_48 ' + vueObj.style.btnTrue + '" style="width:45%;" onclick="buijs_modal_close();">是</button>&nbsp&nbsp&nbsp<button class="bui_btn_48 ' + vueObj.style.btnTrue + '" style="width:45%;" onclick="wx.closeWindow();">否</button></div>'
	    				});
	    			}
	    		}else{
	    			buijs_modal({
	    				isclose: false,
	    				positions: 'center',
	    				width: '85%',
	    				height: '250px',
	    				content: '<div class="bui_p_32 bui_ta_c"><p>您当前的位置不在配送范围内，</p><p>是否继续访问？</p></div>',
	    				footer: '<hr><div class="bui_p_12"><button class="bui_btn_48 ' + vueObj.style.btnTrue + '" style="width:45%;" onclick="buijs_modal_close();">是</button>&nbsp&nbsp&nbsp<button class="bui_btn_48 ' + vueObj.style.btnTrue + '" style="width:45%;" onclick="wx.closeWindow();">否</button></div>'
	    			});
	    		}
	        }
		} else {
	        alert("抱歉！您的浏览器不支持 Web Storage ...");
	    }		
	}
}