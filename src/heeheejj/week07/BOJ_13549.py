# 숨바꼭질 3
# 268ms, 120100kb
# 다익스트라 + dp 이용..!

import sys
import heapq

sys.stdin = open("input.txt", "r")
input = sys.stdin.readline

INF = sys.maxsize

n, k = map(int, input().split())
dp = [INF] * (100001)   # 위치에 따라 해당 위치로 이동하는 데 걸린 최소시간을 저장하는 배열

def dijkstra(start, end):
    if end <= start:    # 수빈이보다 동생이 왼쪽에 있다면 뺄셈을 계속 해주는수밖에 없다.
        print(start - end)
        return

    q = []
    heapq.heappush(q, (0, start))
    dp[start] = 0

    while q:  # 큐가 빌 때 까지
        cost, now = heapq.heappop(q)    # cost: 걸린 최소시간, now: 현재 위치
        arr = [now*2, now-1, now+1]
        for i in range(3):
            x = arr[i]
            if x < 0 or x > 100000:
                continue

            if i == 0 and dp[x] == INF: # 현재 위치에서 2배한 위치일때 + 첫 방문인지 확인
                dp[x] = cost
                heapq.heappush(q, (cost, x)) # 가중치(걸린 시간)는 그대로 유지하고, x = now*2
            elif dp[x] == INF:  # (현재 위치에서 -1 or +1한 위치일 때 +) 첫 방문인지 확인
                dp[x] = cost + 1
                heapq.heappush(q, (cost+1, x)) # 가중치 +1
    print(dp[end])

dijkstra(n, k)