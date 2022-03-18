package DP;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 후반에서 틀렸다고 나오면 작은 data값에서 틀렸다는 것이라고 함
 * 99%에서 틀렸는데 알고보니 max = 0으로 둬서
 * 1
 * 1
 * 일 때 0 을 출력해 틀렸습니다 나옴
 */
public class BJ11053_가장긴증가하는부분수열 {
	static int[] dp;
	static int[] nums;
	static int[] upperBoundIdx;
	static int num, max = 1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		num = Integer.parseInt(br.readLine());
		dp = new int[num];
		Arrays.fill(dp, 0);
		upperBoundIdx = new int[num];
		nums = new int[num];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < num; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		dp[num - 1] = 1;
		for (int curIdx = num - 2; curIdx >= 0; curIdx--) {
			int dpMax = 0;
			for (int nextIdx = curIdx; nextIdx < num; nextIdx++) {
				if (nums[nextIdx] > nums[curIdx]) {
					dpMax = Math.max(dpMax, dp[nextIdx]);
				}
			}
			dp[curIdx] = dpMax + 1;
			max = Math.max(max, dp[curIdx]);
		}
		out.println(max);

	}
}
