package 이진탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BJ2110_공유기설치_F01 {

	static int HOUSE_NUM, ROUTER;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		HOUSE_NUM = Integer.parseInt(st.nextToken());
		ROUTER = Integer.parseInt(st.nextToken());

		Long[] houses = new Long[HOUSE_NUM];
		for (int i = 0; i < HOUSE_NUM; i++) {
			houses[i] = Long.parseLong(br.readLine());
		}
		Arrays.sort(houses);

		Long minus = houses[0];
		Long max = (houses[HOUSE_NUM - 1] - minus) / (ROUTER - 1);
		List<Long> alphas = new ArrayList<>();
		for (int i = 0; i < HOUSE_NUM; i++) {
			alphas.add((houses[i] - minus) % max);
		}
		alphas.sort(Comparator.reverseOrder());

		System.out.println(alphas.get(ROUTER - 1));
	}
}
