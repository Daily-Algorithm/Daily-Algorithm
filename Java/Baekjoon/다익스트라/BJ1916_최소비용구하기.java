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

public class BJ1916_최소비용구하기 {

	static FastReader scan = new FastReader();
	static int N, E, start, end;
	static int[] dist;
	static List<Edge>[] edges;

	public static void main(String[] args) {
		input();
		dijkstra(start);
		System.out.println(dist[end]);
	}

	private static void dijkstra(int start) {
		PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
		pq.add(new Info(start, 0));
		dist[start] = 0;
		while (!pq.isEmpty()) {
			Info cur = pq.poll();
			if (dist[cur.idx] < cur.dist) continue;
			for (Edge e : edges[cur.idx]) {
				if (dist[e.end] <= dist[cur.idx] + e.weight) continue;
				dist[e.end] = dist[cur.idx] + e.weight;
				pq.add(new Info(e.end, dist[e.end]));
			}
		}
	}

	static void input(){
		N = scan.nextInt();
		E = scan.nextInt();
		dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		edges = new List[N + 1];
		for (int i = 0; i < N + 1; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			edges[scan.nextInt()].add(new Edge(scan.nextInt(), scan.nextInt()));
		}
		start = scan.nextInt();
		end = scan.nextInt();
	}

	private static class Info {
		int idx, dist;

		public Info(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}
	}

	private static class Edge {
		int end, weight;

		public Edge(int end, int weight) {
			this.end = end;
			this.weight = weight;
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
