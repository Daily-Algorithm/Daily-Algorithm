package 모든문제;

public class PG최소직사각형 {
	private static int maxOfMaxLength, maxOfMinLength = 0;
	public static int solution(int[][] sizes) {
		for (int[] paper: sizes) {
			maxOfMaxLength = Math.max(maxOfMaxLength, Math.max(paper[0], paper[1]));
			maxOfMinLength = Math.max(maxOfMinLength, Math.min(paper[0], paper[1]));
		}
		return maxOfMaxLength * maxOfMinLength;
	}

	public static void main(String[] args) {
		int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
		System.out.println(solution(sizes));
	}
}
