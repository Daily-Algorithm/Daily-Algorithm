package 모든문제;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PG오픈채팅 {
	public static List<String> solution(String[] record) {
		List<String> answer = new ArrayList<>();
		List<String> results = new ArrayList<>();
		List<String> ids = new ArrayList<>();

		Map<String, String> nicknameMap = new HashMap<>();

		for (String string : record) {
			String[] split = string.split(" ");
			String order = split[0];
			String id = split[1];
			String nickname = null;
			if (split.length == 3) {
				nickname = split[2];
			}

			switch (order) {
				case "Enter" :
					// results, id에 기록, map에 기록
					results.add("님이 들어왔습니다.");
					ids.add(id);
					nicknameMap.put(id, nickname);
					break;
				case "Leave" :
					// results, ids에 기록
					results.add("님이 나갔습니다.");
					ids.add(id);
					break;
				case "Change" :
					// map에 add
					nicknameMap.put(id, nickname);
					break;
			}
		}

		for (int i = 0; i < results.size(); i++) {
			String result = results.get(i);
			String nickname = nicknameMap.get(ids.get(i));
			answer.add(nickname + result);
		}

		return answer;
	}

	public static void main(String[] args) {
		String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		System.out.println(solution(record));
	}
}
