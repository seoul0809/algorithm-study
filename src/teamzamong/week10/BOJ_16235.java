import java.io.*;
import java.util.*;

/*
 	BOJ 16235: 나무 재테크
	https://www.acmicpc.net/problem/16235
	메모리/시간: 21004kb/476ms
	
	[문제]
	N×N 크기의 땅이 있다. 가장 처음에 양분은 모든 칸에 5만큼 들어있다.
	땅에 M개의 나무를 각기 다른 칸에 심고 시작하는데, 각 나무는 사계절을 보내며 다음과 같은 과정을 거친다.
	
	- 봄: 나이만큼 양분 섭취, 나이 1 증가. 가장 어린 나무부터 먹고 부족하면 죽음
	- 여름: 죽은 나무가 양분으로 변함. (나무 / 2)만큼의 양분으로 변화
	- 가을: 나무 나이가 5의 배수면 8방으로 번식
	- 겨울: 모든 칸에 입력으로 주어진 양분만큼 양분 추가
	
	[풀이]
	시뮬레이션 문제. 시간이 0.3초로 짧은 편이기 때문에 중간중간 시간을 줄이기 위한 방법을 사용했다.
	- 각 칸마다 tree 관리를 List로 처리 ***
	  - 처음에는 PQ로 했었는데, List에 값을 추가할 때 맨 마지막부터 추가하니까 굳이 PQ를 쓸 필요가 없이 맨 마지막부터 탐색하면 됨
	  - 추가적인 자료구조를 사용해 관리할 필요가 없기 때문에 시간이 매우 줄어듦
    - 나무 번식을 추가 배열을 사용해서 처리
      - 추가 2차원 배열을 사용해서 매년 각 칸마다 새로 심을 나무 수를 구하고 맨 마지막에 번식하도록 함
 */

public class BOJ_16235 {

	// 8방 탐색용 배열
	private static final int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	private static final int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

	private static int N, M, K;
	private static int[][] plants; // 각 칸당 심을 나무 수 관리
	private static Land[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 값 초기화
		map = new Land[N][N];
		plants = new int[N][N];

		// 지도 생성
		for (int r = 0; r < N; ++r) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; ++c) {
				map[r][c] = new Land(Integer.parseInt(st.nextToken()), 5); // 초기 currNutrient = 5
			}
		}

		// 나무 추가
		for (int m = 0; m < M; ++m) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r - 1][c - 1].trees.add(Integer.parseInt(st.nextToken())); // 절대 같은 칸에 나무 X
		}

		for (int k = 0; k < K; ++k) {
			startYear(map);
		}

		int treeCount = 0;
		for (int r = 0; r < N; ++r) {
			for (int c = 0; c < N; ++c) {
				treeCount += map[r][c].trees.size();
			}
		}

		System.out.println(treeCount);
	}

	private static void startYear(Land[][] map) {

		clearIntMap(plants);

		Land curr;
		List<Integer> trees;
		int addNutrient, tree, plant;

		for (int r = 0; r < N; ++r) {
			for (int c = 0; c < N; ++c) {
				if (!map[r][c].trees.isEmpty()) { // 나무 없다면 굳이 처리할 필요 X

					addNutrient = 0; // 나무 죽어서 생기는 양분
					plant = 0; // 주변에 번식할 나무 개수

					curr = map[r][c]; // 현재 칸
					trees = curr.trees; // 현재 칸 나무 목록

					// 나무는 내림차순 순서로 정렬되어있음
					for (int idx = trees.size() - 1; idx >= 0; --idx) {
						tree = trees.get(idx);

						if (tree <= curr.currNutrient) { // 현재 나무가 양분 섭취 가능 하다면
							curr.currNutrient -= tree; // 양분 주기
							trees.set(idx, tree + 1); // 나이 추가

							if ((tree + 1) % 5 == 0) // 5의 배수 나무면 추후 번식할 것
								++plant;
						} else { // 양분 섭취 불가, 죽음
							addNutrient += tree / 2; // 나중에 추가될 양분이 됨
							trees.remove(idx);
						}
					}

					// 현재 칸에서 번식할 수 있는 나무가 있다면, 나중에 번식할 배열에 추가
					if (plant > 0) {
						updatePlant(r, c, plant);
					}

					// 죽은 나무 양분 추가
					curr.updateNutrient(addNutrient);
				} // end spring, summer

				// winter: 모든 칸에 대해 양분 추가
				map[r][c].updateYearlyNutrient();
			}
		}

		// autumn: 번식
		for (int r = 0; r < N; ++r) {
			for (int c = 0; c < N; ++c) {
				if (plants[r][c] > 0) { // 만약 칸에 심을 나무가 있다면
					trees = map[r][c].trees;

					for (int i = 0, end = plants[r][c]; i < end; ++i) {
						trees.add(1);
					}
				}
			}
		}

	}

	private static void updatePlant(int r, int c, int val) {
		int nr, nc;

		for (int d = 0; d < 8; ++d) {
			nr = r + dr[d];
			nc = c + dc[d];

			if (inRange(nr, nc))
				plants[nr][nc] += val;
		}
	}

	private static boolean inRange(int r, int c) {
		if (r < 0 || c < 0 || r >= N || c >= N)
			return false;
		return true;
	}

	private static void clearIntMap(int[][] map) {
		for (int[] row : map) {
			Arrays.fill(row, 0);
		}
	}

	public static class Land {
		int yearlyNutrient, currNutrient;
		List<Integer> trees = new ArrayList<Integer>();

		public Land(int nutrient, int currNutrient) {
			super();
			this.yearlyNutrient = nutrient;
			this.currNutrient = currNutrient;
		}

		public void updateNutrient(int nutrient) {
			this.currNutrient += nutrient;
		}

		public void updateYearlyNutrient() {
			this.currNutrient += this.yearlyNutrient;
		}
	}
}
