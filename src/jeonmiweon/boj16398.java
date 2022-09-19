package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj16398 {

	static int V;
	static int[] minEdge;
	static int[][] adjMatrix;
	static long answer;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		V = Integer.parseInt(br.readLine());
		adjMatrix = new int[V][V];
		for (int v = 0; v < V; v++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < V; j++)	adjMatrix[v][j] = Integer.parseInt(st.nextToken());
		}
		minEdge = new int[V];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[0] = 0;
		answer = 0;
		visited = new boolean[V];
		
		for (int v = 0; v < V; v++) {
			int min = Integer.MAX_VALUE;
			int minVertex = -1;
			for (int i = 0; i < V; i++) {
				if(!visited[i] && min > minEdge[i]) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			visited[minVertex] = true;
			answer += min;
			
			for (int i = 0; i < V; i++) {
				if(!visited[i] && adjMatrix[minVertex][i] > 0 && minEdge[i] > adjMatrix[minVertex][i])
					minEdge[i] = adjMatrix[minVertex][i];
			}
		}
		System.out.println(answer);
	}

}
