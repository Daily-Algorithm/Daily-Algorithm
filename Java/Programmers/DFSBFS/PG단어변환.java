package DFSBFS;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class PG단어변환 {
	static String start, end;
	static Set<String> wordSet;
	static int answer = 0;

	public static int solution(String begin, String target, String[] words) {
		start = begin;
		end = target;
		wordSet = new HashSet<>(Arrays.asList(words));

		BFS();
		return answer;
	}

	private static void BFS() {
		Queue<Word> queue = new LinkedList<>();
		Word first = new Word(start, start,0);
		queue.add(first);
		while (!queue.isEmpty()) {
			Word cur = queue.poll();
			if (cur.word.equals(end)) {
				answer = cur.count;
				return;
			}
			List<Word> collect = wordSet.stream()
										.map(str -> new Word(str, cur.word + " " + str, cur.count + 1))
										.filter(next -> isAdj(cur, next))
										.collect(Collectors.toList());
			if (!collect.isEmpty()) {
				queue.addAll(collect);
				collect.forEach(next -> wordSet.remove(next.word));
			}
		}
	}

	private static boolean isAdj(Word cur, Word next) {
		String[] curArr = cur.word.split("");
		String[] nextArr = next.word.split("");
		int count = 1;
		for (int i = 0; i < curArr.length; i++) {
			if (!curArr[i].equals(nextArr[i])) {
				count--;
			}
			if (count < 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String begin="hit"; String target = "cog";
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
//		String[] words = {"hot", "dot", "dog", "lot", "log"};
		System.out.println(solution(begin, target, words));
	}

	private static class Word {
		String word, path; int count;
		public Word(String word,String path, int count) {
			this.word = word;
			this.path = path;
			this.count = count;
		}
	}
}
