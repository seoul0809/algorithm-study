package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj1647 {
	
	static int V, E, countEdge;
	static long answer;
	static int[] parents;
	static PriorityQueue<Edge> PQ;
	
	static class Edge implements Comparable<Edge> {
		int from, to, weight;
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	public static void make_set() {
		parents = new int[V + 1];
		for (int v = 0; v <= V; v++)	parents[v] = -1;
	}
	
	public static int find_parent(int node) {
		if(parents[node] < 0)	return node;
		return parents[node] = find_parent(parents[node]);
	}
	
	public static boolean union_set(int node1, int node2) {
		int parent1 = find_parent(node1);
		int parent2 = find_parent(node2);
		if(parent1 == parent2)	return false;
		parents[parent1] += parents[parent2];
		parents[parent2] = parent1;
		return true;
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		PQ = new PriorityQueue<>();
		
		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			PQ.add(new Edge(Integer.parseInt(st.nextToken()), 
								 Integer.parseInt(st.nextToken()), 
								 Integer.parseInt(st.nextToken())));
		}
		make_set();
		countEdge = 0;
		answer = 0;
		while (!PQ.isEmpty()) {
			Edge edge = PQ.poll();
			if(union_set(edge.from, edge.to)) {
				answer += edge.weight;
				if(++countEdge == V - 2)	break;
			}
		}
		System.out.println(answer);
	}
}
