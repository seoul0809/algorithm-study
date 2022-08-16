package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class tue_boj_17136 {

	static int N = 10, answer;
	static int[] counts = {0,5,5,5,5,5};
	static int[][] board;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		board = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		answer = Integer.MAX_VALUE;
		DFS(0, 0, 0);
		
		if(answer == Integer.MAX_VALUE)		answer = -1;
		System.out.println(answer);
	}
	
	private static void DFS(int x, int y, int count) {	
		
		if(x >= N-1 && y >= N) {
			answer = Math.min(count, answer);
			return;
		} else if(y >= N) {
			DFS(x+1, 0, count);
			return;
		}
		
		if(answer <= count)		return;
		
		if(board[x][y] == 1) {
			for(int size = N/2; size > 0; size--) {
				if(counts[size] > 0 && canAttach(x, y, size)) {
					change_board(x, y, size, 0);
					counts[size]--;
					DFS(x, y+1, count+1);
					
					change_board(x, y, size, 1);
					counts[size]++;
				}
			}
		} else 	DFS(x, y+1, count);
	}
	
	private static boolean canAttach(int x, int y, int size) {
		for(int i = x; i < x+size; i++) {
			for(int j = y; j < y+size; j++) {
				if(i < 0 || i >= N || j < 0 || j >= N)	return false;
				if(board[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static void change_board(int x, int y, int size, int state) {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				board[i+x][j+y] = state;
			}
		}
	}
}