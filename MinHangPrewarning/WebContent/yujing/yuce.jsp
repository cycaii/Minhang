<%@ page language="java" contentType="text/html; charset=UTF-8"
      pageEncoding="UTF-8"%>  
<%-- <%@ page language="java" contentType="text/html; charset=GBK" --%>
<%-- 	pageEncoding="GBK"%> --%>
<%@include file="/common/header.jsp"%>
<%@page
	import="minhang.dao.*,minhang.entity.*,minhang.helper.*,java.util.*,minhang.service.*,java.util.Map.Entry"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/meta.jsp"%>
<link href="${ctx}/resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
	<link href="${ctx}/resources/css/bootstrap-datetimepicker.min.css"
		rel="stylesheet" media="screen">
		<title>预测</title>
</head>
<body>
	<div class="container">
		<div>


			<form id="form1" name="form1" method="post" action="yujing_showYuce.action"
				onsubmit="return commit()" class="form-horizontal" role="form">
				<div class="form-group">
					<label for="inputMsg" class="col-sm-2 control-label"> </label> <label
						style="color: rgb(176, 23, 31)" id="inputMsg"></label>
				</div>
				<div class="form-group">
					<label for="algotype" class="col-sm-2 control-label">预测算法</label>
					<div class="col-sm-6">
						<select name="algotype" id="algotype" class="form-control">
							<option value="">选择预测算法</option>
							<s:iterator value="aglorithms" id="aglorithm">
								<option value="<s:property value="aglorithm" />"
									<s:if test="#aglorithm==algotype">selected="selected"</s:if>>
									<s:property value="aglorithm" />
								</option>
							</s:iterator>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="dimtype" class="col-sm-2 control-label">预测选项</label>
					<div class="col-sm-6">
						<select name="dimtype" id="dimtype" class="form-control">
							<option value="">选择预测选项</option>
							<s:iterator value="dimStrs" id="dimStr">
								<option value="<s:property value="dimStr" />"
									<s:if test="#dimStr==dimtype">selected="selected"</s:if>>
									<s:property value="dimStr" />
								</option>
							</s:iterator>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="yuceTime" class="col-sm-2 control-label">预测年月</label>
					<div class="col-sm-10">
						<div class="input-group date form_date  col-sm-7" data-date=""
						data-link-field="yuceTime"
							data-link-format="yyyy-mm">
							<input class="form-control" size="16" type="text"  	value="<s:property value="yuceTime"/>" 
								id="yuceTime" name="yuceTime" readonly> <span
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
			<s:if test="predictList != null && predictList.size() > 0">
				<table class="table table-hover">
					<caption>
						<s:property value="dimtype" />
						(
						<s:property value="yuceTime" />
						)
					</caption>
					<tr>
						<th>维度值</th>
						<th>预测值</th>
					</tr>
					<s:iterator value="predictList" id="e" status="estatus">
						<s:if test="#estatus.count<=30">
							<tr>
								<td><s:property value="key" /></td>
								<td><s:property value="value" /></td>
							</tr>
						</s:if>
					</s:iterator>
				</table>
			</s:if>
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
	$('.form_date').datetimepicker({
		format : "yyyy-mm",
		language : 'zh-CN',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 3,
		minView : 3,
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
		var algosel = $("#algotype").find("option:selected").val();
		if (algosel == "") {
			$("#inputMsg").text("请选择预测算法 ");
			return false;
		}
		var dimsel = $("#dimtype").find("option:selected").val();
		if (dimsel == "") {
			$("#inputMsg").text("请选择预测维度 ");
			return false;
		}

		if (checkInput('yuceTime')) {
			return true;
		} else {
			$("#inputMsg").text("请正确输入预测年月");
			return false;
		}
	}
</script>
