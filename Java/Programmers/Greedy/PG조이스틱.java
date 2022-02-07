package Greedy;

import java.util.Arrays;
import java.util.List;

/**
 * 다른 사람 풀이
 */
public class PG조이스틱 {
	public static int solution(String name) {
		int answer = 0;
		int[] diff = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3,
			2, 1};

		// 알파벳 스틱
		for (char c : name.toCharArray()) {
			answer += diff[c - 'A'];
		}

		// 위치 스틱
		int length = name.length();
		int min = length - 1;

		for (int i = 0; i < length; i++) {
			int next = i + 1;
			while (next < length && name.charAt(next) == 'A') {
				next ++;
			}
			min = Math.min(min, i + length - next + Math.min(i, length - next));
			// i + length - next : 바로옆의 a들을 제외한 다른 문자들의 길이 -1
			// Math.min(i, length-next) : 왼쪽에서 접근하는 경우와 오른쪽에서 접근하는 경우 중 작은 수
			// min : 양 옆으로 이동하는 횟수
		}

		return answer + min;
	}

	public static void main(String[] args) {
		System.out.println(solution("AAABBBBBBBAA"));
	}
}
