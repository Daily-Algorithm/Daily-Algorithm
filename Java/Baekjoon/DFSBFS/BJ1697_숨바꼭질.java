package DFSBFS;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1697_숨바꼭질 {

	static int start, end;
	static Queue<Integer> queue = new LinkedList<>();
	static int[] dist;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		dist = new int[100001];
		visit = new boolean[100001];
		Arrays.fill(dist, -1);
		Arrays.fill(visit, false);
		out.println(BFS(start, end));
	}

	private static int BFS(int start, int end) {
		queue.add(start);
		visit[start] = true;
		dist[start] = 0;
		while (!queue.isEmpty() && queue.peek() != end) {
			int current = queue.poll();
			int[] nextCandidates = new int[]{current - 1, current + 1, current * 2};
			for (int candidate : nextCandidates) {
				if (candidate <= 100000 && candidate >= 0 && !visit[candidate]) {
					queue.add(candidate);
					visit[candidate] = true;
					dist[candidate] = dist[current] + 1;
				}
			}
		}
		return dist[end];
	}
}
