package DP;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ11726_2xn타일링 {
	static int width;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		width = Integer.parseInt(br.readLine());
		dp = new int[width + 1];
		dp[0] = 1; dp[1] = 1;
		for (int idx = 2; idx <= width; idx++) {
			dp[idx] = (dp[idx - 1] + dp[idx - 2]) % 10007;
		}
		out.println(dp[width] % 10007);
	}
}
