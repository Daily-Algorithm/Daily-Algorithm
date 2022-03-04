package DFSBFS;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ9205_맥주마시면서걸어가기 {

	static int TEST;
	static List<Boolean> answers = new ArrayList<>();

	private static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		TEST = Integer.parseInt(br.readLine());
		for (int i = 0; i < TEST; i++) {
			int cvsNum = Integer.parseInt(br.readLine());
			boolean happy = false;
			List<Pos> list = new ArrayList<>();
			boolean[] visited = new boolean[cvsNum + 2];
			LinkedList<Integer>[] adj = new LinkedList[cvsNum + 2];
			for (int j = 0; j < cvsNum + 2; j++) {
				adj[j] = new LinkedList<>();
			}

			for (int j = 0; j < cvsNum + 2; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				list.add(new Pos(x, y));
			}

			for (int j = 0; j < cvsNum + 2; j++) {
				for (int k = j + 1; k < cvsNum + 2; k++) {
					if (dist(list.get(j), list.get(k)) <= 1000) {
						adj[j].add(k);
						adj[k].add(j);
					}
				}
			}

			Queue<Integer> queue = new LinkedList<>();
			int startIdx = 0;
			visited[startIdx] = true;
			queue.add(startIdx);
			while (!queue.isEmpty()) {
				int curIdx = queue.poll();
				if (curIdx == cvsNum + 1) {
					happy = true;
					break;
				}
				for (int adjIdx : adj[curIdx]) {
					if (!visited[adjIdx]) {
						visited[adjIdx] = true;
						queue.add(adjIdx);
					}
				}
			}
			answers.add(happy);
		}

		for (Boolean answer : answers) {
			out.println(answer ? "happy" : "sad");
		}
	}

	private static int dist(Pos cur, Pos next) {
		return Math.abs(cur.x - next.x) + Math.abs(cur.y - next.y);
	}
}
