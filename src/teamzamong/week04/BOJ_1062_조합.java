import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
	BOJ 1062: 가르침
	https://www.acmicpc.net/problem/1062
	걸린 시간: 336ms
	
	문제)
	"anta"로 시작하고 "tica"로 끝나는 단어 N개가 있다.
	K개의 글자를 알고 있다고 할 때, 읽을 수 있는 단어의 최대 개수를 구하기.
	
	풀이 방법) 조합을 사용해서 풀었음.
	1. 먼저, 단어의 시작과 끝에 나오는 글자인 "antic"은 알아야 함.
	   따라서 K < 5인 경우, 어떤 글자도 읽을 수 없기 때문에 0 리턴.
	2. "antic"을 제외한 K - 5개 글자에 대해 가능한 모든 글자 조합을 구한 후, 
	   각 글자 조합에 대해 읽을 수 있는 단어의 개수를 계산, 그 중 최댓값 리턴.
	
 */

public class BOJ_1062_조합 {

	public static String[] words; 		// 입력으로 받은 단어 저장 위한 배열
	public static boolean[] isVisited;  // 조합에서 사용한 알파벳 index 체크 위한 배열 
	public static List<Integer> ans;	// 가능한 정답값 저장 위한 리스트
	public static final String ANTIC = "antic"; // 단어 읽기 위해 무조건 알아야하는 알파벳

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// antic 다섯 글자를 모르면 아무 단어도 읽을 수 없음
		if (K < 5) {
			System.out.println(0);
			return;
		}

		// 변수 초기화
		words = new String[N]; 
		ans = new ArrayList<>();
		isVisited = new boolean[26];

		// antic 방문 처리
		for (int i = 0; i < ANTIC.length(); ++i) {
			isVisited[ANTIC.charAt(i) - 'a'] = true;
		}

		// 단어 입력 받기, antic에 해당하는 알파벳 제외
		for (int i = 0; i < N; ++i) {
			words[i] = br.readLine().trim().replaceAll("[" + ANTIC + "]", "");
		}

		getNCombK(K - 5, 1, 0); // antic에 해당하는 알파벳 제외 가능한 조합 계산
		System.out.println(Collections.max(ans));
	}

	// 알파벳 26개에 대해 nCk 구하기
	// N = 26
	private static void getNCombK(int K, int start, int depth) {
		if (depth == K) {
			ans.add(countReadable(words, isVisited));
			return;
		}

		for (int i = start; i < 26; ++i) {
			if (isVisited[i])
				continue;

			isVisited[i] = true;
			getNCombK(K, i + 1, depth + 1);
			isVisited[i] = false;
		}
	}

	// 가능한 알파벳 조합에 대해 읽을 수 있는 단어 개수 구하기
	private static int countReadable(String[] words, boolean[] isVisited) {
		int count = 0;
		boolean isReadable;

		for (String word : words) {
			isReadable = true;
			for (int i = 0, size = word.length(); i < size; ++i) {
				if (!isVisited[word.charAt(i) - 'a']) {
					isReadable = false;
					break;
				}
			}

			if (isReadable)
				++count;
		}

		return count;
	}
}
