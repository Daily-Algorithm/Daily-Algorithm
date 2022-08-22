package Baekjoon.투포인터슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ11728_배열합치기_T01 {
	static int one, two;
	static String[] oneArr, twoArr;
	static String[] answer;

	public static void main(String[] args) {
		input();
		process();
	}

	private static void process() {
		int oneP = 0;
		int twoP = 0;
		int idx = 0;
		while (oneP < one && twoP < two) {
			if (Integer.parseInt(oneArr[oneP]) <= Integer.parseInt(twoArr[twoP])) {
				answer[idx] = oneArr[oneP];
				oneP++;
			} else {
				answer[idx] = twoArr[twoP];
				twoP++;
			}
			idx++;
		}
		if (oneP != one) {
			for (int i = oneP; i < one; i++, idx++) {
				answer[idx] = oneArr[i];
			}
		}
		if (twoP != two) {
			for (int i = twoP; i < two; i++, idx++) {
				answer[idx] = twoArr[i];
			}
		}

		System.out.println(String.join(" ", answer));
	}

	private static void input() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String[] split = br.readLine()
							   .split(" ");
			one = Integer.parseInt(split[0]);
			oneArr = new String[one];
			two = Integer.parseInt(split[1]);
			twoArr = new String[two];
			answer = new String[one + two];

			oneArr = br.readLine().split(" ");
			twoArr = br.readLine().split(" ");

		} catch (IOException e) {
		}
	}
}
