package DP;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1932_정수삼각형 {
	static int layer;
	static int[][] triangle;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		layer = Integer.parseInt(br.readLine());
		triangle = new int[layer][];
		dp = new int[layer][];
		for (int layerIdx = 0; layerIdx < layer; layerIdx++) {
			triangle[layerIdx] = new int[layerIdx + 1];
			dp[layerIdx] = new int[layerIdx + 1];
			Arrays.fill(dp[layerIdx], 0);
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int numIdx = 0; numIdx <= layerIdx; numIdx++) {
				triangle[layerIdx][numIdx] = Integer.parseInt(st.nextToken());
			}
		}
		dp[0][0] = triangle[0][0];
		for (int layerIdx = 1; layerIdx < layer; layerIdx++) {
			for (int numIdx = 0; numIdx < triangle[layerIdx].length; numIdx++) {
				int point = triangle[layerIdx][numIdx];
				int upLeftP = numIdx == 0 ? 0 : dp[layerIdx - 1][numIdx - 1];
				int upP =
					numIdx == triangle[layerIdx].length - 1 ? 0 : dp[layerIdx - 1][numIdx];
				dp[layerIdx][numIdx] = point + Math.max(upP, upLeftP);
			}
		}
		out.println(Arrays.stream(dp[layer-1]).max().getAsInt());
	}
}
