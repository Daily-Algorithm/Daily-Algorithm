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
 * 1. 주의점 : 조건에 start, end < 10^9이니까 long으로 받아줘야한다
 *
 * 2. refactoring :
 * - nextP인 4가지 P들을 for문으로 구현하면 중복코드를 줄일 수 있을 듯
 * - nextP 조건에 value범위를 추가하면 조금 더 빨라질 듯
 */
public class BJ14395_4연산 {
	static FastReader scan = new FastReader();
	static Queue<Point> queue = new LinkedList<>();
	static Set<Long> visited = new HashSet<>();
	static long start, end;

	public static void main(String[] args) {
		input();
		System.out.println(start == end ? 0 : BFS());
	}

	private static String BFS() {
		Point startP = new Point(start, "");
		processP(startP);
		while (!queue.isEmpty()) {
			Point curP = queue.poll();
			if (curP.value == end) {
				return curP.cal;
			}

			Point multipleP = new Point(curP.value * curP.value, curP.cal + "*");
			processP(multipleP);

			Point plusP = new Point(curP.value + curP.value, curP.cal + "+");
			processP(plusP);

			Point minusP = new Point(0, curP.cal + "-");
			processP(minusP);

			if (curP.value != 0) {
				Point dividedP = new Point(1, curP.cal + "/");
				processP(dividedP);
			}
		}
		return "-1";
	}

	private static void processP(Point p) {
		if (!visited.contains(p.value)) {
			visited.add(p.value);
			queue.add(p);
		}
	}

	static void input(){
		start = scan.nextInt();
		end = scan.nextInt();
	}

	private static class Point {
		long value;
		String cal;

		public Point(long cur, String cal) {
			this.value = cur;
			this.cal = cal;
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
