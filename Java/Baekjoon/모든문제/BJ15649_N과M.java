package 모든문제;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ15649_N과M {
	static int N, M;
	static List<String> answers = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		boolean[] visited = new boolean[N + 1];
		Arrays.fill(visited, false);

		permutation(0, visited, "");
		for (String answer : answers) {
			out.println(answer);
		}
	}

	private static void permutation(int choose, boolean[] visited, String str) {
		if (choose == M) {
			answers.add(str);
		}
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				permutation(choose + 1, visited, str.equals("") ? String.valueOf(i) : str + " " + i);
				visited[i] = false;
			}
		}
	}
}
