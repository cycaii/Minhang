package minhang.servlet;


import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.table.DefaultTableModel;

import minhang.dao.DBWorker;
import minhang.entity.YiweiResult;


public class Tongji extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Tongji() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();

		int year= Integer.parseInt(request.getParameter("year").toString());
		String leixing=request.getParameter("province").toString();
		int xuhao=Integer.parseInt(request.getParameter("city").toString());
		String name=request.getParameter("duibijizhun").toString();
		
		DBWorker dbworker=new DBWorker();
		
		Vector<YiweiResult> yrs=dbworker.getVector(year, leixing, xuhao,name);
	
		Comparator mycomp = new MyComparator();
		Collections.sort(yrs, mycomp);
		
		session.setAttribute("yrs", yrs);
		
		response.sendRedirect("pinfa_tongji.jsp?yemianID=2");
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	 class MyComparator implements Comparator<YiweiResult>{

			
			public int compare(YiweiResult y1, YiweiResult y2) {
				// TODO Auto-generated method stub
				float bfb1=y1.getPercent();
				float bfb2=y2.getPercent();
				if(bfb1<bfb2){
					return 1;
				}else if(bfb1>bfb2){
					return -1;
				}else{
					return 0;
				}
			}
		
	}
}
