<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%-- <%@include file="/common/meta.jsp"%> --%>
<link href="${ctx}/resources/css/menubar.css" rel="stylesheet" type="text/css" />
<title>民航不安全事件分析与预警系统</title>

</head>

<body>
	<h1 style="display: none;">Minhang</h1>


	<div class="wrapper jsc-sidebar-content jsc-sidebar-pulled">
		<div style="background-color: #515151;">
			<a href="#" class="icon-menu link-menu jsc-sidebar-trigger"
				style="font-family: '黑体';color:white">三</a>
		</div>

		<div class="main-content">
			<!-- 			<iframe name="mainFrame" id="mainFrame" frameBorder="0" -->
			<!-- 				scrolling="no" width="100%" height="400" src="index.jsp"> </iframe> -->
			<p><br/><br/></p>
			<h1 class="headline">
				<span class="headline-main">民航不安全事件分析与预警系统</span>
			</h1>
			<p>v 1.0.0</p>
			<p>分析并对历年不安全事件进行预测与预警</p>
			<p>欢迎使用</p>
			<p class="download">
				<a href="login-index.action" class="btn-download">进入系统</a>
			</p>
		</div>

	</div>

	<div class="sidebar jsc-sidebar" id="jsi-nav" data-sidebar-options="">
		<ul class="sidebar-list">
			<!-- 		    <li><a href="http://www.castc.org.cn" target="_blank">CAST</a></li> -->
			<li><a href="#" class="current" target="">关于系统</a></li>
			<li><a href="#" target="">网站地图</a></li>
			<li><a href="#" target="">版权信息</a></li>
			<li><a href="#" target="">联系我们</a></li>
		</ul>
	</div>


</body>

<script src="resources/jquery/jquery-1.8.3.min.js"></script>
<script src="resources/jquery/sidebar.js"></script>
<script>
	$('#jsi-nav').sidebar({
		trigger : '.jsc-sidebar-trigger'
	});
</script>
</html>
