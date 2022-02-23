package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class BJ1431_시리얼번호_F01 {

	public static void main(String[] args) throws IOException {
		TreeMap<Integer, List<String>> map = new TreeMap<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer nums = Integer.valueOf(br.readLine());
		for (int i = 0; i < nums; i++) {
			String line = br.readLine();
			if (map.containsKey(line.length())) {
				List<String> strings = map.get(line.length());
				strings.add(line);
			} else {
				List<String> strings = new ArrayList<>();
				strings.add(line);
				map.put(line.length(), strings);
			}
		}

		Integer standard = map.firstKey();
		while (standard != null) {
			List<String> strings = map.get(standard);
			List<String> noNums = new ArrayList<>();
			List<String> withNums = new ArrayList<>();
			strings.stream().forEach(str -> {
				if (sum(str) > 0)
					withNums.add(str);
				else
					noNums.add(str);
			});

			Collections.sort(withNums, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					if (sum(o1) >= sum(o2)) {
						return 1;
					} else {
						return -1;
					}
				}
			});
			Collections.sort(noNums, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					if (o1.compareTo(o2) > 0) {
						return 1;
					} else {
						return -1;
					}
				}
			});

			if (noNums.size() == 0) {
				withNums.stream()
						.forEach(System.out::println);
			} else if (withNums.size() == 0) {
				noNums.stream()
					  .forEach(System.out::println);
			} else if (noNums.get(0)
							 .compareTo(withNums.get(0)) > 0) {
				withNums.stream()
						.forEach(System.out::println);
				noNums.stream()
					  .forEach(System.out::println);
			} else {
				noNums.stream()
					  .forEach(System.out::println);
				withNums.stream()
						.forEach(System.out::println);
			}
			standard = map.higherKey(standard);
		}
	}

	static int sum(String a) {
		String withoutStr = a.replaceAll("[^0-9]", "");
		if (withoutStr.equals("")) {
			return -1;
		} else {
			String[] withoutStrArr = withoutStr.split("");
			int sum = 0;
			for (int i = 0; i < withoutStrArr.length; i++) {
				sum += Integer.parseInt(withoutStrArr[i]);
			}
			return sum;
		}
	}
}