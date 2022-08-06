
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
// https://www.acmicpc.net/problem/15663
/*
 N개의 자연수와 자연수 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 
 예시 입력)
 4 2
 9 7 9 1
 예시 출력)
 1 7
 1 9
 7 1
 7 9
 9 1
 9 7
 9 9
 
 수열이 중복되면 안된다.
 */
public class BOJ_15663 {
	static int N;
	static int M;
	static StringBuilder sb;
	static StringBuilder num;
	static int[] nums;
	static String[] arr; 
	static Set<String> set; // 중복을 제거하기 위해 set에 수열을 저장
	static boolean[] isSelected; // 수열 내에서도 값이 반복되지 않기 때문에 isSelected로 중복 체크
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		N = sc.nextInt();
		M = sc.nextInt();
		nums = new int[N];
		arr = new String[M];
		set = new LinkedHashSet<>(); // iteration 작업을 할 경우, Set에 삽입된 순서대로 접근
		isSelected = new boolean[N];
		for (int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
		}
		Arrays.sort(nums);
		perm(0);
		Iterator<String> iter = set.iterator(); // set을 Iterator 안에 담기
		while(iter.hasNext()) { // iterator에 다음 값이 있다면
			System.out.print(iter.next()); // iter에서 값 꺼내기
		}
	}
	
	private static void perm(int count) {
		if(count == M) {
			num = new StringBuilder();
			for (int i = 0; i < M; i++) {
				num.append(arr[i]).append(" ");
			}
			set.add(num.append("\n").toString());
			return;
		}
		for (int i = 0; i < N; i++) {
			if(isSelected[i]) continue;
			arr[count] = String.valueOf(nums[i]);
			isSelected[i] = true;
			perm(count + 1);
			isSelected[i] = false;
			
		}
	}

}
