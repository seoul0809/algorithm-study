# 특정 거리의 도시 찾기
# 1692ms, 194560kb
import sys
import heapq

sys.stdin = open("input.txt", "r")
input = sys.stdin.readline

INF = sys.maxsize

n, m, k , x = map(int, input().split())
graph = [[] for i in range(n+1)]
distance = [INF] * (n + 1)

for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append((1, b))

def dijkstra(start):
  q = []
  heapq.heappush(q, (0, start))
  distance[start] = 0

  while q:
    dist, now = heapq.heappop(q)
    if distance[now] < dist:
      continue
    for w, next in graph[now]:
      cost = dist + w
      if cost < distance[next]:
        distance[next] = cost
        heapq.heappush(q, (cost, next))

dijkstra(x)

flag = False
for i in range(1, n+1):
  if distance[i] == k:
      print(i)
      flag = True
if not flag:
    print(-1)