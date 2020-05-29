$(function(){
	readInformation();
})
function readInformation(){

	$.ajax({
		type:"get",
		url:  _jsonUrl + "/merriplusApi/hasNewInformation",
		async: true,
		global: false,
		dataType: "json",
		success: function(rs) {
			var data=rs.data;
			if (data) {
				$('.NewsredDot').show();
			} else{
				$('.NewsredDot').hide();
			}

		}
	});
}



