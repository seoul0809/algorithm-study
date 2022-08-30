import java.util.*;
import java.io.*;

/**
	BOJ 11256: 끝나지 않는 파티
	https://www.acmicpc.net/problem/11256
	메모리/시간: 26484kb/256ms

	[문제]
	A 파티장에서 B 파티장까지 최단 거리를 구한 후, 주어진 C만큼의 시간 뒤에 도착할 수 있는 거리인지 반환하기

	[풀이]
	- 모든 정점에 대해 다른 정점에 대한 최단 거리 구하기 => 플로이드 알고리즘!
 */

public class BOJ_11256 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		// 입력 처리
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] adjMatrix = new int[N + 1][N + 1];
		for (int r = 1; r <= N; ++r) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; ++c) {
				adjMatrix[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 플로이드 알고리즘
		for (int mid = 1; mid <= N; ++mid) {
			for (int from = 1; from <= N; ++from) {
				for (int to = 1; to <= N; ++to) {
					if (adjMatrix[from][to] > adjMatrix[from][mid] + adjMatrix[mid][to])
						adjMatrix[from][to] = adjMatrix[from][mid] + adjMatrix[mid][to];
				}
			}
		} // end floyd

		// 출력 처리
		for (int m = 0; m < M; ++m) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			if (adjMatrix[from][to] <= time) {
				bw.write("Enjoy other party\n");
			} else {
				bw.write("Stay here\n");
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
