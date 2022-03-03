package DFSBFS;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 왜 LinkedList를 Arrays.fill(adjList, new LinkedList<>())로 하면 전부 다 동기화 되는걸까?
 */
public class BJ2644_촌수계산 {

	static int total;
	static int p1, p2;
	static int conn;
	static LinkedList<Integer>[] adjList;
	static boolean[] visited;
	static boolean found = false;
	public static void main(String[] args) throws IOException {
		// p1 -> p2 까지의 depth를 더한 값이 정답
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		total = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		p1 = Integer.parseInt(st.nextToken());
		p2 = Integer.parseInt(st.nextToken());
		conn = Integer.parseInt(br.readLine());

		// adjList, visited 만들기
		visited = new boolean[total + 1];
		Arrays.fill(visited, false);
		adjList = new LinkedList[total + 1];

		for (int i = 0; i < total + 1; i++) {
			adjList[i] = new LinkedList<Integer>();
		}
		for (int i = 0; i < conn; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int link1 = Integer.parseInt(st.nextToken());
			int link2 = Integer.parseInt(st.nextToken());
			adjList[link1].add(link2);
			adjList[link2].add(link1);
		}

		findDepth(p1, p2, 0);
		if (!found) {
			out.println(-1);
		}
	}

	private static void findDepth(int start, int person, int depth) {
		visited[start] = true;
		if (start != person) {
			Iterator<Integer> iter = adjList[start].iterator();
			while (iter.hasNext()) {
				int next = iter.next();
				if (!visited[next]) {
					findDepth(next, person, depth + 1);
				}
			}
		} else {
			found = true;
			out.println(depth);
		}
	}
}
