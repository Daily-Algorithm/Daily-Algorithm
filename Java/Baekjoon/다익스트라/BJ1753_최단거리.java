package 다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1753_최단거리 {
	static FastReader scan = new FastReader();
	static int N, E, startIdx;
	static List<Edge>[] edges;
	static int[] dist;

	public static void main(String[] args) {
		input();
		dijkstra(startIdx);
		printAnswer();
	}

	private static void printAnswer() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if (dist[i] == Integer.MAX_VALUE) sb.append("INF\n");
			else sb.append(dist[i]).append('\n');
		}
		System.out.print(sb);
	}

	private static void dijkstra(int startIdx) {
		for (int i = 0; i < N + 1; i++) dist[i] = Integer.MAX_VALUE;
		PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
		pq.add(new Info(startIdx, 0));
		dist[startIdx] = 0;
		while (!pq.isEmpty()) {
			Info info = pq.poll();
			if (dist[info.idx] < info.dist) continue;
			for (Edge e : edges[info.idx]) {
				if (dist[e.to] <= dist[info.idx] + e.weight) continue;
				dist[e.to] = dist[info.idx] + e.weight;
				pq.add(new Info(e.to, dist[e.to]));
			}
		}
	}

	static void input(){
		N = scan.nextInt();
		E = scan.nextInt();
		startIdx = scan.nextInt();
		dist = new int[N + 1];
		edges = new List[N + 1];
		for (int i = 0; i < N + 1; i++) {
			edges[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			int start = scan.nextInt();
			int end = scan.nextInt();
			int weight = scan.nextInt();
			edges[start].add(new Edge(end, weight));
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
		int idx, dist;

		public Info(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
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
