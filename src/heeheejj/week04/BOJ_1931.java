package heeheejj.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
    문제 이름: 회의실 배정
    시간: 529ms
 */
public class BOJ_1931 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        int[][] meetingTime = new int[N][2];
        for(int n = 0; n < N; n++){
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            meetingTime[n][0] = startTime;
            meetingTime[n][1] = endTime;
        }

        // 회의 끝나는 시간이 빠른 순으로 정렬,
        // 만약 끝나는 시간이 같다면 시작 시간이 빠른 순으로 정렬
        Arrays.sort(meetingTime, (o1, o2) -> o1[1]==o2[1] ? o1[0]-o2[0] : o1[1]-o2[1]);

        int result = 0;
        int tempEndTime = 0;
        for(int i = 0 ; i < N; i++){
            if(tempEndTime <= meetingTime[i][0]){   // 앞 회의의 종료시간이 다음 회의 시작 시간보다 빠르거나 같다면 update
                tempEndTime = meetingTime[i][1];
                result++;
            }
        }
        System.out.println(result);
    }
}
