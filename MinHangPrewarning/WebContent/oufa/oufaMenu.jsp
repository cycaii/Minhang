<%@ page language="java" contentType="text/html; charset=UTF-8"
 	pageEncoding="UTF-8"%>  
<%-- <%@ page language="java" contentType="text/html; charset=GBK" --%>
<%--   	pageEncoding="GBK"%>  --%>
<link href="${ctx}/css/Untitled-1.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/1-1.css" rel="stylesheet" type="text/css" />
 <h1 onClick="javascript:ShowMenu(this,0)">
	<a href="javascript:void(0)">+ 偶发事件</a>
</h1>
<span class="no">
	<h2>
		<a href="oufa_showOufaOneDim.action" target="main"> &nbsp; 一维偶发</a>
	</h2>
	<h2>
		<a href="oufa_showOufaTwoDim.action" target="main"> &nbsp; 二维偶发</a>
	</h2>
</span>
<h1 onClick="javascript:ShowMenu(this,1)">
	<a href="javascript:void(0)">+ 突发事件</a>
</h1>
<span class="no">
	<h2>
		<a href="oufa_showTufaOneDim.action" target="main">  &nbsp;  一维突发</a>
	</h2>
	<h2>
		<a href="oufa_showTufaTwoDim.action" target="main">  &nbsp; 二维突发</a>
	</h2>
</span>
 
 <script language="JavaScript">
<!--//
	function ShowMenu(obj, n) {
		var Nav = obj.parentNode;
		if (!Nav.id) {
			var BName = Nav.getElementsByTagName("ul");
			var HName = Nav.getElementsByTagName("h2");
			var t = 2;
		} else {
			var BName = document.getElementById(Nav.id).getElementsByTagName(
					"span");
			var HName = document.getElementById(Nav.id).getElementsByTagName(
					"h1");
			var t = 1;
		}
		for ( var i = 0; i < HName.length; i++) {
			HName[i].innerHTML = HName[i].innerHTML.replace("-", "+");
			HName[i].className = "";
		}
		obj.className = "h" + t;
		for ( var i = 0; i < BName.length; i++) {
			if (i != n) {
				BName[i].className = "no";
			}
		}
		if (BName[n].className == "no") {
			BName[n].className = "";
			obj.innerHTML = obj.innerHTML.replace("+", "-");
		} else {
			BName[n].className = "no";
			obj.className = "";
			obj.innerHTML = obj.innerHTML.replace("-", "+");
		}
	}
//-->
</script>
 
