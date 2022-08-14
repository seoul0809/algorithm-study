
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
//https://www.acmicpc.net/problem/1655
/*
 수가 하나씩 입력될 때마다 입력된 수들 중 중앙값을 출력하는 문제
 
 만약 입력된 수가 1 2 3 4 5 라면 중앙값인 3을 기준으로 앞쪽은 오름차순, 뒤쪽은 내림차순 정렬
 
 그러므로 우선순위 큐를 2개 써서 해결
 
 시간 : 492ms 
 */
public class BOJ_1655 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> min = new PriorityQueue<>();
		PriorityQueue<Integer> max = new PriorityQueue<>((a,b)->b-a);
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			if(max.isEmpty() || max.size()==min.size()) 
				max.offer(Integer.parseInt(br.readLine()));
			else if(max.size() != min.size()) 
				min.offer(Integer.parseInt(br.readLine()));
			
			if(!max.isEmpty() && ! min.isEmpty() && (max.peek() > min.peek())) {
				min.offer(max.poll());
				max.offer(min.poll());
			}
			sb.append(max.peek()).append("\n");
		}
		System.out.println(sb);
	}

}
