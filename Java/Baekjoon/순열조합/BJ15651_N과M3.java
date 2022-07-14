package 순열조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ15651_N과M3 {
	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();
	static int N, M;

	public static void main(String[] args) {
		input();
		permutation(0, "");
		System.out.println(sb);
	}

	private static void permutation(int i, String seq) {
		if (i == M) {
			sb.append(seq + "\n");
			return;
		}
		for (int j = 1; j <= N; j++) {
			permutation(i + 1, seq.equals("") ? seq + j : seq + " " + j);
		}
	}

	static void input(){
		N = scan.nextInt();
		M = scan.nextInt();
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
