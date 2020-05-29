/**
 * 查询最后一次积分产品，是不是蛋白质粉   by Gzr
 */
var $= require('jquery');

module.exports= {
	getData: function (prams) {
		var dtd= $.Deferred();
		$.ajax({
			type:'get',
			url: _jsonUrl+ '/merriplusApi/getLastProduct',
			dataType: 'json',
			success: function (data) {
				dtd.resolve(data);
			}
		});
		return dtd.promise();
	}
}