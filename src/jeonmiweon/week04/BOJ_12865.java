package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] stuff = new int[N+1][2];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			stuff[i][0] = Integer.parseInt(st.nextToken());
			stuff[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[N+1][K+1];
		
		for(int i = 1; i <= N; i++) {
			for(int k = 1; k <= K; k++) {
				if(k < stuff[i][0])	dp[i][k] = dp[i-1][k];
				else	dp[i][k] = Math.max(dp[i-1][k], dp[i-1][k-stuff[i][0]] + stuff[i][1]);
			}
		}
		System.out.println(dp[N][K]);
	}
}