package week04;

import java.io.*;
import java.util.*;

/*
	BOJ 16236: 아기 상어
	https://www.acmicpc.net/problem/16236
	걸린 시간: 144ms
	
	문제)
	N * N 공간에 물고기 M 마리와 아기 상어 1마리가 존재한다고 하자.
	처음에 아기 상어의 크기는 2이고, 아기 상어는 1초에 상하좌우 인접한 한 칸씩 이동한다.
	- 자기 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없다.
	- 자기 자신의 크기보다 작은 물고기만 먹을 수 있다. 자신의 크기와 같은 수의 물고기를 먹을 때마다 크기가 1 증가한다.
	- 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
		- 거리는 지나가야하는 칸 개수의 최솟값
		- 거리가 가까운 물고기가 많다면, 그 중 가장 위에 있는 것. 그런 물고기가 여러 마리라면, 가장 왼쪽에 있는 것 먹음
		
	풀이)
	먹을 수 있는 물고기가 없을 때까지, 먹을 수 있는 물고기까지의 최단 거리를 계산하면 되는 문제.
	최단 거리에 있는 물고기를 구하는 건 BFS를 활용하면 돼서 까다롭지 않았지만, 
	BFS를 사용한 후, 최단 거리에 있는 물고기 중에서 조건에 맞는 물고기를 찾아서 후처리해줘야하는 부분이 주의해야할 부분.
 */

public class Main_16236_아기상어 {

	public static final int[] dr = { -1, 0, 0, 1 };
	public static final int[] dc = { 0, -1, 1, 0 };
	public static int N;
	public static int[][] map, distance; // 실제 map, 거리 관리용 2차원 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine().trim());
		map = new int[N][N];
		distance = new int[N][N];

		Shark shark = null;
		for (int r = 0; r < N; ++r) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; ++c) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 9) {
					shark = new Shark(r, c);
				}
			}
		}

		int totalTime = 0;
		int roundTime = 0;

		while (true) {
			roundTime = findFish(shark);
			if (roundTime < 0) { // 더 이상 먹을 수 없는 생선이 없는 경우
				break;
			}

			totalTime += roundTime;
		}

		System.out.println(totalTime);

	} // end main

	// 가까운 거리의 생선을 찾기 위한 메서드
	// BFS를 활용해 구현함
	private static int findFish(Shark shark) {
		int r = shark.getR();
		int c = shark.getC();
		int minDist = Integer.MAX_VALUE; // 생선까지 최소 거리

		List<Fish> list = new ArrayList<>(); // 같은 거리의 생선들 비교 위한 리스트
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { r, c });

		distance = new int[N][N];
		distance[r][c] = 1;

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			r = curr[0];
			c = curr[1];
			
			if (distance[r][c] > minDist) // 생선까지 최소 거리 이상으로 탐색 X
				break;

			for (int i = 0; i < 4; ++i) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				// 범위 벗어나거나, 방문한 칸이거나, 현재 상어 크기보다 큰 생선이라 방문할 수 없는 경우
				if (!checkRange(nr, nc) || distance[nr][nc] != 0 || map[nr][nc] > shark.getSize())
					continue;

				distance[nr][nc] = distance[r][c] + 1;

				// 먹을 수 있는 생선인 경우, 리스트에 더하고 최소 거리 업데이트
				if (map[nr][nc] >= 1 && map[nr][nc] < shark.getSize()) {
					list.add(new Fish(nr, nc, distance[nr][nc]));
					minDist = Math.min(minDist, distance[nr][nc]);
				}

				queue.offer(new int[] { nr, nc });
			}
		}

		Collections.sort(list); // 생선 리스트를 거리 기준에 따라 정렬
		if (list.size() != 0) {
			Fish fish = list.get(0);
			shark.eat(fish);
			return fish.getDistance() - 1;
		}

		return -1; // 먹을 수 있는 생선이 없는 경우
	}

	private static boolean checkRange(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= N)
			return false;
		return true;
	}

	
	// 상어 구현 위한 클래스
	public static class Shark {
		private int r, c; // 좌표 r, c
		private int size, eatenFish; // 크기 size, 크기 커지기 전까지 먹은 생선 수 eatenFish 

		public Shark(int r, int c) {
			this.r = r;
			this.c = c;
			size = 2;
			eatenFish = 0;
		}

		public int getR() {
			return r;
		}

		public int getC() {
			return c;
		}

		public int getSize() {
			return size;
		}

		// 생선 먹은 후, 좌표 및 먹은 생선 수, 크기 업데이트
		public void eat(Fish fish) {
			map[r][c] = 0;
			map[fish.getR()][fish.getC()] = 9;
			r = fish.getR();
			c = fish.getC();
			++eatenFish;

			if (eatenFish == size) {
				++size;
				eatenFish = 0;
			}
		}
	}

	// 생선 구현 위한 클래스
	// 거리가 같은 경우, 어떤 생선을 선택할지 결정하려고 Comparable 구현
	public static class Fish implements Comparable<Fish> {
		private int r, c, distance;

		public Fish(int r, int c, int distance) {
			this.r = r;
			this.c = c;
			this.distance = distance;
		}

		public int getR() {
			return r;
		}

		public int getC() {
			return c;
		}

		public int getDistance() {
			return distance;
		}

		@Override
		public int compareTo(Fish o) {
			if (Integer.compare(distance, o.distance) == 0) {
				if (Integer.compare(r, o.r) == 0)
					return Integer.compare(c, o.c);
				return Integer.compare(r, o.r);
			}
			return Integer.compare(distance, o.distance);
		}
	}
}
