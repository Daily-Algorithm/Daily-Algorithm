package 다익스트라;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PG72413_합승택시요금_F01 {
	static List<Edge>[] edges;
	static int N, start, A, B;
	static int answer = Integer.MAX_VALUE;

	public static int solution(int n, int s, int a, int b, int[][] fares) {
		init(n, s, a, b, fares);
		for (int intersection = 1; intersection < N + 1; intersection++) {
			answer = Math.min(answer, countMin(start, intersection) + countMin(intersection, A, B));
		}
		return answer;
	}

	private static int countMin(int from, int to1, int to2) {
		int[] dist = new int[N + 1];
		PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[from] = 0;
		pq.add(new Info(from, 0));
		while (!pq.isEmpty()) {
			Info cur = pq.poll();
			if (dist[cur.idx] < cur.dist) continue;
			for (Edge edge : edges[cur.idx]) {
				if (dist[edge.end] <= dist[cur.idx] + edge.weight) continue;
				dist[edge.end] = dist[cur.idx] + edge.weight;
				pq.add(new Info(edge.end, dist[edge.end]));
			}
		}
		return dist[to1] + dist[to2];
	}

	private static int countMin(int from, int to) {
		if (from == to) return 0;

		int[] dist = new int[N + 1];
		PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[from] = 0;
		pq.add(new Info(from, 0));
		while (!pq.isEmpty()) {
			Info cur = pq.poll();
			if (dist[cur.idx] < cur.dist) continue;
			for (Edge edge : edges[cur.idx]) {
				if (dist[edge.end] <= dist[cur.idx] + edge.weight) continue;
				dist[edge.end] = dist[cur.idx] + edge.weight;
				pq.add(new Info(edge.end, dist[edge.end]));
			}
		}
		return dist[to];
	}

	private static void init(int n, int s, int a, int b, int[][] fares) {
		N = n;
		start = s;
		A = a;
		B = b;
		edges = new List[N + 1];
		for (int i = 0; i < N+1; i++) {
			edges[i] = new ArrayList<>();
		}

		for (int[] arr : fares) {
			edges[arr[0]].add(new Edge(arr[1], arr[2]));
			edges[arr[1]].add(new Edge(arr[0], arr[2]));
		}
	}

	public static void main(String[] args) {
		System.out.println(solution(6, 4, 6, 2,
									new int[][]{
										{4, 1, 10},
										{3, 5, 24},
										{5, 6, 2},
										{3, 1, 41},
										{5, 1, 24},
										{4, 6, 50},
										{2, 4, 66},
										{2, 3, 22},
										{1, 6, 25}}
		));


	}

	private static class Edge {
		int end, weight;

		public Edge(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
	}

	private static class Info {
		int idx, dist;

		public Info(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}
	}
}
