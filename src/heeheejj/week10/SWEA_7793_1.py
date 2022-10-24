# 오! 나의 여신님
# 파이썬 지원을 안해서 정답확인못함..
# bfs + bfs ver
import sys
from collections import deque

sys.stdin = open("input.txt", "r")

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

T = int(input())

def bfs(x, y):
    queue = deque()
    queue.append((x, y))
    # print(queue)
    # print(f"nx: {x}, ny: {y}, dist[x][y]: {dist[x][y]}")
    _map[x][y] = '0'
    while queue:
        x, y = queue.popleft()

        # 악마의 움직임
        devilCnt = len(devilPos)
        while devilCnt > 0:
            devilX, devilY = devilPos.popleft()

            for i in range(4):
                nx = devilX + dx[i]
                ny = devilY + dy[i]
                if nx < 0 or nx >= N or ny < 0 or ny >= M:
                    continue
                if _map[nx][ny] == 'X' or _map[nx][ny] == 'D':
                    continue
                _map[nx][ny] = '*'
                devilPos.append((nx, ny))
            devilCnt -= 1

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx == dst[0] and ny == dst[1]:
                if _map[nx][ny] == 'D': # 도착지가 D가 아닌 것으로 바뀌어있으면 GAME OVER
                    dist[nx][ny] = dist[x][y] + 1
                break
            if nx < 0 or nx >= N or ny < 0 or ny >= M:
                continue
            if _map[nx][ny] != '.':
                continue

            dist[nx][ny] = dist[x][y]+1
            queue.append((nx, ny))
        #     print(queue)
        #     print(f"nx: {nx}, ny: {ny}, dist[nx][ny]: {dist[nx][ny]}")
        # for j in range(N):
        #     print(_map[j])

for t in range(1, T+1):
    N, M = map(int, input().split())
    _map = list()
    dist = [[0]*M for _ in range(N)]

    devilPos = deque()
    for i in range(N):
        line = list(input())
        for j in range(M):
            if line[j] == 'S':
                src = [i, j]
            elif line[j] == 'D':
                dst = [i, j]
            elif line[j] == '*':
                devilPos.append((i, j))
        _map.append(line)

    # dist[dst[0]][dst[1]] = 'GAME OVER'  # GAME OVER로 초기화
    bfs(src[0], src[1])
    if dist[dst[0]][dst[1]] != 0:
        print(f"#{t} {dist[dst[0]][dst[1]]}")
    else:
        print(f"#{t} GAME OVER")
