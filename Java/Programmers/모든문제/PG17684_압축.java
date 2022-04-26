package 모든문제;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PG17684_압축 {
	static Map<String, Integer> strToIdx = new HashMap<>();
	static List<Integer> answer = new ArrayList<>();
	private static List<Integer> solution(String msg) {
		putAlphabet();

		String[] split = msg.split("");
		String before = "";
		for (int idx = 0; idx < split.length; idx++) {
			StringBuilder appended = new StringBuilder(before).append(split[idx]);
			if (!strToIdx.containsKey(appended.toString())) {
				// 현재 idx의 문자를 더한 문자가 사전에 없으면
				answer.add(strToIdx.get(before));
				strToIdx.put(appended.toString(), strToIdx.size() + 1);
				before = "";
				idx--;
			} else {
				// 현재 idx의 문자를 더한 문자가 사전에 있으면
				// 문자의 길이를 키운다.
				before = appended.toString();
			}
		}
		if (!before.equals("")) {
			answer.add(strToIdx.get(before));
		}

		return answer;
	}

	private static void putAlphabet() {
		for (int i = 65; i <=90 ; i++) {
			strToIdx.put(String.valueOf((char) i), i - 64);
		}
	}

	public static void main(String[] args) {
		String msg = "KAKAO";
		System.out.println(solution(msg));
	}
}
