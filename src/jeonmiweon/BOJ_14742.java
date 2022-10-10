import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14742_연구소3_전미원 {

	private static int N, M, map[][], answer;
	private static List<int[]> virusPos;
	private static boolean[] selected;
	private static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		virusPos = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)	virusPos.add(new int[] {i, j});
			}
		}
		selected = new boolean[virusPos.size()];
		answer = Integer.MAX_VALUE;
		comb(0, 0);
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	private static void comb(int count, int start) {
		if (count == M) {
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < virusPos.size(); i++)
				if (selected[i])	list.add(i);
			answer = Math.min(bfs(list), answer);
			return;
		}
		for (int i = start; i < virusPos.size(); i++) {
			if (selected[i])	continue;
			selected[i] = true;
			comb(count + 1, i + 1);
			selected[i] = false;
		}
	}

	private static int bfs(List<Integer> list) {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < list.size(); i++) {
			int[] pos = virusPos.get(list.get(i));
			queue.add(new int[] {pos[0], pos[1], 0});
			visited[pos[0]][pos[1]] = true;
		}
		int countSpace = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0)	countSpace++;
			}
		}
		while (!queue.isEmpty()) {
			int[] pos = queue.poll();
			if (map[pos[0]][pos[1]] == 0)	countSpace--;
			if (countSpace == 0)	return pos[2];
			for (int d = 0; d < 4; d++) {
				int nx = pos[0] + dx[d];
				int ny = pos[1] + dy[d];
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] != 1) {
					queue.add(new int[] {nx, ny, pos[2] + 1});
					visited[nx][ny] = true;
				}
			}
		}
		return Integer.MAX_VALUE;
	}
}
