package DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 섬 여러개 있을 때 1,2섬의 최소값이 최소가 아닐 때가 오류나는 듯
 */
public class BJ2146_다리만들기_F01 {
	static FastReader scan = new FastReader();
	static int width;
	static int[][] map;
	static boolean[][] visited;
	static Queue<Pos> queue = new LinkedList<>();
	static int[] dirX = {0, 0, -1, 1};
	static int[] dirY = {-1, 1, 0, 0};
	static int answer = 0;

	public static void main(String[] args) {
		input();
		initialQ();
		findShortest();
		System.out.println(answer - 1);
	}

	private static void findShortest() {
		while (!queue.isEmpty()) {
			Pos curP = queue.poll();
			for (int i = 0; i < 4; i++) {
				Pos nextP = new Pos(curP.x + dirX[i], curP.y + dirY[i], curP.dist + 1);
				if (inRange(nextP) && !isVisited(nextP)) {
					if (valueOf(nextP) == 0) {
						queue.add(nextP);
						visit(nextP);
					} else {
						answer = nextP.dist;
						return;
					}
				}
			}
		}
	}

	private static void initialQ() {
		Queue<Pos> firstIsland = new LinkedList<>();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < width; y++) {
				if (map[x][y] == 1) {
					Pos firstP = new Pos(x, y, 0);
					firstIsland.add(firstP);
					queue.add(firstP);
					visit(firstP);
					while (!firstIsland.isEmpty()) {
						Pos curP = firstIsland.poll();
						for (int i = 0; i < 4; i++) {
							Pos nextP = new Pos(curP.x + dirX[i], curP.y + dirY[i], 0);
							if (inRange(nextP) && valueOf(nextP) == 1 && !isVisited(nextP)) {
								firstIsland.add(nextP);
								queue.add(nextP);
								visit(nextP);
							}
						}
					}
					return;
				}
			}
		}
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
		visited = new boolean[width][width];
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < width; x++) {
				map[x][y] = Integer.parseInt(scan.next());
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
