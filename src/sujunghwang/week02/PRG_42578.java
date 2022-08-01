import java.util.HashMap;
import java.util.Map;
// https://school.programmers.co.kr/learn/courses/30/lessons/42578
class Solution {
    public static int solution(String[][] clothes) {
        int answer = 1;
        // 같은 이름을 가진 의상은 존재하지 않는다는 조건이 있으므로 종류별 개수를 담는 Map 생성
        Map<String, Integer> map = new HashMap<>();  
        for (String[] cloth : clothes) {
        	if(map.keySet().contains(cloth[1])) {
        		map.put(cloth[1], map.get(cloth[1])+1);  // 옷의 종류가 이미 key에 있으면 value + 1
        	}else {
            	map.put(cloth[1], 1); // 옷의 종류가 key에 없다면 value에 1 넣기
        	}
		}
        for (String key : map.keySet()) {
        	answer *= map.get(key)+1; // 각 종류의 옷을 입지 않는 경우의 수까지 생각해서 +1 한 값을 곱해줌
		}
        return answer-1; // 아무것도 입지 않는 경우는 빼야 하기 때문에 -1을 해줌
    }
}

public class PRG_42578 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[][][] arr = {{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}},{{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}}};
		for (int i = 0; i < arr.length; i++) {
			System.out.println(Solution.solution(arr[i]));
		}
	}
}


