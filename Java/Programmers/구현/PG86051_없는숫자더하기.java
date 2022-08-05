package 구현;

public class PG86051_없는숫자더하기 {

	public int solution(int[] numbers) {
		int result = 45;
		for (int number : numbers) {
			result -= number;
		}
		return result;
	}

}
