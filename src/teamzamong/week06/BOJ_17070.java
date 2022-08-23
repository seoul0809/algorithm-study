import java.util.*;
import java.io.*;

/*
	BOJ 17070: 파이프 옮기기 1
	https://www.acmicpc.net/problem/17070
	메모리/시간: 18076kb/328ms
	
	[문제]
	1*1 크기의 정사각형 칸으로 나눠진 N*N 격자판이 있을 때, 
	파이프를 규칙에 따라 (1,1)에서 (N,N)으로 이동할 수 있는 경우의 수가 몇 개인지 찾기
	
	[풀이]
	(1,1)부터 시작해서 각 칸마다 갈 수 있는 모든 방향에 대해서 DFS로 시도해 본 후,
	(N,N)에 도착했을 때 종료 및 개수 업데이트	
 */

public class BOJ_17070 {

	static int N, count;
	static int[][] map;
	static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 1, 1 } }; // 오른쪽, 오른쪽 아래, 대각선 오른쪽 아래

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		/* 입력 처리 및 초기 세팅 */
		N = Integer.parseInt(br.readLine().trim());
		map = new int[N + 2][N + 2]; // 범위 체크 따로 안 하기 위해 사방으로 padding 추가

		for (int r = 1; r <= N; ++r) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 1; c <= N; ++c) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		Arrays.fill(map[0], 1);
		Arrays.fill(map[N + 1], 1);

		for (int r = 0; r <= N + 1; ++r) {
			map[r][0] = 1;
			map[r][N + 1] = 1;
		}

		/* DFS 시작 */
		count = 0;
		countPipeMove(0, 1, 2);

		/* 답 출력 */
		System.out.println(count);

	}

	/**
	 * 파이프의 한쪽 끝을 (N, N)으로 이동시키는 총 방법의 수인 전역 변수 count를 DFS를 사용해 업데이트한다.
	 * @param direction		파이프의 현재 방향
	 * @param endR			파이프 끝부분의 행 좌표
	 * @param endC			파이프 끝부분의 열 좌표
	 */
	private static void countPipeMove(int direction, int endR, int endC) {
		if (endR == N && endC == N) { // (N, N)에 도착했으면 count 추가
			++count;
			return;
		}

		for (int d = 0; d < 3; ++d) { // 모든 방향 체크
			if (!checkMoveable(direction, endR, endC, d))
				continue;

			countPipeMove(d, endR + deltas[d][0], endC + deltas[d][1]); // 파이프 이동
		}
	}

	/**
	 * 파이프를 주어진 방향으로 이동할 수 있는지 여부를 판단 후 리턴한다. 
	 * @param orgD		파이프의 현재 방향
	 * @param endR		파이프 끝부분의 행 좌표
	 * @param endC		파이프 끝부분의 열 좌표
	 * @param nextD		파이프를 이동하려는 방향
	 * @return			파이프를 주어진 방향으로 이동할 수 있는지 여부. 가능하면 true, 아니면 false
	 */
	private static boolean checkMoveable(int orgD, int endR, int endC, int nextD) {
		// 파이프의 방향에 따라 이동할 수 없는 부분인 경우
		if (orgD == 0 && nextD == 1 || orgD == 1 && nextD == 0)
			return false;

		// 각 방향으로 이동하기 위해 필요한 칸들이 빈 칸인지 확인
		switch (nextD) {
		case 0:
			if (map[endR][endC + 1] != 0)
				return false;
			break;
		case 1:
			if (map[endR + 1][endC] != 0)
				return false;
			break;
		case 2:
			if (map[endR + 1][endC] != 0 || map[endR][endC + 1] != 0 || map[endR + 1][endC + 1] != 0)
				return false;
			break;
		}

		return true;
	}
}
