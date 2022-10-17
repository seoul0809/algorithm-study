import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18352
/*
 X로부터 출발하여 도달할 수 있는 도시 중에서, 최단 거리가 K인 모든 도시를 출력
 이 때 도달할 수 있는 도시 중에서, 최단 거리가 K인 도시가 하나도 존재하지 않으면 -1을 출력
  
 인접리스트와 BFS로 구현
 */
public class BOJ_18352 {
	
	static class Node{
		int to;
		Node next;
		
		public Node(int to, Node next) {
			super();
			this.to = to;
			this.next = next;
		}
	}
	
	static Node[] adjList; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 도시의 개수, 도로의 개수, 거리 정보, 출발 도시의 번호
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int shortDis = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken())-1;
		
		adjList = new Node[V];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			
			// 인접 리스트에 저장
			adjList[from] = new Node(to, adjList[from]);
		}
		
		// 도시에 대한 거리를 담을 배열
		int[] D = new int[V];
		// 배열의 원소값들을 -1로 초기화
		Arrays.fill(D, -1);
		// 출발 도시 X에서 출발 도시 X로 가는 최단 거리는 항상 0이라고 가정
		D[start] = 0;
		// BFS 탐색을 위한 que 생성
		Queue<Integer> que = new LinkedList<>();
		que.offer(start);
		
		while(!que.isEmpty()) {
			int cur = que.poll();
			
			for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
				if(D[temp.to] == -1) { // 아직 방문하지 않은 도시
					D[temp.to] = D[cur] + 1;
					que.offer(temp.to);
				}
			}
		}
		
		// 최단 거리가 K인 도시가 하나도 존재하지 않은지 체크하기 위한 변수
		boolean check = false; 
		for (int i = 0; i < V; i++) {
			if(D[i] == shortDis) {
				System.out.println(i+1);
				check = true;
			}
		}
		if(!check) {
			System.out.println(-1);
		}
	}

}
