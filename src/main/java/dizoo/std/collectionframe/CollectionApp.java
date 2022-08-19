package dizoo.std.collectionframe;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CollectionApp {
	public static void main(String[] args) {
		listMapTest();
	}

	private static void listMapTest() {
		int[] num = { 0, 200, 1, 0, 2, 3, 2, 4, 5, 5, 4, 2, 2, 3, 0, 100 };
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < num.length; i++) {
			list.add(num[i]);
		}
		List<TreeMap<Integer, ArrayList<Integer>>> listMap = new ArrayList<TreeMap<Integer, ArrayList<Integer>>>();
		for (int i = 0; i < list.size(); i++) {
			int number = list.get(i);
			boolean flag = false;

			for (int j = 0; j < listMap.size(); j++) {
				TreeMap<Integer, ArrayList<Integer>> temp = listMap.get(j);
				if (temp.containsKey(number)) {
					temp.get(number).add(i);
					flag = true;
					break;

				}
			}
			if (!flag) {
				TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
				ArrayList<Integer> l = new ArrayList<Integer>();
				l.add(i);
				map.put(number, l);
				listMap.add(map);
			}
		}
		System.out.println(listMap);
	}

}
