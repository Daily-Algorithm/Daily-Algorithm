package 구현;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ16916_부분문자열_F01 {
	static FastReader scan = new FastReader();
	static String origin, cnd;
	static int[] map; 

	public static void main(String[] args) {
		input();
		makeMap();
		System.out.println(getCnd() ? 1 : 0);
	}

	private static void makeMap() {
		int left = 0;
		String[] split = cnd.split("");
		for (int right = 1; right < split.length; right++) {
			if (split[left].equals(split[right])) {
				map[right] = map[right - 1] + 1;
				left++;
			} else {

			}
		}
	}

	private static boolean getCnd() {
		int partL = cnd.length();
		for (int startIdx = 0; startIdx <= origin.length() - partL; startIdx++) {
			String part = origin.substring(startIdx, startIdx + partL);
			if (part.equals(cnd)) {
				return true;
			}
		}
		return false;
	}

	static void input(){
		origin = scan.next();
		cnd = scan.next();
		map = new int[origin.length()];
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
