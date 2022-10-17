package week02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
 * 프로그래머스: 자연수 뒤집어 배열로 만들기 
 * > 자연수 N을 뒤집어 각 자리 숫자를 원소로 가지는 배열 형태로 리턴 
 * 
 * [조건]
 * - N : 10^10 이하의 자연수 => long 범위	  
 * 
 * [풀이 방식]
 * - List를 사용해 각 자리수를 저장 후, 배열로 리턴
 */

public class PRG_12932 {

	public static int[] solution(long n) {

		// 미리 n의 자리수를 알 수 없기 때문에 List에 각 자리의 숫자를 저장
		List<Integer> list = new ArrayList<>();

		while (n > 0) {
			list.add((int) (n % 10)); // 10으로 나눈 나머지 r는 int 범위임이 보장됨 (r < 10)
			n /= 10;
		}

		// List를 int 배열에 저장
		int[] answer = new int[list.size()];
		for (int i = 0, size = answer.length; i < size; i++) {
			answer[i] = list.get(i);
		}

		return answer;
	}

	// 테스트 코드
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(12345)));

	}

}
