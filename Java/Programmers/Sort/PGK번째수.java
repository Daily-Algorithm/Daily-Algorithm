package Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Arrays.copyOfRange(배열, 시작 인덱스, 끝 인덱스 + 1)
 */

public class PGK번째수 {

	public static List<Integer> solution(int[] array, int[][] commands) {
		List<Integer> answer = new ArrayList<>();

		for (int[] command : commands) {
			int start = command[0]-1;
			int end = command[1];
			int nth = command[2]-1;
			int[] chopped = Arrays.copyOfRange(array, start, end);
			Arrays.sort(chopped);
			answer.add(chopped[nth]);
		}

		return answer;

	}

	public static void main(String[] args) {

		int[] arrays = {1, 5, 2, 6, 3, 7, 4};
		int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
		System.out.println(solution(arrays, commands));
	}
}
