package week3;

import java.util.Scanner;
// 하노이의 탑
public class boj_1914 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		System.out.println((int)Math.pow(2, num) - 1);
		if(num > 20)	return;
		
		hanoi(num, 1, 3, 2);
	}

	public static void hanoi(int num, int depart, int arrive, int temp) {
		if(num == 0)	return;
		
		hanoi(num-1, depart, temp, arrive);
		System.out.println(depart + " " + arrive);
		hanoi(num-1, temp, arrive, depart);
	}
}
