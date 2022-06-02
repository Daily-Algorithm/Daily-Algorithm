package 다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ14938_서강그라운드 {
	static FastReader scan = new FastReader();
	static int N, limit, E;
	static List<Edge>[] edges;
	static int[] point;

	static int answer = -1;

	public static void main(String[] args) {
		input();
		for (int i = 1; i < N + 1; i++) {
			answer = Math.max(answer, dijkstra(i));
		}
		System.out.println(answer);
	}

	private static int dijkstra(int start) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
		pq.add(new Info(start, 0, point[start]));
		dist[start] = 0;
		while (!pq.isEmpty()) {
			Info info = pq.poll();
			if (dist[info.idx] < info.dist) continue;
			for (Edge e : edges[info.idx]) {
				if (dist[e.to] <= dist[info.idx] + e.weight) continue;
				dist[e.to] = dist[info.idx] + e.weight;
				pq.add(new Info(e.to, dist[e.to], info.point + point[e.to]));
			}
		}
		int earn = 0;
		for (int i = 1; i < N+1; i++) {
			if (dist[i]<=limit) earn += point[i];
		}
		return earn;
	}

	static void input(){
		N = scan.nextInt();
		limit = scan.nextInt();
		E = scan.nextInt();
		edges = new List[N + 1];
		for (int i = 0; i < N + 1; i++) {
			edges[i] = new ArrayList<>();
		}
		point = new int[N + 1];
		for (int i = 1; i < N+1; i++) {
			point[i] = scan.nextInt();
		}

		for (int i = 0; i < E; i++) {
			int start = scan.nextInt();
			int end = scan.nextInt();
			int weight = scan.nextInt();
			edges[start].add(new Edge(end, weight));
			edges[end].add(new Edge(start, weight));
		}
	}

	private static class Edge {
		int to, weight;
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	private static class Info {
		int idx, dist, point;

		public Info(int idx, int dist, int point) {
			this.idx = idx;
			this.dist = dist;
			this.point = point;
		}
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;
		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
