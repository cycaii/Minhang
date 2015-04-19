<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@include file="/common/header.jsp"%>
<%@page
	import="minhang.dao.*,minhang.entity.*,minhang.helper.*,java.util.*,java.text.*,minhang.service.*,java.util.Map.Entry"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/meta.jsp"%>
<%-- <link href="${ctx}/resources/bootstrap/css/bootstrap.css" --%>
<!-- 	rel="stylesheet"> -->
<link href="${ctx}/resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
	<link href="${ctx}/resources/css/bootstrap-datetimepicker.min.css"
		rel="stylesheet" media="screen">
		<title>�񺽲���ȫ�¼�������Ԥ��ϵͳ</title>
</head>
<body>
	<%
		request.setCharacterEncoding("gbk");
		PinfaEventCompute pinfaEventCompute = (PinfaEventCompute) application
		.getAttribute("PinfaEventCompute");
		DimtypeDao d = (DimtypeDao) application.getAttribute("DimtypeDao");
		List<String> dimStrs = (List<String>) application
		.getAttribute("dimTypeStrs");
		List<PinfaEvent> pinfaList = null;

		//��ȡҳ���������
		String  dimtype1 = request.getParameter("dtype1Select");
		String dimtype2 = request.getParameter("dtype2Select");
		String pinfaDate = request.getParameter("pinfaDate");
		System.out.println("pinfaEvent--dimtype1:" + dimtype1 +"  dimtype2"+dimtype2+ "  pinfaDate:"
		+ pinfaDate);

		String msg = null;
		if (dimtype1 != null&&dimtype2 != null &&pinfaDate!=null&& pinfaDate.trim() != null) {
			pinfaList = pinfaEventCompute.computeTwoDimPinfa(dimtype1,dimtype2,
			pinfaDate);
			if(pinfaList==null||pinfaList.size()==0){
		msg = "��Ǹ��"+pinfaDate+"���� ��"+dimtype1+" "+dimtype2+"��û�н����";
			}
		}
	%>
	<div class="container">
		<div>


			<form id="pinfaform" name="pinfaform" method="post"
				action="pinfaEvent2.jsp" onsubmit="return commit()"
				class="form-horizontal" role="form">
				<div class="form-group">
					<label for="inputMsg" class="col-sm-2 control-label"> </label> <label
						style="color: rgb(176, 23, 31)" id="inputMsg"></label>
				</div>
				<div class="form-group">
					<label for="dtype1Select" class="col-sm-2 control-label">ά��һ</label>
					<div class="col-sm-6">
						<select name="dtype1Select" id="dtype1Select" class="form-control">
							<%
								for (int i = 0; i < dimStrs.size(); i++) {
							%>
							<option value=<%=dimStrs.get(i)%>><%=dimStrs.get(i)%></option>
							<%
								}
							%>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="dtype2Select" class="col-sm-2 control-label">ά�ȶ�</label>
					<div class="col-sm-6">
						<select name="dtype2Select" id="dtype2Select" class="form-control">
							<%
								for (int i = 0; i < dimStrs.size(); i++) {
							%>
							<option value=<%=dimStrs.get(i)%>><%=dimStrs.get(i)%></option>
							<%
								}
							%>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="pinfaDate" class="col-sm-2 control-label">��ֹ����</label>
					<div class="col-sm-10">
						<div class="input-group date form_date  col-sm-7" data-date=""
							data-date-format="dd MM yyyy" data-link-field="pinfaDate"
							data-link-format="yyyy-mm-dd">
							<input class="form-control" size="16" type="text" value=""
								id="pinfaDate" name="pinfaDate" readonly> <span
								class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
						</div>
						<!-- 					<input type="hidden" id="dtp_input2" value="" /><br /> -->
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">ȷ��</button>
					</div>
				</div>

			</form>
		</div>

		<div>
			<%
				if(msg!=null){
			%>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<p><%=msg%></p>
				</div>
			</div>
			<%
				}else
								 if (pinfaList != null && pinfaList.size() > 0) 
								 {
			%>
			<table class="table table-hover">
				<caption><%=dimtype1+" "+dimtype2%>��<%=pinfaDate%>��
				</caption>
				<tr>
					<th>ά��ֵ</th>
					<th>Ƶ��ʱ���</th>
					<th>��Ӧ����ֵ</th>
				</tr>

				<%
					DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
																																for (int i = 0; i < pinfaList.size(); i++) {
																																		PinfaEvent pinfaEvent = pinfaList.get(i);
																																		System.out.println("ά��ֵ :" + pinfaEvent.getDimvalue()
																																				+ "       Ƶ��ʱ���:" + pinfaEvent.getPinfaPeriod()
																																				+ "   ��Ӧ����ֵ:" + pinfaEvent.getCountValue().size());
				%>
				<tr>
					<td><%=pinfaEvent.getDimvalue()%></td>
					<td><%=pinfaEvent.getPinfaPeriod()%></td>
					<td>
						<%
							for(PinfaElem pe:pinfaEvent.getCountValue()){
																																																System.out.println("dimtype:"+pinfaEvent.getDimtype()+"  dimvalue:"+pinfaEvent.getDimvalue()+"   day:"+df.format(pe.getDay()));
						%> <%-- 						&nbsp;<%=df.format(pe.getDay())%>( --%> <a
						href="pinfaEventDetail.jsp?dimtype=<%=pinfaEvent.getDimtype()%>&dimvalue=<%=pinfaEvent.getDimvalue()%>&day=<%=df.format(pe.getDay())%>&dim=2"
						target="_blank"><strong><%=pe.getNum()%></strong></a> <!-- 						) -->
						<%
							}
						%>

					</td>
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
	 * ���ڿؼ�
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
	 * ������� �ǿ�  
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
	 * ���ύ ��� 
	 */
	function commit() {
		var dimsel1 = $("#dtype1Select").find("option:selected").val();
		var dimsel2 = $("#dtype2Select").find("option:selected").val();
		if (dimsel1 == "") {
			$("#inputMsg").text("��ѡ��ͳ��ά��һ ");
			return false;
		}
		if (dimsel2 == "") {
			$("#inputMsg").text("��ѡ��ͳ��ά�ȶ� ");
			return false;
		}
		// 		alert($("#pinfaDate").val());
		if (checkInput('pinfaDate')) {
			return true;
		} else {
			$("#inputMsg").text("����ȷ��������");
			return false;
		}

	}
</script>
