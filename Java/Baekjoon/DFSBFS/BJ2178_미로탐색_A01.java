package DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2178_미로탐색_A01 {
	static int width, height;
	static int[] dirX = {0, 0, -1, 1};
	static int[] dirY = {-1, 1, 0, 0};
	static int[][] map;
	static int[][] dist;
	static boolean[][] visited;
	static LinkedList<Pos> queue;

	static class Pos {
		int xPos;
		int yPos;

		public Pos(int xPos, int yPos) {
			this.xPos = xPos;
			this.yPos = yPos;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());

		map = new int[height + 1][width+ 1];
		dist = new int[height + 1][width + 1];
		visited = new boolean[height + 1][width + 1];
		queue = new LinkedList<>();

		for (int i = 1; i <= height; i++) {
			String str = br.readLine();
			for (int j = 1; j <= width; j++) {
				map[i][j] = str.charAt(j - 1) - '0';
			}
		}

		BFS();
		System.out.println(dist[height][width]);
	}

	private static void BFS() {
		queue.add(new Pos(1, 1));
		dist[1][1] = 1;
		visited[1][1] = true;

		while (!queue.isEmpty()) {
			Pos curP = queue.poll();
			for (int i = 0; i < 4; i++) {
				// 4 = 상하좌우
				Pos nextP = new Pos(curP.xPos + dirX[i], curP.yPos + dirY[i]);
				if (boundaryCheck(nextP) && !visited[nextP.yPos][nextP.xPos]) {
					queue.add(nextP);
					dist[nextP.yPos][nextP.xPos] = dist[curP.yPos][curP.xPos] + 1;
					visited[nextP.yPos][nextP.xPos] = true;
				}
			}
		}
	}

	private static boolean boundaryCheck(Pos nextP) {
		if (nextP.xPos <= 0 || nextP.yPos <= 0 || nextP.xPos > width || nextP.yPos > height) {
			return false;
		} else
			return map[nextP.yPos][nextP.xPos] != 0;
	}
}
