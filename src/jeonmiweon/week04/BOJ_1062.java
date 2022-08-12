package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1062 {

	static int N, num, max;
	static HashSet<Character>[] sets;
	static HashSet<Character> set_all;
	static String[] words;
	static Character[] char_all;
	static char[] selected_char;
	static List<Character> needed;
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		num = Integer.parseInt(st.nextToken());
		if(num < 5)	{
			System.out.println(0);
			return;
		}
		if(num == 26) {
			System.out.println(N);
			return;
		}
		
		sets = new HashSet[N];
		for(int i = 0; i < N; i++)	sets[i] = new HashSet<>();	
		set_all = new HashSet<>();
		words = new String[N];
		
		needed = new ArrayList<>();
		needed.add('a'); needed.add('n'); needed.add('t'); needed.add('i'); needed.add('c');
		
		for(int i = 0 ; i < N; i++) {
			words[i] = br.readLine();
			words[i] = words[i].replace("anta", "");
			words[i] = words[i].replace("tica", "");
			System.out.println(words[i]);
			for(int j = 0; j < words[i].length(); j++) {
				char c = words[i].charAt(j);
				if(needed.contains(c) == false) {
					sets[i].add(c);
					set_all.add(c);
				}
			}
			Character t[] = sets[i].toArray(new Character[0]);
			System.out.println(Arrays.toString(t));
		}
		
		char_all = set_all.toArray(new Character[0]);
		selected_char = new char[num];
		
		if(char_all.length < num-5) {
            System.out.println(N);
            return;
        }
		select_char(0, 0);
		
		System.out.println(max);
	}
	
	private static void select_char(int count, int start) {
		if(count == num-5) {
			System.out.println("select: " + Arrays.toString(selected_char));
			int result = 0;
			for(int i = 0; i < N; i++) {
				HashSet<Character> temp = new HashSet<>();
				temp.addAll(sets[i]);

				for(int j = 0; j < num; j++) {
					temp.remove(selected_char[j]);
				}
				if(temp.isEmpty())	result++;
			}
			if(result > max)	max = result;
			return;
		}
		
		for(int i = start; i < char_all.length; i++) {
			selected_char[count] = char_all[i];
			select_char(count+1, i+1);
		}
	}
}