package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1080 {

	private static int N, M, arr[][], target[][], answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		target = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j)-'0';
			}
		}
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				int num = str.charAt(j)-'0';
				if (arr[i][j] != num)	arr[i][j] = 1;
				else	arr[i][j] = 0;
			}
		}
		if (N < 3 || M < 3) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == 1) {
						System.out.println(-1);
						return;
					}
				}
			}
			System.out.println(0);
			return;
		}
		answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 1) {
					if (i + 2 < N && j + 2 < M) {
						for (int x = i; x < i + 3; x++) {
							for (int y = j; y < j + 3; y++) {
								arr[x][y] = 1-arr[x][y];
							}
						}
						answer++;
					} else {
						System.out.println(-1);
						return;
					}
				}
			}
		}
		System.out.println(answer);
	}
}
