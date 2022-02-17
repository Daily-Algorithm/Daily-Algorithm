package Greedy;

import java.util.TreeMap;

/**
 * treeset 이용하면 중복값 처리를 못하기 때문에 treemap으로 변경
 */

public class PG구명보트 {
	public static int solution(int[] people, int limit) {
		TreeMap<Integer, Integer> treeMap = new TreeMap<>();

		for (int i = 0; i < people.length; i++) {
			if (treeMap.containsKey(people[i])) {
				treeMap.put(people[i], treeMap.get(people[i]) + 1);
			} else {
				treeMap.put(people[i], 1);
			}
		}

		int boat = 0;
		while (!treeMap.isEmpty()) {
			boat++;
			// max = 가장 큰 사람
			Integer max = treeMap.lastKey();
			if (treeMap.get(max) == 1) {
				treeMap.remove(max);
			} else {
				treeMap.put(max, treeMap.get(max) - 1);
			}
			// leftWeight = 남은 무게
			int leftWeight = limit - max;
			// 남은 무게가 treeSet의 최솟값보다 크면, 남은 무게보다 작은 수 중 가장 큰 값 구해서 제거
			if (!treeMap.isEmpty() && leftWeight >= treeMap.firstKey()) {
				Integer floor = treeMap.floorEntry(leftWeight).getKey();
				if (treeMap.get(floor) == 1) {
					treeMap.remove(floor);
				} else {
					treeMap.put(floor, treeMap.get(floor) - 1);
				}
			}
		}
		return boat;
	}

	public static void main(String[] args) {
		int[] people = {70, 50, 80, 50};
		int limit = 100;
		System.out.println(solution(people, limit));
	}
}
