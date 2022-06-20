package 배열돌리기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16927_배열돌리기2 {
	static FastReader scan = new FastReader();
	static int height, width, R;
	static int[] dirX = {0, 1, 0, -1};
	static int[] dirY = {1, 0, -1, 0};
	static int[][] map;
	static int[][] answer;

	public static void main(String[] args) {
		input();
		counterClockWise();
		printMap(answer);
	}

	private static void printMap(int[][] map) {
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				sb.append(map[y][x]);
				sb.append(" ");
			}
			if (y < height - 1) {
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

	private static void counterClockWise() {
		for (int i = 0; i < Math.min(height, width) / 2; i++) {
			int steps = R % (2 * (height - 2 * i) + 2 * (width - 2 * i) - 4);
			Queue<Point> queue = new LinkedList<>();
			Point startP = new Point(i, i, 0);
			queue.add(startP);
			while (!queue.isEmpty()) {
				Point curP = queue.poll();
				Point nextP = new Point(curP.x + dirX[curP.dir], curP.y + dirY[curP.dir], curP.dir);
				if (nextP.x == i && nextP.y == i) {
					moveSteps(curP, steps, i);
					continue;
				}
				if (!inRange(nextP, i)) {
					curP.dir = (curP.dir + 1) % 4;
					queue.add(curP);
				} else {
					moveSteps(curP, steps, i);
					queue.add(nextP);
				}
			}
		}
	}

	private static void moveSteps(Point startP, int steps, int dept) {
		// dept = 몇번째 테두리
		// steps = 이동할 수
		// startP = 이동할 포인트
		Point point = new Point(startP.x, startP.y, startP.dir);
		while (steps > 0) {
			Point nextP = new Point(point.x + dirX[point.dir], point.y + dirY[point.dir], point.dir);
			if (!inRange(nextP, dept)) {
				point.dir = (point.dir + 1) % 4;
			} else {
				point = nextP;
				steps--;
			}
		}
		answer[point.y][point.x] = map[startP.y][startP.x];
	}

	private static boolean inRange(Point nextP, int nth) {
		return nth <= nextP.x && nextP.x <= width - nth - 1 &&
			nth <= nextP.y && nextP.y <= height - nth - 1;
	}

	static void input(){
		height = scan.nextInt();
		width = scan.nextInt();
		R = scan.nextInt();
		map = new int[height][width];
		answer = new int[height][width];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				map[y][x] = scan.nextInt();
			}
		}
	}

	static class Point {
		int x, y, dir;

		public Point(int x, int y, int dir) {
			this.x = x;
			this.y = y;
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
