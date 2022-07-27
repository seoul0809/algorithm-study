package week02;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* 
 * 프로그래머스: 영어 끝말잇기 
 * > N명이 끝말잇기한 내용이 String[] 배열로 주어질 때,
 * > 가장 먼저 탈락하는 사람의 번호와 몇 번째 round에서 탈락하는지를 int[]로 리턴 
 * > 다들 통과하면 [0, 0] 리턴
 * 
 * [조건]
 * - N : 2 <= N <= 10	  
 * - 배열의 길이: N <= words.length() <= 100
 * - 단어의 길이: 2 <= word.length <= 50 
 * - 탈락하는 조건: 1) 끝말잇기 아닐 때 2) 중복일 때
 * 
 * [풀이 방식]
 * - 주어진 배열을 순회하면서 아래를 확인
 * 	 1) 중복인지
 * 	 2) 끝말잇기 조건을 만족하는지
 * - 중복인지 확인하기 위해서 Set을 사용
 * 
 */

public class PRG_12981 {

	public static int[] solution(int n, String[] words) {

		// 중복을 확인하기 위해서 나온 단어들을 Set으로 관리
		Set<String> wordSet = new HashSet<>();

		int person = 1; // 현재 단어를 말한 사람
		int round = 1; // 몇번째 round인지

		String prev = words[0], curr; // 바로 전에 나온 단어, 현재 단어
		wordSet.add(prev);

		// 1번째 사람의 1번째 round는 앞에서 이미 처리해서 i = 1부터 시작
		for (int i = 1, size = words.length; i < size; i++) {
			// 몇번째 사람인지, 몇번째 round인지 관리
			++person;
			if (person > n) {
				person = 1;
				++round;
			}

			curr = words[i];
			
			// 단어 중복 확인 및 끝말잇기 조건 확인
			if (wordSet.contains(curr) || prev.charAt(prev.length() - 1) != curr.charAt(0))
				return new int[] { person, round };

			// 새로운 단어 추가 및 관련 변수 업데이트
			wordSet.add(curr);
			prev = curr;
		}

		// 중간에 return 안 했다면 문제 없는 것
		return new int[] { 0, 0 };
	}

	// 테스트 코드
	public static void main(String[] args) {

		System.out.println(Arrays.toString(solution(3,
				new String[] { "tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank" })));
		System.out.println(Arrays.toString(
				solution(5, new String[] { "hello", "observe", "effect", "take", "either", "recognize", "encourage",
						"ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive" })));
		System.out.println(
				Arrays.toString(solution(2, new String[] { "hello", "one", "even", "never", "now", "world", "draw" })));

	}

}
