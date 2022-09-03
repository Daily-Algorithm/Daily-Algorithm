package Baekjoon.이진탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ2805_나무자르기 {

	static int treeNum, toTake;
	static Long[] treeHeight;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine()
						   .split(" ");
		treeNum = Integer.parseInt(split[0]);
		toTake = Integer.parseInt(split[1]);

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		treeHeight = new Long[treeNum];
		for (int i = 0; i < treeNum; i++) {
			treeHeight[i] = Long.valueOf(st.nextToken());
		}
		Arrays.sort(treeHeight, Comparator.reverseOrder());

		Long maxOfH = treeHeight[0] - 1; Long minOfH = 0L;
		while (!minOfH.equals(maxOfH)) {
			if (cutBy(maxOfH) >= toTake) {
				break;
			} else {
				Long mid = (maxOfH + minOfH) / 2;
				if (cutBy(mid) > toTake) {
					if (minOfH.equals(mid)) {
						maxOfH = minOfH; break;
					}else{
						minOfH = mid;
					}
				}else {
					if (maxOfH.equals(mid)) {
						maxOfH = minOfH; break;
					}else{
						maxOfH = mid;
					}
				}
			}
		}
		System.out.println(maxOfH);
	}

	private static Long cutBy(Long num) {
		Long takeCM = 0L;
		for (Long tree : treeHeight) {
			if (tree > num) {
				takeCM += (tree - num);
			} else {
				break;
			}
		}
		return takeCM;
	}
}
