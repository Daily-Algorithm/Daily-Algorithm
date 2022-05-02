package 모든문제;

import java.util.HashMap;
import java.util.Map;

public class PG81301_숫자문자열과영단어 {
	static Map<String, Integer> map = new HashMap<>();
	public static int solution(String s) {
		makeMap();
		return solveString(s);
	}

	private static int solveString(String s) {
		String[] split = s.split("");
		StringBuilder collect = new StringBuilder();
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < split.length; i++) {
			collect.append(split[i]);
			if (map.containsKey(collect.toString())) {
				answer.append(map.get(collect.toString()));
				collect = new StringBuilder();
			}
		}
		return Integer.parseInt(answer.toString());
	}

	private static void makeMap() {
		for (int i = 0; i <= 9; i++) {
			map.put(String.valueOf(i), i);
		}
		map.put("zero", 0);
		map.put("one", 1);
		map.put("two", 2);
		map.put("three", 3);
		map.put("four", 4);
		map.put("five", 5);
		map.put("six", 6);
		map.put("seven", 7);
		map.put("eight", 8);
		map.put("nine", 9);
	}

	public static void main(String[] args) {
		System.out.println(solution("one4seveneight"));
	}
}
