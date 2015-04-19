<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="minhang.dao.*,minhang.entity.*,java.util.*,minhang.service.*,minhang.helper.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");

		String yearStr = request.getParameter("year").toString();
		String xuhaoStr = request.getParameter("city").toString();
		int year = Integer.parseInt(yearStr);
		int xuhao = Integer.parseInt(xuhaoStr);
		String leixing = request.getParameter("province").toString();
		String name = request.getParameter("duibijizhun").toString();
		System.out.println("tongjiServlet--year:"+year+"  xuhao:"+xuhao+"  province:"+leixing+"   name:"+name);
		session.setAttribute("year", yearStr);
		session.setAttribute("xuhao", xuhaoStr);
		session.setAttribute("leixing", leixing);
		session.setAttribute("name", name);
// 		session.setAttribute("yemianID", 2);
		
		DBWorker dbworker = new DBWorker();

		Vector<YiweiResult> yrs = dbworker.getVector(year, leixing, xuhao,
				name);

		Comparator mycomp = new MyComparator();
		Collections.sort(yrs, mycomp);

		session.setAttribute("yrs", yrs);

 	response.sendRedirect("pinfa_zhishu.jsp?yemianID=2");
	%>
<%-- <jsp:forward page="pinfa_tongji.jsp" />  --%>
</body>
</html>