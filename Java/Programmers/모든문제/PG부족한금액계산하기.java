package 모든문제;

public class PG부족한금액계산하기 {
	public static long solution(int price, int money, int count) {
		long need = 0;
		for (long i = 1; i <= count; i++) {
			need += i * price;
		}
		return need > money ? need - money : 0;
	}
	public static void main(String[] args) {
		int price= 3;
		int money= 20;
		int count= 4;
		System.out.println(solution(price, money, count));
	}
}
