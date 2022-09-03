package Baekjoon.백트래킹순열조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ16938_캠프준비 {
	static FastReader scan = new FastReader();
	static int N, L, R, X, answer;
	static int[] arr;
	public static void main(String[] args) {
		input();
		permutation(new boolean[N], 0, 0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
		System.out.println(answer);
	}

	private static void permutation(
		boolean[] visited,
		int dept,
		int sum,
		int cnt,
		int min,
		int max
	) {
		if (dept == N) {
			if (cnt >= 2 && L <= sum && sum <= R && X <= max - min) answer++;
			return;
		}

		visited[dept] = true;
		permutation(
			visited, dept + 1, sum + arr[dept], cnt + 1,
			Math.min(min, arr[dept]),
			Math.max(max, arr[dept])
		);
		visited[dept] = false;
		permutation(visited, dept + 1, sum, cnt, min, max);

	}

	static void input() {
		String[] split = scan.nextLine().split(" ");
		N = Integer.parseInt(split[0]);
		L = Integer.parseInt(split[1]);
		R = Integer.parseInt(split[2]);
		X = Integer.parseInt(split[3]);
		arr = new int[N];
		arr = Arrays.stream(scan.nextLine().split(" "))
					.mapToInt(Integer::new)
					.toArray();
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;
		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
