package cn.edu.zju.dao;

import java.util.Date;
import java.awt.print.Printable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import sun.security.krb5.KdcComm;

import cn.edu.zju.bean.FingerPrint;
import cn.edu.zju.bean.Grid;

public class FingerPrintDAO {

	private String sql;
	private DAO dbconnect;

	private FingerPrint fingerprint;
	private List<FingerPrint> allfingerprints;

	public int initFingerPrintTable() {
		this.dbconnect = new DAO();
		int flag = 0;

		this.sql = "DROP TABLE IF EXISTS fingerprint;";
		this.dbconnect.executeUpdate(sql);
		System.out.println(this.sql);

		this.sql = "CREATE TABLE `wlf`.`fingerprint` ( "
				+ "`recordid`  int NOT NULL AUTO_INCREMENT ,"
				+ "`itemrow`  text NOT NULL ," + "`gridid`  int NOT NULL ,"
				+ "`deviceid`  text NULL ," + "`datetime`  datetime NOT NULL ,"
				+ "PRIMARY KEY (`recordid`)"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=gbk;";

		this.dbconnect.executeUpdate(sql);
		System.out.println(this.sql);

		return flag;
	}

	public int insertFingerPrintTable(String itemrow, int gridid,
			String deviceid) {
		Date date = new Date();
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(date);// 将时间格式转换成符合
		String createdate = nowTime;
		this.dbconnect = new DAO();

		this.sql = "insert into fingerprint"
				+ "(itemrow,gridid,deviceid,datetime) " + "values ('" + itemrow
				+ "','" + gridid + "','" + deviceid + "','" + createdate
				+ "');";
		System.out.println(this.sql);
		int flag = this.dbconnect.executeUpdate(sql);
		this.dbconnect.closeConn();
		return flag;
	}

	public int insertFingerPrintTable(FingerPrint fingerPrint) {
		// TODO Auto-generated method stub
		Date date = new Date();
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(date);// 将时间格式转换成符合
		String createdate = nowTime;
		this.dbconnect = new DAO();

		this.sql = "insert into fingerprint"
				+ "(itemrow,gridid,deviceid,datetime) " + "values ('"
				+ fingerPrint.getItemrow() + "','" + fingerPrint.getGridid()
				+ "','" + fingerPrint.getDeviceid() + "','" + createdate
				+ "');";
		System.out.println(this.sql);
		int flag = this.dbconnect.executeUpdate(sql);
		this.dbconnect.closeConn();
		return flag;
	}

	public List<FingerPrint> getAllFingerPrint() throws Exception {
		this.dbconnect = new DAO();
		this.sql = "select * from fingerprint";

		this.allfingerprints = new ArrayList<FingerPrint>();

		System.out.println(this.sql);
		dbconnect.executeQuery(sql);

		try {
			while (dbconnect.next()) {
				this.fingerprint = new FingerPrint();

				this.fingerprint.setRecordid(this.dbconnect.getInt("recordid"));
				this.fingerprint
						.setItemrow(this.dbconnect.getString("itemrow"));
				this.fingerprint.setGridid(this.dbconnect.getInt("gridid"));
				this.fingerprint.setDeviceid(this.dbconnect
						.getString("deviceid"));
				this.fingerprint.setDatetime(this.dbconnect
						.getString("datetime"));

				this.allfingerprints.add(this.fingerprint);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.dbconnect.closeMysqlDao();
		return this.allfingerprints;
	}
	
	public FingerPrint getFingerPrintByRecordid(int recordid) throws Exception {
		
		this.dbconnect = new DAO();
		this.sql = "select * from fingerprint where recordid = '" + recordid + "';";

		System.out.println(this.sql);
		dbconnect.executeQuery(sql);

		try {
			while (dbconnect.next()) {
				this.fingerprint = new FingerPrint();

				this.fingerprint.setRecordid(this.dbconnect.getInt("recordid"));
				this.fingerprint
						.setItemrow(this.dbconnect.getString("itemrow"));
				this.fingerprint.setGridid(this.dbconnect.getInt("gridid"));
				this.fingerprint.setDeviceid(this.dbconnect
						.getString("deviceid"));
				this.fingerprint.setDatetime(this.dbconnect
						.getString("datetime"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.dbconnect.closeMysqlDao();
		return this.fingerprint;
	}

	public List<List<FingerPrint>> getFingerPrintRandomlyByGridid(int groupid, int num)
			throws Exception {

		List<FingerPrint> randomFingerPrints = new ArrayList<FingerPrint>();
		List<FingerPrint> nonrandomFingerPrints = new ArrayList<FingerPrint>();
		List<List<FingerPrint>> resultFingerPrints = new ArrayList<List<FingerPrint>>();

		this.dbconnect = new DAO();
		this.sql = "select * from fingerprint where gridid = '" + groupid
				+ "';";

		this.allfingerprints = new ArrayList<FingerPrint>();

		System.out.println(this.sql);
		dbconnect.executeQuery(sql);

		try {
			while (dbconnect.next()) {
				this.fingerprint = new FingerPrint();

				this.fingerprint.setRecordid(this.dbconnect.getInt("recordid"));
				this.fingerprint
						.setItemrow(this.dbconnect.getString("itemrow"));
				this.fingerprint.setGridid(this.dbconnect.getInt("gridid"));
				this.fingerprint.setDeviceid(this.dbconnect
						.getString("deviceid"));
				this.fingerprint.setDatetime(this.dbconnect
						.getString("datetime"));

				this.allfingerprints.add(this.fingerprint);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.dbconnect.closeMysqlDao();

		int maxsize = allfingerprints.size();
		Random random = new Random();

		int count = 0;
		int intRd = 0;
		int flag = 0;
		int[] intRet = new int[num];
		while (count < num) {
			intRd = Math.abs(random.nextInt()) % maxsize;
			for (int i = 0; i < count; i++) {
				if (intRet[i] == intRd) {
					flag = 1;
					break;
				} else {
					flag = 0;
				}
			}
			if (flag == 0) {
				intRet[count] = intRd;
				randomFingerPrints.add(allfingerprints.get(intRd));
				count++;
			}
		}
		// System.out.println("len" + intRet.length);
		//for (int item : intRet) {
			//randomFingerPrints.add(allfingerprints.get(item));
		//}
		int m_flag = 0;
		for(int k = 0; k < maxsize; k++){
			for(int i: intRet){
				if(k==i){
					m_flag = 1;
					break;
				}else {
					m_flag = 0;
				}
			}
			if(m_flag == 0){
				nonrandomFingerPrints.add(allfingerprints.get(k));
			}
		}
		
		resultFingerPrints.add(randomFingerPrints);
		resultFingerPrints.add(nonrandomFingerPrints);

		return resultFingerPrints;
	}
	

	public List<List<FingerPrint>> getAllFingerPrintRandomly(int s_num, int num)
			throws Exception {
		
		List<FingerPrint> totalNonRandomFingerPrint = new ArrayList<FingerPrint>();
		List<FingerPrint> totalRandomFingerPrint = new ArrayList<FingerPrint>();
		List<List<FingerPrint>> totalresultFingerPrints = new ArrayList<List<FingerPrint>>();
		
		for (int k = 1; k <= s_num; k++) {
			List<List<FingerPrint>> result = getFingerPrintRandomlyByGridid(k, num);
			totalRandomFingerPrint.addAll(result.get(0));
			totalNonRandomFingerPrint.addAll(result.get(1));
		}
		System.out.println("totalRandomFingerPrint Size:" + totalRandomFingerPrint.size());
		System.out.println("totalNonRandomFingerPrint Size:" + totalNonRandomFingerPrint.size());
		
		totalresultFingerPrints.add(totalRandomFingerPrint);
		totalresultFingerPrints.add(totalNonRandomFingerPrint);
		
		return totalresultFingerPrints;
	}
	
	public List<List<FingerPrint>> getAllFingerPrintRandomly(int begin, int end, int num)
			throws Exception {
		
		List<FingerPrint> totalNonRandomFingerPrint = new ArrayList<FingerPrint>();
		List<FingerPrint> totalRandomFingerPrint = new ArrayList<FingerPrint>();
		List<List<FingerPrint>> totalresultFingerPrints = new ArrayList<List<FingerPrint>>();
		
		for (int k = begin; k <= end; k++) {
			List<List<FingerPrint>> result = getFingerPrintRandomlyByGridid(k, num);
			totalRandomFingerPrint.addAll(result.get(0));
			totalNonRandomFingerPrint.addAll(result.get(1));
		}
		System.out.println("totalRandomFingerPrint Size:" + totalRandomFingerPrint.size());
		System.out.println("totalNonRandomFingerPrint Size:" + totalNonRandomFingerPrint.size());
		
		totalresultFingerPrints.add(totalRandomFingerPrint);
		totalresultFingerPrints.add(totalNonRandomFingerPrint);
		
		return totalresultFingerPrints;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FingerPrintDAO fingerPrintDAO = new FingerPrintDAO();
		List<List<FingerPrint>> result = fingerPrintDAO
				.getAllFingerPrintRandomly(5, 20);
		for(FingerPrint item: result.get(0)){
			System.out.println(item.getRecordid());
		}
		System.out.println("-----------------------");
		for(FingerPrint item: result.get(1)){
			System.out.println(item.getRecordid());
		}
	}

}
