package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ1062_가르침_F01 {
	static FastReader scan = new FastReader();
	static int N, K;
	static String[] arr;
	static List<String> ableStrings = new ArrayList<>();
	static List<Set<String>> setList = new ArrayList<>();
	static Set[] setArr;
	static int answer = -1;
	public static void main(String[] args) {
		input();
		if (K < 5) {
			System.out.println(0);
		} else {
			powerSet(0, new boolean[ableStrings.size()]);
			if (answer == -1) {
				System.out.println(0);
			} else {
				System.out.println(answer);
			}
		}
	}
	static void powerSet(int dept, boolean[] visited) {
		if(dept == N) {
			answer = Math.max(answer, calWords(visited));
			return;
		}

		visited[dept] = true;
		powerSet(dept + 1, visited);
		visited[dept] = false;
		powerSet(dept + 1, visited);
	}

	private static int calWords(boolean[] visited) {
		Set<String> union = new HashSet<>();
		int chosen = 0;
		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				chosen++;
				union.addAll(setList.get(i));
			}
		}
		if (union.size() > K - 5) {
			return -1;
		} else {
			return chosen;
		}
	}

	static void input(){
		N = scan.nextInt();
		K = scan.nextInt();
		arr = new String[N];
		setArr = new Set[N];
		for (int i = 0; i < N; i++) {
			arr[i] = scan.next();
			arr[i] = arr[i].substring(4, arr[i].length() - 4);
			Set<String> letterS = new HashSet<>();
			letterS.addAll(Arrays.asList(arr[i].split("")));
			if (letterS.size() <= K - 5) {
				ableStrings.add(arr[i]);
				setList.add(letterS);
			}
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
