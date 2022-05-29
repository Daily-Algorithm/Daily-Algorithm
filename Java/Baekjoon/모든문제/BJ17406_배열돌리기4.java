package 모든문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17406_배열돌리기4 {
	static FastReader scan = new FastReader();
	static int width, height, orders;
	static int[][] map, ordersMap, temp;
	static int answer = Integer.MAX_VALUE;
	static int[] dirX = {1, 0, -1, 0};
	static int[] dirY = {0, 1, 0, -1};

	public static void main(String[] args) {
		input();
		permutation(new boolean[orders], new int[orders], 0);
		System.out.println(answer);
	}

	private static int rotateAndCal(int[] seq) {
		temp = clone(map);
		for (int idx : seq) {
			temp = rotate(ordersMap[idx]);
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

	private static int[][] rotate(int[] order) {
		int[][] moved = clone(temp);
		int[] startXY = new int[]{order[1] - order[2], order[0] - order[2]};
		int[] endXY = new int[]{order[1] + order[2], order[0] + order[2]};
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[height][width];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (!(startXY[0] <= x && x <= endXY[0] && startXY[1] <= y && y <= endXY[1])) {
					visited[y][x] = true;
				}
			}
		}
		for (int i = 0; i < (endXY[0] - startXY[0]) / 2; i++) {
			Point startP = new Point(
				startXY[0] + i,
				startXY[1] + i,
				startXY[0] + i + dirX[0],
				startXY[1] + i + dirY[0],
				0
			);
			visited[startP.nextY][startP.nextX] = true;
			moved[startP.nextY][startP.nextX] = temp[startP.curY][startP.curX];
			queue.add(startP);
			while (!queue.isEmpty()) {
				Point cur = queue.poll();
				Point next = new Point(cur.nextX, cur.nextY, cur.nextX + dirX[cur.dir], cur.nextY + dirY[cur.dir], cur.dir);
				boolean isNextAble = false;
				if (inRange(next) && !visited[next.nextY][next.nextX]) {
					isNextAble = true;
				} else if (next.dir <= 2) {
					Point dirChanged = new Point(
						cur.nextX,
						cur.nextY,
						cur.nextX + dirX[cur.dir + 1],
						cur.nextY + dirY[cur.dir + 1],
						cur.dir + 1
					);

					if (inRange(dirChanged) && !visited[dirChanged.nextY][dirChanged.nextX]) {
						isNextAble = true;
						next = dirChanged;
					}
				}
				if (isNextAble) {
					queue.add(next);
					visited[next.nextY][next.nextX] = true;
					moved[next.nextY][next.nextX] = temp[next.curY][next.curX];
				}
			}
		}

		return moved;
	}

	private static boolean inRange(Point next) {
		return 0 <= next.nextX && next.nextX < width && 0 <= next.nextY && next.nextY < height;
	}

	private static int[][] clone(int[][] map) {
		int[][] temp = new int[height][width];
		for (int y = 0; y < height; y++) {
			temp[y] = map[y].clone();
		}
		return temp;
	}

	private static void permutation(boolean[] visited, int[] orderSeq, int depth) {
		if (depth == orders) {
			answer = Math.min(answer, rotateAndCal(orderSeq.clone()));
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
		temp = new int[height][width];
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
