# 플로이드
# 플로이드 워셜 알고리즘
# 모든 지점에서 다른 모든 지점까지의 최단 경로를 모두 구해야 하는 경우

import sys

sys.stdin = open("input.txt", "r")
input = sys.stdin.readline

INF = int(1e9)

n = int(input())
m = int(input())

graph = [[INF] * (n + 1) for _ in range(n + 1)]

for i in range(1, n + 1):
  for j in range(1, n + 1):
    if i == j:
      graph[i][j] = 0

for i in range(m):
  a, b, c = map(int, input().rstrip().split())
  graph[a][b] = min(c, graph[a][b])

for k in range(1, n + 1):
  for a in range(1, n + 1):
    for b in range(1, n + 1):
        if a == b:
            continue
        graph[a][b] = min(graph[a][b], graph[a][k] + graph[k][b])

for a in range(1, n + 1):
  for b in range(1, n + 1):
    if graph[a][b] == INF:
      print("0", end = ' ')
    else:
      print(graph[a][b], end = ' ')
  print("")