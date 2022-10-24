import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/1080
/*
0과 1로만 이루어진 행렬 A와 행렬 B가 있다. 이때, 행렬 A를 행렬 B로 바꾸는데 필요한 연산의 횟수의 최솟값을 구하는 프로그램을 작성하시오.
행렬을 변환하는 연산은 어떤 3×3크기의 부분 행렬에 있는 모든 원소를 뒤집는 것이다. (0 → 1, 1 → 0)

3x3 크기로 바꿔나갈 때 왼쪽에서 오른쪽, 위에서 아래 순서로 바꾸면 가장 3x3 행렬의 가장 왼쪽 위는 다음 순서에도 바뀌지 않는 부분이 된다.
그러므로 이 부분이 다르면 바꾸고 같으면 바꾸지 않는 식으로 바꿔 나간다.

메모리 : 14212	시간 : 120
*/

public class BOJ_1080 {
	
	public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int ans = 0;
        char[][] arr1 = new char[N][];
        char[][] arr2 = new char[N][];
        for(int i = 0; i < N; i++){
        	arr1[i] = br.readLine().toCharArray();
        }
        for(int i = 0; i < N; i++){
        	arr2[i] = br.readLine().toCharArray();
        }
        if(N >= 3 && M >= 3){
            for(int i = 0; i < N-2;i++){
                for(int j = 0; j < M-2; j++){
                    if(arr1[i][j] != arr2[i][j]){
                        change(arr1,i,j);
                        ans++;
                    }
                }
            }
            
        } 
        boolean check = true;
        for (int i = 0; i < N; i++) {
        	if(!Arrays.equals(arr1[i], arr2[i])) {
        		check = false;
        		break;
        	}
        }
        if(!check) ans = -1;
        System.out.println(ans);
    }
	
	private static void change(char[][] arr, int r, int c) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				arr[r+i][c+j] = arr[r+i][c+j]=='0'?'1':'0';
			}
		}
	}
}
