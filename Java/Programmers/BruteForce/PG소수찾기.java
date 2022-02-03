package BruteForce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PG소수찾기 {

	static List<String> splitedL;

	public static int solution(String numbers) {
		String[] splited = numbers.split("");
		splitedL = Arrays.asList(splited);

		Arrays.sort(splited, Collections.reverseOrder());
		int max = Integer.parseInt(String.join("", splited));

		List<Integer> primeNums = primeNums(max);

		System.out.println(primeNums);
		return primeNums.size();
	}

	static List<Integer> primeNums(int max) {
		boolean[] isPrime = new boolean[max+1];
		Arrays.fill(isPrime , true);
		
		for (int i = 2; i <= max; i++) {
			for (int j = 2; i * j <= max; j++) {
				isPrime[i * j] = false;
			}
		}

		List<Integer> primeList = new ArrayList<>();

		for (int i = 2; i < isPrime.length; i++) {
			if (isPrime[i] && isConsistOf(i)) {
				primeList.add(i);
			}
		}

		return primeList;
	}

	static boolean isConsistOf(int num) {
		List<String> copy = new ArrayList<>();
		copy.addAll(splitedL);

		String[] splitedPN = String.valueOf(num)
								   .split("");

		for (String chopped : splitedPN) {
			if (copy.contains(chopped)) {
				copy.remove(chopped);
			} else {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(solution("011"));
	}
}
