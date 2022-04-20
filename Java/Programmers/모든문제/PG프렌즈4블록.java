package 모든문제;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PG프렌즈4블록 {
	static String[][] map;
	static boolean[][] visited;
	static int height, width;
	static int erase = 0;
	public static int solution(int m, int n, String[] board) {
		height = m;
		width = n;
		visited = new boolean[width][height];
		for (boolean[] line : visited) {
			Arrays.fill(line, false);
		}
		makeMap(board);
		process();
		return erase;
	}

	private static void process() {
		int popped;
		while ((popped = findPoppedBs(popBlocks())) > 0) {
			erase += popped;
			gravity();
		}
	}

	private static void printMap() {
		for (int y = 0; y < height; y++) {
			StringBuilder sb = new StringBuilder();
			for (int x = 0; x < width; x++) {
				sb.append(map[x][y] + " ");
			}
			System.out.println(sb.toString());
		}
		System.out.println("----------------");
	}

	private static int findPoppedBs(boolean[][] popBlocks) {
		int cnt = 0;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (popBlocks[x][y]) {
					map[x][y] = "-";
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static void gravity() {
		String[][] temp = new String[width][height];

		for (int x = 0; x < width; x++) {
			String poppedStr = String.join("", map[x]).replaceAll("-", "");
			StringBuilder absolute = new StringBuilder("-".repeat(height - poppedStr.length()));
			temp[x] = absolute.append(poppedStr).toString().split("");
		}
		map = temp;
	}

	private static boolean[][] popBlocks() {
		int[] dirX = {1, 0, 1};
		int[] dirY = {0, 1, 1};
		boolean[][] visited = new boolean[width][height];
		for (boolean[] line : visited) {
			Arrays.fill(line, false);
		}
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				List<Block> fourBs = new ArrayList<>();
				Block curP = new Block(x, y);
				fourBs.add(curP);
				boolean fourB = true;
				for (int i = 0; i < 3; i++) {
					Block nextP = new Block(curP.x + dirX[i], curP.y + dirY[i]);
					fourBs.add(nextP);
					if (!inRange(nextP) || !same(curP, nextP)) {
						fourB = false;
						break;
					}
				}
				if (fourB) {
					fourBs.forEach(block -> visited[block.x][block.y] = true);
				}
			}
		}
		return visited;
	}

	private static boolean same(Block curP, Block nextP) {
		return !map[curP.x][curP.y].equals("-") &&
			map[curP.x][curP.y].equals(map[nextP.x][nextP.y]);
	}

	private static boolean inRange(Block nextP) {
		return nextP.x >= 0 && nextP.x < width && nextP.y >= 0 && nextP.y < height;
	}

	private static void makeMap(String[] board) {
		map = new String[width][height];
		for (int y = 0; y < height; y++) {
			String[] split = board[y].split("");
			for (int x = 0; x < width; x++) {
				map[x][y] = split[x];
			}
		}
	}

	public static void main(String[] args) {
		int m = 4;
//		int m = 6;
		int n = 5;
//		int n = 6;
		String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
//		String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
		System.out.println(solution(m, n, board));
	}

	private static class Block {
		int x, y;
		public Block(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
