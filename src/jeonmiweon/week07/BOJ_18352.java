package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18352 {
	
	static int V, E, K, X;
	static List<Integer> answer;
	static List<Integer>[] adjList;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[V+1];
		for (int v = 0; v <= V; v++)	adjList[v] = new ArrayList<>();
		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
		}
		visited = new boolean[V+1];
		answer = new ArrayList<>();
		bfs(X);
		if(answer.size() > 0) {
			answer.sort((v1,v2)->v1-v2);
			for(int i = 0; i < answer.size(); i++)	System.out.println(answer.get(i));
		}
	}

	private static void bfs(int vertex) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(X);
		visited[X] = true;
		int level = 0;
		while(!queue.isEmpty()) {
			int queueSize = queue.size();
			for (int s = 0; s < queueSize; s++) {
				int next = queue.poll();
				if(level == K)	answer.add(next);
				for (int i = 0; i < adjList[next].size(); i++) {
					int candidate = adjList[next].get(i);
					if(!visited[candidate]) {
						queue.add(candidate);
						visited[candidate] = true;
					}
				}
			}
			level++;
		}
		if(level <= K)	System.out.println(-1);
	}
}
