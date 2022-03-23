package 구현;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ15683_감시_F01 {
	static int width, height, answer;
	static int[][] map;
	static Cctv[] cctvs = new Cctv[8];
	static int cctvCnt = 0;
	static int[] dirX = {0, 1, 0, -1};	// 상, 우, 하, 좌
	static int[] dirY = {-1, 0, 1, 0};
	static int[][][] ccDir = {
		{{0}},
		{{1}, {2}, {3}, {0}}, // 1번 cctv
		{{1, 3}, {0, 2}}, // 2번 cctv
		{{0, 1}, {1, 2}, {2, 3}, {3, 0}}, // 3번 cctv
		{{0, 1, 3}, {0, 1, 2}, {1, 2, 3}, {2, 3, 0}}, // 4번 cctv
		{{0, 1, 2, 3}}, // 5번 cctv
	};
	private static class Cctv{
		int x, y, num;
		public Cctv(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		int remain = height * width;
		map = new int[width][height];
		for (int y = 0; y < height; y++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int x = 0; x < height; x++) {
				map[x][y] = Integer.parseInt(st.nextToken());
				if (map[x][y] >= 1 && map[x][y] <= 5) {
					cctvs[cctvCnt] = new Cctv(x, y, map[x][y]);
					cctvCnt++;
				} else if (map[x][y] == 6) {
					remain--;
				}
			}
		}
		remain -= cctvCnt;

		answer = remain;
		permutation(0, remain, map);
		out.println(answer);
	}

	private static void permutation(int idx, int remain, int[][] map) {
		if (idx == cctvCnt) {
			// 모든 cctv를 확인한 상태면
			answer = Math.min(answer, remain);
			return;
		}

		Cctv curC = cctvs[idx];
		int[][] newMap = copyMap(map);
		for (int i = 0; i < ccDir[curC.num].length; i++) {
			// i = 해당 cctv가 회전하면서 감시할 수 있는 방향묶음의 index
			// ccDir[i] = cctv가 감시할 수 있는 arrows
			int checked = 0;
			for (int arrowIdx = 0; arrowIdx < ccDir[curC.num][i].length; arrowIdx++) {
				int arrowDir = ccDir[curC.num][i][arrowIdx];
				checked += checked(curC, arrowDir, newMap);
			}
			// 이 상태에서 다음 cctv 실행
			permutation(idx + 1, remain - checked, newMap);
			// 다음 회전을 위한 newMap 초기화
			newMap = copyMap(map);
		}
	}

	private static int checked(Cctv cctv, int curCDir, int[][] newMap) {
		int checked = 0;
		Queue<Cctv> queue = new LinkedList<>();
		queue.add(cctv);
		while (!queue.isEmpty()) {
			Cctv curC = queue.poll();
			Cctv nextC = new Cctv(curC.x + dirX[curCDir], curC.y + dirY[curCDir], cctv.num);
			if (inRange(nextC) && newMap[nextC.x][nextC.y] != 6) {
				if (newMap[nextC.x][nextC.y] == 0) {
					// nextC가 범위안에 있고 벽이 아니고, 빈 공간이면(이미 checked 안 돼있으면)
					checked++;
					newMap[nextC.x][nextC.y] = nextC.num;
				}
				queue.add(nextC);
			} else {
				break;
			}
		}
		return checked;
	}

	private static boolean inRange(Cctv nextC) {
		return nextC.x >= 0 && nextC.x < width && nextC.y >= 0 && nextC.y < height;
	}

	private static int[][] copyMap(int[][] map) {
		int[][] temp = new int[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				temp[x][y] = map[x][y];
			}
		}
		return temp;
	}
}
