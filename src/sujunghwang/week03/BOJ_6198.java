
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// https://www.acmicpc.net/problem/6198
// 스택을 사용하지 않은 풀이 - 스택을 사용한 희연이 풀이와 비교용
public class BOJ_6198 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] heights = new int[N];
		for (int i = 0; i < N; i++) {
			heights[i] = Integer.parseInt(br.readLine());
		}
		long ans = 0; // 빌딩 높이가 내림차순이면 1부터 79,999 까지 더해야 하므로 int 범위를 넘어간다.
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				if(heights[j] < heights[i]) ans++;
				else break;
			}
		}
		System.out.println(ans);
	}
}
