<%@ page language="java" contentType="text/html; charset=UTF-8"
      pageEncoding="UTF-8"%>  
<%-- <%@ page language="java" contentType="text/html; charset=GBK" --%>
<%-- 	pageEncoding="GBK"%> --%>
<%@include file="/common/header.jsp"%>		
<%@page import="minhang.dao.*,minhang.entity.*,java.util.*,minhang.service.*"%>
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
					<%@ include file="yujingMenu.jsp"%>
				</div>
			</div>
			<div id="main1">
			
			<s:if test="frameContent=='yuce'">
					<iframe name="main" scrolling="auto" frameborder="0"
						src="yujing_showYuce.action"></iframe>
				</s:if>
				<s:elseif test="frameContent=='yujing'">
					<iframe name="main" scrolling="auto" frameborder="0"
						src="yujing_showYujing.action"></iframe>
				</s:elseif>
			</div>
		</div>
	</div>

</body>
</html>