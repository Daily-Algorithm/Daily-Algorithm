package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1476_날짜 {
	static int E, S, M;
	static int SCOPE_E = 15;
	static int SCOPE_S = 28;
	static int SCOPE_M = 19;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		E = Integer.parseInt(split[0]); // x % 15
		S = Integer.parseInt(split[1]);	// x % 28
		M = Integer.parseInt(split[2]); // x % 19

		for (int i = 0; ; i++) {
			int x = SCOPE_S * i + S;
			if ((x % SCOPE_E == E % SCOPE_E) && x % SCOPE_M == M % SCOPE_M) {
				System.out.println(x);
				break;
			}
		}
	}
}
