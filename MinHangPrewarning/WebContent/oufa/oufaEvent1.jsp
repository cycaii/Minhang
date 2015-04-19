<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%-- <%@include file="/common/meta.jsp"%> --%>
<%-- <link href="${ctx}/resources/bootstrap/css/bootstrap.css" --%>
<!-- 	rel="stylesheet"> -->
<link href="${ctx}/resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
	<link href="${ctx}/resources/css/bootstrap-datetimepicker.min.css"
		rel="stylesheet" media="screen">
		<title>民航不安全事件分析与预警系统</title>
</head>
<body>
	<div class="container">
		<div>

			<form id="pinfaform" name="pinfaform" method="post"
				action="oufa_showOufaOneDim.action" onsubmit="return commit()"
				class="form-horizontal" role="form">
				<div class="form-group">
					<label for="inputMsg" class="col-sm-2 control-label"> </label> <label
						style="color: rgb(176, 23, 31)" id="inputMsg"></label>
				</div>
				<div class="form-group">
					<label for="dimSelect" class="col-sm-2 control-label">偶发维度</label>
					<div class="col-sm-6">
						<select name="dimSelect" id="dimSelect" class="form-control">
							<option value="">选择维度 </option>

							<s:iterator value="dimStrs" id="dimStr">
								<option value="<s:property value="dimStr" />" <s:if test="#dimStr==dimSelect">selected="selected"</s:if>>
									<s:property value="dimStr" />
								</option>
							</s:iterator>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="pinfaDate" class="col-sm-2 control-label">截止日期</label>
					<div class="col-sm-10">
						<div class="input-group date form_date  col-sm-7" data-date=""
							data-date-format="dd MM yyyy" data-link-field="pinfaDate"
							data-link-format="yyyy-mm-dd">
							<input class="form-control" size="16" type="text" value="<s:property value="pinfaDate"/>"
								id="pinfaDate" name="pinfaDate" readonly> <span
								class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
						</div>
						<!-- 					<input type="hidden" id="dtp_input2" value="" /><br /> -->
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">确定</button>
					</div>
				</div>

			</form>
		</div>

		<div>
			<s:if test="msg!=null">
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<p>
							<s:property value="msg" />
						</p>
					</div>
				</div>
			</s:if>
			<s:elseif test="pinfaList != null && pinfaList.size() > 0">

				<table class="table table-hover">
					<caption>
						<s:property value="dimSelect" />
						(
						<s:property value="pinfaDate" />
						)
					</caption>
					<tr>
						<th>维度值</th>
						<th>偶发时间段</th>
						<th>对应数量值</th>
					</tr>

					<s:iterator value="pinfaList" id="pinfaEvent">
						<tr>
							<td><s:property value="dimvalue" /></td>
							<td><s:property value="pinfaPeriod" /></td>
							<td><s:iterator value="countValue" id="PinfaElem">
<!-- 									<a
										href="javascript:detail('<s:property
 										value="dimtype" />','<s:property
 										value="dimvalue" />',<s:property
 										value="day" />,1 )"
										 > <strong><s:property
  										value="num" /></strong></a>  --> 
		<a
										href="oufa/pinfaEventDetail.jsp?dimtype=<s:property value="dimtype"/>&dimvalue=<s:property value="dimvalue" />&day=<s:date name="day" format="yyyy-MM-dd" />&dim=1"
										target="_blank"> <strong><s:property
 												value="num" /></strong></a>  
								</s:iterator></td>
						</tr>
					</s:iterator>
					
				</table>
				</s:elseif>
		</div>
	</div>


</body>
</html>
<script type="text/javascript"
	src="${ctx}/resources/jquery/jquery-2.1.1.min.js" charset="UTF-8"></script>
<script type="text/javascript"
	src="${ctx}/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/locales/bootstrap-datetimepicker.zh-CN.js"
	charset="UTF-8"></script>
<script type="text/javascript">
	/**
	 * 日期控件
	 */
	$('.form_date').datetimepicker({
		format : "yyyy-mm-dd",
		language : 'zh-CN',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 2,
		forceParse : 0
	});

	/**
	 * 检查输入 非空  
	 */
	function checkInput(name) {
		var input = document.getElementById(name).value;
		if (input == null || !input) {
			return false;
		} else {
			$("#inputMsg").text(" ");
			return true;
		}
	}

	/**
	 * 表单提交 检查 
	 */
	function commit() {
		var dimsel = $("#dimSelect").find("option:selected").val();
		if (dimsel == "") {
			$("#inputMsg").text("请选择统计维度 ");
			return false;
		}
		// 		alert($("#pinfaDate").val());
		if (checkInput('pinfaDate')) {
			return true;
		} else {
			$("#inputMsg").text("请正确输入日期");
			return false;
		}

	}
// 	,dimvalue,day,dim
	function detail(dimtype	,dimvalue,day,dim){
		alert(dimtype+  " "+dimvalue+" "+day+" "+dim);
// 		var dimtypeStr =encodeURI(dimtype); 
// 		dimtypeStr=encodeURI(dimtypeStr); 
// 		var dimvalueStr =encodeURI(dimvalue); 
// 		dimtypeStr=encodeURI(dimvalueStr); 
		window.open("oufa_showOufaEventDetail.action?dimtype="+dimtype+"&dimvalue="+dimvalue+"&day="+day+"&dim="+dim);      
		
	}
	
	function test(){
		alert("aaa");
	}
</script>
