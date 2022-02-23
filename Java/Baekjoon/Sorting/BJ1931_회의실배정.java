package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ1931_회의실배정 {

	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] meetings = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			meetings[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		}

		Arrays.sort(meetings, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] == o2[1]) {
					return o1[0] - o2[0];
				}
				return o1[1] - o2[1];
			}
		});
		int[] startMeeting = meetings[0];
		// end 숫자가 가장 작은게 스타트

		int count = 1;
		int able = startMeeting[1];
		for (int i = 1; i < N; i++) {
			int start = meetings[i][0];
			int end = meetings[i][1];
			if (start >= able) {
				able = end;
				count++;
			}
		}
		// 앞의 end에 가장 가까운 start가 다음 수
		System.out.println(count);
	}
}
