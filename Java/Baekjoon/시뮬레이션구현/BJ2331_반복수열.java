package 구현;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

public class BJ2331_반복수열 {
	static Long start;
	static int squared;
	static LinkedHashMap<Long, Boolean> map = new LinkedHashMap();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		start = Long.valueOf(st.nextToken());
		squared = Integer.parseInt(st.nextToken());
		Long before = start;
		while (!map.containsKey(before)) {
			map.put(before, true);
			Long nextNum = nextNum(before);
			before = nextNum;
		}
		map.put(before, false);
		out.println(leftNums());
	}

	private static int leftNums() {
		int i = 0;
		for (Long num : map.keySet()) {
			if (map.get(num)) {
				i++;
			} else {
				break;
			}
		}
		return i;
	}

	private static Long nextNum(Long before) {
		String[] split = before.toString().split("");
		Long sum = 0L;
		for (String seat : split) {
			sum += (long) Math.pow(Long.parseLong(seat), squared);
		}
		return sum;
	}
}
