package 다익스트라;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 다익스트라 {
	static int N, E;
	static Edge[][] edges = new Edge[N][N];
	static int[] dist = new int[N];

	static void dijkstra(int start) {
		// 1. 모든 정점까지의 dist를 최대로 초기화하기
		// 만약 가중치가 Integer.MAX_VALUE를 넘어간다면 그 이상의 수로 초기화
		Arrays.fill(dist, Integer.MAX_VALUE);

		// 2. 최소힙 생성
		PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
			// = PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);

		// 3. 시작점에 대한 정보를 초기화, 갱신
		pq.add(new Info(start, 0));
		dist[start] = 0;

		// 4. 거리정보가 모두 소진될 때까지 거리 갱신을 반복
		while (!pq.isEmpty()) {
			Info info = pq.poll();
			// 꺼낸 정보가 가치없는 정보면 폐기한다
			if (dist[info.idx] < info.dist) continue;
			// 가치있는 정보면 연결된 모든 간선들을 통해 다른 정점들에 대한 정보를 갱신한다.
			for (Edge e : edges[info.idx]) {
				if (dist[e.to] <= dist[info.idx] + e.weight) continue;
				// info.idx를 거치면 e.to 까지 더 빨리 갈 수 있다면 정보를 갱신하고 자료를 추가한다.
				dist[e.to] = dist[info.idx] + e.weight;
				pq.add(new Info(e.to, dist[e.to]));
			}
		}

	}

	private static class Edge {
		int to, weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	static class Info {
		int idx, dist;

		public Info(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}
	}
}
