package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1405_미친로봇 {

	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] dirX = {1, -1, 0, 0}; // 동서남북
	static int[] dirY = {0, 0, -1, 1};
	static double[] percentArr = new double[4];
	static double answer = 0;

	public static void main(String[] args) {
		input();
		boolean[][] visited = new boolean[2 * N + 1][2 * N + 1];
		visited[N][N] = true;
		permutation(N, 1, new Point(N, N), visited);
		System.out.println(answer);
	}

	private static void permutation(int move, double percent, Point point, boolean[][] visited) {
		if (move == 0) {
			answer += percent;
			return;
		}
		for (int j = 0; j < 4; j++) {
			Point next = new Point(point.x + dirX[j], point.y + dirY[j]);
			if (!visited[next.y][next.x]) {
				visited[next.y][next.x] = true;
				permutation(move - 1, percent * percentArr[j], next, visited);
				visited[next.y][next.x] = false;
			}
		}
	}

	static void input() {
		N = scan.nextInt();
		percentArr[0] = scan.nextInt() / (double) 100;
		percentArr[1] = scan.nextInt() / (double) 100;
		percentArr[2] = scan.nextInt() / (double) 100;
		percentArr[3] = scan.nextInt() / (double) 100;
	}

	static class Point {

		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
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
