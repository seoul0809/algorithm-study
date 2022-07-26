package week02;

/* 
 * 프로그래머스: 순열 검사
 * > 길이가 N인 배열에 1부터 N까지 숫자가 중복 없이 한 번씩 들어있는지 확인 
 * 
 * [조건]
 * - 배열의 길이: 10만 이하			  
 * - 배열의 원소: 0 이상 10만 이하의 정수 => int 범위
 * 
 * [풀이 방식]
 * - checkUsed[N + 1] 배열을 사용해 중복 확인
 * - 주어진 배열의 원소가 1~N의 범위를 벗어나거나 중복된 값이 있을 경우 먼저 break 
 */

public class PRG_1877 {

	// boolean 배열을 통한 중복 체크 및 범위 확인
	public static boolean solution(int[] arr) {
		
		int N = arr.length;

		boolean[] checkUsed = new boolean[N + 1];
		checkUsed[0] = true;
		
		for (int i : arr) {
			if (i > N) 				// i = 0, i > N인 경우
				return false;
			if (checkUsed[i]) 		// i가 이미 배열에 사용됐던 경우
				return false;
			checkUsed[i] = true; 	// 이미 나온 숫자임을 표시
		}
		
		return true;
		
	}

	// 테스트 코드
	public static void main(String[] args) {
		int[] arr = { 4, 1, 3, 2 };
		int[] arr2 = { 4, 1, 3 };

		System.out.println(solution(arr));
		System.out.println(solution(arr2));

	}

}
