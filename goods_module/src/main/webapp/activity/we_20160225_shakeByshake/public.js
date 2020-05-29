
var _domain = window.location.host;
//获得gameid
var _gameid =Number(getUrlString('gameid')) ;
//Number(location.search.split('gameid=')[1]);
//生产环境
var url = "";
/*var appid = "wxc29d38541362f295";
if(_domain == 'v.merryplus.com'){
	url = "http://v.merryplus.com/";
}*/
//测试环境
var appid = "wx07c01da2e6bcb01d";
if(_domain == 'www.coding.mobi'){
	url = "http://www.coding.mobi/EMIS/";
}
else
{
	url="http://localhost:8080/EMIS/";
}

function changeDateTimeFormat(dateTime) {
    		var date = new Date(dateTime);
    		//获取年
    		var year = date.getFullYear();
    		//获取月
    		var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1)
    				: date.getMonth() + 1;
    		//获取日
    		var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date
    				.getDate();
    		//获取时
    		var hours = date.getHours() < 10 ? "0" + date.getHours() : date
    				.getHours();
    		//获取分
    		var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date
    				.getMinutes();
    		//获取秒
    		var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date
    				.getSeconds();

    		 var sTime =  year + "年" + month + "月" + currentDate + "日 " ;
//  		 + hours + ":" + minute + ":" + seconds;
    		return sTime;
    	}
function getUrlString(name) {
	var reg = new RegExp("(^|&)"+ name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); //匹配目标参数
	if (r != null){
		return unescape(r[2]);
	}
	return ''; //返回参数值
}