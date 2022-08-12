package Baekjoon.투포인터슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1253_좋다 {
	static FastReader scan = new FastReader();
	static int[] arr;
	static int num, answer;

	public static void main(String[] args) {
		input();
		for (int i = 1; i <= num; i++) {
			if (isGood(i)) answer++;
		}
		System.out.println(answer);
	}

	private static boolean isGood(int targetIdx) {
		int target = arr[targetIdx];
		int L = 1; int R = num;
		while (L < R) {
			if (L == targetIdx) {
				L++;
			} else if (R == targetIdx) {
				R--;
			} else {
				if (arr[L] + arr[R] == target) {
					return true;
				} else if (arr[L] + arr[R] < target) {
					L++;
				} else {
					R--;
				}
			}
		}
		return false;
	}

	static void input(){
		num = scan.nextInt();
		arr = new int[num + 1];
		arr[0] = -1000000001;
		for (int i = 1; i <= num; i++) {
			arr[i] = scan.nextInt();
		}
		Arrays.sort(arr);
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
