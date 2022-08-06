package heeheejj.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
    문제 이름: N과 M (5)
    기본적인 순열 구현
 */
public class BOJ_15654 {
    static int M;
    static int N;
    static int[] inputs;
    static int[] nums;
    static boolean[] isSelected;
    static StringBuilder sb = new StringBuilder();

    static void perm(int cnt){
        if(cnt == M){
            for(int i = 0; i < M; i++){
                sb.append(nums[i]+" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 0; i < N; i++){
            if(isSelected[i]){
                continue;
            }

            nums[cnt] = inputs[i];
            isSelected[i] = true;
            perm(cnt+1);
            isSelected[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine(), " ");
        inputs = new int[N];
        for (int i = 0; i < N; i++){
            inputs[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(inputs);   // 배열 정렬

        isSelected = new boolean[N];
        Arrays.fill(isSelected, false);

        nums = new int[M];
        perm(0);
        System.out.println(sb);
    }
}
