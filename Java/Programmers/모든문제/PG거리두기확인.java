package 모든문제;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PG거리두기확인 {

	static int[] answers = new int[5];
	static List<Point> pList = new ArrayList<>();
	static Queue<Point> queue = new LinkedList<>();
	static boolean[][] visited = new boolean[5][5];
	static String[][] matrix = new String[5][5];
	static int[] dirX = {-1, 1, 0, 0};
	static int[] dirY = {0, 0, -1, 1};

	public static void main(String[] args) {
		String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
			{"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
			{"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
			{"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
			{"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
//		String[][] places = {{"OOPOO", "OPOOO", "OOOOO", "OOOOO", "OOOOO"}};
		System.out.println(Arrays.toString(solution(places)));
	}

	private static int[] solution(String[][] places) {
		for (int i = 0; i < places.length; i++) {
			clearpList();
			matrix = makeMatrix(places[i]);
			answers[i] = isOK();
		}
		return answers;
	}

	private static void clearpList() {
		pList = new ArrayList<>();
	}

	private static void clearQueue() {
		queue = new LinkedList<>();
	}

	private static void clearVisited() {
		for (boolean[] line : visited) {
			Arrays.fill(line, false);
		}
	}

	private static int isOK() {
//		queue.stream()
//			 .forEach(point -> System.out.println(
//				 "P is located in x : " + point.x + " y : " + point.y + " value : " + value(point)));
		for (Point firstP : pList) {
		    clearQueue();
			clearVisited();
			queue.add(firstP);
			System.out.println("first P is located in x : " + firstP.x + " y : " + firstP.y + " value : " + value(firstP));
			while (!queue.isEmpty()) {
				Point cur = queue.poll();
				visited[cur.x][cur.y] = true;
				for (int i = 0; i < 4; i++) {
					Point next = new Point(
						cur.x + dirX[i], cur.y + dirY[i], cur.dist + 1,
						null
					);
					if (inRange(next) && !visited[next.x][next.y] && !value(next).equals("X")) {
						String nextV = value(next);
						next.path = cur.path + " " + nextV + path(next);
//						System.out.println("next candidate "+ "P is located in x : " + next.x + " y : " + next.y + " value : " + nextV);
						if (nextV.equals("O")) {
							queue.add(next);
							visited[next.x][next.y] = true;
						} else if (nextV.equals("P")) {
//							System.out.println(next.path);
							if (next.dist <= 2) {
								System.out.println(
									"reason " + "P is located in x : " + next.x + " y : "
										+ next.y + " value : " + nextV);
								System.out.println(next.path);
								return 0;
							}
						}
					}
				}
			}
		}
		return 1;
	}

	private static boolean inRange(Point next) {
		return next.x >= 0 && next.x < 5 && next.y >= 0 && next.y < 5;
	}

	private static String value(Point point) {
		return matrix[point.x][point.y];
	}

	private static String path(Point point) {
		return String.valueOf(5 * point.y + point.x);
	}

	private static String[][] makeMatrix(String[] place) {
		String[][] matrix = new String[5][5];
		for (int y = 0; y < 5; y++) {
			String[] split = place[y].split("");
			for (int x = 0; x < 5; x++) {
				matrix[x][y] = split[x];
				if (matrix[x][y].equals("P")) {
					pList.add(new Point(x, y, 0, matrix[x][y] + String.valueOf(5 * y + x)));
				}
			}
		}

		printMatrix(matrix);
		System.out.println("=============");
		return matrix;
	}

	private static void printMatrix(String[][] matrix) {
		for (int y = 0; y < 5; y++) {
			StringBuilder sb = new StringBuilder();
			for (int x = 0; x < 5; x++) {
				sb.append(matrix[x][y] + " ");
			}
			System.out.println(sb.toString());
		}
	}

	static class Point {
		int x, y, dist;
		String path;
		public Point(int x, int y, int dist, String path) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.path = path;
		}
	}
}
