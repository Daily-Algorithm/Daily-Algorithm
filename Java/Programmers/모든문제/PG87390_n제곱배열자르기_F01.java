package 모든문제;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PG87390_n제곱배열자르기_F01 {
	static int N;
	static int[][] map;
	static Queue<Point> queue = new LinkedList<>();
	static boolean[][] visited;
	static int[] dirX = {1, 0, 1};
	static int[] dirY = {0, 1, 1};

	public static int[] solution(int n, long left, long right) {
		initialize(n, left, right);
		makeMap();
		return makeArr(left, right);
	}

	private static int[] makeArr(long left, long right) {
		int leftX = Math.toIntExact(left % N);
		int leftY = Math.toIntExact(left / N);
		int rightX = Math.toIntExact(right % N);
		int rightY = Math.toIntExact(right / N);
		int[] arr = new int[Math.toIntExact(right - left + 1)];
		int arrIdx = 0;
		for (int y = leftY; y <= rightY; y++) {
			if (y == leftY) {
				for (int x = leftX; x < N; x++) {
					arr[arrIdx] = map[x][y];
					arrIdx++;
				}
			} else if (y == rightY) {
				for (int x = 0; x <= rightX; x++) {
					arr[arrIdx] = map[x][y];
					arrIdx++;
				}
			} else {
				for (int x = 0; x < N; x++) {
					arr[arrIdx] = map[x][y];
					arrIdx++;
				}
			}
		}
		return arr;
	}

	private static void makeMap() {
		Point first = new Point(0, 0, 1);
		queue.add(first);
		visitAndSet(first);
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			for (int i = 0; i < 3; i++) {
				Point next = new Point(cur.x + dirX[i], cur.y + dirY[i], cur.dist + 1);
				if (inRange(next) && !visited[next.x][next.y]) {
					queue.add(next);
					visitAndSet(next);
				}
			}
		}
	}

	private static void printMatrix(int[][] matrix) {
		for (int y = 0; y < N; y++) {
			StringBuilder sb = new StringBuilder();
			for (int x = 0; x < N; x++) {
				sb.append(matrix[x][y] + " ");
			}
			System.out.println(sb.toString());
		}
	}

	private static boolean inRange(Point next) {
		return next.x >= 0 && next.x < N && next.y >= 0 && next.y < N;
	}

	private static void visitAndSet(Point first) {
		visited[first.x][first.y] = true;
		map[first.x][first.y] = first.dist;
	}

	private static void initialize(int n, long left, long right) {
		N = n;
		map = new int[n][n];
		visited = new boolean[N][N];
		for (boolean[] line : visited) {
			Arrays.fill(line, false);
		}
	}

	public static void main(String[] args) {
//		int n = 3;
		int n = 4;
//		int left = 2;
		int left = 7;
//		int right = 5;
		int right = 14;
		System.out.println(Arrays.toString(solution(n, left, right)));

	}

	static class Point {
		int x, y, dist;
		public Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
}
