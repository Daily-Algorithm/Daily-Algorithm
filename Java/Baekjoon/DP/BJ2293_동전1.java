package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://mygumi.tistory.com/129
 */
public class BJ2293_동전1 {
	static FastReader scan = new FastReader();
	static int n, k;
	static int[] coins;
	static int[] dp;

	public static void main(String[] args) {
		input();
		process();
		System.out.println(dp[k]);
	}

	private static void process() {
		for (int coinIdx = 0; coinIdx < n; coinIdx++) {
			int coin = coins[coinIdx];
			for (int sum = coin; sum <= k; sum++) {
				dp[sum] = dp[sum] + dp[sum - coin];
			}
		}
	}

	static void input(){
		n = scan.nextInt();
		k = scan.nextInt();
		coins = new int[n];
		dp = new int[k + 1];
		dp[0] = 1;
		for (int i = 0; i < n; i++) {
			coins[i] = scan.nextInt();
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
