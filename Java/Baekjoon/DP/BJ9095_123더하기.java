package DP;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 다시 확인
 */
public class BJ9095_123더하기 {
	static int tests;
	static int max = 0;
	static int[] nums;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		tests = Integer.parseInt(br.readLine());
		nums = new int[tests];
		for (int i = 0; i < tests; i++) {
			nums[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, nums[i]);
		}
		dp = new int[max + 1];
		dp[0] = 0; dp[1] = 1; dp[2] = 2; dp[3] = 4;
		for (int i = 4; i <= max; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
		}
		for (int num : nums) {
			out.println(dp[num]);
		}
	}
}
