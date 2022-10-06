package temp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16948 {

	static int N, answer;
	static int HOR = 0, VER = 2, DIA = 1;
	static int[] dx_hor = {0, 1}, dy_ver = {0, 1};
	static int[] dx_dia = {0, 1, 1}, dy_dia = {1, 0, 1};
	static int[][] map;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		answer = 0;
		dfs(0, 1, HOR);
		System.out.println(answer);
	}

	private static void dfs(int x, int y, int direct) {
		if(x == N-1 && y == N-1) {
			answer++;
			return;
		}
		int new_direct;
		switch(direct) {
		case 0:
			for(int i = 0; i < 2; i++) {
				int nx = x + dx_hor[i];
				int ny = y + 1;
				if(nx < 0 || nx > N-1 || ny < 0 || ny > N-1 || map[nx][ny] == 1)	continue;

				if(i == 1)	new_direct = DIA;
				else	new_direct = direct;
				if(new_direct == DIA) {
					if(map[nx][ny-1] == 1 || map[nx-1][ny] == 1)	continue;
				}
				dfs(nx, ny, new_direct);
			}
			break;
		case 2:
			for(int i = 0; i < 2; i++) {
				int nx = x + 1;
				int ny = y + dy_ver[i];
				if(nx < 0 || nx > N-1 || ny < 0 || ny > N-1 || map[nx][ny] == 1)	continue;

				if(i == 1)	new_direct = DIA;
				else	new_direct = direct;
				if(new_direct == DIA) {
					if(map[nx][ny-1] == 1 || map[nx-1][ny] == 1)	continue;
				}
				dfs(nx, ny, new_direct);			
            }
			break;
		case 1:
			for(int i = 0; i < 3; i++) {
				int nx = x + dx_dia[i];
				int ny = y + dy_dia[i];
				if(nx < 0 || nx > N-1 || ny < 0 || ny > N-1 || map[nx][ny] == 1)	continue;

				if(i == 0)	new_direct = HOR;
				else if(i == 1)	new_direct = VER;
				else	new_direct = DIA;

				if(new_direct == DIA) {
					if(map[nx][ny-1] == 1 || map[nx-1][ny] == 1)	continue;
				}
				dfs(nx, ny, new_direct);			
            }
			break;
		}
	}
}
