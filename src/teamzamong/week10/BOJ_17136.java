import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 	BOJ 17136: 색종이 붙이기
	https://www.acmicpc.net/problem/17136
	메모리/시간: 21272kb/256ms
	
	[문제]
	1×1, 2×2, 3×3, 4×4, 5×5 총 다섯 종류의 색종이가 각각 5개씩 있을 때, 10×10 종이의 1이 적힌 칸을 모두 색종이로 덮으려고 한다.
	종이가 주어졌을 때, 1이 적힌 모든 칸을 붙이는데 필요한 색종이의 최소 개수를 구해보자.	
	
	[풀이]
	제일 큰 색종이부터 붙여나가는 그리디 방식으로 최소 색종이 개수를 구할 수 없어서 완전탐색으로 풀어야하는 문제.
	모든 1이 있는 칸에 대해서 다섯 종류의 색종이를 붙이는 모든 경우의 수를 고려해야한다.
	앞에 붙인 색종이가 뒷칸에 영향을 주니까 재귀적으로 접근하는 것(DFS)이 좋다.
	
	DFS를 진행할 때 종이가 2차원 배열이니까 각 칸을 일일이 방문하면서 색종이를 붙일 수 있는지 여부를 확인할 수도 있지만,
	2차원으로 탐색하기보다는 1이 적힌 칸을 list에 저장한 뒤 list의 모든 원소에 대해 모든 경우의 수를 따지도록 했다.	
 */

public class BOJ_17136 {

	private static int minCount;
	private static int[] papers = new int[6]; // 사용한 색종이 개수, 크기 1인 색종이부터 쓰기 위해 크기 6으로 잡음
	private static int[][] map = new int[10][10];
	private static List<int[]> points = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력 처리
		for (int r = 0; r < 10; ++r) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < 10; ++c) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					points.add(new int[] { r, c });
				}
			}
		}

		minCount = Integer.MAX_VALUE;
		dfs(map, papers, 0, points.size());

		System.out.println(minCount == Integer.MAX_VALUE ? -1 : minCount);
	}

	private static void dfs(int[][] map, int[] papers, int idx, int end) {
		if (idx == end) { // 1이 붙은 칸을 모두 처리했을 때 종료
			int total = 0;
			for (int p : papers) {
				total += p;
			}

			if (minCount > total) {
				minCount = total;
			}

			return;
		}

		int[] curr = points.get(idx);
		if (map[curr[0]][curr[1]] == 0) { // 이미 스티커 붙여서 붙일 수 없음
			dfs(map, papers, idx + 1, end);
			return;
		}

		for (int size = 5; size > 0; --size) { // 모든 색종이 크기에 대해서 경우의 수를 따져보기
			if (papers[size] >= 5) // 이미 5개를 썼다면 쓸 수 없는 색종이
				continue;

			if (check(map, curr[0], curr[1], size)) { // 색종이를 튀어나오지 않게 붙일 수 있는 경우
				// 색종이 붙이기
				stick(map, curr[0], curr[1], size, 0);
				++papers[size];
				
				dfs(map, papers, idx + 1, end);
				
				// 원상 복구
				--papers[size];
				stick(map, curr[0], curr[1], size, 1);
			}
		}
	}

	private static boolean check(int[][] map, int sr, int sc, int size) {
		if (sr + size > 10 || sc + size > 10)
			return false;

		for (int r = sr; r < sr + size; ++r) {
			for (int c = sc; c < sc + size; ++c) {
				if (map[r][c] != 1)
					return false;
			}
		}

		return true;
	}

	private static void stick(int[][] map, int sr, int sc, int size, int fill) {
		for (int r = sr; r < sr + size; ++r) {
			for (int c = sc; c < sc + size; ++c) {
				map[r][c] = fill;
			}
		}
	}

}
