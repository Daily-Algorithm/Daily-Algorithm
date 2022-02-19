package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class BJ10816_숫자카드2 {

	public static void main(String[] args) throws IOException {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer cards = Integer.parseInt(br.readLine());
		String[] cardsArr = br.readLine()
							  .split(" ");

		for (String card: cardsArr) {
			Integer cardInt = Integer.valueOf(card);
			if (map.containsKey(cardInt)) {
				map.put(cardInt, map.get(cardInt) + 1);
			} else {
				map.put(cardInt, 1);
			}
		}

		Integer nums = Integer.valueOf(br.readLine());
		String[] findingCards = br.readLine()
								  .split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < nums; i++) {
			Integer findingCard = Integer.parseInt(findingCards[i]);
			if (map.containsKey(findingCard)) {
				sb.append(map.get(findingCard) + " ");
			} else {
				sb.append("0 ");
			}
		}

		System.out.println(sb.deleteCharAt(sb.length()-1).toString());
	}
}
