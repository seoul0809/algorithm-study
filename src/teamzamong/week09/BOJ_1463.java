import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
	BOJ 1463: 1로 만들기
	https://www.acmicpc.net/problem/1463
	메모리/시간: 18116kb/140ms
	
	[문제]
	정수 X가 주어졌을 때, 아래 연산 3개를 사용해 1을 만들기 위해 드는 최소 연산 횟수
	1. X % 3 == 0, X / 3
	2. X % 2 == 0, X / 2
	3. X - 1
	- 1 <= X <= 10^6
	
	[풀이]
	- 1차원 배열을 사용한 DP로 풀이
	- 동적 테이블 정의
		- index: 1로 만들려는 숫자
		- values: 해당 숫자를 1로 만들기 위해 필요한 연산 횟수 최솟값
	- 기본값 및 점화식
		- 기본값: dp[1] = 0;
		- 점화식: dp[n] = min(dp[i-1]+1, 
			                    if i % 2 == 0, dp[i/2] + 1
			                    if i % 3 == 0, dp[i/3] + 1);

*/

public class BOJ_1463 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine().trim());

		System.out.println(dp(N));

		br.close();
	}

	private static int dp(int n) {

		// -1 연산이 있기 때문에 중간에 못 만드는 숫자는 없음!
		int[] dp = new int[n + 1];


		for (int i = 2; i <= n; ++i) {

			dp[i] = dp[i - 1] + 1;

			if (i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			}

			if (i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 3] + 1);
			}

		}

		return dp[n];

	}

}
