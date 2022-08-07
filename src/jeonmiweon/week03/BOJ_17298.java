package week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class day6_boj_17298 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < N; i++) {
			stack.add(Integer.parseInt(st.nextToken()));
		}
		
		int[] result = new int[N];
		result[N-1] = -1;
		int NGE = stack.pop();
		
		for(int i = N-2; i >= 0; i--) {
			int number = stack.pop();
			if(number <= NGE)	result[i] = NGE;
			else {
				result[i] = -1;
				NGE = number;
			}
			if(!stack.empty() && number > stack.peek()) {
				NGE = number;
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) 
			sb.append(result[i]).append(" ");
		System.out.println(sb);
	}
}
