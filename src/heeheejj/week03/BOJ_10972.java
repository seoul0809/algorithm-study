package heeheejj.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    문제 이름: 다음 순열

    1. 뒤에서부터 앞으로, 오름차순이 깨질때(n-1번째 수 < n번째 수가 될 때)까지 i를 -1한다.
       i를 기준으로, 왼쪽 영역: 0 ~ i-1 / 오른쪽 영역: i ~ 끝 이렇게 두 부분으로 나눌 수 있다.
    2. i번째수와 j번째 수를 비교한다. 이 때 j는 오른쪽 영역에서 끝부터 앞으로 이동한다.
       i-1번째 수 < j번째 수 일때 두 수를 swap한다.
    3. 오른쪽 영역의 숫자들을 오름차순으로 정렬해준다.
 */
public class BOJ_10972 {

    public static void swap(int [] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean nextPermutation(int[] a) {
        int i = a.length-1;

        // 뒤에서부터 앞으로, 오름차순이 깨질때까지 i를 -1한다.
       while (i > 0 && a[i-1] >= a[i]) {
            i--;
        }

       // 만약 i가 0 이하이면 그 순열은 마지막 순열이므로 다음 순열이 없다.
        if (i <= 0) return false;

        int j = a.length-1;
        while (a[j] <= a[i-1]) {    // 뒤에서부터 앞으로, 아까 구한 i-1보다 큰 값이 나올때까지 j를 -1한다.
            j--;
        }

        swap(a, i-1, j);    // i, j 둘 다 구하면 i-1, j를 서로 맞바꾼다.

        j = a.length-1;
        while (i < j) {         //  i부터 순열의 끝까지 오름차순이되도록 한다.
            swap(a, i, j);
            i++;
            j--;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine(), " ");

        int[] arr = new int[N];
        for (int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if(nextPermutation(arr)){
            for(int i = 0; i < N; i++){
                sb.append(arr[i]).append(" ");
            }
        } else{
            sb.append("-1");
        }

        System.out.println(sb);
    }
}
