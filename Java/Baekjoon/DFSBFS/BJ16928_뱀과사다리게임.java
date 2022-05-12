package DFSBFS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16928_뱀과사다리게임 {
	static int ladder, snake;
	static Map<Integer, Integer> ladderM = new HashMap<>();
	static Map<Integer, Integer> snakeM = new HashMap<>();
	static FastReader scan = new FastReader();

	public static void main(String[] args) {
		input();
		System.out.println(BFS());
	}

	private static int BFS() {
		Queue<Pos> queue = new LinkedList<>();
		boolean[] visited = new boolean[101];
		Pos start = new Pos(1, 0);
		queue.add(start);

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();
			if (cur.x == 100) {
				return cur.move;
			}
			for (int dice = 1; dice <= 6; dice++) {
				Pos destination = findDestination(cur, dice);
				if (destination.x <= 100 && !visited[destination.x - 1]) {
					queue.add(destination);
					visited[destination.x - 1] = true;
				}
			}
		}
		return -1;
	}

	private static Pos findDestination(Pos before, int dice) {
		Integer afterCheck = before.x + dice;
		if (ladderM.containsKey(afterCheck)) {
			afterCheck = ladderM.get(afterCheck);
		} else if (snakeM.containsKey(afterCheck)) {
			afterCheck = snakeM.get(afterCheck);
		}
		return new Pos(afterCheck, before.move + 1);
	}

	static void input(){
		ladder = scan.nextInt();
		snake = scan.nextInt();
		for (int i = 0; i < ladder; i++) {
			ladderM.put(scan.nextInt(), scan.nextInt());
		}
		for (int i = 0; i < snake; i++) {
			snakeM.put(scan.nextInt(), scan.nextInt());
		}
	}

	private static class Pos {
		int x, move;

		public Pos(int x, int move) {
			this.x = x;
			this.move = move;
		}
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;
		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		public FastReader(String s) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(new File(s)));
		}
		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
