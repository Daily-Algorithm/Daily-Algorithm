package 다익스트라;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PG12978_배달 {
	public int solution(int N, int[][] road, int K) {
		int start = 1;
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		List<Edge>[] edges = new List[N + 1];
		for (int i = 1; i < N + 1; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int[] arr : road) {
			int a = arr[0];
			int b = arr[1];
			int weight = arr[2];
			edges[a].add(new Edge(b, weight));
			edges[b].add(new Edge(a, weight));
		}
		for (int i = 0; i < N + 1; i++) dist[i] = Integer.MAX_VALUE;
		PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
		pq.add(new Info(start, 0));
		dist[start] = 0;
		while (!pq.isEmpty()) {
			Info info = pq.poll();
			if (dist[info.idx] < info.dist) continue;
			for (Edge e : edges[info.idx]) {
				if (dist[e.to] <= dist[info.idx] + e.weight) continue;
				dist[e.to] = dist[info.idx] + e.weight;
				pq.add(new Info(e.to, dist[e.to]));
			}
		}
		return (int) Arrays.stream(dist)
								.filter(value -> value <= K)
								.count() - 1;
	}

	private class Edge {
		int to, weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	private class Info {
		int idx, dist;

		public Info(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}
	}
}
