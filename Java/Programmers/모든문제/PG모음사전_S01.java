package 모든문제;

import java.util.*;

/**
 * 한번 확인해볼 풀이
 */
public class PG모음사전_S01 {
	class Solution {
		List<String> list = new ArrayList<>();
		void dfs(String str, int len) {
			if(len > 5) return;
			list.add(str);
			for(int i = 0; i < 5; i++) dfs(str + "AEIOU".charAt(i), len + 1);
		}
		public int solution(String word) {
			dfs("", 0);
			return list.indexOf(word);
		}
	}
}
