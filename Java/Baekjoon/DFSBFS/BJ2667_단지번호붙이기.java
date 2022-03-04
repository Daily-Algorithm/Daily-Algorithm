package DFSBFS;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BJ2667_단지번호붙이기 {
	static int WIDTH;
	static int[][] map;
	static LinkedList<Integer>[] adj;
	static int[] dirX = {0, 0, -1, 1}; // 상하좌우
	static int[] dirY = {-1, 1, 0, 0}; // 상하좌우
	static Queue<House> queue = new LinkedList<>();
	static boolean[][] visited;
	static List<Integer> answer = new ArrayList<>();

	static class House {
		int x;
		int y;
		public House(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		WIDTH = Integer.parseInt(br.readLine());
		map = new int[WIDTH][WIDTH];
		// map 만들기
		for (int i = 0; i < WIDTH; i++) {
			String line = br.readLine();
			for (int j = 0; j < WIDTH; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		BFS();

		out.println(answer.size());
		Collections.sort(answer);
		for (Integer houses : answer) {
			out.println(houses);
		}
	}

	private static void BFS() {
		for (int y = 0; y < WIDTH; y++) {
			for (int x = 0; x < WIDTH; x++) {
				if (map[y][x] == 1) {
					queue.add(new House(x, y));
					map[y][x] = 0;
					int houses = 0;
					while (!queue.isEmpty()) {
						House curH = queue.poll();
						houses++;
						for (int i = 0; i < 4; i++) {
							House nextH = new House(curH.x + dirX[i], curH.y + dirY[i]);
							if (isQualified(nextH)) {
								queue.add(nextH);
								map[nextH.y][nextH.x] = 0;
							}
						}
					}

					answer.add(houses);
				}
			}
		}

	}

	private static boolean isQualified(House nextH) {
		return nextH.x >= 0 && nextH.x < WIDTH && nextH.y >= 0 && nextH.y < WIDTH
			&& valueOfH(nextH) == 1;
	}

	private static int valueOfH(House nextH) {
		return map[nextH.y][nextH.x];
	}
}
