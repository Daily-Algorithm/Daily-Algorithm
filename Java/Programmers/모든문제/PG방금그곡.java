package 모든문제;

public class PG방금그곡 {
	static String memory;
	static String answer = "";
	static Integer maxPlayT = 0;
	public static String solution(String m, String[] musicinfos) {
		memory = trim(m);
		for (String musicinfo : musicinfos) {
			findAnswer(musicinfo);
		}
		return answer.equals("") ? "(None)" : answer;
	}

	private static String trim(String m) {
		return m.replaceAll("C#", "3")
				.replaceAll("D#", "4")
				.replaceAll("F#", "5")
				.replaceAll("G#", "6")
				.replaceAll("A#", "1");
	}

	private static void findAnswer(String str) {
		String[] split = str.split(",");

		Integer playT = calculateT(split[0].split(":"), split[1].split(":"));
		String name = split[2];

		String[] lyrics = trim(split[3]).split("");
		StringBuilder fullLyric = new StringBuilder("");

		for (int i = 0; i < playT; i++) {
			fullLyric.append(lyrics[i % lyrics.length]);
		}

		if (fullLyric.toString().contains(memory) && playT > maxPlayT) {
			answer = name;
			maxPlayT = playT;
		}
	}

	private static Integer calculateT(String[] start, String[] end) {
		Integer startT = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
		Integer endT = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);
		return endT - startT;
	}

	public static void main(String[] args) {
//		String m = "ABCDEFG";
		String m = "ABCDE";
//		String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
		String[] musicinfos = {"03:00,03:05,FOOOO,ABCDEF", "03:00,03:05,HELLO,ABCDEF"};
		System.out.println(solution(m, musicinfos));
	}
}
