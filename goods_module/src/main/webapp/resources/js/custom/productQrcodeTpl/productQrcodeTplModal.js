(function($) {
	var productQrcdeTpl = function(options){
		debugger;
		var defaultOptions = {
		    elementId : 'productQrcdeTplModal',
		    productId : null,
		};
		options = $.extend({}, defaultOptions, options);
		
		if(!verifyOption(options)){
			return;
		}
		
		var modal = createModal({
			elementId : options.elementId, 
			productId : options.productId
		});
		modal.show();
	};
	
	var verifyOption = function(options){
        if(!options.productId || options.productId == 0){
        	bootbox.alert('产品id不争取');
			return false;
		}
        
		var element = $('#' + options.elementId);
		if(!element){
			bootbox.alert('模态框id不正确');
			return false;
		}
		
		return true;
	};
	
	var createModal = function(options){
		var elementId = options.elementId;
		var productId = options.productId;
		var imageList = options.imageList;
		
		var element = $('#' + elementId);
		element.empty();
		
		
		var mainFrame = createMainFrame(productId);
		element.append(mainFrame);
		
		loadFooterButton(productId);
		
		var modalId = 'productQrcdeTplModal_' + productId;
		return $('#'+modalId).modal();
	};
	
	var createMainFrame = function(productId){
		var html = '<div id="productQrcdeTplModal_'+productId+'" class="modal fade" role="dialog" aria-hidden="true">' +
			           '<div class="modal-dialog">' +
				           '<div class="modal-content">' +
				               '<div class="modal-header">' +
							       '<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>' +
							       '<h4 class="modal-title">海报列表</h4>' +
						       '</div>' +
						       '<div class="modal-body form">' +
					               '<form action="#" class="form-horizontal form-row-seperated" id="productQrcdeTplModal_form'+productId+'">'+
					                   '<div class="form-group" id="formGroupUploadImage">' +
									       '<label class="col-md-3 control-label">上传的图片<span class="required">*</span></label>' +
								           '<div class="col-md-4"><div class="input-group">' +
								               '<input type="text" name="imgUrl" value=""'+
											     'class="form-control input-inline input-medium" placeholder="选择图片 "'+
											     'readonly="readonly" />'+ 
									           '<span class="input-group-btn"><button class="btn blue" id="uploadImgBtn" type="button">添加图片</button></span>'+
								            '</div></div>'+
							           '</div>'+
					               '</form>' +
					           '</div>' +
					           '<div class="modal-footer" id="productQrcdeTplModal_footer"'+productId+' >' +
					               '<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>' +
					               '<button type="button" class="btn btn-primary"><i class="fa fa-check"></i>保存</button>' +
						       '</div>'
			               '</div>' +
				       '</div>' +
				    '</div>';
		return html;
	};
	
	var createImageItem = function(tpl){
		var html = '<div class="form-group">' + 
                       '<label class="col-md-3 control-label">上传的图片<span class="required">*</span></label>' +
                       '<div class="col-md-4"><div class="input-group">' +
                           '<input type="text" name="postImg" value="'+ imageUrl+'" class="form-control input-inline input-medium" placeholder="选择图片 " readonly="readonly" />' + 
                           '<span class="input-group-btn">' +
                               '<button class="btn green" data-toggle="modal" href="#postImage_'+id+'"  type="button">预览图片</button>' +
                               '<button class="btn red" type="button">删除图片</button>' +
                           '</span>' +
                   '</div></div>' +
                   '<div id="postImage_'+id+'" class="modal fade modal-scroll" tabindex="-1" data-replace="true">' +
		               '<div class="modal-dialog"><div class="modal-content">' +
		                   '<div class="modal-body"><img src="'+basePath + url +'"></div>' +
		                   '<div class="modal-footer"><button type="button" data-dismiss="modal" class="btn">关闭</button></div>'+
		               '</div></div>'+
	                '</div>';
		return html;
	};
	
	var ajaxProductQrcodeTplList = function(productId, cb){
		
	};
	
	$.extend({
		productQrcdeTpl : productQrcdeTpl
	});
	
})(jQuery);