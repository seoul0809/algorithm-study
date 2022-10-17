# 1753: 최단 경로

import sys
import heapq

sys.stdin = open("input.txt", "r")
input = sys.stdin.readline

INF = sys.maxsize

# 노드 개수, 간선 개수 입력받기 n, m
n, m = map(int, input().split())
# 시작 노드 번호 입력받기
start = int(input())

# 노드, 간선 정보 담는 테이블 초기화
graph = [[] for i in range(n+1)]
# 최소 비용 테이블 초기화
distance = [INF] * (n + 1)

# 모든 간선 정보 입력받기 (a b c) -> a번노드에서 b번노드로 가는 비용이 c
for _ in range(m):
  a, b, c = map(int, input().split())
  graph[a].append((c, b))

def dijkstra(start):
  q = []
  heapq.heappush(q, (0, start))
  distance[start] = 0

  while q:  # 큐가 빌 때 까지
    dist, now = heapq.heappop(q)
    if distance[now] < dist:
      continue
    for w, next in graph[now]:
      cost = dist + w
      if cost < distance[next]:
        distance[next] = cost
        heapq.heappush(q, (cost, next))

dijkstra(start)

for i in range(1, n+1):
  if distance[i] != INF:
    print(distance[i])
  else:
    print("INF")