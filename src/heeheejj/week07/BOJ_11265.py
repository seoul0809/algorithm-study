# 끝나지 않는 파티
# 플로이드 워셜
import sys

sys.stdin = open("input.txt", "r")
input = sys.stdin.readline

INF = int(1e9)

n, m = map(int, input().rstrip().split())
graph = [list(map(int, input().split())) for _ in range(n)]

for k in range(n):
  for a in range(n):
    for b in range(n):
        if a == b:
            continue
        graph[a][b] = min(graph[a][b], graph[a][k] + graph[k][b])

for i in range(m):
  a, b, c = map(int, input().rstrip().split())
  if graph[a-1][b-1] <= c:
      print("Enjoy other party")
  else:
      print("Stay here")