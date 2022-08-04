import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/*
  BOJ 6198: 옥상 정원 꾸미기
  > https://www.acmicpc.net/problem/6198
  > N개의 빌딩이 있다. 모든 빌딩은 일렬로 서있고, 오른쪽으로만 볼 수 있다.
  > 자신이 위치한 빌딩보다 높거나 같은 빌딩이 있으면 그 다음 빌딩은 볼 수 없다.
  > 관리인들이 볼 수 있는 총 빌딩의 수는?
  
  [조건]
  - 빌딩 개수 N: 1 <= N <= 80,000
    => 최대로 볼 수 있는 빌딩 수(count)는 대략 8만 * 8만 / 2 = 32억으로 long을 써야함
  - 빌딩 높이 h: 1 <= h <= 10^9 (int)
  
  [풀이 방법]
   1. 만약 스택이 비어있다면, 빌딩의 높이를 스택에 넣는다.
   2. 만약 스택이 비어있지 않다면, 현재 빌딩의 높이와 바로 전 빌딩의 높이(stack.top())를 비교한다.
      2.1. 현재 빌딩의 높이가 전 빌딩의 높이보다 높다면, 전 빌딩은 현재 빌딩을 볼 수 없으므로 pop한다.
           pop된 빌딩을 볼 수 있는 빌딩들은 아직 스택에 남아있다. 따라서 스택의 크기를 count에 더해준다.
      2.2. 스택 안에 현재 빌딩을 볼 수 있는 빌딩들만 남을 때까지 2.1.을 반복한다.
      2.3. 현재 빌딩을 스택에 push한다.
   3. 모든 빌딩을 스택에 넣었다면, 아무것도 볼 수 없는 마지막 빌딩을 제외하고, 다른 빌딩들 간 볼 수 있는 빌딩 수를 계산한다.
 */


public class BOJ_6198 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine().trim());
		Stack<Integer> stack = new Stack<>();
		
		
		long count = 0;		
		for (int i = 0; i < N; ++i) {
			int height = Integer.parseInt(br.readLine().trim());
			
			if (stack.isEmpty()) {
				stack.push(height);
			}
			else {
				while (!stack.isEmpty() && height >= stack.peek()) {
					stack.pop();
					count += stack.size();
				}
				stack.push(height);
			}
		}
		
		while (!stack.isEmpty()) {
			stack.pop();
			count += stack.size();
		}
		
		System.out.println(count);
		
	}
}
