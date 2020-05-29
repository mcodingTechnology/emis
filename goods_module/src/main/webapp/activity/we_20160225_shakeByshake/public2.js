
var _domain = window.location.host;
var url = "";
//获得gameid
var _gameid = Number(location.search.split('gameid=')[1]);
//生产环境
var appid = "wxc29d38541362f295";
if(_domain == 'v.merryplus.com'){
	url = "http://v.merryplus.com/";
}
//测试环境
/*var appid = "wx07c01da2e6bcb01d";
var url = "";
if(_domain == 'www.coding.mobi'){
	url = "http://www.coding.mobi/EMIS/";
}*/
else
{
	url="http://192.168.2.77:8080/EMIS/"
}