package cn.edu.zju.util;

import java.util.List;

import cn.edu.zju.bean.FingerPrint;
import cn.edu.zju.bean.ItemRows;
import cn.edu.zju.dao.FingerPrintDAO;

public class TestCalDistance {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FingerPrintDAO fingerprintdao = new FingerPrintDAO();
		List<FingerPrint> allfingerprint = fingerprintdao.getAllFingerPrint();

		FingerPrint fp1 = allfingerprint.get(1);
		ItemRows ir1 = new ItemRows(fp1.getItemrow());
		for(int i = 0; i< allfingerprint.size(); i++){
			FingerPrint fp2 = allfingerprint.get(i);
			ItemRows ir2 = new ItemRows(fp2.getItemrow());
			System.out.println(i+" "+ir2.calManhattonDistance(ir1));
		}
		
	}

}
