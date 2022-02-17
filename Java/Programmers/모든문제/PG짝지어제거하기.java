package 모든문제;

import java.util.Stack;

/**
 * 스택 이용하여 효율성 해결
 */
public class PG짝지어제거하기 {
	public static int solution(String s)
	{
		if (s.length() % 2 == 1) {
			return 0;
		}

		Stack<String> stack = new Stack();
		String[] split = s.split("");

		stack.push(split[0]);
		for (int i = 1; i < split.length; i++) {
			String wait = split[i];
			if (stack.empty()) {
				stack.push(wait);
			} else {
				String peek = stack.peek();
				if (peek.equals(wait)) {
					stack.pop();
				} else {
					stack.push(wait);
				}
			}
		}

		if (stack.empty()) {
			return 1;
		} else {
			return 0;
		}
	}

	public static void main(String[] args) {
		System.out.println(solution("baabaa"));
	}
}
