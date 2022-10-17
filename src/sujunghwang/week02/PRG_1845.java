import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class PRG_1845 {
	
	class Solution {
	    public static int solution(int[] nums) {
	        int answer = 0;
	        // 중복을 제거하기 위해 set으로 변환
	        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
	        
	        // set의 size가 n/2보다 크다면 가장 다양하게 선택할 수 있는 방법은 n/2가 된다
	        if (set.size() >= nums.length/2) answer = nums.length/2;
	        // 그러나 set의 크기가 n/2보다 작다면 set의 size가 가장 다양하게 고룰 수 있는 경우의 수이다. 
	        else answer = set.size();
	        
	        return answer;
	    }
	}

	public static void main(String[] args) {
		int[][] arr = {{3,1,2,3},{3,3,3,2,2,4},{3,3,3,2,2,2}};
		for (int[] i : arr) {
			System.out.println(Solution.solution(i));
		}
		
	}
	
}
