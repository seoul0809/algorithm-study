package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class boj1922 {

	static int V, E, countEdge;
	static long answer;
	static Edge[] edgelist;
	static int[] parents;
	
	static class Edge {
		int from, to, weight;
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}
	
	public static void make_set() {
		parents = new int[V+1];
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
		StringTokenizer st;
		
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		edgelist = new Edge[E];
		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			edgelist[e] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(edgelist, new Comparator<Edge>() {
			@Override
			public int compare(Edge arg0, Edge arg1) {
				return arg0.weight - arg1.weight;
			}
		});
		make_set();
		countEdge = 0;
		answer = 0;
		for (Edge edge : edgelist) {
			if(union_set(edge.from, edge.to)) {
				answer += edge.weight;
				if(++countEdge == V-1)	break;
			}
		}
		System.out.println(answer);
	}
}