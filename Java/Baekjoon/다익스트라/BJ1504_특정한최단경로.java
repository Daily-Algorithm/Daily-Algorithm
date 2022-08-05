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

public class BJ1504_특정한최단경로 {
	static FastReader scan = new FastReader();
	static int N, E, A, B;
	static List<Edge>[] adj;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) {
		input();

		int AB = dijkstra(A, B);
		if (AB == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		} else {
			// 1 -> A -> B -> N
			int ABPath = 0;
			int startToA = dijkstra(1, A);
			int BToEnd = dijkstra(B, N);
			if (startToA != Integer.MAX_VALUE && BToEnd != Integer.MAX_VALUE) {
				ABPath += startToA + AB + BToEnd;
			} else {
				ABPath = Integer.MAX_VALUE;
			}

			// 1 -> B -> A -> N
			int BAPath = 0;
			int startToB = dijkstra(1, B);
			int AToEnd = dijkstra(A, N);
			if (startToB != Integer.MAX_VALUE && AToEnd != Integer.MAX_VALUE) {
				BAPath += startToB + AB + AToEnd;
			} else {
				BAPath = Integer.MAX_VALUE;
			}

			answer = Math.min(ABPath, BAPath);
			if (answer == Integer.MAX_VALUE) {
				System.out.println(-1);
			} else {
				System.out.println(answer);
			}
		}
	}

	private static int dijkstra(int start, int end) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
		pq.add(new Info(start, 0));
		dist[start] = 0;
		while (!pq.isEmpty()) {
			Info cur = pq.poll();
			if (dist[cur.to] < cur.dist) continue;
			for (Edge edge : adj[cur.to]) {
				if (dist[edge.to] <= cur.dist + edge.weight) continue;
				dist[edge.to] = cur.dist + edge.weight;
				pq.add(new Info(edge.to, dist[edge.to]));
			}
		}
		return dist[end];
	}

	static void input(){
		N = scan.nextInt();
		E = scan.nextInt();
		adj = new List[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList();
		}
		for (int i = 0; i < E; i++) {
			int from = scan.nextInt();
			int to = scan.nextInt();
			int weight = scan.nextInt();
			adj[from].add(new Edge(to, weight));
			adj[to].add(new Edge(from, weight));
		}
		A = scan.nextInt();
		B = scan.nextInt();
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
