package Baekjoon.시뮬레이션구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ3107_IPv6 {
	static FastReader scan = new FastReader();
	static String[] answer = new String[8];
	static String[] input;

	public static void main(String[] args) {
		input();
		process();
	}

	private static void process() {
		for (int front = 0; front < input.length; front++) {
			if (input[front].equals("")) {
				int answerIdx = answer.length - 1;
				for (int back = input.length - 1; back >= 0; back--) {
					if (input[back].equals("")) {
						break;
					}
					answer[answerIdx] = restore(input[back]);
					answerIdx--;
				}
				break;
			}
			answer[front] = restore(input[front]);
		}
		System.out.println(String.join(":", answer));
	}

	private static String restore(String before) {
		StringBuilder after = new StringBuilder();
		if (before.length() < 4) {
			after.append("0".repeat(4 - before.length()));
		}
		after.append(before);
		return after.toString();
	}

	static void input(){
		Arrays.fill(answer, "0000");
		input = scan.nextLine()
					.split(":");
	}
	static class FastReader {
	  BufferedReader br;
	  StringTokenizer st;
	  public FastReader() {
		  br = new BufferedReader(new InputStreamReader(System.in));
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
