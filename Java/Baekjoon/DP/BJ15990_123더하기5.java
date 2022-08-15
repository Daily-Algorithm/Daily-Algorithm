package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ15990_123더하기5 {

	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();
	static Map<Integer, Long>[] dp = new Map[100001];
	static int limit = 1000000009;

	public static void main(String[] args) {
		process();
	}

	static void process() {
		int testNum = scan.nextInt();
		makeDp();
		for (int i = 0; i < testNum; i++) {
			Map<Integer, Long> map = dp[scan.nextInt()];
			sb.append((map.get(1) + map.get(2) + map.get(3)) % limit + "\n");
		}
		System.out.println(sb.toString());
	}

	private static void makeDp() {
		dp[1] = new HashMap<>();
		dp[1].put(1, 1L);
		dp[1].put(2, 0L);
		dp[1].put(3, 0L);

		dp[2] = new HashMap<>();
		dp[2].put(1, 0L);
		dp[2].put(2, 1L);
		dp[2].put(3, 0L);

		dp[3] = new HashMap<>();
		dp[3].put(1, 1L);
		dp[3].put(2, 1L);
		dp[3].put(3, 1L);

		for (int i = 4; i < 100001; i++) {
			dp[i] = new HashMap<>();
			dp[i].put(1, (dp[i - 1].get(2) + dp[i - 1].get(3)) % limit);
			dp[i].put(2, (dp[i - 2].get(1) + dp[i - 2].get(3)) % limit);
			dp[i].put(3, (dp[i - 3].get(1) + dp[i - 3].get(2)) % limit);
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
