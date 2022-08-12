package Baekjoon.투포인터슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 메모리초과
 */
public class BJ13144_ListOfUniqueNumber_F01 {
	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int answer = 0;
	static int[] arr;
	static Set<Integer> set = new HashSet<>();

	public static void main(String[] args) {
		input();
		for (int L = 1; L <= N; L++) {
			int R = L;
			set = new HashSet<>();
			while (R <= N && !set.contains(arr[R])) {
				answer++;
				set.add(arr[R]);
				R++;
			}
			set.remove(arr[L]);
		}
		System.out.println(answer);
	}
	static void input(){
		N = scan.nextInt();
		arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
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
