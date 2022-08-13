
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// https://www.acmicpc.net/problem/1062
/*
 알파벳 중 K개의 글자만 읽을 수 있을 때 주어진 단어들 중 읽을 수 있는 글자의 최댓값을 구하시오
 
 단, 모든 단어는 anta로 시작하고 tica로 끝난다.
 
 
 모든 단어에 a,c,i,n,t는 들어가므로 K-5개의 알파벳 조합 중 가장 많은 단어를 읽을 수 있는 조합을 찾아낸다.
 비트마스킹을 이용해서 조합을 구한다.

 시간 : 338ms
 */

public class BOJ_1062 {
	static int N,K,ans,flag;
	static String[] words;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ans = 0;
		words = new String[N];
		// 알파벳 개수가 26이므로 K가 26이면 모든 단어를 읽을 수 있다.
		if(K==26) ans = N;
		// anta, tica에 들어가는 문자가 5개이므로 k가 5보다 작으면 어떤 단어도 배울 수 없다.
		else if(K >= 5) { 
			for (int i = 0; i < N; i++) {
				words[i] = br.readLine();
			}
			// 모든 단어에 a,c,i,n,t가 들어가므로 미리 체크한다.
			flag |= 1<<('a'-'a');
			flag |= 1<<('c'-'a');
			flag |= 1<<('i'-'a');
			flag |= 1<<('n'-'a');
			flag |= 1<<('t'-'a');
			comb(0,0,flag);
		}
		System.out.println(ans);
	}
	private static void comb(int cnt, int start, int flag) {
		if(cnt == K-5) {
			int count = 0;
			for (int i = 0; i < N; i++) {
				boolean check = true; // 현재 조합으로 읽을 수 있는 단어인지 확인하는 변수
				
				// 단어의 글자 중 현재 조합에 포함되지 않는 단어가 하나라도 있다면 
				// 현재 조합으로는 읽을 수 없는 단어이므로 chcek를 false로 바꾸고 break한다.
				for (int j = 0; j < words[i].length(); j++) {
					if((flag & 1 << (words[i].charAt(j)-'a')) == 0) {
						check = false;
						break;
					}
				}
				if(check) count++;
			}
			ans = ans < count?count:ans;
			return;
		}
		for (int i = start; i < 26; i++) {
			if((flag & 1 << i) != 0) continue;
			comb(cnt+1, i+1, flag | 1<<i);
		}
	}

}
