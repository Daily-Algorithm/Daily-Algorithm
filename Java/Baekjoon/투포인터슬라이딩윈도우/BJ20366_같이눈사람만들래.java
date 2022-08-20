package Baekjoon.투포인터슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ20366_같이눈사람만들래 {
	static FastReader scan = new FastReader();
	static int snowballs;
	static int[] diaMs;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) {
		input();
		process();
		System.out.println(answer);
	}

	private static void process() {
		for (int L1 = 0; L1 < snowballs - 3; L1++) {
			for (int R1 = L1 + 3; R1 < snowballs; R1++) {
				int L2 = L1 + 1;
				int R2 = R1 - 1;
				while (L2 < R2) {
					int diff = (diaMs[L1] + diaMs[R1]) - (diaMs[L2] + diaMs[R2]);
					answer = Math.min(answer, Math.abs(diff));
					if (diff < 0) {
						R2--;
					} else if (diff > 0) {
						L2++;
					} else {
						return;
					}
				}

			}
		}
	}

	static void input(){
		snowballs = scan.nextInt();
		diaMs = new int[snowballs];
		diaMs = Arrays.stream(scan.nextLine().split(" ")).mapToInt(Integer::new).toArray();
		Arrays.sort(diaMs);
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
