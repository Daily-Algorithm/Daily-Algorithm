package 구현;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * 몫, 나머지 개념이 아닌 나누기 개념 = int A, B 중 하나를 double형으로 만들어 계산 = A/(double)B
 */
public class PG92341_주차요금 {

	static TreeMap<Integer, Integer> timeMap = new TreeMap<>();
	static Map<Integer, Integer> inMap = new HashMap<>();
	static int defaultTime, defaultFee, unitT, unitFee;
	static int lastT = intTime("23:59");

	public static List<Integer> solution(int[] fees, String[] records) {
		initial(fees);
		for (String record : records) {
			processRecord(record);
		}
		clearInMap();
		return timeMap.values()
					  .stream()
					  .map(time -> calculateT(time))
					  .collect(Collectors.toList());
	}

	private static void initial(int[] fees) {
		defaultTime = fees[0];
		defaultFee = fees[1];
		unitT = fees[2];
		unitFee = fees[3];
	}

	private static Integer calculateT(Integer time) {
		if (time <= defaultTime) {
			return defaultFee;
		} else {
			return defaultFee + (int) Math.ceil((time - defaultTime) / (double) unitT) * unitFee;
		}
	}

	private static void clearInMap() {
		if (!inMap.isEmpty()) {
			for (Integer car : inMap.keySet()) {
				timeMap.put(car, timeMap.get(car) + lastT - inMap.get(car));
			}
		}
	}

	private static void processRecord(String record) {
		String[] split = record.split(" ");
		String strT = split[0];
		Integer car = Integer.valueOf(split[1]);
		if (split[2].equals("IN")) {
			inMap.put(car, intTime(strT));
			if (!timeMap.containsKey(car)) timeMap.put(car, 0);
		} else {
			timeMap.put(car, timeMap.get(car) + intTime(strT) - inMap.get(car));
			inMap.remove(car);
		}
	}

	private static int intTime(String str) {
		String[] split = str.split(":");
		return 60 * Integer.parseInt(split[0]) + Integer.parseInt(split[1]);
	}

	public static void main(String[] args) {
		System.out.println(solution(
			new int[]{180, 5000, 10, 600},
			new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT",
				"07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN",
				"23:00 5961 OUT"}
		));
	}
}
