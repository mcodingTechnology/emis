<%@ page import="com.xingrun.pms.common.util.XRContextUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="format-detection" content="telephone=no" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>积分明细</title>
		<link rel="stylesheet" href="<%= XRContextUtil.getBaseRootPath()%>resources/css/mrmjWeChat.css" />
			<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/metronic_v2.0.2/assets/plugins/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/js/front/wechat/iscroll.js"></script>
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/js/front/wechat/mrmjUtil.js"></script>
		<script type="text/javascript">

			var myScroll,
				pullDownEl, pullDownOffset,
				pullUpEl, pullUpOffset,
				generatedCount = 0;

			var counter = 3; // 下拉模拟的次数

			// 上拉加载更多数据
			function pullUpAction () {
				// 用 ajax 取代 setTimeout
				setTimeout(function () {	// <-- Simulate network congestion, remove setTimeout from production!
					//console.log('pullUpAction  setTimeout');
					
					//console.log('counter=='+counter);
					if(counter <= 0){
						// 没有数据了
						myScroll.refresh();
						$('#id_integrationDetails_no_data').removeClass('cls_hidden');
						$('#id_integrationDetails_more').addClass('cls_hidden');
						$('#pullUp').css({'display':'none'});
						return;
					}

					counter--;
					var tempStr = '';

					// 模拟添加3条记录
					for (var i=0; i<3; i++) {
						tempStr += '<li>';
						tempStr += '	<div>';
						tempStr += '		<div>2014-04-03</div>';
						tempStr += '		<div>09:18:12</div>';
						tempStr += '	</div>';
						tempStr += '	<div class="cls_point"></div>';
						tempStr += '	<div class="cls_left_arrow"></div>';
						tempStr += '	<div>';
						tempStr += '		<div class="cls_info">';
						tempStr += '			<div>钙铁锌咀嚼片（40片）</div>';
						tempStr += '			<div>+80</div>';
						tempStr += '		</div>';		
						tempStr += '	</div>';
						tempStr += '</li>';
					}  // End for

					$('#id_integrationDetails_ul').append(tempStr);

					myScroll.refresh();		// Remember to refresh when contents are loaded (ie: on ajax completion)
				}, 1000);	// <-- Simulate network congestion, remove setTimeout from production!
			}  // End pullUpAction

			function handleIScroll() {
				pullDownEl = document.getElementById('pullDown');
				pullDownOffset = pullDownEl.offsetHeight;
				pullUpEl = document.getElementById('pullUp');	
				pullUpOffset = pullUpEl.offsetHeight;
				
				myScroll = new iScroll('wrapper', {
					useTransition: true,
					topOffset: pullDownOffset,
					onRefresh: function () {
						if (pullUpEl.className.match('loading')) {
							pullUpEl.className = '';
							pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
						}
					},
					onScrollMove: function () {
						if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
							pullUpEl.className = 'flip';
							pullUpEl.querySelector('.pullUpLabel').innerHTML = '松开刷新数据...';
							this.maxScrollY = this.maxScrollY;
						} else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
							pullUpEl.className = '';
							pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
							this.maxScrollY = pullUpOffset;
						}
					},
					onScrollEnd: function () {
						if (pullUpEl.className.match('flip')) {
							pullUpEl.className = 'loading';
							pullUpEl.querySelector('.pullUpLabel').innerHTML = '数据加载中...';				
							pullUpAction();	// Execute custom function (ajax call?)
						}
					}
				});  // End iScroll
			}  // End handleIScroll

			$(document).ready(function() {
				//console.log('ready');

				mrmj.init();
				handleIScroll();
			});  // End ready

		</script>
	</head>
	<body>
		
		<!-- iscroll page start -->
		<div id="wrapper">
			<div id="scroller">
				<div id="pullDown" style="display: none;">
					<span class="pullDownIcon" ></span><span class="pullDownLabel">下拉更新数据...</span>
				</div>

				<div class="cls_tips">总积分： <span class="cls_mark">${pointSum}</span> 分</div>
				
				<ul id="id_integrationDetails_ul" class="cls_record_ul">
					<c:forEach var="productPoints" items="${productPointList}">
						<li>
							<div>
								<div><fmt:formatDate value="${productPoints.addDate}" type="date"/>  </div>
								<div><fmt:formatDate value="${productPoints.addDate}" type="time"/>  </div>
							</div>
							<div class="cls_point"></div>
							<div class="cls_left_arrow"></div>
							<div>
								<div class="cls_info">
									<div>${productPoints.productName}</div>
									<div>+${productPoints.productPoint}</div>	
								</div>					
							</div>
						</li>
					</c:forEach>
				</ul>

				<div id="id_integrationDetails_more" class="cls_more">
					<div></div>
					<div></div>
				</div>
			
				<div id="id_integrationDetails_no_data" class="cls_no_data cls_hidden">亲，没有数据了</div>

				<!-- <div id="pullUp">
					<span class="pullUpIcon"></span><span class="pullUpLabel">上拉加载更多...</span>
				</div> -->
			</div>
		</div>
		<!-- iscroll page end -->
		<!-- 百度网站统计 star -->
		<script type="text/javascript" src="<%= XRContextUtil.getBaseRootPath()%>resources/js/front/webStatistics.js"></script>
		<!-- end -->

	</body>
</html>