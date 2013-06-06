package cn.edu.zju.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.edu.zju.bean.FingerPrint;
import cn.edu.zju.bean.Grid;
import cn.edu.zju.bean.ItemRows;
import cn.edu.zju.dao.FingerPrintDAO;
import cn.edu.zju.dao.GridDAO;

public class GetTrainingData {

	/**
	 * @param args
	 */
	
	private static String filepath_grid = "C:/Users/Steven/workspace/WifiLocationFingerprint/benchmark/map_coordinates.txt";
	private static String filepath_fingerprint_1 = "C:/Users/Steven/workspace/WifiLocationFingerprint/benchmark/Task_1_notrace_trainning_data.txt";
	private static String filepath_fingerprint_2 = "C:/Users/Steven/workspace/WifiLocationFingerprint/benchmark/Task_1_trace_trainning_data.txt";
	private static String filepath_fingerprint_3 = "C:/Users/Steven/workspace/WifiLocationFingerprint/benchmark/Task_2_training_data.txt";
	
	public static boolean GenerateData(){
		FingerPrintDAO fingerprintdao = new FingerPrintDAO();
		GridDAO griddao = new GridDAO();
				
		fingerprintdao.initFingerPrintTable();
		griddao.initGridTable();
		
		
		////////////////////////////////////////////gird data ///////////////////////
		File dataFile = new File(filepath_grid);

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
				Grid grid = new Grid();
				String[] itemStrings = itemString.split("		");
				//System.out.println(itemString);
				for(int i = 0; i < itemStrings.length; i++){
					grid.setGridid(Integer.parseInt(itemStrings[0]));
					grid.setX_coordinate(parseCoordiante(itemStrings[1],0));
					grid.setY_coordinate(parseCoordiante(itemStrings[1],1));
					//System.out.println("grid:" + grid.getGridid());
					//System.out.println("X:" + grid.getX_coordinate());
					//System.out.println("Y:" + grid.getY_coordinate());
				}
				griddao.insertGridTable(grid);
				//System.out.println(itemStrings[1]);
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
		
		////////////////////////////////////////////fingerprint data ///////////////////////
		dataFile = new File(filepath_fingerprint_1);

		if (!dataFile.exists() || !dataFile.isFile()) {
			System.err.println("Invalid file path!");
			return false;
		}
		
		itemString = null;
		breader = null;
		try {
			breader = new BufferedReader(new InputStreamReader(
					new FileInputStream(dataFile)));
			while ((itemString = breader.readLine()) != null) {
				FingerPrint fingerPrint = new FingerPrint();
				String[] itemStrings = itemString.split("	");
				if(Integer.parseInt(itemStrings[0]) != -1){
					fingerPrint.setItemrow(parseFingerPrint(itemStrings,1));
					fingerPrint.setGridid(Integer.parseInt(itemStrings[0]));
					fingerprintdao.insertFingerPrintTable(fingerPrint);
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
		
		////////////////////////////////////////////fingerprint data ///////////////////////
		dataFile = new File(filepath_fingerprint_2);

		if (!dataFile.exists() || !dataFile.isFile()) {
			System.err.println("Invalid file path!");
			return false;
		}
		
		itemString = null;
		breader = null;
		try {
			breader = new BufferedReader(new InputStreamReader(
					new FileInputStream(dataFile)));
			while ((itemString = breader.readLine()) != null) {
				FingerPrint fingerPrint = new FingerPrint();
				String[] itemStrings = itemString.split("	");
				if(!(itemStrings[0].substring(0, 5).equals("Trace"))){
					if(Integer.parseInt(itemStrings[1]) != -1){
						fingerPrint.setItemrow(parseFingerPrint(itemStrings,2));
						fingerPrint.setGridid(Integer.parseInt(itemStrings[1]));
						fingerprintdao.insertFingerPrintTable(fingerPrint);
					}
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
		
		
		////////////////////////////////////////////fingerprint data ///////////////////////
		dataFile = new File(filepath_fingerprint_3);

		if (!dataFile.exists() || !dataFile.isFile()) {
			System.err.println("Invalid file path!");
			return false;
		}
		
		itemString = null;
		breader = null;
		try {
			breader = new BufferedReader(new InputStreamReader(
					new FileInputStream(dataFile)));
			while ((itemString = breader.readLine()) != null) {
				FingerPrint fingerPrint = new FingerPrint();
				String[] itemStrings = itemString.split("	");
				if(Integer.parseInt(itemStrings[0]) != -1){
					fingerPrint.setItemrow(parseFingerPrint(itemStrings,1));
					fingerPrint.setGridid(Integer.parseInt(itemStrings[0]));
					fingerprintdao.insertFingerPrintTable(fingerPrint);
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

	private static Double parseCoordiante(String s, int type) {
		// TODO Auto-generated method stub
		s = s.substring(1, s.length() - 1);
		String[] items = s.split(",");
		return Double.parseDouble(items[type]);
	}

}
