define(['jquery','list'],function($,list){
    //ajax错误处理
    $(document).ajaxStart(function () {
        //$('#ajaxBusy').show();
    }).ajaxStop(function () {
        //$('#ajaxBusy').hide();
    }).ajaxComplete(function(xhr,status){
        if(status.status==403){
            console.log('授权失败！（错误代码：403）');
        }
        if(status.status==404){
            console.log('接口错误（代码：404），请联系管理员...');
        }
        if(status.status==500){
            console.log('服务器出现错误（代码：500），请重试...');
        }
        if(status.status==504){
            console.log('请求超时（代码：504），请重试...');
        }
    });
    //乱序函数
    var shuffle = function(o){
		for(var j, x, i = o.length; i; j = parseInt(Math.random() * i), x = o[--i], o[i] = o[j], o[j] = x);
		return o;
	};

    //获取URL参数
    function getUrlString(name) {
        var reg = new RegExp("(^|&)"+ name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg); //匹配目标参数
        if (r != null){
            return unescape(r[2]);
        }
        return ''; //返回参数值
    }

    //去除括号里的数据
    var filter = function(txt){
        var txt = txt.replace(/（/, "(");
        return txt.split('(')[0];
    }

    //去除人名括号后的列表
    var list2 = [];
    $.each(list,function(i,n){
        n.memberName = filter(n.memberName);
        list2.push(n);
    })


    
    var common = {

        //获取指定个数的人员列表（乱序）
        shuffle:function(i){
            if(i){
                return shuffle(list2).slice(0,i);
            }else{
                return shuffle(list2);
            }
        },

        getUrlString:function(name){
            return getUrlString(name);
        },
        //获取部门人员列表
        branch:function(key,key2){
            var _this = this;
            var arr = [];
            $.each(list2,function(i,n){
                if(n.departmentName==key){
                    n.memberName = filter(n.memberName);
                    arr.push(n);
                }
            })
            if(key2){
                $.each(list2,function(i,n){
                    if(n.departmentName==key2){
                        n.memberName = filter(n.memberName);
                        arr.push(n);
                    }
                })
            }
            return arr;
        },
        //去除人名括号
        filter : function(txt){
            return filter(txt);
        },
        delay:false,    //按键是否延迟中
        //键盘事件
        keyup : function(cb){
            $('body').off('keyup.k').on('keyup.k',function(e){
                if(common.delay) return console.log('1秒延迟中~') || false;
                
                if(e.keyCode==8||e.keyCode==27){
                    history.back();
                }
                if(e.keyCode==13){
                    common.delay = true;
                    setTimeout(function(){
                        common.delay = false;
                    },1000);
                    
                    //如果有全屏显示奖品界面，则隐藏该界面
                    if($('#showPrize:visible').length>0){
                        $('#showPrize').addClass('animated rollOut');
                        setTimeout(function(){
                            $('#showPrize').hide().removeClass('animated rollOut');
                        },1000);
                        // return;
                    }else{
                        if(cb){
                            cb();
                        }
                    }
                    
                }
            })
        }
    }
    return common;
});