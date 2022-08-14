
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// https://www.acmicpc.net/problem/12865
/*
 참고 사이트 : https://infodon.tistory.com/80
 
 시간 : 204ms
 특이사항 : 아래 주석처리한 코드를 넣어서 돌렸더니 런타임 에러가 뜸
 */
public class BOJ_12865 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] stuffs = new int[N+1][];
		int[][] dp = new int[N+1][K+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
//			if(weight > K) continue;
			stuffs[i] = new int[]{weight, value};
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if(j - stuffs[i][0] >= 0) dp[i][j] = Math.max(dp[i-1][j], stuffs[i][1]+dp[i-1][j-stuffs[i][0]]);
				else dp[i][j] = dp[i-1][j];
			}
		}
		System.out.println(dp[N][K]);
	}
}
