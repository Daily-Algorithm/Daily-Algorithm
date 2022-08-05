package 구현;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PG92334_신고결과받기 {

	static Map<String, Set<String>> reported = new HashMap<>();
	static Map<String, Integer> getMail = new HashMap<>();
	static int[] answer;

	public static int[] solution(String[] id_list, String[] report, int k) {
		answer = new int[id_list.length];
		Arrays.stream(report).distinct().forEach(str -> process(str));
		for (String id : id_list) {
			if (reported.getOrDefault(id, new HashSet<>()).size() >= k) {
				reported.get(id)
						.forEach(reporter -> getMail.put(reporter,
														 getMail.getOrDefault(reporter, 0) + 1
						));
			}
		}
		for (int i = 0; i < id_list.length; i++) {
			answer[i] = getMail.getOrDefault(id_list[i], 0);
		}
		return answer;
	}

	private static void process(String str) {
		String[] split = str.split(" ");
		Set<String> set = reported.getOrDefault(split[1], new HashSet<>());
		set.add(split[0]);
		reported.put(split[1], set);
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(
			solution(
				new String[]{"muzi", "frodo", "apeach", "neo"},
				new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"},
				2)));
	}
}
