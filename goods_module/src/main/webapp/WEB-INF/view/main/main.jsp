<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>
<head>
<link href="${basePath}resources/css/ranking.css" rel="stylesheet"
	type="text/css" />
</head>
<!-- 首页主体部分-->
<!-- BEGIN DASHBOARD STATS -->
<div class="row">
	<div class="col-md-12">
		<h3 class="page-title">
			首页 <small>欢迎来到轻奢时光</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="index.html"> 首页 </a> <i
				class="fa fa-angle-right"></i></li>
		</ul>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<xingrun:accessRight menuCode="index" operCode="dataView">
			<div class="row">
				<input type="hidden" id="userId" value="${userId}" />
				<%-- <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<div class="dashboard-stat green">
				<div class="visual">
					<i class="fa fa-user"></i>
				</div>
				<div class="details">
					<div class="number">
						 ${memberNum}
					</div>
					<div class="desc">
						有${memberNum}个会员
					</div>
				</div>
				<a class="more ajaxify" href="memberListPageView.html">
					 查看详情 <i class="m-icon-swapright m-icon-white"></i>
				</a>
			</div>
		</div> --%>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat blue">
						<div class="visual">
							<i class="fa fa-shopping-cart"></i>
						</div>
						<div class="details">
							<div class="number">${orderNum}</div>
							<div class="desc">全部订单</div>
						</div>
						<a class="more ajaxify" href="orderManager.html"> 查看详情 <i
							class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat yellow">
						<div class="visual">
							<i class="fa fa-tasks"></i>
						</div>
						<div class="details">
							<div class="number">
								<input type="hidden" id="tasklnum" value="${productNum}" />
								${productNum}
							</div>
							<div class="desc">全部产品</div>
						</div>
						<a class="more ajaxify" href="productList.html"> 查看详情 <i
							class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
			</div>
		</xingrun:accessRight>
	</div>
	<div class="col-md-6">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-cogs"></i>数据统计
				</div>
			</div>
			<div class="portlet-body" id="blockui_sample_1_portlet_body">
				<div class="row static-info">
					<div class="col-md-7 name">微商城已支付订单数:</div>
					<div class="col-md-5 value" id="sum0"></div>
				</div>
				<!-- <div class="row static-info">
					<div class="col-md-7 name">积分商城已兑换订单数:</div>
					<div class="col-md-5 value" id="sum1"></div>
				</div>
				<div class="row static-info">
					<div class="col-md-7 name">翻牌游戏已领取奖品数:</div>
					<div class="col-md-5 value" id="sum2"></div>
				</div> -->
			</div>
		</div>
	</div>
	<div class="col-md-6"></div>
</div>

<script src="${basePath}resources/js/main/main.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script type="text/javascript">
	//测试appid
	var appid = "wx07c01da2e6bcb01d";
	//生产环境公众号appid
    //var appid = "wxc29d38541362f295";
	mainList.init();
	/* $("#myChats").slimScroll({
		start: "buttom",
        allowPageScroll: true, 
        size: '7px',
        color: ($(this).attr("data-handle-color")  ? $(this).attr("data-handle-color") : '#bbb'),
        railColor: ($(this).attr("data-rail-color")  ? $(this).attr("data-rail-color") : '#eaeaea'),
        position: isRTL ? 'left' : 'right',
        height: height,
        alwaysVisible: ($(this).attr("data-always-visible") == "1" ? true : false),
        railVisible: ($(this).attr("data-rail-visible") == "1" ? true : false),
        disableFadeOut: true
    }); */
</script>