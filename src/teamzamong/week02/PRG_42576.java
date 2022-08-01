package week02;

import java.util.HashMap;
import java.util.Map;

/* 
 * 프로그래머스: 완주하지 못한 선수
 * > 마라톤에 참여한 선수 이름 배열과 완주한 선수 이름 배열이 주어졌을 때,
 * > 완주하지 못한 선수의 이름을 리턴 
 * 
 * [조건]
 * - 1 <= 선수 수 <= 1000000
 * - 완주하지 못한 선수가 언제나 한 명이 있음
 * - 이름은 (1, 20) 길이의 알파벳 소문자로 이루어짐
 * - 참가자 중 동명이인이 있을 수 있음 => Set 안 됨
 * 
 * [풀이 방식]
 * - Map<이름, 명수>를 만든 후, 완주한 선수 목록에 있다면 제거하기
 */

public class PRG_42576 {

	public static String solution(String[] participant, String[] completion) {

		Map<String, Integer> map = new HashMap<>();

		// 마라톤에 참여한 선수에 대한 map 생성
		for (String name : participant) {
			if (!map.containsKey(name)) {
				map.put(name, 1);
			} else {
				map.put(name, map.get(name) + 1);
			}
		}
		
		// 완주한 선수 목록과 비교
		for (String name : completion) {
			if (map.get(name) == 1) {
				map.remove(name);
			} else {
				map.put(name, map.get(name) - 1);
			}
		}
		
		// map에 하나의 선수만 남아있을 것
		String answer = map.keySet().iterator().next();
		
		return answer;
	}

	// 테스트 코드
	public static void main(String[] args) {
		 System.out.println(solution(new String[] {"leo", "kiki", "eden"}, new String[] {"eden", "kiki"}));
		 System.out.println(solution(new String[] {"marina", "josipa", "nikola", "vinko", "filipa"}, new String[] {"josipa", "filipa", "marina", "nikola"}));
		 System.out.println(solution(new String[] {"mislav", "stanko", "mislav", "ana"}, new String[] {"stanko", "ana", "mislav"}));
	}

}
