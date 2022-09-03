package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2281_데스노트_T01 {
	static FastReader scan = new FastReader();
	static int num, width;
	static int[] names;
	static int[][] dp;

	public static void main(String[] args) {
		input();
		System.out.println(minSquareSum(1, names[0] + 1)); // length 공백포함
	}

	private static int minSquareSum(int nextIdx, int curLen) {
		if (nextIdx == num) return 0;
		if (dp[nextIdx][curLen] != -1) return dp[nextIdx][curLen];
		// nextIdx를 첫 이름으로 새 행을 썼을 때의 (공백)^2의 합
		dp[nextIdx][curLen] =
			(width - curLen + 1) * (width - curLen + 1)	// 새 행에서의 남는 칸 수(공백 제거)의 제곱
			+ minSquareSum(nextIdx + 1, names[nextIdx] + 1);	// 다음 행에서의 남는 칸 수의 제곱의 합
		if (curLen + names[nextIdx] <= width) {
			dp[nextIdx][curLen] = Math.min(
				dp[nextIdx][curLen],	// nextIdx를 첫 이름으로 새 행을 썼을 때의 값
				minSquareSum(nextIdx + 1, curLen + names[nextIdx] + 1)	// nextIdx를 행에 이어서 썼을 때의 값
			);
		}
		return dp[nextIdx][curLen];
	}

	static void input(){
		num = scan.nextInt();
		width = scan.nextInt();
		names = new int[num];
		dp = new int[1000][1002];
		for (int i = 0; i < num; i++) {
			names[i] = scan.nextInt();
		}
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
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
	}
}
