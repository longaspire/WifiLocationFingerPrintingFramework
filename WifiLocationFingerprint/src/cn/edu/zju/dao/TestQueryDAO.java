package cn.edu.zju.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zju.bean.FingerPrint;

public class TestQueryDAO {

	private String sql;
	private DAO dbconnect;

	private FingerPrint fingerprint;
	private List<FingerPrint> allfingerprints;

	public int initTestQueryTable() {
		this.dbconnect = new DAO();
		int flag = 0;

		this.sql = "DROP TABLE IF EXISTS testquery;";
		this.dbconnect.executeUpdate(sql);
		System.out.println(this.sql);

		this.sql = "CREATE TABLE `wlf`.`testquery` ( "
				+ "`recordid`  int NOT NULL AUTO_INCREMENT ,"
				+ "`itemrow`  text NOT NULL ," + "`gridid`  int NOT NULL ,"
				+ "`deviceid`  text NULL ," + "`datetime`  datetime NOT NULL ,"
				+ "PRIMARY KEY (`recordid`)"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=gbk;";

		this.dbconnect.executeUpdate(sql);
		System.out.println(this.sql);

		return flag;
	}

	public int insertTestQueryTable(String itemrow, int gridid) {
		Date date = new Date();
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(date);// 将时间格式转换成符合
		String createdate = nowTime;
		this.dbconnect = new DAO();

		this.sql = "insert into testquery" + "(itemrow,gridid,datetime) "
				+ "values ('" + itemrow + "','" + gridid + "','" + createdate
				+ "');";
		System.out.println(this.sql);
		int flag = this.dbconnect.executeUpdate(sql);
		this.dbconnect.closeConn();
		return flag;
	}

	public int updateTestQueryTableByGridid(int gridid, int count) {

		this.dbconnect = new DAO();

		this.sql = "update testquery set gridid = '" + gridid
				+ "' where recordid = '" + count + "';";
		System.out.println(this.sql);
		int flag = this.dbconnect.executeUpdate(sql);
		this.dbconnect.closeConn();
		return flag;
	}

	public FingerPrint getTestQueryTableByRecordid(int recordid) throws Exception {
		this.dbconnect = new DAO();

		this.sql = "select * from testquery where recordid = '" + recordid
				+ "';";
		System.out.println(this.sql);
		dbconnect.executeQuery(sql);

		try {
			while (dbconnect.next()) {
				this.fingerprint = new FingerPrint();

				this.fingerprint.setRecordid(this.dbconnect.getInt("recordid"));
				this.fingerprint
						.setItemrow(this.dbconnect.getString("itemrow"));
				this.fingerprint.setGridid(this.dbconnect.getInt("gridid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.dbconnect.closeMysqlDao();
		return this.fingerprint;
	}

	public List<FingerPrint> getAllFingerPrint() throws Exception {
		this.dbconnect = new DAO();
		this.sql = "select * from testquery";

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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
