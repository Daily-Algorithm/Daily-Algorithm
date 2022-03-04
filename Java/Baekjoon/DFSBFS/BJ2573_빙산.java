package DFSBFS;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2573_빙산 {
	static int width, height;
	static Queue<Ice> queue = new LinkedList<>();
	static int[] dirX = {0, 0, -1, 1};
	static int[] dirY = {-1, 1, 0, 0};
	static int[][] map;
	static boolean[][] visited;
	static int year = 0;
	static int divide = 0;

	private static class Ice {
		int x;
		int y;

		public Ice(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		map = new int[height][width];
		visited = new boolean[height][width];
		clearVisited();

		for (int y = 0; y < height; y++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int x = 0; x < width; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		while (divide < 2 && !isAllMelted()) {
			divide = 0;
			// 빙산들 bfs 처리하기
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					Ice startI = new Ice(x, y);
					if (!visited[y][x] && map[y][x] != 0) {
						divide++;
						queue.add(startI);
						visitIce(startI);
						while (!queue.isEmpty()) {
							Ice curI = queue.poll();
							for (int i = 0; i < 4; i++) {
								// 상하좌우 중 범위 안이고, 방문하지 않았고, 바다가 아니면
								Ice nextI = new Ice(curI.x + dirX[i], curI.y + dirY[i]);
								if (insideMap(nextI)
									&& !visited[nextI.y][nextI.x]
									&& map[nextI.y][nextI.x] != 0) {
									queue.add(nextI);
									visitIce(nextI);
								}
							}
						}
					}
				}
			}
			// year 증가
			afterYear();
			year++;

			clearVisited();
		}

		if (divide >= 2) {
			out.println(year - 1);
		} else {
			out.println(0);
		}
	}

	private static boolean isAllMelted() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (map[y][x] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	private static void visitIce(Ice ice) {
		visited[ice.y][ice.x] = true;
	}

	private static void clearVisited() {
		for (boolean[] line : visited) {
			Arrays.fill(line, false);
		}
	}

	private static int seaAroundIce(Ice ice) {
		int sea = 0;
		for (int i = 0; i < 4; i++) {
			Ice nextI = new Ice(ice.x + dirX[i], ice.y + dirY[i]);
			if (insideMap(nextI) && map[nextI.y][nextI.x] == 0) {
				sea++;
			}
		}
		return sea;
	}

	private static void afterYear() {
		int[][] after = new int[height][width];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (map[y][x] > 0) {
					Ice ice = new Ice(x, y);
					after[ice.y][ice.x] =
						Math.max(map[ice.y][ice.x] - seaAroundIce(ice), 0);
				}
			}
		}
		map = after;
	}

	private static boolean insideMap(Ice nextI) {
		return nextI.x >= 0 && nextI.x < width && nextI.y >= 0 && nextI.y < height;
	}
}
