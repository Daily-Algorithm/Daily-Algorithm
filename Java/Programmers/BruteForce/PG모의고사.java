package BruteForce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PG모의고사 {
	public static List<Integer> solution(int[] answers) {
		List<Integer> answerL = new ArrayList<>();
		int[] one = {1, 2, 3, 4, 5};
		int[] two = {2, 1, 2, 3, 2, 4, 2, 5};
		int[] three = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

		int oneScore = 0;
		int twoScore = 0;
		int threeScore = 0;

		for (int i = 0; i < answers.length; i++) {
			if (answers[i] == one[i % one.length]) {
				oneScore++;
			}
			if (answers[i] == two[i % two.length]) {
				twoScore++;
			}
			if (answers[i] == three[i % three.length]) {
				threeScore++;
			}
		}

		List<Integer> scores = List.of(oneScore, twoScore, threeScore);
		int max = Collections.max(scores);

		if (oneScore == max) {
			answerL.add(1);
		}
		if (twoScore == max) {
			answerL.add(2);
		}
		if (threeScore == max) {
			answerL.add(3);
		}

		return answerL;
	}

}
