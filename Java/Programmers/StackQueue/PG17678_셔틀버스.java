package StackQueue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PG17678_셔틀버스 {
	static int ableBus, interval, limitP;
	static int busArrivedT = intoAbsoluteT("09:00");
	static int loadedP = 0;
	static Deque<Integer> dequeue;
	static int corn = 0;
	public static String solution(int n, int t, int m, String[] timetable) {

		List<Integer> absoluteTs = Arrays.stream(timetable)
										 .map(str -> intoAbsoluteT(str))
										 .sorted()
										 .collect(Collectors.toList());
		initialize(n, t, m, absoluteTs);
		int lastBusArriveT = busArrivedT + interval * (ableBus - 1);
		while (busArrivedT < lastBusArriveT) {
			// 마지막 버스 전까지 전부 태우기
			while (dequeue.peek() <= busArrivedT && loadedP < m) {
				// 버스 기다리는 사람 중 조건에 맞을 때까지 최대한 태우기
				dequeue.poll();
				loadedP += 1;
			}
			nextTurn();
		}

		if (lastBusArriveT < dequeue.peek()) {
			corn = lastBusArriveT;
		} else {
			int lastBusWaitingP = dequeue.size();
			if (limitP <= lastBusWaitingP) {
				// 콘을 끼워넣어야하는 상황
				corn = getRival() - 1;
			} else {
				// 널널하면
				corn = lastBusArriveT;
			}
		}

		return intoStr(corn);
	}

	private static String intoStr(int corn) {
		String hours = String.format("%02d", corn / 60);
		String minutes = String.format("%02d", corn % 60);
		return hours + ":" + minutes;
	}

	private static int getRival() {
		int p = 0;
		int lastP = 0;
		while (p < limitP) {
			lastP = dequeue.poll();
			p++;
		}
		return lastP;
	}

	private static void nextTurn() {
		loadedP = 0;
		busArrivedT += interval;
		ableBus--;
	}

	private static void initialize(
		int n,
		int t,
		int m,
		List<Integer> absoluteTs
	) {
		ableBus = n;
		interval = t;
		limitP = m;
		dequeue = new LinkedList<>(absoluteTs);
	}

	private static int intoAbsoluteT(String str) {
		String[] split = str.split(":");
		return 60 * Integer.parseInt(split[0]) + Integer.parseInt(split[1]);
	}

	public static void main(String[] args) {
//		setThis("1\t1\t5\t[\"08:00\", \"08:01\", \"08:02\", \"08:03\"]");
//		setThis("2\t10\t2\t[\"09:10\", \"09:09\", \"08:00\"]");
//		setThis("2\t1\t2\t[\"09:00\", \"09:00\", \"09:00\", \"09:00\"]");
//		setThis("1\t1\t5\t[\"00:01\", \"00:01\", \"00:01\", \"00:01\", \"00:01\"]");
//		setThis("1\t1\t1\t[\"23:59\"]");
		setThis("10\t60\t45\t[\"23:59\",\"23:59\", \"23:59\", \"23:59\", \"23:59\", \"23:59\", \"23:59\", \"23:59\", \"23:59\", \"23:59\", \"23:59\", \"23:59\", \"23:59\", \"23:59\", \"23:59\", \"23:59\"]");
	}

	private static void setThis(String s) {
		String[] split = s.split("\t");
		int n = Integer.parseInt(split[0]);
		int t = Integer.parseInt(split[1]);
		int m = Integer.parseInt(split[2]);
		String[] timtable = split[3].replaceAll("\\[", "")
									.replaceAll("]", "")
									.replaceAll("\"", "")
									.replaceAll(" \"", "")
									.replaceAll(" ", "")
									.split(",");
		System.out.println(solution(n, t, m, timtable));
	}
}
