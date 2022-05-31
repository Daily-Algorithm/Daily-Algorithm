package 구현;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PG67258_보석쇼핑_F01 {
	static Info answer;
	static Map<String, List<Integer>> map = new HashMap<>();

	public static int[] solution(String[] gems) {
		answer = new Info(new int[]{0, gems.length - 1});
		for (int idx = 0; idx < gems.length; idx++) {
			if (!map.containsKey(gems[idx])) {
				map.put(gems[idx], new ArrayList<>());
				map.get(gems[idx]).add(idx);
			} else {
				map.get(gems[idx]).add(idx);
			}
		}
		comb(new int[2], gems.length, 2, 0, 0);
		return new int[]{answer.scope[0] + 1, answer.scope[1] + 1};
	}

	private static void comb(int[] arr, int n, int r, int index, int target) {
		if (r == 0) {
			int[] cur = arr.clone();
			if (isAble(cur)) {
				findMin(new Info(cur));
			}
			return;
		}
		if (target == n) return;
		arr[index] = target;
		comb(arr, n, r - 1, index + 1, target);//뽑는경우
		comb(arr, n, r, index, target + 1);//안뽑는경우
	}

	private static boolean isAble(int[] arr) {
		for (List<Integer> list : map.values()) {
			boolean found = false;
			for (Integer idx : list) {
				if (arr[0] <= idx && idx <= arr[1]) {
					found = true;
					break;
				}
			}
			if (!found) {
				return false;
			}
		}
		return true;
	}

	private static void findMin(Info info) {
		if (answer.length > info.length) {
			answer = info;
		}
	}

	public static void main(String[] args) {
//		String[] gems = new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
//		String[] gems = new String[]{"AA", "AB", "AC", "AA", "AC"};
//		String[] gems = new String[]{"XYZ", "XYZ", "XYZ"};
		String[] gems = new String[]{"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
		System.out.println(Arrays.toString(solution(gems)));
	}

	private static class Info {
		int[] scope;
		int length;

		public Info(int[] scope) {
			this.scope = scope;
			this.length = scope[1] - scope[0] + 1;
		}
	}
}
