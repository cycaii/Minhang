<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/meta.jsp"%>
<link href="${ctx}/resources/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<title>民航不安全事件分析与预警系统</title>
</head>
<body>
	<div class="container">
		<div>
			<form id="form1" name="form1" action="oufa_showTufaOneDim.action"
				method="post" onsubmit="return commitCheck()"
				class="form-horizontal" role="form">
				<div class="form-group">
					<label for="inputMsg" class="col-sm-2 control-label"> </label> <label
						style="color: rgb(176, 23, 31)" id="inputMsg"></label>
				</div>

				<div class="form-group">
					<label for="dimSelect" class="col-sm-2 control-label">选择维度</label>
					<div class="col-sm-6">
						<select name="dimSelect" id="dimSelect" class="form-control">
							<option value="">默认所有维度</option>
							<s:iterator value="dimStrs" id="dimStr">
								<option value="<s:property value="dimStr" />"
									<s:if test="#dimStr==dimSelect">selected="selected"</s:if>>
									<s:property value="dimStr" />
								</option>
							</s:iterator>
						</select>
					</div>
				</div>
				<div class="form-group" id="year_row">
					<label for="year" class="col-sm-2 control-label">选择年度</label>
					<div class="col-sm-6">
						<select name="year" id="year" class="form-control">
							<s:iterator value="years" id="oneyear">
								<option value="<s:property value="oneyear" />" <s:if test="#oneyear==year">selected="selected"</s:if>>
									<s:property value="oneyear" />
								</option>
							</s:iterator>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="accordingSel">时间类型</label>
					<div class="col-sm-6">
						<select name="accordingSel" id="accordingSel" class="form-control"
							onchange="getCity()">
							<option value="">请选择时间类型</option>
							<option value="年" <s:if test="#accordingSel==\"年\"">selected="selected"</s:if>>年</option>
							<option value="月" <s:if test="#accordingSel==\"月\"">selected="selected"</s:if>>月</option>
							<option value="周" <s:if test="#accordingSel=='周'">selected="selected"</s:if>>周</option>
							<option value="旬" <s:if test="#accordingSel=='旬'">selected="selected"</s:if>>旬</option>
							<option value="季度" <s:if test="#accordingSel=='季度'">selected="selected"</s:if>>季度</option>
							<option value="季节" <s:if test="#accordingSel=='季节'">selected="selected"</s:if>>季节</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label" for="numSel">序号</label>
					<div class="col-sm-6">
						<select name="numSel" id="numSel" class="form-control">
							<option value="">请选择序号</option>
						</select>
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
			<s:elseif test="tufaOneDimResults != null && tufaOneDimResults.size() > 0">
				<table class="table table-hover">
					<caption>
						<s:property value="dimSelect" /> 
						(
						<s:property value="year" />
						年第
						<s:property value="numSel" />
						<s:property value="accordingSel" />
						)
					</caption>
					<tr>
						 <s:if test='null==dimSelect||dimSelect==""'>
							<th>维度</th>
						</s:if>
						<th>维度值</th>
						<th>发生数量值</th>
					</tr>
					<s:iterator value="tufaOneDimResults" id="oufaEvent">

						<tr>
							 <s:if test='null==dimSelect||dimSelect==""'>
								<td><s:property value="dimension" /></td>
							</s:if>
							<td><s:property value="dvalue" /></td>
							<td><s:property value="count" /></td>
						</tr>
					</s:iterator>
				</table>
			</s:elseif>
		</div>
	</div>
</body>
</html>
<script language="JavaScript" type="text/javascript">
	//定义了城市的二维数组，里面的顺序跟省份的顺序是相同的。通过selectedIndex获得省份的下标值来得到相应的城市数组 11   
	var numSel = [
			[ 1 ],
			[ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 ],
			[ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
					19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33,
					34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,
					49, 50, 51, 52 ],
			[ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
					19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33,
					34, 35, 36 ], [ 1, 2, 3, 4 ], [ 1, 2, 3, 4 ] ];
	function getCity() {
		//获得省份下拉框的对象  21         
		var sltProvince = document.form1.accordingSel;
		//获得城市下拉框的对象  23         
		var sltCity = document.form1.numSel;
		//得到对应省份的城市数组  26   
		var provinceCity = numSel[sltProvince.selectedIndex - 1];
		//清空城市下拉框，仅留提示选项 29  
		sltCity.length = 1;
		//将城市数组中的值填充到城市下拉框中 32  
		for ( var i = 0; i < provinceCity.length; i++) {
			sltCity[i + 1] = new Option(provinceCity[i], provinceCity[i]);
		}
	}
	/**
	 * 检查输入 数字 
	 */
	function checkValidateNum(name) {
		var value = document.getElementById(name).value;
		if (value && value != null && !isNaN(value)) {
			$("#inputMsg").text(" ");
			return true;
		}
		// 		$("#inputMsg").text("请输入数字 ");
		return false;
	}

	/**
	 * 表单提交 检查 
	 */
	function commitCheck() {
		// 		var dimsel = $("#dimSelect").find("option:selected").val();
		// 		if (dimsel == "") {
		// 			$("#inputMsg").text("请选择维度 ");
		// 			return false;
		// 		}
		var sel = $("#year").find("option:selected").val();
		if (sel == "") {
			$("#inputMsg").text("请选择年份 ");
			return false;
		}
		sel = $("#accordingSel").find("option:selected").val();
		if (sel == "") {
			$("#inputMsg").text("请选择时间类型 ");
			return false;
		}
		sel = $("#numSel").find("option:selected").val();
		if (sel == "") {
			$("#inputMsg").text("请选择序号 ");
			return false;
		}

		return true;

	}
</script>
