<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="../resources/jquery/jquery-1.8.3.min.js"></script>
<script src="../resources/jquery/sidebar.js"></script>
<script>
	$('#jsi-nav').sidebar({
		trigger : '.jsc-sidebar-trigger'
	});
</script>

	<div class="wrapper jsc-sidebar-content jsc-sidebar-pulled">
		<div>
			<a href="#" class="icon-menu link-menu jsc-sidebar-trigger"
				style="font-family: '黑体';">三</a>
		</div>
		 
	</div>

	<div class="sidebar jsc-sidebar" id="jsi-nav" data-sidebar-options="">
		<ul class="sidebar-list">
			<li><a href="${ctx}/pinfa/pinfaMain.jsp" class="current"
				target="_blank">频发事件</a></li>
			<li><a href="${ctx}/oufa/oufaMain.jsp " target="_blank">偶发事件</a></li>
			<li><a href="${ctx}/yujing/yujingMain.jsp" target="_blank">预测预警</a></li>
			<li><a href="" target="_blank">报告生成</a></li>
		</ul>
	</div>
