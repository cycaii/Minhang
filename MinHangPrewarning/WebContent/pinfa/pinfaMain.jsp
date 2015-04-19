<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@include file="/common/header.jsp"%>
<%@page
	import="minhang.dao.*,minhang.entity.*,java.util.*,minhang.service.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/meta.jsp"%>
<link href="${ctx}/css/Untitled-1.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/1-1.css" rel="stylesheet" type="text/css" />
<title>民航不安全事件分析与预警系统</title>
</head>

<body>
	<div id="body">
		<div id="head">
			<%@ include file="/common/MinhangHead.jsp"%>
		</div>
		<div id="main">
			<div id="left">

				<div id="menu">
					<%@ include file="pinfaMenu.jsp"%>
				</div>
			</div>
			<div id="main1">
				<iframe name="main" scrolling="auto" frameborder="0"
					src="pinfa_zhishu.jsp"></iframe>
			</div>
			<!-- 	 BI链接 -->
			<!-- 			<div id="main1"> -->
			<!-- 				<iframe name="main" scrolling="auto" frameborder="0" -->
			<!-- 					src="http://localhost:9704/analytics/saw.dll?PortalPages&PortalPath=/shared/minhang/_portal/yiweipinfajisuan&NQUser=weblogic&NQPassword=Admin123"></iframe> -->
			<!-- 			</div> -->
		</div>
	</div>

</body>
</html>
<!-- <script> -->

<!-- </script> -->