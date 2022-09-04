# 경로 찾기
# 216ms, 116960kb

import sys
import heapq

sys.stdin = open("input.txt", "r")
input = sys.stdin.readline

INF = sys.maxsize

n = int(input())
graph = [[] for i in range(n)]
for i in range(n):
    line = list(map(int, input().split()))
    for j in range(n):
        if line[j] == 1:
            graph[i].append((1, j))

distance = [[INF] * (n + 1) for _ in range(n)]

def dijkstra(start):
  q = []
  heapq.heappush(q, (0, start))
  # distance[start][start] = 0

  while q:
    dist, now = heapq.heappop(q)
    if distance[start][now] < dist:
      continue
    for w, next in graph[now]:
      cost = dist + w
      if cost < distance[start][next]:
        distance[start][next] = cost
        heapq.heappush(q, (cost, next))

for i in range(n):
    dijkstra(i)
    for j in range(n):
        x = distance[i][j]
        if x != INF:
            print(1, end=' ')
        else:
            print(0, end=' ')
    print()