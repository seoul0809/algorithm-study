package week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 하루에 한 시간 단위로 일을 하거나 일을 쉬어도 된다. 하루에 한 시간 일하면 피로도는 A 만큼 쌓이고 일은 B 만큼 처리할 수 있다.

만약에 한 시간을 쉰다면 피로도는 C 만큼 줄어든다. 단, 피로도가 음수로 내려가면 0으로 바뀐다. 당연히 일을 하지 않고 쉬었기 때문에 처리한 일은 없다.

피로도를 최대한 M 을 넘지 않게 일을 하려고 한다. M 를 넘기면 일하는데 번아웃이 와서 이미 했던 일들도 다 던져버리고 일을 그만두게 된다.

번아웃이 되지 않도록 일을 할때 하루에 최대 얼마나 일을 할 수 있는지 구해보자. 하루는 24시간이다.

입력
첫 번째 줄에 네 정수 A, B, C, M이 공백으로 구분되어 주어진다.

맨 처음 피로도는 0이다.
 */
public class BOJ_22864 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int time = 0, sum = 0, p = 0; // 시간, 처리한 일, 피로도
		if (a <= m){ // 일을 할 때 쌓이는 피로도가 최대 피로도보다 크면 일을 할 수 없다.
			while (time < 24) {
				if (p + a <= m) { // 피로도 + 일 할 때 쌓이는 피로도 <= 최대 피로도
					p += a;  // 피로도 누적
					sum += b; // 처리한 일 누적
				}else {
					p = p - c < 0 ? 0 : p - c; // 피로도가 음수가 되면 0으로 바뀜
				}
				time += 1;
			}
		}
		System.out.println(sum);
	}

}
