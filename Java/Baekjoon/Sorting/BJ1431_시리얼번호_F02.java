package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class BJ1431_시리얼번호_F02 {

	public static void main(String[] args) throws IOException {
		List<String> strings = new ArrayList<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer nums = Integer.valueOf(br.readLine());
		for (int i = 0; i < nums; i++) {
			strings.add(br.readLine());
		}
		Collections.sort(strings, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() == o2.length()) {
					if (sumCompare(o1,o2)==0) {
						return o1.compareTo(o2);
					} else {
						return sumCompare(o1, o2);
					}
				} else {
					return o1.length() - o2.length();
				}
			}
		});

		strings.stream().forEach(System.out::println);
	}

	static Integer sumCompare(String a, String b) {
		String aWithoutStr = a.replaceAll("[^0-9]", "");
		String bWithoutStr = b.replaceAll("[^0-9]", "");
		if (aWithoutStr.equals("") || bWithoutStr.equals("")) {
			return 0;
		} else {
			Integer aSum = Stream.of(aWithoutStr.split(""))
								 .mapToInt(Integer::parseInt)
								 .sum();
			Integer bSum = Stream.of(aWithoutStr.split(""))
								 .mapToInt(Integer::parseInt)
								 .sum();

			return aSum - bSum;
		}
	}
}
