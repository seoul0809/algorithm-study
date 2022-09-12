import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// https://www.acmicpc.net/problem/13549
/*
 현재 위치가 X이고 1초 동안 X + 1, X - 1로 이동하거나 0초 동안 X * 2로 순간이동 할 수 있을 때
 위치 K로 이동하는 가장 빠른 시간 구하기
 
 최단 거리를 찾는 문제이므로 bfs 탐색을 이용
 
 메모리 : 90188KB, 시간 : 352ms
 */

public class BOJ_13549 {

	static int N,K, ans = Integer.MAX_VALUE, MAX = 100001;
	static boolean[] visited;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		visited = new boolean[MAX];
		bfs();
		System.out.println(ans);
	}
	
	private static void bfs() {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {N,0});
		
		while (!que.isEmpty()) {
			
			int[] curArr = que.poll();
			int cur_v = curArr[0];
			int cur_w = curArr[1];
			visited[cur_v] = true;
			
            if(cur_v == K) ans = Math.min(ans, cur_w);
			
            // 현재 위치 * 2로 순간이동
			if(cur_v*2 < MAX && !visited[cur_v * 2]) 
				que.offer(new int[] {cur_v*2, cur_w});
			
			// 현재 위치 + 1로 1초 동안 이동
			if(cur_v+1 < MAX && !visited[cur_v + 1]) 
				que.offer(new int[] {cur_v+1, cur_w+1});
			
			// 현재 위치 + 1로 1초 동안 이동
			if(cur_v-1 >= 0 && !visited[cur_v - 1]) 
				que.offer(new int[] {cur_v-1, cur_w+1});
		}
	}

}
