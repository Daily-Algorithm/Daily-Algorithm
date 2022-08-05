package 구현;

public class PG87389_나머지가1이되는수 {
	public int solution(int n) {
		int answer = n - 1;
		int sqrt = (int) Math.sqrt(n - 1);
		for (int i = 2; i <= sqrt; i++) {
			if ((n - 1) % i == 0) {
				answer = i;
				break;
			}
		}
		return answer;
	}
}
