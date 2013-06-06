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
import cn.edu.zju.bean.RadioMap;
import cn.edu.zju.dao.FingerPrintDAO;

/**
 * Servlet implementation class GenerateDataAndLoadRadioMap
 */
@WebServlet(description = "Generate Data And Load RadioMap", urlPatterns = { "/GenerateDataAndLoadRadioMap" })
public class GenerateDataAndLoadRadioMap extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerateDataAndLoadRadioMap() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FingerPrintDAO fingerPrintDAO = new FingerPrintDAO();
		List<FingerPrint> totalRandomFingerPrint;
		List<FingerPrint> totalNonRandomFingerPrint;

		try {
			List<List<FingerPrint>> result = fingerPrintDAO
					.getAllFingerPrintRandomly(5, 50);

			totalRandomFingerPrint = result.get(0);
			totalNonRandomFingerPrint = result.get(1);

			RadioMap.getInstance().loadRadioMapWithPara(
					totalNonRandomFingerPrint);

			System.out.println(totalRandomFingerPrint.size());
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("	<HEAD><TITLE>View All the Queries</TITLE></HEAD>");
			out.println("	<BODY>");
			out.println("<br />");

			out.println("<a href=\"index.jsp\">Go back Home</a>");
			out.println("<table border=\"1\">");
			out.println("<tr>");
			out.println("<td>" + "recordid" + "</td>");
			out.println("<td>" + "itemrows" + "</td>");
			out.println("</tr>");
			for (int i = 0; i < totalRandomFingerPrint.size(); i++) {
				out.println("<tr>");
				out.println("<td>" + "<a href=\"ServerQuery?recordid="
						+ totalRandomFingerPrint.get(i).getRecordid() + "\">"
						+ totalRandomFingerPrint.get(i).getRecordid() + "</a>"
						+ "</td>");
				out.println("<td>" + totalRandomFingerPrint.get(i).getItemrow()
						+ "</td>");

				out.println("</tr>");
			}
			out.println("</table>");
			out.println("	</BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
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

	}

}
