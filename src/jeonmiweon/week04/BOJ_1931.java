package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1931 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		List<int[]> list = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int[] time = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			list.add(time);
		}
		
		list.sort(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] != o2[1])	return o1[1] - o2[1];
				else return o1[0] - o2[0];
			}
		});
		
		int end_time = 0;
		int count = 0;
		for(int i = 0; i < list.size(); i++) {
			int[] temp = list.get(i);
			if(end_time <= temp[0]) {
				count++;
				end_time = temp[1];
//				System.out.println(temp[0] + " " + temp[1]);
			}
		}
		System.out.println(count);
//		for(int i = 0; i < list.size(); i++) 
//			System.out.println(Arrays.toString(list.get(i)));
	}
}