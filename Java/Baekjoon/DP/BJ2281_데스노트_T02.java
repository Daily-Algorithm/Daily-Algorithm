package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2281_데스노트_T02 {
	static FastReader scan = new FastReader();
	static int num, width;
	static int[] lengths;
	static int[] dp;

	public static void main(String[] args) {
		input();
		System.out.println(process(0));
	}

	private static int process(int idx) {
		if (dp[idx] < Integer.MAX_VALUE) {
			return dp[idx];
		}
		int remain = width - lengths[idx];
		for (int i = idx + 1; i <= num && remain >= 0; i++) {
			if (i == num) {
				dp[idx] = 0;
				break;
			}
			dp[idx] = Math.min(dp[idx], remain * remain + process(i));
			System.out.println(Arrays.toString(dp));
			// dp[idx] : 이어붙이기
			// remain * remain + process(i) : 새로운 줄
			remain -= lengths[i] + 1;
		}
		return dp[idx];
	}

	static void input(){
		num = scan.nextInt();
		width = scan.nextInt();
		lengths = new int[num];
		dp = new int[num];
		for (int i = 0; i < num; i++) {
			lengths[i] = scan.nextInt();
		}
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[num - 1] = 0;
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
