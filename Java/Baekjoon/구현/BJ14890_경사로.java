package 구현;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ14890_경사로 {
	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();
	static int slopeL, width;
	static int[][] yMap;
	static int[][] xMap;
	static boolean[] slopped;
	static int ables = 0;
	public static void main(String[] args) {
		input();
		calAbleOfLine(xMap);
		calAbleOfLine(yMap);
		System.out.println(ables);
	}

	private static void calAbleOfLine(int[][] map) {
		for (int i = 0; i < width; i++) {
			if (isAbleLine(map[i])) {
				ables++;
			}
		}
	}

	private static boolean isAbleLine(int[] line) {
		slopped = new boolean[width];
		for (int idx = 1; idx < width; idx++) {
			int curH = line[idx];
			int leftH = line[idx - 1];
			int heightDiff = Math.abs(curH - leftH);
			if (heightDiff > 1) {
				return false;
			} else if (heightDiff == 1) {
				// 경사로 깔아야 되는 위치
				int dir = leftH - curH;
				Slop candidate = dir > 0 ? new Slop(idx, dir) : new Slop(idx - 1, dir);
				if (sloppable(line, candidate)) {
					setSlop(candidate);
				} else {
					return false;
				}
			}
		}
		return true;
	}

	private static void setSlop(Slop slop) {
		int length = 0;
		for (int idx = slop.startIdx; length < slopeL; idx += slop.dir) {
			slopped[idx] = true;
			length++;
		}
	}

	private static boolean sloppable(int[] line, Slop candidate) {
		int startBlockH = line[candidate.startIdx];
		int sameBlock = 0;
		for (int idx = candidate.startIdx; sameBlock < slopeL; idx += candidate.dir) {
			if (idxInRange(idx)) {
				int curH = line[idx];
				if (startBlockH == curH && !slopped[idx]) {
					sameBlock++;
				} else {
					return false;
				}
			} else {

				return false;
			}
		}
		return true;
	}

	private static boolean idxInRange(int idx) {
		return idx >= 0 && idx < width;
	}

	static void input(){
		width = scan.nextInt();
		slopeL = scan.nextInt();
		yMap = new int[width][width]; // [y][x]
		xMap = new int[width][width]; // [x][y]
		slopped = new boolean[width];
		for (int i = 0; i < width; i++) {
			yMap[i] = Arrays.stream(scan.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
		}
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < width; y++) {
				xMap[x][y] = yMap[y][x];
			}
		}
	}

	private static class Slop {
		int startIdx, dir;

		public Slop(int startIdx, int dir) {
			this.startIdx = startIdx;
			this.dir = dir;
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
