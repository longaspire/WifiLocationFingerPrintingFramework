package cn.edu.zju.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zju.bean.FingerPrint;
import cn.edu.zju.bean.Grid;
import cn.edu.zju.bean.IndoorPoints;
import cn.edu.zju.dao.GridDAO;
import cn.edu.zju.dao.TestQueryDAO;
import cn.edu.zju.tracker.WKnnTracker;

/**
 * Servlet implementation class GetQueryReport
 */
@WebServlet(description = "Get Query Report", urlPatterns = { "/GetQueryReport" })
public class GetQueryReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public double calDistance(double x1,double y1, double x2, double y2){
		return Math.sqrt((double)(x1-x2)*(double)(x1-x2) + (double)(y1-y2)*(double)(y1-y2));
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetQueryReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TestQueryDAO testQueryDAO = new TestQueryDAO();
		int count = 0;
		double sum = 0;
		try {
			List<FingerPrint> allqueries = testQueryDAO.getAllFingerPrint();
			response.setContentType("text/html"); 
			PrintWriter out = response.getWriter(); 
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">"); 
			out.println("<HTML>"); 
			out.println("	<HEAD><TITLE>Query Result</TITLE></HEAD>"); 
			out.println("	<BODY>");
			out.println("<br />");
			out.println("<table border=\"1\">");
			out.println("<tr>");
			out.println("<td>");out.println("queryID");out.println("</td>");
			out.println("<td>");out.println("ground_truth_X");out.println("</td>");
			out.println("<td>");out.println("positioning_X");out.println("</td>");
			out.println("<td>");out.println("ground_truth_Y");out.println("</td>");
			out.println("<td>");out.println("positioning_Y");out.println("</td>");
			out.println("<td>");out.println("Distance");out.println("</td>");
			out.println("</tr>");
			for(int i = 0; i < allqueries.size(); i++){
				FingerPrint query = allqueries.get(i);
				WKnnTracker wknnTracker = new WKnnTracker(7);
				IndoorPoints resultIndoorPoints = wknnTracker
						.getLoctionIndex(query.getItemrowObject());
				GridDAO gridDAO = new GridDAO();
				Grid grid = gridDAO.getGridByGridid(query.getGridid());
				
				out.println("<tr>");
				out.println("<td>");out.println(query.getRecordid());out.println("</td>");
				out.println("<td>");out.println(grid.getX_coordinate());out.println("</td>");
				out.println("<td>");out.println(resultIndoorPoints.getX_coordinate());out.println("</td>");
				out.println("<td>");out.println(grid.getY_coordinate());out.println("</td>");
				out.println("<td>");out.println(resultIndoorPoints.getY_coordinate());out.println("</td>");
				out.println("<td>");
				double dis = calDistance(grid.getX_coordinate(), grid.getY_coordinate(), resultIndoorPoints.getX_coordinate(), resultIndoorPoints.getY_coordinate());
				if(dis <= 2){
					count++;
				}
				sum += dis;
				out.println(dis);
				out.println("</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("<br />");
			out.println("The precision within 2m :" + count + " / " + allqueries.size() + "=" +  ((double)count/allqueries.size()));
			out.println("The average distance :" + sum + " / " + allqueries.size() + "=" +  (sum/allqueries.size()));
			out.println("	</BODY>"); 
			out.println("</HTML>"); 
			out.flush(); 
			out.close(); 	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
