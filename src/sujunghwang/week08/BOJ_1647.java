import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1647
/*
마을에 N개의 집과 그 집들을 연결하는 M개의 길이 있다. 
하나의 마을을 2개의 마을로 분할할 때, 마을 내에서는 모든 집들이 연결되어야 하지만 다른 마을 과의 경로는 존재할 필요가 없다.
길을 유지하는 비용의 최소를 구하시오.

크루스칼 알고리즘을 이용하면 마을 전체의 집을 연결하는 경로가 나온다. 
여기서 가장 큰 유지비를 갖는 경로를 끊으면 2개의 마을로 분리할 때 길의 유지비의 최소가 된다.

메모리 : 320788KB, 시간 : 1196ms
*/

public class BOJ_1647 {
	
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return weight - o.weight;
		}
	}

	static PriorityQueue<Edge> edgePQ;
	static int V,E;
	static int parents[];
		
	static void make() {
		parents = new int[V];
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		edgePQ = new PriorityQueue<>((v1,v2)->v1.weight-v2.weight);
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			edgePQ.add(new Edge(Integer.parseInt(st.nextToken())-1,
					Integer.parseInt(st.nextToken())-1,
					Integer.parseInt(st.nextToken())));
		}
		
		make();
		
		int result = 0, count = 0;
		
		while(true) {
			Edge edge = edgePQ.poll();
			if(union(edge.from, edge.to)) {
				result += edge.weight;
				// PQ를 이용하므로 가장 마지막 값을 추가하지 않으면 유지비가 가장 큰 길을 선택하지 않는 것이다.
				if(++count == V - 2) break; 
			}
		}
		
		System.out.println(result);
	}

}
