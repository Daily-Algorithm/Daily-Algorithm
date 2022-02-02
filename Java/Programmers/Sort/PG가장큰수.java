package Sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * 1. String.valueOf(num)
 * 2. List -> Array : toArray(new String[arr.length])
 * 3. sort : compare override
 */
public class PG가장큰수 {

	public static String solution(int[] numbers) {
		String[] sortedStrArr = sortedStrArr(numbers);

		if (sortedStrArr[0].equals("0")) {
			return "0";
		} else {
			StringBuilder answer = new StringBuilder();
			for (String str : sortedStrArr) {
				answer.append(str);
			}
			return answer.toString();
		}
	}

	static String[] sortedStrArr(int[] arr) {
		String[] strArr = Arrays.stream(arr)
								 .mapToObj(num -> String.valueOf(num))
								 .collect(Collectors.toList())
								 .toArray(new String[arr.length]);

		Arrays.sort(strArr, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return (s2+s1).compareTo(s1+s2);
			}
		});
		return strArr;
	}

	public static void main(String[] args) {
		int[] strings = {0, 0};

		System.out.println(solution(strings));
	}
}
