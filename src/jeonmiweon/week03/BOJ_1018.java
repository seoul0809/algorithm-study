package week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class day2_boj_1018 {
	
	static String[] answer1 = {"WBWBWBWB","BWBWBWBW","WBWBWBWB","BWBWBWBW",
						"WBWBWBWB","BWBWBWBW","WBWBWBWB","BWBWBWBW"};
	static String[] answer2 = {"BWBWBWBW","WBWBWBWB","BWBWBWBW","WBWBWBWB",
						"BWBWBWBW","WBWBWBWB","BWBWBWBW","WBWBWBWB"};
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int HEIGHT = Integer.parseInt(st.nextToken());
		int WIDTH = Integer.parseInt(st.nextToken());
		
		String[] board = new String[HEIGHT];
		for(int i = 0; i < HEIGHT; i++) {
			board[i] = br.readLine();
		}
		
		int min = 2501;
		for(int i = 0; i <= HEIGHT-8; i++) {
			for(int j = 0; j <= WIDTH-8; j++) {
				int sum1 = 0, sum2 = 0;
				for(int x = 0; x < 8; x++) {
					for(int y = 0; y < 8; y++) {
						if(board[x+i].charAt(y+j) != answer1[x].charAt(y)) {
							sum1++;
						}
					}
				} 
				if(sum1 < min)	min = sum1;
				
				for(int x = 0; x < 8; x++) {
					for(int y = 0; y < 8; y++) {
						if(board[x+i].charAt(y+j) != answer2[x].charAt(y)) {
							sum2++;
						}
					}
				}
				if(sum2 < min)	min = sum2;
			}
		}
		System.out.println(min);
	}
}
