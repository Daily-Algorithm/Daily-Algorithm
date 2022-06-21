package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ20055_컨베이어벨트위의로봇 {
	static FastReader scan = new FastReader();
	static Belt[] curArr;
	static Belt[] nextArr;
	static int answer, N, K;
	static int zeroBelt = 0;

	public static void main(String[] args) {
		input();
		while (zeroBelt < K) {
			rotate();
			moveAllRobots();
			putRobotOn();
			answer++;
		}
		System.out.println(answer);
	}

	private static void printRobot() {
		StringBuilder sb1 = new StringBuilder("Robots \n");
		StringBuilder sb2 = new StringBuilder("Endure \n");
		for (Belt belt : curArr) {
			sb1.append(belt.robotOn ? 1 + " " : 0 + " ");
			sb2.append(belt.endure+ " ");
		}
		sb2.append("\n");
		System.out.println(sb1);
		System.out.println(sb2);
	}

	private static void putRobotDown() {
		// 내리기
		if (curArr[N - 1].robotOn) {
			curArr[N - 1].robotOn = false;
		}
	}

	private static void putRobotOn() {
		if (curArr[0].endure != 0) {
			curArr[0].minusEndure();
			curArr[0].robotOn = true;
		}
	}

	private static void moveAllRobots() {
		for (int i = 2 * N - 1; i >= 0; i--) {
			curArr[i].moveRobot();
		}
	}

	private static void rotate() {
		Arrays.stream(curArr).forEach(Belt::rotate);
		curArr = nextArr.clone();
		putRobotDown();
	}

	static void input(){
		N = scan.nextInt();
		K = scan.nextInt();
		curArr = new Belt[2 * N];
		nextArr = new Belt[2 * N];
		for (int i = 0; i < 2 * N; i++) {
			curArr[i] = new Belt(i, scan.nextInt(), false);
		}
		nextArr = curArr.clone();
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

	private static class Belt {
		int idx, endure;
		boolean robotOn;

		public Belt(int idx, int endure, boolean robotOn) {
			this.idx = idx;
			this.endure = endure;
			this.robotOn = robotOn;
		}

		public void minusEndure() {
			this.endure--;
			if (this.endure == 0) {
				zeroBelt++;
			}
		}

		public void rotate() {
			if (this.idx == 2 * N - 1) {
				this.idx = 0;
			} else {
				this.idx++;
			}
			nextArr[idx] = this;
			putRobotDown();
		}

		public void moveRobot() {
			if (this.robotOn && !curArr[idx + 1].robotOn && curArr[idx + 1].endure >= 1) {
				// 다음 벨트로 이동가능하면 로봇 이동
				this.robotOn = false;
				curArr[idx + 1].robotOn = true;
				curArr[idx + 1].minusEndure();
				putRobotDown();
			}
		}
	}
}
