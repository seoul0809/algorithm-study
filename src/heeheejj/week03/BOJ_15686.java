package heeheejj.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    문제 이름: 치킨 배달

    우리의 목표는 도시의 치킨 거리의 최솟값 구하기!!

    chickens의 행번호를 알면 x, y좌표를 알 수 있다.
    조합은 chickens의 행번호에 대해서만 해주면 된다.
 */

public class BOJ_15686 {
    static int M;
    static int[] nums;      // 조합으로 골라진 idx들을 답는 배열 (idx는 chickens의 행번호)
    static int[] inputs;    // 2차원배열 chickens의 행번호 idx를 담는 배열 (조합의 대상)
    static int[][] chickens;
    static int cCnt;
    static int[][] houses;
    static int hCnt;
    static int minCityChickenDist = Integer.MAX_VALUE;
    static int cityChickenDist = 0;

    static void comb(int cnt, int start){
        if(cnt == M){
            calculate();
            if(minCityChickenDist > cityChickenDist){
                minCityChickenDist = cityChickenDist;
            }
            return;
        }

        for(int i = start; i < cCnt; i++){
            nums[cnt] = inputs[i];
            comb(cnt+1, i+1);
        }
    }

    static void calculate(){    //치킨거리의 합 계산하는 메서드
        cityChickenDist = 0;
        for(int i = 0; i < hCnt; i++){
            int chickenDist = Integer.MAX_VALUE;
            for(int j = 0; j < M; j++){
                int dist = Math.abs(chickens[nums[j]][0] - houses[i][0]) + Math.abs(chickens[nums[j]][1] - houses[i][1]);
                if(chickenDist > dist){
                    chickenDist = dist;
                }
            }
            cityChickenDist += chickenDist;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        chickens = new int[13][2];  // 치킨집의 좌표를 담는 배열, chickens[i][0]: i번째 치킨집의 x좌표, [i][1]에는 y좌표
        cCnt = 0;
        houses = new int[2*N][2];   // 집의 좌표를 담는 배열, houses[i][0]: i번째 집의 x좌표, [i][1]에는 y좌표
        hCnt = 0;

        // 지도를 입력받으면서 , 치킨집과 집의 좌표를 저장
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine(), " ");
            for(int j = 0; j < N; j++){
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 0){
                    continue;
                } else if(temp == 1){
                    houses[hCnt][0] = i;
                    houses[hCnt][1] = j;
                    hCnt++;
                } else if(temp == 2){
                    chickens[cCnt][0] = i;
                    chickens[cCnt][1] = j;
                    cCnt++;
                }
            }
        }
        inputs = new int[cCnt];
        nums = new int[M];
        for(int i = 0; i < cCnt; i++){
            inputs[i] = i;
        }

        // 치킨집을 M개 고른다.
        comb(0, 0);

        System.out.println(minCityChickenDist);
    }
}
