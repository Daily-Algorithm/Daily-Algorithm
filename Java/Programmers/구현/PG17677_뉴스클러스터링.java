package 구현;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class PG17677_뉴스클러스터링 {

	static Map<String, Integer> str1M = new HashMap<>();
	static Map<String, Integer> str2M = new HashMap<>();
	static int total = 0;
	static int common = 0;
	static float J;

	public static int solution(String str1, String str2) {
		str1M = makeMap(str1);
		str2M = makeMap(str2);

		if (str1M.isEmpty() && str2M.isEmpty()) {
			J = 1;
		} else {
			calCommon();
			System.out.println("교집합 : " + common);
			calTotal();
			System.out.println("합집합 : " + total);
			J = (common / (float) total);
		}

		return (int) (J * 65536);
	}

	private static void calTotal() {
		str1M.values()
			 .stream()
			 .forEach(value -> total += value);
		str2M.values()
			 .stream()
			 .forEach(value -> total += value);

		for (String str : str1M.keySet()) {
			if (str2M.containsKey(str)) {
				total -= Math.min(str1M.get(str), str2M.get(str));
			}
		}
	}

	private static void calCommon() {
		for (String str : str1M.keySet()) {
			if (str2M.containsKey(str)) {
				common += Math.min(str1M.get(str), str2M.get(str));
			}
		}
	}

	private static Map<String, Integer> makeMap(String str) {
		Map<String, Integer> map = new HashMap<>();
		String[] split = str.toUpperCase().split("");
		for (int idx = 0; idx < split.length - 1; idx++) {
			String cut = split[idx] + split[idx + 1];
			if (Pattern.matches("^[A-Z]*$", cut)) {
				if (map.containsKey(cut)) {
					map.put(cut, map.get(cut) + 1);
				} else {
					map.put(cut, 1);
				}
			}
		}
		return map;
	}

	public static void main(String[] args) {
		String str1 = "FRANCE";
		String str2 = "french";
		System.out.println(solution(str1, str2));
	}
}
