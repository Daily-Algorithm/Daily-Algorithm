package 투포인터;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ16472_고냥이 {
	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();
	static int able, answer = -1;
	static String[] arr;

	public static void main(String[] args) {
		input();
		int R = -1;
		Map<String, Integer> map = new HashMap<>();
		for (int L = 0; L < arr.length; L++) {
			while (map.size() <= able && R + 1 < arr.length) {
				R++;
				map.put(arr[R], map.getOrDefault(arr[R], 0) + 1);
			}
			if (map.size() > able) {
				map.remove(arr[R]);
				R--;
			}
			answer = Math.max(answer, R - L + 1);
			if (map.get(arr[L]) == 1) {
				map.remove(arr[L]);
			} else {
				map.put(arr[L], map.get(arr[L]) - 1);
			}
		}
		System.out.println(answer);
	}
	static void input(){
		able = scan.nextInt();
		arr = scan.nextLine().split("");
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
