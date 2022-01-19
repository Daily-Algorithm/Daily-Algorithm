package Hash;

import java.util.*;

public class PG완주하지못한선수 {
	public static String solution(String[] participant, String[] completion) {
		StringBuilder answer = new StringBuilder();
		Map<String, Integer> notFinished = new HashMap();

		for (String person:participant) {
			if (notFinished.containsKey(person)) {
				notFinished.put(person, notFinished.get(person)+1);
			} else {
				notFinished.put(person, 1);
			}
		}

		for (String complete:completion) {
			if (notFinished.get(complete) == 1) {
				notFinished.remove(complete);
			} else {
				notFinished.put(complete, notFinished.get(complete)-1);
			}
		}

		for (String s : notFinished.keySet()) {
			answer.append(s);
		}

		return answer.toString();
	}

	public static void main(String[] args) {
		String[] participant = {"leo", "kiki", "eden"};
		String[] completion = {"eden", "kiki"};
		System.out.println(solution(participant, completion));
	}
}
