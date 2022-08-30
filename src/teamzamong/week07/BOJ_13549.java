import java.util.*;
import java.io.*;

/*
	BOJ 13549: 숨바꼭질 3
	https://www.acmicpc.net/problem/13549	
	메모리/시간: 23644kb/276ms

	[문제]
	수빈이가 점 N(0 <= N <= 100,000)에 있고 동생이 점 K(0 <= K <= 100,000)에 있다.
	위치 X에서 1초 걸려 걷거나(X+1, X-1) 또는 순간이동으로 0초 후에 2*X 위치로 이동할 수 있을 때,
	수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하기
	
	[풀이]
	간선 값이 0 또는 1인 그래프에서 다익스트라를 사용한다고 생각해서 풀이함.
	- 현 지점에서 이동할 수 있는 모든 경우의 수에 대해 기존 최단 거리 값과 비교 후, 
	  만약 현 지점에서 이동할 수 있는 거리가 더 짧다면 최단 거리 업데이트 (PQ 및 dist 배열 사용)
	- 동생이 있는 지점에 도착했을 때 종료    
*/

public class BOJ_13549 {

	public static class Node {
		int idx, time;

		public Node(int idx, int time) {
			this.idx = idx;
			this.time = time;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		// 입력 처리
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// 다익스트라를 위한 변수
		int[] dist = new int[100_001]; // 0 ~ 100_000
		Arrays.fill(dist, 999_999); // 임의의 큰 값으로 채우기
		dist[N] = 0;

		// 걸린 시간 기준으로 PQ 정렬
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.time, n2.time));
		pq.add(new Node(N, 0));

		// 동생(K) 찾을 때까지 최단 거리 계산 반복
		while (!pq.isEmpty()) {
			Node curr = pq.poll();

			if (curr.idx == K) {
				System.out.println(curr.time);
				System.exit(0);
			}

			if (curr.time > dist[curr.idx])
				continue;

			int idx = curr.idx;
			int time = curr.time;
			
			// X - 1, X + 1
			if (checkRange(idx + 1) && dist[idx + 1] > time + 1) {
				dist[idx + 1] = time + 1;
				pq.offer(new Node(idx + 1, dist[idx + 1]));
			}

			if (checkRange(idx - 1) && dist[idx - 1] > time + 1) {
				dist[idx - 1] = time + 1;
				pq.offer(new Node(idx - 1, dist[idx - 1]));
			}

			// 2 * X
			if (checkRange(idx * 2) && dist[idx * 2] > time) {
				dist[idx * 2] = time;
				pq.offer(new Node(idx * 2, dist[idx * 2]));
			}
		}

	}

	private static boolean checkRange(int n) { // 값이 범위를 벗어나는지 체크
		if (n < 0 || n > 100_000)
			return false;
		return true;
	}

}
