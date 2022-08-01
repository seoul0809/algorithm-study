

public class PRG_1877 {
	
	public static boolean solution(int[] arr){
        boolean answer = true;
        
        boolean[] check = new boolean[arr.length];
        for(int i : arr){
            if(i > arr.length) {
                answer = false;
                break;
            }
            if(check[i-1] == true) {
                answer = false;
                break;
            }
            check[i-1] = true;
        }
        return answer;
    }
	
	public static void main(String[] args) {
		int[][] arr = {{4,1,3,2},{4,1,3}};
		for (int[] a : arr) {
			System.out.println(solution(a));
		}
	}	
}

