package 다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1753_최단경로_T01 {
	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();
	static int V, E, S;
	static int[] dist;
	static ArrayList<Edge>[] adj;

	public static void main(String[] args) {
		input();
		dijkstra(S);
		print();
	}

	private static void print() {
		for (int i = 1; i < V + 1; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				sb.append("INF\n");
			} else {
				sb.append(dist[i] + "\n");
			}
		}
		System.out.println(sb.toString());
	}

	static void input(){
		V = scan.nextInt();
		E = scan.nextInt();
		S = scan.nextInt();
		adj = new ArrayList[V + 1];
		dist = new int[V + 1];
		for (int i = 0; i <= V; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			int from = scan.nextInt();
			int to = scan.nextInt();
			int weight = scan.nextInt();
			adj[from].add(new Edge(to, weight));
		}
	}

	static void dijkstra(int start) {
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
		pq.add(new Info(S, 0));
		dist[S] = 0;
		while (!pq.isEmpty()) {
			Info cur = pq.poll();
			if (dist[cur.to] < cur.dist) continue;
			for (Edge edge : adj[cur.to]) {
				if (dist[edge.to] <= cur.dist + edge.weight) continue;
				dist[edge.to] = cur.dist + edge.weight;
				pq.add(new Info(edge.to, dist[edge.to]));
			}
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

	private static class Edge {
		int to, weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	private static class Info {
		int to, dist;

		public Info(int to, int dist) {
			this.to = to;
			this.dist = dist;
		}
	}
}
