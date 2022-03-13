package DFSBFS;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ10451_순열사이클 {

	static List<Integer> answers = new ArrayList<>();
	static int test;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		test = Integer.parseInt(br.readLine());
		for (int i = 0; i < test; i++) {
			int nums = Integer.parseInt(br.readLine());
			int[] sorted = new int[nums + 1];
			int[] given = new int[nums + 1];
			given[0] = -1;
			sorted[0] = -1;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int idx = 1; idx <= nums; idx++) {
				int nextToken = Integer.parseInt(st.nextToken());
				given[idx] = nextToken;
				sorted[idx] = nextToken;
			}
			Arrays.sort(sorted);
			answers.add(countCycle(nums, given, sorted));
		}
		answers.forEach(out::println);
	}

	private static int countCycle(int nums, int[] given, int[] sorted) {
		int[] adj = new int[nums + 1];
		adj[0] = -1;
		for (int idx = 1; idx <= nums; idx++) {
			int sortedNum = idx;
			int givenNum = given[idx];
			if (sortedNum != givenNum) {
				adj[sortedNum] = givenNum;
			} else {
				adj[sortedNum] = -1;
			}
		}

		boolean[] visited = new boolean[nums + 1];
		Arrays.fill(visited, false);
		int count = 0;
		for (int i = 1; i <= nums; i++) {
			if (!visited[i]) {
				count++;
				Queue<Integer> queue = new LinkedList<>();
				visited[i] = true;
				queue.add(i);
				while (!queue.isEmpty()) {
					Integer cur = queue.poll();
					if (adj[cur] != -1 && !visited[adj[cur]]) {
						queue.add(adj[cur]);
						visited[adj[cur]] = true;
					}
				}
			}
		}
		return count;
	}
}
