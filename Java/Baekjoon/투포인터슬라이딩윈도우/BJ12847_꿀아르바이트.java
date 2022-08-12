package 슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ12847_꿀아르바이트 {
	static FastReader scan = new FastReader();
	static int N, M;
	static int[] arr;
	static long max = Long.MIN_VALUE;

	public static void main(String[] args) {
		input();
		sliding(M);
		System.out.println(max);
	}

	private static void sliding(int m) {
		int left = 0;
		int right = m - 1;
		long sum = 0;
		for (int i = left; i <= right; i++) {
			sum += arr[i];
		}
		compare(sum);
		while (right < N - 1) {
			sum -= arr[left];
			left++;
			right++;
			sum += arr[right];
			compare(sum);
		}
	}

	private static void compare(long sum) {
		if (max < sum) max = sum;
	}

	static void input(){
		N = scan.nextInt();
		M = scan.nextInt();
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = scan.nextInt();
		}
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
