import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15486 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine().trim());
		int[][] appointments = new int[2][N + 1];

		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			appointments[0][i] = Integer.parseInt(st.nextToken()); // 기간
			appointments[1][i] = Integer.parseInt(st.nextToken()); // 이익
		}

		System.out.println(dp(N, appointments));
	}

	private static int dp(int N, int[][] appointments) {

		// dp[i]: i-1번째 날까지 벌 수 있는 최대 금액
		int[] dp = new int[N + 2];

		for (int i = 1; i <= N; ++i) {
			dp[i] = Math.max(dp[i], dp[i - 1]); // 초기값 세팅

			// 상담이 N일 이전에 끝남 + 해당 날짜의 이익보다 i번째 날 상담을 하고난 이후의 이익이 더 클 때
			if (i + appointments[0][i] - 1 <= N && dp[i + appointments[0][i]] < dp[i] + appointments[1][i]) {
				dp[i + appointments[0][i]] = dp[i] + appointments[1][i];
			}

		}

		return Math.max(dp[N], dp[N + 1]);
	}

}
