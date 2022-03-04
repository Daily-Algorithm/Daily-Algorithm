package 이진탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 다른 사람 풀이, 이진탐색 upper bound, lower bound 꼭 찾아보기
 */
public class BJ2110_공유기설치 {

	static int HOUSE_NUM, ROUTER;
	static int[] HOUSES;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		HOUSE_NUM = Integer.parseInt(st.nextToken());
		ROUTER = Integer.parseInt(st.nextToken());

		HOUSES = new int[HOUSE_NUM];
		for (int i = 0; i < HOUSE_NUM; i++) {
			HOUSES[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(HOUSES);

		int start = 1;	// 최소거리
		int end = HOUSES[HOUSE_NUM - 1] - HOUSES[0] + 1; // 최대거리
		while (start < end) {
			int mid = (start + end) / 2;
			if (routersNum(mid) < ROUTER) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		System.out.println(start - 1);
	}

	public static int routersNum(int minDistance) {
		int count = 1;
		int start = HOUSES[0];
		for (int i = 1; i < HOUSE_NUM; i++) {
			if (HOUSES[i] >= start + minDistance) {
				count++;
				start = HOUSES[i];
			}
		}
		return count;
	}
}
