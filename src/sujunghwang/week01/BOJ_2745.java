package week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
B진법 수 N이 주어진다. 이 수를 10진법으로 바꿔 출력하는 프로그램을 작성하시오.

10진법을 넘어가는 진법은 숫자로 표시할 수 없는 자리가 있다. 이런 경우에는 다음과 같이 알파벳 대문자를 사용한다.

A: 10, B: 11, ..., F: 15, ..., Y: 34, Z: 35

입력
첫째 줄에 N과 B가 주어진다. (2 ≤ B ≤ 36)

 * */
public class BOJ_2745 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		char[] arr = st.nextToken().toCharArray(); 
		int b = Integer.parseInt(st.nextToken());
		int alpha = 0;
		int ans = 0;
		for (int i = arr.length-1; i >= 0; i--) { // 마지막 자리부터 계산
			if ('A'<=arr[i] && arr[i]<='Z') alpha = arr[i] - 55; // 만약 값이 A-Z 사이에 있으면 10-35 사이의 값으로 변환
			else alpha = arr[i] - '0'; 							 // 그렇지 않으면 숫자이므로 '0'을 빼서 숫자로 변환 
			ans += alpha * Math.pow(b, (arr.length-1-i));		 // 변환한 값에 b의 자리값만큼 제곱한 수를 곰해서 더함
		}
		System.out.println(ans);
	}

}
