package DFSBFS;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2468_안전영역 {
	static int width;
	static int[][] map;
	static boolean[][] sunkMap;
	static List<Integer> areasPerRain = new ArrayList<>();
	static int minRain = 100;
	static int maxRain = 1;
	static int[] dirX = {0, 0, -1, 1}; // 상하좌우
	static int[] dirY = {-1, 1, 0, 0}; // 상하좌우
	static Queue<Pos> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		width = Integer.parseInt(br.readLine());
		map = new int[width][width];
		sunkMap = new boolean[width][width];
		for (int y = 0; y < width; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int x = 0; x < width; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				minRain = Math.min(map[y][x], minRain);
				maxRain = Math.max(map[y][x], maxRain);
			}
		}

		areasPerRain.add(1);
		// rain 이하가 물에 잠겼을 때의 안전지대 수를 areasPerRain에 add
		for (int rain = minRain; rain < maxRain; rain++) {
			clean();
			sink(rain);
			// 안전지대 수 찾기
			int count = 0;
			for (int y = 0; y < width; y++) {
				for (int x = 0; x < width; x++) {
					Pos startP = new Pos(x, y);
					if (!isSunk(startP)) {
						count++;
						queue.add(startP);
						visit(startP);
						while (!queue.isEmpty()) {
							Pos curP = queue.poll();
							for (int i = 0; i < 4; i++) {
								Pos nextP = new Pos(curP.x + dirX[i], curP.y + dirY[i]);
								if (qualified(nextP) && !isSunk(nextP)) {
									queue.add(nextP);
									visit(nextP);
								}
							}
						}
					}
				}
			}
			areasPerRain.add(count);
		}

		Collections.sort(areasPerRain, Collections.reverseOrder());
		out.println(areasPerRain.get(0));
	}

	private static boolean qualified(Pos nextP) {
		return nextP.x >= 0 && nextP.x < width && nextP.y >= 0 && nextP.y < width;
	}

	private static class Pos{
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	private static void sink(int rain) {
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < width; x++) {
				if (map[y][x] <= rain) {
					sunkMap[y][x] = true;
				}
			}
		}
	}

	private static boolean isSunk(Pos pos) {
		return sunkMap[pos.y][pos.x];
	}

	private static void visit(Pos pos) {
		sunkMap[pos.y][pos.x] = true;
	}

	private static void clean() {
		for (boolean[] line: sunkMap) {
			Arrays.fill(line, false);
		}
	}
}
