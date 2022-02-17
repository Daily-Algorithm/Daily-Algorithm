package Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 헤맨 부분 : 1. 정렬 안 된 테케, 2. 본인 꺼 도난당한 사람
 *
 * 정렬 안 된 입출력 예도 보여주던가 다 정렬된 예제만 보여줘놓고 어이없네
 */
public class PG체육복 {
	public static int solution(int n, int[] lost, int[] reserve) {
		Arrays.sort(lost);
		Arrays.sort(reserve);

		List<Integer> lostL = new ArrayList<>();
		for (int person : lost) {
			lostL.add(person);
		}

		List<Integer> reserveL = new ArrayList<>();
		for (int person : reserve) {
			reserveL.add(person);
		}

		for (Integer person : lost) {
			if (reserveL.contains(person)) {
				lostL.remove(person);
				reserveL.remove(person);
			}
		}

		int notMatched = lostL.size();

		for (int person : lostL) {
			Integer small = person - 1;
			Integer big = person + 1;

			if (reserveL.contains(small)) {
				reserveL.remove(small);
				notMatched--;
			} else if (reserveL.contains(big)) {
				reserveL.remove(big);
				notMatched--;
			}
		}

		return n - notMatched;
	}

	public static void main(String[] args) {
		int n = 5;
		int[] lost = {2, 3, 4};
		int[] reserve = {3, 4, 5};
		System.out.println(solution(n, lost, reserve));
	}
}
