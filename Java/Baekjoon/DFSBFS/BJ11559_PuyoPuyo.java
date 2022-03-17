package DFSBFS;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BJ11559_PuyoPuyo {
	static int[][] map = new int[6][12];	// R:1, G:2, B:3, P:4, Y:5
	static Map<String, Integer> toInteger = new HashMap<>();
	static boolean[][] visited = new boolean[6][12];
	static int[] dirX = {0, 0, -1, 1};
	static int[] dirY = {-1, 1, 0, 0};
	static int pop = 0;
	public static void main(String[] args) throws IOException {
		toInteger.put(".", 0);toInteger.put("R", 1);toInteger.put("G", 2);
		toInteger.put("B", 3);toInteger.put("P", 4);toInteger.put("Y", 5);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		for (int y = 0; y < 12; y++) {
			String[] split = br.readLine().split("");
			for (int x = 0; x < 6; x++) {
				map[x][y] = toInteger.get(split[x]);
			}
		}

		boolean ableToPop = true;
		while (ableToPop) {
			clearVisited();
			int count = 0;
			for (int color = 1; color <= 5; color++) {
				for (int y = 0; y < 12; y++) {
					for (int x = 0; x < 6; x++) {
						Queue<Puyo> queue = new LinkedList<>();
						Puyo startP = new Puyo(x, y);
						if (valueOf(startP) == color && !visited[startP.x][startP.y]) {
							List<Puyo> popable = new ArrayList<>();
							queue.add(startP);
							popable.add(startP);
							visited[startP.x][startP.y] = true;
							while (!queue.isEmpty()) {
								Puyo curP = queue.poll();
								for (int dirIdx = 0; dirIdx < 4; dirIdx++) {
									Puyo nextP = new Puyo(curP.x + dirX[dirIdx], curP.y + dirY[dirIdx]);
									if (inRange(nextP) && !visited[nextP.x][nextP.y]
										&& valueOf(nextP) == color) {
										queue.add(nextP);
										popable.add(nextP);
										visited[nextP.x][nextP.y] = true;
									}
								}
							}
							if (popable.size() >= 4) {
								pop(popable);
								count++;
							}
						}
					}
				}
				if (color == 5) {
					if (count == 0) {
						ableToPop = false;
					} else {
						pop++;
					}
				}
			}
			gravity();
		}
		out.println(pop);
	}

	private static void clearVisited() {
		for (int i = 0; i < 6; i++) {
			Arrays.fill(visited[i], false);
		}
	}
	private static void pop(List<Puyo> popable) {
		for (Puyo puyo : popable) {
		    map[puyo.x][puyo.y] = 0;
		}
	}

	private static void gravity() {
		for (int x = 0; x < 6; x++) {
			int[] row = map[x];
			int[] temp = new int[12];
			Arrays.fill(temp, 0);
			int fillIdx = 11;
			for (int i = 11; i >= 0; i--) {
				if (row[i] > 0) {
					temp[fillIdx] = row[i];
					fillIdx--;
				}
			}
			map[x] = temp;
		}
	}

	private static int valueOf(Puyo puyo) {
		return map[puyo.x][puyo.y];
	}

	private static class Puyo {
		int x, y;

		public Puyo(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static boolean inRange(Puyo puyo) {
		return puyo.x >= 0 && puyo.x < 6 && puyo.y >= 0 && puyo.y < 12;
	}
}
