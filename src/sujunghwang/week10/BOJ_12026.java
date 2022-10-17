import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
// https://www.acmicpc.net/problem/12026
/*
 길이가 N인 보도블록이 있다. 각 보도블록에는 B, O, J 중에 하나가 쓰여 있다. 1번은 반드시 B이다.
 항상 번호가 증가하는 방향으로 점프를 해야 하고 B, O, J, B, O, J, ... 순서로 보도블록을 밟을 수 있다.
 한 번 k칸 만큼 점프를 하는데 필요한 에너지의 양은 k*k일 때 보도블록의 처음에서 끝까지 갈 대 필요한 에너지 양의 최솟값을 구하시오
 
 i번째 보도블록까지 가는데 필요한 최소 에너지의 양을 memo 배열에 저장하는 dp로 풀이
 
 메모리 : 14252, 시간 : 136
 
 특이사항..? 
 char[] 가 아닌 그냥 String으로 풀면 메모리 : 14300, 시간 : 140으로 메모리랑 시간이 좀 더 들어가는 걸 볼 수 있었음
 */
public class BOJ_12026 {

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String road = br.readLine();
		char[] road_arr = road.toCharArray();
		
		int[] memo = new int[N];
		
		Arrays.fill(memo, Integer.MAX_VALUE);
		memo[0] = 0;
		
		for (int i = 1; i < N; i++) {
			char pre = getChar(road.charAt(i));
			for (int j = 0; j < i; j++) {
				if(road_arr[j] == pre && memo[j] != Integer.MAX_VALUE) {
					memo[i] = Math.min(memo[i], memo[j]+(i-j)*(i-j));
				}
			}
		}
		System.out.println(memo[N-1]==Integer.MAX_VALUE?-1:memo[N-1]);

	}
	
	private static char getChar(char c) {
		switch (c){
			case 'B': {
				return 'J';
			}
			case 'O': {
				return 'B';
			}
		}
		return 'O';
	}
}
