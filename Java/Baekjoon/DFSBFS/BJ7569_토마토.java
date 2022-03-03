package DFSBFS;

import static java.lang.System.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 아니 도대체 49~51번째줄 왜 line.charAt(x)-'0'으로 하면 왜 안 되는겨?
 */
public class BJ7569_토마토 {
	static int WIDTH, LENGTH, HEIGHT;
	static int[] dirX = {0, 0, -1, 1, 0, 0};
	static int[] dirY = {-1, 1, 0, 0, 0, 0};	// 상,하,좌,우,위,아래
	static int[] dirZ = {0, 0, 0, 0, 1, -1};
	static int[][][] map;
	static int[][][] dist;
	static boolean[][][] visited;
	static LinkedList<Pos> queue = new LinkedList<>();
	static int maxDist = 0;

	static class Pos {
		int xPos;
		int yPos;
		int zPos;

		public Pos(int xPos, int yPos, int zPos) {
			this.xPos = xPos;
			this.yPos = yPos;
			this.zPos = zPos;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		WIDTH = Integer.parseInt(st.nextToken());
		LENGTH = Integer.parseInt(st.nextToken());
		HEIGHT = Integer.parseInt(st.nextToken());
		map = new int[HEIGHT][LENGTH][WIDTH];	// z, y, x
		dist = new int[HEIGHT][LENGTH][WIDTH];
		visited = new boolean[HEIGHT][LENGTH][WIDTH];

		// map 만들기
		for (int z = 0; z < HEIGHT; z++) {
			for (int y = 0; y < LENGTH; y++) {
				String[] line = br.readLine().split(" ");
				for (int x = 0; x < WIDTH; x++) {
					map[z][y][x] = Integer.parseInt(line[x]);
					if (map[z][y][x] == 1) {
						queue.add(new Pos(x, y, z));
						dist[z][y][x] = 0;
					}
				}
			}
		}
		BFS();

		if (allRotten()) {
			out.println(maxDist);
		} else {
			out.println(-1);
		}
	}

	private static void BFS() {
		while (!queue.isEmpty()) {
			Pos currP = queue.poll();
			for (int i = 0; i < 6; i++) {
				// 6 = 상하좌우위아래
				Pos nextP = new Pos(
					currP.xPos + dirX[i], currP.yPos + dirY[i], currP.zPos + dirZ[i]);

				if (qualified(nextP)
					&& map[nextP.zPos][nextP.yPos][nextP.xPos] == 0) {
					queue.add(nextP);
					map[nextP.zPos][nextP.yPos][nextP.xPos] = 1;
					dist[nextP.zPos][nextP.yPos][nextP.xPos] =
						dist[currP.zPos][currP.yPos][currP.xPos] + 1;
					maxDist = Math.max(maxDist, dist[nextP.zPos][nextP.yPos][nextP.xPos]);
				}
			}
		}
	}

	private static boolean qualified(Pos nextP) {
		return nextP.xPos >= 0 && nextP.xPos < WIDTH
			&& nextP.yPos >= 0 && nextP.yPos < LENGTH
			&& nextP.zPos >= 0 && nextP.zPos < HEIGHT;
	}

	private static boolean allRotten() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < LENGTH; j++) {
				for (int k = 0; k < WIDTH; k++) {
					if (map[i][j][k] == 0) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
