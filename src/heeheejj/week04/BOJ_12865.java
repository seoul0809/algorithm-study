package heeheejj.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865 {
/*
    문제 이름: 평범한 배낭
    시간: 192ms

    냅색 알고리즘
    dp[n][k]는 n번째 물건까지 확인했을 때, 무게가 k인 배낭의 최대 가치!!
 */
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N+1][K+1]; // dp: n번째 물건까지 확인했을 때, 무게가 k인 배낭의 최대 가치

        int[] wArr = new int[N+1];
        int[] vArr = new int[N+1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(in.readLine(), " ");
            wArr[i] = Integer.parseInt(st.nextToken());
            vArr[i] = Integer.parseInt(st.nextToken());
        }

        for(int n = 1; n <= N; n++){
            for(int k = 1; k <= K; k++){
                int tempWeight = wArr[n];
                if(k < tempWeight){
                    dp[n][k] = dp[n-1][k];
                }else{
                    int tempValue = vArr[n];
                    dp[n][k] = Math.max(dp[n-1][k], dp[n-1][k-tempWeight]+tempValue);
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}
