import java.util.HashMap;
import java.util.Map;
// https://school.programmers.co.kr/learn/courses/30/lessons/42576?language=java#
public class PRG_42576 {
	
	class Solution {
	    public static String solution(String[] participant, String[] completion) {
	        String answer = "";	        
	        Map<String, Integer> map = new HashMap<String, Integer>();
	        for (String name : completion) {            // map에 completion의 이름을 Key, 등장 횟수를 Value로 하는 map을 만듦
				if(map.keySet().contains(name))
					map.put(name, map.get(name)+1);     // 값이 이미 map에 있다면 지금 있는 값에 +1
				else map.put(name, 1);                  // 엾다면 1을 넣어줌
			}
	        for (String name : participant) {
				if(!map.keySet().contains(name) || map.get(name) == 0) {  // name이 map의 키에 없거나 value가 0이 됐을 때의 값을 구하면 된다.
					answer = name;
					break;
				}
				map.put(name, map.get(name)-1);
			}
	        
	        
	        return answer;
	    }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[][] participants = {{"leo", "kiki", "eden"},{"marina", "josipa", "nikola", "vinko", "filipa"},{"mislav", "stanko", "mislav", "ana"}};
		String[][] completions = {{"eden", "kiki"},{"josipa", "filipa", "marina", "nikola"},{"stanko", "ana", "mislav"}};
		for (int i = 0; i < completions.length; i++) {
			System.out.println(Solution.solution(participants[i], completions[i]));
		}
	}

} 