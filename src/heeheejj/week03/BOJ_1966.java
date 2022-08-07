package heeheejj.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    문제 이름: 프린터 큐
    알고리즘 분류: 구현, 자료구조, 시뮬레이션, 큐
    링크: https://www.acmicpc.net/problem/1966
 */

public class BOJ_1966 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());
        StringTokenizer st;
        for(int t = 0; t < T; t++){
            st = new StringTokenizer(in.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // 문서 순서대로 중요도를 큐에 저장
            st = new StringTokenizer(in.readLine(), " ");
            Queue<Integer> q = new LinkedList<>();  // 문서의 중요도를 원소로 하는 큐
            Queue<Integer> idx = new LinkedList<>();    // 문서의 인덱스를 원소로 하는 큐 (맨앞 인덱스가 타겟문서의 인덱스인 M과 같은지 확인용)
            int max = Integer.MIN_VALUE;    // 중요도가 가장 높은 값
            for(int i = 0; i < N; i++){
                int temp = Integer.parseInt(st.nextToken());
                q.add(temp);
                if(max < temp){
                    max = temp;
                }
                idx.add(i);
            }

            // 중요도 확인하며 인쇄
            int order = 1; // 타겟 문서의 인쇄 순서 (1부터 시작하여 더해감)

            while(true){
                int cur = q.peek();
                if(cur == max){ // 중요도기 가장 높은 문서를 만나면
                    if(idx.peek() == M){    // 만약 현재 인쇄할 문서가 타겟문서라면 정답
                        sb.append(order).append("\n");
                        break;
                    }

                    q.poll();   // 꺼낸다. (인쇄한다.)
                    order++;    // 타겟 문서의 인쇄 순서는 +1됨
                    idx.poll(); // idx 조정

                    // max값 다시 구하기
                    for(int i = 0 ; i < q.size(); i++){
                        max = Collections.max(q);
                    }
                }else{  // 중요도가 가장 높은 문서가 아니라면
                    int temp = q.poll();    // 맨 앞에서 문서를 꺼내고
                    q.add(temp);            // 다시 맨 뒤로 넣는다.
                    int temp2 = idx.poll(); // idx도 조정해줌
                    idx.add(temp2);
                }
            }
        }
        System.out.println(sb);
    }
}
