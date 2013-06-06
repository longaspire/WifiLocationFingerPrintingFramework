package cn.edu.zju.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zju.util.GetTestData;

/**
 * Servlet implementation class LoadTestData
 */
@WebServlet(description = "Load the Test Data", urlPatterns = { "/LoadTestData" })
public class LoadTestData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadTestData() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(GetTestData.GenerateData()){
			response.setContentType("text/html"); 
			PrintWriter out = response.getWriter(); 
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">"); 
			out.println("<HTML>"); 
			out.println("	<HEAD><TITLE>Load Test Data</TITLE></HEAD>"); 
			out.println("	<BODY>"); 
			out.println("<i>Loading Test Data Compeleted!</i>");
			out.println("<br />");
			out.println("<br />");
			out.println("<a href=\"index.jsp\">Go back Home</a>");
			out.println("	</BODY>"); 
			out.println("</HTML>"); 
			out.flush(); 
			out.close(); 	
		}else{
			response.sendRedirect("error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
