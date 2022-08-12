package heeheejj.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
    문제 이름: 벽 부수고 이동하기
    시간: 724ms
 */
public class BOJ_2206 {
    static int N;
    static int M;
    static int[][] map;

    static private class Position {
        int x, y, dist;
        boolean isDestroyed;

        public Position(int x, int y, boolean isDestroyed, int dist){   // x좌표, y좌표, 벽을 부쉈는지, 이동 거리
            this.x = x;
            this.y = y;
            this.isDestroyed = isDestroyed;
            this.dist = dist;
        }
    }

    static int bfs(){
        boolean[][][] visited = new boolean[2][N][M];   // 방문 여부를 체크
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(0, 0, false, 1));

        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};

        while(!queue.isEmpty()){
            Position temp = queue.poll();

            if(temp.x == N-1 && temp.y == M-1){
                return temp.dist;
            }
            for(int i = 0; i < 4; i++){
                int nx = temp.x + dx[i];
                int ny = temp.y + dy[i];
                boolean isDestroyed = temp.isDestroyed;
                int dist = temp.dist;

                if(nx < 0 || nx >= N || ny < 0 || ny >= M){
                    continue;
                }

                if(map[nx][ny] == 1 && !isDestroyed){   // 벽을 한번도 부수지 않고 벽을 만났을 때
                    queue.add(new Position(nx, ny, true, dist+1));
                    visited[1][nx][ny] = true;
                } else if(map[nx][ny] == 0){    // 벽이 아닐 때
                    if(!isDestroyed && !visited[0][nx][ny]){
                        queue.add(new Position(nx, ny, false, dist+1));
                        visited[0][nx][ny] = true;
                    } else if(isDestroyed && !visited[1][nx][ny]){
                        queue.add(new Position(nx, ny, true, dist+1));
                        visited[1][nx][ny] = true;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++){
            char[] inputs = in.readLine().toCharArray();
            for(int j = 0; j < M; j++){
                map[i][j] = inputs[j] - '0';
            }
        }

        System.out.println(bfs());
    }
}
