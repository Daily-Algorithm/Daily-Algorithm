package 구현;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class PG76502_괄호회전하기 {

	static Set<String> left = new HashSet<>();
	static Map<String, String> pair = new HashMap<>();

	public static int solution(String s) {
		left.add("{");	left.add("(");	left.add("[");
		pair.put("{", "}");	pair.put("(", ")");	pair.put("[", "]");

		int answer = 0;
		for (int i = 0; i < s.length(); i++) {
			Stack<String> stack = setStack(s, i);
			Stack<String> rightStack = new Stack<>();

			boolean right = true;
			while (!stack.isEmpty()) {
				String cur = stack.pop();
				if (left.contains(cur)) {
					if (rightStack.isEmpty() || !pair.get(cur).equals(rightStack.pop())) {
						right = false;
						break;
					}
				} else {
					rightStack.add(cur);
				}
			}

			if (stack.isEmpty() && rightStack.isEmpty() && right) {
				answer++;
			}
		}

		return answer;
	}

	private static Stack<String> setStack(String s, int idx) {
		Stack<String> stack = new Stack<>();
		String[] split = s.split("");
		stack.addAll(Arrays.asList(split).subList(idx, s.length()));
		stack.addAll(Arrays.asList(split).subList(0, idx));
		return stack;
	}

	public static void main(String[] args) {
		System.out.println(solution("[](){}"));
	}
}
