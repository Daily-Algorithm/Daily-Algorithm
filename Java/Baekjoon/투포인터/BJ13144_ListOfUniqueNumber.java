package 투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * answer가 long이어야 하는 이유 = max값이 N(N+1)/2 = 5*10^9 > 2*10^9(int 범위)
 */
public class BJ13144_ListOfUniqueNumber {
	static FastReader scan = new FastReader();
	static int N;
	static long answer = 0;
	static int[] arr;
	static int[] count;

	public static void main(String[] args) {
		input();
		int R = 0;
		for (int L = 1; L <= N; L++) {
			while (R + 1 <= N && count[arr[R + 1]] == 0) {
				R++;
				count[arr[R]]++;
			}
			answer += R - L + 1;
			count[arr[L]]--;
		}
		System.out.println(answer);
	}
	static void input(){
		N = scan.nextInt();
		arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = scan.nextInt();
		}
		count = new int[100001];
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
