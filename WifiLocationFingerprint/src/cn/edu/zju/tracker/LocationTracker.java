package cn.edu.zju.tracker;

import cn.edu.zju.bean.IndoorPoints;
import cn.edu.zju.bean.ItemRows;

public abstract class LocationTracker {
	
	public LocationTracker(){};
	public abstract IndoorPoints getLoctionIndex(ItemRows itemRows);
	public abstract int getLoctionGridID(ItemRows itemRows);
}
