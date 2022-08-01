import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// https://school.programmers.co.kr/learn/courses/30/lessons/12906
public class PRG_12906 {
	
	public static int[] solution(int []arr) {
        int[] answer = {};
        // 연속적으로 나타나는 숫자만 제거하는 것이므로 set이 아닌 list 사용
        List<Integer> list = new ArrayList<>(); 
        list.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
        	// arr의 i번째 값과 리스트의 마지막 값이 다르면 list에 추가
			if(arr[i] != list.get(list.size()-1)) { 
				list.add(arr[i]);
			}
		}

        answer = list.stream().mapToInt(i->i).toArray();
        return answer;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] arr = {{1,1,3,3,0,1,1},{4,4,4,3,3}};
		for (int[] a : arr) {
			System.out.println(Arrays.toString(solution(a)));
		}
	
	}

}
