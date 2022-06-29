package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ12931_두배더하기 {

	static FastReader scan = new FastReader();
	static int[] processArr;
	static int[] resultArr;
	static int N, calPlus, calMultiple;

	public static void main(String[] args) {
		input();
		for (int i = 0; i < N; i++) {
			calNum(resultArr[i]);
		}
		System.out.println(calPlus + calMultiple);
	}

	private static void calNum(int after) {
		int before = 0;
		int multiple = 0;
		int plus = 0;
		while (after != before) {
			if (after % 2 == 0) {
				after /= 2;
				multiple++;
			} else {
				after--;
				plus++;
			}
		}
		calMultiple = Math.max(calMultiple, multiple);
		calPlus += plus;
	}

	static void input(){
		N = scan.nextInt();
		processArr = new int[N];
		resultArr = new int[N];
		for (int i = 0; i < N; i++) {
			resultArr[i] = scan.nextInt();
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
