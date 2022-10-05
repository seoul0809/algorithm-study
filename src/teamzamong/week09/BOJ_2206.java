import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 	BOJ 2206: 벽 부수고 이동하기
 	https://www.acmicpc.net/problem/2206
 	메모리/시간: 115396kb/688ms
 	
 	[문제]
 	N*M 2차원 배열이 주어졌을 때, 0은 이동 가능/1은 벽이라 이동 불가능을 뜻한다.
 	이동하는 중 한 개의 벽을 부수고 이동할 수 있다고 할 때,
 	(1,1)에서 (N,M)로 이동할 때의 최단 경로의 길이를 구하시오.
 	단, 시작하는 칸과 끝나는 칸도 포함해서 센다.
 	
 	[풀이]
 	기존 BFS의 방문 배열에 벽을 부쉈는지 여부를 포함해 3차원 방문배열을 사용해서 푸는 문제
 	BFS 풀이와 동일하지만, 가려는 곳이 벽일 때 이미 벽을 부쉈는지 여부를 한 번 더 확인한다.
 	
 */

public class BOJ_2206 {

	public static final int[] dr = { 0, 0, -1, 1 };
	public static final int[] dc = { -1, 1, 0, 0 };

	public static int N, M;
	public static char map[][];
	public static boolean visited[][][];

	public static class Point {
		int r, c, h, k; // row, col, 이동횟수, 벽 부쉈는지 여부(0:false, 1:true)

		public Point(int r, int c, int h, int k) {
			this.r = r;
			this.c = c;
			this.h = h;
			this.k = k;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M]; // (0,0)~(N-1,M-1)로 수정해서 풀이

		// input이 0100처럼 띄어쓰기 없이 들어오기 때문에
		// 아래와 같이 곧장 char 배열로 처리하는 것이 시간이 더 적게 걸렸음!
		for (int r = 0; r < N; ++r) {
			map[r] = br.readLine().trim().toCharArray();
		}

		// visited[r][c][0]: 벽을 안 부수고 (r,c)에 방문한 적이 있는지
		// visited[r][c][1]: 벽을 부수고 (r,c)에 방문한 적이 있는지
		visited = new boolean[N][M][2];

		System.out.println(bfs(visited));

	}

	private static boolean inRange(int r, int c) {
		if (r < 0 || c < 0 || r >= N || c >= M)
			return false;
		return true;
	}

	private static int bfs(boolean[][][] visited) {

		Queue<Point> queue = new ArrayDeque<>();

		// 시작값: 시작하는 값부터 이동 횟수를 셈
		queue.offer(new Point(0, 0, 1, 0));
		visited[0][0][0] = true;

		int cr, cc, ch, ck;
		int nr, nc;
		while (!queue.isEmpty()) {
			Point curr = queue.poll();
			cr = curr.r;
			cc = curr.c;
			ch = curr.h;
			ck = curr.k;

			if (cr == N - 1 && cc == M - 1) { // 목적지 도착, BFS기 때문에 최단임
				return ch;
			}

			for (int d = 0; d < 4; ++d) {
				nr = cr + dr[d];
				nc = cc + dc[d];

				// 범위 벗어남, 벽인데 이미 벽을 부숴서 방문 불가, 이미 방문한 곳인 경우
				if (!inRange(nr, nc) || (map[nr][nc] == '1' && ck == 1) || visited[nr][nc][ck])
					continue;

				if (map[nr][nc] == '1') { // 벽인 경우, 부숴서 갈 수 있음
					queue.offer(new Point(nr, nc, ch + 1, 1));
					visited[nr][nc][1] = true;
				} else { // 벽이 아닌 경우
					queue.offer(new Point(nr, nc, ch + 1, ck));
					visited[nr][nc][ck] = true;
				}
			}
		}

		return -1;
	}

}
