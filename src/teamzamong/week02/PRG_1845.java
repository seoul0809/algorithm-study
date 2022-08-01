package week02;

import java.util.HashSet;
import java.util.Set;

/* 
 * 프로그래머스: 폰켓몬
 * > 폰켓몬의 종류 번호가 담긴 배열(nums)을 받아 N/2개의 폰켓몬을 고를 때, 
 * > 가장 많은 종류의 폰켓몬을 선택하는 방법 
 * 
 * [조건]
 * - 1 <= nums.length <= 10000
 * - 종류 번호 n: 1 <= n <= 2000000
 * 
 * [풀이 방식]
 * - 폰켓몬의 종류가 N/2개 이상일 때, 최대로 고를 수 있는 것은 N/2개의 폰켓몬이다.
 * - 폰켓몬의 종류가 N/2개 미만일 때, 최대로 고를 수 있는 것은 총 폰켓몬 종류 수와 같다.
 * => 결국 종류가 몇 개인지만 세면 된다! (중복 제거해주는 Set 활용)
 */

public class PRG_1845 {

	public static int solution(int[] nums) {

		Set<Integer> set = new HashSet<>();

		for (int n : nums) {
			set.add(n);
		}

		return (set.size() >= (nums.length / 2)) ? nums.length / 2 : set.size();
	}

	// 테스트 코드
	public static void main(String[] args) {
		System.out.println(solution(new int[] { 3, 1, 2, 3 }));
		System.out.println(solution(new int[] { 3, 3, 3, 2, 2, 4 }));
		System.out.println(solution(new int[] { 3, 3, 3, 2, 2, 2 }));
	}

}
