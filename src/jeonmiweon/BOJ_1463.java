package temp;

import java.util.Scanner;

public class BOJ_1463 {

	public static void main(String[] args) {
		
		int array[] = new int[1000001];
		array[0] = array[1] = 0;
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();
		for (int i = 2; i <= number; i++) {
			array[i] = array[i-1] + 1;
			if(i % 3 == 0) {
				int temp = array[i/3] + 1;
				if(temp < array[i])	array[i] = temp;
			}
			if(i % 2 == 0) {
				int temp = array[i/2] + 1;
				if(temp < array[i])	array[i] = temp;
			}
		}
		System.out.println(array[number]);
	}
}
