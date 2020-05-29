requirejs(['jquery','api','common'],function($,api,common){
	/*var gui = require('nw.gui');
    var win = gui.Window.get();
    $('body').off('keyup').on('keyup',function(e){
        if(e.keyCode==27){
            win.close();
        }
    })*/
    $('.page a').focus(function(){
        $(this).addClass('cur').siblings().removeClass('cur')
    })
    $('.page a').eq(0).focus();

    //缓存头像
    function list(){
		var html = ''
		$.each(common.shuffle(),function(i,n){
			html += '<li> <div class="img"><img src="'+((n.picUrl=="N/A" ||n.picUrl=="") ? "file/photo.png" : n.picUrl)+'"></div> <div class="branch">'+n.departmentName+'</div> <div class="name">'+n.memberName+'</div> </li>';
			//html += '<li><div class="name">'+n.memberName+'</div> </li>';
		})
		return html;
	}
	$('#list').html(list())
	
});
