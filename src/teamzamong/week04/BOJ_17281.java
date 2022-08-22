import java.io.*;
import java.util.*;

/* 
	BOJ 17281: 야구
	https://www.acmicpc.net/problem/17281
	시간: 880 ms
	
	문제)
	각 이닝당 n번째 타자의 성적이 주어졌을 때, 가장 많은 득점을 하는 타순을 찾고 그 때의 득점 구하기
	
	풀이)
	가능한 타순을 모두 구해 게임을 진행한 후, 최종 점수 중 최대값을 구함
	
 */

public class BOJ_17281 {

	static int N;
	static Player[] players;

	static class Player {
		int playerNo, currBase;
		int[] hits;
	
		public Player(int N, int playerNo) {
			this.playerNo = playerNo;
			hits = new int[N + 1];
			currBase = 0;
		}
	
		// 현재 이닝의 성적 가져오기
		public int getHit(int inning) {
			return hits[inning];
		}
	
		// 진루하기
		// 리턴값은 홈에 들어오면 1, 아니면 0
		public int moveBase(int base) {
			if (currBase + base > 3) {
				currBase = 0;
				return 1;
			}
	
			currBase += base;
			return 0;
		}
	}

	static class Game {
		int currInning, currScore, outCount;
		List<Player> bases;
	
		public Game() {
			currInning = 1;
			outCount = 0;
			currScore = 0;
			bases = new ArrayList<>();
		}
	
		// 새로운 타자가 들어왔을 때 게임 진행
		public void getNewPlayer(Player player, int inningNo) {
			int hit = player.getHit(inningNo);
	
			switch (hit) {
			case 0: // 아웃
				++outCount;
				break;
			case 4: // 홈런
				currScore += bases.size() + 1;
				clearBases();
				break;
			default:
				moveBases(player, hit);
			}
	
			if (outCount == 3) { // 다음 이닝으로 넘어가기
				++currInning;
				outCount = 0;
				clearBases();
			}
		}
	
		// 모든 베이스 비우기
		private void clearBases() {
			if (bases.isEmpty())
				return;
	
			for (Player player : bases) {
				player.currBase = 0;
			}
	
			bases.clear();
		}
	
		// 베이스의 모든 타자를 진루하는 메서드
		private void moveBases(Player curr, int hit) {			
			if (bases.isEmpty()) {
				bases.add(curr);
				curr.moveBase(hit);
				return;
			}
	
			// 베이스 상 타자들에 대해 처리 - 만약 홈인하면 점수 업데이트
			for (int i = bases.size() - 1; i >= 0; --i) {
				int score = bases.get(i).moveBase(hit);
				if (score > 0) {
					bases.remove(i);
					currScore += score;
				}
			}
	
			bases.add(curr);
			curr.moveBase(hit);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine().trim());
		players = new Player[10];

		for (int p = 1; p <= 9; ++p) {
			players[p] = new Player(N, p);
		}

		for (int n = 1; n <= N; ++n) {
			st = new StringTokenizer(br.readLine());
			for (int p = 1; p <= 9; ++p) {
				players[p].hits[n] = Integer.parseInt(st.nextToken());
			}
		}

		int maxScore = Integer.MIN_VALUE;
		int[] order = { 2, 3, 4, 5, 6, 7, 8, 9 }; // 1번 타자 제외

		Game game;
		int currInning, currHitOrder;
		do {
			game = new Game();
			currInning = game.currInning;
			currHitOrder = 0;

			while (currInning <= N) {
				if (currHitOrder < 3) {
					game.getNewPlayer(players[order[currHitOrder]], currInning);
				} else if (currHitOrder > 3) {
					game.getNewPlayer(players[order[currHitOrder - 1]], currInning);
				} else {
					game.getNewPlayer(players[1], currInning);
				}

				currHitOrder = currHitOrder == 8 ? 0 : currHitOrder + 1;
				currInning = game.currInning;
			}

			maxScore = Math.max(game.currScore, maxScore);

		} while (nextPermutation(order));

		System.out.println(maxScore);

	}

	private static boolean nextPermutation(int[] playerOrder) {
		int N = playerOrder.length;

		int i = N - 1;
		while (i > 0 && playerOrder[i] <= playerOrder[i - 1])
			--i;

		if (i == 0)
			return false;

		int j = N - 1;
		while (playerOrder[j] <= playerOrder[i - 1])
			--j;

		swap(playerOrder, j, i - 1);

		int k = N - 1;
		while (i < k) {
			swap(playerOrder, i++, k--);
		}

		return true;
	}

	private static void swap(int[] arr, int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
}
