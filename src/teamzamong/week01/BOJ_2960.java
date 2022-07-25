import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ_2960: 에라토스테네스의 체
 * 정석적인 에라토스테네스의 체 구현은 아니고, 문제 설명대로 구현하면 되는 문제
 * 숫자 i에 대해서 해당 숫자의 배수 중 이미 지워지지 않는 것(j로 체크)을 지워나가면 됨
 * 
 * 시간 복잡도: O(N^2)? j가 i의 배수이기 때문에 O(N)에 더 가까울 것 같음
 */

public class BOJ_2960 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력 받기
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int count = 0;
		boolean[] check = new boolean[N + 1]; // 지웠는지 여부 확인하기 위한 배열

		for (int i = 2; i <= N; i++) {
			for (int j = i; j <= N; j += i) {
				if (check[j]) // 이미 지웠으면 skip
					continue;

				check[j] = true; // 숫자 지우기

				if (++count == K) {
					System.out.println(j);
					return; // for loop 2개 동시에 어떻게 나가야할지 모르겠어서 이렇게 구현해봄
				}
			}
		}
	}

}
