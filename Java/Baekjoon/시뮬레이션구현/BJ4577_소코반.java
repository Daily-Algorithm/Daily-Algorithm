package Baekjoon.시뮬레이션구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ4577_소코반 {
	static FastReader scan = new FastReader();
	static int[] dirX = {0,0,-1,1};
	static int[] dirY = {-1,1,0,0};
	static StringBuilder sb = new StringBuilder();
	static int height, width;
	public static void main(String[] args){
		input();
		System.out.print(sb.toString());
	}

	private static void input() {
		int gameIdx = 1;
		while(true){
			height = Integer.parseInt(scan.next());
			width = Integer.parseInt(scan.next());
			if (height == 0 && width == 0) return;
			String[][] map = new String[height][width];
			boolean[][] blocked = new boolean[height][width];
			Point start = null;
			for (int y = 0; y < height; y++) {
				map[y] = scan.nextLine().split("");
				for (int x = 0; x < width; x++) {
					if(map[y][x].equals("#")) blocked[y][x] = true;
					if(map[y][x].equals("w") || map[y][x].equals("W")) start = new Point(x, y);
				}
			}

			String[] strOrders = scan.nextLine().split("");
			int[] orders = new int[strOrders.length];
			for (int i = 0; i < strOrders.length; i++) {
				switch (strOrders[i]) {
					case "U":
						orders[i] = 0;
						break;
					case "D":
						orders[i] = 1;
						break;
					case "L":
						orders[i] = 2;
						break;
					case "R":
						orders[i] = 3;
						break;
				}
			}
			game(gameIdx, start, map, blocked, orders);
			gameIdx++;
		}
	}

	private static void game(
		int gameIdx,
		Point ch,
		String[][] map,
		boolean[][] blocked,
		int[] orders
	) {
		for (int order : orders) {
			Point chNext = new Point(ch.x + dirX[order], ch.y + dirY[order]);
			if (inRange(chNext) && !isBlocked(blocked, chNext)) {
				if (isBox(map, chNext)) {
					Point boxNext = new Point(chNext.x + dirX[order], chNext.y + dirY[order]);
					if (inRange(boxNext)
						&& !isBlocked(blocked, boxNext)
						&& !isBox(map, boxNext))
					{
						map[chNext.y][chNext.x] = map[chNext.y][chNext.x].equals("B") ? "+" : ".";
						map[boxNext.y][boxNext.x] = map[boxNext.y][boxNext.x].equals("+") ? "B" : "b";
					} else {
						continue;
					}
				}
				map[ch.y][ch.x] = map[ch.y][ch.x].equals("W") ? "+" : ".";
				map[chNext.y][chNext.x] = map[chNext.y][chNext.x].equals("+") ? "W" : "w";
				ch = chNext;
			}
			if (isComplete(map)) break;
		}
		sb.append("Game ")
		  .append(gameIdx)
		  .append(": ")
		  .append(isComplete(map) ? "complete\n" : "incomplete\n");
		for (String[] strings : map) {
			sb.append(String.join("", strings))
			  .append("\n");
		}
	}

	private static boolean isComplete(String[][] map) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (map[y][x].equals("b")) return false;
			}
		}
		return true;
	}

	private static boolean isBox(String[][] map, Point nextP) {
		return map[nextP.y][nextP.x].equals("b") || map[nextP.y][nextP.x].equals("B");
	}

	private static boolean isBlocked(boolean[][] blocked, Point point) {
		return blocked[point.y][point.x];
	}

	private static boolean inRange(Point point) {
		return 0 <= point.x && point.x < width && 0 <= point.y && point.y < height;
	}

	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static class FastReader {
		BufferedReader br;
		StringTokenizer st;
		FastReader(){
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		String next(){
			while(st == null || !st.hasMoreElements()){
				try{
					st = new StringTokenizer(br.readLine());
				} catch(IOException e){
				}
			}
			return st.nextToken();
		}
		String nextLine() {
			String str = "";
			try{
				str = br.readLine();
			} catch(IOException e){
			}
			return str;
		}
	}
}
