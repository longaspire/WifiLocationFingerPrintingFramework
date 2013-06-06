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
 * Servlet implementation class TotalStastics
 */
@WebServlet("/TotalStastics")
public class TotalStastics extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TotalStastics() {
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
		int k = 7;
		int numOfEachGroup = 20;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("	<HEAD><TITLE>Query Result</TITLE></HEAD>");
		out.println("	<BODY>");
		out.println("<br />");

		try {

			for (int knum = 13; knum <= 15; knum++) {
				out.println("<br />");
				out.println("<br />");
				for (int c = 0; c < 10; c++) {
					sum = 0;
					List<List<FingerPrint>> result = fingerPrintDAO
							.getAllFingerPrintRandomly(k, numOfEachGroup);
					totalRandomFingerPrint = result.get(0);
					totalNonRandomFingerPrint = result.get(1);
					RadioMap.getInstance().loadRadioMapWithPara(
							totalNonRandomFingerPrint);
					for (int i = 0; i < totalRandomFingerPrint.size(); i++) {
						FingerPrint query = totalRandomFingerPrint.get(i);
						WKnnTracker wknnTracker = new WKnnTracker(knum);
						int positioning_result = wknnTracker
								.getLoctionGridID(query.getItemrowObject());
						if (positioning_result == query.getGridid()) {
							sum++;
						}
					}
					out.println(knum + "/" + (c + 1));
					out.println("<br />");
					out.println((double) sum
							/ totalRandomFingerPrint.size());
					out.println("<br />");
				}
			}
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
