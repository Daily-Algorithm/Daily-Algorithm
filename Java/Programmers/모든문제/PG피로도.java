package 모든문제;

import java.util.Arrays;

public class PG피로도 {
	private static int maxDungeon = 0;
	private static int dungeonNum;
	private static boolean visited[];
	private static int[][] dungeonMap;
	public static int solution(int k, int[][] dungeons) {
		dungeonNum = dungeons.length;
		dungeonMap = dungeons;
		visited = new boolean[dungeons.length];
		Arrays.fill(visited, false);
		permutation(visited, k, 0);
		return maxDungeon;
	}

	private static void permutation(boolean[] visited, int left, int visitedD) {
		maxDungeon = Math.max(maxDungeon, visitedD);

		for (int i = 0; i < dungeonNum; i++) {
			if (!visited[i] && left >= dungeonMap[i][0]) {
				visited[i] = true;
				permutation(visited, left - dungeonMap[i][1], visitedD + 1);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		int k = 80;
		int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
		System.out.println(solution(k, dungeons));
	}
}
