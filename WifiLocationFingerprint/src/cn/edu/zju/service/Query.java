package cn.edu.zju.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zju.bean.ItemRows;
import cn.edu.zju.tracker.WKnnTracker;

/**
 * Servlet implementation class Query
 */
@WebServlet(description = "to execute a indoor positioning query", urlPatterns = { "/Query" })
public class Query extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Query() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// String deviceid = "GT-I9300";
		String deviceString = request.getParameter("deviceId");
		String itemrowString = request.getParameter("ItemRows");
		// String itemrowString =
		// "[{\"BSSID\":\"80:f6:2e:15:03:90\",\"RSSI\":-54},{\"BSSID\":\"80:f6:2e:15:03:91\",\"RSSI\":-54},{\"BSSID\":\"6c:e8:73:74:b3:c4\",\"RSSI\":-58},{\"BSSID\":\"80:f6:2e:15:08:b1\",\"RSSI\":-68},{\"BSSID\":\"80:f6:2e:1e:4c:11\",\"RSSI\":-68},{\"BSSID\":\"8c:21:0a:85:01:d0\",\"RSSI\":-68},{\"BSSID\":\"80:f6:2e:15:08:90\",\"RSSI\":-69},{\"BSSID\":\"80:f6:2e:15:08:91\",\"RSSI\":-69},{\"BSSID\":\"80:f6:2e:15:08:b0\",\"RSSI\":-69},{\"BSSID\":\"80:f6:2e:1e:4c:10\",\"RSSI\":-70},{\"BSSID\":\"80:f6:2e:15:08:51\",\"RSSI\":-72},{\"BSSID\":\"80:f6:2e:15:08:50\",\"RSSI\":-73},{\"BSSID\":\"20:dc:e6:5d:e2:34\",\"RSSI\":-75},{\"BSSID\":\"80:f6:2e:15:04:90\",\"RSSI\":-79},{\"BSSID\":\"80:f6:2e:15:08:e1\",\"RSSI\":-81},{\"BSSID\":\"80:f6:2e:15:08:e0\",\"RSSI\":-82},{\"BSSID\":\"c4:ca:d9:ee:2c:f0\",\"RSSI\":-82},{\"BSSID\":\"80:f6:2e:15:04:70\",\"RSSI\":-83},{\"BSSID\":\"80:f6:2e:15:04:71\",\"RSSI\":-83},{\"BSSID\":\"c4:ca:d9:ee:2c:f1\",\"RSSI\":-83},{\"BSSID\":\"80:f6:2e:1e:4c:31\",\"RSSI\":-85},{\"BSSID\":\"80:f6:2e:15:0b:f0\",\"RSSI\":-88},{\"BSSID\":\"80:f6:2e:15:0b:f1\",\"RSSI\":-88},{\"BSSID\":\"20:dc:e6:5d:e2:7c\",\"RSSI\":-90},{\"BSSID\":\"c4:ca:d9:ed:b6:11\",\"RSSI\":-90},{\"BSSID\":\"80:f6:2e:15:08:f1\",\"RSSI\":-93},{\"BSSID\":\"80:f6:2e:15:08:f0\",\"RSSI\":-94}]";
		// "[{\"BSSID\":\"80:f6:2e:15:03:90\",\"RSSI\":-54},{\"BSSID\":\"80:f6:2e:15:03:91\",\"RSSI\":-54},{\"BSSID\":\"6c:e8:73:74:b3:c4\",\"RSSI\":-58},{\"BSSID\":\"80:f6:2e:15:08:b1\",\"RSSI\":-68},{\"BSSID\":\"80:f6:2e:1e:4c:11\",\"RSSI\":-68},{\"BSSID\":\"8c:21:0a:85:01:d0\",\"RSSI\":-68},{\"BSSID\":\"80:f6:2e:15:08:90\",\"RSSI\":-69},{\"BSSID\":\"80:f6:2e:15:08:91\",\"RSSI\":-69},{\"BSSID\":\"80:f6:2e:15:08:b0\",\"RSSI\":-69},{\"BSSID\":\"80:f6:2e:1e:4c:10\",\"RSSI\":-70},{\"BSSID\":\"80:f6:2e:15:08:51\",\"RSSI\":-72},{\"BSSID\":\"80:f6:2e:15:08:50\",\"RSSI\":-73},{\"BSSID\":\"20:dc:e6:5d:e2:34\",\"RSSI\":-75},{\"BSSID\":\"80:f6:2e:15:04:90\",\"RSSI\":-79},{\"BSSID\":\"80:f6:2e:15:08:e1\",\"RSSI\":-81},{\"BSSID\":\"80:f6:2e:15:08:e0\",\"RSSI\":-82},{\"BSSID\":\"c4:ca:d9:ee:2c:f0\",\"RSSI\":-82},{\"BSSID\":\"80:f6:2e:15:04:70\",\"RSSI\":-83},{\"BSSID\":\"80:f6:2e:15:04:71\",\"RSSI\":-83},{\"BSSID\":\"c4:ca:d9:ee:2c:f1\",\"RSSI\":-83},{\"BSSID\":\"80:f6:2e:1e:4c:31\",\"RSSI\":-85},{\"BSSID\":\"80:f6:2e:15:0b:f0\",\"RSSI\":-88},{\"BSSID\":\"80:f6:2e:15:0b:f1\",\"RSSI\":-88},{\"BSSID\":\"20:dc:e6:5d:e2:7c\",\"RSSI\":-90},{\"BSSID\":\"c4:ca:d9:ed:b6:11\",\"RSSI\":-90},{\"BSSID\":\"80:f6:2e:15:08:f1\",\"RSSI\":-93},{\"BSSID\":\"80:f6:2e:15:08:f0\",\"RSSI\":-94}]";
		// "[{\"BSSID\":\"80:f6:2e:15:03:90\",\"RSSI\":-51},{\"BSSID\":\"80:f6:2e:15:03:91\",\"RSSI\":-54},{\"BSSID\":\"6c:e8:73:74:b3:c4\",\"RSSI\":-58},{\"BSSID\":\"80:f6:2e:15:08:b1\",\"RSSI\":-68},{\"BSSID\":\"80:f6:2e:1e:4c:11\",\"RSSI\":-68},{\"BSSID\":\"8c:21:0a:85:01:d0\",\"RSSI\":-68},{\"BSSID\":\"80:f6:2e:15:08:90\",\"RSSI\":-69},{\"BSSID\":\"80:f6:2e:15:08:91\",\"RSSI\":-69},{\"BSSID\":\"80:f6:2e:15:08:b0\",\"RSSI\":-69},{\"BSSID\":\"80:f6:2e:1e:4c:10\",\"RSSI\":-70},{\"BSSID\":\"80:f6:2e:15:08:51\",\"RSSI\":-72},{\"BSSID\":\"80:f6:2e:15:08:50\",\"RSSI\":-73},{\"BSSID\":\"20:dc:e6:5d:e2:34\",\"RSSI\":-75},{\"BSSID\":\"80:f6:2e:15:04:90\",\"RSSI\":-79},{\"BSSID\":\"80:f6:2e:15:08:e1\",\"RSSI\":-81},{\"BSSID\":\"80:f6:2e:15:08:e0\",\"RSSI\":-82},{\"BSSID\":\"c4:ca:d9:ee:2c:f0\",\"RSSI\":-82},{\"BSSID\":\"80:f6:2e:15:04:70\",\"RSSI\":-83},{\"BSSID\":\"80:f6:2e:15:04:71\",\"RSSI\":-83},{\"BSSID\":\"c4:ca:d9:ee:2c:f1\",\"RSSI\":-83},{\"BSSID\":\"80:f6:2e:1e:4c:31\",\"RSSI\":-85},{\"BSSID\":\"80:f6:2e:15:0b:f0\",\"RSSI\":-88},{\"BSSID\":\"80:f6:2e:15:0b:f1\",\"RSSI\":-88},{\"BSSID\":\"20:dc:e6:5d:e2:7c\",\"RSSI\":-90},{\"BSSID\":\"c4:ca:d9:ed:b6:11\",\"RSSI\":-90},{\"BSSID\":\"80:f6:2e:15:08:f1\",\"RSSI\":-93},{\"BSSID\":\"80:f6:2e:15:08:f0\",\"RSSI\":-94}]";
		System.out.println(itemrowString);
		ItemRows itemrows = new ItemRows(itemrowString);
		int gridid = -1;
		WKnnTracker wknnTracker = new WKnnTracker(4);
		try {
			gridid = wknnTracker.getLoctionGridID(itemrows);
			if (gridid != -1) {
				response.setCharacterEncoding("gbk");
				PrintWriter out = response.getWriter();

				out.print("<?xml version=\"1.0\" encoding=\"gbk\"?>");
				out.print("<wlf>");
				out.print("<code>");
				out.print("001");
				out.print("</code>");

				out.print("<gridid>");
				out.print(gridid);
				out.print("</gridid>");

				out.print("</wlf>");
				out.flush();
				out.close();
			} else {
				response.setCharacterEncoding("gbk");
				PrintWriter out = response.getWriter();

				out.print("<?xml version=\"1.0\" encoding=\"gbk\"?>");
				out.print("<wlf>");
				out.print("<code>");
				out.print("400");
				out.print("</code>");

				out.print("</wlf>");
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.sendRedirect("error.jsp");
			e.printStackTrace();
		}
	}

}
