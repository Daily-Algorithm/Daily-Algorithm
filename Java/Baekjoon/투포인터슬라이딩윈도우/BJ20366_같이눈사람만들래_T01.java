package Baekjoon.투포인터슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ20366_같이눈사람만들래_T01 {
	static FastReader scan = new FastReader();
	static int snowballs;
	static int[] diaMs;
	static List<Snowman> snowmenL = new ArrayList<>();
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) {
		input();
		makeSnowmenL();
		snowmenL.sort((o1, o2) -> o1.height - o2.height);
		getAnswer();
		System.out.println(answer);
	}

	private static void getAnswer() {
		for (int i = 0; i < snowmenL.size(); i++) {
			for (int j = i + 1; j < snowmenL.size(); j++) {
				Snowman lower = snowmenL.get(i);
				Snowman higher = snowmenL.get(j);
				Set<Integer> set = new HashSet<>();
				set.add(lower.idx1);
				set.add(lower.idx2);
				set.add(higher.idx1);
				set.add(higher.idx2);
				if (set.size() == 4) {
					answer = Math.min(answer, higher.height - lower.height);
					break;
				}
			}
		}
	}

	private static void makeSnowmenL() {
		for (int idx1 = 0; idx1 < snowballs; idx1++) {
			for (int idx2 = idx1 + 1; idx2 < snowballs; idx2++) {
				snowmenL.add(new Snowman(idx1, idx2, diaMs[idx1] + diaMs[idx2]));
			}
		}
	}

	static void input(){
		snowballs = scan.nextInt();
		diaMs = new int[snowballs];
		diaMs = Arrays.stream(scan.nextLine().split(" ")).mapToInt(Integer::new).toArray();
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

	private static class Snowman {
		int idx1, idx2, height;

		public Snowman(int idx1, int idx2, int height) {
			this.idx1 = idx1;
			this.idx2 = idx2;
			this.height = height;
		}
	}
}
