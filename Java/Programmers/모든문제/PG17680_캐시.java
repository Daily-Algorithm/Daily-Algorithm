package 모든문제;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU cache Algorithm + 일부 조건 처리 구현하는 문제
 */

public class PG17680_캐시 {
	private static Map<String, ListNode> nodeMap = new HashMap<>();
	private static int capacity;
	private static ListNode head = new ListNode(null);
	private static ListNode tail = new ListNode(null);
	private static int time = 0;

	public static int solution(int cacheSize, String[] cities) {
		init(cacheSize);
		for (String city : cities) {
			put(city.toLowerCase());
		}
		return time;
	}

	private static void init(int cacheSize) {
		capacity = cacheSize;
		head.next = tail;
		tail.prev = head;
	}

	private static void remove(ListNode node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		nodeMap.remove(node.key);
	}

	private static void insertToHead(ListNode node) {
		head.next.prev = node;
		node.next = head.next;
		node.prev = head;
		head.next = node;
		nodeMap.put(node.key, node);
	}

	public static void put(String key) {
		ListNode newNode = new ListNode(key);
		if (nodeMap.containsKey(key)) {
			time += 1;
			ListNode oldNode = nodeMap.get(key);
			remove(oldNode);
		} else {
			time += 5;
			if (nodeMap.size() >= capacity && capacity > 0) {
				ListNode tailNode = tail.prev;
				remove(tailNode);
			}
		}
		if (capacity != 0) {
			insertToHead(newNode);
		}
	}

	public static void main(String[] args) {
		System.out.println(solution(
			0,
			new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"}
		));
	}

	private static class ListNode {
		private String key;
		private ListNode prev;
		private ListNode next;

		public ListNode(String key) {
			this.key = key;
			this.prev = null;
			this.next = null;
		}
	}
}
