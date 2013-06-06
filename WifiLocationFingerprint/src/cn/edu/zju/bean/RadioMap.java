package cn.edu.zju.bean;

import java.util.ArrayList;
import java.util.List;

import cn.edu.zju.dao.FingerPrintDAO;

/**
 * singleton model.ie.we only need only radiomap
 * 
 * @author xpp
 * 
 */
public class RadioMap {
	// a radio map. first item: id. second item : Rssi vector
	public ArrayList<FingerPrint> m_radioMap = null;

	private static RadioMap radioMap = new RadioMap();
	// smaller than -90db, omitted!
	public static final int sigalThreshold = -100;

	private RadioMap(){
		
	}

	public synchronized boolean loadRadioMap() throws Exception {
		// step 1. make sure m_radioMap does exist and be empty;
		if (this.m_radioMap == null) {
			this.m_radioMap = new ArrayList<>();
		} else if (!this.m_radioMap.isEmpty()) {
			this.m_radioMap.clear();
		}
		
		FingerPrintDAO fingerprintdao = new FingerPrintDAO();
		m_radioMap = (ArrayList<FingerPrint>) fingerprintdao.getAllFingerPrint();
		
		if(this.m_radioMap != null)
		{
			return true;
		}
		
		return false;

	}
	
	public synchronized boolean loadRadioMapWithPara(List<FingerPrint> totalNonRandomFingerPrint){
		
		if (this.m_radioMap == null) {
			this.m_radioMap = new ArrayList<>();
		} else if (!this.m_radioMap.isEmpty()) {
			this.m_radioMap.clear();
		}
		
		m_radioMap = (ArrayList<FingerPrint>) totalNonRandomFingerPrint;
		
		if(this.m_radioMap != null)
		{
			return true;
		}
		
		return false;
	}

	public static RadioMap getInstance() {
		return RadioMap.radioMap;
	}
}
