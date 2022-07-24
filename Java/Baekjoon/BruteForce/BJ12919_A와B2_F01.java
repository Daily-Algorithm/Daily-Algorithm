package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ12919_A와B2_F01 {
	static FastReader scan = new FastReader();
	static String from, to;
	static boolean answer;

	public static void main(String[] args) {
		input();
		bruteforce(to);
		System.out.println(answer ? "1" : "0");
	}

	private static void bruteforce(String to) {
		if (to.length() == from.length()) {
			if (to.equals(from)) {
				answer = true;
			}
			return;
		}
		if (answer) return;

		if (to.charAt(to.length() - 1) == 'A') {
			bruteforce(to.substring(0, to.length() - 1));
		}
		if (to.charAt(0) == 'B') {
			bruteforce(new StringBuffer(to.substring(1)).reverse().toString());
		}
	}

	static void input(){
		from = scan.next();
		to = scan.next();
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
	}
}
