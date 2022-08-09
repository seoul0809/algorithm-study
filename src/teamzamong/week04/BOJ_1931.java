import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 
	BOJ 1931: 회의실 배정
	시간: 568ms
	
	그리디 알고리즘을 사용해서 푸는 문제.
	제일 많은 회의를 배정하기 위해서는 현재 제일 빨리 끝나는 회의를 배정해야 함.
	
	들어온 회의를 정렬하기 위해서 Meeting이라는 클래스에 Comparable를 구현함.
	빨리 끝날수록 더 앞에 있고, 만약 끝나는 시간이 같다면 일찍 시작하는 회의가 우선.
*/

public class BOJ_1931 {

	public static class Meeting implements Comparable<Meeting> {
		int start, end;

		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public int compareTo(Meeting m) {
			if (Integer.compare(end, m.end) == 0)
				return Integer.compare(start, m.start);
			return Integer.compare(end, m.end);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Meeting[] list = new Meeting[N];

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			list[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(list);

		System.out.println(getMaxMeeting(list));
	}

	private static int getMaxMeeting(Meeting[] list) {
		int count = 1;
		int end = list[0].end;

		for (int i = 1, N = list.length; i < N; ++i) {
			if (list[i].start < end)
				continue;

			end = list[i].end;
			++count;
		}

		return count;
	}
}
