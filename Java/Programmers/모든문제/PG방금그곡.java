package 모든문제;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PG방금그곡 {

	static List<String> fullLyrics = new ArrayList<>();
	static List<String> names = new ArrayList<>();
	static int answerIdx = -1;
	public static String solution(String m, String[] musicinfos) {
		String trimM = trim(m);
		Arrays.stream(musicinfos).forEach(str -> complete(str, trimM));
		findAnswer(trimM);
		return answerIdx == -1 ? "(None)" : names.get(answerIdx);
	}

	private static String trim(String m) {
		return m.replaceAll("C#", "3")
				.replaceAll("D#", "4")
				.replaceAll("F#", "5")
				.replaceAll("A#", "1");
	}

	private static void findAnswer(String m) {
		for (int i = 0; i < fullLyrics.size(); i++) {
			String lyric = fullLyrics.get(i);
			if (lyric.contains(m)) {
				if (answerIdx == -1) {
					answerIdx = i;
				} else {
					if (names.get(answerIdx).length() < names.get(i).length()) {
						answerIdx = i;
					}
				}
			}
		}
	}

	private static void complete(String str, String m) {
		String[] split = str.split(",");
		String[] start = split[0].split(":");
		Integer startT = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
		String[] end = split[1].split(":");
		Integer endT = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);
		Integer time = endT - startT;
		String name = split[2];
		String[] lyrics = trim(split[3]).split("");

		StringBuilder sb = new StringBuilder();
		System.out.println(time);
		while (time > 0) {
			sb.append(lyrics[sb.length() % lyrics.length]);
			time--;
		}

		fullLyrics.add(sb.toString());
		names.add(name);
	}

	public static void main(String[] args) {
//		String m = "ABCDEFG";
		String m = "CDEFGAC";
//		String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
		String[] musicinfos = {"12:00,12:06,HELLO,CDEFGA"};
		System.out.println(solution(m, musicinfos));
	}
}
