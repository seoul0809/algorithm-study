# 파이프 옮기기 1
# 완탐 - 시간초과ㅜ, dp로 다시 풀기
import sys

'''
status, idx 둘 다 0(HOR): 가로, 1(DIAG): 대각선, 2(VER): 세로
status  |  idx  | dx[status][idx] | dy[status][idx]
---------------------------------------
   0    |   0   |        0        |       1
        |   1   |        1        |       1
---------------------------------------
   1    |   0   |        0        |       1
        |   1   |        1        |       1
        |   2   |        1        |       0
---------------------------------------
   2    |   0   |        0        |       0
        |   1   |        1        |       1
        |   2   |        1        |       0
'''
HOR, DIAG, VER = 0, 1, 2
dx = [[0, 1], [0, 1, 1], [0, 1, 1]]
dy = [[1, 1], [1, 1, 0], [0, 1, 0]]

result = 0
depth = 0

def dfs(status, x, y, depth):
    if (x == N and y == 1) or (x == 1 and y == N):
        return
    if x == N and y == N:
        global result
        result += 1
        return
    size = len(dx[status])
    for nextStatus in range(size):
        if (status == HOR and nextStatus == VER) or (status == VER and nextStatus == HOR):
            continue
        nx, ny = x + dx[status][nextStatus], y + dy[status][nextStatus]
        if x == nx and y == ny:
            continue
        if map[nx][ny] == 1:
            continue
        if nextStatus == 1 and (map[x+1][y] == 1 or map[x][y+1] == 1):
            continue
        dfs(nextStatus, nx, ny, depth+1)

sys.stdin = open("input.txt","r")
input = sys.stdin.readline
N = int(input())
map = [[1 for _ in range(N+2)]]+[[1]+list(map(int, input().split()))+[1] for _ in range(N)]
if map[N][N] == 1:
    print(0)
    exit()
map.append([1]*(N+2))
dfs(HOR, 1, 2, 0)
print(result)