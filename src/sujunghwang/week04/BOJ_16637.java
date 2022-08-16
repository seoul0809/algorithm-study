
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
// https://www.acmicpc.net/problem/16637
/*
 길이가 N인 수식이 있다. 수식은 0보다 크거나 같고, 9보다 작거나 같은 정수와 연산자(+, -, ×)로 이루어져 있다. 
 
 연산자 우선순위는 모두 동일하기 때문에, 수식을 계산할 때는 왼쪽에서부터 순서대로 계산해야 한다. 
 
 수식에 괄호를 추가하면, 괄호 안에 들어있는 식은 먼저 계산해야 한다. 
 
 단, 괄호 안에는 연산자가 하나만 들어 있어야 한다. 예를 들어, 3+8×7-9×2에 괄호를 3+(8×7)-(9×2)와 같이 추가했으면, 식의 결과는 41이 된다. 
 
 하지만, 중첩된 괄호는 사용할 수 없다. 즉, 3+((8×7)-9)×2, 3+((8×7)-(9×2))은 모두 괄호 안에 괄호가 있기 때문에, 올바른 식이 아니다.

 수식이 주어졌을 때, 괄호를 적절히 추가해 만들 수 있는 식의 결과의 최댓값을 구하는 프로그램을 작성하시오.
  
 추가하는 괄호 개수의 제한은 없으며, 추가하지 않아도 된다.
  
 시간 : 124ms
 */

public class BOJ_16637 {
	static int N;
	static List<Integer> nums = new ArrayList<>();
	static List<Character> oper = new ArrayList<>();
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			// 수식에 들어가는 수가 0-9까지 즉, 한자리 숫자이므로 숫자와 연산자가 번갈아 나온다.
			// 짝수에는 숫자, 홀수에는 연산자
			if(i%2==0) nums.add(br.read()-'0');
			else oper.add((char) br.read());
		}
		dfs(0,nums.get(0));
		System.out.println(max);
		
	}
	private static void dfs(int idx, int res) {
		if(idx >= oper.size()) { // 기저조건 : 인덱스 값이 연산자 리스트의 크기보다 크거나 같으면 종료
			max = Math.max(max, res);
			return;
		}
		
		// 괄호가 없는 경우
		int temp = calc(res,oper.get(idx),nums.get(idx+1));
		dfs(idx+1, calc(res, oper.get(idx), nums.get(idx+1)));
		
		// 괄호가 있는 경우
		// 맨 뒤부터 괄호를 묶음
		if (idx + 1 < oper.size()) {
			// result의 오른쪽에 있는 값에 괄호가 있다고 생각하고 여기 먼저 계산한다.
			temp = calc(nums.get(idx+1),oper.get(idx+1),nums.get(idx+2));
			// 하나의 연산자가 선택되면 바로 다음 연산자는 괄호에 못 들어가므로 +2 해준다.
			dfs(idx+2, calc(res,oper.get(idx),temp));
		}
		
	}
	private static int calc(int a, char op, int b) {
		switch(op) {
		case '+':
			return a+b;
		case '-':
			return a-b;
		default:
			return a*b;
			
		}
	}
}
