package Greedy;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BJ1744_수묶기 {
	static int nums;
	static List<Integer> negative = new ArrayList<>();
	static List<Integer> positive = new ArrayList<>();
	static int oneNum, zeroNum = 0;
	static int answer = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		nums = Integer.parseInt(br.readLine());
		for (int i = 0; i < nums; i++) {
			int number = Integer.parseInt(br.readLine());
			if (number == 1) {
				oneNum++;
			} else if (number == 0) {
				zeroNum++;
			} else if (number > 1) {
				positive.add(number);
			} else if (number < 0) {
				negative.add(number);
			}
		}

		Collections.sort(positive, Collections.reverseOrder());	// 큰 순서대로
		Collections.sort(negative);	// 작은 순서대로

		int positiveNums = positive.size();
		if (positiveNums % 2 == 0) {
			for (int i = 0; i < positiveNums; i = i + 2) {
				answer += positive.get(i) * positive.get(i + 1);
			}
		} else {
			for (int i = 0; i < positiveNums - 1; i = i + 2) {
				answer += positive.get(i) * positive.get(i + 1);
			}
			answer += positive.get(positiveNums - 1);
		}
		answer += oneNum;

		int negativeNums = negative.size();
		if (negativeNums % 2 == 0) {
			for (int i = 0; i < negativeNums; i = i + 2) {
				answer += negative.get(i) * negative.get(i + 1);
			}
		} else {
			for (int i = 0; i < negativeNums - 1; i = i + 2) {
				answer += negative.get(i) * negative.get(i + 1);
			}
			if (zeroNum == 0) {
				answer += negative.get(negativeNums - 1);
			}
		}

		out.println(answer);
	}
}
