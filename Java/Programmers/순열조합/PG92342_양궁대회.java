package 순열조합;

import java.util.Arrays;

/**
 * 중복조합
 */
public class PG92342_양궁대회 {
	static int trial;
	static int[] apitch = new int[11];
	static int diff = -1;
	static int lionP = -1;
	static int[] answer = new int[11];
	public static int[] solution(int n, int[] info) {
		apitch = info;
		trial = n;
		int[] initial = new int[11];

		combination(initial, 0, 0);
		return diff == -1 ? new int[]{-1} : answer;
	}

	private static void combination(int[] arr, int dept, int idx) {
		if (dept == trial) {
			countDiff(arr);
			return;
		}
		for (int i = idx; i < 11; i++) {
			int[] temp = arr.clone();
			temp[i] += 1;
			combination(temp, dept + 1, i);
		}
	}

	private static void countDiff(int[] arr) {
		// 더 큰 점수차 확인
		int apitchScore = 0;
		int lionScore = 0;

		for (int idx = 0; idx < 11; idx++) {
			int score = 10 - idx;
			int apitchShots = apitch[idx];
			int lionShots = arr[idx];
			if (apitchShots != 0 || lionShots != 0) {
				if (apitchShots >= lionShots) {
					apitchScore += score;
				} else {
					lionScore += score;
				}
			}
		}
		int diffOfArr = lionScore - apitchScore;
		if (diffOfArr > 0) {
			if (diff < diffOfArr || (diff == diffOfArr && moreLessScore(answer, arr))) {
				diff = diffOfArr;
				lionP = lionScore;
				answer = arr;
			}
		}
	}

	private static boolean moreLessScore(int[] answer, int[] arr) {
		for (int i = 10; i >= 0; i--) {
			if (answer[i] > arr[i]) {
				return false;
			} else if (answer[i] < arr[i]) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int n = 5;
		int[] info = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
		System.out.println(Arrays.toString(solution(n, info)));
	}
}
