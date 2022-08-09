
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// https://www.acmicpc.net/problem/1931
/* 
 문제 번호 : 1931 
 문제 이름 : 회의실 배정 
 걸린 시간 : 552ms
 
 문제)
 한 개의 회의실이 있는데 이를 사용하고자 하는 N개의 회의에 대하여 회의실 사용표를 만들려고 한다. 
 
 각 회의 I에 대해 시작시간과 끝나는 시간이 주어져 있고, 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수를 찾아보자.
  
 단, 회의는 한번 시작하면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다. 
 
 회의의 시작시간과 끝나는 시간이 같을 수도 있다. 이 경우에는 시작하자마자 끝나는 것으로 생각하면 된다.
 
 
 풀이 방법)
 한 회의가 끝나야 다음 회의를 시작할 수 있기 때문에 끝나는 시간을 기준으로 오름차순 정렬한다.
 단, 끝나는 시간이 같을 경우 시작 시간을 기준으로 오름차순 정렬한다.
 
 그런 다음 끝나는 시간이 가장 빠른 회의의 끝나는 시간을 end_time에 저장하고 
 반복을 돌려서 end_time보다 시작 시간이 크거나 같은 회의를 찾는다면 ans에 1을 더하고
 그 회의의 끝나는 시간을 end_time으로 지정한다. 
 
 이렇게 하면 끝나는 시간이 짧은 회의들을 고르는 것이므로 가장 많은 회의를 고를 수 있다.
 */
public class BOJ_1931 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] meetings = new int[N][2];
		int end_time = 0;
		int ans = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());	
			meetings[i] = new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
		}
		Arrays.sort(meetings, (a,b)->a[1]!=b[1]?a[1]-b[1]:a[0]-b[0]);
		for (int i = 0; i < N; i++) {
			if(end_time <= meetings[i][0]) {
				end_time = meetings[i][1];
				ans++;
			}
		}
		System.out.println(ans);
	}

}
