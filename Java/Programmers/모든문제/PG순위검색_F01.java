package 모든문제;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 시간초과, 주어진 info로 가능한 query를 map으로 만들어야겠음
 */
public class PG순위검색_F01 {
	static Map<String, List<Integer>> map = new HashMap<>();
	static String[][] keymakers;
	static List<String> keys;
	static String[] langArr = {"cpp", "java", "python"};
	static String[] typeArr = {"backend", "frontend"};
	static String[] careerArr = {"junior", "senior"};
	static String[] foodArr = {"chicken", "pizza"};

	public static int[] solution(String[] info, String[] query) {
		for (String personInfo : info) {
			trimAndPutIntoMap(personInfo);
			// 지원자 정보를 key로, key를 정보로 가지는 지원자들의 점수 list를 value로
		}

		int[] answer = new int[query.length];
		for (int i = 0; i < query.length; i++) {
			keymakers = new String[4][];
			keys = new ArrayList<>();
			// keymakers, keys 초기화
			answer[i] = findResult(query[i]); // query에 대한 모든 경우의 수에 대한 정답찾기
		}
		return answer;
	}

	private static int findResult(String query) {
		String[] conditions = query.split(" and ");

		String[] languages =
			conditions[0].equals("-") ? langArr : new String[]{conditions[0]};
		// language 조건이 "-"면 langArr을, 아니면 language 조건이 담긴 배열
		keymakers[0] = languages;

		String[] types =
			conditions[1].equals("-") ? typeArr : new String[]{conditions[1]};
		keymakers[1] = types;

		String[] careers =
			conditions[2].equals("-") ? careerArr : new String[]{conditions[2]};
		keymakers[2] = careers;

		String foodAndScore = conditions[3];
		StringTokenizer st = new StringTokenizer(foodAndScore, " ");
		String food = st.nextToken();
		String score = st.nextToken();
		String[] foods =
			food.equals("-") ? foodArr : new String[]{food};
		keymakers[3] = foods;

		Integer overScore = Integer.valueOf(score);
		permutation(0, "");	// query로 만들 수 있는 key들을 keys에 담은 후,
		int overCnt = 0;
		for (String key : keys) {
			if (map.containsKey(key)) {
				// key에 대한 값들 중, overScore보다 큰 값들의 개수 구하기
				int count = binarySearch(key, overScore);
				overCnt += count;
			}
		}
		return overCnt;
	}
	private static int binarySearch(String key, Integer overScore) {
		List<Integer> scores = map.get(key);
		Collections.sort(scores, Collections.reverseOrder());
		if (scores.get(0) >= overScore) {
			int leftIdx = 0;
			int rightIdx = scores.size();
			int mid;
			while (leftIdx < rightIdx) {
				mid = (leftIdx + rightIdx) / 2;
				if (overScore <= scores.get(mid)) {
					leftIdx = mid + 1;
				} else {
					rightIdx = mid;
				}
			}
			return leftIdx == 0 ? 1 : leftIdx;
		} else {
			return 0;
		}
	}

	private static void permutation(int keymakerIdx, String keyStr) {
		// 모든 조건에 대해 나올 수 있는 key들을 keys에 담는다
		if (keymakerIdx == 4) {
			keys.add(keyStr);
			return;
		}
		String[] curArr = keymakers[keymakerIdx];
		for (int i = 0; i < curArr.length; i++) {
			if (keymakerIdx == 0) {
				permutation(keymakerIdx + 1, curArr[i]);
			} else {
				permutation(keymakerIdx + 1, keyStr + " " + curArr[i]);
			}
		}
	}

	private static void trimAndPutIntoMap(String personInfo) {
		int standard = personInfo.lastIndexOf(" ");
		Integer score = Integer.valueOf(personInfo.substring(standard + 1));
		String key = personInfo.substring(0, standard);
		putIntoMap(key, score);
	}

	private static void putIntoMap(String A, Integer score) {
		if (map.containsKey(A)) {
			List<Integer> list = map.get(A);
			list.add(score);
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(score);
			map.put(A, list);
		}
	}

	public static void main(String[] args) {
		String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210",
			"python frontend senior chicken 150", "cpp backend senior pizza 260",
			"java backend junior chicken 80", "python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100",
			"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
			"- and backend and senior and - 150", "- and - and - and chicken 100",
			"- and - and - and - 150"};
		System.out.println(Arrays.toString(solution(info, query)));

	}
}
