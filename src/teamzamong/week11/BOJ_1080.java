import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
	BOJ 1080: 행렬
	https://www.acmicpc.net/problem/1080
	메모리/시간: 14268kb/120ms

	[문제]
	숫자 0과 1로 이루어진 행렬 A, B가 주어졌을 때, 행렬 A를 행렬 B로 바꾸는데 필요한 연산 최소 횟수
	- 행렬 변환 연산: 3*3 부분 행렬의 원소 뒤집기 (0 <-> 1)

	[풀이]
	행렬 A의 각 원소마다 만약 행렬 B의 같은 위치 원소와 값이 다르다면 뒤집어 주면 된다.
	더 이상 3*3 부분 행렬을 만들 수 없어 바꿀 수 없는 원소의 경우, 못 바꾸는 거니까 -1을 반환하면 된다.
 */

public class BOJ_1080 {

	private static int N, M;
	private static char[][] A, B;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		A = new char[N][];
		B = new char[N][];

		for (int i = 0; i < N; ++i) {
			A[i] = br.readLine().trim().toCharArray();
		}

		for (int i = 0; i < N; ++i) {
			B[i] = br.readLine().trim().toCharArray();
		}

		System.out.println(changeCount(A, B));

	}

	private static int changeCount(char[][] A, char[][] B) {

		int count = 0;

		// 3 * 3 부분 행렬을 만들 수 있는 범위 내에서
		for (int r = 0; r < N - 2; ++r) {
			for (int c = 0; c < M - 2; ++c) {
				if (A[r][c] == B[r][c]) // 같다면 안 바꿔도 됨
					continue;

				flip(A, r, c); // 다르다면 바꾸기
				++count;
			}
		}

		// 모두 뒤집었을 때 결과값이 동일한지 비교
		for (int r = 0; r < N; ++r) {
			for (int c = 0; c < M; ++c) {
				if (A[r][c] != B[r][c]) // 안 바뀐 부분이 있다면, ex) 부분 행렬 범위 밖
					return -1;
			}
		}

		return count;
	}

	private static void flip(char[][] A, int r, int c) {
		for (int i = r; i < r + 3; ++i) {
			for (int j = c; j < c + 3; ++j) {
				A[i][j] = A[i][j] == '0' ? '1' : '0'; // 값 반전
			}
		}
	}

}
