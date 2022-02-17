package 모든문제;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO: 2022-02-15 다른 사람 풀이 보기 
public class PG튜플 {
	public static List<Integer> solution(String s) {
		List<Integer> answer = new ArrayList<>();

		List<List<String>> refined = refineString(s);
		answer.add(Integer.valueOf(refined.get(0).get(0)));
		for (int i = 1; i < refined.size(); i++) {
			List<String> before = refined.get(i - 1);
			List<String> current = new ArrayList<>(refined.get(i));
			for (int j = 0; j < before.size(); j++) {
				current.remove(before.get(j));
			}
			answer.add(Integer.valueOf(current.get(0)));
		}

		return answer;
	}

	static List<List<String>> refineString(String str) {
		str = str.substring(1, str.length() - 1).replaceAll("},", "}/");

		String[] split = str.split("/");
		List<List<String>> refined = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			String strOfList = split[i].replace("{", "").replace("}", "");
			List<String> wait = Arrays.asList(strOfList.split(","));
			refined.add(wait);
		}

		refined.sort((o1, o2) -> o1.size() >= o2.size() ? 1 : -1);
		return refined;
	}

	public static void main(String[] args) {
		System.out.println(solution("{{4,2,3},{3},{2,3,4,1},{2,3}}"));
	}
}
