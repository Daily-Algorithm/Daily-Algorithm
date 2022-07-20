package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2580_스도쿠 {
	static FastReader scan = new FastReader();
	static int[][] map = new int[9][9];

	public static void main(String[] args) {
		input();
		find(0, 0);
	}

	private static void find(int x, int y) {
		if (x == 9) {
			find(0, y + 1);
			return;
		}
		if (y == 9) {
			printMap(map);
			System.exit(0);
		}

		if (map[y][x] == 0) {
			for (int i = 1; i < 10; i++) {
				if (check(x, y, i)) {
					map[y][x] = i;
					find(x + 1, y);
					map[y][x] = 0;
				}
			}
			return;
		}

		find(x + 1, y);
	}


	private static boolean check(int x, int y, int i) {
		for (int idxY = 0; idxY < 9; idxY++) {
			if (map[idxY][x] == i) {
				return false;
			}
		}

		for (int idxX = 0; idxX < 9; idxX++) {
			if (map[y][idxX] == i) {
				return false;
			}
		}

		int minX = 3 * (x / 3);
		int minY = 3 * (y / 3);

		for (int idxY = minY; idxY < minY + 3; idxY++) {
			for (int idxX = minX; idxX < minX + 3; idxX++) {
				if (map[idxY][idxX] == i) {
					return false;
				}
			}
		}

		return true;
	}

	static void input(){
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				map[y][x] = scan.nextInt();
			}
		}
	}

	private static void printMap(int[][] map) {
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				sb.append(map[y][x]);
				sb.append(" ");
			}
			if (y < 8) {
				sb.append("\n");
			}
		}
		System.out.println(sb);
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
