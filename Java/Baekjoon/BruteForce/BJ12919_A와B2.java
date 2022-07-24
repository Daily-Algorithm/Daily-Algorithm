package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ12919_A와B2 {
	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();
	static String from, to;
	static boolean answer;

	public static void main(String[] args) {
		input();
		bruteforce(from);
		System.out.println(answer ? "1" : "0");
	}

	private static void bruteforce(String from) {
		if (from.length() == to.length()) {
			if (from.equals(to)) answer = true;
			return;
		}

		if (answer) return;

		bruteforce(from + "A");
		String two = new StringBuffer(from + "B").reverse().toString();
		bruteforce(two);
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
