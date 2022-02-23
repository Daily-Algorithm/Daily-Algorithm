package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BJ12845_모두의마블 {

	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		List<Integer> cards = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			cards.add(Integer.parseInt(st.nextToken()));
		}

		int maxIdx = findMaxIdx(cards);
		int level = cards.get(maxIdx);
		int gold = level * (N - 1) + sumExceptMax(cards, maxIdx);

		System.out.println(gold);
	}

	static int sumExceptMax(List<Integer> cards, int maxIdx) {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			if (i != maxIdx) {
				sum += cards.get(i);
			}
		}
		return sum;
	}

	static int findMaxIdx(List<Integer> cards) {
		List<Integer> copy = new ArrayList<>(cards);
		copy.sort(Comparator.reverseOrder());
		return cards.indexOf(copy.get(0));
	}
}
