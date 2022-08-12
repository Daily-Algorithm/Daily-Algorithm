package Baekjoon.투포인터슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ12891_DNA비밀번호 {
	static FastReader scan = new FastReader();
	static int strL, subL, cnt;
	static String[] arr;
	static Map<String, Integer> standard = new HashMap<>();
	static Map<String, Integer> map = new HashMap<>();

	public static void main(String[] args) {
		input();
		process();
		System.out.println(cnt);
	}

	private static boolean isRight() {
		for (String alphabet : map.keySet()) {
			int num = map.get(alphabet);
			if (num < standard.get(alphabet)) return false;
		}
		return true;
	}

	private static void process() {
		int left = 0;
		int right = subL - 1;
		if (isRight()) cnt++;
		while (right != strL - 1) {
			map.put(arr[left], map.get(arr[left]) - 1);
			left++;
			right++;
			map.put(arr[right], map.get(arr[right]) + 1);
			if (isRight()) cnt++;
		}
	}

	static void input(){
		strL = scan.nextInt();
		subL = scan.nextInt();
		arr = scan.nextLine().split("");
		standard.put("A", scan.nextInt());
		standard.put("C", scan.nextInt());
		standard.put("G", scan.nextInt());
		standard.put("T", scan.nextInt());

		map.put("A", 0);
		map.put("C", 0);
		map.put("G", 0);
		map.put("T", 0);

		for (int i = 0; i < subL; i++) {
			map.put(arr[i], map.get(arr[i]) + 1);
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
