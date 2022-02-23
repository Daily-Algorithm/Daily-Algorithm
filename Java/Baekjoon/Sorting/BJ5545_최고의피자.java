package Sorting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BJ5545_최고의피자 {

	static int N, DOUGH, TOPPING, DOUGHCAL;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		DOUGH = sc.nextInt();
		TOPPING = sc.nextInt();
		DOUGHCAL = sc.nextInt();

		List<Integer> calories = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			calories.add(sc.nextInt());
		}
		Collections.sort(calories, Collections.reverseOrder());
		List<Integer> calPerWon = new ArrayList<>();
		calPerWon.add(DOUGHCAL / DOUGH);

		int toppingCal = 0;
		int money = DOUGH;
		for (int i = 0; i < N; i++) {
			toppingCal += calories.get(i);
			money += TOPPING;
			int calorie = DOUGHCAL + toppingCal;
			calPerWon.add(calorie / money);
		}
		Collections.sort(calPerWon,Collections.reverseOrder());
		System.out.println(calPerWon.get(0));
	}
}
