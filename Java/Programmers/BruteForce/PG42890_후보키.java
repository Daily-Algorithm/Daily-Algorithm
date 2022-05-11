package BruteForce;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PG42890_후보키 {
	static int columns, rows;
	static String[][] map;
	static Set<Set<Integer>> uniqueIndexes = new HashSet<>();

	public static int solution(String[][] relations) {
		initialize(relations);
		chooseColumns(0, new boolean[columns], "");
		return uniqueIndexes.size();
	}

	private static void initialize(String[][] relations) {
		rows = relations.length;
		columns = relations[0].length;
		map = relations;
	}

	private static void chooseColumns(
		int dept,
		boolean[] visited,
		String chosen
	) {
		if (dept == columns) {
			if (!chosen.equals("")) {
				isUniAndMinKey(chosen);
			}
			return;
		}
		visited[dept] = true;
		chooseColumns(dept + 1, visited, chosen + dept + " "); //"0 1 2"
		visited[dept] = false;
		chooseColumns(dept + 1, visited, chosen);
	}

	private static void isUniAndMinKey(String chosen) {
		int[] indexes = Arrays.stream(chosen.split(" "))
							  .mapToInt(Integer::valueOf)
							  .toArray();
		if (isUniqueKey(indexes)) {
			Set<Integer> subset = new HashSet<Integer>();
			Arrays.stream(indexes).forEach(subset::add);

			List<Set<Integer>> collect = uniqueIndexes.stream()
													  .filter(set -> set.containsAll(subset))
													  .collect(Collectors.toList());

			collect.forEach(s -> uniqueIndexes.remove(s));
			uniqueIndexes.add(subset);
		}
	}

//	private static void isCandKey(String indexes) {
//		if (isUniqueKey(indexes)) {
//			if (candMap.containsKey(indexes.size())) {
//				candMap.put(indexes.size(), candMap.get(indexes.size()) + 1);
//			} else {
//				candMap.put(indexes.size(), 1);
//			}
//		}
//	}

	private static boolean isUniqueKey(int[] indexes) {
		Set<String> set = new HashSet<>();
		for (int rowIdx = 0; rowIdx < rows; rowIdx++) {
			StringBuilder sb = new StringBuilder();
			for (int index : indexes) {
				sb.append(map[rowIdx][index] + " ");
			}
			if (set.contains(sb.toString())) {
				return false;
			} else {
				set.add(sb.toString());
			}
		}
//		System.out.println(indexes);
		return true;
	}
//	private static void combination(int[] comArr, int n, int r, int index, int target) {
//		if(r==0){
//			for(int i : comArr){
//				System.out.print(i+" ");
//			}
//			System.out.println();
//			return;
//		}
//		if(target == n)return;
//
//		comArr[index] = target;
//		combination(comArr, n, r-1, index+1, target+1);//뽑는경우
//		combination(comArr, n, r, index, target+1);//안뽑는경우
//
//	}

	public static void main(String[] args) {
//		String[][] relations = {{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"},
//			{"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"},
//			{"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}};
		String[][] relations = {
			{"a","1","aaa","c","ng"},
			{"a","1","bbb","e","g"},
			{"c","1","aaa","d","ng"},
			{"d","2","bbb","d","ng"}};
		System.out.println(solution(relations));
	}
}
