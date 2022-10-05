import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
	BOJ 18405: 경쟁적 전염
	https://www.acmicpc.net/problem/18405
	메모리/시간: 21312kb/316ms
	
	[문제]
	N*N 크기의 시험관이 있다. 특정 위치에 1~K번의 바이러스가 존재할 수 있다.
	시험관의 바이러스가 1초마다 상하좌우의 방향으로 증식해나간다.
	이때, 번호가 낮은 종류의 바이러스부터 증식하고, 이미 바이러스가 존재하는 칸이라면 증식할 수 없다.
	S초가 지난 후, (X,Y)에 존재하는 바이러스를 출력하라. 
	단, 시험관의 가장 왼쪽 위에 해당하는 곳은 (1,1)에 해당한다.
	
	[풀이]
	BFS 풀이에 바이러스의 값과 시간을 고려해 푸는 문제!
	바이러스의 값은 PQ를 사용해서 정렬하고, 시간만큼만 진행하는 것은 PQ의 사이즈를 사용해 처리했다.	
*/


public class BOJ_18405 {

	public static final int[] dr = { 0, 0, -1, 1 };
	public static final int[] dc = { 1, -1, 0, 0 };

	public static int N, K, S, X, Y;
	public static int[][] map;
	public static PriorityQueue<Virus> pq;

	public static class Virus implements Comparable<Virus> {
		int r, c, v, t; // row, col, value, time

		public Virus(int r, int c, int v, int t) {
			super();
			this.r = r;
			this.c = c;
			this.v = v;
			this.t = t;
		}

		// 시간이 더 빠른 순서, 시간 같다면 값이 더 작은 순서
		public int compareTo(Virus other) {
			return Integer.compare(this.t, other.t) == 0 ? Integer.compare(this.v, other.v)
					: Integer.compare(this.t, other.t);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N]; // (0,0) ~ (N-1,N-1)을 사용
		pq = new PriorityQueue<>();

		for (int r = 0; r < N; ++r) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; ++c) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] != 0) {
					pq.add(new Virus(r, c, map[r][c], 0));
				}
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		System.out.println(bfs(pq, map, S, X, Y));

	}

	private static int bfs(PriorityQueue<Virus> pq, int[][] map, int S, int X, int Y) {

		int time = 0;
		int r, c, v, t;
		int nr, nc;

		// 채울 수 있는 칸을 모두 채웠거나 시간이 다 지나는 것 중 더 빠른 걸로 종료
		while (!pq.isEmpty() && time < S) {

			int size = pq.size();

			for (int i = 0; i < size; ++i) {
				Virus curr = pq.poll(); // pq 사용해서 제일 작은 값의 바이러스부터 처리

				r = curr.r;
				c = curr.c;
				v = curr.v;
				t = curr.t;

				for (int d = 0; d < 4; ++d) {
					nr = r + dr[d];
					nc = c + dc[d];

					if (canVisit(nr, nc)) {
						map[nr][nc] = v;
						pq.offer(new Virus(nr, nc, v, t + 1));
					}
				}
			}

			++time;
		}

		return map[X - 1][Y - 1]; // (0,0)부터 인덱스를 시작해서 (X-1,Y-1)의 값을 반환
	}

	private static boolean canVisit(int r, int c) {
		if (r < 0 || c < 0 || r >= N | c >= N || map[r][c] != 0)
			return false;
		return true;
	}

}
