package Hash;

import java.util.*;

public class PG베스트앨범 {
	public static List<Integer> solution(String[] genres, int[] plays) {
		Map<String, int[][]> songMap = new HashMap<>();
		Map<String, Integer> genreOrder = new HashMap<>();

		for (int i = 0; i < genres.length; i++) {
			if (songMap.containsKey(genres[i])) {
				int[][] arrOfArr = songMap.get(genres[i]);
				int[] indexPlayArr = {i, plays[i]};
				int[][] newArrOfArr = new int[arrOfArr.length+1][];

				System.arraycopy(arrOfArr, 0, newArrOfArr, 0, arrOfArr.length);

				newArrOfArr[arrOfArr.length] = indexPlayArr;
				songMap.put(genres[i], newArrOfArr);
				genreOrder.put(genres[i], genreOrder.get(genres[i])+plays[i]);
			} else {
				int[] indexPlayArr = {i, plays[i]};
				int[][] arrOfArr = new int[1][];
				arrOfArr[0] = indexPlayArr;
				songMap.put(genres[i], arrOfArr);
				genreOrder.put(genres[i], plays[i]);
			}
		}

		List<String> genreList = new ArrayList<>(genreOrder.keySet());
		genreList.sort(((o1, o2) -> (genreOrder.get(o2)
											   .compareTo(genreOrder.get(o1)))));

		List<Integer> answer = new ArrayList<>();

		for (String genre : genreList) {
			int[][] arrOfArr = songMap.get(genre);
			if (arrOfArr.length >= 2) {
				Arrays.sort(arrOfArr, ((o1, o2) -> o2[1]-o1[1]));
				songMap.put(genre, arrOfArr);
				answer.add(arrOfArr[0][0]); answer.add(arrOfArr[1][0]);
			} else {
				answer.add(arrOfArr[0][0]);
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		String[] gen ={"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		System.out.println(solution(gen, plays));
	}
}