package 투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2230_수고르기 {
	static FastReader scan = new FastReader();
	static int N, limit;
	static int[] arr;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) {
		input();
		process();
		System.out.println(answer);
	}

	private static void process() {
		Arrays.sort(arr);
		int R = -1;
		for (int L = 0; L < N; L++) {
			while (R + 1 < N && (arr[R + 1] - arr[L]) < limit) {
				R++;
			}
			if (R + 1 < N && (arr[R + 1] - arr[L]) >= limit) {
				answer = Math.min(answer, arr[R + 1] - arr[L]);
			}
		}
	}

	static void input(){
		N = scan.nextInt();
		limit = scan.nextInt();
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
