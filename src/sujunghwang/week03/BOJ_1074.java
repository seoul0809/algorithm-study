import java.util.Scanner;
// https://www.acmicpc.net/problem/1074
public class BOJ_1074 {
	static int solution(int N,int r,int c) {
		int sum = 0;
		if (N == 1) { // N이 1일 때 = 2*2 배열일 때 >> 가장 작은 형태 
			// r == 0, c == 0 일 때는 0이므로 if문 생성 X
			if (r == 0 && c == 1) {
				return 1;
			} else if (r == 1 && c == 0) {
				return 2;
			} else if (r == 1 && c == 1){
				return 3;
			}
			return sum;
		}else { // 위에서와 마찬가지로 1사분면 일 때는 생략 
			// 2사분면 - 2^(n-1) * 2^(n-1)을 1번 더해줌
			if(r < Math.pow(2, (N-1)) && c >= Math.pow(2, (N-1))) { 
				sum += Math.pow(2, 2*(N-1)); // 2^(n-1) * 2^(n-1)
				c -= Math.pow(2, (N-1));
			}
			// 3사분면 - 2^(n-1) * 2^(n-1)을 2번 더해줌
			else if(r >= Math.pow(2, (N-1)) && c < Math.pow(2, (N-1))) {
				sum += 2*Math.pow(2, 2*(N-1));
				r -= Math.pow(2, (N-1));
			}
			// 4사분면 - 2^(n-1) * 2^(n-1)을 3번 더해줌
			else if(r >= Math.pow(2, (N-1)) && c >= Math.pow(2, (N-1))) {
				sum += 3*Math.pow(2, 2*(N-1));
				r -= Math.pow(2, (N-1));
				c -= Math.pow(2, (N-1));
			}
			return sum+solution(N-1, r,c);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); int r = sc.nextInt(); int c = sc.nextInt();
		System.out.println(solution(N,r,c));
	}

}
