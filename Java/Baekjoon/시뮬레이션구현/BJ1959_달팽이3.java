package Baekjoon.시뮬레이션구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1959_달팽이3 {

	static FastReader scan = new FastReader();
	static long width, height;
	static long turn, endX, endY;
	public static void main(String[] args) {
		input();
		if (width < height) {
			turn = width * 2 - 1;
			endX = width % 2 == 0 ? width / 2 : width / 2 + 1;
			endY = width % 2 == 0 ? width / 2 + 1 : width / 2 + 1 + (height - width);
		} else if (width > height) {
			turn = (height - 1) * 2;
			endX = height % 2 == 0 ? height / 2 : height / 2 + width - height + 1;
			endY = height / 2 + 1;
		} else {
			// 같을 때
			turn = (height - 1) * 2;
			endX = width % 2 == 0 ? width / 2 : width / 2 + 1;
			endY = width / 2 + 1;
		}
		System.out.println(turn);
		System.out.println(endY + " " + endX);
	}

	private static void input() {
		height = scan.nextLong();
		width = scan.nextLong();
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;
		FastReader(){
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		String next(){
			while(st==null||!st.hasMoreElements()){
				try {
					st = new StringTokenizer(br.readLine());
				} catch(IOException e){

				}
			}
			return st.nextToken();
		}
		long nextLong(){
			return Long.parseLong(next());
		}
	}
}
