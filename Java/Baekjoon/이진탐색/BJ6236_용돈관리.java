package 이진탐색;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 역대급으로 가독성 떨어지는 문제, 계속 틀리다가 맞았다.
 * 틀린 부분은 39번째줄 int highWDMoney = SORTED.get(DAYS - 1);
 * 가장 작은 수가 아닌 가장 큰 수여야 함
 */
public class BJ6236_용돈관리 {
	static int DAYS, COUNT;
	static int SUM = 0;
	static List<Integer> USED = new ArrayList<>();
	static List<Integer> SORTED = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		DAYS = Integer.parseInt(st.nextToken());
		COUNT = Integer.parseInt(st.nextToken());

		for (int i = 0; i < DAYS; i++) {
			Integer usedMoney = Integer.valueOf(br.readLine());
			USED.add(usedMoney);
			SORTED.add(usedMoney);
			SUM += usedMoney;
		}
		Collections.sort(SORTED);
		out.println(upperWDDays());
	}

	private static int upperWDDays() {
		int lowWDMoney = SUM;	// 가장 큰 돈
		int highWDMoney = SORTED.get(DAYS - 1);    // 가장 작은 돈
		int midWDMoney = 0;
		while (lowWDMoney > highWDMoney) {
			midWDMoney = (lowWDMoney + highWDMoney) / 2;
			if (withdrawDays(midWDMoney) <= COUNT) {
				lowWDMoney = midWDMoney;
			} else {
				highWDMoney = midWDMoney + 1;
			}
		}
		return lowWDMoney;

	}

	private static int withdrawDays(int money) {
		int withdraw = 0;
		int left = 0;
		for (int day = 0; day < DAYS; day++) {
			if (left < USED.get(day)) {
				left = 0;
				while (left < USED.get(day)) {
					withdraw++;
					left += money;
				}
			}
			left -= USED.get(day);

		}
		return withdraw;
	}
}