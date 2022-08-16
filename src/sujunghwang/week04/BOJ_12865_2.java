
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// https://www.acmicpc.net/problem/12865
/*
 참고 사이트 : https://infodon.tistory.com/80
 
 오류 해결
 시간 204->196으로 줄어들었음.
 시간 : 196ms
 */
public class BOJ_12865_2 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] stuffs = new int[N+1][];
		int pass_count = 0;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			// 기존 코드에서 추가된 부분
			if(weight > K) {
				pass_count++;
				continue;
			}
			// pass_count를 빼지 않으면 빈 값이 들어갈 수 있으므로 빼준다.
			stuffs[i-pass_count] = new int[]{weight, value};
		}
		// 넣지 않은 값의 수만큼 빼고 dp 배열 생성
		N -= pass_count;
		int[][] dp = new int[N+1][K+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if(j - stuffs[i][0] >= 0) dp[i][j] = Math.max(dp[i-1][j], stuffs[i][1]+dp[i-1][j-stuffs[i][0]]);
				else dp[i][j] = dp[i-1][j];
			}
		}
		System.out.println(dp[N][K]);
	}
}
