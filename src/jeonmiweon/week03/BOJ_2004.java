package week3;

import java.util.Scanner;

public class day4_boj_2004 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		long N = sc.nextInt();
		long M = sc.nextInt();
		
		int count_5 = Numof5(N) - Numof5(N-M) - Numof5(M);
		int count_2 = Numof2(N) - Numof2(N-M) - Numof2(M);
		System.out.println(Math.min(count_5, count_2));
	}

	private static int Numof5(long num) {
		int count = 0;
		while(num >= 5) {
			count += num/5;
			num /= 5;
		}
		return count;
	}
	
	private static int Numof2(long num) {
		int count = 0;
		while(num >= 2) {
			count += num/2;
			num /= 2;
		}
		return count;
	}
}
