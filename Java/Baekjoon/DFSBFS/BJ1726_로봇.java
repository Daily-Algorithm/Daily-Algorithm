package DFSBFS;

import static java.lang.System.*;

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
 * 왜 turn이랑 go를 따로 봐야하고 go가 먼저, turn이 다음이지? 아리까리하네
 */
public class BJ1726_로봇 {
	static int width, height;
	static int[][] map;
	static boolean[][][] visited;
	static Queue<Robot> queue = new LinkedList<>();
	static Robot startR, endR;
	static int[] dirX = {1, 0, -1, 0};    // 우, 하, 좌, 상
	static int[] dirY = {0, 1, 0, -1};
	static Map<String, Integer> correctDir = new HashMap<>();
	public static void main(String[] args) throws IOException {
		correctDir.put("1", 0);
		correctDir.put("2", 2);
		correctDir.put("3", 1);
		correctDir.put("4", 3);

		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		map = new int[width][height];
		visited = new boolean[width][height][4];
		for (int y = 0; y < height; y++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int x = 0; x < width; x++) {
				map[x][y] = Integer.parseInt(st.nextToken());
				Arrays.fill(visited[x][y], false);
			}
		}
		String[] start = br.readLine().split(" ");
		String[] end = br.readLine().split(" ");

		startR = new Robot(
			Integer.parseInt(start[1]) - 1,
			Integer.parseInt(start[0]) - 1,
			correctDir.get(start[2]), 0
		);
		endR = new Robot(
			Integer.parseInt(end[1]) - 1,
			Integer.parseInt(end[0]) - 1,
			correctDir.get(end[2]), 0);

		queue.add(startR);
		visit(startR);
		while (!queue.isEmpty()) {
			Robot curR = queue.poll();
			if (isEndRobot(curR)) {
				out.println(curR.count);
				break;
			}

			for (int dist = 1; dist < 4; dist++) {
				Robot nextR = new Robot(
					curR.x + dirX[curR.dir] * dist,
					curR.y + dirY[curR.dir] * dist,
					curR.dir,
					curR.count+ 1
				);
				if (inRange(nextR) && !isVisited(nextR) && notBlocked(curR, nextR)) {
					queue.add(nextR);
					visit(nextR);
				}
			}

			for (int dir = 1; dir < 4; dir++) {
				int turnNum = dir == 3 ? 1 : dir;
				Robot nextR = new Robot(curR.x, curR.y, (curR.dir + dir) % 4, curR.count + turnNum);
				if (!isVisited(nextR)) {
					visit(nextR);
					queue.add(nextR);
				}
			}
		}
	}

	private static boolean isVisited(Robot robot) {
		return visited[robot.x][robot.y][robot.dir];
	}

	private static boolean notBlocked(Robot curR, Robot nextR) {
		int leftX = Math.min(curR.x, nextR.x);
		int rightX = Math.max(curR.x, nextR.x);
		int leftY = Math.min(curR.y, nextR.y);
		int rightY = Math.max(curR.y, nextR.y);
		for (int y = leftY; y <= rightY; y++) {
			for (int x = leftX; x <= rightX; x++) {
				if (map[x][y] == 1) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isEndRobot(Robot robot) {
		return robot.x == endR.x && robot.y == endR.y && robot.dir == endR.dir;
	}

	private static boolean inRange(Robot nextR) {
		return nextR.x >= 0 && nextR.x < width
			&& nextR.y >= 0 && nextR.y < height
			&& map[nextR.x][nextR.y] != 1;
	}

	private static void visit(Robot robot) {
		visited[robot.x][robot.y][robot.dir] = true;
	}

	private static class Robot {
		int x;
		int y;
		int dir;
		int count;

		public Robot(int x, int y, int dir, int count) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.count = count;
		}
	}
}