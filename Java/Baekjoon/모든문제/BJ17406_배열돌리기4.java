package 모든문제;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17406_배열돌리기4 {
	static FastReader scan = new FastReader();
	static int width, height, orders;
	static int[][] map;
	static int[][] ordersMap;
	static List<int[]> orderSeqL = new ArrayList<>();
	static int answer = Integer.MAX_VALUE;
	static int[] dirX = {1, 0, -1, 0};
	static int[] dirY = {0, 1, 0, -1};

	public static void main(String[] args) {
		input();
		permutation(new boolean[orders], new int[orders], 0);
		for (int[] seq : orderSeqL) {
			answer = Math.min(answer, rotateAndCal(seq));
		}
	}

	private static int rotateAndCal(int[] seq) {
		int[][] temp = clone(map);
		for (int idx : seq) {
			rotate(temp, ordersMap[idx]);
		}
		return calculate(temp);
	}

	private static int calculate(int[][] temp) {
		int minSum = Integer.MAX_VALUE;
		for (int[] line : temp) {
			minSum = Math.min(minSum, Arrays.stream(line).sum());
		}
		return minSum;
	}

	private static void rotate(int[][] origin, int[] order) {
		int[][] temp = clone(origin);
		int[] startXY = new int[]{order[1] - order[2], order[0] - order[2]};
		int[] endXY = new int[]{order[1] + order[2], order[0] + order[2]};
		boolean[][] visited = new boolean[height][width];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (startXY[0] > x || x > endXY[0] || startXY[1] > y || y > endXY[1]) {
					visited[y][x] = true;
				}
			}
		}
		for (int i = 0; i < (endXY[0] - startXY[0] + 1) / 2; i++) {
			Point start = new Point(startXY[0] + i, startXY[1] + i, startXY[0] + dirX[0], startXY[1] + dirY[0], 0);
			Queue<Point> queue = new LinkedList<>();
			queue.add(start);
			visited[start.nextY][start.nextX] = true;
			temp[start.nextY][start.nextX] = origin[start.curY][start.curX];

			while (!queue.isEmpty()) {
				Point cur = queue.poll();
				Point next = new Point(
					cur.nextX, cur.nextY,
					cur.nextX + dirX[cur.dir], cur.nextY + dirY[cur.dir],
					cur.dir
				);
				if (inRange(next)) {
					if (!visited[next.nextY][next.nextX]) {
						visited
					} else {

					}
					queue.add(next);
					temp[next.y + dirY[next.dir]][next.x + dirX[next.dir]] = origin[next.y][next.x];
				}
			}
		}
		origin = temp;
	}

	private static boolean inRange(Point next) {
		return 0 <= next.nextX && next.nextX < width && 0 < next.nextY && next.nextY < height;
	}

	private static int[][] clone(int[][] map) {
		int[][] temp = new int[height][width];
		for (int y = 0; y < height; y++) {
			temp[y] = map[y].clone();
		}
		return temp;
	}

	private static void printMap(int[][] temp) {
		for (int[] line : temp) {
			System.out.println(Arrays.toString(line));
		}
	}

	private static void permutation(boolean[] visited, int[] orderSeq, int depth) {
		if (depth == orders) {
			orderSeqL.add(orderSeq.clone());
			return;
		}
		for (int i = 0; i < orders; i++) {
			if (!visited[i]) {
				visited[i] = true;
				orderSeq[depth] = i;
				permutation(visited, orderSeq, depth + 1);
				visited[i] = false;
			}
		}
	}

	static void input(){
		height = scan.nextInt();
		width = scan.nextInt();
		orders = scan.nextInt();
		map = new int[height][width];
		ordersMap = new int[orders][3];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				map[y][x] = scan.nextInt();
			}
		}

		for (int i = 0; i < orders; i++) {
			ordersMap[i] = new int[]{scan.nextInt() - 1, scan.nextInt() - 1, scan.nextInt()};
		}
	}
//	static void perm(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
//		if (depth == r) {
//			print(output, r);
//			return;
//		}
//
//		for (int i=0; i<n; i++) {
//			if (visited[i] != true) {
//				visited[i] = true;
//				output[depth] = arr[i];
//				perm(arr, output, visited, depth + 1, n, r);
//				output[depth] = 0; // 이 줄은 없어도 됨
//				visited[i] = false;;
//			}
//		}
//	}

	private static class Point{
		int curX, curY, nextX, nextY, dir;

		public Point(int curX, int curY, int nextX, int nextY, int dir) {
			this.curX = curX;
			this.curY = curY;
			this.nextX = nextX;
			this.nextY = nextY;
			this.dir = dir;
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
	  long nextLong() {
	    return Long.parseLong(next());
	  }
	  double nextDouble() {
	    return Double.parseDouble(next());
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
}
