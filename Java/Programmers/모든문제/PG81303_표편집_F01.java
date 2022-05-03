package 모든문제;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 시간초과, 런타임에러
 */
public class PG81303_표편집_F01 {
	static String[] firstIdArr;
	static List<String> processL;
	static int pointIdx;
	static Set<String> oxSet = new HashSet<>();	// id
	static Stack<Row> removedS = new Stack<>();

	public static String solution(int n, int k, String[] cmd) {
		initialize(n, k);
		for (String command : cmd) {
			parseCmdAndDo(command);
		}
		return makeAnswer(n);
	}

	private static String makeAnswer(int n) {
		StringBuilder sb = new StringBuilder();
		for (int id = 0; id < n; id++) {
			if (oxSet.contains(String.valueOf(id))) {
				sb.append("O");
			} else {
				sb.append("X");
			}
		}
		System.out.println(processL);
		System.out.println(pointIdx);
		return sb.toString();
	}

	private static void parseCmdAndDo(String command) {
		String[] split = command.split("");
		System.out.println("Cmd : " + command);
		System.out.println("- CurIdx : " + pointIdx);
		if (split[0].equals("U")) {
			pointIdx -= Integer.parseInt(split[2]);
		} else if (split[0].equals("D")) {
			pointIdx += Integer.parseInt(split[2]);
		} else if (split[0].equals("C")) {
			// 삭제
			String removedId = processL.get(pointIdx);
			Row removed = new Row(pointIdx, removedId);
			processL.remove(pointIdx);
			removedS.push(removed);
			oxSet.remove(removedId);
			if (pointIdx == processL.size()) {
				pointIdx--;
			}
		} else if (split[0].equals("Z")) {
			// 추가
			Row adding = removedS.pop();
			processL.add(adding.idx, adding.id);
			oxSet.add(adding.id);
			if (adding.idx <= pointIdx) {
				pointIdx++;
			}
		}
	}

	private static void initialize(int n, int k) {
		firstIdArr = new String[n];
		for (int idx = 0; idx < n; idx++) {
			firstIdArr[idx] = String.valueOf(idx);
			oxSet.add(String.valueOf(idx));
		}
		processL = new ArrayList<>(Arrays.asList(firstIdArr));
		pointIdx = k;
	}

	public static void main(String[] args) {
		int n = 8;
		int k = 2;
//		String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
		String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};
		System.out.println(solution(n, k, cmd));
	}

	static class Row {
		int idx; String id;

		public Row(int idx, String id) {
			this.idx = idx;
			this.id = id;
		}
	}
}
