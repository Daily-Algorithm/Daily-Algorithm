package 모든문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ15650_N과M2 {

	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		recur("", 0);
	}

	static void recur(String sequence, int before) {
		if (sequence.length() == M) {
			System.out.println(String.join(" ", sequence.split("")));
		}
		for (int i = before + 1; i <= N; i++) {
			String nextSequence = sequence + i;
			recur(nextSequence, i);
		}
	}
}
