# 게리맨더링
# 184ms

# 조합을 이용해 sector1, sector2를 나눈다.
# 각 섹터에서 bfs를 돌려서 방문한 횟수가 조합을 통해 얻은 섹터의 원소의 개수가 같아야 선거구가 잘 나눠진 것이다.
# 선거구가 잘 나눠졌으면 각 선거구의 인구수를 더해서 그 차이를 구한다.
# 그 차이가 이전까지의 최솟값보다 작으면 갱신해준다.
# 만약 최솟값(result변수)이 99999로 초기값과 같다면 어떠한 방법으로도 두 선거구로 나누지 못한 경우로, -1을 출력한다.

import sys
from itertools import combinations
from collections import deque
sys.stdin = open("input.txt", "r")
input = sys.stdin.readline

def getPopDiff(sec1, sec2):
    sec1_pop, sec2_pop = 0, 0
    for x in sec1:
        sec1_pop += population[x]
    for x in sec2:
        sec2_pop += population[x]
    return abs(sec1_pop - sec2_pop)

def bfs(sector, visited):
    sectorCnt = 1
    queue = deque()
    startNum = sector[0]
    queue.append(startNum)
    visited[startNum] = True

    while queue:
        num = queue.popleft()
        for x in graph[num]:
            if not visited[x] and x in sector:
                queue.append(x)
                visited[x] = True
                sectorCnt += 1
        # print(queue)
    return sectorCnt
N = int(input())
population = [0] + list(map(int, input().split()))
graph = [[0]]

for _ in range(N):
    line = list(map(int, input().split()))
    graph.append(line[1:])

nums = [i for i in range(1, N+1)]
result = 99999
for i in range(1, N//2+1):
    combs = list(combinations(nums, i))
    for comb in combs:
        visited = [False]*(N+1)
        a_cnt = bfs(comb, visited)
        if len(comb) != a_cnt:
            continue

        rest_comb = list()
        for i in range(1, N+1):
            if i not in list(comb):
                rest_comb.append(i)
        b_cnt = bfs(rest_comb, visited)
        if len(rest_comb) == b_cnt:
            result = min(result, getPopDiff(comb, rest_comb))
if result != 99999:
    print(result)
else:
    print(-1)