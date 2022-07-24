package week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2441 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		for (int i = N; i > 0; i--) {
			sb.append(" ".repeat(5-i)).append("*".repeat(i)).append("\n");
		}
		System.out.println(sb);
		
	}

}
