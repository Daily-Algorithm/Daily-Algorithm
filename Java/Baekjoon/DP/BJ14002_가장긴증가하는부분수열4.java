package DP;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14002_가장긴증가하는부분수열4 {
	static int[] dp, sequence, record;
	static int nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		nums = Integer.parseInt(br.readLine());
		dp = new int[nums];
		sequence = new int[nums];
		record = new int[nums];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < nums; i++) {
			sequence[i] = Integer.parseInt(st.nextToken());
		}

		dp[0] = 1; record[0] = 0;
		int dpMax = 1;	int dpMaxIdx = 0;
		for (int i = 1; i < nums; i++) {
			dp[i] = 1; record[i] = i;
			for (int before = 0; before < i; before++) {
				if (sequence[before] < sequence[i] && dp[before] >= dp[i]) {
					dp[i] = dp[before] + 1;
					record[i] = before;
					dpMaxIdx = Math.max(dp[i], dpMax) == dp[i] ? i : dpMaxIdx;
					dpMax = Math.max(dp[i], dpMax);
				}
			}
		}

		out.println(dpMax);
		String[] part = new String[dpMax];
		int partIdx = dpMax - 1;
		for (int idx = dpMaxIdx; ; idx = record[idx]) {
			part[partIdx] = String.valueOf(sequence[idx]);	partIdx--;
			if (idx == record[idx]) {
				break;
			}
		}
		out.println(String.join(" ", part));
	}
}
