package DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1260_DFS와BFS {
	static int N, M, V;
	static LinkedList<Integer>[] adjList;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		adjList = new LinkedList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new LinkedList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			adjList[v1].add(v2);
			adjList[v2].add(v1);
		}

		for (int i = 1; i < N + 1; i++) {
			Collections.sort(adjList[i]);
		}

		boolean[] visited = new boolean[N + 1];
		dfs(V, visited);
		visited = new boolean[N + 1];
		System.out.println();
		bfs(V, visited);
	}

	private static void bfs(int v, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		visited[v] = true;
		queue.add(v);
		while (!queue.isEmpty()) {
			v = queue.poll();
			System.out.print(v + " ");
			Iterator<Integer> iter = adjList[v].iterator();
			while (iter.hasNext()) {
				int w = iter.next();
				if (!visited[w]) {
					visited[w] = true;
					queue.add(w);
				}
			}
		}
	}

	private static void dfs(int v, boolean[] visited) {
		visited[v] = true;
		System.out.print(v + " ");
		ListIterator<Integer> iter = adjList[v].listIterator();
		while (iter.hasNext()) {
			int w = iter.next();
			if (!visited[w]) {
				dfs(w, visited);
			}
		}
	}
}
