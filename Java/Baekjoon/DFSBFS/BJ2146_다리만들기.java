package DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2146_다리만들기 {
	static FastReader scan = new FastReader();
	static int width;
	static int[][] map;
	static int[][] islandMap;
	static int islandNum;
	static boolean[][] visited;
	static Queue<Pos> queue = new LinkedList<>();
	static int[] dirX = {0, 0, -1, 1};
	static int[] dirY = {-1, 1, 0, 0};
	static Map<Integer, Queue<Pos>> islandIdx = new HashMap<>();
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) {
		input();
		drawIsland();
		getShortestD();
		System.out.println(answer - 1);
	}

	private static void getShortestD() {
		for (int i = 1; i <= islandNum; i++) {
			clearVisit();
			findShortDOfIdx(i);
		}
	}

	private static void findShortDOfIdx(int i) {
		Queue<Pos> idxQ = islandIdx.get(i);
		while (!idxQ.isEmpty()) {
			Pos curP = idxQ.poll();
			visit(curP);
			for (int j = 0; j < 4; j++) {
				Pos nextP = new Pos(curP.x + dirX[j], curP.y + dirY[j], curP.dist + 1);
				if (inRange(nextP) && !isVisited(nextP)) {
					// 바다, 같은 섬, 다른 섬
					if (valueOf(nextP) == 0) {
						idxQ.add(nextP);
						visit(nextP);
					} else if (i != islandMap[nextP.x][nextP.y]) {
						answer = Math.min(answer, nextP.dist);
						return;
					}
				}
			}
		}
	}

	private static void clearVisit() {
		for (int x = 0; x < width; x++) {
			Arrays.fill(visited[x], false);
		}
	}

	private static void drawIsland() {
		int idx = 1;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < width; y++) {
				Pos curP = new Pos(x, y, 0);
				if (!isVisited(curP) && valueOf(curP) == 1) {
					// 방문하지 않은 섬이면
					Queue<Pos> queue = new LinkedList<>();
					Queue<Pos> islandQ = new LinkedList<>();
					queue.add(curP);
					islandQ.add(curP);
					visit(curP);
					islandMap[curP.x][curP.y] = idx;
					while (!queue.isEmpty()) {
						Pos curLand = queue.poll();
						for (int i = 0; i < 4; i++) {
							Pos nextP = new Pos(curLand.x + dirX[i], curLand.y + dirY[i], 0);
							if (inRange(nextP) && !isVisited(nextP) && valueOf(nextP) == 1) {
								queue.add(nextP);
								islandQ.add(nextP);
								visit(nextP);
								islandMap[nextP.x][nextP.y] = idx;
							}
						}
					}
					islandIdx.put(idx, islandQ);
					idx++;
				}
			}
		}
		islandNum = idx - 1;
	}

	private static int valueOf(Pos nextP) {
		return map[nextP.x][nextP.y];
	}

	private static boolean inRange(Pos nextP) {
		return nextP.x >= 0 && nextP.x < width && nextP.y >= 0 && nextP.y < width;
	}

	private static void visit(Pos firstP) {
		visited[firstP.x][firstP.y] = true;
	}

	private static boolean isVisited(Pos cur) {
		return visited[cur.x][cur.y];
	}

	static void input(){
		width = scan.nextInt();
		map = new int[width][width];
		islandMap = new int[width][width];
		visited = new boolean[width][width];
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < width; x++) {
				map[x][y] = Integer.parseInt(scan.next());
				islandMap[x][y] = 0;
				visited[x][y] = false;
			}
		}
	}

	public static class Pos {
		int x, y, dist;

		public Pos(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;
		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine()," ");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
