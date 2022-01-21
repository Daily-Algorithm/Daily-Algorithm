package StackQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// TODO: 2022-01-20 location 나오면 while break하고 return
//  {[index1, priority1], [index2, priority2]}

public class PG프린터 {
	public static int solution(int[] priorities, int location) {
		Queue<Integer> priorityQ = new LinkedList<>();
		Queue<Integer> indexQ = new LinkedList<>();
		List<Integer> print = new ArrayList<>();

		for (int i = 0; i < priorities.length; i++) {
			priorityQ.add(priorities[i]);
			indexQ.add(i);
		}

		while (!priorityQ.isEmpty()) {
			int wait = priorityQ.poll();
			int waitIndex = indexQ.poll();

			if (!priorityQ.isEmpty() && Collections.max(priorityQ) > wait) {
				priorityQ.add(wait);
				indexQ.add(waitIndex);
			} else {
				print.add(waitIndex);
			}

		}

		return print.indexOf(location) + 1;
	}

	public static void main(String[] args) {
		int[] priorities = {1, 1, 9, 1, 1, 1};
		int location = 0;

		System.out.println(solution(priorities, location));
	}
}
