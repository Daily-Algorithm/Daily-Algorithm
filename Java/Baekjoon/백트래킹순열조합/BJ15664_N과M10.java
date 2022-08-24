package Baekjoon.백트래킹순열조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BJ15664_N과M10 {
	static int N, M;
	static int[] arr;
	static Set<String> set = new HashSet<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		input();
		process();
		System.out.println(sb.toString());
	}

	private static void process() {
		permutation(new boolean[N], 0, 0, "");
	}

	private static void permutation(boolean[] visited, int dept, int choice, String seq) {
		if (choice == M) {
			if (!set.contains(seq)) {
				set.add(seq);
				sb.append(seq).append("\n");
			}
			return;
		}
		if (dept == N) {
			return;
		}
		if (!visited[dept]) {
			visited[dept] = true;
			permutation(
				visited,
				dept + 1,
				choice + 1,
				seq.equals("") ? String.valueOf(arr[dept]) : seq + " " + arr[dept]
			);
			visited[dept] = false;
			permutation(visited, dept + 1, choice, seq);
		}
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line1 = br.readLine()
						   .split(" ");
		N = Integer.parseInt(line1[0]);
		M = Integer.parseInt(line1[1]);
		arr = new int[N];
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::new).toArray();
		Arrays.sort(arr);
	}
}
