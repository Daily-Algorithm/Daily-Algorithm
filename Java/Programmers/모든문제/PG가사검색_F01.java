package 모든문제;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 효율성 탈락, 리스트에 start, end 추가하지 말고 해보기
 */
public class PG가사검색_F01 {
	static HashMap<Integer, List<String>> map = new HashMap<>();
	static HashMap<Integer, List<String>> reverseMap = new HashMap<>();
	public static int[] solution(String[] words, String[] queries) {
		for (String word : words) {
			int length = word.length();
			String reverseWord = new StringBuffer(word).reverse().toString();
			List<String> strs;
			List<String> reverseStrs;
			if (map.containsKey(length)) {
				strs = map.get(length);
				strs.add(word);

				reverseStrs = reverseMap.get(length);
				reverseStrs.add(reverseWord);
			} else {
				strs = new ArrayList<>();
				strs.add(word);
				map.put(length, strs);

				reverseStrs = new ArrayList<>();
				reverseStrs.add(reverseWord);
				reverseMap.put(length, reverseStrs);
			}
		}

		int[] answer = new int[queries.length];
		int i = 0;
		for (String query : queries) {
			int length = query.length();
			if (map.containsKey(length)) {
				if (prefix(query)) {
					// 접두사
					List<String> reverseStrs = reverseMap.get(length);
					String reverseRTA = new StringBuffer(replaceToA(query)).reverse().toString();
					String reverseRTZ = new StringBuffer(replaceToZ(query)).reverse().toString();
					answer[i] = countWords(reverseStrs, reverseRTA, reverseRTZ);
					i++;
				} else {
					// 접미사
					List<String> strings = map.get(length);
					answer[i] = countWords(strings, replaceToA(query), replaceToZ(query));
					i++;
				}
			} else {
				answer[i] = 0;
				i++;
			}
		}

		return answer;
	}

	public static boolean prefix(String query) {
		return query.indexOf("?") == 0;
	}
	public static String replaceToA(String query) {
		return query.replaceAll("\\?", "a");
	}

	public static String replaceToZ(String query) {
		return query.replaceAll("\\?", "z");
	}

	public static int countWords(List<String> strings, String start, String end) {
		List<String> copy = new ArrayList<>(strings);
		copy.add(start);
		copy.add(end);
		Collections.sort(copy);
		return BSRight(copy, end) - BSLeft(copy, start) - 1;
	}

	static int BSLeft(List<String> strings, String target) {
		int start = 0;
		int end = strings.size() - 1;
		int mid;
		while (start <= end) {
			mid = (start + end) / 2;
			if (strings.get(mid).compareTo(target) < 0) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		return start;
	}

	static int BSRight(List<String> strings, String target) {
		int start = 0;
		int end = strings.size() - 1;
		int mid;
		while (start <= end) {
			mid = (start + end) / 2;
			if (strings.get(mid).compareTo(target) <= 0) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		return end;
	}

	public static void main(String[] args) {
		String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
		String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
		System.out.println(solution(words, queries));
	}
}
