package Baekjoon.투포인터슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ11728_배열합치기 {
	static int one, two;
	static int[] answer;

	public static void main(String[] args) {
		input();
		process();
	}

	private static void process() {
		Arrays.sort(answer);
		StringBuilder sb = new StringBuilder();
		for (int j : answer) {
			sb.append(j + " ");
		}
		System.out.println(sb.toString());
	}

	private static void input() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String[] split = br.readLine()
							   .split(" ");
			one = Integer.parseInt(split[0]);
			two = Integer.parseInt(split[1]);
			answer = new int[one + two];

			String[] oneArr = br.readLine()
								.split(" ");
			for (int i = 0; i < one; i++) {
				answer[i] = Integer.parseInt(oneArr[i]);
			}
			String[] twoArr = br.readLine()
								.split(" ");
			for (int i = 0; i < two; i++) {
				answer[one + i] = Integer.parseInt(twoArr[i]);
			}

		} catch (IOException e) {
		}
	}
}
