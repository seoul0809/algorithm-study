import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1753
/*
 방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하기
 
 다익스트라 알고리즘(특정한 하나의 정점에서 다른 모든 정점으로 가는 최단 경로를 구하는 알고리즘) 사용
 
 메모리 : 104644KB, 시간 : 908ms 
 */

public class BOJ_1753 {
	
	static class Node{
		int to, weight;
		Node next;
		
		public Node(int to, int weight, Node next) {
			super();
			this.to = to;
			this.weight = weight;
			this.next = next;
		}
	}

	static int V;
	static Node[] adjList;
	static int[] D;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		
		adjList = new Node[V+1];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		
		dijk(start);
		for (int i = 1; i <= V; i++) {
			if(D[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(D[i]);
		}
		
	}
	
	private static void dijk(int start) {
		// 가중치를 기준으로 정렬하는 우선순위 큐
		PriorityQueue<Node> pq = new PriorityQueue<>((v1,v2) -> v1.weight - v2.weight);
		// 방문 관리 배열
		boolean[] visited = new boolean[V+1];
		// 최단 경로를 닮을 배열
		D = new int[V+1];
		// 값 비교를 통해 더 작은 값을 넣어가면서 최단 거리를 구할 것이므로 
		// 배열을 Integer.MAX_VALUE로 초기화
		Arrays.fill(D, Integer.MAX_VALUE);
		// 시작점 자신은 0으로 출력해야 하므로 0으로 초기화 
		D[start] = 0;
		pq.offer(new Node(start,0,adjList[start].next));
		
		while (!pq.isEmpty()) {
			Node curNode = pq.poll();
			int cur = curNode.to;
			
			if(visited[cur]) continue;
			visited[cur] = true;
			
			for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
				// 현재 값보다 cur을 거쳐서 가는 경우의 값이 더 작으면 배열 값 업데이트
				if(D[temp.to] > D[cur] + temp.weight) {
					D[temp.to] = D[cur] + temp.weight;
					pq.offer(new Node(temp.to, D[temp.to], temp.next));
				}
			}
		}
	}
}












