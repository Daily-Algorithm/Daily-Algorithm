package Baekjoon.투포인터슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ3273_두수의합_F02 {
	static FastReader scan = new FastReader();
	static int num, end, answer;
	static int[] arr;

	public static void main(String[] args) {
		input();
		process(0, 0, 0, new boolean[num]);
		System.out.println(answer);
	}

	private static void process(int cnt, int sum, int dept, boolean[] visited) {
		if (cnt == 2) {
			if (sum == end) {
				answer++;
			}
			return;
		}
		if (dept == num) {
			return;
		}

		if (!visited[dept]) {
			visited[dept] = true;
			process(cnt + 1, sum + arr[dept], dept + 1, visited);
			visited[dept] = false;
			process(cnt, sum, dept + 1, visited);
		}

	}

	static void input(){
		num = scan.nextInt();
		arr = new int[num];
		for (int i = 0; i < num; i++) {
			arr[i] = scan.nextInt();
		}
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
