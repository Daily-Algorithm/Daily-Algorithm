package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BJ1018_체스판다시칠하기 {
	static String[][] matrix;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int height = Integer.parseInt(split[0]);
		int width = Integer.parseInt(split[1]);

		List<Integer> changesL = new ArrayList<>();

		matrix = new String[height][width];
		for (int i = 0; i < height; i++) {
			matrix[i] = br.readLine().split("");
		}

		for (int i = 0; i <= height - 8; i++) {
			for (int j = 0; j <= width - 8; j++) {
				changesL.add(countMatrix(j, i, matrix));
			}
		}

		Collections.sort(changesL);
		System.out.println(changesL.get(0));
	}

	static int countMatrix(int x, int y, String[][] smatrix) {
		/**
		 * 1. 기준이 B일 때 바꿔야 할 개수 :
		 * 홀행(홀열 중 W개수 + 짝열 중 B개수) SUM + 짝행(홀열 중 B개수 + 짝열 중 W개수) SUM
		 *
		 * 2. 기준이 W일 때 바꿔야 할 개수 :
		 * 홀행(홀열 중 B개수 + 짝열 중 W개수) SUM + 짝행(홀열 중 W개수 + 짝열 중 B개수) SUM
		 *
		 * 1 & 2 중 작은 수가 countMatrix
		 */
		// i = 행, j = 열
		int bOfOddJ = 0; int wOfOddJ = 0;
		int bOfEvenJ = 0; int wOfEvenJ = 0;

		for (int i = y; i < y + 8; i++) {
			if (i % 2 == 0) {
				// 홀행일 때는 wOfOddJ + bOfEvenJ
				for (int j = x; j < x + 8; j++) {
					if (j % 2 == 0) {
						if (!smatrix[i][j].equals("B")) {
							wOfOddJ++;
						}
					} else {
						if (smatrix[i][j].equals("B")) {
							bOfEvenJ++;
						}
					}
				}
			} else {
				// 짝행일 때는 bOfOddJ + wOfEvenJ;
				for (int j = x; j < x + 8; j++) {
					if (j % 2 == 0) {
						if (smatrix[i][j].equals("B")) {
							bOfOddJ++;
						}
					} else {
						if (!smatrix[i][j].equals("B")) {
							wOfEvenJ++;
						}
					}
				}
			}
		}

		int ifB = bOfOddJ + wOfOddJ + bOfEvenJ + wOfEvenJ;
		int ifW = 64 - ifB;
		return Math.min(ifB, ifW);
	}
}
