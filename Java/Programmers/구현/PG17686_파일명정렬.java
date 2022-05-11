package 구현;

import java.util.Arrays;

public class PG17686_파일명정렬 {
	public static String[] solution(String[] files) {
		Arrays.sort(files, ((o1, o2) -> {
			String[] devideO1 = devide(o1);
			String[] devideO2 = devide(o2);
			int headCompare = devideO1[0].compareToIgnoreCase(devideO2[0].toUpperCase());
			if (headCompare < 0) {
				// head 비교
				return -1;
			} else if (headCompare > 0) {
				return 1;
			} else {
				int numberCompare = Integer.valueOf(devideO1[1])
										   .compareTo(Integer.valueOf(devideO2[1]));
				if (numberCompare < 0) {
					return -1;
				} else if (numberCompare > 0) {
					return 1;
				} else {
					return 0;
				}
			}
		}));
		return files;
	}

	private static String[] devide(String str) {
		String[] split = str.split("[0-9]+");	// 숫자 기준으로 나눔(숫자 포함X)
		String HEAD = split[0];
		String[] arr = str.substring(HEAD.length()).split("");
		int notInt = -1;
		for (int i = 0; i < arr.length; i++) {
			if (!arr[i].matches("[0-9]")) {
				notInt = i;
				break;
			}
		}
		String NUMBER;
		if (notInt != -1) {
			NUMBER = str.substring(HEAD.length(), HEAD.length() + notInt);
		} else {
			NUMBER = str.substring(HEAD.length());
		}
		return new String[]{HEAD, NUMBER};
	}

	public static void main(String[] args) {
//		String[] arr = new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF",
//			"img2.JPG"};
		String[] arr = new String[]{"img0000123aa", "img00  000.345", "img001a00png", "img2", "IMG02"};
		for (String str : arr) {
			System.out.println(Arrays.toString(devide(str)));
		}
		System.out.println(Arrays.toString(solution(arr)));
	}
}
