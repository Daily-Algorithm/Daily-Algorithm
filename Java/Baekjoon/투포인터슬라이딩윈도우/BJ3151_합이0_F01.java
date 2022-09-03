package Baekjoon.투포인터슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 이분탐색이 틀린 듯..?
 */
public class BJ3151_합이0_F01 {
	static int N;
	static int[] arr;
	static Map<Integer, Integer> cnts = new HashMap<>();
	static long answer = 0;

	public static void main(String[] args) throws IOException {
		input();
		process();
		System.out.println(answer);
	}

	private static void process() {
		for (int i = 0; i < arr.length && arr[i] <= 0; i++) {
			cntAnswers(i);
		}
	}

	private static void cntAnswers(int i) {
		int L = i + 1;
		int R = N - 1;
		while (L < R) {
			if (value(i) + value(L) + value(R) == 0) {
				if (value(L) == value(R)) {
					answer += (R - L + 1) * (R - L) / 2;
					return;
				} else {
					int cntsOfL = findUpperIdx(L) - findLowerIdx(L);
					int cntsOfR = findUpperIdx(R) - findLowerIdx(R);
					answer += (long) cntsOfL * (long)cntsOfR;
					L += cntsOfL;
					R -= cntsOfR;
				}
			} else if (value(i) + value(L) + value(R) < 0) {
				L++;
			} else {
				R--;
			}
		}
	}
	private static int findUpperIdx(int i) {
		int value = value(i);
		int L = i;
		int R = N;

		while (L < R) {
			int mid = (L + R) / 2;
			if (value >= arr[mid]) {
				L = mid + 1;
			} else {
				R = mid;
			}
		}
		return L;
	}
	private static int findLowerIdx(int i) {
		int value = value(i);
		int L = i;
		int R = N - 1;

		while (L < R) {
			int mid = (L + R) / 2;
			if (value <= arr[mid]) {
				R = mid;
			} else {
				L = mid + 1;
			}
		}
		return L;
	}

	private static int value(int i) {
		return arr[i];
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::new).toArray();
		Arrays.stream(arr).forEach(num -> cnts.put(num, cnts.getOrDefault(num, 0) + 1));
		Arrays.sort(arr);
	}
}