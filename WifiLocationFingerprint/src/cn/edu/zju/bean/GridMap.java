package cn.edu.zju.bean;

import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.zju.dao.GridDAO;

public class GridMap {
	
	private ArrayList<Grid> m_allgrid = null;
	
	public HashMap<Integer, Grid> m_allgridmap = null;

	private static GridMap gridmap = new GridMap();

	private GridMap(){
		
	}
	
	public synchronized boolean loadGridMap() throws Exception {
		if (this.m_allgridmap == null) {
			this.m_allgridmap = new HashMap<>();
		} else if (!this.m_allgridmap.isEmpty()) {
			this.m_allgridmap.clear();
		}
		
		GridDAO griddao = new GridDAO();
		m_allgrid = (ArrayList<Grid>) griddao.getGridByGroup();
		
		for(int i=0; i<m_allgrid.size(); i++)
		{
			m_allgridmap.put(m_allgrid.get(i).getGridid(), m_allgrid.get(i));
		}
		
		printGridMap();
	
		if(this.m_allgridmap != null)
		{
			return true;
		}
		
		return false;

	}

	public static GridMap getInstance() {
		return GridMap.gridmap;
	}
	
	public void printGridMap(){
		for(int i=0; i<m_allgrid.size(); i++){
			System.out.println(m_allgrid.get(i).getGridid());
			System.out.println(m_allgrid.get(i).getX_coordinate());
			System.out.println(m_allgrid.get(i).getY_coordinate());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
