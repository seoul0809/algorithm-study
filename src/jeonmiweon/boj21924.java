package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj21924 {

	static int V, E;
	static long answer, total;
	static int[] minEdge;
	static boolean[] visited;
	static PriorityQueue<Vertex> PQ;
	static List<Vertex>[] adjList;
	
	static class Vertex implements Comparable<Vertex> {
		int to, weight;
		public Vertex(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Vertex o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[V + 1];
		for (int v = 0; v <= V; v++)	adjList[v] = new ArrayList<>();
		total = 0;
		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from].add(new Vertex(to, weight));
			adjList[to].add(new Vertex(from, weight));
			total += weight;
		}
		visited = new boolean[V + 1];
		PQ = new PriorityQueue<>();
		minEdge = new int[V + 1];
		for (int v = 0; v <= V; v++)	minEdge[v] = Integer.MAX_VALUE;
		minEdge[1] = 0;
		answer = 0;
		PQ.add(new Vertex(1, 0));
		
		while (!PQ.isEmpty()) {
			Vertex vertex = PQ.poll();
			if(visited[vertex.to])	continue;
			visited[vertex.to] = true;
			answer += vertex.weight;
			
			for (int i = 0; i < adjList[vertex.to].size(); i++) {
				Vertex next = adjList[vertex.to].get(i);
				if(!visited[next.to] && minEdge[next.to] > next.weight) {
					PQ.add(new Vertex(next.to, next.weight));
					minEdge[next.to]= next.weight; 
				}
			}
		}
		int count = 0;
		for (int v = 1; v <= V; v++)	if(visited[v])	count++;
		if(count == V)	System.out.println(total - answer);
		else	System.out.println(-1);
	}
}
