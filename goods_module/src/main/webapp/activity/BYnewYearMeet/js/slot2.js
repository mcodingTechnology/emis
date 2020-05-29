requirejs(['jquery','api','common','jSlots'],function($,api,common){
    //插入人员列表
    function list(){
        //有部门信息 '<li class="no"><div class="img"><img src="file/photo.png"></div> <div class="branch">???</div> <div class="name">?????</div> </li>'
        var html = '<li class="no"><div class="img"><img src="file/photo.png"></div> <div class="branch" style="padding-bottom:30px"></div> <div class="name">?????</div> </li>'
        $.each(common.shuffle(20),function(i,n){
            html += '<li> <div class="img"><img src="'+((n.picUrl=="N/A" ||n.picUrl=="") ? "file/photo.png" : n.picUrl)+'"></div> <div class="branch" style="padding-bottom:30px"></div> <div class="name">'+n.memberName+'</div> </li>';
        })
        return html;
    }
    $('#slot ul').html(list());
    var htmlArr = [];
    for(var i=0;i<3;i++){
        htmlArr.push('<ul style="position: relative;margin: 0 66px;">'+list()+'</ul>')
    }
    //初始化拉霸
    $('#slot ul').jSlots({
        spinner : '.hd',
        url:api.lottery_second,
        time:1000,
        number:3,   //生成多少个拉霸实例
        html:htmlArr,    //每组实例对应的滚动html代码
        loops:1,
        level:2        //抽奖等级
    });

    // var wrap= $('.jSlots-wrapper')
    // function jSlotGame(){
    //     if(wrap.scrollLeft()<=0)
    //         wrap.scrollLeft($("#box1").width()); 
    //     else{
    //         wrap.scrollLeft(wrap.scrollLeft()-5);
    //     }
    // }

    common.keyup(function(){
        $('.hd').click();
    })

})