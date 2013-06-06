package cn.edu.zju.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zju.bean.Grid;

public class GridDAO {

	private String sql;
	private DAO dbconnect;

	private Grid grid;
	private List<Grid> allgrids;
	
	
	public int initGridTable() {
		this.dbconnect = new DAO();
		int flag = 0;

		this.sql = "DROP TABLE IF EXISTS grid;";
		this.dbconnect.executeUpdate(sql);
		System.out.println(this.sql);

		this.sql = "CREATE TABLE `wlf`.`grid` ( "
				+ "`id`  int NOT NULL AUTO_INCREMENT ,"
				+ "`gridid`  int NOT NULL ,"
				+ "`x_coordinate`  double NOT NULL ,"
				+ "`y_coordinate`  double NOT NULL ,"
				+ "`remarks`  text NULL ," + "PRIMARY KEY (`id`)"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=gbk;";

		this.dbconnect.executeUpdate(sql);
		System.out.println(this.sql);

		return flag;
	}

	public int insertGridTable(int gridid, Double x_coordinate,
			Double y_coordinate, String remarks) {

		this.dbconnect = new DAO();

		this.sql = "insert into grid"
				+ "(gridid,x_coordinate,y_coordinate,remarks) " + "values ('"
				+ gridid + "','" + x_coordinate + "','" + y_coordinate + "','"
				+ remarks + "');";
		System.out.println(this.sql);
		int flag = this.dbconnect.executeUpdate(sql);
		this.dbconnect.closeConn();
		return flag;
	}
	
	public int insertGridTable(Grid grid) {
		// TODO Auto-generated method stub
		this.dbconnect = new DAO();

		this.sql = "insert into grid"
				+ "(gridid,x_coordinate,y_coordinate,remarks) " + "values ('"
				+ grid.getGridid() + "','" + grid.getX_coordinate() + "','" + grid.getY_coordinate() + "','"
				+ grid.getRemarks() + "');";
		System.out.println(this.sql);
		int flag = this.dbconnect.executeUpdate(sql);
		this.dbconnect.closeConn();
		return flag;
	}

	public List<Grid> getAllGrid() throws Exception {
		this.dbconnect = new DAO();
		this.sql = "select * from grid";

		this.allgrids = new ArrayList<Grid>();

		System.out.println(this.sql);
		dbconnect.executeQuery(sql);

		try {
			while (dbconnect.next()) {
				this.grid = new Grid();

				this.grid.setId(this.dbconnect.getInt("id"));
				this.grid.setGridid(this.dbconnect.getInt("recordid"));
				this.grid.setX_coordinate(this.dbconnect
						.getDouble("x_coordinate"));
				this.grid.setY_coordinate(this.dbconnect
						.getDouble("y_coordinate"));
				this.grid.setRemarks(this.dbconnect.getString("remarks"));

				this.allgrids.add(this.grid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.dbconnect.closeMysqlDao();
		return this.allgrids;
	}
	
	public List<Grid> getGridByGroup() throws Exception{
		this.dbconnect = new DAO();
		this.sql = "SELECT grid.gridid, AVG(grid.x_coordinate), AVG(grid.y_coordinate) FROM `grid` GROUP BY grid.gridid;";
		
		this.allgrids = new ArrayList<Grid>();

		System.out.println(this.sql);
		dbconnect.executeQuery(sql);

		try {
			while (dbconnect.next()) {
				this.grid = new Grid();

				this.grid.setGridid(this.dbconnect.getInt(1));
				this.grid.setX_coordinate(this.dbconnect
						.getDouble(2));
				this.grid.setY_coordinate(this.dbconnect
						.getDouble(3));

				this.allgrids.add(this.grid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.dbconnect.closeMysqlDao();
		return this.allgrids;
	}
	
	public Grid getGridByGridid(int gridid) throws Exception{
		
		this.dbconnect = new DAO();
		this.sql = "SELECT * FROM `grid` where gridid = '" + gridid + "';";
		
		System.out.println(this.sql);
		dbconnect.executeQuery(sql);

		try {
			while (dbconnect.next()) {
				this.grid = new Grid();

				this.grid.setGridid(this.dbconnect.getInt("gridid"));
				this.grid.setX_coordinate(this.dbconnect
						.getDouble("x_coordinate"));
				this.grid.setY_coordinate(this.dbconnect
						.getDouble("y_coordinate"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.dbconnect.closeMysqlDao();
		return this.grid;
	}
	
	

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	}

	

}
