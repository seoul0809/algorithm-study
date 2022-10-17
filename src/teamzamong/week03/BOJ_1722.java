import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 1722: 순열의 순서 
 * > https://www.acmicpc.net/problem/1722
 * > 1부터 N까지 수를 임의로 배열한 순열이 오름차순으로 정렬되어있다.
 * > N이 주어졌을 때, 1) k가 주어지면 k번째 순열 2) 임의의 순열이 주어지면 이 순열의 순서 구하기
 *  
 * [입력] 
 * - N: 1 <= N <= 20
 * - 1) k: 1 <= k <= N! (N이 20일 때, k는 int 범위 벗어남, long 사용)
 * - 2) N개의 숫자, 1부터 N까지 정수 한 번씩
 * 
 * [풀이 방법] 
 * 1) k가 주어졌을 때 k번째 순열 구하기
 * 	  N개의 숫자가 주어졌을 때, 만들 수 있는 순열의 개수는 N!개이다.
 *    순열의 첫번째 숫자가 고정되어있다면, 만들 수 있는 순열의 개수는 (N-1)!개이다.
 * 	  따라서 순열의 첫번째 숫자는 (k-1)/(N-1)!와 같다.
 *    다음 숫자를 구하기 위해서 k에서 (k-1)/(N-1)!을 뺀 값을 더해주면, 
 *    같은 원리로 순열의 두번째 ... N번째 숫자를 찾을 수 있다. 
 * 2) 1번을 반대로 활용하면 된다.
 */

public class BOJ_1722 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine().trim());

		st = new StringTokenizer(br.readLine());
		int q = Integer.parseInt(st.nextToken());

		switch (q) {
		case 1:
			long k = Long.parseLong(st.nextToken());
			getKthPerm(N, k);
			break;
			
		case 2:
			int[] perm = new int[N];
			for (int i = 0; i < N; ++i) {
				perm[i] = Integer.parseInt(st.nextToken());
			}
			getPermIdx(perm, N);
		}
	}

	private static void getKthPerm(int N, long k) {
		long curr = k - 1;

		// nums: 순열에 들어갈 수 있는 숫자 목록
		// fac: factorial 값 저장할 변수, long 타입
		long fac = 1;
		List<Integer> nums = new ArrayList<>(N);

		for (int i = 1; i <= N; ++i) {
			nums.add(i);
			fac *= i;
		}
		fac /= N;

		int idx = 0; // 순열의 몇번째 수인지, 0부터 시작
		int[] ans = new int[N]; // 정답 순열을 담을 배열

		for (int d = N - 1; d > 0; --d) {
			int tmp = (int) (curr / fac);
			ans[idx++] = nums.get(tmp);
			nums.remove(tmp); // 이미 사용한 숫자 삭제
			curr -= tmp * fac;
			fac /= d;
		}

		ans[N - 1] = nums.get(0);

		for (int i : ans) {
			System.out.print(i + " ");
		}
	}

	private static void getPermIdx(int[] perm, int N) {
		
		long fac = 1;
		List<Integer> nums = new ArrayList<>(N);
		
		for (int i = 1; i <= N; ++i) {
			nums.add(i);
			fac *= i;
		}
		fac /= N;

		long ans = 1;
		int div = N - 1;
		for (int i = 0; i < N - 1; ++i) {
			// 지금 숫자가 list에서 몇번째 숫자인지 찾기
			int j = 0;
			for (j = 0; j < nums.size(); j++) {
				if (perm[i] == nums.get(j)) {
					nums.remove(j);
					break;
				}
			}
			
			ans += j * fac;
			fac /= div;
			--div;
		}

		System.out.println(ans);
	}

}
