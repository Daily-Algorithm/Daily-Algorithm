package 모든문제;

import java.util.Stack;

public class PG81303_표편집 {
	static Stack<Integer> removedS = new Stack<>();
	static int tableSize;
	static int pointIdx;

	public static String solution(int n, int k, String[] cmd) {
		initialize(n, k);
		for (String command : cmd) {
			parseCmdAndDo(command);
		}
		return makeAnswer(n);
	}

	private static String makeAnswer(int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tableSize; i++) {
			sb.append("O");
		}
		while (!removedS.isEmpty()) {
			Integer pop = removedS.pop();
			System.out.println(pop);
			sb.insert(pop.intValue(), "X");
		}
		return sb.toString();
	}

	private static void parseCmdAndDo(String command) {
		char c = command.charAt(0);
		if (c == 'U') {
			pointIdx -= Integer.parseInt(command.substring(2));
		} else if (c == 'D') {
			pointIdx += Integer.parseInt(command.substring(2));
		} else if (c == 'C') {
			removedS.add(pointIdx);
			tableSize--;
			if (pointIdx == tableSize) {
				pointIdx--;
			}
		} else if (c == 'Z') {
			// 추가
			if (removedS.pop() <= pointIdx) {
				pointIdx++;
			}
			tableSize++;
		}
	}

	private static void initialize(int n, int k) {
		tableSize = n;
		pointIdx = k;
	}

	public static void main(String[] args) {
		int n = 8;
		int k = 2;
//		String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
		String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "U 1", "C"};
		System.out.println(solution(n, k, cmd));
	}
}
