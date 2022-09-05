import java.util.*;
import java.io.*;

/*
	BOJ 1753: 최단경로
	https://www.acmicpc.net/problem/1753	
	메모리/시간: 106380kb/908ms
	
 	[문제]
 	방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로 구하기
 	단, 간선의 가중치는 10 이하의 자연수
 	
 	[풀이]
 	주어진 시작점에서 다른 모든 정점으로의 최단 거리 구하기 => 다익스트라 알고리즘 활용
 */

public class BOJ_1753 {

	public static class Vertex {
		int idx, weight;
		Vertex next;

		public Vertex(int to, int weight, Vertex next) {
			this.idx = to;
			this.weight = weight;
			this.next = next;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		int start = Integer.parseInt(br.readLine().trim()); // 시작 정점
		Vertex[] adjList = new Vertex[V + 1]; // 1부터 시작

		// 입력 처리
		for (int e = 0; e < E; ++e) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			// 방향 그래프
			adjList[from] = new Vertex(to, weight, adjList[from]);
		}

		for (int v = 1; v < V; ++v) {
			adjList[v] = new Vertex(v, 0, adjList[v]);
		}

		// 다익스트라 위한 자료구조
		Integer[] D = new Integer[V + 1]; // 최소 거리 관리 위한 배열
		Arrays.fill(D, Integer.MAX_VALUE);

		// 시작점 설정
		D[start] = 0;

		// 최단 경로 관리 위한 pq
		PriorityQueue<Vertex> pq = new PriorityQueue<>((v1, v2) -> Integer.compare(v1.weight, v2.weight));
		pq.offer(new Vertex(start, D[start], null));

		int cnt = 0;
		while (!pq.isEmpty()) {
			Vertex curr = pq.poll();

			if (curr.weight > D[curr.idx])
				continue; // 최소 경로 아닌 경우 정점 방문 X

			if (++cnt == V) // 정점 다 방문하면 break
				break;

			for (Vertex temp = adjList[curr.idx]; temp != null; temp = temp.next) {
				if (D[temp.idx] > D[curr.idx] + temp.weight) { // 경로 더 작으면 D 업데이트 및 PQ에 넣기
					D[temp.idx] = D[curr.idx] + temp.weight;
					pq.offer(new Vertex(temp.idx, D[temp.idx], null));
				}
			}
		}

		// 모든 정점에 대한 거리 출력
		for (int v = 1; v <= V; ++v) {
			System.out.println(D[v] == Integer.MAX_VALUE ? "INF" : D[v]);

		}
	} // end main
}
