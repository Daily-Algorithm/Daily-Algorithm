package 모든문제;

import java.util.Arrays;

public class PGN개최소공배수 {
	public static int solution(int[] arr) {
		int answer = 0;
		Arrays.sort(arr);
		int max = arr[arr.length-1];
		int n = 1;

		boolean solved = false;
		while (!solved) {
			for (int i = 0; i < arr.length - 1; i++) {
				int nameoji = (max * n) % arr[i];
				if (nameoji != 0) {
					n++;
					break;
				} else if (i == arr.length - 2) {
					solved = true;
					answer = max * n;
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		int[] arr = {2, 6, 8, 14};
		System.out.println(solution(arr));
	}
}
