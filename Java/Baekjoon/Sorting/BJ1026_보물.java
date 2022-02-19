package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class BJ1026_보물 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Integer[] A = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
		Integer[] B = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);

		Arrays.sort(A, Comparator.reverseOrder());
		Arrays.sort(B);

		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += A[i] * B[i];
		}
		System.out.println(sum);
	}
}
