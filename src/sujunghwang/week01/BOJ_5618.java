package week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//자연수 n개가 주어진다. 이 자연수의 공약수를 모두 구하는 프로그램을 작성하시오.
public class BOJ_5618 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N]; // n개의 자연수를 입력받을 배열
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); // 배열에 입력값 저장
		}
		Arrays.sort(arr); // 최소값을 쉽게 찾기 위해 정렬
		for (int i = 1; i < arr[0]+1; i++) { // 공약수의 가장 큰 값은 입력된 값들의 가장 작은 값보다 작거나 같다.
			boolean check = true; // 모든 자연수가 나누어 떨어지는지 확인
			for (int j = 0; j < N; j++) {
				if (arr[j] % i != 0) check = false;
			}
			if (check) System.out.println(i);
		}
	}

}
