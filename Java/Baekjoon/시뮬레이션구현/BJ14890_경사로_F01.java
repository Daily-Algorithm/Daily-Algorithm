package 구현;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ14890_경사로_F01 {
	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();
	static int slopeL, width;
	static int[][] yMap;
	static int[][] xMap;
	static int ables = 0;
	public static void main(String[] args) {
		input();
		calAbleOfRow();
		calAbleOfColumn();
		System.out.println(ables);
	}

	private static void calAbleOfColumn() {
		// x축 세기
		for (int x = 0; x < width; x++) {
			int[] column = xMap[x];
			boolean possible = true;
			List<Integer> lowBs = new ArrayList<>();
			for (int idx = 1; idx < width; idx++) {
				int curH = column[idx];
				int beforeH = column[idx - 1];
				if (Math.abs(beforeH - curH) > 1) {
					possible = false;
					break;
				} else if (Math.abs(beforeH - curH) == 1) {
					if (beforeH < curH) { // 오른쪽 직각 삼각형
						int left = idx - 1;
						if (left >= slopeL) {
							lowBs.add(idx - 1);
						} else {
							possible = false;
							break;
						}
					} else { // 왼쪽 직각 삼각형
						int left = width - idx - 1;
						if (left >= slopeL) {
							lowBs.add(idx);
						} else {
							possible = false;
							break;
						}
					}
				}
			}

			if (possible) {
				if (!lowBs.isEmpty()) {
					for (int idx = 1; idx < lowBs.size(); idx++) {
						Integer beforeIdx = lowBs.get(idx - 1);
						Integer curIdx = lowBs.get(idx);
						if (curIdx - beforeIdx + 1 < 2 * slopeL) {
							possible = false;
							break;
						}
					}
				}
			}

			if (possible) {
				System.out.println("Able At Column " + x);
				ables++;
			}
		}
	}

	private static void calAbleOfRow() {
		// y축 세기
		for (int y = 0; y < width; y++) {
			int[] row = yMap[y];
			boolean possible = true;
			List<Integer> lowBs = new ArrayList<>();
			for (int idx = 1; idx < width; idx++) {
				int curH = row[idx];
				int beforeH = row[idx - 1];
				if (Math.abs(beforeH - curH) > 1) {
					possible = false;
					break;
				} else if (Math.abs(beforeH - curH) == 1) {
					if (beforeH < curH) { // 오른쪽 직각 삼각형
						int left = idx - 1;
						if (left >= slopeL) {
							lowBs.add(idx - 1);
						} else {
							possible = false;
							break;
						}
					} else { // 왼쪽 직각 삼각형
						int left = width - idx - 1;
						if (left >= slopeL) {
							lowBs.add(idx);
						} else {
							possible = false;
							break;
						}
					}
				}
			}

			if (possible) {
				if (!lowBs.isEmpty()) {
					for (int idx = 1; idx < lowBs.size(); idx++) {
						Integer beforeIdx = lowBs.get(idx - 1);
						Integer curIdx = lowBs.get(idx);
						if (curIdx - beforeIdx + 1 < 2 * slopeL) {
							possible = false;
							break;
						}
					}
				}
			}

			if (possible) {
				System.out.println("Able At Row " + y);
				ables++;
			}

		}
	}

	static void input(){
		width = scan.nextInt();
		slopeL = scan.nextInt();
		yMap = new int[width][width]; // [y][x]
		xMap = new int[width][width]; // [x][y]
		for (int i = 0; i < width; i++) {
			yMap[i] = Arrays.stream(scan.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
		}
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < width; y++) {
				xMap[x][y] = yMap[y][x];
			}
		}
	}
	static class FastReader {
		BufferedReader br;
		StringTokenizer st;
		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		public FastReader(String s) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(new File(s)));
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
		long nextLong() {
			return Long.parseLong(next());
		}
		double nextDouble() {
			return Double.parseDouble(next());
		}
		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
