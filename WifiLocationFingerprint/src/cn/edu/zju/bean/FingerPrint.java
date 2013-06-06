package cn.edu.zju.bean;



public class FingerPrint {
	
	private int recordid;
	private String itemrow;
	private int gridid;
	private String deviceid;
	private String datetime;

	public FingerPrint(){
		
	}
	
	public FingerPrint(String itemrow, int gridid, String deviceid) {
		// TODO Auto-generated constructor stub
		this.itemrow = itemrow;
		this.gridid = gridid;
		this.deviceid = deviceid;
	}


	public int getRecordid() {
		return recordid;
	}


	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}


	public String getItemrow() {
		return itemrow;
	}
	
	public ItemRows getItemrowObject(){
		return new ItemRows(itemrow);
	}


	public void setItemrow(String itemrow) {
		this.itemrow = itemrow;
	}


	public int getGridid() {
		return gridid;
	}


	public void setGridid(int gridid) {
		this.gridid = gridid;
	}


	public String getDeviceid() {
		return deviceid;
	}


	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}


	public String getDatetime() {
		return datetime;
	}


	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
