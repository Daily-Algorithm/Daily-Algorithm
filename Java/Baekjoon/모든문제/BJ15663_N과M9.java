package 모든문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * Collections.sort()에서 comparator 오버라이딩하는 거 좀 다시 봐야겠다.
 * return -1이면 자리 바꾸는건가?
 */

public class BJ15663_N과M9 {
	static FastReader scan = new FastReader();
	//정답은 sb에 append 를 사용하여 출력
	//만약 개행까지 출력하고 싶으면 append('\n')을 추가
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static String[] arr;
	static boolean[] visited;
	static Set<String> answers = new HashSet<>();
	public static void main(String[] args) {
		input();
		makeAnswers(0, 0, visited, "");
		printAnswers();
	}

	private static void printAnswers() {
		List<String> strings = new ArrayList<>(answers);
		List<String[]> arrs = strings.stream()
										.map(str -> str.split(" "))
										.collect(Collectors.toList());
		Collections.sort(arrs, new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				for (int i = 0; i < M; i++) {
					if (!o1[i].equals(o2[i])) {
						if (Integer.valueOf(o1[i]).compareTo(Integer.valueOf(o2[i])) < 0) {
							return -1;
						}
						return 1;
					}
				}
				return 0;
			}
		});
		for (String[] arr : arrs) {
			System.out.println(String.join(" ", arr));
		}
	}

	static void makeAnswers(int dept, int choice, boolean[] visited, String str) {
		if (dept == M) {
			answers.add(str);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				String chosen = arr[i];
				visited[i] = true;
				makeAnswers(
					dept + 1, choice + 1, visited, str.equals("") ? chosen : str + " " + chosen);
				visited[i] = false;
			}
		}
	}

	static void input(){
		N = scan.nextInt();
		M = scan.nextInt();
		arr = new String[N];
		visited = new boolean[N];
		Arrays.fill(visited, false);
		for (int i = 0; i < N; i++) {
			arr[i] = scan.next();
		}
	}
	static class FastReader {
		BufferedReader br;
		StringTokenizer st;
		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
