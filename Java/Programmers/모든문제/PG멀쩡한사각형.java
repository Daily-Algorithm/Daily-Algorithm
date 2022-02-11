package 모든문제;

/**
 * solved : 그래프로 생각해서 지나가는 선(y = (h/w) x) 이하의 정사각형 수 구함
 * 1. long 변환
 * 2. (h/w) x는 오차가 생기고 h*x/w 로 해야 오차가 생기지 않음
 *
 * other : 다른 사람들은 최대공약수 이용했다는데 한번 봐봐야겠다
 */
public class PG멀쩡한사각형 {
	public static long solution(int w, int h) {
		long half = 0;
		for (int i = 0; i < w; i++) {
			double meet = (double) h * i / w;
			double max = Math.floor(meet);
			half += max;
		}

		return half * 2;
	}

	public static void main(String[] args) {
		System.out.println(solution(8,12));
	}
}
