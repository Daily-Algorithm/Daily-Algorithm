package DP;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14501_퇴사 {
	static int dday;
	static int[] needDay, money;
	static int[] earnMoney;
	static int maxMoney =0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		dday = Integer.parseInt(br.readLine());
		needDay = new int[dday];
		money = new int[dday];
		earnMoney = new int[dday + 1];

		for (int i = 0; i < dday; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			needDay[i] = Integer.parseInt(st.nextToken());
			money[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = dday - 1; i >= 0; i--) {
			int endDay = needDay[i] + i;
			if (endDay <= dday) {
				earnMoney[i] = Math.max(money[i] + earnMoney[endDay], maxMoney);
				maxMoney = earnMoney[i];
			} else {
				// 선택하지 않음
				earnMoney[i] = maxMoney;
			}
		}
		out.println(maxMoney);
	}

}
