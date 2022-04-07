package 모든문제;

import java.util.HashMap;
import java.util.Map;

public class PG124나라의숫자 {

	static Map<String, String> convert = new HashMap<>();
	static String answer = "";
	public static String solution(int n) {
		convert.put("0", "4");
		convert.put("1", "1");
		convert.put("2", "2");

		while (n > 0) {
			int mok = n / 3;
			int nameoji = n % 3;
			answer = convert.get(String.valueOf(nameoji)) + answer;
			if (nameoji == 0) {
				n = mok - 1;
			} else {
				n = mok;
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		System.out.println(solution(12));
	}
}
