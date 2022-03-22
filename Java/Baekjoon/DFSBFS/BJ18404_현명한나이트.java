package DFSBFS;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ18404_현명한나이트 {
	static int[] dirX = {1, 2, 1, 2, -1, -2, -1, -2};
	static int[] dirY = {-2, -1, 2, 1, -2, -1, 2, 1};
	static int width;
	static int[][] enemies;
	static int[][] dist;
	static Knight startK;
	static boolean[][] visited;
	private static class Knight {
		int x,y,dist;

		public Knight(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		width = Integer.parseInt(st.nextToken());
		dist = new int[width][width];
		visited = new boolean[width][width];
		for (boolean[] line: visited) {
			Arrays.fill(line, false);
		}
		enemies = new int[Integer.parseInt(st.nextToken())][2];
		st = new StringTokenizer(br.readLine(), " ");
		startK = new Knight(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, 0);
		for (int i = 0; i < enemies.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			enemies[i] = new int[]{Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1};
		}
		BFS();

		StringBuilder sb = new StringBuilder("");
		for (int[] enemy : enemies) {
			sb.append(dist[enemy[0]][enemy[1]]);
			sb.append(" ");
		}
		out.println(sb.toString());
	}

	private static void BFS() {
		Queue<Knight> queue = new LinkedList<>();
		queue.add(startK);
		dist[startK.x][startK.y] = 0;
		visited[startK.x][startK.y] = true;
		while (!queue.isEmpty()) {
			Knight curK = queue.poll();
			for (int i = 0; i < 8; i++) {
				Knight nextK = new Knight(curK.x + dirX[i], curK.y + dirY[i], curK.dist + 1);
				if (inRange(nextK) && !visited[nextK.x][nextK.y]) {
					queue.add(nextK);
					dist[nextK.x][nextK.y] = nextK.dist;
					visited[nextK.x][nextK.y] = true;
				}
			}
		}
	}

	private static boolean reached(int[] enemy, Knight curK) {
		return enemy[0] == curK.x && enemy[1] == curK.y;
	}

	private static boolean inRange(Knight nextK) {
		return nextK.x >= 0 && nextK.x < width && nextK.y >= 0 && nextK.y < width;
	}

}
