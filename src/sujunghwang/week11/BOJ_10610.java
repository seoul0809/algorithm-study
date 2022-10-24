import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/10610
/*
 숫자 N에 포함된 숫자들을 섞어 30의 배수가 되는 가장 큰 수 출력, 존재하지 않으면 -1 출력
 
 30의 배수는 3의 배수이면서 1의 자리가 0이어야 한다.
 3의 배수는 각 자리수를 모두 합한 것이 3으로 나누어 떨어져야 한다.
 
 가장 큰 수는 위 조건을 만족하면서 내림차순으로 정렬된 수이다.
 
 메모리 : 16512	시간 : 188 

 */


public class BOJ_10610 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] nums = br.readLine().toCharArray();
        
        Arrays.sort(nums);
        
        // 30의 배수 : 3의 배수이면서 1의 자리가 0
        if(nums[0] == '0') { // nums가 정렬된 상태이므로 첫 번째 수가 0이 아니면 0이 없다는 뜻
        	int sum = 0;
        	for (char c : nums) {
				sum += c-'0';
			}
        	if(sum % 3 == 0) { // 3의 배수인지 체크
        		
        		// 오름차순으로 정렬되어 있기 때문에 문자열을 뒤집어준다.
        		System.out.println(new StringBuffer(String.valueOf(nums)).reverse());
        	} else System.out.println(-1);
        } else System.out.println(-1);
        
	}

}
