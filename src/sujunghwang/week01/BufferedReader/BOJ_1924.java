package week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1924 {

	public static void main(String[] args) throws IOException{
		String[] day = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
		int[] month = {0,31,28,31,30,31,30,31,31,30,31,30,31};
		int dif = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		for (int i : Arrays.copyOfRange(month, 0, a)) {
			dif += i;
		}
		dif += b;
		System.out.println(day[dif%7]);
	}

}
