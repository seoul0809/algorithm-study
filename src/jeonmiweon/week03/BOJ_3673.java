package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class day5_boj_3673 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long[] mods;
		int[] numbers;
		
		int TEST_CASE = Integer.parseInt(st.nextToken());
		for(int i = 1; i <= TEST_CASE; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			numbers = new int[len];
			int count = 0;
			for(int j = 0; j < len; j++) {
				numbers[count++] = Integer.parseInt(st.nextToken());
			}
			
			int sum = 0;
			mods = new long[d];
			mods[0]++;
			
			for(int j = 0; j < len; j++) {
				sum = (sum + numbers[j]) % d;
				mods[sum]++;
			}
			
			long answer = 0;
			for(int k = 0; k < d; k++) {
				answer += (mods[k] * (mods[k]-1) / 2); 
			}
			System.out.println(answer);
		}
	}

}
