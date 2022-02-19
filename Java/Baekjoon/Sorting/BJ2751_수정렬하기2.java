package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Collections.sort() < Arrays.sort()
 */
public class BJ2751_수정렬하기2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer N = Integer.parseInt(br.readLine());
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(br.readLine()));
		}
		Collections.sort(list);

		StringBuilder sb = new StringBuilder();
		for (Integer num : list) {
			sb.append(String.valueOf(num) + "\n");
		}
		System.out.println(sb.toString());
	}
}
