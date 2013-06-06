package cn.edu.zju.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zju.dao.FingerPrintDAO;

/**
 * Servlet implementation class AddRadioMap
 */
@WebServlet(description = "add a itemrow into radio map", urlPatterns = { "/AddRadioMap" })
public class AddRadioMap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRadioMap() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String deviceid = (String)request.getParameter("deviceID");
		String gridid = (String)request.getParameter("gridId");
		String itemrows = (String)request.getParameter("ItemRows");
		//System.out.println(itemrows);
		//JSONObject jsonObject = JSONObject.fromObject(itemrows); 
		//ItemRows ir = (ItemRows)JSONObject.toBean(jsonObject, ItemRows.class);
		
		System.out.println("itemrow: " + itemrows);
		System.out.println("gridid: " + gridid);
		System.out.println("deviceid: " + deviceid);
		
		FingerPrintDAO fingerprintdao = new FingerPrintDAO();
		if(fingerprintdao.insertFingerPrintTable(itemrows, Integer.parseInt(gridid), deviceid) == 0)
		{
			response.setCharacterEncoding("gbk");
			PrintWriter out = response.getWriter();
			
			out.print("<?xml version=\"1.0\" encoding=\"gbk\"?>");
			out.print("<wlf>");
			out.print("<code>");
			out.print("001");
			out.print("</code>");
			out.print("</wlf>");
			out.flush();
			out.close();
		}else {
			response.setCharacterEncoding("gbk");
			PrintWriter out = response.getWriter();
			
			out.print("<?xml version=\"1.0\" encoding=\"gbk\"?>");
			out.print("<wlf>");
			out.print("<code>");
			out.print("401");
			out.print("</code>");
			out.print("</wlf>");
			out.flush();
			out.close();
		}
	}

}
