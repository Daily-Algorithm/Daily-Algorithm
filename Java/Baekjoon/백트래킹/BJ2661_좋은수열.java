package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2661_좋은수열 {
	static FastReader scan = new FastReader();
	static int N;
	static boolean found = false;

	public static void main(String[] args) {
		input();
		permutation("");
	}

	private static void permutation(String seq) {
		// length가 N이면 answer의 후보
		if (seq.length() == N) {
			System.out.println(seq);
			found = true;
			return;
		}

		// seq에 붙이는 과정
		for (int i = 1; i <= 3; i++) {
			String next = seq + i;
			if (!found && ableSeq(next)) {
				permutation(next);
			}
		}
	}

	private static boolean ableSeq(String seq) {
		int seqL = seq.length();
		if (seqL > 1) {
			for (int length = 1; length <= seqL / 2; length++) {
				String left = seq.substring(seqL - 2 * length, seqL - length);
				String right = seq.substring(seqL - length, seqL);
				if (left.equals(right)) return false;
			}
			return true;
		}
		return true;
	}

	static void input(){
		N = scan.nextInt();
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
