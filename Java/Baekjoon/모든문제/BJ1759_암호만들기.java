package 모든문제;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BJ1759_암호만들기 {
	static int L, C;
	static TreeSet<String> answers = new TreeSet<>();
	static Set<String> vowels = new HashSet<>();
	static String[] arr;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		vowels.add("a");
		vowels.add("e");
		vowels.add("i");
		vowels.add("o");
		vowels.add("u");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new String[C];
		visited = new boolean[C];
		Arrays.fill(visited, false);

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < C; i++) {
			String alphabet = st.nextToken();
			if (vowels.contains(alphabet)) {
				vowels.add(alphabet);
			}
			arr[i] = alphabet;
		}
		Arrays.sort(arr);

		combination(0, 0, visited, 0, 0, "");
		for (String answer : answers) {
			out.println(answer);
		}
	}

	private static void combination(
		int length,
		int dept,
		boolean[] visited,
		int vowel,
		int consonant,
		String password
	) {
		if (length == L) {
			if (vowel >= 1 && consonant >= 2) {
				answers.add(password);
			}
			return;
		}
		if (dept == C) {
			return;
		} else {
			visited[dept] = true;
			String alphabet = arr[dept];
			if (vowels.contains(alphabet)) {
				combination(
					length + 1, dept + 1, visited, vowel + 1, consonant, password + alphabet);
			} else {
				combination(
					length + 1, dept + 1, visited, vowel, consonant + 1, password + alphabet);
			}

			visited[dept] = false;
			combination(length, dept + 1, visited, vowel, consonant, password);
		}
	}
}
