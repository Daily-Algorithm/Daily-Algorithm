package StackQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * 아쉬운 점 : 스택큐로 풀지 못함.
 */
public class PG주식가격 {
	public static List<Integer> solution(int[] prices) {
		List<Integer> answer = new ArrayList<>();

		for (int i = 0; i < prices.length; i++) {
			int standard = prices[i];
			for (int j = i + 1; j < prices.length; j++) {
				if (standard > prices[j] || j == prices.length - 1) {
					answer.add(j - i);
					break;
				}
			}
		}
		answer.add(0);

		return answer;
	}

	public static void main(String[] args) {
		int[] prices = {1, 2, 3, 2, 3};
		System.out.println(solution(prices));
	}
}
