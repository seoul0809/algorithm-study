import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
	BOJ 14501: 퇴사
	https://www.acmicpc.net/problem/14501
	걸린 시간: 132ms
	
	문제)
	N일 동안의 상담 일정이 주어졌을 때, 얻을 수 있는 최대 수익 구하기.
	상담은 (상담을 완료하는데 걸리는 시간, 상담을 했을 때 받을 수 있는 금액)으로 이루어져 있음.
	N + 1은 퇴사 날짜라 이전에 모든 상담을 완료해야 함.
	
	풀이)
	상담마다 금액이 다르기 때문에, 그리디한 방법으로 풀 수 없고 조합으로 풀어야 함.
	상담 날짜를 하루씩 진행하면서 상담을 선택하거나 선택하지 않는 방식으로 풀이
	1. 종료 조건
	   - 상담 종료 날짜가 N + 1 이후일 때
	   - 마지막날까지 근무 완료한 경우
	2. 진행 조건
	   2.1. 현재 날짜에 일이 끝나지 않은 경우: 상담 선택 X, 다음 날로 넘어감
	   2.2. 현재 날짜에 새로운 일을 시작할 수 있는 경우:
	        - 현재 날짜 상담 선택: 상담 종료 날짜와 금액 업데이트
	        - 현재 날짜 상담 선택 X: 
	          - 상담 종료 날짜를 오늘로 업데이트해 다음 날 일할 수 있도록 함
	          - 금액 업데이트 X
	          
	수업에서 풀었던 SWEA의 햄버거 문제와 비슷하지만, 따져줘야하는 조건이 복잡해서 까다로웠음.  
 */

public class BOJ_14501 {

	static int N, maxProfit; // 퇴사 날짜, 최대 수익
	static Meeting[] meetings; // 상담 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine().trim());
		maxProfit = Integer.MIN_VALUE;
		meetings = new Meeting[N + 1];
		
		// 입력 처리
		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());
			meetings[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		calcMaxProfit(1, 0, 0);

		System.out.println(maxProfit);

	}

	// 날짜 조건에 맞는 모든 가능한 상담 조합 구해 최대 수익 계산
	// currDay : 오늘 날짜
	// endDay : 일이 끝나는 날. 그 다음 날부터 일할 수 있음
	private static void calcMaxProfit(int currDay, int endDay, int totalProfit) {
		if (endDay > N) // 끝나는 날이 마지막 근무일 이후면 X
			return;

		if (currDay == N + 1) { // 마지막 날까지 근무 완료한 경우
			maxProfit = Math.max(maxProfit, totalProfit);
			return;
		}

		if (endDay >= currDay) { // 일이 아직 끝나지 않은 경우
			calcMaxProfit(currDay + 1, endDay, totalProfit);
		} else {
			// 해당 날짜의 일 수행
			calcMaxProfit(currDay + 1, endDay + meetings[currDay].getDays(),
					totalProfit + meetings[currDay].getPrice());
			
			// 해당 날짜의 일 수행 X, 다음날로 넘어가기
			calcMaxProfit(currDay + 1, currDay, totalProfit);
		}
	}
	
	public static class Meeting {
		private final int days, price;

		public Meeting(int days, int price) {
			this.days = days;
			this.price = price;
		}

		public int getDays() {
			return days;
		}

		public int getPrice() {
			return price;
		}
	}

}
