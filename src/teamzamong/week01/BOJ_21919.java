import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ_21919: 소수 최소 공배수
 * 수열 A에서 소수들을 골라 최소공배수 구하기
 * 
 * 최대 입력 숫자 개수 10,000		==> O(N^2)까지 OK
 * 2<= A_i <= 1,000,000		==> int 범위
 * 답이 2^63					==> long 범위의 답!
 * 
 * 에라토스테네스의 체, 최대공약수(=1), 최대공배수 다 구해줘야하는 문제
 */

public class BOJ_21919 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력 받기
		int N = Integer.parseInt(br.readLine().trim());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		int maxVal = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			maxVal = Math.max(maxVal, arr[i]);
		}

		// 1) 배열 내 소수 찾기
		boolean[] checkPrime = new boolean[maxVal + 1];
		for (int i = 2; i * i <= maxVal; i++) {
			if (checkPrime[i])
				continue;
			for (int j = i * i; j <= maxVal; j += i) {
				checkPrime[j] = true;
			}
		}

		// 2) 소수의 최소공배수 구하기
		long lcm = 1;
		for (int n : arr) {
			if (!checkPrime[n]) { // 배열의 숫자가 소수인지 확인
				lcm *= n;
				checkPrime[n] = true; // 중복 처리
			}
		}

		// 2-1) 소수가 없는 경우 처리
		if (lcm == 1) {
			System.out.println(-1);
		} else {
			System.out.println(lcm);
		}

	}

}
