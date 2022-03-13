package BruteForce;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ2615_오목 {
	static List<List<Integer>> map = new ArrayList<>();
	static int[] dirX = {0, 1, 1, 1}; // 하, 우, 대오아래, 대왼아래, 대오위
	static int[] dirY = {1, 0, 1, -1};
	static int width, height;

	private static class Stone {
		int x;
		int y;
		public Stone(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = "";
		while ((line = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line, " ");
			List<Integer> list = new ArrayList<>();
			while (st.hasMoreTokens()) {
				list.add(Integer.valueOf(st.nextToken()));
			}
			map.add(list);
		}
		height = map.size();
		width = map.get(0).size();
		Stone leftStone = findLeftStone();
		if (leftStone != null) {
			out.println(valueOf(leftStone));
			out.println(leftStone.y + 1 + " " + leftStone.x + 1);
		} else {
			out.println(0);
		}
	}

	private static Stone findLeftStone() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				for (int dirIdx = 0; dirIdx < 4; dirIdx++) {
					Stone curS = new Stone(x, y);
					boolean found = true;
					for (int step = 0; step < 4; step++) {
						Stone nextS = new Stone(curS.x + dirX[dirIdx], curS.y + dirY[dirIdx]);
						if (isQualified(nextS) && isSame(curS, nextS)) {
							curS = nextS;
						} else {
							found = false;
							break;
						}
					}
					Stone startS = new Stone(x, y);
					if (found && isStart(startS, dirIdx) && isEnd(curS, dirIdx)) {
						return startS;
					}
				}
			}
		}
		return null;
	}

	private static boolean isStart(Stone startS, int dirIdx) {
		Stone before = new Stone(startS.x - dirX[dirIdx], startS.y - dirY[dirIdx]);
		return !isQualified(before) || !isSame(before, startS);
	}

	private static boolean isEnd(Stone curS, int dirIdx) {
		Stone after = new Stone(curS.x + dirX[dirIdx], curS.y + dirY[dirIdx]);
		return !isQualified(after) || !isSame(after, curS);
	}

	private static boolean isSame(Stone A, Stone B) {
		return valueOf(A) == valueOf(B);
	}

	private static boolean isQualified(Stone A) {
		return A.x >= 0 && A.x < width && A.y >= 0 && A.y < height;
	}

	private static int valueOf(Stone A) {
		return map.get(A.y).get(A.x);
	}
}
