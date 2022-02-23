package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Stream;

public class BJ1449_수리공항승 {

	public static void main(String[] args) throws IOException {
		TreeSet<Integer> holes = new TreeSet<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine()
						   .split(" ");
		Integer holeNum = Integer.valueOf(split[0]);
		Integer tapeLength = Integer.valueOf(split[1]);

		List<Integer> holesList = Arrays.asList(Stream.of(br.readLine()
														   .split(" "))
													 .mapToInt(Integer::parseInt)
													 .boxed()
													 .toArray(Integer[]::new));

		holes.addAll(holesList);

		Integer num = 0;
		Integer standard = holes.first();
		while (standard < holes.last()) {
			num++;
			if (holes.higher(standard + tapeLength - 1) == null) {
				standard = holes.last() + 1;
				break;
			} else {
				standard = holes.higher(standard + tapeLength - 1);
			}
		}

		if (standard == holes.last()) {
			System.out.println(num + 1);
		} else {
			System.out.println(num);
		}
	}
}
