package 순열조합;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PG메뉴리뉴얼 {

	static Map<Integer, Map<String, Integer>> map = new HashMap<>(); // <dishNum, <course, num>>
	public static List<String> solution(String[] orders, int[] course) {
		Arrays.stream(course).forEach(num -> map.put(num, new HashMap<>()));

		for (String order : orders) {
			String[] orderArr = order.split("");
			Arrays.sort(orderArr);
			boolean[] visited = new boolean[orderArr.length];
			Arrays.fill(visited, false);
			permutation(orderArr, visited, "", 0);
		}

		List<String> answers = new ArrayList<>();
		for (Integer dishNum : map.keySet()) {
			Map<String, Integer> courseAndNum = map.get(dishNum);
			int max = findMax(courseAndNum);
			if (max >= 2) {
				List<String> maxCourse = courseAndNum.keySet()
													 .stream()
													 .filter(str -> courseAndNum.get(str) == max)
													 .collect(Collectors.toList());
				answers.addAll(maxCourse);
			}
		}
		Collections.sort(answers);
		return answers;
	}

	private static int findMax(Map<String, Integer> courseAndNum) {
		int max = 0;
		for (String key : courseAndNum.keySet()) {
			max = Math.max(max, courseAndNum.get(key));
		}
		return max;
	}

	private static void permutation(String[] orderArr, boolean[] visited, String str, int dept) {
		if (dept == orderArr.length) {
			putIntoMap(str);
			return;
		}
		visited[dept] = false;
		permutation(orderArr, visited, str, dept + 1);
		visited[dept] = true;
		permutation(orderArr, visited, str + orderArr[dept], dept + 1);

	}

	private static void putIntoMap(String str) {
		// static Map<Integer, Map<String, Integer>> map = new HashMap<>(); // <dishNum, <course, num>>
		if (map.containsKey(str.length())) {
			Map<String, Integer> courseAndNum = map.get(str.length());
			if (courseAndNum.containsKey(str)) {
				courseAndNum.put(str, courseAndNum.get(str) + 1);
			} else {
				courseAndNum.put(str, 1);
			}
		}
	}

	public static void main(String[] args) {
		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int[] course = {2, 3, 4};
		System.out.println(solution(orders, course));
	}
}
