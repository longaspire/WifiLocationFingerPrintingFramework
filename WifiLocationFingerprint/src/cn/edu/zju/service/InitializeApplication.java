package cn.edu.zju.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zju.bean.GridMap;
import cn.edu.zju.bean.RadioMap;

/**
 * Servlet implementation class InitializeApplication
 */
@WebServlet(description = "Initialize the application", urlPatterns = { "/InitializeApplication" })
public class InitializeApplication extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitializeApplication() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			if(GridMap.getInstance().loadGridMap() && RadioMap.getInstance().loadRadioMap())
			{
				response.setContentType("text/html"); 
				PrintWriter out = response.getWriter(); 
				out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">"); 
				out.println("<HTML>"); 
				out.println("	<HEAD><TITLE>Initialize Application</TITLE></HEAD>"); 
				out.println("	<BODY>"); 
				out.println("<i>Initialization Compeleted!</i>");
				out.println("<br />");
				out.println("<br />");
				out.println("<a href=\"index.jsp\">Go back Home</a>");
				out.println("	</BODY>"); 
				out.println("</HTML>"); 
				out.flush(); 
				out.close(); 	
			}else
			{
				response.sendRedirect("error.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.sendRedirect("error.jsp");
			e.printStackTrace();
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
