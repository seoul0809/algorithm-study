package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753 {
	
	static class Vertex {
		int to, weight;
		public Vertex(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
	
	static class Node {
		int to, weight;
		Node next;
		public Node(int to, int weight, Node next) {
			this.to = to;
			this.weight = weight;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine())-1;
		Node[] adjList = new Node[V];
		
		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		boolean[] visited = new boolean[V];
		int[] D = new int[V];
		for (int i = 0; i < V; i++)	D[i] = Integer.MAX_VALUE;
		D[start] = 0;
		PriorityQueue<Vertex> PQ = new PriorityQueue<>((r1,r2)->r1.weight-r2.weight);
		PQ.add(new Vertex(start, D[0]));

		while (!PQ.isEmpty()) {
			Vertex ver = PQ.poll();
			int minVertex = ver.to;
			if(visited[minVertex])	continue;
			visited[minVertex] = true;

            for (Node node = adjList[minVertex]; node != null; node = node.next) {
				if(!visited[node.to] && D[node.to] > D[minVertex] + node.weight) {
					D[node.to] = D[minVertex] + node.weight;
					PQ.add(new Vertex(node.to, D[node.to]));
				}
			}
		}
		for (int i = 0; i < V; i++) {
			if(D[i] == Integer.MAX_VALUE)	System.out.println("INF");
			else	System.out.println(D[i]);
		}
	}
}
