package week02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
 * 프로그래머스: 같은 숫자는 싫어
 * > 주어진 배열에서 연속적으로 나타나는 숫자는 제거하고, 남은 수를 순서대로 리턴 
 * 
 * [조건]
 * - 1 <= arr.length <= 1,000,000 -> O(NlogN)
 * - 배열 원소 크기: 0 <= int <= 9
 * 
 * [풀이 방식]
 * - 이전 원소의 값(prev)를 기준으로 중복을 확인한 후, List에 추가
 * - 연속적 중복은 안 되지만, 배열 내 중복은 가능하기 때문에 Set은 안 됨
 */

public class PRG_12906 {

	public static int[] solution(int[] arr) {

		// 미리 결과 배열의 크기를 알 수 없기 때문에 List에 숫자 저장
		List<Integer> list = new ArrayList<>();

		int prev = arr[0];
		list.add(prev);

		// 중복 확인 후 저장
		for (Integer n : arr) {
			if (prev != n)
				list.add(n);
			prev = n;
		}

		// List<Integer> -> int[]
		int[] answer = new int[list.size()];
		for (int i = 0, size = answer.length; i < size; i++) {
			answer[i] = list.get(i);
		}

		return answer;
	}

	// 테스트 코드
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] { 1, 1, 3, 3, 0, 1, 1 })));
		System.out.println(Arrays.toString(solution(new int[] { 4, 4, 4, 3, 3 })));
	}

}
