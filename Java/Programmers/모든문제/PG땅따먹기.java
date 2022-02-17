package 모든문제;

import java.util.Arrays;

/**
 * 아오 힘들어
 */

public class PG땅따먹기 {
	static int solution(int[][] land) {
		for (int i = 1; i < land.length; i++) {
			int[] currentLine = land[i];
			int[] beforeLine = land[i - 1];
			for (int j = 0; j < 4; j++) {
				currentLine[j] = currentLine[j] + maxOfOther(j, beforeLine);
			}
		}

		int[] lastLine = land[land.length-1];
		Arrays.sort(lastLine);

		return lastLine[lastLine.length-1];
	}

	static int maxOfOther(int index, int[] arr) {
		int[] clone = arr.clone();
		clone[index] = 0;
		Arrays.sort(clone);
		return clone[clone.length-1];
	}

	public static void main(String[] args) {
		int[][] land = {{1, 2, 3, 5}, {5, 6, 7, 8}, {4, 3, 2, 1}};
		System.out.println(solution(land));
	}
}
