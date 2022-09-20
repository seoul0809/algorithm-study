import java.util.*;
import java.io.*;

/*
	BOJ 1922: 네트워크 연결
	https://www.acmicpc.net/problem/1922
	메모리/시간: 46960kb/432ms

	[문제]
	네트워크 상 모든 컴퓨터를 연결하려고 할 때 필요한 최소비용 계산
	- 컴퓨터의 수 N : 1 ≤ N ≤ 1000
	- 연결할 수 있는 선의 수 M : 1 ≤ M ≤ 100,000

	[풀이]
	컴퓨터(정점)의 수에 비해 연결할 수 있는 선(간선)의 수가 적으므로, 크루스칼 알고리즘을 사용하여 풀이.
	간선의 배열과 PQ를 둘 다 구현해봤을 때, 간선의 숫자가 많아질수록 배열 정렬하는 시간이 많이 들어서 PQ가 더 빠름!
*/

public class BOJ_1922 {

	/* 간선 표현하기 위한 클래스. 비용 오름차순으로 정렬 */
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

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		/* 입력 처리 */
		int N = Integer.parseInt(br.readLine().trim());
		int M = Integer.parseInt(br.readLine().trim());

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

	/* 크루스칼 알고리즘 */
	public static int kruskal(int vCount, PriorityQueue<Edge> edges, int[] parents) {
		int cost = 0;
		int count = 0;

		while (!edges.isEmpty()) {
			Edge edge = edges.poll();
			if (union(parents, edge.from, edge.to)) {
				cost += edge.weight;
				if (++count == vCount - 1)
					break;
			}

		}

		return cost;
	}

	/* union-find 관련 함수 */
	private static int findRoot(int[] parents, int idx) {
		if (parents[idx] < 0)
			return idx;
		else
			return parents[idx] = findRoot(parents, parents[idx]);
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

}
