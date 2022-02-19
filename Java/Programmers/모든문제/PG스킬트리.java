package 모든문제;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PG스킬트리 {
	public static int solution(String skill, String[] skill_trees) {
		List<String> orders = Arrays.asList(skill.split(""));

		int count = 0;
		for (String tree : skill_trees) {
			String[] treeSplit = tree.split("");
			List<String> treeOrder = new ArrayList<>();
			for (int i = 0; i < treeSplit.length; i++) {
				if (orders.contains(treeSplit[i])) {
					treeOrder.add(treeSplit[i]);
				}
			}
			String treeOrderStr = String.join("", treeOrder);
			if (skill.equals(treeOrderStr)
				|| (treeOrderStr.contains(orders.get(0)) && skill.contains(treeOrderStr))
				|| treeOrderStr.equals("")) {
				System.out.println(treeOrderStr);
				count++;
			}
		}

		return count;
	}

	public static void main(String[] args) {
		String skill = "CBDFE";
		String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA","CDF"};
		System.out.println(solution(skill, skill_trees));
	}
}
