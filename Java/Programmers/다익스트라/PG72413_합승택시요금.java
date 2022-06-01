package 다익스트라;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 되게 좋은 문제 같다
 * 다익스트라로 풀었는데도 시간초과나는 부분들이 있어 질문하기를 봤더니 효율성을 고민하면서 푸는 게 쟁점인 문제 같다
 *
 * FirstTry : 1) start->교차점, 2) 교차점->A, 3) 교차점->B 모두 구하는 거
 * SecondTry : 2), 3)이 교차점에서 출발하는 건 같으니 오버로딩을 통해 1)을 구하고 2), 3)은 한번에 구하는 거
 * ThirdTry : 문제를 보면 양방향이기 때문에 a->b로 가는 최소거리 = b->a로 가는 최소거리 이기 때문에
 * 				start에서 모든 점의 dist배열을 distFromStart, A에서 모든 점의 dist배열을 distFromA, B에서 모든 점의 dist배열을 distFromB
 * 				세번만 구해 intersection들을 살핌
 */

public class PG72413_합승택시요금 {
	static List<Edge>[] edges;
	static int N, start, A, B;
	static int answer = Integer.MAX_VALUE;

	public static int solution(int n, int s, int a, int b, int[][] fares) {
		init(n, s, a, b, fares);
		int[] distFromStart = makeDistArr(start);
		int[] distFromA = makeDistArr(A);
		int[] distFromB = makeDistArr(B);
		for (int intersection = 1; intersection < N + 1; intersection++) {
			answer = Math.min(answer, distFromStart[intersection] + distFromA[intersection] + distFromB[intersection]);
		}
		return answer;
	}

	private static int[] makeDistArr(int start) {
		int[] dist = new int[N + 1];
		PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		pq.add(new Info(start, 0));
		while (!pq.isEmpty()) {
			Info cur = pq.poll();
			if (dist[cur.idx] < cur.dist) continue;
			for (Edge edge : edges[cur.idx]) {
				if (dist[edge.end] <= dist[cur.idx] + edge.weight) continue;
				dist[edge.end] = dist[cur.idx] + edge.weight;
				pq.add(new Info(edge.end, dist[edge.end]));
			}
		}
		return dist.clone();
	}

	private static void init(int n, int s, int a, int b, int[][] fares) {
		N = n;
		start = s;
		A = a;
		B = b;
		edges = new List[N + 1];
		for (int i = 0; i < N+1; i++) {
			edges[i] = new ArrayList<>();
		}

		for (int[] arr : fares) {
			edges[arr[0]].add(new Edge(arr[1], arr[2]));
			edges[arr[1]].add(new Edge(arr[0], arr[2]));
		}
	}

	public static void main(String[] args) {
		System.out.println(solution(6, 4, 6, 2,
									new int[][]{
										{4, 1, 10},
										{3, 5, 24},
										{5, 6, 2},
										{3, 1, 41},
										{5, 1, 24},
										{4, 6, 50},
										{2, 4, 66},
										{2, 3, 22},
										{1, 6, 25}}
		));


	}

	private static class Edge {
		int end, weight;

		public Edge(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
	}

	private static class Info {
		int idx, dist;

		public Info(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}
	}
}
