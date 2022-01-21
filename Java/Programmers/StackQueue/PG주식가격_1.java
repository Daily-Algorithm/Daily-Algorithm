package StackQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 이전에 푼 방식, 큐 이용함 - 형편없음
 */
class PG주식가격_1 {
	public List<Integer> solution(int[] prices) {
		List<Integer> answer = new ArrayList<>();

		Queue<Integer> priceQ = new LinkedList<>();
		for (int price:prices) {
			priceQ.offer(price);
		}

		while (priceQ.size() != 0) {
			int currPrice = priceQ.poll();
			int time = 0;
			if (priceQ.size() != 0) {
				for (int price:priceQ) {
					time++;
					if (price < currPrice) {
						break;
					}
				}
			}
			answer.add(time);
		}
		return answer;
	}
}

