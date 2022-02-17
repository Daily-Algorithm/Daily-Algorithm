package 모든문제;

import java.util.HashMap;

public class PG모음사전 {
	public static int solution(String word) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("A", 0); map.put("E", 1); map.put("I", 2); map.put("O", 3); map.put("U", 4);

		String[] split = word.split("");
		int[] multiples = {781, 156, 31, 6, 1};

		int answer = 0;
		for (int i = 0; i < split.length; i++) {
			answer += multiples[i] * map.get(split[i]) + 1;
		}
		return answer;
	}

	public static void main(String[] args) {
		System.out.println(solution("EIO"));
	}
}
