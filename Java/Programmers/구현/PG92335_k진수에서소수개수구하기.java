package 구현;

import java.util.Arrays;

public class PG92335_k진수에서소수개수구하기 {
	public static int solution(int n, int k) {
		String changedNum = Integer.toString(n, k);
		return findPrime(changedNum);
	}

	private static int findPrime(String changedNum) {
		long[] candidates = Arrays.stream(changedNum.split("0"))
								 .filter(str -> !str.equals(""))
								 .mapToLong(Long::parseLong)
								 .toArray();
		System.out.println(Arrays.toString(candidates));

		int answer = 0;
		for (long candidate : candidates) {
			if (candidate != 1 && isPrime(candidate)) {
				answer++;
			}
		}

		return answer;
	}

	private static boolean isPrime(long candidate) {
		int sqrt = (int) Math.sqrt(candidate);
		for (int i = 2; i <= sqrt; i++) {
			if (candidate % i == 0 && candidate > 3) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
//		int n = 437674;
		int n = 110011;
//		int k = 3;
		int k = 10;
		System.out.println(solution(n, k));
	}
}
