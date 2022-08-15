package Baekjoon.시뮬레이션구현;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ20058_마법사상어와파이어스톰 {

	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();
	static int N,Q,total;
	static int max = 0;
	static int[][] map;
	static int[] orders;
	static int[] dirX = {0,0,1,-1};
	static int[] dirY = {1,-1,0,0};

	public static void main(String[] args) {
		input();
		process();
	}

	private static void process() {
		for (int order : orders) {
			int sectionWidth = (int) Math.pow(2, order);
			rotate(sectionWidth);
			shrink();
		}
		bfs();

		sb.append(total + "\n" + max);
		System.out.println(sb.toString());
	}

	private static void bfs() {
		boolean[][] visited = new boolean[map.length][map.length];
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map.length; x++) {
				if (map[y][x] != 0 && !visited[y][x]) {
					Queue<Point> queue = new LinkedList<>();
					queue.add(new Point(x, y));
					visited[y][x] = true;
					int cnt = 1;
					while (!queue.isEmpty()) {
						Point cur = queue.poll();
						for (int i = 0; i < 4; i++) {
							Point next = new Point(cur.x + dirX[i], cur.y + dirY[i]);
							if (inRange(next)
								&& map[next.y][next.x] != 0
								&& !visited[next.y][next.x]
							) {
								visited[next.y][next.x] = true;
								queue.add(next);
								cnt++;
							}
						}
					}
					max = Math.max(max, cnt);
				}
			}
		}
	}

	private static void shrink() {
		Queue<Point> queue = new LinkedList<>();
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map.length; x++) {
				if (map[y][x] != 0) {
					int adjIce = 0;
					for (int i = 0; i < 4; i++) {
						Point adjP = new Point(x + dirX[i], y + dirY[i]);
						if (inRange(adjP) && isIce(adjP)) adjIce++;
					}
					if (adjIce < 3) {
						queue.add(new Point(x, y));
					}
				}
			}
		}
		total -= queue.size();
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			map[cur.y][cur.x] -= 1;
		}
	}

	private static boolean inRange(Point point) {
		return 0 <= point.x && point.x < map.length && 0 <= point.y && point.y < map.length;
	}

	private static boolean isIce(Point point) {
		return map[point.y][point.x] != 0;
	}

	private static void rotate(int sectionWidth) {
		for (int yStart = 0; yStart < map.length; yStart += sectionWidth) {
			for (int xStart = 0; xStart < map.length; xStart += sectionWidth) {
				int[][] temp = new int[sectionWidth][sectionWidth];
				for (int y = yStart; y < yStart + sectionWidth; y++) {
					for (int x = xStart; x < xStart + sectionWidth; x++) {
						temp[x - xStart][sectionWidth - 1 - (y - yStart)] = map[y][x];
					}
				}
				for (int y = yStart; y < yStart + sectionWidth; y++) {
					for (int x = xStart; x < xStart + sectionWidth; x++) {
						map[y][x] = temp[y - yStart][x - xStart];
					}
				}
			}
		}
	}

	static void input(){
		N = scan.nextInt();
		Q = scan.nextInt();
		int width = (int) Math.pow(2, N);
		map = new int[width][width];
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < width; x++) {
				map[y][x] = scan.nextInt();
				total += map[y][x];
			}
		}
		orders = new int[Q];
		for (int i = 0; i < Q; i++) {
			orders[i] = scan.nextInt();
		}
	}
	static class FastReader {
	  BufferedReader br;
	  StringTokenizer st;
	  public FastReader() {
	    br = new BufferedReader(new InputStreamReader(System.in));
	  }
	  public FastReader(String s) throws FileNotFoundException {
	    br = new BufferedReader(new FileReader(new File(s)));
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

	private static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
