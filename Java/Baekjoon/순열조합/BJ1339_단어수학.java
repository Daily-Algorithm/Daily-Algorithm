package 순열조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ1339_단어수학 {
	static int n;
	static FastReader scan = new FastReader();
	static List<String> words;
	static int answer = 0;
	static Map<String, Integer> multiples = new HashMap<>();

	public static void main(String[] args) {
		input();
		makeOrderAndAnswer();
		System.out.println(answer);
	}

	private static void makeOrderAndAnswer() {
		words = new ArrayList<>(multiples.keySet());
		// 알파벳의 배수가 큰 순서대로 정렬
		words.sort((o1, o2) -> multiples.get(o2) - multiples.get(o1));
		int max = 9;
		for (String word : words) {
			// 가장 배수가 큰 알파벳에 max값 부여하면 최대가 된다
			answer += max * multiples.get(word);
			max--;
		}
	}


	static void input(){
		n = scan.nextInt();

		for (int i = 0; i < n; i++) {
			String[] split = scan.nextLine().split("");
			int maxDigit = split.length;
			for (int j = 0; j < maxDigit; j++) {
				// alphabet마다의 배수 만들기
				String alphabet = split[j];	// 해당 알파벳
				int alphabetDigit = maxDigit - j - 1;	// 해당 알파벳의 자리수
				int multiple = (int) Math.pow(10, alphabetDigit);	// 자리수를 배수로 변환
				if (multiples.containsKey(alphabet)) {
					// 알파벳의 총 배수를 구하기 위해 알파벳의 multiple들을 모두 더하기
					multiples.put(alphabet, multiples.get(alphabet) + multiple);
				} else {
					multiples.put(alphabet, multiple);
				}
			}
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
