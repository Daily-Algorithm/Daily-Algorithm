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
 * stringbuilder append랑 string +연산이 왜 시간차이가 날까?
 */
public class BJ13913_숨바꼭질4 {
	static FastReader scan = new FastReader();
	static int subin, sister, move;
	static String path = "";
	static Set<Integer> visited = new HashSet<>();
	public static void main(String[] args) {
		input();
		BFS();
		System.out.println(move);
		System.out.println(path);
	}

	private static void BFS() {
		if (subin < sister) {
			Queue<Point> queue = new LinkedList<>();
			Point subinP = new Point(subin, 0, new StringBuilder(String.valueOf(subin)));
			queue.add(subinP);
			visited.add(subinP.x);
			while (!queue.isEmpty()) {
				Point curP = queue.poll();
				if (curP.x == sister) {
					move = curP.move;
					path = curP.path.toString();
					return;
				}
				Point rightP = new Point(
					curP.x + 1,
					curP.move + 1,
					new StringBuilder(curP.path).append(" ").append(curP.x + 1)
				);
				if (isValid(rightP)) {
					queue.add(rightP);
					visited.add(rightP.x);
				}
				Point leftP = new Point(
					curP.x - 1,
					curP.move + 1,
					new StringBuilder(curP.path).append(" ").append(curP.x - 1)
				);
				if (isValid(leftP)) {
					queue.add(leftP);
					visited.add(leftP.x);
				}
				Point twiceP = new Point(
					curP.x * 2,
					curP.move + 1,
					new StringBuilder(curP.path).append(" ").append(curP.x * 2)
				);
				if (isValid(twiceP)) {
					queue.add(twiceP);
					visited.add(twiceP.x);
				}
			}
		} else if (subin > sister) {
			move = subin - sister;
			StringBuilder sb = new StringBuilder();
			for (int i = subin; i > sister; i--) {
				sb.append(i).append(" ");
			}
			sb.append(sister);
			path = sb.toString();
		} else {
			move = 0;
			path = String.valueOf(subin);
		}
	}

	private static boolean isValid(Point rightP) {
		return rightP.x >= 0 && rightP.x <= 100000 && !visited.contains(rightP.x);
	}

	static void input(){
		subin = scan.nextInt();
		sister = scan.nextInt();
	}

	private static class Point {
		int x, move;
		StringBuilder path;
		public Point(int x, int move, StringBuilder path) {
			this.x = x;
			this.move = move;
			this.path = path;
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
