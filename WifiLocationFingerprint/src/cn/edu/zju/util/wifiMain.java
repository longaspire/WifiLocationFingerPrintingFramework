package cn.edu.zju.util;

import cn.edu.zju.dao.GridDAO;

public class wifiMain {

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// RadioMap radioMap = RadioMap.getInstance();

		// WKnnTracker wKnnTracker = new WKnnTracker(2);
		//
		// wKnnTracker.getLoctionIndex(radioMap.m_radioMap.get(0).second);
		
		//FingerPrintDAO fingerprintdao = new FingerPrintDAO();
		GridDAO griddao = new GridDAO();
		
		//fingerprintdao.initFingerPrintTable();
		griddao.initGridTable();
		
		/*
		List<FingerPrint> allfingerprint = fingerprintdao.getAllFingerPrint();

		FingerPrint fp1 = allfingerprint.get(0);
		FingerPrint fp2 = allfingerprint.get(1);

		ItemRows ir1 = new ItemRows(fp1.getItemrow());

		ItemRows ir2 = new ItemRows(fp2.getItemrow());

		System.out.println(ir2.calManhattonDistance(ir1));
		*/
		
		int row = 5;
		int col = 10;
		double x = 0;
		double y = 0;
		for (int i = 1; i <= row; i++) {
			x = 1.232189;
			x = x * i;
			for (int j = 1; j <= col; j++) {
				y = 3.423333;
				y = y * j;
				griddao.insertGridTable((i - 1) * col + j, x, y, "test data");
			}
		} //
		griddao.insertGridTable(34, 2.4345, 4.12321312, "this is a test");

		System.exit(0);
	}

}
