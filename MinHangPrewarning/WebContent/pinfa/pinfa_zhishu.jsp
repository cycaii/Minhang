<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/common/header.jsp"%>	
<%@page import="minhang.entity.YiweiResult"%>
<%@page import="minhang.dao.DBWorker,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/meta.jsp"%>
<link href="${ctx}/resources/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<title>频发统计</title>
</head>

<body>
	<div class="container">
		<div>
			<form id="form1" name="form1" method="post"
				action="Tongjiservlet.jsp" onsubmit="return commit()"
				class="form-horizontal" role="form">
				<div class="form-group">
					<label for="inputMsg" class="col-sm-2 control-label"> </label> <label
						style="color: rgb(176, 23, 31)" id="inputMsg"></label>
				</div>
				<div class="form-group">
					<label for="year" class="col-sm-2 control-label">年份</label>
					<div class="col-sm-6">
						<select name="year" id="year" class="form-control">
							<%
								String[] years = { "1952", "1953", "1954", "1955", "1956", "1957",
														"1958", "1959", "1960", "1961", "1962", "1963", "1964",
														"1965", "1966", "1967", "1968", "1969", "1970", "1971",
																																		"1972", "1973", "1974", "1975", "1976", "1977", "1978",
													"1979", "1980", "1981", "1982", "1983", "1984", "1985",
																																		"1986", "1987", "1988", "1989", "1990", "1991", "1992",
																																		"1993", "1994", "1995", "1996", "1997", "1998", "1999",
																																		"2000", "2001", "2002", "2003", "2004", "2005", "2006",
																																		"2007", "2008", "2009", "2010", "2011", "2012", "2013",
																																		"2014" };

																																for (int i = 0; i < years.length; i++) {
							%>
							<option value="<%=1952 + i%>"><%=years[i]%></option>
							<%
								}
							%>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label" for="province">统计类型</label>
					<div class="col-sm-6">
						<select name="province" id="province" class="form-control"
							onchange="getCity()">
							<option value="">请选择统计类型</option>
							<option value="年">年</option>
							<option value="月">月</option>
							<option value="周">周</option>
							<option value="旬">旬</option>
							<option value="季度">季度</option>
							<option value="季节">季节</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label" for="city">序号</label>
					<div class="col-sm-6">
						<select name="city" id="city" class="form-control">
							<option value="">请选择序号</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="duibijizhun">对比基准</label>
					<div class="col-sm-6">
						<select name="duibijizhun" id="duibijizhun" class="form-control">
							<option value="">请选择对比基准</option>
							<%
								DBWorker dbworker = new DBWorker();
																														Vector<String> names = null;
																														if (dbworker.getNames() != null) {
																															names = dbworker.getNames();
																														}
																														for (int i = 0; i < names.size(); i++) {
																															System.out.println("names " + names.get(i));
							%>
							<option value="<%=names.get(i)%>"><%=names.get(i)%></option>
							<%
								}
							%>
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
			<table class="table table-hover">
				<%
					String yemianID = request.getParameter("yemianID");

															if (yemianID != null && yemianID.equals("2")) {
																String year = session.getAttribute("year").toString();
																String xuhao = session.getAttribute("xuhao").toString();
																String leixing = session.getAttribute("leixing").toString();
																String name = session.getAttribute("name").toString();
																// 								String xuhao = request.getParameter("xuhao");
																// 								String leixing = request.getParameter("leixing");
																// 								String name = request.getParameter("name");
																System.out.println("yemianID:" + yemianID + "  " + year + " "
																		+ xuhao);
																session.setAttribute("year", null);
																session.setAttribute("year", null);
																session.setAttribute("year", null);
																session.setAttribute("year", null);
																Vector<YiweiResult> yrs = (Vector<YiweiResult>) session
																		.getAttribute("yrs");
				%>
				<caption><%=year%>年<%=xuhao + leixing%>&nbsp;（对比基准<%=name%>）
				</caption>
				<tr>
					<th width="130">维度类型</th>
					<th width="130">维度值</th>
					<th width="130">频数</th>
					<th width="130">平均频数</th>
					<th width="130">百分百</th>
					<th width="130">频发指数</th>
				</tr>
				<%
					for (int i = 0; i < yrs.size(); i++) {
				%>
				<tr>
					<td><%=yrs.get(i).getWeidulx()%></td>
					<td><%=yrs.get(i).getWeiduvalue()%></td>
					<td><%=String.valueOf(yrs.get(i).getPinshu())%></td>
					<td><%=String.valueOf(yrs.get(i).getAvgpinshu())%></td>
					<td><%=String.valueOf(yrs.get(i).getPercent())%></td>
					<td><%=String.valueOf(yrs.get(i).getZhishu())%></td>
				</tr>
				<%
					}
															}
				%>

			</table>
		</div>
	</div>
</body>
</html>
<script language="JavaScript" type="text/javascript">
	//定义了城市的二维数组，里面的顺序跟省份的顺序是相同的。通过selectedIndex获得省份的下标值来得到相应的城市数组 11   
	var city = [
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
		var sltProvince = document.form1.province;
		//获得城市下拉框的对象  23         
		var sltCity = document.form1.city;
		//得到对应省份的城市数组  26   
		var provinceCity = city[sltProvince.selectedIndex - 1];
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
		return false;
	}

	/**
	 * 表单提交 检查 
	 */
	function commit() {
		
		var  sel = $("#year").find("option:selected").val();
		if ( sel == "") {
			$("#inputMsg").text("请选择年份 ");
			return false;
		}
		 sel = $("#province").find("option:selected").val();
		if ( sel == "") {
			$("#inputMsg").text("请选择统计类型 ");
			return false;
		}
		sel = $("#city").find("option:selected").val();
		if ( sel == "") {
			$("#inputMsg").text("请选择序号 ");
			return false;
		}
		sel = $("#duibijizhun").find("option:selected").val();
		if ( sel == "") {
			$("#inputMsg").text("请选择对比基准 ");
			return false;
		}
		
		if (checkValidateNum('year') && checkValidateNum('month')) {
			return true;
		} else {
			$("#inputMsg").text("请正确输入预测年月");
			return false;
		}
	}
</script>