package DFSBFS;

import java.util.LinkedList;
import java.util.Queue;

public class PG86971_전력망둘로나누기 {
	static LinkedList<Integer>[] adj;
	static int N;
	static int answer;

	public static int solution(int n, int[][] wires) {
		input(n, wires);
		for (int from = 1; from <= N; from++) {
			LinkedList<Integer> adjWithFrom = adj[from];
			for (int not : adjWithFrom) {
				bfs(from, not);
			}
		}
		return answer;
	}

	private static void bfs(int from, int not) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		int adjNum = 1;
		queue.add(from);
		visited[from] = true;
		while (!queue.isEmpty()) {
			LinkedList<Integer> adjL = adj[queue.poll()];
			for (Integer element : adjL) {
				if (!visited[element] && element != not) {
					adjNum++;
					queue.add(element);
					visited[element] = true;
				}
			}
		}
		answer = Math.min(answer, Math.abs(N - 2 * adjNum));
	}

	private static void input(int n, int[][] wires) {
		N = n;
		answer = wires.length;
		adj = new LinkedList[N + 1];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new LinkedList<>();
		}

		for (int[] info : wires) {
			adj[info[0]].add(info[1]);
			adj[info[1]].add(info[0]);
		}
	}

	public static void main(String[] args) {
		int n = 7;
//		int[][] wires = {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};
//		int[][] wires = {{1,2},{2,3},{3,4}};
		int[][] wires = {{1,2},{2,7},{3,7},{3,4},{4,5},{6,7}};
		System.out.println(solution(n, wires));
	}
}
