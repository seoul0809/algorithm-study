import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1922
/*
각 컴퓨터를 연결하는데 필요한 비용이 주어졌을 때 모든 컴퓨터를 연결하는데 필요한 최소비용 구하기

크루스칼 알고리즘과 우선순위 큐를 이용하여 풀이

메모리 : 46492KB, 시간 : 424ms
*/

public class BOJ_1922 {

	static class Edge{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}
	
	static int[] parents;
	static int N,M;
	static PriorityQueue<Edge> edgePQ;
	
	static void make() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i; // 모든 노드가 자신을 부모(대표자)로 하는 집합으로 만듦
		}
	}
	
	static int find(int a) { // a의 대표자 찾기
		if(parents[a] == a) return a;
		
		return parents[a] = find(parents[a]); // 우리의 대표자를 나의 부모로 : path compression
	}
	
	static boolean union(int a, int b) { // 리턴값 : true => union 성공
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		edgePQ = new PriorityQueue<>((v1,v2)->v1.weight-v2.weight); // 간선의 비용을 기준으로 정렬
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			edgePQ.add(new Edge(Integer.parseInt(st.nextToken())-1,	
					Integer.parseInt(st.nextToken())-1,	
					Integer.parseInt(st.nextToken())));
		}
		
		make();
		
		int result = 0, count = 0;
		while (true) {
			Edge edge = edgePQ.poll();
			if(union(edge.from, edge.to)) {
				result += edge.weight;
				if(++count == N-1) break;
			}
		}
		System.out.println(result);
	}
}
