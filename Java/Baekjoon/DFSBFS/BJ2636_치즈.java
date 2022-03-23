package DFSBFS;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2636_치즈 {
	static int width, height;
	static int[][] map;
	static Queue<Cheese> cheeseQ = new LinkedList<>();
	static Queue<Cheese> sideAirQ = new LinkedList<>();
	static int[] dirX = {0, 0, -1, 1};
	static int[] dirY = {-1, 1, 0, 0};
	static boolean[][] air;
	private static class Cheese{
		int x, y;
		public Cheese(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		map = new int[width][height];
		air = new boolean[width][height];
		for (boolean[] line : air) {
			Arrays.fill(line, false);
		}
		for (int y = 0; y < height; y++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int x = 0; x < width; x++) {
				map[x][y] = Integer.parseInt(st.nextToken());
				if (map[x][y] == 1) {
					cheeseQ.add(new Cheese(x, y));
				}
			}
		}

		// sideAir BFS로 air set
		sideAir();
		airBFS(sideAirQ);

		int hours = 0;
		while (true) {
			Queue<Cheese> meltedQ = new LinkedList<>();
			Queue<Cheese> leftQ = new LinkedList<>();
			while (!cheeseQ.isEmpty()) {	// 현재 치즈들에 대하여 melt과정
				Cheese curC = cheeseQ.poll();
				boolean ableToMelt = false;
				for (int i = 0; i < 4; i++) {
					Cheese aroundC = new Cheese(curC.x + dirX[i], curC.y + dirY[i]);
					if (air[aroundC.x][aroundC.y]) {
						meltedQ.add(curC);	// air가 되는 Cheese
						ableToMelt = true;
						break;
					}
				}
				if (!ableToMelt) {
					leftQ.add(curC);	// 다음 cheeseQ가 되는 Cheese
				}
			}
			hours++;
			if (leftQ.isEmpty()) {	// 남은 치즈가 없으면 return
				out.println(hours);
				out.println(meltedQ.size());
				break;
			} else {	// 남은 치즈가 있으면
				cheeseQ = leftQ;
				airBFS(meltedQ);
			}
		}
	}

	private static void airBFS(Queue<Cheese> queue) {
		while (!queue.isEmpty()) {
			Cheese curA = queue.poll();
			air[curA.x][curA.y] = true;
			for (int i = 0; i < 4; i++) {
				Cheese nextA = new Cheese(curA.x + dirX[i], curA.y + dirY[i]);
				if (inRange(nextA) && map[nextA.x][nextA.y] == 0 && !air[nextA.x][nextA.y]) {
					air[nextA.x][nextA.y] = true;
					queue.add(nextA);
				}
			}
		}
	}

	private static void sideAir() {
		int[] xs = {0, width - 1};
		int[] ys = {0, height - 1};
		for (int x = 0; x < width; x++) {
			for (int i = 0; i < 2; i++) {
				Cheese pos = new Cheese(x, ys[i]);
				if (map[pos.x][pos.y] == 0) {sideAirQ.add(pos);}
			}
		}
		for (int y = 0; y < height; y++) {
			for (int i = 0; i < 2; i++) {
				Cheese pos = new Cheese(xs[i], y);
				if (map[pos.x][pos.y] == 0) {sideAirQ.add(pos);}
			}
		}
	}

	private static boolean inRange(Cheese cheese) {
		return cheese.x >= 0 && cheese.x < width && cheese.y >= 0 && cheese.y < height;
	}
}
