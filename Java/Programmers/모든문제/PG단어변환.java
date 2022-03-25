package 모든문제;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;



public class PG단어변환 {
	static Map<String, List<String>> adjMap = new HashMap<>();
	static int answer = 0;
	private static class bfsS {
		String str; int dist;
		public bfsS(String str, int dist) {
			this.str = str;
			this.dist = dist;
		}
	}

	public static int solution(String begin, String target, String[] words) {
		if (!makeAdj(words, begin) || !adjMap.containsKey(target)) {
			return 0;
		} else {
			bfs(begin, target);
			return answer;
		}
	}

	private static boolean makeAdj(String[] words, String begin) {
		for (String word : words) {
			if (oneDiff(begin, word)) {
				putIntoMap(begin, word);
				putIntoMap(word, begin);
			}
		}
		if (!adjMap.containsKey(begin)) {
			return false;
		} else {
			for (int i = 0; i < words.length; i++) {
				for (int j = i + 1; j < words.length; j++) {
					if (oneDiff(words[i], words[j])) {
						putIntoMap(words[i], words[j]);
						putIntoMap(words[j], words[i]);
					}
				}
			}
			return true;
		}
	}

	private static void putIntoMap(String A, String B) {
		if (adjMap.containsKey(A)) {
			List<String> list = adjMap.get(A);
			list.add(B);
		} else {
			List<String> list = new ArrayList<>();
			list.add(B);
			adjMap.put(A, list);
		}
	}

	private static void bfs(String start, String target) {
		Queue<bfsS> queue = new LinkedList<>();
		Map<bfsS, Boolean> visited = new HashMap<>();
		bfsS startS = new bfsS(start, 0);
		visited.put(startS, true);
		queue.add(startS);
		while (!queue.isEmpty()) {
			bfsS cur = queue.poll();
			if (cur.str.equals(target)) {
				answer = cur.dist;
				break;
			}
			Iterator<String> adjs = adjMap.get(cur.str).iterator();
			while (adjs.hasNext()) {
				bfsS next = new bfsS(adjs.next(), cur.dist + 1);
				if (!visited.containsKey(next)) {
					visited.put(next, true);
					queue.add(next);
				}
			}
		}
	}

	private static boolean oneDiff(String A, String B) {
		int diff = 1;
		String[] splitA = A.split("");
		String[] splitB = B.split("");
		for (int i = 0; i < splitA.length; i++) {
			if (!splitA[i].equals(splitB[i])) {diff--;}
			if (diff < 0) {return false;}
		}
		return true;
	}

	public static void main(String[] args) {
		String begin = "hit"; String target = "cog";
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
		System.out.println(solution(begin, target, words));
	}
}
