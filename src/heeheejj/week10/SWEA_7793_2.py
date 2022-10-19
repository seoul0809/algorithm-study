# 오! 나의 여신님
# 파이썬 지원을 안해서 정답확인못함..
# bfs 사용 ver
import sys
from collections import deque
dx =[-1, 0, 1, 0]
dy = [0, 1, 0, -1]

sys.stdin = open("input.txt", "r")

def printArray(arr):
    print("======================")
    for i in range(N):
        print(arr[i])
T = int(input())

def expandDevil():
    devilCnt = len(devilPos)
    temp = devilPos[:]
    for x, y in temp:
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx < 0 or ny < 0 or nx >= N or ny >= M:
                continue
            if _map[nx][ny] == 'S' or _map[nx][ny]  == '.': # 악마 확장되는 경우
                _map[nx][ny] = '*'
                devilPos.append((nx, ny))
            else:   # 여신이나 돌이 있는 위치에는 악마 확장 X, 이미 악마 있는 경우에도 pass
                continue

def bfs(startX, startY):
    global result
    queue = deque()
    queue.append((startX, startY))
    visited[startX][startY] = True

    while queue:
        x, y = queue.popleft()
        expandDevil()
        #printArray(_map)
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx < 0 or ny < 0 or nx >= N or ny >= M:
                continue
            if visited[nx][ny]:
                continue
            if _map[nx][ny] == '.':
                result += 1
                visited[nx][ny] = True
                queue.append((nx, ny))
                # if visited[nx][ny]:
                #     continue
                # else:
                #     result += 1
                #     visited[nx][ny] = True
                #     queue.append((nx, ny))
                    #print(f"여신 이동: nx: {nx}, ny: {ny}, result: {result}")
            elif _map[nx][ny] == 'D':
                result += 1
                return True # 여신에게 도착하면 True 리턴
    return False    # 여신에게 도착할 수 없으면 False 리턴

for t in range(1, T+1):
    N, M = map(int, input().split())
    _map = list()
    visited = [[False]*M for _ in range(N)]
    devilPos = list()
    startX, startY = -1, -1 # 수연이의 x, y좌표
    #dstX, dstY, = -1, -1
    result = 0
    for i in range(N):
        line = list(input())
        for j in range(M):
            if line[j] == '*':
                devilPos.append((i, j))
            elif line[j] == 'S':
                startX, startY = i, j
        _map.append(line)

    if bfs(startX, startY):
        print(f"#{t} {result}")
    else:
        print(f"#{t} GAME OVER")