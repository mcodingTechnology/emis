/**
 * 更新会员信息接口   by Gzr
 * brandCode       //公用brandCode
 * mobilePhone	   //手机号码
 * smsCode    	   //短信验证码
 * fullName
 * gender
 * birthday
 * ext1
 * ext2
 */
var $= require('jquery');

module.exports= {
	getData: function (prams) {
		var dtd= $.Deferred();
		var data= {
				brandCode: mp_brandCode
			};
		var _data= $.extend(data,prams);
		$.ajax({
			type:'post',
			url: _jsonUrl+ '/merriplusApi/updateMemberWithSMSCode',
			dataType: 'json',
			data: data,
			success: function (data) {
				dtd.resolve(data);
			}
		});
		return dtd.promise();
	}
}