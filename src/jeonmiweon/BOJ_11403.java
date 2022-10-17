package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11403 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int map[][] = new int[N][N];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[n][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] > 0)	continue;
					if(map[i][k] + map[k][j] == 2)	map[i][j] = 1;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
