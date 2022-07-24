import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ_2609: 최대공약수와 최대공배수
 * 0 < A, B <= 10,000에 대해 최대공약수와 최대공배수 구하기
 * 
 * 아래 공식은 외우면 좋다고 함!!
 * 최대공약수(GCD): A % B = r 이라 할 때, GCD(A, B) = GCD(B, r)
 * 최소공배수(LCM): A * B = GCD(A, B) * LCM(A, B)
 */

public class BOJ_2609 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력 받기
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		// 최대공약수 구하기
		int gcd = GCD(A, B);
		System.out.println(gcd);

		// 최대공배수 구하기
		// A, B <= 10000이기 때문에 곱해도 overflow 발생하지 않음
		System.out.println(A * B / gcd);

	}

	public static int GCD(int a, int b) {
		if (b == 0)
			return a;
		return GCD(b, a % b);
	}

}
