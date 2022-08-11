package 모든문제;

public class PG6006_외벽점검 {
	static int[][] weaksArrs;
	static int[] Ds;
	static int result = Integer.MAX_VALUE;

	public static int solution(int n, int[] weak, int[] dist) {
		initial(n, weak, dist);
		for (int[] weaks : weaksArrs) {
			permutation(weaks, new boolean[dist.length], 0, 0);
		}
		return result == Integer.MAX_VALUE ? -1 : result;
	}

	private static void initial(int n, int[] weak, int[] dist) {
		Ds = dist;
		makeArrs(n, weak);
	}

	private static void permutation(int[] weaks, boolean[] isSent, int nextIdx, int sentFs) {
		// nextIdx = weaks의 nextIdx 미만의 원소들은 모두 점검된 취약지점, 즉 다음으로 확인해야할 취약지점의 idx
		if (nextIdx == weaks.length) {
			// 취약지점 모두 전부 확인했다면
			result = Math.min(result, sentFs);
			return;
		}
		for (int i = 0; i < Ds.length; i++) {
			if (isSent[i]) continue;	// 이미 보내진 친구이면 다음 친구로 넘어가기
			int ableToLook = weaks[nextIdx] + Ds[i];	// ableToLook = 볼 수 있는 최대 포인트
			int checkedIdx = nextIdx;
			for (int weakIdx = nextIdx; weakIdx < weaks.length; weakIdx++) {
				if (weaks[weakIdx] > ableToLook) break;	// 만약 weaks의 weakIdx 원소가 볼 수 없는 포인트이면 break
				checkedIdx++;	// 볼 수 있는 포인트이면 checkedIdx++
			}
			isSent[i] = true;	// 보내진 친구 표시
			permutation(weaks, isSent, checkedIdx, sentFs + 1);	// 보내진 친구의 수 증가
			isSent[i] = false;
		}
	}

	private static void makeArrs(int n, int[] weak) {
		weaksArrs = new int[weak.length][weak.length];
		for (int i = 0; i < weak.length; i++) {
			for (int j = i; j < weak.length; j++) {
				weaksArrs[i][j - i] = weak[j];
			}
			for (int k = 0; k < i; k++) {
				weaksArrs[i][weak.length - i + k] = n + weak[k];
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(solution(12, new int[]{1, 5, 6, 10}, new int[]{1, 2, 3, 4}));
		System.out.println(solution(12, new int[]{1, 3, 4, 9, 10}, new int[]{3, 5, 7}));
	}
}
