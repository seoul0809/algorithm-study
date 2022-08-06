import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// https://www.acmicpc.net/problem/16439
/*
 N명의 사람이 치킨을 주문합니다.

 치킨은 총 M가지 종류가 있고 각자 치킨의 선호도가 다릅니다. 
 
 한 사람의 만족도는 시킨 치킨 중에서 선호도가 가장 큰 값으로 결정됩니다.

 최대 세 가지 종류의 치킨만 시키고자 할 때 가능한 만족도의 합의 최댓값을 구해주세요. 
 
 회원의 수 N (1 ≤ N ≤ 30)
 치킨 종류의 수 M (3 ≤ M ≤ 30)
 */

public class BOJ_16439 {
	static int N;
	static int M;
	static int max; // 만족도의 최댓값
	static int[][] arr; // 각 회원의 치킨 선호도를 담을 배열
	static int[] numbers; // 선택된 치킨을 담을 배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		numbers = new int[3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		solution(0,1);
		System.out.println(max);
		
	}
	
	private static void solution(int cnt, int start) {
		if(cnt == 3) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				// 각 회원의 선호도의 최대값을 저장
				sum += Math.max(arr[i][numbers[0]], Math.max(arr[i][numbers[1]], arr[i][numbers[2]]));
			}
			if (max < sum) max = sum;
			return;
		}
		for (int i = start-1; i < M; i++) {
			numbers[cnt] = i;
			solution(cnt+1, i+1); // 치킨을 시키는 것은 순서가 없기 때문에 중복을 방지
		}
	}

}
