import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 	BOJ 10610: 30
	https://www.acmicpc.net/problem/10610
	메모리/시간: 26116kb/240ms
	
	[문제]
	주어진 숫자 N이 주어졌을 때, 구성하는 숫자들로 30의 배수면서 가장 큰 숫자를 구하기
	
	[풀이]
    30의 배수려면
    - 10의 배수: 0으로 끝나야 함
    - 3의 배수: 모든 자리수의 합이 3의 배수여야 함
    그 중 제일 큰 숫자를 구하려면 숫자들을 내림차순으로 배치하면 된다.
    String으로 받아서 charAt으로 한 글자씩 가져오는 것보다 char[]로 만드는 것이 빠르다!
 */

public class BOJ_10610 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] arr = br.readLine().toCharArray();

		int[] nums = new int[10]; // 0 ~ 9의 숫자

		int n, val = 0;
		for (int i = 0, end = st.length(); i < end; ++i) {
			n = arr[i] - '0';
			++nums[n];
            val += n;
		}

		if (nums[0] == 0 || val % 3 != 0) { // 30의 배수려면 10의 배수, 3의 배수여야 함
			System.out.println(-1);
		} else {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			for (int i = 9; i >= 0; --i) {
				for (int j = 0, end = nums[i]; j < end; ++j) {
					bw.write(i + "");
				}
			}
			bw.flush();
			bw.close();
		}
	}

}
