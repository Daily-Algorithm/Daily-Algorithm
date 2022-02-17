package Greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PG구명보트_F01 {
	public static int solution(int[] people, int limit) {
		int answer = 0;

		// 남은 사람들 리스트 left
		List<Integer> left = Arrays.stream(people)
								   .boxed()
								   .collect(Collectors.toList());
		Collections.sort(left);

		while (left.size() > 1) {
			int light = left.get(0);
			int leftWeight = limit - light;
			if (leftWeight < light) {
				// light 부터 left에 있는 모든 사람들을 한 보트에 태워서 보내기
				answer += left.size();
				break;
			} else {
				for (int i = left.size() - 1; i > 0; i--) {
					if (leftWeight >= left.get(i)) {
						left.remove(i);
						break;
					}
				}
				answer++;
				left.remove(0);
				// 얘를 줄이면 될 것 같음
			}
			if (left.size() == 1) {
				answer ++;
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		int[] people = {70, 50, 80, 50};
		int limit = 100;
		System.out.println(solution(people, limit));
	}
}
