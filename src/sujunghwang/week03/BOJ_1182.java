
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//https://www.acmicpc.net/problem/1182
/*
 N개의 정수로 이루어진 수열이 있을 때,
 
 크기가 양수인 부분수열 중에서 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램을 작성하시오.
 */
public class BOJ_1182 {
	static int N;
	static int S;
	static int[] nums; // 
	static boolean[] isSelected;
	static int sum;
	static int totalCnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		S = Integer.parseInt(st.nextToken()); 
		nums = new int[N];
		isSelected = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		subset(0);
		
		// 아무것도 선택하지 않을 때도 합이 0이므로 그 경우를 빼준다
		System.out.println(S==0?totalCnt-1:totalCnt); 
	}
	
	private static void subset(int index) {
		if(index == N) {
			sum = 0;
			for (int i = 0; i < N; i++) {
				if(isSelected[i]) {
					sum += nums[i]; // 부분집합으로 선택된 값들의 합을 구함
				}
			}
			if(sum == S) { // 부분집합의 합이 S와 같다면 totalCnt++
				totalCnt++;
			}
			return;
		}
		
		isSelected[index] = true;
		subset(index+1);
		
		isSelected[index] = false;
		subset(index+1);
		
	}

}
