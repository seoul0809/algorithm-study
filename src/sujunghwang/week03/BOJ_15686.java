import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// https://www.acmicpc.net/problem/15686
/*
 NxN 크기의 도시 지도가 주어집니다.
 
 지도에서 1은 집, 2는 치킨집을 의미합니다.
 
 최대 M개의 치킨집만 남긴다고 할 때 집과 치킨집과의 거리의 최소값을 구해주세요.
 */
public class BOJ_15686 {

	static int N; static int M;
	static List<Integer[]> house; // 집들의 좌표를 담을 리스트
	static List<Integer[]> chicken; // 치킨집들의 좌표를 담을 리스트
	static List<Integer[]> remain; // 살아남은 치킨집들의 좌표를 담을 리스트
	static int sum_house; 
	static int sum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		house = new ArrayList<>();
		chicken = new ArrayList<>();
		remain = new ArrayList<>();
		
		sum = Integer.MAX_VALUE;
		
		// 전체 지도에서 집과 치킨집의 좌표만 저장한다.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 1) { // 입력값이 1이면 집이므로 house에 좌표 저장
					Integer[] a = {i,j};
					house.add(a);
				}else if(num == 2) { // 입력값이 2면 치킨집이므로 chicken에 좌표 저장
					Integer[] a = {i,j};
					chicken.add(a);
				}
			}
		}
		select(0,0);
		System.out.println(sum);
		
	}
	
	// M개의 치킨집을 고를 메소드
	private static void select(int cnt, int start) {
		if(cnt == M) { 
			calc();
			if(sum > sum_house) sum = sum_house;
			return;
		}
		for (int i = start; i < chicken.size(); i++) {
			remain.add(chicken.get(i));
			select(cnt+1, i+1);
			remain.remove(chicken.get(i));
		}
		
	}
	
	// 치킨집과 집들간의 거리를 계산할 메소드
	private static void calc(){
		int min = 0;
		sum_house = 0;
		for (int i = 0; i < house.size(); i++) {
			min = Integer.MAX_VALUE;
			for (int j = 0; j < remain.size(); j++) {
				int a = Math.abs(house.get(i)[0]-remain.get(j)[0]);
				int b = Math.abs(house.get(i)[1]-remain.get(j)[1]);
				if(a + b < min) {
					min = a+b;
				}
			}
			sum_house += min;
		}
	}

}
