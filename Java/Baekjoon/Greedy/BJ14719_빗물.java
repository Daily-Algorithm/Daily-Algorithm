package Greedy;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ14719_빗물 {
	static int[][] map;
	static int width, height;

	private static class Pos {
		int x, y;

		public Pos(int x, int y) {
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
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < width; i++) {
			int blocks = Integer.parseInt(st.nextToken());
			Arrays.fill(map[i], 0);
			for (int y = height - 1; y >= height - blocks; y--) {
				map[i][y] = 1;
			}
		}
		out.println(rain());
	}

	private static int rain() {
		int rain = 0;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Pos curP = new Pos(x, y);
				if (valueOf(curP) == 0 && leftIsBlocked(curP) && rightIsBlocked(curP)) {
					rain++;
				}
			}
		}
		return rain;
	}

	private static int valueOf(Pos curP) {
		return map[curP.x][curP.y];
	}

	private static boolean rightIsBlocked(Pos curP) {
		int curX = curP.x;
		int curY = curP.y;
		for (int x = curX + 1; x < width; x++) {
			if (map[x][curY] == 1) {
				return true;
			}
		}
		return false;
	}

	private static boolean leftIsBlocked(Pos curP) {
		int curX = curP.x;
		int curY = curP.y;
		for (int x = curX - 1; x >= 0; x--) {
			if (map[x][curY] == 1) {
				return true;
			}
		}
		return false;
	}
}
