package 모든문제;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PG68645_삼각달팽이 {
	static int[] dirX = {0,1,-1}; // down, right, left
	static int[] dirY = {1, 0, -1};
	static int layer, max;
	static boolean[][] visited;
	static int[][] map;
	static int[] answer;
	public static int[] solution(int n) {
		init(n);
		fillNums();
		mergeArr();
		return answer;
	}

	private static void mergeArr() {
		int idx = 0;
		for (int y = 0; y < layer; y++) {
			for (int x = 0; x < map[y].length; x++) {
				answer[idx] = map[y][x];
				idx++;
			}
		}
	}

	private static void fillNums() {
		Snail start = new Snail(0, 0, 0, 1);
		Queue<Snail> queue = new LinkedList<>();
		queue.add(start);
		visitAndFill(start);
		while (!queue.isEmpty()) {
			Snail cur = queue.poll();
			if (cur.num == max) {
				return;
			}
			Snail next;
			if (ableSameDir(cur)) {
				next = new Snail(
					cur.x + dirX[cur.dir], cur.y + dirY[cur.dir], cur.dir, cur.num + 1);
			} else {
				next = new Snail(
					cur.x + dirX[(cur.dir + 1) % 3], cur.y + dirY[(cur.dir + 1) % 3], (cur.dir + 1) % 3, cur.num + 1);
			}
			queue.add(next);
			visitAndFill(next);
		}
	}

	private static boolean ableSameDir(Snail snail) {
		int nextX = snail.x + dirX[snail.dir];
		int nextY = snail.y + dirY[snail.dir];
		return 0 <= nextX && nextX <= nextY && nextY < layer && !visited[nextY][nextX];
	}

	private static void visitAndFill(Snail snail) {
		visited[snail.y][snail.x] = true;
		map[snail.y][snail.x] = snail.num;
	}

	private static void init(int n) {
		layer = n;
		max = n * (n + 1) / 2;
		answer = new int[max];
		visited = new boolean[n][];
		map = new int[n][];

		for (int i = 0; i < n; i++) {
			visited[i] = new boolean[i + 1];
			map[i] = new int[i + 1];
		}
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(4)));
		System.out.println();
		System.out.println(Arrays.toString(solution(5)));
		System.out.println();
		System.out.println(Arrays.toString(solution(6)));

	}

	private static class Snail {
		int x, y, dir, num;

		public Snail(int x, int y, int dir, int num) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.num = num;
		}
	}
}
