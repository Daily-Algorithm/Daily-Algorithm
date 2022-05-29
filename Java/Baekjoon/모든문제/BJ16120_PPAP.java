package 모든문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ16120_PPAP {
	static FastReader scan = new FastReader();

	public static void main(String[] args) {
		String input = scan.next();
		Stack<Character> stack = new Stack<>();
		boolean isRight = true;
		for (int i = 0; i < input.length(); i++) {
			char cur = input.charAt(i);
			if (cur == 'A') {
				if (stack.size() < 2 || stack.pop() != 'P' || stack.pop() != 'P') {
					isRight = false;
				} else {
					if (i + 1 >= input.length() || input.charAt(i + 1) != 'P') {
						isRight = false;
					}
				}
			} else {
				stack.add(cur);
			}
		}
		if (isRight && stack.size() == 1) {
			System.out.println("PPAP");
		} else {
			System.out.println("NP");
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
	}
}
