import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14503_로봇청소기_전미원 {
	
	private static int N, M, r, c, d, map[][], answer, flag;
	private static boolean[][] visited;
	private static int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		answer = 0;
		visited = new boolean[N][M];
		visited[r][c] = true;
		while (true) {
			flag = 0;
			answer++;
			for (int i = d-1; i >= d-4; i--) {
				int dir = i < 0 ? i+4 : i;
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] != 1) {
					r = nr;	c = nc;	d = dir;
					visited[nr][nc] = true;	
					flag = 1;
					break;
				}
			}
			if (flag == 0) {
				int dir = d-2 < 0 ? d+2 : d-2;
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 1) {
					r = nr;	c = nc;	
					if (visited[nr][nc])	answer--;
					else	visited[nr][nc] = true;
				} else	break;
			}
		}
		System.out.println(answer);
	}
}
