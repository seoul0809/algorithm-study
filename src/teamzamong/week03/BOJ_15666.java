import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 
	BOJ 15666: N과 M (12) 
	https://www.acmicpc.net/problem/15666
	
	N과 M인 자연수 중에서 M개를 고른 수열.
	같은 수를 여러번 골라도 되지만, 고른 수열은 비내림차순이어야 함.
	중복된 순열이 존재하면 안 됨!
	
	1. 같은 숫자를 여러 번 골라도 됨 
	   => 새로운 숫자를 고를 때 주어진 숫자 배열의 다음 숫자가 아닌 자기 자신을 다시 선택할 수 있도록 함
	2. 비내림차순 수열이어야 함 
	   => 주어진 숫자 배열을 미리 정렬해놓고 그 순서대로 수열에 넣도록 함
	3. 중복된 수열이 존재하면 안 됨
	   => 만약 지금 넣으려는 숫자가 이미 전에 넣었던 숫자라면 continue
	   
	시간: 148ms
*/

public class BOJ_15666 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder result = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] nums = new int[N];
		int[] ans = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(nums);

		getPerm(result, nums, ans, N, M, 0, 0);

		System.out.println(result);

	}

	private static void getPerm(StringBuilder sb, int[] nums, int[] ans, int N, int M, int start, int depth) {
		if (depth == M) {
			for (int n : ans) {
				sb.append(n).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = start; i < N; ++i) {
			if (i > 0 && nums[i - 1] == nums[i])
				continue;

			ans[depth] = nums[i];

			getPerm(sb, nums, ans, N, M, i, depth + 1);
		}
	}
}
