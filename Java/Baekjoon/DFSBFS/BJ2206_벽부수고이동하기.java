package DFSBFS;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2206_벽부수고이동하기 {
	static int[][] map;
	static int width, height;
	static boolean[][][] visited;
	static Queue<Pos> queue = new LinkedList<>();
	static Pos start = new Pos(0, 0, 1, 0);
	static int[] dirX = {0, 0, -1, 1}; // 상하좌우
	static int[] dirY = {-1, 1, 0, 0}; // 상하좌우
	private static class Pos{
		int x, y, dist, crash;

		public Pos(int x, int y, int dist, int crash) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.crash = crash;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		map = new int[width][height];
		visited = new boolean[width][height][2];	// [0] = crash 사용X, [1] = crash 사용O
		for (int y = 0; y < height; y++) {
			String[] line = br.readLine().split("");
			for (int x = 0; x < width; x++) {
				map[x][y] = Integer.parseInt(line[x]);
			}
		}

		queue.add(start);
		visited[start.x][start.y][0] = true;
		while (!queue.isEmpty()) {
			Pos curP = queue.poll();
			if (isEndP(curP)) {
				out.println(curP.dist);
				return;
			}
			for (int i = 0; i < 4; i++) {
				Pos nextP = new Pos(curP.x + dirX[i], curP.y + dirY[i], curP.dist + 1, curP.crash);
				if (inRange(nextP)) {
					if (valueOf(nextP) == 0
						&& !visited[nextP.x][nextP.y][nextP.crash == 0 ? 0 : 1]) {
						visited[nextP.x][nextP.y][curP.crash == 0 ? 0 : 1] = true;
						queue.add(nextP);
					} else if (valueOf(nextP) == 1
						&& nextP.crash == 0
						&& !visited[nextP.x][nextP.y][1]) {
						nextP.crash = 1;
						queue.add(nextP);
						visited[nextP.x][nextP.y][1] = true;
					}
				}
			}
		}
		out.println(-1);

	}

	private static boolean isEndP(Pos curP) {
		return curP.x == width - 1 && curP.y == height - 1;
	}

	private static int valueOf(Pos nextP) {
		return map[nextP.x][nextP.y];
	}

	private static boolean inRange(Pos nextP) {
		return nextP.x >= 0 && nextP.x < width && nextP.y >= 0 && nextP.y < height;
	}
}
