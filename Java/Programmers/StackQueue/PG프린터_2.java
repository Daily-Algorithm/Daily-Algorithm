package StackQueue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PG프린터_2 {

	public static void main(String[] args) {
		int[] priorities = {1, 1, 9, 1, 1, 1};
		int location = 0;
		System.out.println(solution(priorities, location));
	}

	public static int solution(int[] priorities, int location) {
		Queue<Document> queue = new LinkedList<>();
		for (int i = 0; i < priorities.length; i++) {
			queue.add(new Document(i, priorities[i]));
		}
		Arrays.sort(priorities);
		int order = 0;
		for (int i = priorities.length - 1; i >= 0; i--) {
			while (!queue.isEmpty() && queue.peek().prior != priorities[i]) {
				queue.add(queue.poll());
			}
			if (queue.peek().idx == location) {
				return order;
			}
			queue.poll();
			order++;
		}
		return 0;
	}

	static class Document {
		int idx, prior;
		public Document(int idx, int prior) {
			this.idx = idx;
			this.prior = prior;
		}
	}
}
