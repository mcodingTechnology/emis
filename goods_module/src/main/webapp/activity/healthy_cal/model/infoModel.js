var $= require('jquery');

module.exports = {

	default: {
		data: null
	},

	getData: function () {
		var dtd= $.Deferred();

		if (this.data) {
			dtd.resolve(this.data)
		}else {
			$.ajax({
				type: 'get',
				url: _jsonUrl+'/merriplusApi/getMemberDetail',
				dataType: 'json',
				success: function (data){ 
					this.data = data;
					dtd.resolve(data);
				}
			})
		}
		
		return dtd.promise();

	}
}