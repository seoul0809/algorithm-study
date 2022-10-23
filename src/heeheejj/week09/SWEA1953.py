# 탈주범 검거
import sys
from collections import deque

sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
T = int(input())

dx = [[], [-1, 0, 1, 0], [-1, 1], [0, 0], [-1, 0], [0, 1], [0, 1], [0, -1]]
dy = [[], [0, 1, 0, -1], [0, 0], [-1, 1], [0, 1], [1, 0], [-1, 0], [-1, 0]]

def bfs(x, y):
    global result
    queue = deque()
    queue.append((x, y))
    time = 1
    while queue:
        x, y = queue.popleft()
        type = tmap[x][y]
        size = len(dx[type])
        for i in range(size):
            nx = x + dx[type][i]
            ny = y + dy[type][i]
            if nx < 0 or ny < 0 or nx >= N or ny >= M:
                continue
            if tmap[nx][ny] == 0 or visited[nx][ny] != 0:
                continue

            nextType = tmap[nx][ny]
            nextSize = len(dx[nextType])
            for j in range(nextSize):
                _nx = x + dx[nextType][j]
                _ny = y + dy[nextType][j]
                if -1*dx[type][i] == dx[nextType][j] and -1*dy[type][i] == dy[nextType][j]:
                    queue.append((nx, ny))
                    visited[nx][ny] = visited[x][y] + 1

for t in range(1, T+1):
    N, M, R, C, L = map(int, input().split())   # 세로 크기, 가로 크기, 맨홀뚜껑이 위치한장소 세로위치, 가로위치, 소요된시간
    tmap = [list(map(int, input().split())) for _ in range(N)]
    visited = [[0]*M for _ in range(N)]
    visited[R][C] = 1


    bfs(R, C)

    result = 0
    for i in range(N):
        for j in range(M):
            if 0 < visited[i][j] <= L:
                result += 1
    print(f"#{t} {result}")