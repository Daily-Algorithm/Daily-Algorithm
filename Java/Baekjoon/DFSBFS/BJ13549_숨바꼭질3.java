package DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * cur.x * 2를 먼저 해야하는 건 알겠는데 왜 cur.x - 1를 cur.x + 1보다 먼저 해야되는 건지는 모르겠다.
 */
public class BJ13549_숨바꼭질3 {
	static FastReader scan = new FastReader();
	static int subin, sister;
	static Queue<Point> queue = new LinkedList<>();
	static Set<Integer> visited = new HashSet<>();
	static int answer;

	public static void main(String[] args) {
		input();
		BFS();
		System.out.println(answer);
	}

	private static void BFS() {
		Point first = new Point(subin, 0);
		queue.add(first);
		visited.add(first.x);
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			if (cur.x == sister) {
				answer = cur.move;
				return;
			}
			Point[] arr = new Point[]{
				new Point(cur.x * 2, cur.move),
				new Point(cur.x - 1, cur.move + 1),
				new Point(cur.x + 1, cur.move + 1),
				};
			for (Point next : arr) {
				if (isValid(next)) {
					queue.add(next);
					visited.add(next.x);
				}
			}
		}
	}

	private static boolean isValid(Point next) {
		return next.x >= 0 && next.x <= 100000 && !visited.contains(next.x);
	}

	static void input(){
		subin = scan.nextInt();
		sister = scan.nextInt();
	}

	static class Point {
		int x, move;

		public Point(int x, int move) {
			this.x = x;
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
					st = new StringTokenizer(br.readLine(), " ");
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
