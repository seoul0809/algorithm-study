package heeheejj.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
    문제 이름: 부분수열의 합

    비트마스킹을 이용해 부분집합을 구하였다.

    참고:
    1<<k : k번째만 on인 상태
    A & (1<<k); : 0이 아니면 A집합에 k번쨰 원소가 포함된 것, 0이면 포함되지 않은 것

    참고: https://loosie.tistory.com/238
         https://hongjuzzang.github.io/bitmask/bit_mask/#-비트-연산-연습문제
 */
public class BOJ_1182 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine(), " ");
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        for(int i = 1; i < (1 << N); i++){     // i=0은 공집합이라서 제외, 0..01 부터 1..1까지 모든 부분집합 / 부분집합의 개수는 총 2^N개

            int sum = 0;
            for(int j = 0; j < N; j++){ // 몇번째 수를 포함하고 있는지 확인,
                if((i & (1<<j)) != 0){  // i & (1<<j) 가 0이 아니면 i는 j번쨰 수를 포함하는 집합이다.
                    sum += arr[j];
                }
            }

            // 합이 S가 되면 개수를 +1해준다.
            if(sum == S)    cnt++;
        }

        System.out.println(cnt);
    }
}
