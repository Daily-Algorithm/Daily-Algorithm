package 모든문제;

/**
 * 실패 : 문제대로 직관적이게 풂. 수를 점점 늘려가면서 다른 비트의 개수를 찾았음.
 */
public class PG2개이하로다른비트_F01 {
	public static long[] solution(long[] numbers) {
		long[] answer = new long[numbers.length];

		for (int i = 0; i < numbers.length; i++) {
			answer[i] = calculate(numbers[i]);
		}

		return answer;
	}

	static Long calculate(long number) {
		String standardBit = Long.toBinaryString(number);

		long large = number + 1;
		while (true) {
			// 비트 다른 자리수 계산
			String largeBit = Long.toBinaryString(large);
			standardBit = addZero(standardBit, largeBit.length() - standardBit.length());
			String[] standardBitArr = standardBit.split("");
			String[] largeBitArr = largeBit.split("");

			int diff = 0;
			for (int i = 0; i < largeBitArr.length; i++) {
				if (!largeBitArr[i].equals(standardBitArr[i])) {
					diff++;
				}
				if (diff == 3) {
					break;
				}
			}

			if (diff <= 2) {
				// 만약 1, 2개가 다르면 break
				return large;
			} else {
				// 3개 이상이 다르면 large ++
				large++;
			}
		}
	}

	static String addZero(String standardBit, int num) {
		for (int i = 0; i < num; i++) {
			standardBit = "0" + standardBit;
		}
		return standardBit;
	}

	public static void main(String[] args) {
		long[] numbers = {2,7};
		System.out.println(solution(numbers));
	}
}
