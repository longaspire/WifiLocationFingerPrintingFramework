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
import cn.edu.zju.tracker.WKnnTracker;

/**
 * Servlet implementation class ClassificationTest
 */
@WebServlet(description = "ClassificationTest", urlPatterns = { "/ClassificationTest" })
public class ClassificationTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClassificationTest() {
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
		double sum = 0;
		int begin = 1;
		int end = 7;
		int numOfEachGroup = 100;
		int accuracy[] = new int[end];
		for (int item : accuracy) {
			item = 0;
		}

		try {
			List<List<FingerPrint>> result = fingerPrintDAO
					.getAllFingerPrintRandomly(begin, end, numOfEachGroup);

			totalRandomFingerPrint = result.get(0);
			totalNonRandomFingerPrint = result.get(1);

			if (RadioMap.getInstance().loadRadioMapWithPara(
					totalNonRandomFingerPrint)) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
				out.println("<HTML>");
				out.println("	<HEAD><TITLE>Query Result</TITLE></HEAD>");
				out.println("	<BODY>");
				out.println("<br />");
				out.println("<table border=\"1\">");
				out.println("<tr>");
				out.println("<td>");
				out.println("queryID");
				out.println("</td>");
				out.println("<td>");
				out.println("ground_truth");
				out.println("</td>");
				out.println("<td>");
				out.println("positioning_ID");
				out.println("</td>");
				out.println("<td>");
				out.println("Accuracy");
				out.println("</td>");

				out.println("</tr>");
				for (int i = 0; i < totalRandomFingerPrint.size(); i++) {
					FingerPrint query = totalRandomFingerPrint.get(i);
					WKnnTracker wknnTracker = new WKnnTracker(4);
					int positioning_result = wknnTracker.getLoctionGridID(query
							.getItemrowObject());
					out.println("<tr>");
					out.println("<td>");
					out.println(query.getRecordid());
					out.println("</td>");
					out.println("<td>");
					out.println(query.getGridid());
					out.println("</td>");
					out.println("<td>");
					out.println(positioning_result);
					out.println("</td>");
					if (positioning_result == query.getGridid()) {
						out.println("<td>");
						out.println("True");
						out.println("</td>");
						sum++;
						accuracy[query.getGridid()-1]++;
					} else {
						out.println("<td>");
						out.println("False");
						out.println("</td>");
					}
					out.println("</tr>");
				}
				out.println("</table>");
				out.println("<br />");
				out.println("<table border=\"1\">");
				out.println("<tr>");
				out.println("<td>");
				out.println("RoomID");
				out.println("</td>");
				out.println("<td>");
				out.println("Correct Number");
				out.println("</td>");
				out.println("<td>");
				out.println("Total Number");
				out.println("</td>");
				out.println("<td>");
				out.println("Precision");
				out.println("</td>");
				out.println("</tr>");
				
				for (int i = (begin - 1); i < accuracy.length; i++) {
					out.println("<tr>");
					out.println("<td>");
					out.println((i+1));
					out.println("</td>");
					out.println("<td>");
					out.println(accuracy[i]);
					out.println("</td>");
					out.println("<td>");
					out.println(numOfEachGroup);
					out.println("</td>");
					out.println("<td>");
					out.println((double)accuracy[i]/numOfEachGroup);
					out.println("</td>");
					out.println("</tr>");
				}
				
				out.println("<tr>");
				out.println("<td>");
				out.println("Total");
				out.println("</td>");
				out.println("<td>");
				out.println((int)sum);
				out.println("</td>");
				out.println("<td>");
				out.println(totalRandomFingerPrint.size());
				out.println("</td>");
				out.println("<td>");
				out.println((double) sum / totalRandomFingerPrint.size());
				out.println("</td>");
				out.println("</tr>");
				
				out.println("</table>");
				out.println("	</BODY>");
				out.println("</HTML>");
				out.flush();
				out.close();
			} else {
				response.sendRedirect("error.jsp");
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
	}

}
