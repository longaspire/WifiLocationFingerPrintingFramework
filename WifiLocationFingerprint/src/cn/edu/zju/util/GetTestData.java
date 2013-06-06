package cn.edu.zju.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.edu.zju.bean.ItemRows;
import cn.edu.zju.dao.TestQueryDAO;

public class GetTestData {

	/**
	 * @param args
	 */
	
	private static String filepath_testfile = "C:/Users/Steven/workspace/WifiLocationFingerprint/benchmark/Task_1_test_data.txt";
	private static String filepath_groundtruth = "C:/Users/Steven/workspace/WifiLocationFingerprint/benchmark/Task_1_ground_truth.txt";
	
	
	
	public static boolean GenerateData(){
		
		TestQueryDAO testQueryDAO = new TestQueryDAO();
		testQueryDAO.initTestQueryTable();
		
		////////////////////////////////////////////test fingerprint data ///////////////////////
		File dataFile = new File(filepath_testfile);

		if (!dataFile.exists() || !dataFile.isFile()) {
			System.err.println("Invalid file path!");
			return false;
		}
		
		String itemString = null;
		BufferedReader breader = null;
		try {
			breader = new BufferedReader(new InputStreamReader(
					new FileInputStream(dataFile)));
			while ((itemString = breader.readLine()) != null) {
				
				String[] itemStrings = itemString.split("	");
				if(!(itemStrings[0].substring(0, 5).equals("Trace"))){
					testQueryDAO.insertTestQueryTable(parseFingerPrint(itemStrings,1),0);
				}							
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (breader != null) {
				try {
					breader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		dataFile = new File(filepath_groundtruth);

		if (!dataFile.exists() || !dataFile.isFile()) {
			System.err.println("Invalid file path!");
			return false;
		}
		
		itemString = null;
		breader = null;
		int count = 1;
		try {
			breader = new BufferedReader(new InputStreamReader(
					new FileInputStream(dataFile)));
			while ((itemString = breader.readLine()) != null) {
				if(!(itemString.substring(0, 1).equals("T"))){
					testQueryDAO.updateTestQueryTableByGridid(Integer.parseInt(itemString), count);
					count++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (breader != null) {
				try {
					breader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		GenerateData();
		
	}
	
	private static String parseFingerPrint(String[] itemStrings, int begin) {
		// TODO Auto-generated method stub
		
		ItemRows itemrows = new ItemRows();
		for(int i = begin; i < itemStrings.length; i++){
			//System.out.println(parseItemRow(itemStrings[i],0));
			//System.out.println(parseItemRow(itemStrings[i],1));
			itemrows.add(String.valueOf(parseItemRow(itemStrings[i],0)), parseItemRow(itemStrings[i],1));
		}
		System.out.println(itemrows.toJSONString());
		return itemrows.toJSONString();
	}
	

	private static int parseItemRow(String s, int type) {
		// TODO Auto-generated method stub
		String[] items = s.split(":");
		return Integer.parseInt(items[type]);
	}


}
