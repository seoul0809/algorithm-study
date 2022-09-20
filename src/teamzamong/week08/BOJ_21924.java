import java.io.*;
import java.util.*;

/*
	BOJ 21924: 도시 건설
	https://www.acmicpc.net/problem/21924
	메모리/시간: 178628kb/944ms
	
	[문제]
	N개의 건물과 건물을 연결하는 도로 M개가 주어졌을 때, 최소 비용만 들도록 도로를 건설했을 때 절약 비용 계산
	
	- 건물의 개수 N : 3 ≤ N ≤ 10^5
	- 도로의 개수 M : 2 ≤ M ≤ min(N(N - 1) / 2, 5 * 10^5)
	
	[풀이]
	건물(정점)의 개수에 비해 도로(간선)의 길이가 많은 편이므로, 프림 알고리즘을 사용
	=> 절약 비용 = (전체 도로 비용) - (최소 간선 트리 비용)
*/

public class BOJ_21924 {

	/* 정점 표현 위한 클래스 */
	public static class Vertex implements Comparable<Vertex> {
		int to, weight;
		Vertex next;

		public Vertex(int to, int weight, Vertex next) {
			this.to = to;
			this.weight = weight;
			this.next = next;
		}

		public int compareTo(Vertex other) {
			return Integer.compare(this.weight, other.weight);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력 처리
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Vertex[] vertices = new Vertex[N + 1];
		
		long totalCost = 0; // 전체 비용 계산 
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			// 양방향 그래프 처리
			vertices[from] = new Vertex(to, weight, vertices[from]);
			vertices[to] = new Vertex(from, weight, vertices[to]);
			totalCost += weight;
		}

		long minCost = prim(vertices);

		if (minCost == -1) { // 전체 연결 못하는 경우
			System.out.println(minCost);
		} else {
			System.out.println(totalCost - minCost);
		}
	}

	/* 프림 알고리즘 구현 */
	public static long prim(Vertex[] vertices) {
		
		int V = vertices.length;
		int[] minEdges = new int[V];
		boolean[] visited = new boolean[V];

		Arrays.fill(minEdges, Integer.MAX_VALUE);

		// 1번 건물부터 연결 시작
		minEdges[1] = 0;
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(1, 0, null));

		long totalWeight = 0;
		int cnt = 0;
		
		while (!pq.isEmpty()) {
			Vertex minVertex = pq.poll();

			if (visited[minVertex.to])
				continue;

			visited[minVertex.to] = true;
			totalWeight += minVertex.weight;
			if (++cnt == V - 1)
				break;

			for (Vertex temp = vertices[minVertex.to]; temp != null; temp = temp.next) {
				if (!visited[temp.to] && minEdges[temp.to] > temp.weight) {
					minEdges[temp.to] = temp.weight;
					pq.offer(new Vertex(temp.to, temp.weight, null));
				}
			}
		}

		// 모든 건물이 연결되었는지 확인
		for (int i = 1; i < V; ++i) {
			if (minEdges[i] == Integer.MAX_VALUE) {
				totalWeight = -1;
				break;
			}
		}

		return totalWeight;
	}

}
