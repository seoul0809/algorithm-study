package week11;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_10610 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		Character[] num = new Character[str.length()];
		int sum = 0;
		for (int i = 0; i < num.length; i++) {
			num[i] = str.charAt(i);
			sum += num[i]-'0';
		}
		Arrays.sort(num, (o1, o2) -> o2-o1);
		if (sum % 3 == 0 && num[num.length-1] == '0') {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < num.length; i++)
				sb.append(num[i]);
			System.out.println(sb);
		}
		else	System.out.println(-1);
		sc.close();
	}
}
