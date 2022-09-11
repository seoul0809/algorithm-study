import java.util.*;
import java.io.*;

/*
	BOJ 1774: 행성 연결
	https://www.acmicpc.net/problem/1774
	메모리/시간: 61096kb/1016ms

	[문제]
	N개의 우주신과 이미 연결된 신들과의 통로 개수 M개가 주어졌을 때 남은 모든 우주신과 연결하기 위해 드는 최소 비용 계산 
	 
	- 우주신의 개수 N : N ≤ 1000
	- 이미 연결된 통로 개수 M: M ≤ 1000
	
	[풀이]
	계속 MST 문제만 풀고 있어서 크루스칼로 풀긴 했지만, 이게 제일 빠른 방법은 아닌 것 같음.
	- 이미 연결된 통로는 union을 통해 방문 여부 표시
	- 남은 우주신 간 통로들은 PQ를 사용해 최소 간선을 저장 및 크루스칼로 최소 신장 트리 계산
*/

public class BOJ_1774 {

	/* 좌표 표현 위한 클래스 */
	public static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	/* 간선(통로) 표현 위한 클래스 */
	public static class Edge implements Comparable<Edge> {
		int from, to;
		double dist;

		public Edge(int from, int to, double dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}

		public int compareTo(Edge other) {
			return Double.compare(this.dist, other.dist);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		/* 입력 받기 */
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Point[] points = new Point[N + 1];
		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			points[i] = new Point(r, c);
		}

		// 크루스칼 위한 변수
		int[] parents = new int[N + 1];
		Arrays.fill(parents, -1);

		// 이미 존재하는 통로에 대해 union을 사용해 통로 존재 여부 표시
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			union(parents, from, to);
		}
		
		// 우주신 간 거리 계산, PQ 사용해 최소 거리부터 정렬되도록 함
		PriorityQueue<Edge> edges = new PriorityQueue<>();		
		for (int i = 1; i <= N - 1; ++i) {
			for (int j = 2; j <= N; ++j) {
				if (i == j)
					continue;

				edges.offer(new Edge(i, j, calcDistance(points[i], points[j])));
			}
		}
		
		// 소수점 둘째자리까지 출력
		System.out.printf("%.2f", kruskal(N, parents, edges));
	}

	/* UNION FIND 및 크루스칼 알고리즘 관련 함수 */
	public static double kruskal(int N, int[] parents, PriorityQueue<Edge> edges) {
		int count = 0;
		double totalDist = 0;

		while (!edges.isEmpty()) {
			Edge minEdge = edges.poll();

			if (!union(parents, minEdge.from, minEdge.to))
				continue;

			totalDist += minEdge.dist;

			if (++count == N - 1)
				break;
		}

		return totalDist;
	}

	private static boolean union(int[] parents, int a, int b) {
		int rootA = findRoot(parents, a);
		int rootB = findRoot(parents, b);

		if (rootA == rootB)
			return false;

		parents[rootA] += parents[rootB];
		parents[rootB] = rootA;

		return true;
	}

	private static int findRoot(int[] parents, int a) {
		if (parents[a] < 0)
			return a;
		else
			return parents[a] = findRoot(parents, parents[a]);
	}

	/* 좌표 간 거리 계산 함수 */
	private static double calcDistance(Point x, Point y) {
		return Math.sqrt(Math.pow(x.r - y.r, 2) + Math.pow(x.c - y.c, 2));
	}

}
