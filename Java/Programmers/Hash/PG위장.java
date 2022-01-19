package Hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PG위장 {
	public static int solution(String[][] clothes) {

		Map<String, List> closet = new HashMap<>();

		for (int i = 0; i < clothes.length; i++) {
			if (closet.containsKey(clothes[i][1])) {
				List<String> newlist = closet.get(clothes[i][1]);
				newlist.add(clothes[i][0]);
				closet.put(clothes[i][1], newlist);
			} else {
				List<String> newlist = new ArrayList<>();
				newlist.add(clothes[i][0]);
				closet.put(clothes[i][1], newlist);
			}
		}
		int[] kinds = new int[closet.keySet().size()];
		int i = 0;
		for (String key: closet.keySet()) {
			kinds[i] = closet.get(key).size();
			i++;
		}

		int answer = 1;
		for (int kind:kinds) {
			answer *= (kind+1);
		}
		return answer-1;
	}

	public static void main(String[] args) {
		String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"},
			{"green_turban", "headgear"}};

		System.out.println(solution(clothes));

	}
}
