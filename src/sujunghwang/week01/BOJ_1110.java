package week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
0보다 크거나 같고, 99보다 작거나 같은 정수가 주어질 때 다음과 같은 연산을 할 수 있다. 먼저 주어진 수가 10보다 작다면 앞에 0을 붙여 두 자리 수로 만들고, 각 자리의 숫자를 더한다. 

그 다음, 주어진 수의 가장 오른쪽 자리 수와 앞에서 구한 합의 가장 오른쪽 자리 수를 이어 붙이면 새로운 수를 만들 수 있다. 다음 예를 보자.

26부터 시작한다. 2+6 = 8이다. 새로운 수는 68이다. 6+8 = 14이다. 새로운 수는 84이다. 8+4 = 12이다. 새로운 수는 42이다. 4+2 = 6이다. 새로운 수는 26이다.

위의 예는 4번만에 원래 수로 돌아올 수 있다. 따라서 26의 사이클의 길이는 4이다.

N이 주어졌을 때, N의 사이클의 길이를 구하는 프로그램을 작성하시오.
 */
public class BOJ_1110 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String num = br.readLine();
		int sum = 0;
		int ans = 0;
		String temp = "";
		String new_num = num; // 새로운 수를 저장할 변수
		while (true) {
			sum = 0;
			if (temp.equals(num)) break; // 
			for (int i = 0; i < new_num.length(); i++) {
				sum += new_num.charAt(i) - '0'; // 각 자릿수의 합을 저장
			}
			temp = new_num.charAt(new_num.length()-1) + Integer.toString(sum % 10); // 주어진 수의 가장 오른쪽 자리 수와 합의 가장 오쪽 자리 수를 이어 붙임
			temp = Integer.toString(Integer.parseInt(temp)); // 맨 앞 자리가 0이면 num과 다른 값이라고 나와기 때문에 0을 제거하기 위해 String > int > String 로 변환
			new_num = temp; // 새로운 수에 저장
			ans++;
		}
		System.out.println(ans);
	}

}
