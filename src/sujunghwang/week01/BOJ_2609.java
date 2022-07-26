package week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//두 개의 자연수를 입력받아 최대 공약수와 최소 공배수를 출력하는 프로그램을 작성하시오.
public class BOJ_2609 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
		System.out.println(gcd(a,b));
		System.out.println(lcm(a,b));
	}
	
	public static int gcd(int a, int b) { // 유클리드 호제법을 이용한 최대공약수 구하기 > a,b의 최대공약수는 b,c의 최대공약수와 같다.
		while(b!=0) {
			int temp = a%b;
			a = b;
			b = temp;
		}
		return a;
	}
	
	public static int lcm(int a, int b) { // 위에서 구한 최대 공약수에 a와 b를 곱하면 최소공배수가 된다.
		return a*b / gcd(a,b);
	}

}
