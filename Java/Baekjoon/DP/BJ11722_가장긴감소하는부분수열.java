package DP;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11722_가장긴감소하는부분수열 {

	static int N;
	static int[] sequence;
	static int[] dp;
	static int max = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		N = Integer.parseInt(br.readLine());
		sequence = new int[N];
		dp = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			sequence[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			for (int before = 0; before <= i; before++) {
				if (sequence[before] > sequence[i]) {
					dp[i] = Math.max(dp[i], dp[before] + 1);
				}
			}
			max = Math.max(dp[i], max);
		}
		out.println(max);
	}
}
