$(document).ready(function() {
	$('#searchButton').val(search);

	getHistotyList();
});

function getHistotyList() {
	//搜索历史加载
	var content = '';
	if (window.localStorage.getItem("searchHistory")) {
		var searchHistory = window.localStorage.getItem("searchHistory").split(",");
		searchHistory.forEach(function(ele, index) {
			content += '<a href="javascript:;" class="bui_block bui_ptb_12 bui_plr_24 bui_media bui_vm">' +
				'<div class="bui_media_b">' + ele + '</div>' +
				'<div class="bui_media_r bui_fac_lgray"><i class="fa fa-angle-right"></i>' +
				'</div>' +
				'</a>' +
				'<hr />';
		});

	}

	$('#history').html(content);
	clearHistory();
	clickHistory();
}

function clearHistory() {
	$('.clear_history').on({
		'touchend': function() {
			window.localStorage.removeItem("searchHistory");
			getHistotyList();
		}
	})
}

function clickHistory() {
	$('#history a').on({
		'touchstart': function() {
			var searchButton = escape($(this).children('.bui_media_b').text());
			window.location.href = 'product_list.html?productName=' + searchButton;
			return false;
		}
	})
}
$('.search_commit').on({
	'touchend': function() {
		var searchButton = escape($('#searchButton').val());
		var searchHistory;
		if (window.localStorage.getItem("searchHistory")) {
			searchHistory = window.localStorage.getItem("searchHistory").split(",");

		} else {
			searchHistory = new Array();
		}
		var flag = true;
		searchHistory.forEach(function(ele, index) {
			if (ele == $('#searchButton').val()) {
				flag = false;
			}
		});
		if (flag) {
			searchHistory.push($('#searchButton').val());
		}

		window.localStorage.setItem("searchHistory", searchHistory);
		window.location.href = 'product_list.html?productName=' + searchButton;
	}
})