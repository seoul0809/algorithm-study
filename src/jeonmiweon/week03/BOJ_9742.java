package week3;

import java.util.Scanner;

public class day3_boj_9742 {

	static int totalCount, target_num;
	static String answer;
	static char[] chars;
	static boolean[] isSelected;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			
			String str = sc.next();
			target_num = sc.nextInt();
			
			totalCount = 0;
			chars = new char[str.length()];
			isSelected = new boolean[str.length()];
			
			perm(str, 0);
			
			if(totalCount < target_num)	answer = "No permutation";
			System.out.printf("%s %d = %s\n", str, target_num, answer);
		}
	}

	private static void perm(String str, int count) {
		
		if(count == str.length()) {
			totalCount++;
			if(totalCount == target_num) {
				answer = new String(chars);
			}
			return;
		}
		
		for (int i = 0; i < str.length(); i++) {
			
			if(isSelected[i])	continue;
			isSelected[i] = true;
			chars[count] = str.charAt(i);
			perm(str, count+1);
			isSelected[i] = false;
		}
	}
}
