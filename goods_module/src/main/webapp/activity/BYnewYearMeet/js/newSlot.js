requirejs(['jquery','api','common','jSlots'],function($,api,common){
    var level = common.getUrlString('level') || 3;
    var slotNums= common.getUrlString('num') || 8;
    var stopCtrl= false;                         //true禁止操作

    // initList();

    // //刷出已抽中的名单
    // function initList(){
    //     var url= api.url+'/front/yearParty/Winning.html?level='+level;
    //     $.ajax({
    //         type: 'get',
    //         url :  url,
    //         dataType : 'json',
    //         success : function (rs){
    //             if (rs.code == '0' && rs.data.memberList.length) {
    //                 rs.data.memberList.map(function(data,index){
    //                     $('.so').eq(0).children('.name').html(data.name);
    //                     $('.so').eq(0).children('img').attr('src',data.headImgUrl || 'file/photo.png');
    //                     $('.so').eq(0).removeClass('so');
    //                 })
    //             }
    //         }
    //     })
    // }

    function slotFun(num){
        if (stopCtrl == true) return ;
        var url=api.url+'/front/yearParty/lottery.html?level='+level+'&num='+slotNums;
        stopCtrl = true;
        $.ajax({
            type:'get',
            url:url,
            // url:"js/newSlot.json",
            dataType:'json',
            success:function(rs){
                if (rs.code == '0') {
                    var amount = rs.data.memberList.length;
                    setLoad(0,amount,rs.data.memberList);
                }else {
                    alert(rs.msg)
                }
                
            },
            error:function(){
                stopCtrl= false;
            }
        })
    }

    function setLoad(num,length,data){
        if (num>=length) {
            stopCtrl= false;
            return $('.so:lt('+length+')').removeClass('so');
        };
        $('.so').eq(num).css('transform','rotateY(90deg)');
        setTimeout(function(){
            $('.so').eq(num).children('.name').html(data[num].name);
            $('.so').eq(num).children('.img').children('img').attr('src',data[num].headImgUrl || 'file/photo.png')
            $('.so').eq(num).css('transform','rotateY(0deg)');
        },200)
        setTimeout(setLoad.bind(this,num+1,length,data),400)
    }

    common.keyup(function(){
        slotFun()
    })

})