package week7;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_13549 {

	static int N, K;
	static boolean[] visited;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		if(K == 0) {
			System.out.println(N);
			return;
		}
		visited = new boolean[100001];
		bfs(N);
	}

	public static void bfs(int x) {
		Queue<Integer> queue = new LinkedList<>();
		for (int i = x; i < 100001; i += i) {
			if(i == K) {
				System.out.println(0);
				return;
			}
			if(i == 0)	{
				queue.add(0);
				visited[0] = true;
				break;
			}
			queue.add(i);
			visited[i] = true;
		}
		int time = 0;
		while (!queue.isEmpty()) {
			int queueSize = queue.size();
			for (int s = 0; s < queueSize; s++) {
				int pos = queue.poll();
				for (int i = pos-1; i < 100001; i += i) {
					if(i < 0)	break;
					if(i == 0)	{
						queue.add(0);
						visited[0] = true;
						break;
					}
					if(i == K) {
						System.out.println(time+1);
						return;
					}
					if(visited[i])	continue;
					queue.add(i);
					visited[i] = true;
				}
				for (int i = pos+1; i < 100001; i += i) {
					if(i == 0)	{
						queue.add(0);
						visited[0] = true;
						break;
					}
					if(i == K) {
						System.out.println(time+1);
						return;
					}
					if(visited[i])	continue;
					queue.add(i);
					visited[i] = true;
				}
			}
			time++;
		}
	}
}
