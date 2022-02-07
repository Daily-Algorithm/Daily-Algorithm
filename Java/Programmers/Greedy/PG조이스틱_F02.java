package Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PG조이스틱_F02 {
	public static int solution(String name) {
		List<String> alphabet = Arrays.asList(
			new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
				"O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"});

		String[] splited = name.split("");
		List<Integer> notA = new ArrayList<>(); // false = "A"

		// 상하 이동
		int UDCnt = 0;
		for (int i = 0; i < splited.length; i++) {
			if (!splited[i].equals("A")) {
				notA.add(i);
				int index = alphabet.indexOf(splited[i]);
				UDCnt += Math.min(index, 26 - index);
			}
		}

		// 좌우 이동
		int seat = name.length();
		int move = 0;
		if(notA.get(0) != 0){notA.add(0,0);}
		while (notA.size() >= 2) {
			int front = notA.get(0);
			int next = notA.get(1);
			int back = notA.get(notA.size()-1);
			int straight = Math.abs(front - next);    // 4
			int reverse = seat - Math.abs(front - back);    // 1
			if (reverse < straight) {
				move += reverse;
				notA.remove(0);
				Collections.reverse(notA);
			} else {
				move += straight;
				notA.remove(0);
			}
		}

		return UDCnt + move;
	}

	public static void main(String[] args) {
		System.out.println(solution("AAABBBBBBBAA"));
	}
}
