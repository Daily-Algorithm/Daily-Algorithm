package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ11047_동전0 {

	static int N, MONEY;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		MONEY = Integer.parseInt(st.nextToken());

		List<Integer> coins = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			coins.add(Integer.valueOf(br.readLine()));
		}

		int count = 0;
		for (int i = N - 1; i >= 0 && MONEY > 0; i--) {
			int kind = coins.get(i);
			if (kind <= MONEY) {
				int coinNum = MONEY / kind;
				MONEY = MONEY % kind;
				count += coinNum;
			}
		}
		System.out.println(count);
	}
}
