package 모든문제;

import java.util.ArrayList;
import java.util.List;

public class PG87390_n제곱배열자르기 {

	public static List<Long> solution(int n, long left, long right) {
		List<Long> answer = new ArrayList<>();
		for (long idx = left; idx <= right; idx++) {
			long idxX = idx % n;
			long idxY = idx / n;
			answer.add(Math.max(idxX, idxY) + 1);
		}
		return answer;
	}


	public static void main(String[] args) {
//		int n = 3;
		int n = 4;
//		int left = 2;
		long left = 7;
//		int right = 5;
		long right = 14;
		System.out.println(solution(n, left, right));
	}
}
