package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1541_잃어버린괄호 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), "-");
		int start = refine(st.nextToken());
		while (st.hasMoreTokens()) {
			start -= refine(st.nextToken());
		}
		System.out.println(start);
	}

	static int refine(String str) {
		int refined = 0;
		StringTokenizer st = new StringTokenizer(str, "+");
		while (st.hasMoreTokens()) {
			refined += Integer.parseInt(st.nextToken());
		}
		return refined;
	}
}
