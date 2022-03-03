package DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2178_미로탐색 {
	static int width, height;
	static HashMap<Integer, List<Integer>> adjMap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());

		String[][] matrix = new String[height][width];
		for (int i = 0; i < height; i++) {
			matrix[i] = br.readLine().split("");
		}

		adjMap = new HashMap<>();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (matrix[i][j].equals("1")) {
					int currIdx = i * width + j;
					if (j < width - 1 && matrix[i][j + 1].equals("1")) {
						//오른쪽으로 갈 수 있으면
						int rightIdx = i * width + (j + 1);
						putIntoMap(currIdx, rightIdx);
						putIntoMap(rightIdx, currIdx);
					}
					if (i < height - 1 && matrix[i + 1][j].equals("1")) {
						// 아래로 갈 수 있으면
						int downIdx = (i + 1) * width + j;
						putIntoMap(currIdx, downIdx);
						putIntoMap(downIdx, currIdx);
					}
				}
			}
		}
		boolean[] visited = new boolean[width * height];
		Integer[] edgeTo = new Integer[width * height];
		Arrays.fill(visited, false);
		Arrays.fill(edgeTo, -1);
		bfs(0, visited, edgeTo);
		System.out.println(pathTo(width * height - 1, visited, edgeTo));
	}

	private static int pathTo(int last, boolean[] visited, Integer[] edgeTo) {
		int start = 0;
		if(!visited[last]){
			return -1;
		}
		List<Integer> path = new ArrayList<>();
		for (int i = last; i != start; i = edgeTo[i]) {
			path.add(i);
		}
		path.add(start);
		return path.size();
	}

	private static void bfs(int v, boolean[] visited, Integer[] edgeTo) {
		Queue<Integer> queue = new LinkedList<>();
		visited[v] = true;
		queue.add(v);
		while (!queue.isEmpty()) {
			v = queue.poll();
			Iterator<Integer> iter = adjMap.get(v).iterator();
			while (iter.hasNext()) {
				int w = iter.next();
				if (!visited[w]) {
					visited[w] = true;
					edgeTo[w] = v;
					queue.add(w);
				}
			}
		}
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
