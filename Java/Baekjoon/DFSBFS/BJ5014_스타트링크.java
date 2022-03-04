package DFSBFS;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ5014_스타트링크 {
	static int TOTAL, KH, SL, U, D;
	static int[] button;
	static boolean[] visit;
	static Queue<Integer> queue = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		TOTAL = Integer.parseInt(st.nextToken());
		KH = Integer.parseInt(st.nextToken());
		SL = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		visit = new boolean[TOTAL + 1];
		Arrays.fill(visit, false);

		button = new int[TOTAL + 1];
		Arrays.fill(button, 0);

		queue.add(KH);
		visit[KH] = true;
		button[KH]++;
		while (!queue.isEmpty() && KH != SL) {
			KH = queue.poll();
			int up = KH + U;
			if (insideBuilding(up) && !visit[up]) {
				queue.add(up);
				visit[up] = true;
				button[up] = button[KH] + 1;
			}
			int down = KH - D;
			if (insideBuilding(down) && !visit[down]) {
				queue.add(down);
				visit[down] = true;
				button[down] = button[KH] + 1;
			}
		}

		if (KH == SL) {
			out.println(button[KH] - 1);
		} else {
			out.println("use the stairs");
		}
	}

	static boolean insideBuilding(int floor) {
		return TOTAL >= floor && floor > 0;
	}

}
