
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// https://www.acmicpc.net/problem/17281
/*
 야구 게임 
 
 9명의 야구 선수 위치를 배치해서 가능한 가장 큰 득점을 구하는 문제
 단, 첫번째 타자의 위치는 4번으로 고정
 
 타자가 공을 쳐서 얻을 수 있는 결과는 안타, 2루타, 3루타, 홈런, 아웃

 안타: 타자와 모든 주자가 한 루씩 진루한다.
 2루타: 타자와 모든 주자가 두 루씩 진루한다.
 3루타: 타자와 모든 주자가 세 루씩 진루한다.
 홈런: 타자와 모든 주자가 홈까지 진루한다.
 아웃: 모든 주자는 진루하지 못하고, 공격 팀에 아웃이 하나 증가한다.
 
 시간 : 556ms
 */

public class BOJ_17281 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 첫번째 타자를 제외한 다른 타자들의 순서를 정할 배열
		int[] order = {1,2,3,4,5,6,7,8};
		int max = 0;
		// 이닝 별 타자들의 득점을 받을 배열
		char[][] person = new char[N][];
		for (int i = 0; i < N; i++) {
			person[i] = br.readLine().replace(" ","").toCharArray();
		}
		do {
			int score = 0;
			int start_taja = 0;
			for (int i = 0; i < N; i++) {
				int out = 0;
				int[] lu = {0,0,0};
				for (int j = start_taja; j < 9; j++) {
					int cur = 0;
					
					// 첫번째 타자의 위치가 4번으로 고정이기 때문에 3보다 작을 때, 클 때, 3일 때로 나눠서 값을 넣어준다.
					if(j<3) cur = person[i][order[j]]-'0';
					else if(j>3) cur = person[i][order[j-1]]-'0';
					else cur = person[i][0]-'0';
					
					switch(cur) {
					case 0: // 아웃
						out++;
						break;
					case 1: // 안타
						score += lu[2];
						lu[2] = lu[1];
						lu[1] = lu[0];
						lu[0] = 1;
						break;
					case 2: // 2루타
						score += lu[2] + lu[1];
						lu[2] = lu[0];
						lu[1] = 1;
						lu[0] = 0;
						break;
					case 3: // 3루타
						score += lu[2] + lu[1] + lu[0];
						lu[2] = 1;
						lu[1] = lu[0] = 0;
						break;
					default: // 홈런
						score += lu[2] + lu[1] + lu[0] + 1;
						lu[2] = lu[1] = lu[0] = 0;
					} 
					if(j==8) j = -1; // 끝까지 다 돌았으면 j값을 다시 초기화해준다.
					if(out == 3) {
						// 3 아웃이면 종료
						// 타순은 이닝이 변경되어도 유지되어야 하기 때문에 다음 이닝의 시작 타자를 현재 타자+1로 설정
						start_taja = j+1;
						break;	
					}
				}
			} max = max<score?score:max;
		} while(np(order));
		System.out.println(max);
	}
	
	private static boolean np(int[] orders) {
		int i = 7;
		while (i>0 && orders[i - 1] > orders[i]) {
			--i;
		}
		if(i==0) return false;
		int j = 7;
		while (orders[i - 1] >= orders[j]) --j;
		swap(orders, i - 1, j);
		int k = 7;
		while (i < k)
			swap(orders, i++, k--);
		return true;
		
	}
	
	private static void swap(int[] orders, int i, int j) {
		int temp = orders[i];
		orders[i] = orders[j];
		orders[j] = temp;
	}

}
