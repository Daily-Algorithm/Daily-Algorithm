package DFSBFS;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ14503_로봇청소기 {
	static int width, height, startX, startY, startDir;
	static int[][] map;
	static Queue<Robot> queue = new LinkedList<>();
	static Map<Integer, Integer[]> posMap = new HashMap<>();
	static Map<Integer, Integer[]> backPosMap = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		startY = Integer.parseInt(st.nextToken());
		startX = Integer.parseInt(st.nextToken());
		startDir = Integer.parseInt(st.nextToken());
		map = new int[height][width];

		posMap.put(0, new Integer[]{-1, 0}); // 북일 때 x--가 next
		posMap.put(1, new Integer[]{0, -1}); // 동일 때 y--가 next
		posMap.put(2, new Integer[]{1, 0}); // 남일 때 x++가 next
		posMap.put(3, new Integer[]{0, 1}); // 서일 때 y++가 next

		backPosMap.put(0, new Integer[]{0, 1}); // 북일 때 y++가 next
		backPosMap.put(1, new Integer[]{-1, 0}); // 동일 때 x--가 next
		backPosMap.put(2, new Integer[]{0, -1}); // 남일 때 y--가 next
		backPosMap.put(3, new Integer[]{1, 0}); // 서일 때 x++가 next

		for (int y = 0; y < height; y++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int x = 0; x < width; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		Robot startR = new Robot(startX, startY, startDir);
		queue.add(startR);
		clean(startR);
		int count = 1;
		while (!queue.isEmpty()) {
			Robot curR = queue.poll();
			for (int i = 0; i < 4; i++) {
				Robot nextR = new Robot(
					curR.x + posMap.get(curR.dir)[0],
					curR.y + posMap.get(curR.dir)[1],
					(curR.dir + 3) % 4
				);
				if (qualifiedR(nextR) && valueOfR(nextR) == 0) {
					// nextR이 범위안에 있고, 청소돼있지 않으면 청소
					queue.add(nextR);
					clean(nextR);
					count++;
					break;
				} else {
					// 왼쪽으로 방향 틀기
					curR.dir = (curR.dir + 3) % 4;
					if (i == 3) {
						// 사방이 벽이거나 청소돼있고, 뒤가 벽이 아니면 후진
						Robot backR = new Robot(
							curR.x + backPosMap.get(curR.dir)[0],
							curR.y + backPosMap.get(curR.dir)[1],
							curR.dir
						);
						if (qualifiedR(backR) && valueOfR(backR) != 1) {
							queue.add(backR);
						}
					}
				}
			}
		}
		out.println(count);
	}

	private static void clean(Robot robot) {
		map[robot.y][robot.x] = 2;
	}

	private static int valueOfR(Robot robot) {
		return map[robot.y][robot.x];
	}

	private static boolean qualifiedR(Robot robot) {
		return robot.x >= 0 && robot.x < width && robot.y >= 0 && robot.y < height;
	}

	private static class Robot {
		int x;
		int y;
		int dir;

		public Robot(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}
