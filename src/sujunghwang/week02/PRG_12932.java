/*
자연수 n을 뒤집어 각 자리 숫자를 원소로 가지는 배열 형태로 리턴해주세요. 
예를들어 n이 12345이면 [5,4,3,2,1]을 리턴합니다.
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PRG_12932 {
	
	public static int[] solution(long n) { // n은 100만 이하
        List<Integer> num_arr = new ArrayList<>(); 
        while (n>0) {
        	num_arr.add((int) (n%10));  // n이 long 이므로 int로 형변환해서 add
        	n /= 10; // n에는 10으로 나눈 몫을 저장
        }
        int[] answer = num_arr.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(5248130551L)));
	}
}