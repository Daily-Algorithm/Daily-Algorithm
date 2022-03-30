package 모든문제;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PG다단칫솔판매 {
	static Map<String, Person> personMap = new HashMap<>();
	static Map<String, Integer> earnMap = new HashMap<>();
	static Map<String, Integer> actualMap = new HashMap<>();

	private static class Person {
		String refer, name;
		public Person(String refer, String name) {
			this.refer = refer;
			this.name = name;
		}
	}

	public static List<Integer> solution(
		String[] enroll,
		String[] referral,
		String[] seller,
		int[] amount
	) {
		personMap.put("center", new Person("", "center"));
		actualMap.put("center", 0);
		makePerson(enroll, referral);	// personMap에 기록
		for (int i = 0; i < seller.length; i++) {
			String sellPName = seller[i];
			int earn = amount[i] * 100;
			divideEarn(sellPName, earn);
		}
		return printByEnrollOrder(enroll);
	}

	private static void divideEarn(String personName, int firstEarn) {
		String curPName = personName;
		int earned = firstEarn;
		while (!curPName.equals("center") && earned != 0) {
			int curPCommission = (int) Math.floor(earned * 0.1);
			int curPGet = earned - curPCommission;
			actualMap.put(curPName, actualMap.get(curPName) + curPGet);
			curPName = personMap.get(curPName).refer;
			earned = curPCommission;
		}
	}

	private static List<Integer> printByEnrollOrder(String[] enroll) {
		List<Integer> answer = new ArrayList<>();
		for (String name : enroll) {
			int all = 0;
			all += actualMap.get(name);
			answer.add(all);
			System.out.println(name + " : " + all);
		}
		return answer;
	}

	private static void makeEarnMap(String[] seller, int[] amount) {
		for (int i = 0; i < amount.length; i++) {
			String person = seller[i];
			Integer earn = amount[i] * 100;
			if (earnMap.containsKey(person)) {
				earnMap.put(person, earnMap.get(person) + earn);
			} else {
				earnMap.put(person, earn);
			}
		}
	}

	private static void makePerson(String[] enroll, String[] referral) {
		for (int i = 0; i < enroll.length; i++) {
			Person referP = referral[i].equals("-") ? personMap.get("center") : personMap.get(referral[i]);
			Person enrollP = new Person(referP.name, enroll[i]);
			personMap.put(enrollP.name, enrollP);
			actualMap.put(enrollP.name, 0);
		}
	}

	public static void main(String[] args) {
		String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
		String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
		String[] seller = {"young", "john", "tod", "emily", "mary"};
		int[] amount = {12, 4, 2, 5, 10};
		System.out.println(solution(enroll, referral, seller, amount));
	}
}
