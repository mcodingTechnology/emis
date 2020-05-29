<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <div class="col-md-12 ">
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-reorder"></i>
                    修改公告
                    <!-- <c:choose>
                        <c:when test="${editUser!=null}">修改公告</c:when>
                        <c:otherwise>增加产品类目</c:otherwise>
                    </c:choose> -->
                </div>
            </div>
            <div class="portlet-body form">
                <form action="#" id="informationForm" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="1028"/>
                    <div class="form-body">
                         <div class="form-group">
                            <label class="col-md-3 control-label">公告标题
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9"  >
                                <input name="title" type="text" id="title" placeholder="公告标题" class="form-control input-inline input-medium"/> 
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">公告内容
                                <span class="required">*</span>
                            </label>
                            <div class="col-md-9"  >
                                <textarea name="content" style="" id="content" placeholder="请输入公告内容" class="form-control  "></textarea>  
                            </div>
                        </div>

                       
        
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button id="singleAdd" type="button" class="btn purple">
                                <i class="fa fa-check"></i> 提 交
                            </button>
                             
                            <!-- <button id="backListPage" href="categoryList.html" type="button" class="btn default ajaxify">
                                返 回
                            </button> -->
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
 
<script type="text/javascript">
$.getFormData = function(form) {
        var unindexed_array = form.serializeArray();
        var indexed_array = {};
        for (var i = 0; i < unindexed_array.length; i++) {
            indexed_array[unindexed_array[i].name] = unindexed_array[i].value;
        }
        return indexed_array;
    }
var addInformation=function(){
    var initEvent=function(){
        $('#singleAdd').on('click',function(){
            saveInformation();
        })
        getInformation();
    }
    var getInformation=function(){
        $.getJSON("merriplusApi/getPublicInformation.html",function(rs){
            if(rs.status=="00"){
                $('#title').val(rs.data[0].title);
                $('#content').val(rs.data[0].content);
                $('#id').val(rs.data[0].id);
            }
        })
    }
    var saveInformation=function(){

       var informationForm = $("#informationForm");

            if(!informationForm.valid()){
                return;
            }
            var requestURL = "addNewPublicInformation.html";
            var tips = "增加失败!";
            if($("#id").val() != ''){
                requestURL = "addNewPublicInformation.html";
                tips = "修改失败!";
            }
            
            $.ajax({
                type: "POST",
                url: requestURL,
                data: JSON.stringify($.getFormData(informationForm)),
                dataType: "json",
                contentType:"application/json",
                success: function (data) {
                     if(data.status=="00"){
                       $("#backListPage").click();
                     }
                }
            });
    }
    return {
        init:initEvent,
        getInformation:getInformation(),
        saveInformation:saveInformation()
    }
}()
 
	addInformation.init();
    // imageUpload("imageButton","imageUrl","product");//
</script>
