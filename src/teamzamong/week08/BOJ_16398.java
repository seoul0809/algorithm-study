import java.io.*;
import java.util.*;

/*
	BOJ 16398: 행성 연결
	https://www.acmicpc.net/problem/16398
	메모리/시간: 120120kb/968ms

	[문제]
	N개의 행성과 그 행성들을 연결하는 M개의 플로우가 존재할 때, 모든 행성을 연결할 수 있는 최소 비용 계산
	행성 a에서 자기 자신까지의 비용은 언제나 0
	 
 	- 행성의 개수 N : 1 ≤ N ≤ 1000
	
	[풀이]
	행성 간 플로우 관리 비용이 N * N 행렬로 주어졌기 때문에 겹치는 부분만 가지치기해주면 크루스칼로 풀이할 수 있었던 문제
*/

public class BOJ_16398 {

	public static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		public int compareTo(Edge other) {
			return Integer.compare(this.weight, other.weight);
		}
	}

	/* UNION FIND 및 크루스칼 알고리즘 관련 함수 */
	private static int findRoot(int[] parents, int a) {
		if (parents[a] < 0)
			return a;
		else
			return parents[a] = findRoot(parents, parents[a]);
	}

	private static boolean union(int[] parents, int a, int b) {
		int rootA = findRoot(parents, a);
		int rootB = findRoot(parents, b);

		if (rootA == rootB)
			return false;

		parents[rootA] += parents[rootB];
		parents[rootB] = rootA;

		return true;
	}

	public static long kruskal(int vertexCount, PriorityQueue<Edge> edges, int[] parents) {
		long totalCost = 0;
		int count = 0;

		while (!edges.isEmpty()) {
			Edge edge = edges.poll();
			
			if (union(parents, edge.from, edge.to)) {
				totalCost += edge.weight;

				if (++count == vertexCount - 1)
					break;
			}
		}
		
		return totalCost;
	}

	/* main */	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		/* 입력 처리 */
		int N = Integer.parseInt(br.readLine().trim());
		
		PriorityQueue<Edge> edges = new PriorityQueue<>();
		for (int r = 0; r < N / 2 + 1; ++r) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; ++c) {
				int weight = Integer.parseInt(st.nextToken());
				
				if (r >= c) continue; // r == c인 경우 값이 0이라 넘어가고, from->to 비용이 PQ에 중복 추가되지 않도록 처리
				
				edges.offer(new Edge(r, c, weight));
			}
		}
		
		/* 크루스칼 알고리즘 위한 parents 배열 */
		int[] parents = new int[N];
		Arrays.fill(parents, -1);
		
		// 크루스칼 알고리즘으로 최소 비용 계산 후 출력		
		System.out.println(kruskal(N, edges, parents));
	}

}
