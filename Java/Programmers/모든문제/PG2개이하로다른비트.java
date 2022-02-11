package 모든문제;

import java.util.Arrays;

/**
 * 성공 : 직접 2개 이하로 다른 비트를 만들어서 수로 변환
 */

public class PG2개이하로다른비트 {
	public static long[] solution(long[] numbers) {
		long[] answer = new long[numbers.length];

		for (int i = 0; i < numbers.length; i++) {
			answer[i] = calculate(numbers[i]);
		}

		return answer;
	}

	static Long calculate(long number) {
		String[] standardArr = Long.toBinaryString(number)
								   .split("");

		boolean continuedOne = true;	// continuedOne = 이진수가 1이 연속된 수인가

		for (int i = standardArr.length - 1; i >= 0; i--) {
			// 1의 자리부터 시작해 0이 있으면 1로 바꾸고
			if (standardArr[i].equals("0")) {
				standardArr[i] = "1";
				// 그 전 자리수를 0으로 바꾼다.
				if (i != standardArr.length - 1) {
					standardArr[i + 1] = "0";
				}
				continuedOne = false;
				break;
			}
		}

		String answer;
		if (!continuedOne) {
			// 1이 연속된 수가 아니면 for문에서 바꿨기 때문에 그대로 answer
			answer = String.join("", standardArr);
		} else {
			// 1이 연속된 수면 for문에서 바뀌지 않았기 때문에 맨앞자리수를 10으로 바꿔서 answer
			standardArr[0] = "0";
			answer = "1" + String.join("", standardArr);
		}
		return Long.valueOf(answer, 2);
	}

	public static void main(String[] args) {
		long[] numbers = {0};
		System.out.println(Arrays.toString(solution(numbers)));
	}
}
