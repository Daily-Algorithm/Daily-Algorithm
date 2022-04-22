package 순열조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BFS로 하면 왜 가지 뻗는 모양은 확인하지 못하는거지?
 */
public class BJ1941_소문난칠공주 {
	static FastReader scan = new FastReader();
	static int[] dirX = {1, -1, 0, 0};
	static int[] dirY = {0, 0, 1, -1};
	static String[][] map = new String[5][5];
	static Map<Integer, int[]> idxToXY = new HashMap<>();
	static boolean[] visited = new boolean[25];
	static int answer = 0;
	public static void main(String[] args) {
		Arrays.fill(visited, false);
		input();
		makeIdxToXY();
		combination(visited, 0, 0);
		System.out.println(answer);
	}

	private static void combination(boolean[] visited, int checked, int chose) {
		if (chose == 7) {
			if (isSevenPrincess(visited)) {
				answer++;
			}
			return;
		}
		if (checked == 25) {
			return;
		}

		visited[checked] = true;
		combination(visited, checked + 1, chose + 1);
		visited[checked] = false;
		combination(visited, checked + 1, chose);
	}

	private static boolean isSevenPrincess(boolean[] chosenArr) {
		for (int idx = 0; idx < 25; idx++) {
			if (chosenArr[idx]) {
				Queue<Integer> queue = new LinkedList<>();
				boolean[] visited = new boolean[25];
				Arrays.fill(visited, false);

				int cnt = 1;
				int S = value(idxToXY.get(idx)).equals("S") ? 1 : 0;
				queue.add(idx);
				visited[idx] = true;
				while (!queue.isEmpty()) {
					Integer curIdx = queue.poll();
					int[] curXY = idxToXY.get(curIdx);
					for (int i = 0; i < 4; i++) {
						Integer nextX = curXY[0] + dirX[i];
						Integer nextY = curXY[1] + dirY[i];
						Integer nextIdx = xyToIdx(nextX, nextY);
						if (inRange(nextX, nextY) && chosenArr[nextIdx] && !visited[nextIdx]) {
							cnt++;
							queue.add(nextIdx);
							visited[nextIdx] = true;
							if (value(new int[]{nextX, nextY}).equals("S")) {S++;}
						}
					}
				}
				return cnt == 7 && S >= 4;
			}
		}
		return false;
	}

//	private static void countT(boolean[] choosed) {
//		int cnt = 0;
//		for (boolean b: choosed) {
//			if (b) {
//				cnt++;
//			}
//		}
//		System.out.println(cnt);
//	}

	private static String value(int[] xy) {
		return map[xy[0]][xy[1]];
	}
//
//	private static boolean qualified(boolean[][] matrix, Point start) {
//		boolean[][] visited = new boolean[5][5];
//		for (boolean[] line : visited) {
//			Arrays.fill(line, false);
//		}
//
//		Queue<Point> queue = new LinkedList<>();
//		queue.add(start);
//		visited[start.x][start.y] = true;
//
//		int cnt = 1;
//		int S = value(start).equals("S") ? 1 : 0;
//		while (!queue.isEmpty()) {
//			Point cur = queue.poll();
//			for (int i = 0; i < 4; i++) {
//				Point next = new Point(cur.x+dirX[i], cur.y+dirY[i]);
//				if (inRange(next) && matrix[next.x][next.y] && !visited[next.x][next.y]) {
//					cnt++;
//					S = value(next).equals("S") ? S + 1 : S;
//					queue.add(next);
//				}
//			}
//		}
//		return cnt == 7 && S >= 4;
//	}

	private static boolean inRange(int x, int y) {
		return x >= 0 && x < 5 && y >= 0 && y < 5;
	}

	static void input(){
		for (int y = 0; y < 5; y++) {
			String[] split = scan.next().split("");
			for (int x = 0; x < 5; x++) {
				map[x][y] = split[x];
			}
		}
	}

	private static void makeIdxToXY() {
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				int idx = xyToIdx(x, y);
				idxToXY.put(idx, new int[]{x, y});
			}
		}
	}

	private static int xyToIdx(int x, int y) {
		return 5 * x + y;
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
	}
}
