package Greedy;

import java.util.Arrays;
import java.util.List;

public class PG조이스틱_F01 {
	public static int solution(String name) {
		List<String> alphabet = Arrays.asList(
			new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
				"O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"});

		String[] splited = name.split("");
		List<Boolean> notA = Arrays.asList(new Boolean[splited.length]); // false = "A"

		// 상하 이동
		int UDCnt = 0;
		for (int i = 0; i < splited.length; i++) {
			if (!splited[i].equals("A")) {
				notA.set(i, true);
				int index = alphabet.indexOf(splited[i]);
				UDCnt += Math.min(index, 26 - index);
			} else {
				notA.set(i, false);
			}
		}

		// 좌우 이동
		int curr = 0;
		int move = 0;
		notA.set(curr, false);
		while (notA.contains(true)) {
			// 오른쪽 중에서 notA
			int L = -1;
			int R = -1;

			for (int i = curr + 1; i < notA.size(); i++) {
				if (notA.get(i)) {
					R = i;
					break;
				}
			}

			// 왼쪽 중에서 notA
			for (int i = curr - 1; i > 0; i--) {
				if (notA.get(i)) {
					L = i; break;
				}
			}

			if (L == -1) {
				move += Math.min(Math.abs(curr - R), name.length() - Math.abs(curr - R));
				curr = R;
			} else if (R == -1) {
				move += Math.min(Math.abs(curr - L), name.length() - Math.abs(curr - L));
				curr = L;
			} else {
				int straight = Math.abs(curr - R);
				int reverse = Math.abs(curr - L);
				if (straight <= reverse) {
					move += straight;
					curr = R;
				} else {
					move += reverse;
					curr = L;
				}
			}
			notA.set(curr, false);
		}

		System.out.println("UP DOWN : " + UDCnt);
		System.out.println("move : " + move);
		return UDCnt + move;
	}

	public static void main(String[] args) {
		System.out.println(solution("BBABAAAB"));
	}
}
