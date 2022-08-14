package Baekjoon.이진탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2512_예산 {
	static FastReader scan = new FastReader();
	static int N, M;
	static int[] needs;

	public static void main(String[] args) {
		input();
		process();
	}

	private static void process() {
		// mid = M보다 작거나 같은 전체 예산 중 가장 큰 예산
		int left = 0;
		int right = needs[N - 1];
		int result = 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (calTotal(mid) <= M) {
				result = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(result);
	}

	private static int calTotal(int num) {
		int sum = 0;
		for (int need : needs) {
			sum += Math.min(need, num);
		}
		return sum;
	}

	static void input(){
		N = scan.nextInt();
		needs = new int[N];
		for (int i = 0; i < N; i++) {
			needs[i] = scan.nextInt();
		}
		Arrays.sort(needs);
		M = scan.nextInt();
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
