import java.util.Scanner;

/* 
	BOJ 1436: 영화 감독 숌
	https://www.acmicpc.net/problem/1436
	
	완전 탐색으로 풀면 되는 문제.
	최대로 구해야하는 N번째 영화가 1만번째 영화기 때문에 정말 최악의 경우라도 666만까지 가면 됨.
	숫자를 하나씩 증가하며 확인해본다고 하더라도 연산 개수가 1억개 이하기 때문에 완전 탐색으로 가능하다.
	
	처음으로 6이 3개 이상 연속으로 들어가는 수인 666부터 시작해서 하나씩 증가하며 탐색하면 됨. 
	
	시간: 388ms
*/

public class BOJ_1436 {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		System.out.println(calcMovieTitle(N));
		in.close();
	}
	
	private static int calcMovieTitle(int N) {
		int n = 0;
		int curr = 665;
		
		while (n < N) {
			++curr;
			if (Integer.toString(curr).contains("666")) {
				++n;
			}
		}
		
		return curr; 
	}
}
