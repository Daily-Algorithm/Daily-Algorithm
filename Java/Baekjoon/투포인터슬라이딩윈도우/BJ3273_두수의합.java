package Baekjoon.투포인터슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ3273_두수의합 {
	static FastReader scan = new FastReader();
	static int num, end, answer;
	static int[] arr;

	public static void main(String[] args) {
		input();
		twoPointer();
		System.out.println(answer);
	}

	private static void twoPointer() {
		int L = 0;
		int R = num - 1;
		while (L < R) {
			if (arr[L] + arr[R] > end) {
				R--;
			} else if (arr[L] + arr[R] < end) {
				L++;
			} else {
				answer++;
				L++;
			}
		}
	}

	static void input(){
		num = scan.nextInt();
		arr = new int[num];
		for (int i = 0; i < num; i++) {
			arr[i] = scan.nextInt();
		}
		Arrays.sort(arr);
		end = scan.nextInt();
	}
	static class FastReader {
	  BufferedReader br;
	  StringTokenizer st;
	  public FastReader() {
		  br = new BufferedReader(new InputStreamReader(System.in));
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
