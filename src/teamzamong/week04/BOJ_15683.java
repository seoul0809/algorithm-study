import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
	BOJ 15683: 감시
	https://www.acmicpc.net/problem/15683
	걸린 시간: 260ms
	
	문제)
	각기 볼 수 있는 방향이 다른 5 종류 CCTV가 있음. 각 CCTV는 0, 90, 180, 270도로 회전 가능.
	CCTV는 감시할 방향에 있는 모든 칸 (다른 CCTV 포함)을 감시할 수 있고, 벽은 감시 불가능하다.
	사무실의 크기와 상태, 그리고 CCTV의 정보가 주어졌을 때, CCTV의 방향을 적절히 정해서, 사각 지대의 최소 크기 구하기
	
	풀이)
	사무실의 세로, 가로 크기가 1 <= N,M <= 8이고 CCTV의 최대 개수가 8개이기 때문에
	충분히 완전 탐색으로 풀어도 시간 초과 나지 않는 범위 내이다.
	따라서 주어진 사무실 조건에 가능한 모든 CCTV 방향을 구해 각각의 사각 지대 크기를 구한 후 최소값을 구함.
*/

public class BOJ_15683 {

	// delta:상하좌우 일반적인 델타 배열
	// cctvDeltas: 각 CCTV 종류에 따라 한 번에 가능한 감시 방향을 표현한 3차원 배열.
	//			   cctvDeltas[i][j][k]에서 i - 1는 CCTV의 종류, j는 가능한 감시 방향, k는 delta 배열의 인덱스  
	static final int[][] delta = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static final int[][][] cctvDeltas = { { { 0 }, { 1 }, { 2 }, { 3 } }, 
										  { { 0, 1 }, { 2, 3 } },
										  { { 0, 2 }, { 0, 3 }, { 1, 2 }, { 1, 3 } }, 
										  { { 0, 1, 2 }, { 0, 1, 3 }, { 0, 2, 3 }, { 1, 2, 3 } },
										  { { 0, 1, 2, 3 } } };

	// CCTV를 표현하기 위한 클래스
	// 가능한 방향 종류가 적은 순으로 정렬되도록 Comparable을 구현함
	// 5: 한 방향만 가능, 2: 두 방향 가능, 1/3/4: 네 방향 가능
	public static class CCTV implements Comparable<CCTV> {
		int model, r, c;
		int[][] deltas;
		int dCount;

		public CCTV(int model, int r, int c) {
			this.model = model;
			this.r = r;
			this.c = c;

			deltas = cctvDeltas[model - 1];
			dCount = deltas.length;
		}

		public int getR() {
			return r;
		}

		public int getC() {
			return c;
		}

		// 주어진 방향 인덱스에 맞는 delta 배열 리턴
		public int[] getCorrDelta(int direct) { 
			return deltas[direct];
		}

		@Override
		public int compareTo(CCTV c) {
			if (model == 2 && (c.model == 3 || c.model == 4))
				return -1;

			if (c.model == 2 && (model == 3 || model == 4))
				return 1;

			return Integer.compare(c.model, model);
		}
	}
	
	
	static int N, M, cctvCount, minBlindSpot;
	static int[] cctvPerm;
	static int[][] map;
	static List<CCTV> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		list = new ArrayList<>();

		// 입력 처리
		for (int r = 0; r < N; ++r) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; ++c) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] != 0 && map[r][c] != 6) {
					list.add(new CCTV(map[r][c], r, c)); // CCTV 좌표 저장
				}
			}
		}

		// CCTV가 없는 경우 사각 지대 크기 반환
		if (list.size() == 0) {
			System.out.println(countBlindSpot(map));
			return;
		}

		Collections.sort(list);
		cctvCount = list.size();
		cctvPerm = new int[cctvCount];
		minBlindSpot = Integer.MAX_VALUE;

		getCCTVPerm(0);

		System.out.println(minBlindSpot);
	}

	// 0 ~ 3 (4가지 방향 경우의 수)를 원소로 가진 CCTV 개수 길이의 순열 구해 최소 사각 지대 크기 갱신
	// 4가지 방향보다 적은 CCTV 종류가 있지만, 이건 사각 지대 개수 구하는 메서드에서 따로 처리
	private static void getCCTVPerm(int depth) {
		if (depth == cctvCount) {
			minBlindSpot = Math.min(minBlindSpot, checkTotalBlindSpot(cctvPerm));
			return;
		}

		for (int i = 0; i < 4; ++i) {
			cctvPerm[depth] = i;
			getCCTVPerm(depth + 1);
		}
	}

	// CCTV의 방향이 주어졌을 때, 지도에서 총 사각 지대 크기 구하기
	private static int checkTotalBlindSpot(int[] directions) {
		// 해당 CCTV에 대해 가능하지 않은 방향이라면 계산하지 않고 반환
		for (int c = 0, cCount = directions.length; c < cCount; ++c) {
			if (directions[c] >= list.get(c).dCount)
				return Integer.MAX_VALUE;
		}

		// 새로운 map을 만들어 CCTV가 볼 수 있는 구역을 마크한 후, 사각 지대 크기 반환
		int[][] tempMap = new int[N][];
		for (int i = 0; i < N; ++i) {
			tempMap[i] = Arrays.copyOf(map[i], M);
		}

		for (int c = 0, cCount = directions.length; c < cCount; ++c) {
			checkCCTVWatch(tempMap, list.get(c), directions[c]);
		}

		return countBlindSpot(tempMap);
	}

	// 지도와 CCTV, CCTV의 방향이 주어졌을 때, 감시 가능 구역을 마크하는 메서드
	private static void checkCCTVWatch(int[][] map, CCTV cctv, int direct) {
		int[] deltas = cctv.getCorrDelta(direct);
		int r = cctv.getR();
		int c = cctv.getC();
		int dCount = deltas.length;

		for (int d = 0; d < dCount; ++d) { // 주어진 모든 방향에 대해서
			r = cctv.getR();
			c = cctv.getC();

			while (true) {
				int nr = r + delta[deltas[d]][0];
				int nc = c + delta[deltas[d]][1];

				if (!checkRange(nr, nc) || map[nr][nc] == 6) // 범위 벗어났거나 벽인 경우
					break;

				if (map[nr][nc] == 0) // 감시 가능 지역 체크
					map[nr][nc] = 9;
				
				r = nr;
				c = nc;
			}
		}
	}

	// 사각 지대 개수 계산하는 함수
	private static int countBlindSpot(int[][] map) {
		int count = 0;
		for (int r = 0; r < N; ++r) {
			for (int c = 0; c < M; ++c) {
				if (map[r][c] == 0)
					++count;
			}
		}
		return count;
	}

	private static boolean checkRange(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= M)
			return false;
		return true;
	}

}
