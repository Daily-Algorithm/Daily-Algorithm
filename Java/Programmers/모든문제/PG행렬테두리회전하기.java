package 모든문제;

import java.util.ArrayList;
import java.util.List;

public class PG행렬테두리회전하기 {
	static int[][] map;
	static int height, width;
	static int[] dirX = {1, 0, -1, 0}; // 우, 하, 좌, 상
	static int[] dirY = {0, 1, 0, -1};
	static List<Integer> answer = new ArrayList<>();
	public static List<Integer> solution(int rows, int columns, int[][] queries) {
		height = rows;
		width = columns;
		makeMap(columns, rows);
		for (int[] query : queries) {
			rotate(query);
		}
		return answer;
	}

	private static class Pos {
		int x, y, dirIdx;
		public Pos(int x, int y, int dirIdx) {
			this.x = x;
			this.y = y;
			this.dirIdx = dirIdx;
		}
	}

	private static void rotate(int[] query) {
		int[][] temp =  clone(map);
		int leftX = query[1] - 1;
		int topY = query[0] - 1;
		int rightX = query[3] - 1;
		int bottomY = query[2] - 1;
		Pos TL = new Pos(leftX, topY, 0);
		Pos TR = new Pos(rightX, topY, 1);
		Pos BR = new Pos(rightX, bottomY, 2);
		Pos BL = new Pos(leftX, bottomY, 3);

		List<Pos> movePL = new ArrayList<>();
		movePL.add(TL);
		movePL.add(TR);
		movePL.add(BR);
		movePL.add(BL);
		for (int x = leftX + 1; x < rightX; x++) {
			Pos topP = new Pos(x, topY, 0);
			Pos bottomP = new Pos(x, bottomY, 2);
			movePL.add(topP);
			movePL.add(bottomP);
		}
		for (int y = topY + 1; y < bottomY; y++) {
			Pos rightP = new Pos(rightX, y, 1);
			Pos leftP = new Pos(leftX, y, 3);
			movePL.add(rightP);
			movePL.add(leftP);
		}

		int min = height * width;
		for (Pos curP : movePL) {
			int nextX = curP.x + dirX[curP.dirIdx];
			int nextY = curP.y + dirY[curP.dirIdx];
			temp[nextX][nextY] = valueOf(curP);
			min = Math.min(min, valueOf(curP));
		}
		map = temp;
		answer.add(min);
	}

	private static int valueOf(Pos curP) {
		return map[curP.x][curP.y];
	}

	private static int[][] clone(int[][] map) {
		int[][] temp = new int[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				temp[x][y] = map[x][y];
			}
		}
		return temp;
	}

	private static void makeMap(int columns, int rows) {
		map = new int[columns][rows];
		for (int x = 0; x < columns; x++) {
			for (int y = 0; y < rows; y++) {
				map[x][y] = columns * y + (x + 1);
			}
		}
	}

	public static void main(String[] args) {
		int rows=6;
		int columns = 6;
		int[][] queries = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};
		System.out.println(solution(rows, columns, queries));
	}
}
