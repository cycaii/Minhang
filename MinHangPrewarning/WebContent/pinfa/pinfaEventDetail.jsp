<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@include file="/common/header.jsp"%>
<%@page
	import="minhang.dao.*,minhang.entity.*,minhang.helper.*,java.util.*,java.text.*,minhang.service.*,java.util.Map.Entry"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/meta.jsp"%>
<link href="${ctx}/resources/bootstrap/css/bootstrap.css"
	rel="stylesheet">
	<title>�񺽲���ȫ�¼�������Ԥ��ϵͳ</title>
</head>
<body>
	<%
		// 		request.setCharacterEncoding("gbk");

		DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
		String daystr = request.getParameter("day");
		System.out.println("daystr:" + daystr);
		Date day = df.parse(daystr);
		PinfaEventDao pinfaEventDao = (PinfaEventDao) application
				.getAttribute("PinfaEventDao");
		String dimtype, dimvalue, dimtype1=null, dimvalue1=null, dimtype2=null, dimvalue2=null;
		String dimstr = request.getParameter("dim");
		int dim = Integer.parseInt(dimstr);

		String dimtypestr = request.getParameter("dimtype");
		String dimvaluestr = request.getParameter("dimvalue");
		dimtype = new String(dimtypestr.getBytes("ISO-8859-1"), "GBK");
		dimvalue = new String(dimvaluestr.getBytes("ISO-8859-1"), "GBK");
		System.out.println("detail-dimtype:" + dimtype + "  dimvalue:"
				+ dimvalue);
		
		List<Totalbase> totals = null;
		if (dim==1) {
			totals = pinfaEventDao.queryOneDimPinfaEventDetails(dimtype,
					dimvalue, day);
		} else if (dim==2) {
			dimtype1 = dimtype.split(" ")[0];
			dimvalue1 = dimvalue.split(" ")[0];
			dimtype2 = dimtype.split(" ")[1];
			dimvalue2 = dimvalue.split(" ")[1];
			System.out.println("detail-dimtype1:" + dimtype1
					+ "  dimvalue1:" + dimvalue1 + "   dimtype2:"
					+ dimtype2 + "  dimvalue2:" + dimvalue2);

			totals = pinfaEventDao.queryTwoDimPinfaEventDetails(dimtype1,
					dimvalue1, dimtype2, dimvalue2, day);
		}
	%>
	<div class="container">
		<div class="table-responsive ">
			<table class="table table-bordered" data-show-columns="true">
				<caption>
					<%
						if (dim==1) {
					%><%="��"+dimtype + "��:" + dimvalue%>
					<%
						} else if (dim==2) {
					%><%="��"+dimtype1 + "��:" + dimvalue1 + " ��" + dimtype2 + "��:"
						+ dimvalue2%>
					<%
						}
					%>��<%=daystr%>��
				</caption>
				<tr>
					<th>����</th>
					<th>�ϱ�����</th>
					<th>��������</th>
					<th>�����ص�</th>
					<th>����������</th>
					<th>���ʹ���</th>
					<th>����������</th>
					<th>������ʹ�õ�λ</th>
					<th>�¼����</th>
					<th>�¼��ȼ�</th>
					<th>�ȼ�����</th>
					<th>�¼�����</th>
					<th>�������</th>
					<th>���ε�λ</th>
					<th>�¼�����</th>
					<th>���ʹ���</th>
					<th>�¼�ԭ��</th>
					<th>ԭ�����</th>
					<th>ԭ������</th>
					<th>����ֵ</th>
					<th>���н׶�</th>
					<th>�¼�����</th>
					<th>�����ɵ�</th>
					<th>�ƻ�Ŀ�ĵ�</th>
				</tr>
				<%
					for (Totalbase t : totals) {
				%>
				<tr>
					<td><%=t.getBiaoti()%></td>
					<td><%=t.getShangbaoriqi().substring(0, 19)%></td>
					<td><%=t.getFashengdiqu()%></td>
					<td><%=t.getFashengdidian()%></td>
					<td><%=t.getHangkongqijixing()%></td>
					<td><%=t.getJixingdalei()%></td>
					<td><%=t.getHangkongqijihao()%></td>
					<td><%=t.getHangkongqishiyongdanwei()%></td>
					<td><%=t.getShijianhouguo()%></td>
					<td><%=t.getShijiandengji()%></td>
					<td><%=t.getDengjidalei()%></td>
					<td><%=t.getShijiantiaokuan()%></td>
					<td><%=t.getTiaokuandalei()%></td>
					<td><%=t.getZerendanwei()%></td>
					<td><%=t.getShijianleixing()%></td>
					<td><%=t.getLeixingdalei()%></td>
					<td><%=t.getShijianyuanyin()%></td>
					<td><%=t.getYuanyindalei()%></td>
					<td><%=t.getYuanyinyinsu()%></td>
					<td><%=t.getFengxianzhi()%></td>
					<td><%=t.getFeixingjieduan()%></td>
					<td><%=t.getShijianxingzhi()%></td>
					<td><%=t.getZuihouqifeidian()%></td>
					<td><%=t.getJihuamudidi()%></td>
				</tr>
				<%
					}
				%>
			</table>

		</div>
	</div>
</body>
</html>
