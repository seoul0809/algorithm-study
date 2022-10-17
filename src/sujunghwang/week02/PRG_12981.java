import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// https://school.programmers.co.kr/learn/courses/30/lessons/12981
public class PRG_12981 {
	
	public static int[] solution(int n, String[] words) {
        int[] answer = {0,0};
        List<String> list = new ArrayList<>();  // 끝말잇기중인 단어를 담을 배열
        list.add(words[0]); // list에 미리 첫번째 단어를 넣어놓음
        for (int i = 1; i < words.length; i++) { // 첫번째 단어를 미리 넣어놨기 때문에 2번째 단어부터 반복
        	String LastWord = words[i-1];
        	// 중복 단어 처리                  앞 단어의 마지막 문자와 이어지지 않는 단어 처리
			if(list.contains(words[i]) || (LastWord.charAt(LastWord.length()-1) != words[i].charAt(0))) {
				answer = new int[]{i%n + 1, i/n + 1}; 
				break;
			}
			list.add(words[i]);
		}

        return answer;
    }
	
	public static void main(String[] args) {
		String[][] arr = {{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"}
		,{"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"}
		,{"hello", "one", "even", "never", "now", "world", "draw"}};
		int[] nums = {3,5,2};
		for (int i = 0; i < nums.length; i++) {
			System.out.println(Arrays.toString(solution(nums[i], arr[i])));
		}
	}

}
