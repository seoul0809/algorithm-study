import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/* 
	BOJ 2164: 카드2
    https://www.acmicpc.net/problem/2164
    
    큐를 활용해서 푸는 문제.
    카드가 제일 작은 수가 위에 올라오게 순서대로 주어져있을 때
    1) 제일 위에 있는 카드는 버리고(queue.poll())
    2) 다음에 있는 카드는 제일 아래로 보내는 것(queue.offer(queue.poll())을 반복했을 때
    제일 마지막에 남아있는 카드를 구하면 된다. 
    
    시간: 188ms
 */

public class BOJ_2164 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 1; i < n + 1; i++) {
			queue.offer(i);
		}

		while (queue.size() > 1) {
			queue.poll();
			queue.offer(queue.poll());
		}

		System.out.println(queue.poll());

	}

}
