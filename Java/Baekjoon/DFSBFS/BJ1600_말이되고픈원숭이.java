package DFSBFS;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * point : visited에 x, y 말고도 horseMove가 있어야 함
 */
public class BJ1600_말이되고픈원숭이 {
	static int horseMove, width, height;
	static int[][] map;
	static boolean[][][] visited;
	static Monkey startM;
	static int endMX, endMY;
	static Queue<Monkey> queue = new LinkedList<>();
	static int answer = -1;
	static int[] dirX = {0, 0, -1, 1, 1, 2, 1, 2, -1, -2, -1, -2};
	static int[] dirY = {-1, 1, 0, 0, -2, -1, 2, 1, -2, -1, 2, 1};

	private static class Monkey {
		int x,y,dist,HM;

		public Monkey(int x, int y, int dist, int HM) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.HM = HM;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		horseMove = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());
		map = new int[width][height];
		visited = new boolean[width][height][horseMove + 1];
		startM = new Monkey(0, 0, 0, 0);
		endMX = width - 1; endMY = height - 1;
		for (int y = 0; y < height; y++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int x = 0; x < width; x++) {
				map[x][y] = Integer.parseInt(st.nextToken());
			}
		}

		queue.add(startM);
		visit(startM);
		while (!queue.isEmpty()) {
			Monkey curM = queue.poll();
			if (isEndMonkey(curM)) {
				answer = curM.dist;
				break;
			}
			Monkey nextM = null;
			if (curM.HM != horseMove) {
				for (int dirIdx = 0; dirIdx < 12; dirIdx++) {
					// 0~4 : monkey, 5~11 : horse
					nextM =
						dirIdx >= 4 ? new Monkey(
							curM.x + dirX[dirIdx], curM.y + dirY[dirIdx], curM.dist + 1,
							curM.HM + 1
						)
							: new Monkey(
								curM.x + dirX[dirIdx], curM.y + dirY[dirIdx], curM.dist + 1,
								curM.HM
							);
					if (inRange(nextM) && !visited[nextM.x][nextM.y][nextM.HM]
						&& !isObstacle(nextM)) {
						queue.add(nextM);
						visit(nextM);
					}
				}
			} else {
				for (int dirIdx = 0; dirIdx < 4; dirIdx++) {
					nextM = new Monkey(
						curM.x + dirX[dirIdx], curM.y + dirY[dirIdx], curM.dist + 1, curM.HM);
					if (inRange(nextM) && !visited[nextM.x][nextM.y][nextM.HM]
						&& !isObstacle(nextM)) {
						queue.add(nextM);
						visit(nextM);
					}
				}
			}
		}
		out.println(answer);

	}

	private static boolean isObstacle(Monkey monkey) {
		return map[monkey.x][monkey.y] == 1;
	}

	private static boolean isEndMonkey(Monkey monkey) {
		return monkey.x == endMX && monkey.y == endMY;
	}
	private static boolean inRange(Monkey monkey) {
		return monkey.x >= 0 && monkey.x < width && monkey.y >= 0 && monkey.y < height;
	}
	private static void visit(Monkey monkey) {
		visited[monkey.x][monkey.y][monkey.HM] = true;
	}
}
