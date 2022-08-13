package week4;

import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;

public class day4_boj_1655 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer> () {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			int number = Integer.parseInt(br.readLine());
			if(maxHeap.size() > minHeap.size()) {
				minHeap.add(number);
				
				if(minHeap.peek() < maxHeap.peek()) {
					minHeap.add(maxHeap.poll());
					maxHeap.add(minHeap.poll());
				}
			} else {
				maxHeap.add(number);
				
				if(!minHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
					minHeap.add(maxHeap.poll());
					maxHeap.add(minHeap.poll());
				}
			}
			sb.append(maxHeap.peek() + "\n");
		}
		System.out.println(sb);
	}
}
