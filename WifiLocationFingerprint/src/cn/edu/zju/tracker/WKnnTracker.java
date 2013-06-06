package cn.edu.zju.tracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import cn.edu.zju.bean.GridMap;
import cn.edu.zju.bean.IndoorPoints;
import cn.edu.zju.bean.ItemRows;
import cn.edu.zju.bean.RadioMap;

public class WKnnTracker extends LocationTracker {

	private int Ktop = 0;

	private ArrayList<KnnItem> weights = null;
	private ArrayList<KnnItem> DeterminateSets = null;

	private class KnnItem implements Comparable<KnnItem> {
		private int index;
		private int weight;
		private int gridid;

		public KnnItem(int mIndex, int mWeight, int mgridid) {
			this.index = mIndex;
			this.weight = mWeight;
			this.gridid = mgridid;
		}

		@Override
		public int compareTo(KnnItem o) {
			return this.weight - o.weight;
		}

	}

	public WKnnTracker(int ktop) {
		super();
		if (ktop <= 0)
			ktop = 1;
		this.Ktop = ktop;
		weights = new ArrayList<>();
		DeterminateSets = new ArrayList<>();
	}

	@Override
	public IndoorPoints getLoctionIndex(ItemRows itemRows) {
		// TODO Auto-generated method stub
		calWeight(itemRows);
		Collections.sort(weights);
		printWeights();
		IndoorPoints resultIndoorPoints = calPosition();
		return resultIndoorPoints;
	}

	@Override
	public int getLoctionGridID(ItemRows itemRows) {
		// TODO Auto-generated method stub
		calWeight(itemRows);
		Collections.sort(weights);
		printWeights();
		int resultGridid = calGridID();
		return resultGridid;
	}

	private int calGridID() {
		// TODO Auto-generated method stub
		for (int i = 0; i < weights.size() && i < this.Ktop; i++) {
			if (weights.get(i).weight == 0) {
				DeterminateSets.add(weights.get(i));
			}
			// System.out.println(gridmap.m_allgrid.get(weights.get(i).gridid).getX_coordinate());
			// System.out.println(gridmap.m_allgrid.get(weights.get(i).gridid).getY_coordinate());
		}
		HashMap<Integer, Integer> groupWeightArrayList = new HashMap<Integer, Integer>();
		// if exists the true nearest neighbors
		if (DeterminateSets.size() != 0) {
			System.out.println("DeterminateSetSize:" + DeterminateSets.size());
			for (int k = 0; k < DeterminateSets.size(); k++) {
				int index = DeterminateSets.get(k).gridid;
				if (!groupWeightArrayList.containsKey(index)) {
					groupWeightArrayList.put(index, 1);
				} else {
					groupWeightArrayList.put(index,
							groupWeightArrayList.get(index) + 1);
				}
			}
			
			int max = 0;
			int maxcursorIndex = 0;
			Iterator<Integer> iterator = groupWeightArrayList.keySet()
					.iterator();
			while (iterator.hasNext()) {
				int cursorIndex = iterator.next();
				int cursor = groupWeightArrayList.get(cursorIndex);
				System.out.println("cursorIndex:" + cursorIndex + "; cursor:" + cursor);
				if (cursor > max) {
					max = cursor;
					maxcursorIndex = cursorIndex;
				}else if(cursor == max){
					maxcursorIndex = compareWithSumWeight(cursorIndex,maxcursorIndex);
					max = groupWeightArrayList.get(cursorIndex);
				}
				System.out.println("maxcursorIndex:" + maxcursorIndex + "; max:" + max);
				
			}
			return maxcursorIndex;
		} else {
			System.out.println("DeterminateSetSize:" + DeterminateSets.size());
			for (int i = 0; i < weights.size() && i < this.Ktop; i++) {
				int index = weights.get(i).gridid;
				if (!groupWeightArrayList.containsKey(index)) {
					groupWeightArrayList.put(index, 1);
				} else {
					groupWeightArrayList.put(index,
							groupWeightArrayList.get(index) + 1);
				}
			}
			
			int max = 0;
			int maxcursorIndex = 0;
			Iterator<Integer> iterator = groupWeightArrayList.keySet()
					.iterator();
			while (iterator.hasNext()) {
				
				int cursorIndex = iterator.next();
				int cursor = groupWeightArrayList.get(cursorIndex);
				System.out.println("cursorIndex:" + cursorIndex + "; cursor:" + cursor);
				if (cursor > max) {
					max = cursor;
					maxcursorIndex = cursorIndex;
				}else if(cursor == max){
					maxcursorIndex = compareWithSumWeight(cursorIndex,maxcursorIndex);
					max = groupWeightArrayList.get(cursorIndex);
				}
				System.out.println("maxcursorIndex:" + maxcursorIndex + "; max:" + max);
			}
			return maxcursorIndex;

		}
		
	}

	private int compareWithSumWeight(int cursorIndex, int maxcursorIndex) {
		// TODO Auto-generated method stub
		int sumOfcursor = 0;
		int sumOfmax = 0;
		for (int i = 0; i < this.Ktop; i++) {
			if (weights.get(i).gridid == cursorIndex) {
				sumOfcursor += weights.get(i).weight;
			}else if(weights.get(i).gridid == maxcursorIndex){
				sumOfmax += weights.get(i).weight;
			}
		}
		if(sumOfcursor > sumOfmax){
			return maxcursorIndex;
		}else if(sumOfcursor < sumOfmax){
			return cursorIndex;
		}else{
			return weights.get(0).gridid;
		}
	}

	private IndoorPoints calPosition() {
		// TODO Auto-generated method stub
		GridMap gridmap = GridMap.getInstance();

		double sum_weight = 0;
		for (int i = 0; i < weights.size() && i < this.Ktop; i++) {
			if (weights.get(i).weight == 0) {
				DeterminateSets.add(weights.get(i));
			}
			// System.out.println(gridmap.m_allgrid.get(weights.get(i).gridid).getX_coordinate());
			// System.out.println(gridmap.m_allgrid.get(weights.get(i).gridid).getY_coordinate());
			sum_weight += ((double) 1) / (weights.get(i).weight);
		}

		// if exists the true nearest neighbors
		if (DeterminateSets.size() != 0) {
			System.out.println("DeterminateSetSize:" + DeterminateSets.size());
			double x = 0;
			double y = 0;
			for (int k = 0; k < DeterminateSets.size(); k++) {
				System.out.println("Gridid:" + DeterminateSets.get(k).gridid);
				x += ((double) 1 / DeterminateSets.size())
						* gridmap.m_allgridmap.get(
								DeterminateSets.get(k).gridid)
								.getX_coordinate();
				y += ((double) 1 / DeterminateSets.size())
						* gridmap.m_allgridmap.get(
								DeterminateSets.get(k).gridid)
								.getY_coordinate();
			}
			System.out.println("X_coordinate: " + x);
			System.out.println("Y_coordinate: " + y);
			return new IndoorPoints(x, y);
		} else {
			double x = 0;
			double y = 0;
			for (int i = 0; i < weights.size() && i < this.Ktop; i++) {
				double t_weight = ((double) 1) / (weights.get(i).weight)
						/ ((double) sum_weight);
				x += t_weight
						* (gridmap.m_allgridmap.get(weights.get(i).gridid)
								.getX_coordinate());
				y += t_weight
						* (gridmap.m_allgridmap.get(weights.get(i).gridid)
								.getY_coordinate());
			}
			System.out.println("X_coordinate: " + x);
			System.out.println("Y_coordinate: " + y);
			return new IndoorPoints(x, y);
		}
	}

	private void calWeight(ItemRows uItemRows) {

		RadioMap radioMap = RadioMap.getInstance();

		if (radioMap.m_radioMap == null) {
			System.err.println("please load radio map first");
			return;
		}

		for (int i = 0; i < radioMap.m_radioMap.size(); i++) {
			int itemIndex = radioMap.m_radioMap.get(i).getRecordid();
			ItemRows itemRows = radioMap.m_radioMap.get(i).getItemrowObject();
			int gridid = radioMap.m_radioMap.get(i).getGridid();
			//int weight = uItemRows.calManhattonDistance(itemRows);
			int weight = uItemRows.calManhattonDistanceWithFiltering(itemRows);
			weights.add(new KnnItem(itemIndex, weight, gridid));
		}
	}

	public void printWeights() {
		// System.out.println(weights.size());
		for (int i = 0; i < weights.size() && i < this.Ktop; i++) {
			System.out.println("index:" + weights.get(i).gridid + "; weight:"
					+ weights.get(i).weight);
		}
	}
}