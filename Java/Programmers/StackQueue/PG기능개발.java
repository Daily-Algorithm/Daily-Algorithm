package StackQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PG기능개발 {
	public static List<Integer> solution(int[] progresses, int[] speeds) {
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 0; i < progresses.length; i++) {
			queue.add(daysTilDist(progresses[i], speeds[i]));
		}

		List<Integer> answer = new ArrayList<>();

		int count;
		while (!queue.isEmpty()) {
			count = 1;
			int poll = queue.poll();
			while (queue.peek() != null && poll >= queue.peek()) {
				count++;
				queue.poll();
			}
			answer.add(count);
		}

		return answer;
	}


	static int daysTilDist(int progress, int speed) {
		int leftPercent = 100 - progress;
		int divDays = leftPercent / speed;
		int modDays = leftPercent % speed;
		if (modDays > 0) {
			return divDays + 1;
		} else {
			return divDays;
		}
	}

	public static void main(String[] args) {
		int[] progresses = {93, 30, 55};
		int[] speeds = {1, 30, 5};
		System.out.println(solution(progresses, speeds));
	}
}
