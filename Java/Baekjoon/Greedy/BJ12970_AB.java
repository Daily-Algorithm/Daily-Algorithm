package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ12970_AB {
	static FastReader scan = new FastReader();
	static int N, K;
	static String answer = "";

	public static void main(String[] args) {
		input();
		makeAnswer();
		if (answer.equals("")) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}

	private static void makeAnswer() {
		// 만들 수 있는 최대값은 N^2/4
		int maxK = N % 2 == 0 ? N * N / 4 : (N + 1) * (N - 1) / 4;
		int aNum = N % 2 == 0 ? N / 2 : (N - 1) / 2;

		if (K <= maxK) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++) {
				if (i < aNum) {
					sb.append("A");
				} else {
					sb.append("B");
				}
			}
			while (maxK != K) {
				int replaced = sb.lastIndexOf("AB");
				sb = sb.replace(replaced, replaced + 2, "BA");
				maxK--;
			}
			answer = sb.toString();
		}
	}

	static void input(){
		N = scan.nextInt();
		K = scan.nextInt();
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
