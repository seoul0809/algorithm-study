import java.util.*;
import java.io.*;

/*
	BOJ 1647: 도시 분할 계획
	https://www.acmicpc.net/problem/1647
	메모리/시간: 321100kb/1212ms

	[문제]
	N개의 집과 그 집들을 연결하는 M개의 길로 이루어진 마을에 대해 마을을 두 개의 분리된 마을로 분리하되, 
	각 마을 내에서는 임의의 두 집에 대해 경로가 존재하도록 분리. 다만, 이때 길의 유지비의 합이 최소가 되어야 한다.
	 	
	- 집의 개수 N : 2 ≤ N ≤ 100,000
	- 길의 수 M : 1 ≤ M ≤ 1,000,000

	[풀이]
	모든 집(정점)를 연결하는 최소 신장 트리를 구하되, 마지막 간선은 추가하지 않으면 두 개의 마을로 분리할 수 있음.
	분리하기 위해 마지막 간선을 추가하지 않는 이유는 해당 간선이 최소 신장 트리 중 제일 비싼 간선이기 때문.
	=> 정점의 수에 비해 간선(길)의 수가 적으므로, 크루스칼 알고리즘을 사용하여 풀이
*/

public class BOJ_1647 {

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

				if (++count == vertexCount - 2) // 마지막 간선은 더해주지 않음. 마을 분리 위해서
					break;
			}
		}
		
		return totalCost;
	}

	/* main */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		/* 입력 처리 */
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		PriorityQueue<Edge> edges = new PriorityQueue<>();
		
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			edges.offer(new Edge(from, to, weight));
		}
		
		/* 크루스칼 알고리즘 위한 parents 배열 */
		int[] parents = new int[N + 1];
		Arrays.fill(parents, -1);
		
		// 크루스칼 알고리즘으로 최소 비용 계산 후 출력
		System.out.println(kruskal(N, edges, parents));
	}

}
