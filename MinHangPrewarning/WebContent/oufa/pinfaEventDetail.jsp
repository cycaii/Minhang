<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@include file="/common/header.jsp"%>
<%@page
	import="minhang.dao.*,minhang.entity.*,minhang.algo.*,java.util.*,java.text.*,minhang.service.*,java.util.Map.Entry"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/meta.jsp"%>
<link href="${ctx}/resources/bootstrap/css/bootstrap.css"
	rel="stylesheet">
	<title>民航不安全事件分析与预警系统</title>
</head>
<body>
	<%
				request.setCharacterEncoding("gbk");

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String daystr = request.getParameter("day");
		System.out.println("daystr:" + daystr);
		Date day = df.parse(daystr);

		PinfaEventDao pinfaEventDao =  PinfaEventDao.getInstance();
		String dimtype, dimvalue, dimtype1=null, dimvalue1=null, dimtype2=null, dimvalue2=null;
		String dimstr = request.getParameter("dim");
		int dim = Integer.parseInt(dimstr);

		String dimtypestr =  request.getParameter("dimtype") ;
		String dimvaluestr = request.getParameter("dimvalue");
		System.out.println("detail-dimtypestr:" + dimtypestr + "  dimvaluestr:"
				+ dimvaluestr);
// 		dimtype = new String(dimtypestr.getBytes("ISO-8859-1"), "GBK");
// 		dimvalue = new String(dimvaluestr.getBytes("ISO-8859-1"), "GBK");
dimtype =dimtypestr;
dimvalue =dimvaluestr;
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
					%><%="【"+dimtype + "】:" + dimvalue%>
					<%
						} else if (dim==2) {
					%><%="【"+dimtype1 + "】:" + dimvalue1 + " 【" + dimtype2 + "】:"
						+ dimvalue2%>
					<%
						}
					%>（<%=daystr%>）
				</caption>
				<tr>
					<th>标题</th>
					<th>上报日期</th>
					<th>发生地区</th>
					<th>发生地点</th>
					<th>航空器机型</th>
<!-- 					<th>机型大类</th> -->
					<th>航空器机号</th>
					<th>航空器使用单位</th>
<!-- 					<th>事件后果</th> -->
					<th>事件等级</th>
					<th>等级大类</th>
					<th>事件条款</th>
					<th>条款大类</th>
					<th>责任单位</th>
					<th>事件类型</th>
					<th>类型大类</th>
					<th>事件原因</th>
					<th>原因大类</th>
<!-- 					<th>原因因素</th> -->
					<th>风险值</th>
					<th>飞行阶段</th>
					<th>事件性质</th>
<!-- 					<th>最后起飞点</th> -->
<!-- 					<th>计划目的地</th> -->
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
<%-- 					<td><%=t.getJixingdalei()%></td> --%>
					<td><%=t.getHangkongqijihao()%></td>
					<td><%=t.getHangkongqishiyongdanwei()%></td>
<%-- 					<td><%=t.getShijianhouguo()%></td> --%>
					<td><%=t.getShijiandengji()%></td>
					<td><%=t.getDengjidalei()%></td>
					<td><%=t.getShijiantiaokuan()%></td>
					<td><%=t.getTiaokuandalei()%></td>
					<td><%=t.getZerendanwei()%></td>
					<td><%=t.getShijianleixing()%></td>
					<td><%=t.getLeixingdalei()%></td>
					<td><%=t.getShijianyuanyin()%></td>
					<td><%=t.getYuanyindalei()%></td>
<%-- 					<td><%=t.getYuanyinyinsu()%></td> --%>
					<td><%=t.getFengxianzhi()%></td>
					<td><%=t.getFeixingjieduan()%></td>
					<td><%=t.getShijianxingzhi()%></td>
<%-- 					<td><%=t.getZuihouqifeidian()%></td> --%>
<%-- 					<td><%=t.getJihuamudidi()%></td> --%>
				</tr>
				<%
					}
				%>
			</table>

		</div>
	</div>
</body>
</html>
