package cn.edu.zju.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

public class DAO {
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private int num = 0;
	public static String userName;
	public static String password;
	public static String dbName;
	public static String url;
	
	public DAO()
	{
		try
		{
			init();
			openConn();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// open a connection
	public void openConn() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.conn = DriverManager.getConnection(url);
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	// initialize the URL of connection
	private void init() {
		// TODO Auto-generated method stub
		url = "jdbc:mysql://localhost:3306/wlf?user=root&password=123456&useUnicode=true&characterEncoding=GBK";		
	}
	
	// return a connection
	public Connection getConn()
	{
		return this.conn;
	}
	
	// execute the query
	public ResultSet executeQuery(String sql)
	{
		try
		{
			this.stmt = this.conn.createStatement(1004, 
					1007);
			this.stmt.execute("set names GBK");
			this.rs = this.stmt.executeQuery(sql);
		}
		catch (SQLException e)
		{
			System.err.println("Data.executeQuery: " + e.getMessage());
		}
		return this.rs;
	}
	
	// execute the update
	public int executeUpdate(String sql)
	{
		try
		{
			this.stmt = this.conn.createStatement(1004, 
					1007);
			this.num = this.stmt.executeUpdate(sql);
		}
		catch (SQLException e)
		{
			System.err.println("Data.executeQuery: " + e.getMessage());
		}
		return this.num;
	}
	
	// close the result set
	public void closeRs()
	{
		try
		{
			this.rs.close();
		}
		catch (SQLException e)
		{
			System.err.println("Date.executeQuery: " + e.getMessage());
		}
	}
	
	// close the statement
	public void closeStmt()
	{
		try {
			this.stmt.close();
		}
		catch (SQLException e)
		{
			System.err.println("Date.executeQuery: " + e.getMessage());
		}
	}
	
	// close the connection
	public void closeConn()
	{
		try
		{
			this.conn.close();
		}
		catch (SQLException e)
		{
			System.err.println("Data.executeQuery: " + e.getMessage());
		}
	}
	
	// close all
	public void closeMysqlDao()
	{
		closeStmt();
		closeRs();
		closeConn();
	}
	
	
	public boolean getBoolean(int i) throws Exception {
		return rs.getBoolean(i);
	}

	public boolean getBoolean(String s) throws Exception {
		return rs.getBoolean(s);
	}

	public byte getByte(int i) throws Exception {
		return rs.getByte(i);
	}

	public byte getByte(String s) throws Exception {
		return rs.getByte(s);
	}

	public byte[] getBytes(int i) throws Exception {
		return rs.getBytes(i);
	}

	public byte[] getBytes(String s) throws Exception {
		return rs.getBytes(s);
	}

	public Date getDate(int i) throws Exception {
		return rs.getDate(i);
	}

	public Date getDate(String s) throws Exception {
		return rs.getDate(s);
	}

	public Time getTime(int i) throws Exception {
		return rs.getTime(i);
	}

	public Time getTime(String s) throws Exception {
		return rs.getTime(s);
	}

	public double getDouble(int i) throws Exception {
		return rs.getDouble(i);
	}

	public double getDouble(String s) throws Exception {
		return rs.getDouble(s);
	}

	public float getFloat(int i) throws Exception {
		return rs.getFloat(i);
	}

	public float getFloat(String s) throws Exception {
		return rs.getFloat(s);
	}

	public int getInt(int i) throws Exception {
		return rs.getInt(i);
	}

	public int getInt(String s) throws Exception {
		return rs.getInt(s);
	}

	public long getLong(int i) throws Exception {
		return rs.getLong(i);
	}

	public long getLong(String s) throws Exception {
		return rs.getLong(s);
	}

	public short getShort(int i) throws Exception {
		return rs.getShort(i);
	}

	public short getShort(String s) throws Exception {
		return rs.getShort(s);
	}

	public String getString(int i) throws Exception {
		return rs.getString(i);
	}

	public String getString(String s) throws Exception {
		return rs.getString(s);
	}
	
	public boolean next()
	{
		try
		{
			return rs.next();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
}
