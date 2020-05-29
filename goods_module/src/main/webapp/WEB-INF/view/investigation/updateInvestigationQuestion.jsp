<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
	<div class="col-md-12 ">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i> 修改调查题目
				</div>
			</div>
			<div class="portlet-body form">
				<form action="#" id="submitForm" class="form-horizontal">
					<input type="hidden" id="id" name="id" value="${iqo.question.id}" />
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-1 control-label">调查题目 
							<span class="required">*</span>
							</label>
							<div class="col-md-2">
								<input type="text" name="questiontitle" id="questiontitle"
									value="${iqo.question.question}" 
									class="form-control input-inline input-medium"
									placeholder="请输入调查题目">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-1 control-label">题目归属 <span
								class="required">*</span>
							</label>
							<div class="col-md-2">
								<select class="form-control input-medium select2me" name="questionflag">
									<option value="0" <c:if test="${iqo.question.questionflag==0}">selected</c:if>>A套题目</option>
									<option value="1" <c:if test="${iqo.question.questionflag==1}">selected</c:if>>B套题目</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-1 control-label">题目类型 <span
								class="required">*</span>
							</label>
							<div class="col-md-4">
								<select class="form-control input-medium select2me" name="radio">
									<option value="0" <c:if test="${iqo.question.questiontype==0}">selected</c:if>>单选</option>
									<option value="1" <c:if test="${iqo.question.questiontype==1}">selected</c:if>>多选</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-1 control-label">题目说明&nbsp<span>*</span></label>
							<div class="col-md-2">
								<input type="text" name="description"
									value="${iqo.question.description}"
									class="form-control input-inline input-medium"
									placeholder="请输入题目说明">
							</div>
						</div>

						<c:forEach items="${iqo.options}" var="opt" varStatus="status">
							<div class="form-group">
								<input type="hidden" id="optionId" name="optionId" value="${opt.id}" />
								<label class="col-md-1 control-label">调查选项${status.count}</label>
								<div class="col-md-2">
									<input type="text" name="option" value="${opt.optioncontent}"
										class="form-control input-inline input-medium"
										placeholder="请输入调查选项1"/>
								</div>
								<label class="col-md-2 control-label">选项${status.count}关键词</label>
								<div class="col-md-3">
									<input type="text" name="key" value="${opt.mark}"
										class="form-control input-inline input-medium"
										placeholder="请输入对应的关键词">
								</div>
								<div class="col-md-2">
									<button type="button" class="btn btn-success" name="optionId" value="${opt.id}" id="childButton${status.count}">
										<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
											编辑选项问题
									</button>
								</div>
							</div>
							<div id="childQuestionId${status.count}" style="display:none">
  					    	    <div class="form-group">
  					    	    	<input type="hidden" name="poptionId" value="${opt.id}" />
  					    	    	<input type="hidden" name="cquestionId" value="${child[status.count-1].question.id}" />
					    		    <label class="col-md-2 control-label">跳转题目</label>  
					    		    <div class="col-md-3">
					    			    <input type="text" name="cquestion" value="${child[status.count-1].question.question}"
					    				    class="form-control input-inline input-medium" placeholder="请输入子题目">    
					    		    </div>    
					    	    </div>
						    	<div class="form-group">
						    		<input type="hidden" name="coption1Id" value="${child[status.count-1].options[0].id}" />
						  			<label class="col-md-2 control-label">调查选项1</label>
						  		  	<div class="col-md-2">
					  					  <input type="text" name="coption1" value="${child[status.count-1].options[0].optioncontent}"
					  						  class="form-control input-inline input-medium" placeholder="请输入调查选项1">  
						  		  	</div>  
						  		  	<label class="col-md-2 control-label">选项1关键词</label>
						  		  	<div class="col-md-3">  
					  				 	 <input type="text" name="ckey1"  value="${child[status.count-1].options[0].mark}"
					  						  class="form-control input-inline input-medium" placeholder="请输入对应的关键词">
					  		 		</div>
					  			</div>
					  			<div class="form-group">
					  				<input type="hidden" name="coption2Id" value="${child[status.count-1].options[1].id}" />
					  		  		<label class="col-md-2 control-label">调查选项2</label>  
					  		  		<div class="col-md-2">  
					  			  		<input type="text" name="coption2" value="${child[status.count-1].options[1].optioncontent}"
					  				  		class="form-control input-inline input-medium" placeholder="请输入调查选项2">  
					  		  		</div>  
					  		  		<label class="col-md-2 control-label">选项2关键词</label>  
					  		  		<div class="col-md-3">  
					  			  		<input type="text" name="ckey2"  value="${child[status.count-1].options[1].mark}"
					  				  		class="form-control input-inline input-medium" placeholder="请输入对应的关键词">  
					  		  		</div>
	                  	  		</div>  
	                  	 		<div class="form-group">
	                  	 			<input type="hidden" name="coption3Id" value="${child[status.count-1].options[2].id}" />
					  		  		<label class="col-md-2 control-label">调查选项3</label>  
					  		  		<div class="col-md-2">  
					  			  		<input type="text" name="coption3" value="${child[status.count-1].options[2].optioncontent}"
					  				  		class="form-control input-inline input-medium" placeholder="请输入调查选项3">  
					  		  		</div>
					  		  		<label class="col-md-2 control-label">选项3关键词</label>  
					  		  		<div class="col-md-3">  
					  			  		<input type="text" name="ckey3"  value="${child[status.count-1].options[2].mark}"
					  				  		class="form-control input-inline input-medium" placeholder="请输入对应的关键词">  
					  		  		</div>  
	                  	  		</div>  
					  		</div>
						</c:forEach>
						<div id="optDiv"></div>
						<div class="row">
							<div class="col-md-4 col-md-offset-8">
								<button id="moreOption" type="button" class="btn btn-danger">
									<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
										新增加选项
								</button>
							</div>
						</div>

						<div class="form-actions fluid">
							<div class="col-md-offset-3 col-md-9">
								<button id="singleAdd" type="button" class="btn purple">
									<i class="fa fa-check"></i> 提 交
								</button>
								<button id="backListPage" href="queryInvestigationPageView.html"
									type="button" class="btn default ajaxify">返 回</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script
	src="${basePath}resources/js/custom/investigation/updateInvestigationQuestion.js"
	type="text/javascript">
</script>
<script type="text/javascript">
	game_addGameQuestionManager.init();
</script>
