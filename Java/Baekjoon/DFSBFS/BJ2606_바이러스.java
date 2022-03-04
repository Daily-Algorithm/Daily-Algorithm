package DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2606_바이러스 {
	static int TOTAL, CONN;
	static HashMap<Integer, List<Integer>> adjMap = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TOTAL = Integer.parseInt(br.readLine());
		CONN = Integer.parseInt(br.readLine());

		for (int i = 0; i < CONN; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int current = Integer.parseInt(st.nextToken());
			int adj = Integer.parseInt(st.nextToken());
			putIntoMap(current, adj);
			putIntoMap(adj, current);
		}

		boolean[] visited = new boolean[TOTAL + 1];
		bfs(1, visited);
	}

	private static void bfs(int v, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		visited[v] = true;
		queue.add(v);

		int count = 0;
		while (!queue.isEmpty()) {
			v = queue.poll();
			Iterator<Integer> iter = adjMap.get(v).iterator();
			while (iter.hasNext()) {
				int w = iter.next();
				if (!visited[w]) {
					count++;
					visited[w] = true;
					queue.add(w);
				}
			}
		}

		System.out.println(count);
	}

	private static void putIntoMap(int currIdx, int adjIdx) {
		if (adjMap.containsKey(currIdx)) {
			List<Integer> list = adjMap.get(currIdx);
			list.add(adjIdx);
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(adjIdx);
			adjMap.put(currIdx, list);
		}
	}
}
