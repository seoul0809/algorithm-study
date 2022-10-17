import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11265
/*
 A에서 B로 갈 때 시간내에 도착할 수 있는지 없는지 구하기
 
 플로이드-와샬 알고리즘(모든 정점에서 모든 정점으로의 최단거리를 구하는 알고리즘) 사용
 
 StringBuilder 사용했을 때 >> 메모리 : 27388KB, 시간 : 464ms
 BufferedWriter 사용했을 때 >> 메모리 : 25448KB, 시간 : 448ms
 */

public class BOJ_11265 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int size = Integer.parseInt(st.nextToken());
		int customers = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[size+1][size+1];
		for (int i = 1; i <= size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// i에서 k를 지나 j로 가는 시간이 기존 시간보다 더 짧으면 값 바꾸기
		for (int k = 1; k <= size; k++) {
			for (int i = 1; i <= size; i++) {
				for (int j = 1; j <= size; j++) {
					if(map[i][j] > (map[i][k]+map[k][j])) {
						map[i][j] = map[i][k]+map[k][j];
					}
				}
			}
		}
		
		for (int i = 0; i < customers; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			if(map[from][to] <= time) bw.write("Enjoy other party\n");
			else bw.write("Stay here\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
