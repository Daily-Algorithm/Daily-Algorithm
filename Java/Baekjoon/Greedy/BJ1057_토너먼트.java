package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1057_토너먼트 {

	static int N, KIM, IM;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine()
					   .split(" ");
		N = Integer.parseInt(split[0]);
		KIM = Integer.parseInt(split[1]);
		IM = Integer.parseInt(split[2]);

		int count = 0;
		while (KIM != IM) {
			KIM = (int) Math.ceil((double) KIM / 2);
			IM = (int) Math.ceil((double) IM / 2);
			count++;
		}
		System.out.println(count);
	}
}
