$(document).ready(function() {

	//获取用户信息
	global_vueGetJson({
		url: workSpace.jsonUrl + '/merriplusApi/getMemberDetail',
		vueName: 'memberDetail',
		success: function() {
			//获取标签列表
			myTag_getTagList();
			//设置选中数组
			vm.$set('myTagSelected', vueData.memberDetail.data.tags ? vueData.memberDetail.data.tags.split(',') : []);
			setTimeout(function() {
				myTag_changeStyle();
				$("[name='myTagSelected']").parent('label').unbind().bind('click', function(e) {
					if(!vueData.memberDetail.data.mobilePhone) {
						e.preventDefault();
						global_checkMemberPhoneFinish();
					};
				});
				$("[name='myTagSelected']").change(function(e) {
					myTag_changeStyle();
					myTag_updataTagList();
				});
			}, 300);
		}
	})

});
//获取标签列表
function myTag_getTagList() {
	global_vueGetJson({
		url: workSpace.jsonUrl + 'dicGroupItem/service/findByDicGroupCode',
		data: {
			dicGroupCode: 'member_select_tags'
		},
		vueName: 'member_select_tags',
	});
};

function myTag_updataTagList() {
	buijs_mask({ type: 'loading' });
	global_vuePostJson({
		url: workSpace.jsonUrl + '/merriplusApi/updateOrAddMemberDetail',
		data: {
			tags: vueData.myTagSelected.join(',')
		},
		success: function(data) {
			buijs_mask_close();

			global_vueGetJson({
				url: workSpace.jsonUrl + '/merriplusApi/getMemberDetail',
				vueName: 'memberDetail',
			});
		}
	});
};

//改变选中样式
function myTag_changeStyle() {
	$("[name=myTagSelected]").map(function() {
		var nclass = 'bui_bdc_white_d64 bui_tc_white_d64',
			aclass = 'bui_bgc_red bui_tc_white';
		if($(this).prop('checked')) {
			$(this).parent('label').addClass(aclass).removeClass(nclass);

		} else {
			$(this).parent('label').addClass(nclass).removeClass(aclass);
		}
	})
};