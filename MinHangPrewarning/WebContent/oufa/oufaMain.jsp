<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/meta.jsp"%>
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
					<%@ include file="oufaMenu.jsp"%>
				</div>
			</div>
			<div id="main1">
				<s:if test="frameContent=='oufaEvent1'">
					<iframe name="main" scrolling="auto" frameborder="0"
						src="oufa_showOufaOneDim.action"></iframe>
				</s:if>
				<s:elseif test="frameContent=='oufaEvent2'">
					<iframe name="main" scrolling="auto" frameborder="0"
						src="oufa_showOufaTwoDim.action"></iframe>
				</s:elseif>
				<s:elseif test="frameContent=='tufaEvent1'">
					<iframe name="main" scrolling="auto" frameborder="0"
						src="oufa_showTufaOneDim.action"></iframe>
				</s:elseif>
				<s:elseif test="frameContent=='tufaEvent2'">
					<iframe name="main" scrolling="auto" frameborder="0"
						src="oufa_showTufaTwoDim.action"></iframe>
				</s:elseif>
			</div>
		</div>
	</div>

</body>
</html>
