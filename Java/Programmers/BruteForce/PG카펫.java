package BruteForce;

public class PG카펫 {
	public static int[] solution(int brown, int yellow) {
		int area = brown + yellow;
		int W = 0;
		int H = 0;

		for (int width = 1; width <= area / 2; width++) {
			if (area % width == 0) {
				int height = area / width;
				if (width + height == 2 + brown / 2 && width >= height) {
					W = width;
					H = height;
					break;
				}
			}
		}

		int[] answer = {W, H};

		return answer;
	}

	public static void main(String[] args) {
		solution(24,24);
	}
}
