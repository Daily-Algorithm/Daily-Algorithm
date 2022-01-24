package Heap;

import java.util.PriorityQueue;

public class PG더맵게 {
	public static int solution(int[] scoville, int K) {
		PriorityQueue<Integer> heap = new PriorityQueue<>();

		for(int i = 0; i < scoville.length; i++) {
			heap.add(scoville[i]);
		}

		int mix = 0;
		while (heap.peek() < K) {
			mix++;
			if (heap.size() < 2) {
				mix = -1; break;
			}

			int min = heap.poll();
			int secondMin = heap.poll();
			int mixedSco = min + (secondMin * 2);
			heap.add(mixedSco);
		}

		return mix;
	}
	public static void main(String[] args) {
		int[] scoville = {1, 2, 3, 9, 10, 12};
		int K = 7;

		System.out.println(solution(scoville, K));

	}
}
