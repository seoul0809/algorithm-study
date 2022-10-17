# 퇴사
# dynamic programming

# 맨 뒤 인덱스부터 접근한다.
# n번째 날 기준의 최대 수익(dp)은 두 가지 경우 중 더 큰 값이다. 
# 1. n번째 날을 선택 안했을 경우: n+1번째날 기준으로한 최대 수익(dp)
# 2. n번쨰 날을 선택 했을 경우: n번째날의 수익 + (n+(n번쨰날의 상담의 기간))번째 날 기준으로 한 최대 수익(dp)

import sys
sys.stdin = open("input.txt","r")
input = sys.stdin.readline

N = int(input())
schedule = [list(map(int, input().split())) for _ in range(N)]

dp = [0 for _ in range(N+1)]
for i in range(N-1, -1, -1):
  if i + schedule[i][0] > N: # (i+1)일 + 해당 일의 Ti 더했을 떄 8일이 넘어가면
    dp[i] = dp[i+1]
  else:
    dp[i] = max(schedule[i][1]+dp[i+schedule[i][0]], dp[i+1])

print(dp[0])