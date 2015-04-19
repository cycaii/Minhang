<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page language="java" contentType="text/html; charset=GBK" --%>
<%-- 	pageEncoding="GBK"%> --%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/meta.jsp"%>
<link href="${ctx}/resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
	<link href="${ctx}/resources/css/bootstrap-datetimepicker.min.css"
		rel="stylesheet" media="screen">
		<title>预警</title>
</head>
<body>
	<div class="container">
		<div>
			<form id="form1" name="form1" method="post"
				action="yujing_showYujing.action" onsubmit="return commit()"
				class="form-horizontal" role="form">
				<div class="form-group">
					<label for="inputMsg" class="col-sm-2 control-label"> </label> <label
						style="color: rgb(176, 23, 31)" id="inputMsg"></label>
				</div>
				<div class="form-group">
					<label for="algotype" class="col-sm-2 control-label">预测算法</label>
					<div class="col-sm-6">
						<select name="algotype" id="algotype" class="form-control">
							<option value="">选择预警算法</option>
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
					<label for="yuceTime" class="col-sm-2 control-label">预警年月</label>
					<div class="col-sm-10">
						<div class="input-group date form_date  col-sm-7" data-date=""
							data-link-field="yuceTime" data-link-format="yyyy-mm">
							<input class="form-control" size="16" type="text"
								value="<s:property value="yuceTime"/>" id="yuceTime"
								name="yuceTime" readonly> <span
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

			<s:if test="predictProList != null && predictProList.size() > 0">
				<table class="table table-bordered">
					<caption>
						预警结果(
						<s:property value="yuceTime" />
						)
					</caption>
					<tr>
						<th>事件类型</th>
						<th>几乎不可能</th>
						<th>极少发生</th>
						<th>偶然发生</th>
						<th>可能发生</th>
						<th>经常发生</th>
					</tr>
					<s:iterator value="predictProList" id="predictProbability"
						status="status">
						<tr>

							<td><s:property value="dimvalue" /> &nbsp;<s:property
									value="riskvalue" /></td>

							<s:if test="riskvalue<10">
								<td <s:if test="probability<=0.001">  bgcolor="green" </s:if>></td>
								<td
									<s:if test="probability>0.001&&probability<=0.01">  bgcolor="green" </s:if>></td>
								<td
									<s:if test="probability>0.01&&probability<=0.1">  bgcolor="blue"</s:if>></td>
								<td
									<s:if test="probability>0.1&&probability<=0.5">  bgcolor="blue" </s:if>></td>
								<td
									<s:if test="probability>0.5&&probability<=1"> bgcolor="yellow" </s:if>></td>
							</s:if>
							<s:elseif test="riskvalue<50">
								<td <s:if test="probability<=0.001"> bgcolor="green" </s:if>></td>
								<td
									<s:if test="probability>0.001&&probability<=0.01">  bgcolor="blue" </s:if>></td>
								<td
									<s:if test="probability>0.01&&probability<=0.1">   bgcolor="blue"</s:if>></td>
								<td
									<s:if test="probability>0.1&&probability<=0.5"> bgcolor="yellow" </s:if>></td>
								<td
									<s:if test="probability>0.5&&probability<=1"> bgcolor="yellow" </s:if>></td>
							</s:elseif>
							<s:elseif test="riskvalue<100">

								<td <s:if test="probability<=0.001"> bgcolor="blue"</s:if>></td>
								<td
									<s:if test="probability>0.001&&probability<=0.01">bgcolor="yellow"</s:if>></td>
								<td
									<s:if test="probability>0.01&&probability<=0.1"> bgcolor="yellow"</s:if>></td>
								<td
									<s:if test="probability>0.1&&probability<=0.5"> bgcolor="orange"</s:if>></td>
								<td
									<s:if test="probability>0.5&&probability<=1">bgcolor="orange"</s:if>></td>
							</s:elseif>
							<s:elseif test="riskvalue<300">

								<td <s:if test="probability<=0.001"> bgcolor="blue"</s:if>></td>
								<td
									<s:if test="probability>0.001&&probability<=0.01">bgcolor="yellow"</s:if>></td>
								<td
									<s:if test="probability>0.01&&probability<=0.1"> bgcolor="orange"</s:if>></td>
								<td
									<s:if test="probability>0.1&&probability<=0.5"> bgcolor="orange"</s:if>></td>
								<td
									<s:if test="probability>0.5&&probability<=1">bgcolor="red"</s:if>></td>
							</s:elseif>
							<s:else>
								<td <s:if test="probability<=0.001"> bgcolor="yellow"</s:if>></td>
								<td
									<s:if test="probability>0.001&&probability<=0.01">bgcolor="orange"</s:if>></td>
								<td
									<s:if test="probability>0.01&&probability<=0.1"> bgcolor="orange"</s:if>></td>
								<td
									<s:if test="probability>0.1&&probability<=0.5"> bgcolor="red"</s:if>></td>
								<td
									<s:if test="probability>0.5&&probability<=1">bgcolor="red"</s:if>></td>
							</s:else>
						</tr>

					</s:iterator>
				</table>
				<br />
				<table class="table table-hover">
					<caption>具体结果</caption>
					<tr>
						<th>事件类型</th>
						<th>风险值</th>
						<th>概率</th>
					</tr>
					<s:iterator value="predictProList" id="predictProbability">
						<tr>
							<td><s:property value="dimvalue" /></td>
							<td><s:property value="riskvalue" /></td>
							<td><s:property value="probability" /></td>
						</tr>

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

		if (checkInput('yuceTime')) {
			return true;
		} else {
			$("#inputMsg").text("请正确输入预警年月");
			return false;
		}
	}
</script>
