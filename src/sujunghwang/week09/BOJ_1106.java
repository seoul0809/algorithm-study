import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1106
/*
세계적인 호텔인 형택 호텔의 사장인 김형택은 이번에 수입을 조금 늘리기 위해서 홍보를 하려고 한다.

형택이가 홍보를 할 수 있는 도시가 주어지고, 각 도시별로 홍보하는데 드는 비용과, 그 때 몇 명의 호텔 고객이 늘어나는지에 대한 정보가 있다.

예를 들어, “어떤 도시에서 9원을 들여서 홍보하면 3명의 고객이 늘어난다.”와 같은 정보이다. 이때, 이러한 정보에 나타난 돈에 정수배 만큼을 투자할 수 있다. 즉, 9원을 들여서 3명의 고객, 18원을 들여서 6명의 고객, 27원을 들여서 9명의 고객을 늘어나게 할 수 있지만, 3원을 들여서 홍보해서 1명의 고객, 12원을 들여서 4명의 고객을 늘어나게 할 수는 없다.

각 도시에는 무한 명의 잠재적인 고객이 있다. 이때, 호텔의 고객을 적어도 C명 늘이기 위해 형택이가 투자해야 하는 돈의 최솟값을 구하는 프로그램을 작성하시오.

===============================

0-1 knapsack 과 비슷한 유형

목표 고객의 수만큼 메모 배열을 생성하여 풀이

메모리 : 14344
시간 : 128
 */

public class BOJ_1106 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int C = Integer.parseInt(st.nextToken()); // 목표 고객
		int N= Integer.parseInt(st.nextToken()); // 도시의 개수
		
		int[][] arr = new int[N][2]; // 비용, 고객의 수
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		int[] memo = new int[C+1];
		Arrays.fill(memo, Integer.MAX_VALUE); // 최소값을 찾는 문제이므로 Integer.MAX_VALUE를 넣어서 배열 초기화
		
		for (int[] i : arr) {
			for (int j = 1; j <= C; j++) {
				if(j <= i[1]) { // 고객의 수가 홍보를 통해 얻을 수 있는 고객의 수보다 적거나 같을 경우
					memo[j] = Math.min(memo[j], i[0]); 
				}
				if(j > i[1]) { // 고객의 수가 홍보를 통해 얻을 수 있는 고객의 수보다 많을 경우
					memo[j] = Math.min(memo[j], memo[j-i[1]]+i[0]);
				}
				
			}
		}
		System.out.println(memo[C]);
	}

}
