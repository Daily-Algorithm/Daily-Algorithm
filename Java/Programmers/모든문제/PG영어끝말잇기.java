package 모든문제;

import java.util.Arrays;
import java.util.HashMap;

/**
 * String word의 마지막 문자 : word.charAt(word.length()-1)
 */

public class PG영어끝말잇기 {
	public static int[] solution(int n, String[] words) {
		HashMap<String, Integer> wordsBefore = new HashMap<>();
		int caught = -1;

		wordsBefore.put(words[0], 1);
		for (int i = 1; i < words.length; i++) {
			String word = words[i];
			if (wordsBefore.containsKey(word)) {
				// 만약 있다면 caught = i 하고 break
				caught = i;
				break;
			} else {
				// 없다면 map에 넣는다.
				wordsBefore.put(word, 1);
			}

			String before = words[i - 1];
			char lastOfBefore = before.charAt(before.length() - 1);
			char firstOfWord = word.charAt(0);
			if (lastOfBefore != firstOfWord) {
				// 끝말인지 확인
				caught = i;
				break;
			}
		}

		if (caught == -1) {
			return new int[]{0, 0};
		} else {
			int nth = caught / n;
			int person = caught % n;
			return new int[]{person + 1, nth + 1};
		}
	}



	public static void main(String[] args) {
		int n = 5;
		String[] words = {"hello", "observe", "effect", "take", "either", "recognize", "encourage",
			"ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
		System.out.println(Arrays.toString(solution(n, words)));
	}
}
