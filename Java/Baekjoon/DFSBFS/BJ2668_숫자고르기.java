package DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BJ2668_숫자고르기 {
	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();
	static int n;
	static int[] first;
	static int[] second;
	static LinkedList<Integer>[] adj;
	static TreeSet<Integer> answer = new TreeSet<>();

	public static void main(String[] args) {
		input();
		process();
		print();
	}

	private static void print() {
		sb.append(answer.size()).append("\n");
		answer.forEach(integer -> sb.append(integer).append("\n"));
		System.out.print(sb.toString());
	}

	private static void process() {
		for (int i = 1; i <= n; i++) {
			bfs(i, new boolean[n + 1], i, new ArrayList<>());
		}
	}

	private static void bfs(int startP, boolean[] visited, int first, List<Integer> list) {
		visited[startP] = true;
		list.add(startP);
		Integer next = adj[startP].get(0);
		if (!visited[next]) {
			bfs(next, visited, first, list);
		} else {
			if (next == first) answer.addAll(list);
			return;
		}

	}

	static void input(){
		n = scan.nextInt();
		first = new int[n + 1];
		second = new int[n + 1];
		adj = new LinkedList[n + 1];
		for (int i = 1; i <= n; i++) {
			first[i] = i;
			second[i] = scan.nextInt();
			adj[i] = new LinkedList<>();
			adj[i].add(second[i]);
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
