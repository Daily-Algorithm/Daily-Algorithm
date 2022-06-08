package 투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2470_두용액 {
	static FastReader scan = new FastReader();
	static int num;
	static int[] arr;
	static int pair1, pair2;
	static int closerToZero = Integer.MAX_VALUE;

	public static void main(String[] args) {
		input();
		findAnswer();
		System.out.println(pair1 + " " + pair2);
	}

	private static void findAnswer() {
		int L = 1, R = num;
		while (L < R) {
			int mix = arr[L] + arr[R];
			if (closerToZero > Math.abs(mix)) {
				closerToZero = Math.abs(mix);
				pair1 = arr[L];
				pair2 = arr[R];
				return;
			}
			if (mix < 0) L++;
			else R--;
		}
	}

	static void input(){
		num = scan.nextInt();
		arr = new int[num + 1];
		for (int i = 1; i <= num; i++) {
			arr[i] = scan.nextInt();
		}
		Arrays.sort(arr, 1, num + 1);
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
