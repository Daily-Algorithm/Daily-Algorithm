package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * doOrder()에서 톱니바퀴 idx에 따라 switch문으로 했었는데 실패했는데,
 * 톱니바퀴 idx를 기준으로 왼쪽, 오른쪽 나눠서 for문 돌리니 성공
 */

public class BJ14891_톱니바퀴 {
	static FastReader scan = new FastReader();
	static String[][] wheels = new String[4][8];
	static int[][] orders;
	static int score = 0;

	public static void main(String[] args) {
		input();
		for (int[] order : orders) {
			doOrder(order);
		}
		calculate();
		System.out.println(score);
	}

	private static void calculate() {
		if (wheels[0][0].equals("1")) {
			score += 1;
		}
		if (wheels[1][0].equals("1")) {
			score += 2;
		}
		if (wheels[2][0].equals("1")) {
			score += 4;
		}
		if (wheels[3][0].equals("1")) {
			score += 8;
		}
	}

	private static void doOrder(int[] order) {
		int wheelIdx = order[0] - 1;

		int[] dir = new int[4];
		dir[wheelIdx] = order[1];

		for (int i = wheelIdx - 1; i >= 0; i--) {
			dir[i] = wheels[i][2].equals(wheels[i + 1][6]) ? 0 : dir[i + 1] * -1;
		}
		for (int i = wheelIdx + 1; i < 4; i++) {
			dir[i] = wheels[i][6].equals(wheels[i - 1][2]) ? 0 : dir[i - 1] * -1;
		}

		for (int i = 0; i < 4; i++) {
			if (dir[i] == 1) {
				wheels[i] = CW(wheels[i]);
			} else if (dir[i] == -1) {
				wheels[i] = counterCW(wheels[i]);
			}
		}
	}

	private static String[] counterCW(String[] before) {
		String[] temp = new String[8];
		for (int tempIdx = 0; tempIdx < 8; tempIdx++) {
			temp[tempIdx] = before[(tempIdx + 1) % 8];
		}
		return temp;
	}

	private static String[] CW(String[] before) {
		String[] temp = new String[8];
		for (int tempIdx = 0; tempIdx < 8; tempIdx++) {
			temp[tempIdx] = before[(7 + tempIdx) % 8];
		}
		return temp;
	}

	static void input(){
		wheels[0] = scan.nextLine().split("");
		wheels[1] = scan.nextLine().split("");
		wheels[2] = scan.nextLine().split("");
		wheels[3] = scan.nextLine().split("");

		int rotate = scan.nextInt();
		orders = new int[rotate][2];
		for (int i = 0; i < rotate; i++) {
			orders[i] = new int[]{scan.nextInt(), scan.nextInt()};
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
