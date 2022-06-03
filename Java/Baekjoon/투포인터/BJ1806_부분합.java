package 투포인터;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1806_부분합 {
	static FastReader scan = new FastReader();
	static int N, S;
	static int[] arr;
	public static void main(String[] args) {
		input();
		pro();
	}

	private static void pro() {
		// ans = 만족하는 배열의 길이 중 가장 짧은 것
		int R = 0, sum = 0, ans = N + 1;
		for (int L = 1; L <= N; L++) {
			// L이 이동하였으니 L-1을 구간에서 제외하기
			sum -= arr[L - 1];
			// R을 옮길 수 있을 때까지 옮기기
			while (R + 1 <= N && sum < S) sum += arr[++R];
			// [L...R]의 합, 즉 sum이 조건을 만족하면 정답 갱신하기
			if (sum >= S) ans = Math.min(ans, R - L + 1);
		}
		// ans 값을 보고 불가능 판단하기(ans == N + 1 이면 갱신된 적이 없으니 ans = 0)
		if (ans == N + 1) ans = 0;
		System.out.println(ans);
	}

	static void input(){
		N = scan.nextInt();
		S = scan.nextInt();
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
	  long nextLong() {
	    return Long.parseLong(next());
	  }
	  double nextDouble() {
	    return Double.parseDouble(next());
	  }
	  String nextLine() {
	    String str = "";
	    try {
	      str = br.readLine();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    return str;
	  }
	}
}
