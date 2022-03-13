package DFSBFS;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BJ10026_적록색약 {
	static String[][] map;
	static String[][] changeMap;
	static int width;
	static int[] dirX = {0, 0, -1, 1}; // 상, 하, 좌, 우
	static int[] dirY = {-1, 1, 0, 0};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		width = Integer.parseInt(br.readLine());
		map = new String[width][width];
		changeMap = new String[width][width];
		for (int i = 0; i < width; i++) {
			map[i] = br.readLine().split("");
			for (int j = 0; j < width; j++) {
				if (map[i][j].equals("G")) {
					changeMap[i][j] = "R";
				} else {
					changeMap[i][j] = map[i][j];
				}
			}
		}
		int standard = sectionOf("R", map) + sectionOf("G", map) + sectionOf("B", map);
		int notStandard = sectionOf("R", changeMap) + sectionOf("B", changeMap);

		out.println(standard + " " + notStandard);
	}

	private static class Pos {
		int x; int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static int sectionOf(String color, String[][] map) {
		int section = 0;
		boolean[][] visited = new boolean[width][width];
		Queue<Pos> queue = new LinkedList<>();
		for (boolean[] line: visited) {
			Arrays.fill(line, false);
		}

		for (int y = 0; y < width; y++) {
			for (int x = 0; x < width; x++) {
				if (map[y][x].equals(color) && !visited[y][x]) {
					section++;
					queue.add(new Pos(x, y));
					while (!queue.isEmpty()) {
						Pos cur = queue.poll();
						for (int i = 0; i < 4; i++) {
							Pos next = new Pos(cur.x + dirX[i], cur.y + dirY[i]);
							if (isQualify(next)
								&& !visited[next.y][next.x]
								&& map[next.y][next.x].equals(color)) {
								queue.add(next);
								visited[next.y][next.x] = true;
							}
						}
					}
				}
			}
		}
		return section;
	}

	private static boolean isQualify(Pos pos) {
		return pos.x >= 0 && pos.x < width && pos.y >= 0 && pos.y < width;
	}
}
