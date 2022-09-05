import java.io.*;
import java.util.*;

/*
	BOJ 18352: 특정 거리의 도시 찾기
	https://www.acmicpc.net/problem/18352
	메모리/시간: 240124kb/680ms
	
	[문제]
	특정 도시 X로부터 출발해서 도달할 수 있는 모든 도시 중에서 최단 거리가 정확히 K인 모든 도시의 번호 출력하기
	
	[풀이]
	모든 도로의 거리는 1이기 때문에 BFS로도 충분히 풀 수 있음! 다익스트라와 BFS 둘 다 풀어봤는데, BFS가 (당연히) 더 빠름.
	- 시작 정점 X에 대해 너비 K까지만 BFS를 수행한 후, 그 때 큐에 들어있는 것이 거리 K인 모든 도시와 같음.
	- 오름차순으로 결과를 출력하기 위해서 PriorityQueue를 사용함.
 */

public class BOJ_18352 {

	public static class Vertex {
		int idx, weight;
		Vertex next;

		public Vertex(int idx, int weight, Vertex next) {
			this.idx = idx;
			this.weight = weight;
			this.next = next;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		/* 입력 받기 */
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		Vertex[] vertices = new Vertex[N + 1];
		for (int e = 0; e < M; ++e) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			vertices[from] = new Vertex(to, 1, vertices[from]); // 단방향 그래프
		}

		/* BFS 위한 변수들 */
		boolean[] visited = new boolean[N + 1];
		PriorityQueue<Vertex> pq = new PriorityQueue<>(
				(v1, v2) -> Integer.compare(v1.weight, v2.weight) == 0 ? Integer.compare(v1.idx, v2.idx)
						: Integer.compare(v1.weight, v2.weight));

		pq.offer(new Vertex(X, 0, null));
		visited[X] = true;

		int size = 0, depth = 0;
		while (!pq.isEmpty()) {
			size = pq.size();

			for (int s = 0; s < size; ++s) {
				Vertex curr = pq.poll();

				for (Vertex temp = vertices[curr.idx]; temp != null; temp = temp.next) {
					if (visited[temp.idx])
						continue;
					visited[temp.idx] = true;
					pq.offer(new Vertex(temp.idx, curr.weight + 1, null));
				}
			}

			if (++depth == K)
				break;
		}

		if (pq.isEmpty()) {
			bw.write("-1\n");
		} else {
			while (!pq.isEmpty()) {
				bw.write(pq.poll().idx + "\n");
			}
		}

		bw.flush();
		bw.close();
		br.close();
	} // end main

}
