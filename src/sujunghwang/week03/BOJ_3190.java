
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3190
/*
'Dummy' 라는 도스게임이 있다. 이 게임에는 뱀이 나와서 기어다니는데, 사과를 먹으면 뱀 길이가 늘어난다. 뱀이 이리저리 기어다니다가 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.

게임은 NxN 정사각 보드위에서 진행되고, 몇몇 칸에는 사과가 놓여져 있다. 보드의 상하좌우 끝에 벽이 있다. 게임이 시작할때 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1 이다. 뱀은 처음에 오른쪽을 향한다.

뱀은 매 초마다 이동을 하는데 다음과 같은 규칙을 따른다.

먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
사과의 위치와 뱀의 이동경로가 주어질 때 이 게임이 몇 초에 끝나는지 계산하라. 
 */
public class BOJ_3190 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Queue<String> que = new LinkedList<>();  // 뱀의 몸이 있는 위치를 담을 큐
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int cur_r = 0; int cur_c = 0;  // 현재 뱀의 머리 위치
		List<String> apples = new ArrayList<>(); // 사과의 위치를 담을 리스트
		boolean check = false;  
		int[][] moves = {{0,1},{-1,0},{0,-1},{1,0}};  // 오른쪽, 위, 왼쪽, 아래
		
		int[] temp = new int[2];  
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				temp[j] = Integer.parseInt(st.nextToken())-1;
			}
			apples.add(Arrays.toString(temp));  // contains로 값을 비교할 것이므로 Arrays.toString을 이용
		}
		
		int L = Integer.parseInt(br.readLine());
		int time = 0;
		String move = null;
		int[] position = {0,0};
		String dir = "D";
		int m = 1;
		que.add(Arrays.toString(position));
		
		Loop:for (int i = 0; i < L; i++) {
			move = br.readLine();
			if(dir.equals("L")) m = ++m%4; // moves의 순서를 보면 인덱스가 1 늘어나는 것이 왼쪽으로 회전하는 상황이 된다.
			else m = m == 0? 3 : --m;  // 오른쪽으로 회전할 경우
			// 현재 시간이 입력된 시간보다 같아지면 종료된다
			while(time < Integer.parseInt(move.split(" ")[0])) { 
				// 현재 좌표에 회전한 방향의 좌표를 더해준다.
				cur_r += moves[m][0];
				cur_c += moves[m][1];
				position[0] = cur_r;
				position[1] = cur_c;
				time++;
				// 뱀의 머리가 벽 또는 자기자신의 몸과 부딪히는 경우 break
				if(que.contains(Arrays.toString(position)) || cur_r < 0 || cur_r >= N || cur_c < 0 || cur_c >= N) {
					cur_r += moves[m][0];
					cur_c += moves[m][1];
					check = true;
					break Loop; 
				}
				que.add(Arrays.toString(position));
				// 사과를 먹지 않을 경우 뱀의 몸 길이가 늘어나지 않으므로 que에서 마지막 값을 지운다.
				if(!apples.contains(Arrays.toString(position))) {
					que.remove();
				}else { // 사과를 먹을 경우 사과의 위치를 지운다.
					apples.remove(Arrays.toString(position));
				}
			}
			dir = move.split(" ")[1];
		}
		// break로 for문을 탈출하지 않고 끝까지 돌아서 나왔다면 아직 게임이 끝나지 않았으므로 다시 반복을 돌린다.
		if (!check) {
			if (dir.equals("L"))
				m = ++m % 4;
			else
				m = m == 0 ? 3 : --m;
			while (0 <= cur_r && cur_r < N && 0 <= cur_c && cur_c < N) {
				cur_r += moves[m][0];
				cur_c += moves[m][1];
				time++;
				position[0] = cur_r;
				position[1] = cur_c;
				if (que.contains(Arrays.toString(position)))
					break;
				que.add(Arrays.toString(position));
				if (!apples.contains(Arrays.toString(position))) {
					que.remove();
				} else {
					apples.remove(Arrays.toString(position));
				}
			}
		}
		System.out.println(time);
		
	}

}
