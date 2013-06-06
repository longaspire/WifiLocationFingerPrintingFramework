package cn.edu.zju.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zju.bean.FingerPrint;
import cn.edu.zju.dao.FingerPrintDAO;
import cn.edu.zju.tracker.WKnnTracker;

/**
 * Servlet implementation class ServerQuery
 */
@WebServlet(description = "The Query From Server", urlPatterns = { "/ServerQuery" })
public class ServerQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServerQuery() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public double calDistance(double x1,double y1, double x2, double y2){
		return Math.sqrt((double)(x1-x2)*(double)(x1-x2) + (double)(y1-y2)*(double)(y1-y2));
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String recordid = (String) request.getParameter("recordid");
		System.out.println(recordid);
		FingerPrintDAO fingerPrintDAO = new FingerPrintDAO();
		try {
			FingerPrint fingerPrint = fingerPrintDAO.getFingerPrintByRecordid(Integer.parseInt(recordid));
			WKnnTracker wknnTracker = new WKnnTracker(10);
			int result = -1;
			result = wknnTracker.getLoctionGridID(fingerPrint.getItemrowObject());
			
			if (result != -1) {
				
				response.setContentType("text/html"); 
				PrintWriter out = response.getWriter(); 
				out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">"); 
				out.println("<HTML>"); 
				out.println("	<HEAD><TITLE>Query Result</TITLE></HEAD>"); 
				out.println("	<BODY>"); 
				out.println("<h2>Query " + fingerPrint.getRecordid() + "</h2>");
				out.println(fingerPrint.getItemrow());
				out.println("<br />");
				out.println("<br />");
				out.println(result);
				out.println(fingerPrint.getGridid());
				boolean flag = (result == fingerPrint.getGridid());
				out.println(flag);
				out.println("	</BODY>"); 
				out.println("</HTML>"); 
				out.flush(); 
				out.close(); 	
				
				/*
				response.setCharacterEncoding("gbk");
				PrintWriter out = response.getWriter();

				out.print("<?xml version=\"1.0\" encoding=\"gbk\"?>");
				out.print("<wlf>");
				out.print("<code>");
				out.print("001");
				out.print("</code>");

				out.print("<xcoordinate>");
				out.print(resultIndoorPoints.getX_coordinate());
				out.print("</xcoordinate>");

				out.print("<ycoordinate>");
				out.print(resultIndoorPoints.getY_coordinate());
				out.print("</ycoordinate>");

				out.print("<ground_truth_xcoordinate>");
				out.print(grid.getX_coordinate());
				out.print("</ground_truth_xcoordinate>");

				out.print("<ground_truth_ycoordinate>");
				out.print(grid.getY_coordinate());
				out.print("</ground_truth_ycoordinate>");

				out.print("</wlf>");
				out.flush();
				out.close();
				*/
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.sendRedirect("error.jsp");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
