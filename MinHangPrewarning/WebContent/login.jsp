<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <%@include file="/common/meta.jsp"%> --%>
<link href="${ctx}/resources/css/footer.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/resources/css/login.css" rel="stylesheet"
	type="text/css" />

<title>登录</title>
</head>
<body>
	<div>
		<div id="web_bg"
			style="position: absolute; width: 100%; height: 100%; z-index: -1">
			<img id="bgpic" style="position: fixed;"
				src="${ctx}/resources/images/airplane1.jpg" height="100%"
				width="100%" />
		</div>


		<div
			style="position: absolute; right: 15%; top: 12%; height: 320px; width: 422px; visibility: visible;">
			<div class="login" style="border: 2px solid #eee;">
				<div class="header">
					<div class="minhangname">民航系统登录</div>
				</div>

				<div class="loginTips">
					<div class="error_tips" id="error_tips" style="display: none;">
						<span class="error_logo" id="error_logo"></span> <span
							class="err_m" id="err_m"></span>
					</div>
				</div>

				<div class="web_login">
					<div class="tips"></div>
					<div class="login_form">
						<form action="login.action" method="post" id="loginform"
							name="loginform" style="margin: 0">
							<input id="errval" type="hidden"
								value='<s:property value="errinfo"/>'>

							<div class="uinArea" id="uinArea">
								<label class="input_tips" id="uin_tips" for="username"
									style="display: block;">用户名 </label>
								<div class="inputOuter">
									<input type="text" class="inputstyle" id="username"
										name="username" value="" tabindex="1">
								</div>
							</div>

							<div class="pwdArea" id="pwdArea">
								<label class="input_tips" id="pwd_tips" for="password"
									style="display: block;">密码</label>
								<div class="inputOuter">
									<input type="password" class="inputstyle password"
										id="password" name="password" value="" maxlength="16"
										tabindex="2">
								</div>
							</div>

							<div class="submit">
								<a class="login_button" href="" hidefocus="true"><input
									type="submit" tabindex="6" value="登 录" class="btn"
									id="login_button" onClick="javascript:return checkInput()"></a>
								<!-- 									 onClick="javascript:return checkInput()" -->
							</div>
						</form>
					</div>

					<div class="bottom" id="bottom_web" style="display: block;">
						<a class="link" id="feedback_web" href="index.action">返回首页</a> <span
							class="dotted">|</span> <a href="#" class="link" id="forgetpwd">意见反馈</a>
					</div>
				</div>

			</div>
		</div>
		<div class="picfoot">
			<div class="cgpic">
				<div class="MainBgSwitchLeft" id="left"
					onClick="javascript:changepic('0')"></div>
				<div class="MainBgSwitchRight" id="right"
					onClick="javascript:changepic('1')"></div>
			</div>

			<%@ include file="/common/footer.jsp"%>
			<!-- 			<div class="lay_foot"> -->
			<!-- 				<div class="lay_inner"> -->
			<!-- 					<div class="copyright"> -->
			<!-- 						<p class="copyright_link"> -->
			<!-- 							<a href="#">关于系统</a> | <a href="#">网站地图</a> | <a href="#">使用指南</a> -->
			<!-- 							| <a href="#">反馈建议</a> | <a href="#">版权所有</a> | <a href="#">联系我们</a> -->
			<!-- 						</p> -->


			<!-- 						<p class="copyright_en"> -->
			<!-- 							Copyright @ 2005 - 2014 民航.<a target="_blank" href="">All -->
			<!-- 								Rights Reserved.</a> -->
			<!-- 						</p> -->
			<!-- 						<p class="copyright_cn"> -->
			<!-- 							<a href="#">中国民航科学技术研究院</a> 版权所有 -->
			<!-- 						</p> -->
			<!-- 					</div> -->
			<!-- 				</div> -->
			<!-- 			</div> -->

		</div>

	</div>




</body>
</html>
<script src="${ctx}/resources/jquery/jquery-1.8.3.min.js"></script>
<script>
var currentpic = 1;
var total = 8;
function changepic(direction){
	var src = "resources/images/airplane";//document.getElementById("bgpic").src;
	if(direction == "0"){
		if(currentpic == 1)
			currentpic = total+1;
		document.getElementById("bgpic").src = src+(--currentpic)+".jpg";
	}
	else if(direction == "1"){
		if(currentpic == total)
			currentpic = 0;
		document.getElementById("bgpic").src = src+(++currentpic)+".jpg";
	}
	
}
	/**
	 *是否登录成功 
	 */
	window.onload = function()//用window的onload事件，窗体加载完毕的时候
	{
<%-- 		var s =<%=session.getAttribute("err")%>; --%>
<%-- 		var s = '<%=session["err"]%>'; --%>
        var s = $("#errval").val();
		if (s != "") {
			$("#tips").hide();
			$("#error_tips").show();
			$("#err_m").text(s);
		}
		if($("#username").val()!="")
		{
			$("#uin_tips").text("");
		}
		if($("#password").val()!="")
		{
			$("#pwd_tips").text("");
		}
	}

	/**
	 * 用户名 输入框提示
	 */
	$("#username").focus(function() {
		$("#tips").show();
		$("#error_tips").hide();

		var value = $("#uin_tips").html();
		if (value == "用户名") {
			$("#uin_tips").text("");
		}
	});
	$("#username").blur(function() {
		if ($(this).val() == "") {
			$("#uin_tips").text("用户名");
		}
	});
	/**
	 * 密码 输入框提示
	 */
	$("#password").focus(function() {
		$("#tips").show();
		$("#error_tips").hide();

		var value = $("#pwd_tips").html();
		if (value == "密码") {
			$("#pwd_tips").text("");
		}
	});
	$("#password").blur(function() {
		if ($(this).val() == "") {
			$("#pwd_tips").text("密码");
		}
	});
	/**
	 * 检查输入
	 */
	function checkInput() {
		if ($("#username").val() == "" || $("#password").val() == "") {
			$("#tips").hide();
			$("#error_tips").show();
			$("#err_m").text("请输入完整！");
			return false;
		}
		return true;
		/**	$.ajax({
				type : "POST",
				url : "loginprocess.jsp",
				data : "username=" + $("#username").val().toString() + "&password="
						+ $("#password").val().toString(),
				success : function(response) {
					alert(response);
					if (response == '0') {
						alert("请输入正确的用户名和密码！");
						$("#err_m").text("请输入正确的用户名和密码！");
					}else{
						alert("进入系统 ");
					}
				}
			})*/

	}
	
</script>