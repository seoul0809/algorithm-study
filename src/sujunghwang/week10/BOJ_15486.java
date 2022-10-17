import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// https://www.acmicpc.net/problem/15486
/*
N+1일 까지 일을 하고 퇴사를 하려고 한다. 
1일부터 N일까지 하루에 하나씩 상담을 할 수 있다. 
각 상담 별 걸리는 기간과 받을 수 있는 금액이 주어졌을 때 퇴사 전에 할 수 있는 상담의 최대 수익을 구하시오

===========================================================================
dp문제인데 for문 돌릴 때 memo[현재 날짜]가 아닌 memo[다음 상담 가능 날짜]를 업데이트 해줘야 시간 초과 안남

시간이 오래 걸려서 다른 사람들 풀이 보다가 아래처럼 입력을 받으면서 동시에 dp를 수행하는 코드가 있어서 해보았더니 시간이 훨씬 줄어들었음

원래 코드는 입력을 모두 받은 후에 다시 반복문 돌리면서 memo 배열 채워주는 코드였음

원래 코드
메모리 : 345620	시간 : 820

바뀐 코드
메모리 : 304680	시간 : 704

 */

public class BOJ_15486 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		int T, P;
		int[] memo = new int[N+1];
		
		int max = 0;
		int ans = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			T = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			
			max = Math.max(max, memo[i]);
			
			int next = T+i;
			if(next <= N) {
				memo[next] = Math.max(memo[next], max+P);
				ans = Math.max(memo[next], ans);
			}
		}
		
		System.out.println(ans);
	}
}