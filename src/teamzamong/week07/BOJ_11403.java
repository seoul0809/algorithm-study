import java.util.*;
import java.io.*;

/*
	BOJ 11403: 경로 찾기
	https://www.acmicpc.net/problem/11403
	메모리/시간: 17316kb/196ms

	[문제]
	가중치 없는 방향 그래프 G가 주어졌을 때, 
	모든 정점 (i, j)에 대해서 i에서 j로 가는 경로가 있는지 없는지 구하기
	
	[풀이]
	가중치가 없는 그래프이지만, 모든 정점 쌍에 대해서 연결 경로 여부를 찾는 것이기 때문에 플로이드 알고리즘 활용.
	
*/

public class BOJ_11403 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		/* 입력 처리 */
		int N = Integer.parseInt(br.readLine().trim());
		int[][] adjMatrix = new int[N][N];

		for (int r = 0; r < N; ++r) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; ++c) {
					adjMatrix[r][c] = Integer.parseInt(st.nextToken());

			}
		}

		/* 플로이드 알고리즘 */
		for (int mid = 0; mid < N; ++mid) {
			for (int from = 0; from < N; ++from) {
				for (int to = 0; to < N; ++to) {
					if (adjMatrix[from][to] == 1) // 이미 경로 있는 경우 처리 X
						continue;

					if (adjMatrix[from][mid] + adjMatrix[mid][to] == 2) // 연결 경로가 있는 경우, 체크
						adjMatrix[from][to] = 1;
				}
			}
		}

		/* 출력 */
		for (int[] row : adjMatrix) {
			for (int col : row) {
				bw.write(col + " ");
			}
			bw.write("\n");
		}
		
		
		bw.flush();
		bw.close();
		br.close();
	}

}
