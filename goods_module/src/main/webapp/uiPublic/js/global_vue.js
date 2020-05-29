function global_vue(options) {
	var defaults = {
		el: 'html',
		data: {},
		components: {},
		methods: {
			//时间戳
			unixTimeChange: function(dateTime, format) {
				var date = new Date(dateTime);
				var year = date.getFullYear(); //获取年
				var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1; //获取月
				var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate(); //获取日
				var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours(); //获取时
				var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes(); //获取分
				var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds(); //获取秒

				if(format == 'time') {
					return year + '-' + month + '-' + currentDate + ' ' + hours + ':' + minute + ':' + seconds;
				} else {
					return year + "-" + month + "-" + currentDate;
				};
			}
		},
		success: function() {}
	};
	var setObj = $.extend(defaults, options);
	//绑定vue
	vm = new Vue({
		el: setObj.el,
		data: setObj.data,
		methods: setObj.methods,
		components: setObj.components
	});

	//过滤器-数据类型
	Vue.filter('typeof', function(value) {
		return typeof(value);
	});
	setTimeout(function() {
		setObj.success()
	}, 300)
};
//vueAjaxGet
function global_getJsonSetVue(url, setVueName, getData, callback) {
	$.ajax({
		type: "get",
		url: url,
		async: true,
		dataType: 'json',
		data: getData ? getData : null,
		beforeSend: function() {},
		complete: function() {},
		error: function(data) {},
		success: function(data) {
			vm.$set(setVueName, data);
			callback ? callback(data) : null;
		}
	});
};
//vueAjaxPost
function global_getVueSetJson(url, vueData, key, callback) {
	var postData = {};
	if(!key) {
		postData = vueData || null
	} else {
		var vueDataParse = typeof(vueData) == 'string' ? JSON.parse(vueData) : vueData;
		$.map(key, function(key) {
			postData[key] = vueDataParse && vueDataParse[key] ? vueDataParse[key] : null
		});
		postData = typeof(vueData) == 'string' ? JSON.stringify(postData) : postData;
	};
	var ajaxData = $.extend({
		type: "post",
		url: url,
		async: true,
		dataType: 'json',
		data: postData,
		success: function(data) {
			callback ? callback(data) : null;
		}
	}, typeof(vueData) == 'string' ? {
		contentType: "application/json;charset=UTF-8"
	} : null);
	$.ajax(ajaxData);
};

//vueAjaxGet new
function global_vueGetJson(options) {
	var defaults = {
		vueName: null,
		url: '',
		data: {},
		beforeSend: function(data) {},
		complete: function(data) {},
		success: function(data) {},
		error: function(data) {},
		localStorage: false,
	}
	var setObj = $.extend(defaults, options);
	//使用缓存
	if(setObj.localStorage && setObj.vueName) {
		if(window.localStorage.getItem(setObj.vueName)) {
			vm.$set(setObj.vueName, JSON.parse(window.localStorage.getItem(setObj.vueName)));
			setObj.success(JSON.parse(window.localStorage.getItem(setObj.vueName)));
		} else {
			getUrl();
		}
	} else {
		getUrl();
	}

	function getUrl() {
		$.ajax({
			type: "get",
			async: true,
			url: setObj.url,
			dataType: 'json',
			data: setObj.data,
			beforeSend: function(data) {
				setObj.beforeSend(data);
			},
			complete: function(data) {
				setObj.complete(data);
			},
			success: function(data) {
				if(setObj.vueName) {
					vm.$set(setObj.vueName, data);
				};
				if(setObj.localStorage) {
					window.localStorage.setItem(setObj.vueName, JSON.stringify(data));
				};
				setObj.success(data);
			},
			error: function(data) {
				setObj.error(data);
			},
		});
	}
};

function global_vuePostJson(options) {
	var defaults = {
		vueName: null,
		url: '',
		data: null,
		beforeSend: function(data) {},
		complete: function(data) {},
		success: function(data) {},
		error: function(data) {},
		localStorage: false,
	}
	var setObj = $.extend(defaults, options);
	$.ajax({
		type: "post",
		url: setObj.url,
		async: true,
		dataType: 'json',
		contentType: 'application/json;charset=UTF-8',
		data: typeof(setObj.data) == 'string' ? setObj.data : JSON.stringify(setObj.data),
		beforeSend: function(data) {
			setObj.beforeSend(data);
		},
		complete: function(data) {
			setObj.complete(data);
		},
		success: function(data) {
			if(setObj.vueName) {
				vm.$set(setObj.vueName, data);
			};
			if(setObj.localStorage) {
				window.localStorage.setItem(setObj.vueName, JSON.stringify(data));
			};
			setObj.success(data);
		},
		error: function(data) {
			setObj.error(data);
		},

	});

}