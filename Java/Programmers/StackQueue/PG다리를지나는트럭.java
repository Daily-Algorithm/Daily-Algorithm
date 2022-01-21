package StackQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// solved : sum 때문에 시간초과 남 -> 추가할 때만 ~ 해결

public class PG다리를지나는트럭 {
	public static int solution(int bridge_length, int weight, int[] truck_weights) {
		Queue<Integer> truckQ = new LinkedList<>();
		for (int i = 0; i < truck_weights.length; i++) {
			truckQ.add(truck_weights[i]);
		}

		Queue<Integer> bridgeQ = new LinkedList<>();
		for (int i = 0; i < bridge_length; i++) {
			bridgeQ.add(0);
		}

		int count = 0;
		int bridgeW = 0;
		List<Integer> crossed = new ArrayList<>();
		while (crossed.size() != truck_weights.length) {
			count++;

			int waitingT = truckQ.peek() == null ? 0 : truckQ.peek();
			int crossedT = bridgeQ.poll();

			if (crossedT > 0) {
				crossed.add(crossedT);
				bridgeW -= crossedT;
			}
			if (bridgeW + waitingT <= weight && !truckQ.isEmpty()) {
				// waitingT 트럭이 포함돼도 될 때
				bridgeQ.offer(truckQ.poll());
				bridgeW += waitingT;
			} else {
				bridgeQ.offer(0);
			}
		}

		return count;
	}

	static int weightOnBridge(Queue<Integer> bridgeQ) {
		ArrayList<Integer> bridgeList = new ArrayList<>(bridgeQ);
		return bridgeList.stream()
						 .mapToInt(Integer::intValue)
						 .sum();
	}

	public static void main(String[] args) {
		int bridge_length = 100;
		int weight = 100;
		int[] truck_weight = {10};

		System.out.println(solution(bridge_length, weight, truck_weight));
	}
}
