package Baekjoon.투포인터슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class BJ3273_두수의합_F01 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer n = Integer.valueOf(br.readLine());
		List<Integer> splited = Arrays.asList(Stream.of(br.readLine()
														  .split(" "))
													.mapToInt(Integer::parseInt)
													.boxed()
													.toArray(Integer[]::new));
		Collections.sort(splited);

		Integer x = Integer.valueOf(br.readLine());

		Integer answer = 0;
		for (int i = 0; i < n / 2; i++) {
			Integer lowerNum = splited.get(i);
			if (lowerNum > x) {
				break;
			} else {
				if (splited.indexOf((Integer) x - lowerNum) > splited.indexOf(lowerNum)) {
					answer++;
				}
			}
		}
		System.out.println(answer);
	}
}
