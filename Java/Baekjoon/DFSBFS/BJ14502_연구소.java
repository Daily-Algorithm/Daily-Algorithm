package DFSBFS;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 다른 사람 풀이, BFS를 이용한 백트래킹? 뭐시기라는데 알아봐야 함
 */
public class BJ14502_연구소 {

	static int[][] map;
	static int[][] copyMap;
	static int[][] spreadMap;
	static int width, height;
	static int safeZone;
	static int[] dirX = {0, 0, -1, 1};
	static int[] dirY = {-1, 1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		map = new int[height][width];
		copyMap = new int[height][width];
		spreadMap = new int[height][width];

		for (int i = 0; i < height; i++) {
			int[] split = Arrays.stream(br.readLine().split(" "))
								.mapToInt(Integer::parseInt)
								.toArray();
			map[i] = split;
			copyMap[i] = split;
			spreadMap[i] = split;
		}

		makeWall(0);
		out.println(safeZone);
	}

	private static class Pos {
		int x; int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static void makeWall(int count) {
		if (count == 3) {
			spreadVirus();
			countZero();
			return;
		}
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (copyMap[y][x] == 0) {
					copyMap[y][x] = 1;
					makeWall(count + 1);
					copyMap[y][x] = 0;
				}
			}
		}
	}

	private static void countZero() {
		int count = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (spreadMap[y][x] == 0) {
					count++;
				}
			}
		}
		safeZone = Math.max(count, safeZone);
	}

	private static void spreadVirus() {
		boolean[][] visited = new boolean[height][width];
		Queue<Pos> queue = new LinkedList<>();
		for (int i = 0; i < height; i++) {
			Arrays.fill(visited[i], false);
		}
		spreadMap = deepCopy(copyMap);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (spreadMap[y][x] == 2 && !visited[y][x]) {
					queue.add(new Pos(x, y));
					while (!queue.isEmpty()) {
						Pos cur = queue.poll();
						for (int i = 0; i < 4; i++) {
							Pos next = new Pos(cur.x + dirX[i], cur.y + dirY[i]);
							if (ableToSpread(next) && !visited[next.y][next.x]) {
								queue.add(next);
								visited[next.y][next.x] = true;
								spreadMap[next.y][next.x] = 2;
							}
						}
					}
				}
			}
		}
	}

	private static boolean ableToSpread(Pos pos) {
		return pos.x >= 0 && pos.x < width && pos.y >= 0 && pos.y < height
			&& map[pos.y][pos.x] == 0;
	}

	private static int[][] deepCopy(int[][] map) {
		int[][] temp = new int[height][width];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				temp[y][x] = map[y][x];
			}
		}
		return temp;
	}
}
