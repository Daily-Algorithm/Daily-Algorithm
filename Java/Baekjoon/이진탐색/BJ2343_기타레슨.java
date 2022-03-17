package 이진탐색;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * left와 right 중에 작은 부분이 mid + 1 변화가 일어나야 함
 */
public class BJ2343_기타레슨 {
	static int videoNums, bluerays;
	static int sumOfVideo, max = 0;
	static int[] videos;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		videoNums = Integer.parseInt(st.nextToken());
		bluerays = Integer.parseInt(st.nextToken());
		videos = new int[videoNums];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < videoNums; i++) {
			videos[i] = Integer.parseInt(st.nextToken());
			sumOfVideo += videos[i];
			max = Math.max(max, videos[i]);
		}

		// max <= answer <= sum
		// left >= max
		int left = sumOfVideo;
		int right = max;
		while (left > right) {
			int mid = (left + right) / 2;
			if (bluerays >= bundle(mid)) {
				// bundle이 적거나 같으면
				left = mid;
			} else {
				right = mid + 1;
			}
		}
		out.println(left);
	}

	private static int bundle(int limit) {
		int bundle = 0;
		int sizeOfBundle = 0;
		for (int i = 0; i < videoNums; i++) {
			int ifIn = sizeOfBundle + videos[i];
			if (ifIn > limit) {
				bundle++;
				sizeOfBundle = videos[i];
			} else if (ifIn < limit) {
				sizeOfBundle += videos[i];
			} else {
				bundle++;
				sizeOfBundle = 0;
			}
		}
		return sizeOfBundle > 0 ? bundle + 1 : bundle;
	}
}
