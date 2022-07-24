package week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11720 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sum=0;
		int N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		for (int i = 0; i < N; i++) {
			sum += s.charAt(i)-'0';
		}
		System.out.println(sum);	

	}

}
