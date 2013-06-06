package cn.edu.zju.bean;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.edu.zju.util.Pair;

/**
 * inner class for a row.which is used to store a vector of rssi(train data);
 * 
 * @author ZJUDB
 * 
 */
public class ItemRows {
	// m_itemRows is an arraylist to store a vector of rssi at some grid!
	private ArrayList<Pair<String, Integer>> m_itemRows = null;
	private Pair<String, Integer> newpair = null;

	public ItemRows() {
		m_itemRows = new ArrayList<>();
	}

	public ItemRows(String jsonstring) {
		m_itemRows = new ArrayList<>();
		JSONArray jarray = JSONArray.fromObject(jsonstring);
		for (int i = 0; i < jarray.size(); i++) {
			JSONObject jo = JSONObject.fromObject(jarray.get(i));
			this.add((String) jo.get("BSSID"), (int) jo.get("RSSI"));
		}
	}

	public void add(String bssid, int rssi) {
		newpair = new Pair<String, Integer>(bssid, rssi);
		m_itemRows.add(newpair);
	}

	public void printItemRows() {
		System.out.println("size:" + m_itemRows.size());
		while (m_itemRows.iterator().hasNext()) {
			System.out.println(m_itemRows.iterator().next().first
					+ m_itemRows.iterator().next().second);
		}
	}

	public String toJSONString() {
		// System.out.println(m_itemRows.size());
		String JSONString = "[";
		for (int i = 0; i < m_itemRows.size() - 1; i++) {
			JSONString += "{" + "\"BSSID\":\"" + m_itemRows.get(i).first
					+ "\"," + "\"RSSI\":" + m_itemRows.get(i).second + "},";
		}
		JSONString += "{" + "\"BSSID\":\""
				+ m_itemRows.get(m_itemRows.size() - 1).first + "\","
				+ "\"RSSI\":" + m_itemRows.get(m_itemRows.size() - 1).second
				+ "}]";

		return JSONString;
	}

	public int calManhattonDistance(ItemRows rItemRows) {

		int ret = 0;

		for (int i = 0; i < m_itemRows.size(); i++) {
			String fmacString = m_itemRows.get(i).first;
			int fRssi = m_itemRows.get(i).second;
			boolean bfind = false;
			for (int j = 0; j < rItemRows.m_itemRows.size() && !bfind; j++) {
				String smacString = rItemRows.m_itemRows.get(j).first;
				int sRssi = rItemRows.m_itemRows.get(j).second;
				if (fmacString.equals(smacString)) {
					ret += Math.abs(sRssi - fRssi);
					bfind = true;
				}
			}
			if (!bfind) {
				ret += Math.abs(fRssi - RadioMap.sigalThreshold);
			}
		}

		for (int i = 0; i < rItemRows.m_itemRows.size(); i++) {
			String fmacString = rItemRows.m_itemRows.get(i).first;
			int fRssi = rItemRows.m_itemRows.get(i).second;
			boolean bfind = false;
			for (int j = 0; j < m_itemRows.size() && !bfind; j++) {
				String smacString = m_itemRows.get(j).first;
				// int sRssi = m_itemRows.get(j).second;
				if (fmacString.equals(smacString)) {
					bfind = true;
				}
			}
			if (!bfind) {
				ret += Math.abs(fRssi - RadioMap.sigalThreshold);
			}
		}

		return ret;
	}

	public int calManhattonDistanceWithFiltering(ItemRows rItemRows) {

		int ret = 0;

		for (int i = 0; i < m_itemRows.size(); i++) {
			String fmacString = m_itemRows.get(i).first;
			int fRssi = m_itemRows.get(i).second;
			boolean bfind = false;
			boolean needtobefiltered = false;
			if (fmacString.equals("14:CF:92:92:A0:1E")
					|| fmacString.equals("14:CF:92:92:A0:74")) {
				needtobefiltered = true;
			}
			if (!needtobefiltered) {
				for (int j = 0; j < rItemRows.m_itemRows.size() && !bfind; j++) {
					String smacString = rItemRows.m_itemRows.get(j).first;
					int sRssi = rItemRows.m_itemRows.get(j).second;
					if (fmacString.equals(smacString)) {
						ret += Math.abs(sRssi - fRssi);
						bfind = true;
					}
				}
				if (!bfind) {
					ret += Math.abs(fRssi - RadioMap.sigalThreshold);
				}
			}

		}

		for (int i = 0; i < rItemRows.m_itemRows.size(); i++) {
			String fmacString = rItemRows.m_itemRows.get(i).first;
			int fRssi = rItemRows.m_itemRows.get(i).second;
			boolean bfind = false;
			boolean needtobefiltered = false;
			if (fmacString.equals("14:CF:92:92:A0:1E")
					|| fmacString.equals("14:CF:92:92:A0:74")) {
				needtobefiltered = true;
			}
			if (!needtobefiltered) {
				for (int j = 0; j < m_itemRows.size() && !bfind; j++) {
					String smacString = m_itemRows.get(j).first;
					// int sRssi = m_itemRows.get(j).second;
					if (fmacString.equals(smacString)) {
						bfind = true;
					}
				}
				if (!bfind) {
					ret += Math.abs(fRssi - RadioMap.sigalThreshold);
				}
			}
		}

		return ret;
	}

	public double calEuclideanDistance(ItemRows rItemRows) {

		int ret = 0;

		for (int i = 0; i < m_itemRows.size(); i++) {
			String fmacString = m_itemRows.get(i).first;
			int fRssi = m_itemRows.get(i).second;
			boolean bfind = false;
			for (int j = 0; j < rItemRows.m_itemRows.size() && !bfind; j++) {
				String smacString = rItemRows.m_itemRows.get(j).first;
				int sRssi = rItemRows.m_itemRows.get(j).second;
				if (fmacString.equals(smacString)) {
					ret += (sRssi - fRssi) * (sRssi - fRssi);
					bfind = true;
				}
			}
			if (!bfind) {
				ret += (fRssi - RadioMap.sigalThreshold)
						* (fRssi - RadioMap.sigalThreshold);
			}
		}

		for (int i = 0; i < rItemRows.m_itemRows.size(); i++) {
			String fmacString = rItemRows.m_itemRows.get(i).first;
			int fRssi = rItemRows.m_itemRows.get(i).second;
			boolean bfind = false;
			for (int j = 0; j < m_itemRows.size() && !bfind; j++) {
				String smacString = m_itemRows.get(j).first;
				// int sRssi = m_itemRows.get(j).second;
				if (fmacString.equals(smacString)) {
					bfind = true;
				}
			}
			if (!bfind) {
				ret += (fRssi - RadioMap.sigalThreshold)
						* (fRssi - RadioMap.sigalThreshold);
			}
		}

		return Math.sqrt(ret);
	}

	public int calManhattonDistance_old(ItemRows rItemRows) {
		int ret = 0;
		/**
		 * left Item -> right item;
		 */
		for (int i = 0; i < m_itemRows.size(); i++) {
			String fmacString = m_itemRows.get(i).first;
			int fRssi = m_itemRows.get(i).second;
			boolean bfind = false; // indicate whether we find the same ap;
			for (int j = 0; j < rItemRows.m_itemRows.size() && !bfind; j++) {
				String smacString = rItemRows.m_itemRows.get(j).first;
				int sRssi = rItemRows.m_itemRows.get(j).second;
				// same ap!
				if (fmacString.equals(smacString)) {
					ret += Math.abs(sRssi - fRssi);
					bfind = true;
				}
			}

			// if we can not find the recevied ap from train item.
			// we suppose there existes this ap(< sigalThreshold) which
			// cause we omit it
			// if(!bfind){
			// ret+=Math.abs(fRssi - RadioMap.sigalThreshold);
			// }
		}

		/**
		 * find the right item does not exist in left item
		 */
		for (int i = 0; i < rItemRows.m_itemRows.size(); i++) {
			String fmacString = rItemRows.m_itemRows.get(i).first;
			int fRssi = rItemRows.m_itemRows.get(i).second;
			boolean bfind = false; // indicate whether we find the same ap;
			for (int j = 0; j < m_itemRows.size() && !bfind; j++) {
				String smacString = rItemRows.m_itemRows.get(j).first;
				// int sRssi = rItemRows.m_itemRows.get(j).second;
				// same ap!
				if (fmacString.equals(smacString)) {
					bfind = true;
				}
			}
			if (!bfind) {
				ret += Math.abs(fRssi - RadioMap.sigalThreshold);
			}
		}
		return ret;
	}
}