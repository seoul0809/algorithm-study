package heeheejj.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    문제 이름: 하노이의 탑 이동 순서
    알고리즘 분류: 재귀
    시간: 520ms

    3개의 막대가 있고, n개의 원판을 출발막대에서 목적막대로 옮기려고 할 때
    다음과 같은 구조가 반복된다. (임시막대: 3개의 막대 중 출발막대도 목적막대도 아닌 막대)
    1. n-1개의 원판을 출발막대에서에서 임시막대로 옮긴다. -> 재귀호출
    2. n번째 원판을 출발막대에서 목적막대로 옮긴다.
    3. 임시막대에 있는 n-1개의 원판을 목적막대로 옮긴다. -> 재귀호출
    종료조건은 원판이 하나남았을 때이다.

    cf) 이동 횟수 공식
    위 구조를 생각해보면, n개의 원판을 이동시키기 위한 이동횟수를 a(n)이라고 할 때
    a(n)을 다음과 같은 점화식으로 표현할 수 있다.
    a(n) = 2 * a(n-1) + 1

    이 떄, b(n) = a(n) + 1 이라고 가정하자. ( a(1) = 1이므로 b(1) = a(1) + 1 = 2 )
    양변에 +1을 하면 b(n) = 2 * a(n-1) + 2 = 2 * (a(n-1)+1)
    따라서 b(n) = 2 * b(n-1)이며, b(1) = 2이므로
    b(n)은 공비가 2이고 첫째항이 2인 공비수열이다.
    b(n) = 2 * 2^(n-1) = 2^n
    따라서 a(n) = 2^n - 1
 */

public class BOJ_11729 {

    static int totalCnt = 0;
    static StringBuilder sb = new StringBuilder();

    // n번 원판을 from에서 to로 옮긴다. temp는 to로 가기 전 거쳐가야하는 임시막대
    static void hanoi(int n, int from, int temp, int to){
        if(n == 1){
            totalCnt++;
            sb.append(from+" "+to+"\n");
            return;
        }

        hanoi(n-1, from, to, temp);     // 1. n-1번째 원판을 임시막대로 옮긴다.
        sb.append(from+" "+to+"\n");
        hanoi(n-1, temp, from, to);

        totalCnt++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        hanoi(N, 1, 2, 3);

        sb.insert(0, totalCnt+"\n");
        System.out.println(sb);
    }
}
