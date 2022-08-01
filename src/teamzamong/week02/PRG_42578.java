package week02;

import java.util.HashMap;
import java.util.Map;

/* 
 * 프로그래머스: 위장
 * > 2차원 배열 clothes가 주어질 때 서로 다른 옷의 조합의 수를 return
 * > clothes의 각 행은 [의상의 이름, 의상의 종류]로 이루어져 있음
 * 
 * [조건]
 * - 1<= 의상의 수 <= 30
 * - 같은 이름인 의상 존재하지 않음
 * - 문자열 배열이며, 1 <= 문자열의 길이 <= 20
 * - 하루 최소 한 개의 의상을 입음
 * 
 * [풀이 방식]
 * - 가능한 서로 다른 옷의 가지수는 (각 의상 종류 개수 + 1)를 곱한 것 - 1과 같음
 *  	=> 각 의상 종류 개수 + 1 : 각 의상 종류 + 해당 종류 옷 안 입었을 때
 * 		=> -1 하는 것은 아무것도 안 입은 경우 제외
 */

public class PRG_42578 {

	public static int solution(String[][] clothes) {

		// 의상 종류, 개수
		Map<String, Integer> map = new HashMap<>();

		// 의상 종류와 각각 개수 세기
		for (String[] clothing : clothes) {
			if (!map.containsKey(clothing[1])) {
				map.put(clothing[1], 1);
			} else {
				map.put(clothing[1], map.get(clothing[1]) + 1);
			}
		}

		// 의상 종류 개수 곱하기
		int kinds = 1;

		for (Integer i : map.values()) {
			kinds *= (i + 1);
		}

		return kinds - 1;

	}

	// 테스트 코드
	public static void main(String[] args) {
		System.out.println(solution(new String[][] { { "yellow_hat", "headgear" }, { "blue_sunglasses", "eyewear" },
				{ "green_turban", "headgear" } }));
		System.out.println(solution(
				new String[][] { { "crow_mask", "face" }, { "blue_sunglasses", "face" }, { "smoky_makeup", "face" } }));
	}

}
