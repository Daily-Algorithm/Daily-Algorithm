package DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ14226_이모티콘 {
	static FastReader scan = new FastReader();
	static int finalEmoji;
	static Set<String> visited = new HashSet<>();
	public static void main(String[] args) {
		input();
		BFS();
	}

	private static void BFS() {
		// 1부터 시작하고, 연산은 클립보드에 저장/붙여넣기/이모티콘 중 하나 삭제
		Queue<Screen> queue = new LinkedList<>();
		Screen first = new Screen(1, 0, 0);
		queue.add(first);
		visited.add(screenStr(first));
		while (!queue.isEmpty()) {
			Screen curS = queue.poll();
			if (curS.emoji == finalEmoji) {
				System.out.println(curS.move);
				return;
			}
			Screen save = new Screen(curS.emoji, curS.emoji, curS.move + 1);
			if (!visited.contains(screenStr(save))) {
				queue.add(save);
				visited.add(screenStr(save));
			}
			if (curS.clipboard != 0) {
				Screen paste = new Screen(curS.emoji + curS.clipboard, curS.clipboard, curS.move + 1);
				if (!visited.contains(screenStr(paste))) {
					queue.add(paste);
					visited.add(screenStr(paste));
				}
			}
			if (curS.emoji >= 3) {
				Screen minus = new Screen(curS.emoji - 1, curS.clipboard, curS.move + 1);
				if (!visited.contains(screenStr(minus))) {
					queue.add(minus);
					visited.add(screenStr(minus));
				}
			}
		}
	}

	private static String screenStr(Screen first) {
		return first.emoji + String.valueOf(first.clipboard);
	}

	static void input(){
		finalEmoji = scan.nextInt();
	}

	private static class Screen {
		int emoji, clipboard, move;

		public Screen(int emoji, int clipboard, int move) {
			this.emoji = emoji;
			this.clipboard = clipboard;
			this.move = move;
		}
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;
		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
