package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14499_주사위굴리기 {

	static FastReader scan = new FastReader();
	static int N, M, startX, startY, K;
	static int[][] map;
	static int[] orderArr;

	public static void main(String[] args) {
		input();
		doOrders();
	}

	private static void doOrders() {
		Dice dice = new Dice(new int[6], startX, startY);
		for (int order : orderArr) {
			switch (order) {
				case 1:
					// right
					if (inRange(dice.x + 1, dice.y)) {
						dice.moveRight();
						dice.compareMap();
						System.out.println(dice.arr[0]);
					}
					break;
				case 2:
					// left
					if (inRange(dice.x - 1, dice.y)) {
						dice.moveLeft();
						dice.compareMap();
						System.out.println(dice.arr[0]);
					}
					break;
				case 3:
					// top
					if (inRange(dice.x, dice.y - 1)) {
						dice.moveTop();
						dice.compareMap();
						System.out.println(dice.arr[0]);
					}
					break;
				case 4:
					// down
					if (inRange(dice.x, dice.y + 1)) {
						dice.moveDown();
						dice.compareMap();
						System.out.println(dice.arr[0]);
					}
					break;
			}
		}
	}

	private static boolean inRange(int x, int y) {
		return x >= 0 && x < M && y >= 0 && y < N;
	}

	static void input() {
		N = scan.nextInt();
		M = scan.nextInt();
		startY = scan.nextInt();
		startX = scan.nextInt();
		K = scan.nextInt();

		map = new int[M][N];
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				map[x][y] = scan.nextInt();
			}
		}

		orderArr = new int[K];
		for (int i = 0; i < K; i++) {
			orderArr[i] = scan.nextInt();
		}
	}

	static class Dice {
		int[] arr;
		int x, y;

		public Dice(int[] arr, int x, int y) {
			this.arr = arr;
			this.x = x;
			this.y = y;
		}

		public void moveRight() {
			int[] copy = this.arr.clone();
			int[] temp = this.arr.clone();
			temp[0] = copy[3];
			temp[2] = copy[0];
			temp[3] = copy[5];
			temp[5] = copy[2];
			this.arr = temp;
			this.x++;
		}
		public void moveLeft() {
			int[] copy = this.arr.clone();
			int[] temp = this.arr.clone();
			temp[0] = copy[2];
			temp[2] = copy[5];
			temp[3] = copy[0];
			temp[5] = copy[3];
			this.arr = temp;
			this.x--;
		}
		public void moveTop() {
			int[] copy = this.arr.clone();
			int[] temp = this.arr.clone();
			temp[0] = copy[4];
			temp[4] = copy[5];
			temp[1] = copy[0];
			temp[5] = copy[1];
			this.arr = temp;
			this.y--;
		}
		public void moveDown() {
			int[] copy = this.arr.clone();
			int[] temp = this.arr.clone();
			temp[0] = copy[1];
			temp[1] = copy[5];
			temp[4] = copy[0];
			temp[5] = copy[4];
			this.arr = temp;
			this.y++;
		}

		public void compareMap() {
			if (map[this.x][this.y] == 0) {
				map[this.x][this.y] = this.arr[5];
			} else {
				this.arr[5] = map[this.x][this.y];
				map[this.x][this.y] = 0;
			}
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
