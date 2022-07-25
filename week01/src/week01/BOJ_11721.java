package week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11721 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		if (s.length() % 10 == 0) {
			for (int i = 0; i < s.length() / 10; i++) {
				System.out.println(s.substring(i*10, (i+1)*10));
			}
		}else {
			for (int i = 0; i < s.length() / 10 ; i++) {
				System.out.println(s.substring(i*10, (i+1)*10));
			}
			System.out.println(s.substring(s.length() / 10 * 10));
		}
	}

}
