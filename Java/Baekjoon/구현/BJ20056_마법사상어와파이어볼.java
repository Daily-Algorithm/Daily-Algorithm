package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ20056_마법사상어와파이어볼 {

	static FastReader scan = new FastReader();
	static int width, fireball_num, order_num;
	static int[] dirX = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] dirY = {-1, -1, 0, 1, 1, 1, 0, -1};
	static Queue<FB>[][] map;
	static int answer = 0;

	public static void main(String[] args) {
		input();
		for (int i = 0; i < order_num; i++) {
			doOrder();
			merge();
		}
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < width; y++) {
				if (!map[x][y].isEmpty()) {
					map[x][y].forEach(fb -> answer += fb.mass);
				}
			}
		}
		System.out.println(answer);
	}

	private static void merge() {
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < width; x++) {
				if (map[y][x].size() > 1) {
					int firstDir = map[y][x].peek().dir % 2;
					boolean sameDir = true;
					int mass = 0;
					int speed = 0;
					int balls = 0;
					while (!map[y][x].isEmpty()) {
						FB curB = map[y][x].poll();
						if (firstDir != curB.dir % 2) {
							sameDir = false;
						}
						mass += curB.mass;
						speed += curB.speed;
						balls++;
					}
					mass = mass / 5;
					speed = speed / balls;
					if (mass != 0) {
						if (sameDir) {
							for (int i = 0; i <= 6; i += 2) {
								map[y][x].add(new FB(x, y, mass, i, speed));
							}
						} else {
							for (int i = 1; i <= 7; i += 2) {
								map[y][x].add(new FB(x, y, mass, i, speed));
							}
						}
					}
				}
			}
		}
	}

	private static void doOrder() {
		Queue<FB>[][] after = new Queue[width][width];
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < width; x++) {
				after[y][x] = new LinkedList<>();
			}
		}
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < width; x++) {
				while (!map[y][x].isEmpty()) {
					FB before = map[y][x].poll();
					FB moved = new FB(
						calLocate(before.x, dirX, before.dir, before.speed),
						calLocate(before.y, dirY, before.dir, before.speed),
						before.mass,
						before.dir,
						before.speed
					);
					after[moved.y][moved.x].add(moved);
				}
			}
		}

		map = after;
	}

	private static int calLocate(int x, int[] dirX, int dir, int speed) {
		return (x + 1000 + dirX[dir] * speed) % width;
	}

	static void input() {
		width = scan.nextInt();
		fireball_num = scan.nextInt();
		order_num = scan.nextInt();
		map = new Queue[width][width];
		for (int y = 0; y < width; y++) {
			Arrays.fill(map[y], new LinkedList<>());
		}

		for (int i = 0; i < fireball_num; i++) {
			int y = scan.nextInt() - 1;
			int x = scan.nextInt() - 1;
			int mass = scan.nextInt();
			int speed = scan.nextInt();
			int dir = scan.nextInt();
			map[y][x].add(new FB(x, y, mass, dir, speed));
		}
	}

	private static class FB {
		int x, y, mass, speed, dir;
		public FB(int x, int y, int mass, int dir, int speed) {
			this.x = x;
			this.y = y;
			this.mass = mass;
			this.speed = speed;
			this.dir = dir;
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
					st = new StringTokenizer(br.readLine());
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
