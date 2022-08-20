package Baekjoon.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ4485_녹색옷을입은애가젤다지 {
	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();
	static int[] dirX = {0, 0, 1, -1};
	static int[] dirY = {1, -1, 0, 0};

	public static void main(String[] args) {
		input();
		System.out.println(sb.toString());
	}
	static void input(){
		int problemIdx = 1;
		while (true) {
			int width = scan.nextInt();
			if (width==0) break;
			int[][] map = new int[width][width];
			for (int y = 0; y < width; y++) {
				map[y] = Arrays.stream(scan.nextLine().split(" "))
							   .mapToInt(Integer::new)
							   .toArray();
			}
			dijkstra(map, width, problemIdx);
			problemIdx++;
		}
	}

	private static void dijkstra(int[][] map, int width, int problemIdx) {
		int[][] dist = new int[width][width];
		for (int[] line : dist) {
			Arrays.fill(line, Integer.MAX_VALUE);
		}
		PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
		dist[0][0] = map[0][0];
		pq.add(new Info(0, 0, map[0][0]));
		while (!pq.isEmpty()) {
			Info cur = pq.poll();
			if (dist[cur.toY][cur.toX] < cur.dist) continue;
			for (int i = 0; i < 4; i++) {
				if (!inRange(cur, i, width)) continue;
				Edge edge = new Edge(
					cur.toX + dirX[i],
					cur.toY + dirY[i],
					map[cur.toY + dirY[i]][cur.toX + dirX[i]]
				);
				if (dist[edge.toY][edge.toX] <= cur.dist + edge.weight) continue;
				dist[edge.toY][edge.toX] = cur.dist + edge.weight;
				pq.add(new Info(edge.toX, edge.toY, dist[edge.toY][edge.toX]));
			}
		}
		sb.append("Problem ")
		  .append(problemIdx)
		  .append(": ")
		  .append(dist[width - 1][width - 1])
		  .append("\n");
	}

	private static boolean inRange(Info cur, int i, int width) {
		return 0 <= cur.toX + dirX[i] && cur.toX + dirX[i] < width
			&& 0 <= cur.toY + dirY[i] && cur.toY + dirY[i] < width;
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
	  String nextLine() {
	    String str = "";
	    try {
	      str = br.readLine();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    return str;
	  }
	}

	private static class Info {
		int toX, toY, dist;

		public Info(int toX, int toY, int dist) {
			this.toX = toX;
			this.toY = toY;
			this.dist = dist;
		}
	}

	private static class Edge {
		int toX, toY, weight;

		public Edge(int toX, int toY, int weight) {
			this.toX = toX;
			this.toY = toY;
			this.weight = weight;
		}
	}
}
