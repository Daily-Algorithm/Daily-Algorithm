package DFSBFS;

import java.util.LinkedList;
import java.util.Queue;

public class PG43162_네트워크 {

	public int solution(int n, int[][] computers) {
		int answer = 0;
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[n];
		for (int i = 0; i < computers.length; i++) {
			if (!visited[i]) {
				queue.add(i);
				visited[i] = true;
				answer++;
				while (!queue.isEmpty()) {
					int[] connected = computers[queue.poll()];
					for (int adjIdx = 0; adjIdx < connected.length; adjIdx++) {
						if (connected[adjIdx] == 1 && !visited[adjIdx]) {
							queue.add(adjIdx);
							visited[adjIdx] = true;
						}
					}
				}
			}
		}
		return answer;
	}
}
