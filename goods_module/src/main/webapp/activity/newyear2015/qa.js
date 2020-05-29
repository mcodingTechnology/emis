//弹窗
function fanjs_modal(options) {
    var fanjs_modal_on
    var defaults = {
        positions: 'auto',
        ajaxload: null,
        footer: '',
        width: '320px',
        height: 'auto',
        opened: function() {},
        closed: function() {}
    };
    var setting = $.extend(defaults, options);
    if (setting.positions == 'auto') {
        if ($('body').hasClass('bui_body_lg') || $('body').hasClass('bui_body_md')) {
            setting.positions = 'center'
        }
        if ($('body').hasClass('bui_body_sm')) {
            setting.positions = 'top'
        }
    };
    fanjs_modal_on = true;
    fanjs_modal_content();

    $(".fan_modal").show(0, function() {
        //fanjs_modal_resize();
        $(this).addClass('active');
        setTimeout(function() {
            $(".bui_modal_b").buijs_scrollbar();
        }, 300)
    });
    //插入内容
    function fanjs_modal_content() {
        //判断遮罩层是否存在
        if ($(".bui_modal_mask").length == 0) {
            $("body").append('<div class="bui_modal_mask bui_bgc_black_f72"></div>');
            $('.bui_modal_mask').fadeIn(100);
        }
        //判断模拟弹窗是否存在
        if ($(".fan_modal").length == 0) {
            $('body').append(
                '<div class="fan_modal ' + setting.positions + '" style="width:' + setting.width + '; top:56px; left:50%;margin-left:-148px; border-radius: 12px;">' +
                '<div class="bui_modal_b bui_p_12">' + setting.content + '</div>' +
                '<div class="bui_modal_f">' + setting.footer + '</div>' +
                '</div>');
        } else {
            $(".fan_modal").attr('class', '.fan_modal ' + setting.positions).width(setting.width);
            $(".bui_modal_h span").html(setting.title);
            $(".bui_modal_f").html(setting.footer);
        };
        //内容为字符串
        if (setting.content != null) {
            $(".bui_modal_b").html(setting.content);
        };
        //内容为ajax
        if (setting.ajaxload != null) {
            $('.fan_modal').attr('bui_modal_url', setting.ajaxload);
            $.ajax({
                type: "get",
                url: setting.ajaxload,
                async: true,
                success: function(data) {
                    $('.bui_modal_b').html(data);
                    return false;
                },
                error: function(data) {
                    $('.bui_modal_b').html('<div class="bui_ta_c bui_tc_o24 bui_ptb_32"><h1 class="bui_btn_48 bui_btnsq bui_ts_32 bui_bgc_black bui_round">!</h1><p class="bui_mt_12">连接错误，请检测您的网络设置</p></div>');
                    $('.bui_modal_f').html('');
                    return false;
                }
            });
        };
    };
}(jQuery);
function year_startQuestings() {
    $("#home").attr('style', 'display:none');
    $("#question-pages").attr('style', 'display:block');
    $("#question1").attr('style', 'display:block');
}
function year_Question3() {
    $(".result").attr('style', 'display:none');
    $(".result ul li").attr('style', 'display:none');
    $("#question-pages").attr('style', 'display:block');
    $("#question3").attr('style', 'display:block');
}
function year_Question4() {
    $(".result").attr('style', 'display:none');
    $(".result ul li").attr('style', 'display:none');
    $("#question-pages").attr('style', 'display:block');
    $("#question4").attr('style', 'display:block');
}
function year_Question5() {
    $(".result").attr('style', 'display:none');
    $(".result ul li").attr('style', 'display:none');
    $("#question-pages").attr('style', 'display:block');
    $("#question5").attr('style', 'display:block');
}
function year_resultPage1A(){
    $("#question-pages").attr('style', 'display:none');
    $("#question1").attr('style', 'display:none');
    $("#result-pages1").attr('style', 'display:block');
    $(".question1-resulta").attr('style', 'display:block');
}
function year_resultPage1B(){
    $("#question-pages").attr('style', 'display:none');
    $("#question1").attr('style', 'display:none');
    $("#result-pages1").attr('style', 'display:block');;
    $(".question1-resultb").attr('style', 'display:block');
}
function year_resultPage1C(){
    $("#question-pages").attr('style', 'display:none');
    $("#question1").attr('style', 'display:none');
    $("#result-pages1").attr('style', 'display:block');
    $(".question1-resultc").attr('style', 'display:block');
}
function year_resultPage2A(){
    $("#question-pages").attr('style', 'display:none');
    $("#question2").attr('style', 'display:none');
    $("#result-pages2").attr('style', 'display:block');
    $(".question2-resulta").attr('style', 'display:block');
}
function year_resultPage2B(){
    $("#question-pages").attr('style', 'display:none');
    $("#question2").attr('style', 'display:none');
    $("#result-pages2").attr('style', 'display:block');
    $(".question2-resultb").attr('style', 'display:block');
}
function year_resultPage2C(){
    $("#question-pages").attr('style', 'display:none');
    $("#question2").attr('style', 'display:none');
    $("#result-pages2").attr('style', 'display:block');
    $(".question2-resultc").attr('style', 'display:block');
}
function year_resultPage3A(){
    $("#question-pages").attr('style', 'display:none');
    $("#question3").attr('style', 'display:none');
    $("#result-pages3").attr('style', 'display:block');
    $(".question3-resulta").attr('style', 'display:block');
}
function year_resultPage3B(){
    $("#question-pages").attr('style', 'display:none');
    $("#question3").attr('style', 'display:none');
    $("#result-pages3").attr('style', 'display:block');
    $(".question3-resultb").attr('style', 'display:block');
}
function year_resultPage3C(){
    $("#question-pages").attr('style', 'display:none');
    $("#question3").attr('style', 'display:none');
    $("#result-pages3").attr('style', 'display:block');
    $(".question3-resultc").attr('style', 'display:block');
}
function year_resultPage4A(){
    $("#question-pages").attr('style', 'display:none');
    $("#question4").attr('style', 'display:none');
    $("#result-pages4").attr('style', 'display:block');
    $(".question4-resulta").attr('style', 'display:block');
}
function year_resultPage4B(){
    $("#question-pages").attr('style', 'display:none');
    $("#question4").attr('style', 'display:none');
    $("#result-pages4").attr('style', 'display:block');
    $(".question4-resultb").attr('style', 'display:block');
}
function year_resultPage4C(){
    $("#question-pages").attr('style', 'display:none');
    $("#question4").attr('style', 'display:none');
    $("#result-pages4").attr('style', 'display:block');
    $(".question4-resultc").attr('style', 'display:block');
}
function year_resultPage5A(){
    $("#question-pages").attr('style', 'display:none');
    $("#question5").attr('style', 'display:none');
    $("#result-pages5").attr('style', 'display:block');
    $(".question5-resulta").attr('style', 'display:block');
}
function year_resultPage5B(){
    $("#question-pages").attr('style', 'display:none');
    $("#question5").attr('style', 'display:none');
    $("#result-pages5").attr('style', 'display:block');
    $(".question5-resultb").attr('style', 'display:block');
}
function year_resultPage5C(){
    $("#question-pages").attr('style', 'display:none');
    $("#question5").attr('style', 'display:none');
    $("#result-pages5").attr('style', 'display:block');
    $(".question5-resultc").attr('style', 'display:block');
}