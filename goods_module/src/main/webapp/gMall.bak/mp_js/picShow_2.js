$(function(){

	
})

function lookPic(){
	var hr=  location.href;
	wx.previewImage({
	    current: hr, // 当前显示图片的http链接
	    urls: [_jsonUrl+'/gMall/images/picShow_2.jpg'] // 需要预览的图片http链接列表
	});
}