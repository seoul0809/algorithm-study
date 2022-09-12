import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/11403
/*
 메모리 : 15700KB	/ 시간 : 168ms
 
 가중치 없는 방향 그래프 G가 주어졌을 때, 모든 정점 (i, j)에 대해서, i에서 j로 가는 경로가 있는지 없는지 구하기
 
 플로이드-와샬 알고리즘(모든 정점에서 모든 정점으로의 최단거리를 구하는 알고리즘) 사용
 */

public class BOJ_11403 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		char[][] adjMatrix = new char[N][];
		
		for (int i = 0; i < N; i++) {
			adjMatrix[i] = br.readLine().replace(" ", "").toCharArray();
		}
		
		// i에서 k를 지나 j로 갈 수 있으면 체크
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(adjMatrix[i][k] == '1' & adjMatrix[k][j] == '1')
						adjMatrix[i][j] = '1';
				}
			}
		}
		
		for (char[] c : adjMatrix) {
			for (int i = 0; i < N; i++) {
				sb.append(c[i]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
