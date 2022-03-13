package Greedy;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BJ10610_30 {
	static String N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		N = br.readLine();

		if (N.contains("0") && sumOfNum(N) % 3 == 0) {
			out.println(makeMax(N));
		} else {
			out.println(-1);
		}
	}

	private static String makeMax(String num) {
		StringBuffer sb = new StringBuffer();
		String[] split = num.split("");
		Arrays.sort(split, Comparator.reverseOrder());
		for (String each : split) {
			sb.append(each);
		}
		return sb.toString();
	}

	static Long sumOfNum(String num) {
		Long sum = 0L;
		for (int i = 0; i < num.length(); i++) {
			int number = num.charAt(i) - '0';
			sum += number;
		}
		return sum;
	}
}
